package Navent.Cache;

import java.net.InetSocketAddress;
import java.util.HashMap;

public class BumexMemcached {

    static HashMap<String, Object> mockCache = new HashMap<String, Object>();
    
    public BumexMemcached(InetSocketAddress[] servers) {
    }

    public void set(String key, Object value) {
       mockCache.put(key, value );
    }

    public Object get(String key) {
        return mockCache.get(key);
    }
    
    public void delete(String key) {
        mockCache.remove(key);
    }
}
