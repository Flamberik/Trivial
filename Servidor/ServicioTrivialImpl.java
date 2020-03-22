import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ServicioTrivialImpl extends UnicastRemoteObject implements ServicioTrivial {
  //List<String> jugadores; //Modificado BORRAR
  List<ServicioJugador> jugadores; //Esta lista debe ser tipo Servicio Jugador para que el server se pueda conectar con cada uno de los clientes (Utilizar las funciones definidas)

  List<Pregunta> respuestas; //Cada elemento del array contendrá un objeto de la clase Pregunta: la pregunta, respuesta y el id del jugador asociado
  int indice_gestor;
  Pregunta pregunta;

  ServicioTrivialImpl() throws RemoteException {
    jugadores = new LinkedList<ServicioJugador>();
    respuesta = new LinkedList<Pregunta>;
    indice_gestor=0;
    //añadido CHECKEAR
    new ServicioJugador gestor = jugadores.get(indice_gestor);
  }


  void altaJugador (ServicioJugador j) throws RemoteException {
    jugadores.add(j);
  }

  void bajaJugador (ServicioJugador j) throws RemoteException {
    jugadores.remove(jugadores.indexOf(j));
  }

  void contestarPregunta (Pregunta p) throws RemoteException { //El jugador responde una pregunta y la mete
    respuestas.add(p);
  }

  void enviarPregunta (Pregunta p) throws RemoteException { //La funcion que utiliza el gestor para mandar la pregunta al servidor
    Thread hilo = new Thread() {
      public void run(){
        System.out.println("run by: " + getName());
      }
    };
    //
    for(ServicioJugador c: jugadores) {
      if(indicegestor!=jugadores.getindexof(c) {
        //Aun por saber si esto funciona
        new Thread("" + c.indice) {
          public void run(){
            respuestas.add(jugadores.getindexof(c), c.mostrar_pregunta());
          }
        }.start();
        //Comprobar si los hilos funcionas con un print dentro del for por ejemplo (Mirar ejemploHilos.java)
      }
    }
  }



  //DUDAS Y NO ACABADA
  void respuestaGanadora(Pregunta p) throws RemoteException {//Enviarle a todo el mundo cual ha sido la respuesta ganadora

    indice_gestor++;//para cambiar el gestor d ela proxima partida
  }


  //añadido
  int tam_lista_jugadores() throws RemoteException {
    return jugadores.size();
  }

  void asigna_los_indices() throws RemoteException{//Esta función asigna a cada cliente el valor de índice que tienen en la lista (Para discriminar entre gestor y jugadores)
    for(ServicioJugador c: jugadores) {
      c.asignar_indice(jugadores.indexOf(c));//Metemos en cada cliente el indice que le corresponde
    }
  }

  String avisa_jugadores() throws RemoteException {
    String pregunta;
    for(ServicioJugador c: jugadores) {
      if(indicegestor!=jugadores.getindexof(c) {
        c.esperando_Pregunta();
      }
      else {
        pregunta = c.solicitar_pregunta();
      }
    }
    return pregunta;

  }



}
