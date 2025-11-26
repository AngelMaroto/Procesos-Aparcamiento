package org.example;

import java.util.Random;

public class Coche extends Thread{

    private String matricula;
    private final Aparcamiento aparcamiento;
    private final int tMinAparcar;
    private final int tMaxAparcar;



    public Coche(String matricula, Aparcamiento aparcamiento, int tMinAparcar, int tMaxAparcar) {
        this.matricula = matricula;
        this.aparcamiento = aparcamiento;
        this.tMinAparcar = tMinAparcar;
        this.tMaxAparcar = tMaxAparcar;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    @Override
    public void run() {
        //Autorizarse
        Boolean autorizado=false;
        while(!autorizado){
            autorizado=this.aparcamiento.autorizar(this);
            if(autorizado==false){
                System.out.println(this.matricula+" no puede pasas");
                try{
                    this.aparcamiento.wait();
                }catch(InterruptedException e){
                    System.out.println(this.matricula+" esta esperando");
                }
            }
        }
        //OCUPAR
        int plazaLibre = this.aparcamiento.plazaLibre();

        //Entra al aparcamiento
        System.out.println(this.matricula+" puede pasar");
        this.aparcamiento.ocuparPlaza(this, plazaLibre);
        Random tiempo=new Random();
        int tOcupado=this.tMinAparcar+tiempo.nextInt(tMaxAparcar-tMinAparcar+1);
        System.out.println(this.matricula+" esta  "+tOcupado+" segundos, ocupando la plaza"+plazaLibre);
        try {
            Thread.sleep(tOcupado*1000);
        }catch(InterruptedException e){
            System.out.println(this.matricula+" esta ocupando la plaza "+plazaLibre);
        }
        //SALIR

        synchronized (this.aparcamiento) {
            System.out.println(this.matricula+"libera la plaza"+plazaLibre);
            this.aparcamiento.liberarPlaza(plazaLibre);

            aparcamiento.notifyAll();
        }

    }

}
