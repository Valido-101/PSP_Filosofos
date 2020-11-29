package soluci�n;

public class Portero_del_Comedor {

private int comensal = 4; // Es el n�mero de comensales total de fil�sofos menos 1
    
    /**
     * Monitor para coger un comensal de los 4 y poder seguir el proceso de ejecuci�n de los fil�sofos.
     */
    public synchronized void obtenerComensal(int id_f) throws InterruptedException{
        while(comensal==0){ // Si no hay comensales libres toca esperar
            this.wait();
        } 
        System.out.println("El Fil�sofo " + (id_f+1) + " es el comensal " + comensal);
        comensal--; // Conteo de comensales
    }
    
    /**
     * Monitor para soltar un comensal de los 4 y poder seguir el proceso de ejecuci�n de los fil�sofos.
     */
    public synchronized void soltarComensal(int id_f) throws InterruptedException{
        comensal++; // Conteo de comensales
        System.out.println("El Fil�sofo " + (id_f+1) + " ya NO es el comensal " + comensal);
        this.notify(); // Notificaci�n al siguiente de que hay comensal disponible. Este m�todo saca al fil�sofo del estado de espera.
	
  }
}
