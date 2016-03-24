package gessi.ossecos.istarparser;

import java.util.ArrayList;

public class GraphIS {

    private final ArrayList<NodeIS> nodeISList;
    private final ArrayList<EdgeIS> edgeISList;

    public GraphIS() {
        nodeISList = new ArrayList<>();
        edgeISList = new ArrayList<>();
    }

    public void addNode(NodeIS node) {
        if (node != null) {
            nodeISList.add(node);
        }
    }

    public void addEdge(EdgeIS edge) {
        if (edge != null) {
        edgeISList.add(edge);
        }
    }

    public ArrayList<NodeIS> getNodeISList() {
        return nodeISList;
    }

    public ArrayList<EdgeIS> getEdgeISList() {
        return edgeISList;
    }

}