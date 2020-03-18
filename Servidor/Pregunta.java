import java.io.*;

class Pregunta implements Serializable {
    private String pregunta;
    private String respuesta;
    private String jugador;

	
    Pregunta(String pregunta){
	this.pregunta = pregunta;

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

    public void setJugador(String jugador){
	this.jugador = jugador;
    }

    public String getJugador(){
	return jugador;
    }
