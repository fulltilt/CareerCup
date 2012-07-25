package misc;

/**
 * Refactored java-code originally based on Mark Nelson's C++ implementation of Ukkonen's algorithm.
 * http://illya-keeplearning.blogspot.com/search/label/suffix%20tree
 */
import java.util.*;

public class SuffixTree {
    private String text;
    private Node root;
    private int nodesCount;

    public SuffixTree(String text) {
        this.text = text;
        root = new Node(null);

        Suffix active = new Suffix(root, 0, -1);
        for (int i = 0; i < text.length(); i++) {
            addPrefix(active, i);
        }
    }

    private void addPrefix(Suffix active, int endIndex) {
        Node lastParentNode = null;
        Node parentNode;

        while (true) {
            Edge edge;
            parentNode = active.getOriginNode();

            // Step 1 is to try and find a matching edge for the given node.
            // If a matching edge exists, we are done adding edges, so we break out of this big loop.
            if (active.isExplicit()) {
                edge = active.getOriginNode().findEdge(text.charAt(endIndex));
                if (edge != null)
                    break;
            } else {
                //implicit node, a little more complicated
                edge = active.getOriginNode().findEdge(text.charAt(active.getBeginIndex()));
                int span = active.getSpan();
                if (text.charAt(edge.getBeginIndex() + span + 1) == text.charAt(endIndex))
                    break;
                parentNode = edge.splitEdge(active);
            }

            // We didn't find a matching edge, so we create a new one, add it to the tree at the parent node position,
            // and insert it into the hash table.  When we create a new node, it also means we need to create
            // a suffix link to the new node from the last node we visited.
            Edge newEdge = new Edge(endIndex, text.length() - 1, parentNode);
            newEdge.insert();
            updateSuffixNode(lastParentNode, parentNode);
            lastParentNode = parentNode;

            // This final step is where we move to the next smaller suffix
            if (active.getOriginNode() == root)
                active.incBeginIndex();
            else
                active.changeOriginNode();
            active.canonize();
        }
        updateSuffixNode(lastParentNode, parentNode);
        active.incEndIndex();   //Now the endpoint is the next active point
        active.canonize();
    }

    private void updateSuffixNode(Node node, Node suffixNode) {
        if ((node != null) && (node != root)) {
            node.setSuffixNode(suffixNode);
        }
    }

    public void dumpEdges() {
        System.out.println("\tEdge \tStart \tEnd \tSuf \tFirst \tLast \tString");
        Queue<Node> queue = new LinkedList<Node>();

        queue.add(root);
        dumpEdges(queue);
    }

    private void dumpEdges(Queue<Node> queue) {
        while (!queue.isEmpty()) {
            Node node = queue.remove();
            for (Edge edge : node.getEdges()) {
                Node suffixNode = edge.getEndNode().getSuffixNode();
                System.out.print("\t" + edge + " " +
                        "\t\t" + edge.getStartNode() + " " +
                        "\t\t" + edge.getEndNode() + " " +
                        "\t\t" + ((suffixNode == null) ? "-1" : suffixNode) + " " +
                        "\t\t" + edge.getBeginIndex() + " " +
                        "\t\t" + edge.getEndIndex() + " " +
                        "\t\t");
                for (int l = edge.getBeginIndex(); l <= edge.getEndIndex(); l++) {
                    System.out.print(text.charAt(l));
                }
                System.out.println();

                if (edge.getEndNode() != null)
                    queue.add(edge.getEndNode());
            }
        }
    }


    private class Edge {
        private int beginIndex;     // can't be changed
        private int endIndex;
        private Node startNode;
        private Node endNode;       // can't be changed, could be used as edge id

        // each time edge is created, a new end node is created
        public Edge(int beginIndex, int endIndex, Node startNode) {
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
            this.startNode = startNode;
            this.endNode = new Node(null);
        }

        public Node splitEdge(Suffix suffix) {
            remove();
            Edge newEdge = new Edge(beginIndex, beginIndex + suffix.getSpan(), suffix.getOriginNode());
            newEdge.insert();
            newEdge.endNode.setSuffixNode(suffix.getOriginNode());
            beginIndex += suffix.getSpan() + 1;
            startNode = newEdge.getEndNode();
            insert();
            return newEdge.getEndNode();
        }

        public void insert() {
            startNode.addEdge(text.charAt(beginIndex), this);
        }

        public void remove() {
            startNode.removeEdge(text.charAt(beginIndex));
        }

        public int getSpan() {
            return endIndex - beginIndex;
        }

        public int getBeginIndex() {
            return beginIndex;
        }

        public int getEndIndex() {
            return endIndex;
        }

        public void setEndIndex(int endIndex) {
            this.endIndex = endIndex;
        }

        public Node getStartNode() {
            return startNode;
        }

        public void setStartNode(Node startNode) {
            this.startNode = startNode;
        }

        public Node getEndNode() {
            return endNode;
        }

        @Override
        public String toString() {
            return endNode.toString();
        }
    }


    private class Suffix {
        private Node originNode;
        private int beginIndex;
        private int endIndex;

        public Suffix(Node originNode, int beginIndex, int endIndex) {
            this.originNode = originNode;
            this.beginIndex = beginIndex;
            this.endIndex = endIndex;
        }

        public boolean isExplicit() {
            return beginIndex > endIndex;
        }

        public boolean isImplicit() {
            return endIndex >= beginIndex;
        }

        public void canonize() {
            if (!isExplicit()) {
                Edge edge = originNode.findEdge(text.charAt(beginIndex));

                int edgeSpan = edge.getSpan();
                while (edgeSpan <= getSpan()) {
                    beginIndex += edgeSpan + 1;
                    originNode = edge.getEndNode();
                    if (beginIndex <= endIndex) {
                        edge = edge.getEndNode().findEdge(text.charAt(beginIndex));
                        edgeSpan = edge.getSpan();
                    }
                }
            }
        }

        public int getSpan() {
            return endIndex - beginIndex;
        }

        public Node getOriginNode() {
            return originNode;
        }

        public int getBeginIndex() {
            return beginIndex;
        }

        public void incBeginIndex() {
            beginIndex++;
        }

        public void changeOriginNode() {
            originNode = originNode.getSuffixNode();
        }

        public int getEndIndex() {
            return endIndex;
        }

        public void incEndIndex() {
            endIndex++;
        }
    }


    private class Node {
        private Node suffixNode;
        private Map<Character, Edge> edges;
        private int name;

        public Node(Node suffixNode) {
            this.suffixNode = suffixNode;
            edges = new HashMap<Character, Edge>();

            name = nodesCount++;
        }

        public void addEdge(char ch, Edge edge) {
            edges.put(ch, edge);
        }

        public void removeEdge(char ch) {
            edges.remove(ch);
        }

        public Edge findEdge(char ch) {
            return edges.get(ch);
        }

        public Node getSuffixNode() {
            return suffixNode;
        }

        public void setSuffixNode(Node suffixNode) {
            this.suffixNode = suffixNode;
        }

        public Collection<Edge> getEdges() {
            return edges.values();
        }

        @Override
        public String toString() {
            return ((Integer) name).toString();
        }
    }
}
