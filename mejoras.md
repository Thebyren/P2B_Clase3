Mejora #[1]:
Ubicación:src\main\java\com\negocio\models\Pedido.java linea 26-30
Descripción del cambio:
se hizo un if para verificar que no se agreguen productos con el mismo nombre
se lanza un error si se intenta agregar un producto que ya existe
Justificación:
Se agregó una validación para que no se agreguen productos con el mismo nombre.


Mejora #[2]:
Ubicación:src\main\java\com\negocio\models\Pedido.java linea 18
Descripción del cambio:
se agrego el localdatetime.now() para asignar la fecha de orden
Justificación:
asi se tiene control de que dia se hizo la orden



Mejora #[3]:
Ubicación: src\main\java\com\negocio\models\DescuentoAplicable.java
Descripción del cambio:
se implemento la interface descuento aplicable
Justificación:
se agrego una intefaz para aplicar descuentos de forma mas dinamica