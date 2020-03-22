import java.io.*;

class Pregunta implements Serializable {
    private String pregunta;
    private String respuesta;
    private int indice_jugador;


  Pregunta(String pregunta, int indice){
	this.pregunta = pregunta;
  this.indice_jugador = indice;
  }
    public String getPregunta (){
	return pregunta;
    }

    public void setRespuesta(String pregunta){
	this.pregunta = pregunta;
    }

    public String getRespuesta(){
	  return respuesta;
    }


    public String getJugador(){
	     return this.indice_jugador;
    }
