/* Created by meganlee on 11/15/14.*/

import java.util.ArrayList;
import java.util.List;

public class UndirectedGraphNode {
    int label;
    List<UndirectedGraphNode> neighbors;
    public UndirectedGraphNode(int val) {
        label = val;
        neighbors = new ArrayList<UndirectedGraphNode>();
    }
}
