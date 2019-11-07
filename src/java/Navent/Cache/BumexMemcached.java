package Navent.Cache;

import java.net.InetSocketAddress;
import java.util.HashMap;

public class BumexMemcached {
    private static BumexMemcached cache;

    static HashMap<String, Object> mockCache = new HashMap<String, Object>();
    
    private BumexMemcached(InetSocketAddress[] servers) {
    }
    public static BumexMemcached getCache(InetSocketAddress[] servers){
        if (cache == null){
            cache = new BumexMemcached(servers);
        }
        return cache;
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
