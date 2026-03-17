package amazoninternal.codingassessment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class IpSearch {
    public static void main(String[] args) {
        Trie root = new Trie();
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
                    currNode.children.putIfAbsent(part, new Trie());
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
    Map<String, Trie> children = new HashMap<>();
}
/*
Time Complexity :
    n = number of allowed IPS
    b = number of blocked Ips
    k = size of the ip which is 4 here so

    O(n * (b + k)) , now since k is constant
    O(n * b)

Space complexity is O(N + B)
    Trie Storage: Still O(N x K), which simplifies to O(N).
    Set Storage: $O(B)$, where B is the number of blocked IPs.Total SC: O(N + B).
    Since k is constant, it simplifies to O(n)
    Since an IPv4 address is always split into exactly 4 segments,
    the traversal depth is constant (K = 4).However, Space Complexity
    accounts for more than just the stack; it primarily measures the heap memory
    used to store your data structure.Why Space Complexity is O(N)
    While the "height" of your Trie is fixed at 4, the "width" grows as you add more IPs.
    Node Count: In the worst case, if you have $N$ IPs that share no common prefixes (e.g., 1.1.1.1, 2.2.2.2, 3.3.3.3),
    you will have to create 4 new Trie nodes for every single IP.Total Nodes: 4 times N nodes.
    Storage: Each node contains a HashMap. Even if the depth is constant, the number of objects living on the heap
    is directly proportional to the number of unique IPs (N) you insert.
*/