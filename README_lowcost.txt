Se ejecuta el servidor
  $ java -Djava.security.policy=servidor.permisos ServidorTrivial 54321 numero_de_jugadores

Se ejecutan los clientes
  $ java -Djava.security.policy=cliente.permisos ClienteTrivial localhost 54321 id_jugador

    1.En este proceso el cliente se dará de alta como jugador, siendo introducido en una
      lista donde el servidor tendrá a todos los jugadores localizados.
    2.El servidor espera a que hayan tantos jugadores conectados como lo indique la variable de entrada por (defecto 3) para dar comienzo al juego.
    3.Selecciona al primer jugador de gestor y:
        -Envia notificación al gestor elegido diciendole que es el actual gestor y que escriba la pregunta que será formulada
        -Envía una notificación al resto de jugadores diciendole quien es quien hará la pregunta y que aguarden hasta que la
         pregunta sea formulada.
    4. El gestor actual escribe la pregunta y la envía
    5. Envia notificacion
        -Al gestor de espera hasta que las respuestas esté enviadas (todas)
        -A los jugadores les muestra la pregunta y espera su respuesta
    6. El servidor recibe todas las preguntas, envía a todos los jugadores (Al gestor no) de nuevo la pregunta
       y todas las respuestas ofrecidas, en formato de lista de números
    7. Cuando los jugadores voten su respuesta favorita el servidor recibirá la información,
        -Mirará la respuesta más votada
        -Buscará el id del jugador que lanzó dicha respuesta
        -Envia notificación a todos los jugadores (Incluyendo al gestor) diciendo quien fue el más votado.

    8. El servidor cambia el rol de gestor al siguiente usuario de la lista y saltamos al paso 2
