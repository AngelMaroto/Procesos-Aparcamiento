package org.example;

public class Aparcamiento {
    private final String[] plazas;
    private static final int MAX_COCHES = 50;

    public Aparcamiento() {
        this.plazas = new String[MAX_COCHES];
    }

    // MÉTODO ÚNICO: todo dentro de synchronized
    public int entrar(String matricula) {
        synchronized(this) {
            // Esperar mientras NO haya plaza libre
            while (!hayPlazaLibre()) {
                try {
                    System.out.println(matricula + " esta esperando");
                    this.wait();
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return -1;
                }
            }

            // Hay plaza: encontrarla y ocuparla ATÓMICAMENTE
            int plazaLibre = encontrarPlazaLibre();
            plazas[plazaLibre] = matricula;
            System.out.println(matricula + " ocupa plaza " + (plazaLibre + 1));
            return plazaLibre;
        }
    }

    public void salir(int numPlaza) {
        synchronized(this) {
            String matricula = plazas[numPlaza];
            plazas[numPlaza] = null;
            System.out.println(matricula + " libera plaza " + (numPlaza + 1));
            this.notifyAll();  // Despierta a todos los que esperan
        }
    }

    private boolean hayPlazaLibre() {
        for (String plaza : plazas) {
            if (plaza == null) return true;
        }
        return false;
    }

    private int encontrarPlazaLibre() {
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == null) return i;
        }
        return -1;  // No debería llegar aquí
    }
}
