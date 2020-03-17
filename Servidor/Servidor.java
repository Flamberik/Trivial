import java.rmi.*;

interface Servidor extends Remote {
    void altaCliente (Cliente o) throws RemoteException;
    void bajaCliente (CLiente o) throws RemoteException;
    void contestarPregunta (Pregunta p) throws RemoteException; //Metodo de CLiente
    void enviarPregunta (Pregunta p) throws RemoteException; //Metodo de Gestor
				     
}
