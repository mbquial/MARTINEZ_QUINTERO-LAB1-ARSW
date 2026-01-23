/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.eci.arsw.threads;

/**
 *
 * @author Nikolas Martínez Rivera
 * @author María Belén Quintero
 */
public class CountThread implements Runnable {

    private int a, b;

    CountThread(int[] interval){
        setInterval(interval);
    }

    @Override
    public void run() {
        for(int i = a; i<=b; i++){
            System.out.println(i);
        }
    }

    public void setInterval(int [] interval){
        a = interval[0];
        b = interval[1];
    }

}
