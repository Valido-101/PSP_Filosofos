package solución;

public class Portero_del_Comedor {

private int comensal = 4; // Es el número de comensales total de filósofos menos 1
    
    /**
     * Monitor para coger un comensal de los 4 y poder seguir el proceso de ejecución de los filósofos.
     */
    public synchronized void obtenerComensal(int id_f) throws InterruptedException{
        while(comensal==0){ // Si no hay comensales libres toca esperar
            this.wait();
        } 
        System.out.println("El Filósofo " + (id_f+1) + " es el comensal " + comensal);
        comensal--; // Conteo de comensales
    }
    
    /**
     * Monitor para soltar un comensal de los 4 y poder seguir el proceso de ejecución de los filósofos.
     */
    public synchronized void soltarComensal(int id_f) throws InterruptedException{
        comensal++; // Conteo de comensales
        System.out.println("El Filósofo " + (id_f+1) + " ya NO es el comensal " + comensal);
        this.notify(); // Notificación al siguiente de que hay comensal disponible. Este método saca al filósofo del estado de espera.
	
  }
}
