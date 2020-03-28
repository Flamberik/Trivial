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
      int indice_gestor = 0;
      try {
          ServicioTrivialImpl srv = new ServicioTrivialImpl();
          Naming.rebind("rmi://localhost:" + args[0] + "/Trivial", srv);
          //Aqui empieza el juego
          while(srv.tam_lista_jugadores() != Integer.parseInt(args[1])) {//Comprueba que en el lobby halla el número predefinido de jugadores
            System.out.println("Esperando a que los jugadores se conecten\n");
            Thread.sleep(2000);
          }
          //Recibe la lista completa de todos los clientes
          srv.asigna_los_indices();

          //Envia notificación a todos los jugadores (No al gestor) que esperen a que el gestor formule la pregunta
          Pregunta pregunta = srv.avisa_jugadores();//cadena es la pregunta que el gestor escribe

          //¿Cuanto vale pregunta?
          System.out.println(pregunta.getPregunta());
          //Enviamos la pregunta a todos los jugadores
          srv.enviarPregunta(pregunta);

          //El servidore ya tiene todas las respuestas
          //ahora se las manda al gestor
          //srv.enviarPrguntas_alGestor()
          int indice_respuestaGanadora;
          indice_respuestaGanadora=srv.jugadores.get(indice_gestor).conjuntoRespuestas(srv.respuestas);

         //Hacemos un bucle
         for(ServicioJugador c: srv.jugadores) {
           if(indice_respuestaGanadora==c.getIndice()) {//Comprobamos el índice de cada jugador, si es el que ha ganado se indica.
             c.mensajePersonalizado("Illo has ganao");
           }
           else{
             c.mensajePersonalizado("Illo eres to malo");
           }
        }
        indice_gestor++; //El gestor pasa a ser el siguiente.
      } //fin try
      //Aqui termina el juego
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
