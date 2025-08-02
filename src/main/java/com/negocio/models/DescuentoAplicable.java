package com.negocio.models;

public interface DescuentoAplicable {
    public boolean aplicarDescuento(int pedidoId, double porcentaje);
}
