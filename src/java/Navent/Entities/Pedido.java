package Navent.Entities;

public class Pedido {
    private int _IdPedido;
    private String _Nombre;
    private String _Monto;
    private String _Descuento;

    public Pedido() {
    }    

    public Pedido(int _IdPedido, String _Nombre, String _Monto, String _Descuento) {
        this._IdPedido = _IdPedido;
        this._Nombre = _Nombre;
        this._Monto = _Monto;
        this._Descuento = _Descuento;
    }        

    public int getIdPedido() {
        return _IdPedido;
    }

    public void setIdPedido(int _IdPedido) {
        this._IdPedido = _IdPedido;
    }

    public String getNombre() {
        return _Nombre;
    }

    public void setNombre(String _Nombre) {
        this._Nombre = _Nombre;
    }

    public String getMonto() {
        return _Monto;
    }

    public void setMonto(String _Monto) {
        this._Monto = _Monto;
    }

    public String getDescuento() {
        return _Descuento;
    }

    public void setDescuento(String _Descuento) {
        this._Descuento = _Descuento;
    }
 
    
}