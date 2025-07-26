## ERROR 1: Atributos públicos (Mala práctica de encapsulamiento)
* Ubicacion: modsrc/main/java/com/negocio/models/Producto.java lineas 5-8
* Tipo de error: Mala practica
### solucion implementada:
se cambio a atributos privados y agregaron metodos para acceso
### impacto:
mala practica, violacion hacia el principio del encapsulamiento, afectaba a todo el modelo y sus funciones

## ERROR 2: Constructor sin validaciones
* Ubicacion: modsrc/main/java/com/negocio/models/Producto.java lineas 17
* Tipo de error: Mala practica
### solucion implementada:
se agrego una validacion para ver si el stock es negativo
### impacto:
permitia que se crearan productos de stock negativo.

## ERROR 3: Método que permite stock negativo
* Ubicacion: modsrc/main/java/com/negocio/models/Producto.java lineas 29-35
* Tipo de error: Logico
### solucion implementada:
se llama el getter de stock y se valida si exite stock antes de la operacion
### impacto:
no verificaba si existia stock asi que podia llevar el inventario a negativo.


## ERROR 4: Método con lógica incorrecta
* Ubicacion: modsrc/main/java/com/negocio/models/Producto.java lineas 41
* Tipo de error: Logico
* solucion implementada:
se uso la operacion >= para permitir que se pueda vender la cantidad justa implementada
* impacto:
no dejaba que el usuario vendiera la justa cantidad existente
Cálculo incorrecto del total (suma precios sin considerar cantidades)


## ERROR 4: Cálculo incorrecto del total (suma precios sin considerar cantidades)
* Ubicacion: modsrc/main/java/com/negocio/models/Producto.java lineas 41
* Tipo de error: Logico
* solucion implementada:
se uso la operacion >= para permitir que se pueda vender la cantidad justa implementada
* impacto:
no dejaba que el usuario vendiera la justa cantidad existente
