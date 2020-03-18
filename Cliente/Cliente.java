import java.rmi.*;

interface Cliente extends Remote {
    String getResultado(String resultado) throws RemoteException;

    //-----------------------------
    //        Jugador
    //-----------------------------
    void Esperando_Pregunta() throws RemoteException;
	//Esta función la va a utlizar el servidor  para indicarle al jugador correspondiente que el juego ha comenzado pero no es el que hace la pregunta
	

    String Responder() throws RemoteException;



//-----------------------------
//        Gestor
//-----------------------------

    String solicitar_pregunta() throws RemoteException ;


    //Esta función la va a utlizar el servidor  para indicarle al jugador correspondiende que es el que hace la pregunta




//-----------------------------
//        Para ambos
//-----------------------------
	void conjuntoRespuestas (ArrayList <Pregunta> p) throws RemoteException;
	//Todos los clientes se quedan esperando (los que no sean gestor después de mandar la respuesta. Una vez que el servidor tiene todas las respuestas le manda el conjuntoRespuestas a los jugadores. El cliente que sea gestor en ese momento podrá decidir quién ha ganao en su código y llamará otra vez al servidor.
	//

}
