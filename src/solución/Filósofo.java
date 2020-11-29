package soluci�n;

import java.util.Random;

public class Fil�sofo extends Thread{
	
	// Variable para generar n�meros aleatorios:
    private Random aleatorio = new Random();
    // Variable para la ID del Fil�sofo:
    private int id;
    // Variables para los tenedores:
    private Tenedor tenedor_izqda, tenedor_dcha;
    // Variable para el comensal:
    private Portero_del_Comedor comensal;
    

    public Fil�sofo(int id, Tenedor tenedor_dcha, Tenedor tenedor_izqda, Portero_del_Comedor comensal){
        // Se asignan los valores recibidos a las variables
        this.id = id;
        this.tenedor_dcha = tenedor_dcha;
        this.tenedor_izqda = tenedor_izqda;
        this.comensal = comensal;
    }
    
    public void filosofoPiensa() 
    {
    	try {
			Fil�sofo.sleep(aleatorio.nextInt(1000) + 100);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    public void filosofoCome()
    {
    	try {
			sleep(aleatorio.nextInt(1000) + 500);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    /**
     * M�todo que se ejecuta indefinidamente en cada hilo
     */
    @Override
    public void run(){
    	// Se repite infinitamente While
        while(true){ 
            
            try {
                // Obtener el comensal para poder comer:
                comensal.obtenerComensal(id);
                // Obtener el Tenedor Derecho:
                tenedor_dcha.cogerTenedor(id);
                // Obtener el Tenedor Izquierdo:
                if (!tenedor_izqda.cogerTenedorIzqdo(id)){
                    // Si no se consigue el izquierdo: el fil�sofo tendra que volver a casilla de salida y volver a obtener el comensal:
                    System.out.println("El Fil�sofo " + (id+1) + " tendr� que soltar el tenedor " + (id+1) + " por exceso de tiempo y salir a pensar.");
                    // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gr�fica:
                    // Como no ha conseguido el Tenedor izquierdo suelta el derecho
                    tenedor_dcha.soltarTenedor(id);
                    // Como no ha conseguido el Tenedor izquierdo suelta el comensal
                    comensal.soltarComensal(id);
                    // Y ahora el Fil�sofo piensa
                    System.out.println("El Fil�sofo " + (id+1) + " est� pensando.");
                    this.filosofoPiensa();
                    // Fin de Ahora el Fil�sofo piensa
                    continue; // Se vuelve a poner en la casilla de salida y volver a obtener el comensal.
                    
                }
                    // Si ha conseguido el Tenedor Izquierdo. El fil�sofo sigue adelante:
                   System.out.println("El Fil�sofo " + (id+1) + " est� comiendo.");
                    // Simular el tiempo que tarda el fil�sofo en comer, entre 0.5 y 1 segundos:
                    this.filosofoCome();
                    // Fin de Simular el tiempo que tarda el fil�sofo en comer, entre 0.5 y 1 segundos
                    // Fin de Ahora el Fil�sofo come 
                // Suelta el Tenedor izquierdo:
                tenedor_izqda.soltarTenedor(id);
                // Suelta el Tenedor derecho:
                tenedor_dcha.soltarTenedor(id);
                // Suelta el comensal:
                comensal.soltarComensal(id);
                // Ahora el Fil�sofo piensa 
                System.out.println("El Fil�sofo " + (id+1) + " est� pensando.");
                // El tiempo que tarda el fil�sofo en pensar, entre 100 y 1000 milisegundos:
                try {
                    Fil�sofo.sleep(aleatorio.nextInt(1000) + 100);
                } catch (InterruptedException ex) {
                    System.out.println("Error. Descripci�n: " + ex.toString());
                }
                // Fin de Ahora el Fil�sofo piensa
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                System.err.println("Se ha producido un error. Descripci�n: " + ex.toString());
            }
            
        }

    }       

}
