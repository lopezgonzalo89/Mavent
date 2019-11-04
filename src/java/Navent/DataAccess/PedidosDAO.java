package Navent.DataAccess;

import Navent.Entities.Pedido;
import java.util.HashMap;

public class PedidosDAO {
    
    static HashMap<Integer, Pedido> mockPedido = new HashMap<Integer, Pedido>();
    
    public static Pedido select(Integer IdPedido) {
        return mockPedido.get(IdPedido);        
    }
    
    static int ultIdPedido = 0;
    
    // null, toma como referencia ultIdPedido para crear uno nuevo.
    public static void insertOrUpdate(Pedido pedido) {
        if (pedido.getIdPedido() == null){
        pedido.setIdPedido(++ultIdPedido);
        }
        mockPedido.put(pedido.getIdPedido(), pedido);
    }

    public static void delete(Pedido pedido) {
       mockPedido.remove(pedido.getIdPedido());
    }
}
