package amazoninternal.codingassessment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IpSearch {
    public static void main(String[] args) {
        Trie root = new Trie("-1");
        List<String> blockedIps = List.of("192.0.0.5", "194.0.0.6");
        formIpTrie(root, List.of("192.0.0.1", "192.0.0.2", "192.0.0.3", "194.0.0.1", "194.0.0.5", "192.0.0.5"), blockedIps);
        System.out.println(searchIP(root, "192.0.0.5", blockedIps));
        System.out.println(searchIP(root, "192.0.0.3", blockedIps));
    }

    public static void formIpTrie(Trie root, List<String> ips, List<String> blockedIps) {
        for (String ip : ips) {
            if (!blockedIps.contains(ip)) {
                Trie currNode = root;
                String[] ipParts = ip.split("\\.");
                for (String part : ipParts) {
                    currNode.children.putIfAbsent(part, new Trie(part));
                    currNode = currNode.children.get(part);
                }
            }
        }
    }

    public static boolean searchIP(Trie node, String ip, List<String> blockedIps) {
        if (blockedIps.contains(ip))
            return false;
        String[] ipParts = ip.split("\\.");
        for (String part : ipParts) {
            if (node.children.containsKey(part)) {
                node = node.children.get(part);
            } else {
                return false;
            }
        }
        return true;
    }
}

class Trie {
    String key;
    Map<String, Trie> children;

    public Trie(String key) {
        this.key = key;
        this.children = new HashMap<>();
    }
}
/*
Time Complexity :
    n = number of allowed IPS
    b = number of blocked Ips
    k = size of the ip which is 4 here so

    O(n * (b + k)) , now since k is constant
    O(n * b)

Space complexity is O(m · k)
     where m is the number of non-blocked IPs inserted (m ≤ n).
     Since k is constant, it simplifies to O(n)

*/