/* Created by meganlee on 11/15/14. */

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class CloneGraph {
    //---------------------- Solution -------------------//
    // BFS (DFS, queue --> stack)
    public UndirectedGraphNode cloneGraph(UndirectedGraphNode node) {
        if (node == null) {
            return null;
        }

        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        Queue<UndirectedGraphNode> q = new LinkedList<UndirectedGraphNode>();
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        q.offer(node);
        map.put(node, copy);

        // use queue, bfs
        while (!q.isEmpty()) {
            UndirectedGraphNode cur = q.poll();
            UndirectedGraphNode curCopy = map.get(cur);
            for (UndirectedGraphNode nb : cur.neighbors) {
                if (!map.containsKey(nb)) {
                    q.offer(nb);
                    map.put(nb, new UndirectedGraphNode(nb.label));
                }
                curCopy.neighbors.add(map.get(nb));
            }
        }
        return copy;
    }


    //---------------------- Recursion DFS -------------------//
    public UndirectedGraphNode cloneGraph2(UndirectedGraphNode node) {
        if(node == null) {
            return null;
        }
        Map<UndirectedGraphNode, UndirectedGraphNode> map = new HashMap<UndirectedGraphNode, UndirectedGraphNode>();
        UndirectedGraphNode copy = new UndirectedGraphNode(node.label);
        map.put(node, copy);
        helper(node, map);
        return copy;
    }

    private void helper(UndirectedGraphNode node, Map<UndirectedGraphNode, UndirectedGraphNode> map) {
        for(UndirectedGraphNode nb : node.neighbors) {
            if (!map.containsKey(nb)) {
                map.put(nb, new UndirectedGraphNode(nb.label));
                helper(nb, map);
            }
            map.get(node).neighbors.add(map.get(nb));
        }
    }

}

