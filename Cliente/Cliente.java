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
          ServicioJugadorImpl c = new ServicioJugadorImpl(); //No sé ni si hace falta
          //Aqui empieza el juego
          srv.altaJugador(c); //Damos de alta al jugador en el servidor



          //Aqui termina el juego

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
