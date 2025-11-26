package org.example;

public class Aparcamiento {

    private final String[] plazas = new String[2];

    private static final int MAX_COCHES = 5;


    synchronized public boolean autorizar(Coche coche) {
        for (int i = 0; i < plazas.length; i++) {
            if (plazas[i] == null || plazas[i].equals("")) {
                return true;
            }
        }
        return false;
    }
    synchronized public int plazaLibre(){
        int plazaLibre=0;
        for (int i = 0; i < this.plazas.length; i++) {
            if(plazas[i]==null||plazas[i].equals("")){
                plazaLibre=i;
                break;
            }
        }
        return plazaLibre;
    }

    synchronized public void ocuparPlaza(Coche coche, int numPlaza) {
        plazas[numPlaza] = coche.getMatricula();
    }

    synchronized public void liberarPlaza(int numPlaza) {
        plazas[numPlaza] = "";
    }

}
