import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.Scanner;

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
      System.out.println("\nTodavía no ha llegado la pregunta. ¡Paciencia! \n");

    }

    public Pregunta mostrar_pregunta(Pregunta p) throws RemoteException {
      System.out.println("El indice de este jugador es: " + indice);
      //Los clientes muestran el valor de la pregunta y se quedan a la espera de que escriban por teclado y return susodicha cadena
      System.out.println("La pregunta es: " + p.getPregunta() + "\n");

      // EN EL CODIGO DEL CLIENTE
      System.out.println("¡Escribe tu respuesta!\n");
      Scanner teclado = new Scanner(System.in);
      String respuesta = teclado.nextLine();

      p.setRespuesta(respuesta);
      System.out.println("La respuesta introducida es: " + p.getRespuesta());

      p.setIndice(indice);//Aqui va el indice del jugador);
      return p;
    }

    //-----------------------------
    //        Gestor
    //-----------------------------
    public String solicitar_pregunta() throws RemoteException { //Función para avisar al jugador correspondiente que en este turno es el gestor
      System.out.println("\nEn este turno te toca a ti preguntar, escribe la pregunta que quieras enviar\n");

// EN EL CODIGO DEL CLIENTE
      Scanner teclado = new Scanner(System.in);
      String pregunta = teclado.nextLine();

    //  System.out.println("SE HA MANDAO LA PREGUNTA "+pregunta);
      return pregunta;
    }


public    int conjuntoRespuestas (ArrayList <Pregunta> p, int ind_gest) throws RemoteException {
      //El gestor recibe el array de preguntas:
      //Muestra por pantalla todas las respuestas
        for (Pregunta preg : p) {

        System.out.println("La respuesta del jugador "+preg.getIndice() + " es: " + preg.getRespuesta() + "\n");
      }
    //  System.out.println("¿Cuál es la mejor respuesta? ¡Escribe el índice del jugador que la haya escrito!");
      Scanner scan = new Scanner(System.in);
      int numleido = -1;
      while(numleido < 0 || numleido> p.size() || numleido==ind_gest){
        System.out.println("¿Cuál es la mejor respuesta? ¡Escribe el índice del jugador que la haya escrito!");
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
      public void asignar_indice(int index) throws RemoteException {
        indice=index;
      }

      public void mensajePersonalizado(String s) throws RemoteException {
        System.out.println(s);
      }

}
