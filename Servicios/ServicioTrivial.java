
import java.rmi.*;

interface ServicioTrivialImpl extends Remote {
    void altaCliente (Jugador j) throws RemoteException;
    void bajaCliente (Jugador j) throws RemoteException;
    void contestarPregunta (Pregunta p) throws RemoteException; //Metodo de CLiente
    void enviarPregunta (Pregunta p) throws RemoteException; //Metodo de Gestor

}
