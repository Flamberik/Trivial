import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class Cliente {
  static public void main (String args[]) {
     if (args.length!=2) {
          System.err.println("Uso: Servidor direccionIP numPuertoRegistro");
          return;
      }
      if (System.getSecurityManager() == null) {
          System.setSecurityManager(new RMISecurityManager());
      }

      try {
          ServicioTrivial srv = (ServicioTrivial) Naming.lookup("//" + args[0] + ":" + args[1] + "/Trivial");
          ServicioJugadorImpl c = new ServicioJugadorImpl();
          //Aqui empieza el juego
          srv.altaJugador(c); //Damos de alta al jugador en el servidor
          System.out.println("Conexión establecida, esperando a los demás jugadores...");
          //El resto de la lógica del programa lo llevará a cabo el servidor.

      }
      catch (RemoteException e) {
          System.err.println("Error de comunicacion: " + e.toString());
          System.exit(1);
      }
      catch (Exception e) {
          System.err.println("Excepcion en el Servidor de Trivial:");
          e.printStackTrace();
          System.exit(1);
      }
    }
}
