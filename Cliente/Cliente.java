import java.rmi.*;

interface Cliente extends Remote {
    String getResultado(String resultado) throws RemoteException;
}
