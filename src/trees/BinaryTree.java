package trees;

import java.util.*;

public class BinaryTree {
    private Node root = null;   // Root node pointer. Will be null for an empty tree.
    private int balanceFactor;
    
    public static class Node {
        Node leftChild = null;
        Node rightChild = null;

        int value;

        Node(int newValue) { value = newValue; }
    }

    public BinaryTree() {
        root = new Node(1);
        root.leftChild = new Node(2);
        root.rightChild = new Node(3);
        root.rightChild.leftChild = new Node(6);
        root.leftChild.leftChild = new Node(4);
        root.leftChild.rightChild = new Node(5);
        root.leftChild.leftChild.rightChild = new Node(7);
        root.rightChild.rightChild = new Node(5);
        root.rightChild.rightChild.leftChild = new Node(4);
        root.rightChild.rightChild.leftChild.leftChild = new Node(2);
        root.rightChild.rightChild.leftChild.leftChild.leftChild = new Node(1);
        root.rightChild.rightChild.leftChild.leftChild.rightChild = new Node(3);
        root.rightChild.rightChild.rightChild = new Node(8);
        root.rightChild.rightChild.rightChild.leftChild = new Node(7);
        root.rightChild.rightChild.rightChild.rightChild = new Node(9);
        root.rightChild.rightChild.rightChild.rightChild.rightChild = new Node(10);
    }

    public int getHeight(Node node) {
        if (node == null)
            return 0;

        return 1 + Math.max(getHeight(node.leftChild), getHeight(node.rightChild));
    }

    public Node getRoot() { return root; }
    public void setRoot(int value) { root = new Node(value); }
    
    /******************************/

     /* Get the maximum element of a binary tree
        -algorithm: since this isn't a binary search tree where the rightmost element is always the max,
                    recurse in order doing a Math.max on the children and then Math.max with the resul
                    and current Node
     */     
     public int getMaxElement() { return getMaxElement(root); }
     private int getMaxElement(Node node) {
           if (node == null) 
               return Integer.MIN_VALUE;
           
           return Math.max(node.value, Math.max(getMaxElement(node.leftChild), getMaxElement(node.rightChild)));
     }
     
     /******************************/
     
    /* Find fxn that prints out the current level it's on
     * -algorithm: uses print-by-level logic but stops once the value is found and returns the current level
    */
    public boolean find(int value) { return find(root, value); }
    private boolean find(Node node, int value) {
        if (node == null)
            return false;

        LinkedList<Node> currentLevel = new LinkedList<Node>();
        LinkedList<Node> children = new LinkedList<Node>();
        currentLevel.add(node);
        int level = 1;

        while (!currentLevel.isEmpty()) {
            for (Node n : currentLevel) {
                if (n.value == value) {
                    System.out.println("Value " + value + " has been found on level " + level);
                    return true;
                }

                if (n.leftChild != null)
                    children.add(n.leftChild);
                if (n.rightChild != null)
                    children.add(n.rightChild);
            }
            currentLevel.clear();
            currentLevel.addAll(children);
            children.clear();
            ++level;
        }

        System.out.println("Value " + value + " was unable to be found");
        return false;
    }
    
    /******************************/

    /* Print a tree level-by-level
       -algorithm: Have 2 lists: one that represents the current level and the other that represents the children of the current
                   level. Once inside the loop, first put the children into a separate list then clear the current list while printing
                   out each of the nodes on the current level. Afterwards, clear the currentLevel list and add children to the 
                   current level. Clear the children list
    */
    public void printByLevel() { printByLevel(root); }
    private void printByLevel(Node node) {
        if (node == null)
            return;

        LinkedList<Node> currentLevel = new LinkedList<Node>();
        LinkedList<Node> children = new LinkedList<Node>();
        currentLevel.add(node);

        while (!currentLevel.isEmpty()) {
            for (Node n : currentLevel) {
                System.out.print(n.value + " ");

                if (n.leftChild != null)
                    children.add(n.leftChild);
                if (n.rightChild != null)
                    children.add(n.rightChild);
            }
            currentLevel.clear();
            currentLevel.addAll(children);
            children.clear();
            System.out.println();
        }
    }
    
    /******************************/
    
    /* Print all the paths from the roots to each leaf node
       -algorithm: Have any array of adequate length to accommodate a path and pass in a
                   variable which keeps track of the path length (since we can't use the
                   array's length as it's of fixed size). Once we get current Node to be null,
                   print out the path. Since pathLength is a primitive, we don't have to worry
                   about other calls to printPaths to affect each other. Also, it works out that
                   the path array will print out a path at a time and once it goes up the call
                   stack, any old values will be properly overwritten and this is all determined
                   by the local pathLength variable.
                   -notes: it would be cleaner if we could print when the node is null but we can't do 
                    that as it means it would be called for a null left AND null right child which we don't
                    want so we have to determine if the current Node is a leaf before printing  
    */
    public void printPaths() {
        int[] path = new int[1000];
        printPaths(root, path, 0);
    }
    public void printPaths(Node node, int[] path, int pathLength) {
        if (node == null) 
            return;
        
        path[pathLength] = node.value;
        ++pathLength;
        
        if (node.leftChild == null && node.rightChild == null) {
            for (int i = 0; i < pathLength; i++)
                System.out.print(path[i] + " ");
            System.out.println();
            return;
        }
        
        printPaths(node.leftChild, path, pathLength);
        printPaths(node.rightChild, path, pathLength);
    }
    
    /******************************/

    /* Check if the tree is a binary search tree
       -algorithm:  
    */
    public boolean isBST(Node node) { return isBST(node, Integer.MIN_VALUE, Integer.MAX_VALUE); }
    private boolean isBST(Node node, int min, int max) {
        if (node == null)   // if we made it this far, so far, the tree is a BST
            return true;

        // if the Node's data falls outside the range, return false
        if (node.value < min || node.value > max)
            return false;

        // check if the left child is within range (min...node.data) else return false
        return isBST(node.leftChild, min, node.value) &&

               // check if right child should be in range (node.data + 1...max) else return false
               isBST(node.rightChild, node.value + 1, max);
    }
    
    /* 2nd version of isBST() */
    public boolean isBST() {  
        return isBST(root, Integer.MIN_VALUE);
    }
    
    private boolean isBST(Node node, int previousValue) {
        if (node == null)
            return true;
            
        return isBST(node.leftChild, previousValue) 
               && node.value >= previousValue
               && isBST(node.rightChild, node.value);
    }
    
    /******************************/
    
    /*
        -algorithm: 
    */
    public void doubleTree() {
        doubleTree(root);
    }
    private void doubleTree(Node node) {
        if (node == null)
            return;

        Node newNode = new Node(node.value);
        newNode.leftChild = node.leftChild;
        node.leftChild = newNode;

        doubleTree(newNode.leftChild);
        doubleTree(node.rightChild);        // tricky part here is that if we did newNode.rightChild instead, the right children never get doubled
    }
    
    /******************************/

    /* CI51 - return mirror of a tree 
       -algorithm: 
    */
    public Node mirror() {
        Node newRoot = mirror(root);
        printByLevel(newRoot);
        return newRoot;
    }
    private Node mirror(Node node) {
        if (node == null)
            return null;

        Node newNode = new Node(node.value);
        newNode.leftChild = mirror(node.rightChild);
        newNode.rightChild = mirror(node.leftChild);

        return newNode;
    }

    /******************************/

    /* CI52 - verify if a binary tree is symmetrical 
       -algorithm: 
       -note: similar algorithm in BinarySearchTree.java but this has the added step to see that both
              values are the same (i.e. that they are exact mirrors of on another.
    */
    public boolean isSymmetrical() {
        return isSymmetrical(root, root);
    }
    private boolean isSymmetrical(Node node1, Node node2) {
        if (node1 == null && node2 == null)
            return true;
            
        if (node1 == null || node2 == null) // a symmetrical trees nodes are either both null or both not null
            return false;
            
        if (node1.value != node2.value)
            return false;
            
        return isSymmetrical(node1.leftChild, node2.rightChild) 
               && isSymmetrical(node1.rightChild, node2.leftChild); 
    }
    
    /******************************/

    /* CI85 - get depth 
       -algorithm: 
    */
    public int getDepth() {
        return getDepth(root);
    }
    private int getDepth(Node node) {
        if (node == null)
            return 0;
            
        return 1 + Math.max(getDepth(node.leftChild), getDepth(node.rightChild));
    }
    
    /******************************/
    
    /* CI50 - is a tree a subtree of another 
       -algorithm: 
    */
    public static boolean isSubTree(BinaryTree tree1, BinaryTree tree2) {   
        return isSubTree(tree1.getRoot(), tree2.getRoot());
    }
    private static boolean isSubTree(Node root1, Node root2) {
        boolean result = false;
        
        if (root1 != null && root2 != null) {
            if (root1.value == root2.value)
                result = doesTree1ContainTree2(root1, root2);
            if (!result)
                result = isSubTree(root1.leftChild, root2);
            if (!result)
                result = isSubTree(root1.rightChild, root2); 
        }
        
        return result;
    }
    
    /* Determine if a tree is a subtree of another tree
       -algorithm: 
    */     
    private static boolean doesTree1ContainTree2(Node root1, Node root2) {
        if (root2 == null)  // check the potential subTree first. If we get a null value for the subtree, return true
            return true;
            
        if (root1 == null) // return false because there's no corresponding root1 node
            return false;
            
        if (root1.value != root2.value)
            return false;
            
        return doesTree1ContainTree2(root1.leftChild, root1.leftChild) 
               && doesTree1ContainTree2(root1.rightChild, root1.rightChild);
    }
    
    /******************************/
    
    /* Determines if there exists a path in a tree whose sum of values equals 'sum'
     * -algorithm: determine if the sum is currently zero. If so, return true. Else recursively
     *             call itself on each child and subtracting the node's current value from 'sum'.
     *             The trick to this is to chain results of both children via the '||'
     */
    public boolean hasPathSum(int sum) {
        return hasPathSum(root, sum);
    }
    private boolean hasPathSum(Node node, int sum) {
        if (node == null)
            return (sum == 0);

        // if sum is reached in a child node that isn't a leaf 
        if (sum == 0)
            return true;
        
        return hasPathSum(node.leftChild, sum - node.value) || hasPathSum(node.rightChild, sum - node.value);
    }    
    
    /* CI60 - just like hasPathSum but it prints out all the paths that add up to the expected sum 
     * -algorithm: We need a 3rd argument which is an ArrayList of Nodes. The process is just like hasPathSum
     *             but with 2 additional steps: add the node to the list at the beginning and take it off
     *             at the end which is important as the ArrayList is shared 
     */
    public void printSumPaths(int expectedSum) {       
        ArrayList<Node> path = new ArrayList<Node>();
        printSumPaths(root, expectedSum, path);
    }
    private void printSumPaths(Node node, int sum, ArrayList<Node> path) {
        if (node == null)
            return;
        
        path.add(node);
        
        if (sum == 0) {
            for (Node n : path)
                System.out.print(n.value + " ");
            System.out.println();
            return;
        }
        
        printSumPaths(node.leftChild, sum - node.value, path);
        printSumPaths(node.rightChild, sum - node.value, path);
        
        path.remove(path.size() - 1);
    }
    
    /******************************/
    
    /* CI86 - is the tree balanced 
       -algorithm: get the depth for the left and right child. If the difference is > 1, return false. Recursively go down
                   the tree for each node and check. (In BinarySearchTree.java, there the algorithm used there may be more efficient)
    */
    public boolean isBalanced() {
        return isBalanced(root);
    }
    private boolean isBalanced(Node node) {
        if (node == null)
            return true;
            
        int leftDepth = getDepth(node.leftChild);
        int rightDepth = getDepth(node.rightChild);
        if (Math.abs(leftDepth - rightDepth) > 1)
            return false;
            
        return isBalanced(node.leftChild) && isBalanced(node.rightChild);
    }
    
    /******************************/
    
    /* Find the lowest common ancestor in a binary tree
     * -algorithm: starting from the root, do a search for the first node on the left child and then
     *             do a search for the second node on the left child. If found, do the same for the 
     *             left child. If not found, repeat procedure on the right child. If each node is
     *             not under the same child, we found the lowest common ancestor
     * -assumptions: n1 and n2 are in the Tree in question           
     */
    public Node lowestCommonAncestor(Node n1, Node n2) {
        if (n1 == null || n2 == null)
            return null;
        
        Node current = root;
        while (current != null) {
            if (findNode(current.leftChild, n1) && findNode(current.leftChild, n2))
                current = current.leftChild;
            else if (findNode(current.rightChild, n1) && findNode(current.rightChild, n2))
                current = current.rightChild;
            else 
                return current;
        }
        
        return null;
    }
    
    /* Helper fxn for lowestCommonAncestor. Given a root node, determines if a node is under the root
     */
    public boolean findNode(Node root, Node node) {
        if (root == null)
            return false;
        
        if (node == root)
            return true;
        
        return findNode(root.leftChild, node) || findNode(root.rightChild, node);
    }
    /******************************/
    
    /* ??
       -algorithm: 
    */    
    public double getBalanceFactor() {
        balanceFactor = 0;
        getBalanceFactor(root, 1);
        
        return balanceFactor;
    }
    private int getBalanceFactor(Node node, int level) {
        if (node == null)
            return 0;
        
        int desc = getBalanceFactor(node.leftChild, level + 1) + 
                   getBalanceFactor(node.rightChild, level + 1);
        balanceFactor += desc * 1.0 / level;
           
        return desc + 1;
    }
    
    /* Get the the max width of a tree
       -algorithm: 
    */
    public int getDiameter(Node node) {
        if (node == null)
            return 0;

        int leftDiameter  = getDiameter(node.leftChild);
        int rightDiameter = getDiameter(node.rightChild);
        int rootDiameter  = getHeight(node.leftChild) + getHeight(node.rightChild) + 1;

        return Math.max(rootDiameter, Math.max(leftDiameter, rightDiameter));
    }

    /******************************/
    
    public static void main(String[] args) {
        //BinaryTree bt = new BinaryTree();
        //System.out.println(bt.getBalanceFactor());
        //bt.printByLevel();
    }
}
