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
          while(srv.tam_lista_jugadores() != args[2]) {//Comprueba que en el lobby halla el número predefinido de jugadores
            print("Esperando a que los jugadores se conecten\n");
            Thread.sleep(2000);
          }
          //Recibe la lista completa de todos los clientes
          srv.asigna_los_indices();

          //Envia notificación a todos los jugadores (No al gestor) que esperen a que el gestor formule la pregunta
          srv.


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
