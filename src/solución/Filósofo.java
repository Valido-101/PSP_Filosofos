package solución;

import java.util.Random;

public class Filósofo extends Thread{
	
	// Variable para generar números aleatorios:
    private Random aleatorio = new Random();
    // Variable para la ID del Filósofo:
    private int id;
    // Variables para los tenedores:
    private Tenedor tenedor_izqda, tenedor_dcha;
    // Variable para el comensal:
    private Portero_del_Comedor comensal;
    

    public Filósofo(int id, Tenedor tenedor_dcha, Tenedor tenedor_izqda, Portero_del_Comedor comensal){
        // Se asignan los valores recibidos a las variables
        this.id = id;
        this.tenedor_dcha = tenedor_dcha;
        this.tenedor_izqda = tenedor_izqda;
        this.comensal = comensal;
    }
    
    public void filosofoPiensa() 
    {
    	try {
			Filósofo.sleep(aleatorio.nextInt(1000) + 100);
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
     * Método que se ejecuta indefinidamente en cada hilo
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
                    // Si no se consigue el izquierdo: el filósofo tendra que volver a casilla de salida y volver a obtener el comensal:
                    System.out.println("El Filósofo " + (id+1) + " tendrá que soltar el tenedor " + (id+1) + " por exceso de tiempo y salir a pensar.");
                    // Siempre se valora si el log es distinto a null, si lo es se ecribe en la interface gráfica:
                    // Como no ha conseguido el Tenedor izquierdo suelta el derecho
                    tenedor_dcha.soltarTenedor(id);
                    // Como no ha conseguido el Tenedor izquierdo suelta el comensal
                    comensal.soltarComensal(id);
                    // Y ahora el Filósofo piensa
                    System.out.println("El Filósofo " + (id+1) + " está pensando.");
                    this.filosofoPiensa();
                    // Fin de Ahora el Filósofo piensa
                    continue; // Se vuelve a poner en la casilla de salida y volver a obtener el comensal.
                    
                }
                    // Si ha conseguido el Tenedor Izquierdo. El filósofo sigue adelante:
                   System.out.println("El Filósofo " + (id+1) + " está comiendo.");
                    // Simular el tiempo que tarda el filósofo en comer, entre 0.5 y 1 segundos:
                    this.filosofoCome();
                    // Fin de Simular el tiempo que tarda el filósofo en comer, entre 0.5 y 1 segundos
                    // Fin de Ahora el Filósofo come 
                // Suelta el Tenedor izquierdo:
                tenedor_izqda.soltarTenedor(id);
                // Suelta el Tenedor derecho:
                tenedor_dcha.soltarTenedor(id);
                // Suelta el comensal:
                comensal.soltarComensal(id);
                // Ahora el Filósofo piensa 
                System.out.println("El Filósofo " + (id+1) + " está pensando.");
                // El tiempo que tarda el filósofo en pensar, entre 100 y 1000 milisegundos:
                try {
                    Filósofo.sleep(aleatorio.nextInt(1000) + 100);
                } catch (InterruptedException ex) {
                    System.out.println("Error. Descripción: " + ex.toString());
                }
                // Fin de Ahora el Filósofo piensa
            } catch (InterruptedException ex) {
                ex.printStackTrace();
                System.err.println("Se ha producido un error. Descripción: " + ex.toString());
            }
            
        }

    }       

}
