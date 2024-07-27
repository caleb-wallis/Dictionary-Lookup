/**
 * Node for a DictionaryBST
 */
public class DictionaryNode{

    // Value of the node
    String value;
    // Definition of the value
    String definition;
    // The left node of this node
    DictionaryNode left;
    // The right node of this node
    DictionaryNode right;

    /**
     * Initializes a DictionaryNode Object
     * 
     * @param s Value of this node
     * @param def Definition of this nodes value
     */
    public DictionaryNode(String s, String def){
        value = s;
        definition = def;
        left = null;
        right = null;
    }
}