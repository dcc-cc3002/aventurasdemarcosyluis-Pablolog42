# AventurasdeMarcosyLuis

#### Alumno: Pablo González Aguilera - 20973277-7

Entrega 3

El siguiente programa consiste en la implementación del juego RPG de "Las flipantes Aventuras de Marcos y Luis".

El juego código distintos personajes, enemigos e ítems, y las posibles interacciones entre sí. El código fuente del juego se encuentra en `/src/test/java/aventurasdemarcoyluis`

El juego utiliza el paradigma de Modelo - vista - Controlador.

En esta iteración, se pretende finalizar la integración entre el modelo y el controlador del juego.

Las interacciones entre personajes, enemigos e ítems están descritas en el enunciado de la _Tarea_, que se encuentra en: https://www.u-cursos.cl/ingenieria/2021/2/CC3002/1/tareas/r/2021102601013E9E5BB0728CE350__Enunciado_Tarea_03_CC3002.pdf

Estas interacciones están mediadas casi en su mayoría por el controlador del juego, en este caso contenido en la clase GameController (`java/aventurasdemarcoyluis/controller/GameController.java`)

El controlador está encargado de realizar las siguientes labores:

1. Crear a los personajes principales.
2. Crear a los enemigos.
3. Crear a los Items.
4. Crear el baúl de los personajes principales.
5. Implementar los turnos.
6. Que un jugador pueda utilizar un elemento al ba´ul.
7. Obtener los elementos del baúl.
8. Obtener todos los personajes del turno.
9. Quitar a un personaje del “turno” cuando est´a K.O.
10. Saber cuando los personajes principales ganan o pierden.
11. Obtener el personaje que posee el turno actual.
12. Obtener el personaje del siguiente turno.
13. Terminar el turno del jugador actual.

Los números de cada requerimiento se referencian en las clases que testean al controlador, encontradas en: `src/test/java/TestController`

Es el controlador quien se encarga de relacionar cada una de estas etapas.

Se espera que, dentro de cada batalla, se realicen diversos turnos intercalados entre el Jugador (quien es el que controla y posee a los personajes principales),}
y el "Enemigo" (Quienes son los encargados de atacar aleatoriamente a algún jugador.)

Además de esto, se tiene que dentro de un mismo Juego, al pasar cada batalla los personajes principales van subiendo de nivel,
obteniendo nuevos items y viéndose enfrentados a más enemigos.

El juego termina en el momento en que:

1. El Jugador ha sido derrotado (Sus personajes principales están ambos KO)
2. El Jugador ha peleado 5 batallas seguidas sin ser derrotado (El jugador Gana.)


En general, se ha utilizado una aproximación de resolución del problema basada en herencia de clases, en conjunto de implementación de interfaces para componentes con métodos comunes.

En esta última entrega, se han implementado todas las fases del juego, las posibles transiciones válidas entre estas, junto con la utilización del patron de diseño _State_ como pilar fundamental para el manejo del flujo de juego.

Se implementa además un manejo íntegro de excepciones, basado fundamentalmente en 3 tipos de excepciones propias, para manejo de selecciones, transiciones y ataques inválidos. 

Se adjuntan los tests arbitrarios de ataque e item solicitados dentro de la clase `TestPhase.java`.

Se incluyen además en `/src/test/java/` todos los tests de UnitTesting con coverage de aprox. 91% (Nota: como se pedía solo coverage en el backend, y no en la GUI, se toma como porcentaje de coverage el del paquete `aventurasdemarcoyluis.backend`), junto con los diagramas de clase UML en la carpeta `/UML Diagrams/`


Se ha incluido una GUI muy básica (No alcanza a hacer un turno entero del juego :(, pero lo inicial está implementado. )

## Para Ejecutar la GUI básica

1. Seleccionar la pestaña de Gradle en el IDE
2. Ir a Tasks -> Application -> Run.

Intentar ejecutar la clase MainGUI directamente no funciona, puesto que gradle no importa las libraries de javafx necesarias.


NOTA: Hay un problema con el diagrama UML de la solucion. La última version de intelij no me deja expandirlo todo, y por lo tanto solamente quedan a la vista los paquetes en lso que estan contenidas las clases :(. Adjunto el archivo.uml en la carpeta "UML diagrams". El problema es que al intentar expandir el diagram para ver las clases del paquete "aventurasdemarcoyluis", intelij muestra por menos de un segunso el paquete expandido, y luego casi inmediatamente lo contrae, impidiendo sacar uan foto para adjuntarla con la entrega.