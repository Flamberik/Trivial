import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ServicioJugadorImpl extends UnicastRemoteObject implements ServicioJugador{
    //String getResultado(String resultado) throws RemoteException;
    boolean comienzo=false;//Esta creo que sera innecesaria
    int indice;

    ServicioJugadorImpl() throws RemoteException {

    }

    //-----------------------------
    //        Jugador
    //-----------------------------
    void esperando_Pregunta() throws RemoteException {//¿Como conseguimos que el servidor avise a los clientes de que el gestor ya ha escrito la pregunta
      comienzo=true;
      System.out.println("\n El gestor está escribiendo la pregunta...\n");

    }

    Pregunta mostrar_pregunta(Pregunta p) throws RemoteException {
      //Los clientes muestran el valor de la pregunta y se quedan a la espera de que escriban por teclado y return susodicha cadena
      System.out.println("La pregunta es: " + p.getPregunta());

      /* EN EL CODIGO DEL CLIENTE
            Scanner teclado = new Scanner(System.in);
            String pregunta = teclado.nextLine();
      */
    }

    //-----------------------------
    //        Gestor
    //-----------------------------
    String solicitar_pregunta() throws RemoteException { //Función para avisar al jugador correspondiente que en este turno es el gestor
      System.out.println("\n En este turno eres tu el gestor, escribe la pregunta que quieras enviar\n");

/* EN EL CODIGO DEL CLIENTE
      Scanner teclado = new Scanner(System.in);
      String pregunta = teclado.nextLine();
*/

      return pregunta;
    }


    //-----------------------------
    //        Para ambos
    //-----------------------------
      void asignar_indice(int index) throws RemoteException {
        indice=index;
      }
}
