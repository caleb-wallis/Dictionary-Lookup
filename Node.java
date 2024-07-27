/**
 * Node for a StrBST
 */
public class Node{

    // Value of the node
    String value;
    // The left node of this node
    Node left;
    // The right node of this node
    Node right;

    /**
     * Initializes a Node Object
     * 
     * @param s Value of this node
     */
    public Node(String s){
        value = s;
        left = null;
        right = null;
    }
}