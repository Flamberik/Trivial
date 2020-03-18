import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class ServicioTrivialImpl extends UnicastRemoteObject implements ServicioTrivialImpl {
  List<Jugador> viciados; //Lista de jugadores,
  ServicioTrivialImpl() throws RemoteException {
    viciados = new LinkedList<Jugador>();
  }

  public void altaJugador(Jugador j) throws RemoteException {
    viciados.add(j);
  }

  public void bajaJugador(Jugador j) throws RemoteException {
    l.remove(l.indexOf(j));
  }

//Aun  no tengo claro el esquema de las preguntas-respuestas
  public void contestarPregunta(Jugador j, Pregunta p) throws RemoteException {

  }

  public void enviarPregunta (Pregunta p) throws RemoteException {

  }

}
