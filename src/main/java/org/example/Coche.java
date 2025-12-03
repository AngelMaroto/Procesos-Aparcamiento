package org.example;

import java.util.Random;

public class Coche implements Runnable {
    private String matricula;
    private final Aparcamiento aparcamiento;
    private final int tMinAparcar;
    private final int tMaxAparcar;
    private int miPlaza = -1;

    public Coche(String matricula, Aparcamiento aparcamiento, int tMinAparcar, int tMaxAparcar) {
        this.matricula = matricula;
        this.aparcamiento = aparcamiento;
        this.tMinAparcar = tMinAparcar;
        this.tMaxAparcar = tMaxAparcar;
    }

    public String getMatricula() { return matricula; }

    @Override
    public void run() {
        // 1. ENTRAR (espera y ocupa atómicamente)
        miPlaza = aparcamiento.entrar(matricula);
        if (miPlaza == -1) return;  // Error

        // 2. APARCAR
        Random tiempo = new Random();
        int tOcupado = tMinAparcar + tiempo.nextInt(tMaxAparcar - tMinAparcar + 1);
        System.out.println(matricula + " aparca " + tOcupado + "s en plaza " + (miPlaza+1));

        try {
            Thread.sleep(tOcupado * 1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        // 3. SALIR
        aparcamiento.salir(miPlaza);
    }
}

