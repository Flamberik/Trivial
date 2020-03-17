import java.rmi.*;
import java.util.ArrayList;

interface Gestor extends Remote {
    void conjuntoRespuestas (ArrayList <Pregunta> p) throws RemoteException;
}
