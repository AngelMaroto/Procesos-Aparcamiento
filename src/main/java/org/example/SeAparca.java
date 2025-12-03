package org.example;

import java.util.Random;

public class SeAparca {
   static void main(String[] args) {

       Aparcamiento aparcamiento = new Aparcamiento();
        int tMin = 5;
        int tMax = 30;
        int tEsperaMin=5;
        int tEsperaMax=30;
        int tiempo ;
        int matriculaC = 1;

        Random rand = new Random();

        while(true){
            tiempo = tEsperaMin+ rand.nextInt(tEsperaMax-tEsperaMin+1);
            System.out.println("El siguiente coche llega en "+tiempo+" segundos");
            try {
                Thread.sleep(tiempo*100);
                Coche c = new Coche("Matricula-> "+String.valueOf(matriculaC), aparcamiento, tMin, tMax);
                new Thread(c).start();
                matriculaC++;
            }catch (InterruptedException e){
                System.out.println("interrumpido");
            }
        }
    }
}
