import java.rmi.*;
import java.util.*;

interface ServicioJugador extends Remote {
  //  String getResultado(String resultado) throws RemoteException; Esta clase se ha quitado y no sé si hará falta en el futuro

    //-----------------------------
    //        Jugador
    //-----------------------------
    void esperando_Pregunta() throws RemoteException;
	//Esta función la va a utlizar el servidor  para indicarle al jugador correspondiente que el juego ha comenzado pero no es el que hace la pregunta


    Pregunta mostrar_pregunta(Pregunta p) throws RemoteException;
    //Este es el notification



//-----------------------------
//        Gestor
//-----------------------------

    String solicitar_pregunta() throws RemoteException ;
    //Esta función la va a utlizar el servidor  para indicarle al jugador correspondiende (gestor) que es el que hace la pregunta




//-----------------------------
//        Para ambos
//-----------------------------
  void asignar_indice(int index) throws RemoteException;

	int conjuntoRespuestas (ArrayList <Pregunta> p, int ind_gest) throws RemoteException;
	//Todos los clientes se quedan esperando (los que no sean gestor después de mandar la respuesta. Una vez que el servidor tiene todas las respuestas le manda el conjuntoRespuestas a los jugadores. El cliente que sea gestor en ese momento podrá decidir quién ha ganao en su código y llamará otra vez al servidor.

  public void mensajePersonalizado(String s) throws RemoteException;

  public int getIndice() throws RemoteException;

}
