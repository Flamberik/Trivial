import java.rmi.*;
import java.rmi.server.*;

class Servidor {
  static public void main (String args[]) {
     if (args.length!=2) {
          System.err.println("Uso: Servidor numPuertoRegistro numJugadores");
          return;
      }
      if (System.getSecurityManager() == null) {
          System.setSecurityManager(new RMISecurityManager());
      }

      try {
          ServicioTrivialImpl srv = new ServicioTrivialImpl();
          Naming.rebind("rmi://localhost:" + args[0] + "/Trivial", srv);
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
