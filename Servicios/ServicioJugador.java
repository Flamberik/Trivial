import java.rmi.*;

interface Cliente extends Remote {
    String getResultado(String resultado) throws RemoteException;

    //-----------------------------
    //        Jugador
    //-----------------------------
    void Esperando_Pregunta() throws RemoteException;

    String Responder() throws RemoteException;



//-----------------------------
//        Gestor
//-----------------------------

    String solicitar_pregunta() throws RemoteException ;


}
