# AventurasdeMarcosyLuis

#### Alumno: Pablo González Aguilera - 20973277-7

El siguiente programa consiste en la implementación del juego RPG de "Las flipantes Aventuras de Marcos y Luis". 

El juego código distintos personajes, enemigos e ítems, y las posibles interacciones entre sí. El código fuente del juego se encuentra en `/src/test/java/aventurasdemarcoyluis.examples.PlayExample.Java` 

Las interacciones entre personajes, enemigos e ítems están descritas en el enunciado de la _Tarea_, que se encuentra en: https://www.u-cursos.cl/ingenieria/2021/2/CC3002/1/tareas/r/2021091617049EBCB4003D150FF0__Enunciado_Tarea_01.pdf

En general, se ha utilizado una aproximación de resolución del problema basada en herencia de clases, en donde se tiene que la jerarquia decreciente de clases para entidades es:

`Entity <- Player <- Marcos, Luis`
`Entity <- Enemy <- Goomba, Spiny, Boo`

Se realiza algo análogo en el caso de los items del juego.

Para ejecutar el código, se incluye una pequeña demostración funcional del programa en `/src/main/java/aventurasdemarcoyluis`, paquete que en sí está subdividido acorde a cada uno de los componentes principales del juego antes descritos.

Algunas suposiciones realizadas (puesto que no se han descrito explícitamente en el enunciado de la tarea) son:
    
    1. Los enemigos igual tienen FP. esto es en caso de querer agregar algún enemigo especial que utilice FP como "maná"
    2. Los enemigos pueden utilizar items. (Similar a 1., esto es en caso de querer agregar esta mecanica más adelante)
    3. Un pesronaje K.O. puede "Revivir" al utilizar el item  _Red Mushroom_
    4. La implementación del "estado invencible" se ha realizado mediante un campo de Booleano en cada personaje.
    5. La vida (HP) y el maná (FP) de cada entidad tienen valores máximos (maxFp, maxHp), y mínimos (HP,FP>0)
    6. Cada instancia de una entidad tiene un nombre (String name) carácteristico, en caso de querer jugar con nombres de la vida real (Por , si Luis Jara quisiera jugar con el personaje Luis)

Se incluyen además en `/src/test/java/` todos los tests de UnitTesting con coverage de aprox. 94%, junto con los diagramas de clase UML en la carpeta `/UML Diagrams/`