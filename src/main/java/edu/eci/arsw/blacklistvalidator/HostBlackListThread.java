package edu.eci.arsw.blacklistvalidator;

import java.util.ArrayList;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class HostBlackListThread extends Thread{
    private String host;
    private int start, end, occurrences;
    private ArrayList<Integer> foundLists;
    private HostBlacklistsDataSourceFacade skds;

    @Override
    public void run(){
        for (int i = start; i <= end; i++){
            if (skds.isInBlackListServer(i, host)){
                occurrences++;
                foundLists.add(i);
            }
        }
    }

    public int getOcurrences(){
        return occurrences;
    }

    public ArrayList<Integer> getBlackList(){
        return foundLists;
    }
}
