import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;

class ServicioJugadorImpl extends UnicastRemoteObject implements ServicioJugador{
    //String getResultado(String resultado) throws RemoteException;
    boolean comienzo=false;//No se usa
    int indice;

    public ServicioJugadorImpl() throws RemoteException {

    }

    public int getIndice() throws RemoteException {
      return indice;
    }

    //-----------------------------
    //        Jugador
    //-----------------------------
    public void esperando_Pregunta() throws RemoteException {//Los jugadores esperan la pregunta del que la realiza
      comienzo=true;
      System.out.println("\nTodavía no ha llegado la pregunta. ¡Paciencia! \n");

    }

    public Pregunta mostrar_pregunta(Pregunta p) throws RemoteException { //Muestra la pregunta que el gestor escribe
      System.out.println("El indice de este jugador es: " + indice);
      //Los clientes muestran el valor de la pregunta y se quedan a la espera de que escriban por teclado y devuelve la susodicha cadena
      System.out.println("La pregunta es: " + p.getPregunta() + "\n");

      // Se devuelve el objeto pregunta con la respuesta introducida
      System.out.println("¡Escribe tu respuesta!\n");
      Scanner teclado = new Scanner(System.in);
      String respuesta = teclado.nextLine();

      p.setRespuesta(respuesta);
      System.out.println("La respuesta introducida es: " + p.getRespuesta());

      p.setIndice(indice);//Aqui el jugador envía en su objeto Pregunta el índice que el servidor le asignó, que sirve de identificador
      return p;
    }

    //-----------------------------
    //        Gestor
    //-----------------------------
    public String solicitar_pregunta() throws RemoteException { //Se avisa al jugador correspondiente que le toca preguntar y se recibe de vuelta una cadena con la pregunta que haya introducido
      System.out.println("\nEn este turno te toca a ti preguntar, escribe la pregunta que quieras enviar\n");

      Scanner teclado = new Scanner(System.in);
      String pregunta = teclado.nextLine();

      return pregunta;
    }


public    int conjuntoRespuestas (ArrayList <Pregunta> p, int ind_gest) throws RemoteException {
      //El que preguntó recibe el array de objetos Preguntas donde se encuentran tanto las preguntas, los identificadores de jugador y sus respuestas.
      //Muestra por pantalla todas las respuestas y elige el índice de la respuesta que considere más divertida.
        for (Pregunta preg : p) {

        System.out.println("La respuesta del jugador "+preg.getIndice() + " es: " + preg.getRespuesta() + "\n");
      }
    //  System.out.println("¿Cuál es la mejor respuesta? ¡Escribe el índice del jugador que la haya escrito!");
      Scanner scan = new Scanner(System.in);
      int numleido = -1;
      while(numleido < 0 || numleido> p.size() || numleido==ind_gest){
        if(numleido==ind_gest){ //Hay que asegurarse de que lo introducido por teclado es una respuesta válida y no un índice fuera de rango o texto.
            System.out.println("¡No puedes ponerte a ti mismo!");
        }else{
        System.out.println("¿Cuál es la mejor respuesta? ¡Escribe el índice del jugador que la haya escrito!");
      }
      while (!scan.hasNextInt()) {
            System.out.println("Eso no es una respuesta válida, prueba otra vez.");
            scan.nextLine();
          }
      numleido = scan.nextInt();
    }
      return numleido;
    }



    //-----------------------------
    //        Para ambos
    //-----------------------------
    //El servidor utiliza esta función para identificarlos posteriormente
      public void asignar_indice(int index) throws RemoteException {
        indice=index;
      }

      public void mensajePersonalizado(String s) throws RemoteException {
        System.out.println(s);
      }

}
