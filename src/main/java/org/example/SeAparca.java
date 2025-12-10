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

        Random rand = new Random();

        while(true){
            tiempo = tEsperaMin+ rand.nextInt(tEsperaMax-tEsperaMin+1);
            System.out.println("El siguiente coche llega en "+tiempo+" segundos");
            try {
                Thread.sleep(tiempo*100);
                Coche c = new Coche("Coche -> "+generarMatricula(), aparcamiento, tMin, tMax);
                new Thread(c).start();
            }catch (InterruptedException e){
                System.out.println("interrumpido");
            }
        }
    }

    public static String generarMatricula(){
        String ma = "";
        Random rand = new Random();
        for (int j = 0; j < 4; j++) {
            int i = rand.nextInt(0, 9) ;
            ma += i;
        }
        for (int j = 0; j < 3; j++) {
            int i = rand.nextInt(65, 90) ;
            ma += (char) i;
        }
        return ma;
    }
}
