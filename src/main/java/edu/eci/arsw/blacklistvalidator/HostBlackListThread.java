package edu.eci.arsw.blacklistvalidator;

import java.util.ArrayList;

import edu.eci.arsw.spamkeywordsdatasource.HostBlacklistsDataSourceFacade;

public class HostBlackListThread extends Thread{
    private String host;
    private int start, end, occurrences;
    private ArrayList<Integer> foundLists;
    private HostBlacklistsDataSourceFacade skds;
    private int checkedListsCount;

    HostBlackListThread(String host, int start, int end, HostBlacklistsDataSourceFacade skds){
        this.host = host;
        this.start = start;
        this.end = end;
        this.occurrences = 0;
        this.foundLists = new ArrayList<>();
        this.skds = skds;

    }

    @Override
    public void run(){
        for (int i = start; i <= end; i++){
            checkedListsCount++;
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

    public int getCheckedListCount(){
        return checkedListsCount;
    }
}
