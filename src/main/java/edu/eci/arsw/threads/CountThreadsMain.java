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
public class CountThreadsMain {

    public static void main(String args[]){
        Thread t1 = new Thread(new CountThread(new int[]{0,99}));
        Thread t2 = new Thread(new CountThread(new int[]{99,199}));
        Thread t3 = new Thread(new CountThread(new int[]{200,299}));

        t1.run();
        t2.run();
        t3.run();

        //t1.start();
        //t2.start();
        //t3.start();
    }
    
}
