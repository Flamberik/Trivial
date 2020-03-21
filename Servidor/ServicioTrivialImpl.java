import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ServicioTrivialImpl extends UnicastRemoteObject implements ServicioTrivial {
    List<String> jugadores; //Tenemos que implementar la clase jugador!
    List<Pregunta> respuestas;
    int indice_gestor;
    Pregunta pregunta;

    ServicioTrivialImpl() throws RemoteException {
      l = new LinkedList<Jugador>();
      indice_gestor=0;
    }


    void altaJugador (String j) throws RemoteException {
      jugadores.add(j);
    }

    void bajaJugador (String j) throws RemoteException {
      jugadores.remove(jugadores.indexOf(j));
    }

    void contestarPregunta (Pregunta p) throws RemoteException { //El jugador responde una pregunta y la mete
      respuestas.add(p);
    }

    void enviarPregunta (Pregunta p) throws RemoteException { //La funcion que utiliza el gestor para mandar la pregunta al servidor
        pregunta=p;
    }



    //DUDAS Y NO ACABADA
    void respuestaGanadora(Pregunta p) throws RemoteException {//Enviarle a todo el mundo cual ha sido la respuesta ganadora

      indice_gestor++;//para cambiar el gestor d ela proxima partida
    }



}
