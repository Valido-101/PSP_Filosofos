package soluci�n;

public class Main {

	public static void main(String[] args) {
		
		// Se crea el Array para contener las 5 instancias de Tenedores:
        Tenedor[] tenedor = new Tenedor[5];
        // Se crea el Array para contener las 5 instancias de Fil�sofos:
        Fil�sofo[] filosofo = new Fil�sofo[5];
        // Se crea una sola instancia de Portero_del_Comedor:
        Portero_del_Comedor comensal = new Portero_del_Comedor();
        // Se crea una sola instancia de Log:
        
        // Se crean las 5 instancias de Tenedores:
        for(int i=0; i<tenedor.length; i++){
            tenedor[i] = new Tenedor(i);
        }
        
        // Se crean las 5 instancias de Fil�sofos:
        for(int i=0; i<filosofo.length; i++){
            /* El fil�sofo coge el tenedor de la izquierda 
            *  y el de la derecha se contabiliza con el m�dulo(%)
            *  porque cuando llega a cuatro el siguiente es cero
            */
            // Ahora al fil�sofo se le pasa: un ID, un tenedor Dercho, un tenedor Izdo, el comensal, los componentes gr�ficos correspondientes y un log
            filosofo[i] = new Fil�sofo(i, tenedor[i], tenedor[(i+1)%5], comensal);
        }
        
        // Se echa a andar todos los Fil�sofos:
        for(int i=0; i<filosofo.length; i++){
            filosofo[i].start();

	}
        
	}

}
