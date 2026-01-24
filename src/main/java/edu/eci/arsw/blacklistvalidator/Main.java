/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.blacklistvalidator;

import java.util.List;
import java.util.Scanner;

/**
 *
 * @author María Belén Quintero
 * @author Nikolas Martinez Rivera
 */
public class Main {

    public static void main(String a[]){
        HostBlackListsValidator hblv=new HostBlackListsValidator();
        
        try (Scanner scan = new Scanner(System.in)) {
            System.out.println("Host: ");
            String host = scan.nextLine();
            System.out.println("Numbers of threads: ");
            int hilitos = scan.nextInt();

            List<Integer> blackListOcurrences=hblv.checkHost(host, hilitos);
            System.out.println("The host was found in the following blacklists:"+blackListOcurrences);
        }
        
    }
    
    //Números de host que tengo: 200.24.34.55 (sale reportado) --- 202.24.34.55 (sale reportado) --- 212.24.24.55 (está limpio)
}
