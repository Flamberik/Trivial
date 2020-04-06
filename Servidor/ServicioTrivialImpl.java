import java.util.*;
import java.rmi.*;
import java.rmi.server.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

class ServicioTrivialImpl extends UnicastRemoteObject implements ServicioTrivial {
  //List<String> jugadores; //Modificado BORRAR
  //Esta lista debe ser tipo Servicio Jugador para que el server se pueda conectar con cada uno de los clientes (Utilizar las funciones definidas)
  ArrayList<ServicioJugador> jugadores;

  ArrayList<Pregunta> respuestas; //Cada elemento del array contendrá un objeto de la clase Pregunta: la pregunta, respuesta y el id del jugador asociado
  int indice_gestor;
  Pregunta pregunta;

  ServicioTrivialImpl() throws RemoteException {
    jugadores = new ArrayList<ServicioJugador>();
    respuestas = new ArrayList<Pregunta>();
    indice_gestor=0;
    //añadido CHECKEAR
//    ServicioJugador gestor = new jugadores.get(indice_gestor);
  }


public  void altaJugador (ServicioJugador j) throws RemoteException {
    jugadores.add(j);
  }

  public void bajaJugador (ServicioJugador j) throws RemoteException {
    jugadores.remove(jugadores.indexOf(j));
  }

  public void contestarPregunta (Pregunta p) throws RemoteException { //El jugador responde una pregunta y la mete
    respuestas.add(p);
  }


//La peor función de la historia de las malas funciones
  public void enviarPregunta (Pregunta p) throws RemoteException  { //La funcion que utiliza el gestor para mandar la pregunta al servidor
    Thread[] threads = new Thread[jugadores.size()];
    try {
      for(ServicioJugador c: jugadores) {
        if(indice_gestor!=c.getIndice()) { //Aun por saber si esto funciona pero creemos que sí
          threads[c.getIndice()]=new Thread("" + c.getIndice()) {
            public void run(){
              int a=0;
              try{ //Hay que corregir el fallo que da enviarPregunta (mirar salida).
              a = c.getIndice();
            }catch (RemoteException e){ System.out.println("Aborta"); }
            try{
              Pregunta b = c.mostrar_pregunta(p);
              System.out.println("LA respuesta que le llega a la función enviarPregunta es: "+b.getRespuesta()); //ESTA ES LA RESPUESTA QUE SE HA RECIBIDO POR TECLADO
              respuestas.add(b); //La b tenía antes exactamente lo que pone en Pregunta b (parámetros a y b para poder hacer el try/catch)
            }catch (RemoteException e){ System.out.println("Aborta"); }
          }
          };
          threads[c.getIndice()].start();
        }
      }

      for(int i=0; i<jugadores.size();i++){
        if(i==indice_gestor){
        }
        else{
          try{
            threads[i].join();
        }  catch (InterruptedException e){System.out.println("Tuhmuerto");}
      }
    }
    } catch (RemoteException e){ System.out.println("Aborta"); }

  /*  for (Thread thread : threads){
      try{
        thread.join();
      }
      catch (InterruptedException e){System.out.println("Tuhmuerto");}catch(NullPointerException e){System.out.println("Nopointer");}
    //Compr obar si los hilos funcionas con un print dentro del for por ejemplo (Mirar ejemploHilos.java)*/
  }


  //NO SE USA
public  void respuestaGanadora(Pregunta p) throws RemoteException {//Enviarle a todo el mundo cual ha sido la respuesta ganadora

    indice_gestor++;//para cambiar el gestor d ela proxima partida
  }


  //añadido
  public int tam_lista_jugadores() throws RemoteException {
    return jugadores.size();
  }

  public void asigna_los_indices() throws RemoteException{//Esta función asigna a cada cliente el valor de índice que tienen en la lista (Para discriminar entre gestor y jugadores)
    for(ServicioJugador c: jugadores) {
      c.asignar_indice(jugadores.indexOf(c));//Metemos en cada cliente el indice que le corresponde
    }
  }

  public Pregunta avisa_jugadores(int indice_gestor) throws RemoteException { //Esta función se ejecuta al comenzar el juego para decirle al gestor que está esperando una pregunta y a los clientes que el gestor la va a escribir
    this.indice_gestor= indice_gestor;
    String pregunta = "";
    for(ServicioJugador c: jugadores) {//METER ESTE FOR EN HILOS PARA MAXIMIZAR RENDIMIENTO!!
      if(indice_gestor!=c.getIndice()) {
        c.esperando_Pregunta();
      }
    }
    jugadores.get(indice_gestor).solicitar_pregunta();

    Pregunta p = new Pregunta(pregunta);
    p.setIndice(indice_gestor); //Convertimos el string a objeto tipo pregunta lo devolvemos.
    return p;

  }



}
