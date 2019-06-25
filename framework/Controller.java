package dankmemer.dependencies.framework;

import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;

public class Controller {


    private static final Comparator<Node> PRIORITY_COMPARATOR = Comparator.comparingInt(o -> o.priority().ordinal());
    private final PriorityQueue<Node> children = new PriorityQueue<Node>(100, PRIORITY_COMPARATOR);

    
    
    public Controller(Node... nodes) {
        children.addAll(Arrays.asList(nodes));
    }

    public void addNodes(Node... nodes) {
        children.addAll(Arrays.asList(nodes));
    }

    public void clearNodes() {
        children.clear();
    }

    public void removeNodes(Node... nodes) {
        for (Node n : nodes) {
            children.remove(n);
        }
    }

    public PriorityQueue<Node> getNodes() {
        return children;
    }

    public Node getCurrentNode() {
    	for (Node n : children) {
    	      if (n.validate()) 
    	    	  return n;
    	}
    	return null;
    }

}