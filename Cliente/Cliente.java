import java.util.*;
import java.rmi.*;
import java.rmi.server.*;

class Cliente {
  static public void main (String args[]) {
     if (args.length!=3) {
          System.err.println("Uso: Servidor direccionIP numPuertoRegistro idJugador");
          return;
      }
      if (System.getSecurityManager() == null) {
          System.setSecurityManager(new RMISecurityManager());
      }

      try {
          ServicioTrivialImpl srv = (ServicioTrivial) Naming.lookup("//" + args[0] + ":" + args[1] + "/Trivial");
          ServicioJugadorImpl c = new ServicioJugadorImpl();
          
          //Aqui empieza el juego



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
