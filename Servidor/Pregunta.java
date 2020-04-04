import java.io.*;

class Pregunta implements Serializable {
    private String pregunta;
    private String respuesta;
    private int indice_jugador;


  Pregunta(String pregunta){
	this.pregunta = pregunta;
  }
    public String getPregunta (){
	return pregunta;
    }

    public void setRespuesta(String respuesta){
	this.respuesta = respuesta;
    }

    public String getRespuesta(){
	  return respuesta;
    }


    public int getJugador(){
	     return indice_jugador;
    }

    public int getIndice() {
      return indice_jugador;
    }

    public void setIndice(int i) {
      this.indice_jugador=i;
    }

}
