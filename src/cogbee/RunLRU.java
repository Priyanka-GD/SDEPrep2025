package cogbee;

import java.io.IOException;

public class RunLRU {
    public static void main(String args[]) throws IOException
    {
        LRUCache lruObj = new LRUCache();
        lruObj.put(1,1);
        lruObj.put(2,2);
        lruObj.put(3,3);
        System.out.println(lruObj.get(3));
        System.out.println(lruObj.get(4));
    }
}
