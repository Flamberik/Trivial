import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ServicioJugadorImpl extends UnicastRemoteObject implements ServicioJugador{
    //String getResultado(String resultado) throws RemoteException;
    boolean comienzo=false;//Esta creo que sera innecesaria
    int indice;

    public ServicioJugadorImpl() throws RemoteException {

    }

    public int getIndice() throws RemoteException {
      return indice;
    }

    //-----------------------------
    //        Jugador
    //-----------------------------
    public void esperando_Pregunta() throws RemoteException {//¿Como conseguimos que el servidor avise a los clientes de que el gestor ya ha escrito la pregunta
      comienzo=true;
      System.out.println("\n El gestor está escribiendo la pregunta...\n");

    }

    public Pregunta mostrar_pregunta(Pregunta p) throws RemoteException {
      System.out.println("El indice de este jugador es: " + indice);
      //Los clientes muestran el valor de la pregunta y se quedan a la espera de que escriban por teclado y return susodicha cadena
      System.out.println("La pregunta es: " + p.getPregunta() + "\n");

      // EN EL CODIGO DEL CLIENTE
      System.out.println("Escribe tu respuesta tronco\n");
      Scanner teclado = new Scanner(System.in);
      String respuesta = teclado.nextLine();

      System.out.println("La cadena que vamos a meter en p.respuesta: " + respuesta);
      p.setRespuesta(respuesta);
      System.out.println("La respuesta introducida es: " + p.getRespuesta());

      p.setIndice(indice);//Aqui va el indice del jugador);
      return p;
    }

    //-----------------------------
    //        Gestor
    //-----------------------------
    public String solicitar_pregunta() throws RemoteException { //Función para avisar al jugador correspondiente que en este turno es el gestor
      System.out.println("\n En este turno eres tu el gestor, escribe la pregunta que quieras enviar\n");

// EN EL CODIGO DEL CLIENTE
      Scanner teclado = new Scanner(System.in);
      String pregunta = teclado.nextLine();


      return pregunta;
    }


public    int conjuntoRespuestas (ArrayList <Pregunta> p) throws RemoteException {
      //El gestor recibe el array de preguntas:
      //Muestra por pantalla todas las respuestas
        for (Pregunta preg : p) {
        System.out.println("En medio del for");
        System.out.println(preg.getIndice() + ": " + preg.getRespuesta() + "\n");
      }
      Scanner teclado = new Scanner(System.in);
      String indice_respuestaGanadora = teclado.nextLine();
      return Integer.parseInt(indice_respuestaGanadora);
    }



    //-----------------------------
    //        Para ambos
    //-----------------------------
      public void asignar_indice(int index) throws RemoteException {
        indice=index;
      }

      public void mensajePersonalizado(String s) throws RemoteException {
        System.out.println(s);
      }

}
