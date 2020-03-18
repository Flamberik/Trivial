import java.rmi.*;

interface Cliente extends Remote {
    //String getResultado(String resultado) throws RemoteException;


    //-----------------------------
    //        Jugador
    //-----------------------------
    void Esperando_Pregunta() throws RemoteException {
      //¿Como conseguimos que el servidor avise a los clientes que el gestor ya ha escrito la pregunta
    }

    String Responder() throws RemoteException {
      //Los clientes muestran el valor de la pregunta y se quedan a la espera de que escriban por teclado y return susodicha cadena
    }

    //-----------------------------
    //        Gestor
    //-----------------------------
    String solicitar_pregunta() throws RemoteException { //Función para avisar al jugador correspondiente que en este turno es el gestor
      System.out.println("\n En este turno eres tu el gestor, escribe la pregunta que quieras enviar\n");

      Scanner teclado = new Scanner(System.in);
      String pregunta = teclado.nextLine();

      return pregunta;
    }

}
