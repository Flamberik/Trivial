
import java.rmi.*;

interface ServicioTrivial extends Remote {
    public void altaJugador (ServicioJugador j) throws RemoteException;
    public void bajaJugador (ServicioJugador j) throws RemoteException;
    public void contestarPregunta (Pregunta p) throws RemoteException; //Todos los clientes responden la pregunta que les ha llegado excepto el gestor.
    public void enviarPregunta (Pregunta p) throws RemoteException; //El gestor manda esta pregunta para que la reciban los clientes
    public void respuestaGanadora(Pregunta p) throws RemoteException; //El gestor indica quién ha ganado con esta función

    //añadido
  public void asigna_los_indices() throws RemoteException; //Esta función asigna a cada cliente el valor de índice que tienen en la lista (Para discriminar entre gestor y jugadores)
  public int tam_lista_jugadores() throws RemoteException;

  public  Pregunta avisa_jugadores() throws RemoteException;
  //Esta función se ejecuta al comenzar el juego para decirle al gestor que está esperando una pregunta y a los clientes que el gestor la va a escribir




}
