Mejora #[1]:
Ubicación:src\main\java\com\negocio\models\Pedido.java linea 26-30
Descripción del cambio:
se hizo un if para verificar que no se agreguen productos con el mismo nombre
se lanza un error si se intenta agregar un producto que ya existe
Justificación:
Se agregó una validación para que no se agreguen productos con el mismo nombre.