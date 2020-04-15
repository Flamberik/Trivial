import java.rmi.*;
import java.rmi.server.*;
import java.util.*;


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
      while(srv.tam_lista_jugadores() != Integer.parseInt(args[1])) {//El servidor espera a que haya tantos jugadores como el número pasado como parámetro
        System.out.println("Esperando a que los jugadores se conecten\n");
        Thread.sleep(2000);
      }
      //Asigna internamente y a cada cliente en particular un índice que funcionará como token de identificación
      srv.asigna_los_indices();
      //Avisa a todos los jugadores de que comienza el juego
      for(ServicioJugador c: srv.jugadores) {
        //Le decimos a cada jugador que el juego comienza
        c.mensajePersonalizado("¡Comienza el juego!");
      }

      List<Integer> puntos = new ArrayList<>(); //ArrayList donde se almacena la puntuación de cada jugador
      for(int i=0;i<Integer.parseInt(args[1]);i++){//Inicializamos el array a 0 puntos
        puntos.add(0);
      }
      int jugadorganador = -1; //Se guardará el indice del jugador que gane la partida, l comenzamos a -1 por que aún la partida no fue concluida
      boolean juego=true;
      while(juego){
        System.out.println("Esta ronda la pregunta la realizará el jugador " + indice_gestor);
        //Envia notificación a todos los jugadores (excepto al que pregunta) que esperen a que el que pregunta formule la pregunta
        Pregunta pregunta = srv.avisa_jugadores(indice_gestor);//cadena es la pregunta que el gestor escribe
        System.out.println(pregunta.getPregunta()); //El servidor muestra la pregunta recibida (no es necsario realmente)
        //Enviamos la pregunta a todos los jugadores y rellena el array de respuestas a la vez
        srv.enviarPregunta(pregunta);
        //El servidor ya tiene todas las respuestas
        //ahora se las manda al que pregunta
        //srv.enviarPrguntas_alGestor()
        int indice_respuestaGanadora;
        //Una vez que todos hayan respondido se indica quién es el jugador que ha ganado esta ronda
        indice_respuestaGanadora=srv.jugadores.get(indice_gestor).conjuntoRespuestas(srv.respuestas, indice_gestor);

        //Hacemos un bucle
        for(ServicioJugador c: srv.jugadores) {
          if(indice_respuestaGanadora==c.getIndice()) {//Comprobamos el índice de cada jugador, si es el que ha ganado se le indica.
            c.mensajePersonalizado("¡Has ganado esta ronda! Un punto más a tu marcador.");
            puntos.set(indice_respuestaGanadora, puntos.get(indice_respuestaGanadora)+1);
            switch(puntos.get(c.getIndice())){ //Se indica a cada jugador el número de puntos que tiene
              case 0:
                c.mensajePersonalizado("Actualmente no tienes ningún punto.");
                break;
              case 1:
                c.mensajePersonalizado("Actualmente tienes "+puntos.get(c.getIndice()) +" punto.");
                break;
              default:
                c.mensajePersonalizado("Actualmente tienes "+puntos.get(c.getIndice()) +" puntos.");
            }
          }
          else if(indice_gestor==c.getIndice()){//Muestra a cada jugador los puntos que tienen al final de cada ronda
            c.mensajePersonalizado("¡Ha ganado el jugador "+indice_respuestaGanadora +"!");
            switch(puntos.get(c.getIndice())){
              case 0:
                c.mensajePersonalizado("Actualmente no tienes ningún punto.");
                break;
              case 1:
                c.mensajePersonalizado("Actualmente tienes "+puntos.get(c.getIndice()) +" punto.");
                break;
              default:
                c.mensajePersonalizado("Actualmente tienes "+puntos.get(c.getIndice()) +" puntos.");
            }


          }else{
            c.mensajePersonalizado("Has perdido esta ronda :(");
            switch(puntos.get(c.getIndice())){
              case 0:
                c.mensajePersonalizado("Actualmente no tienes ningún punto.");
                break;
              case 1:
                c.mensajePersonalizado("Actualmente tienes "+puntos.get(c.getIndice()) +" punto.");
                break;
              default:
                c.mensajePersonalizado("Actualmente tienes "+puntos.get(c.getIndice()) +" puntos.");
            }
          }
        }
        indice_gestor++; //El gestor pasa a ser el siguiente.
        if(indice_gestor>=Integer.parseInt(args[1])){
          indice_gestor = 0;
        }
        int a=0;
        for(Integer i: puntos){//Cuando un jugador acumule más de 3 puntos gana la partida
          if(i>=3){
            juego=false;
            jugadorganador=a;
          }
          a++;
        }
        srv.respuestas.clear(); //Vaciamos el array para que no se vayan acumulando respuestas
      }

      for(ServicioJugador c: srv.jugadores){
        c.mensajePersonalizado("¡La victoria es para el jugador "+ jugadorganador+"!");
      }
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
