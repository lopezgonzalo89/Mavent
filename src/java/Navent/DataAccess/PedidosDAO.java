package Navent.DataAccess;

import Navent.Entities.Pedido;
import java.util.HashMap;

public class PedidosDAO {
    
    static HashMap<Integer, Pedido> mockPedido = new HashMap<Integer, Pedido>();
    
    public Pedido select(Integer IdPedido) {
        return mockPedido.get(IdPedido);        
    }
    
    static int ultIdPedido = 0;
    
    public void insertOrUpdate(Pedido pedido) {
        pedido.setIdPedido(++ultIdPedido);
        mockPedido.put(pedido.getIdPedido(), pedido);
    }

    public void delete(Pedido pedido) {
       mockPedido.remove(pedido.getIdPedido());
    }
}
