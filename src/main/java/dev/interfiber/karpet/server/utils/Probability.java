package dev.interfiber.karpet.server.utils;


import java.util.Random;

public class Probability {
    public static boolean Calculate(double Percent){
        Random r = new Random();
        int rng = r.nextInt(100);
        if (rng < Percent){
            return true;
        } else {
            return false;
        }
    }
}
