
import java.rmi.*;

interface ServicioTrivialImpl extends Remote {
    void altaJugador (Jugador j) throws RemoteException;
    void bajaJugador (Jugador j) throws RemoteException;
    void contestarPregunta (Pregunta p) throws RemoteException; //Todos los clientes responden la pregunta que les ha llegado excepto el gestor.
    void enviarPregunta (Pregunta p) throws RemoteException; //El gestor manda esta pregunta para que la reciban los clientes
    void respuestaGanadora(Pregunta p) throws RemoteException; //El gestor indica quién ha ganado con esta función

    //añadido
    void asigna_los_indices() throws RemoteException;//Esta función asigna a cada cliente el valor de índice que tienen en la lista (Para discriminar entre gestor y jugadores)
    int tam_lista_jugadores() throws RemoteException;

}
