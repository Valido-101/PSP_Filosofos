package soluci�n;

import java.util.Random;

public class Tenedor {
	
	// Variable para generar n�meros aleatorios:
    private Random random = new Random();
    // ID del Tenedor
    private int id;
    // Est� ocupado el tenedor o no?:
    private boolean tenedor_libre = true;
    
    /**
     * Constructor de la clase Tenedor
     */
    public Tenedor(int id){
        this.id = id;
    }
    
    // Crear m�todos synchronized => Monitores
    // Solo puede acceder un Thread a la vez.
    /**
     * Monitor para coger el tenedor derecho y poder seguir el proceso de ejecuci�n de los fil�sofos.
     */
    public synchronized void cogerTenedor(int id_f) throws InterruptedException{
        while(!tenedor_libre) 
        {
            this.wait();
        }
        System.out.println("El Fil�sofo " + (id_f+1) + " coge el tenedor " + (id+1));
        tenedor_libre = false;
    }
    /**
     * Monitor para coger el tenedor izquierdo y poder seguir el proceso de ejecuci�n de los fil�sofos,
     * Pero si no consigue cogerlo en un tiempo x retornar� false y tendra que salir a pensar y no podra comer,
     * Tendr� que volver a empezar el proceso de comer.
     */
    public synchronized boolean cogerTenedorIzqdo(int id_filosofo) throws InterruptedException{
        while(!tenedor_libre){
            this.wait(random.nextInt(1000) + 500); // S�lo espera aleatoriamente entre 0.5 y 1 seg y si no, retorna false
            return false;
        } 
        System.out.println("El Fil�sofo " + (id_filosofo+1) + " coge el tenedor " + (id+1));
        tenedor_libre = false;
        return true;
    }
    /**
     * Monitor para soltar un tenedor izquierdo o derecho y salir a pensar.
     */
    public synchronized void soltarTenedor(int id_f) throws InterruptedException {
    	tenedor_libre = true;
        System.out.println("El Fil�sofo " + (id_f+1) + " suelta el tenedor " + (id+1));
        this.notify();
    }

}
