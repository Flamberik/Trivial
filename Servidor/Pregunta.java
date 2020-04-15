import java.io.*;

//Esta clase contiene tanto la pregunta (que la enviará el jugador que pregunte en cada momento) así como las respuestas.
//El resto de jugadores completarán los campos de respuesta y de índice.

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
