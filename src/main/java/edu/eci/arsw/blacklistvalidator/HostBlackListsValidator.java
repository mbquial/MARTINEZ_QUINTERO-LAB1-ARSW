/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author María Belén Quintero
 * @author Nikolas Martinez Rivera
 */
public class HostBlackListsValidator {

    private static final int BLACK_LIST_ALARM_COUNT=5;
    
    /**
     * Check the given host's IP address in all the available black lists,
     * and report it as NOT Trustworthy when such IP was reported in at least
     * BLACK_LIST_ALARM_COUNT lists, or as Trustworthy in any other case.
     * The search is not exhaustive: When the number of occurrences is equal to
     * BLACK_LIST_ALARM_COUNT, the search is finished, the host reported as
     * NOT Trustworthy, and the list of the five blacklists returned.
     * @param ipaddress suspicious host's IP address.
     * @return  Blacklists numbers where the given host's IP address was found.
     */
    public List<Integer> checkHost(String ipaddress, int N){
        
        LinkedList<Integer> blackListOcurrences=new LinkedList<>();
        int ocurrencesCount=0;
        HostBlacklistsDataSourceFacade skds=HostBlacklistsDataSourceFacade.getInstance();
        int totalList = skds.getRegisteredServersCount();
        int listsPerThread = totalList/N;
        int checkedListsCount=0;        
        
        HostBlackListThread[] threads = new HostBlackListThread[N];
        for (int i = 0; i < N; i++){
            int start = i * listsPerThread;
            int end;
            if (i == N-1){
                end = totalList - 1;
            } else {
                end = start + listsPerThread - 1;
            }

            threads[i] = new HostBlackListThread(ipaddress, start, end, skds);
            threads[i].start();
        }

        for (int i = 0; i < N; i++){
            try {
                threads[i].join();
            } catch (InterruptedException e) {
                System.out.println("Thread interrupted :(");
            }
        }

        for (HostBlackListThread hilito : threads) {
            ocurrencesCount += hilito.getOcurrences();
            blackListOcurrences.addAll(hilito.getBlackList());
            checkedListsCount += hilito.getCheckedListCount();
        }

        if (ocurrencesCount>=BLACK_LIST_ALARM_COUNT){
            skds.reportAsNotTrustworthy(ipaddress);
        }
        else{
            skds.reportAsTrustworthy(ipaddress);
        }                
        
        LOG.log(Level.INFO, "Checked Black Lists:{0} of {1}", new Object[]{checkedListsCount, skds.getRegisteredServersCount()});
        
        return blackListOcurrences;
    }
    
    
    private static final Logger LOG = Logger.getLogger(HostBlackListsValidator.class.getName());
    
    
    
}
