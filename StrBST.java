/**
 * A String Binary Search Tree
 */
public class StrBST{

    // Root of the search tree
    Node root;

    // Initialize a StrBST object
    public StrBST(){
        root = null;
    }
    
    /**
     * Inserts the string into the binary search tree
     * 
     * @param s String to insert
     */
    public void insert (String s){
        // Call recursive insertion method
        root = insertR(root, s);
    }

    /**
     * Recursively insert a string to the StrBST
     * 
     * @param cRoot Root to recurse from
     * @param s String to insert
     * @return The current root if found a place to insert the string
     */
    private Node insertR(Node cRoot, String s){
        // No nodes so add here
        if (cRoot == null){
            cRoot = new Node(s);
        }

        // The difference between the string and the current nodes value
        int value = s.compareTo(cRoot.value);

        // Value is smaller
        if (0 > value){
            cRoot.left = insertR(cRoot.left, s);
        }
        // Value is larger
        else if (0 < value){
            cRoot.right = insertR(cRoot.right, s);
        }
        
        return cRoot;
    }

     /**
     * Removes the string from the binary search tree
     * 
     * @param s String to remove
     */
    public void remove(String s){
        // Call recursive deletion method
        root = removeR(root, s);
    }

     /**
     * Recursively remove a string from the StrBST
     * 
     * @param cRoot Root to recurse from
     * @param s String to remove
     * @return The current root after attempting to remove the value
     */
    private Node removeR(Node cRoot, String s){
        // No more nodes so return
        if (cRoot == null){
            return cRoot;
        }

        // The difference between the string and the current nodes value
        int value = s.compareTo(cRoot.value);

        // Value is smaller
        if (0 > value){
            cRoot.left = removeR(cRoot.left, s);
        }
        // Value is larger
        else if (0 < value){
            cRoot.right = removeR(cRoot.right, s);
        }
        else{
            // Node with only one child or no child
            if (cRoot.left == null)
                return cRoot.right;
            else if (cRoot.right == null)
                return cRoot.left;

            // Node with two children: Get the in-order successor (smallest in the right sub-tree)
            cRoot.value = minValue(cRoot.right);

            // Delete the inorder successor
            cRoot.right = removeR(cRoot.right, cRoot.value);
        }
        
        return cRoot;
    }

    /**
     * Find the left most root of the current root (generally a right sub-tree for 2 child removal)
     * 
     * @param cRoot Current root to go left from
     * @return The current roots value after reaching the left most nodes
     */
    private String minValue(Node cRoot) {
        // While not at the left most null
        while (cRoot.left != null) {
            // Go to the next left node
            cRoot = cRoot.left;
        }
        // Return the left most node
        return cRoot.value;
    }

    /**
     * Searches for a string in the binary search tree
     * 
     * @param s String to find
     * @return True if string is found; otherwise false
     */
    public boolean search(String s){
        // Call recursive search method
        boolean found = searchR(root, s);
        return found;
    }       

     /**
     * Recursively search for a string from the StrBST
     * 
     * @param cRoot Root to recurse from
     * @param s String to find
     * @return True if string exists in the strBST; otherwise false
     */
    private boolean searchR(Node cRoot, String s){
        boolean found = false;

        // No nodes so didn't find in the tree
        if (cRoot == null){
            return false;
        }

        // The difference between the string and the current nodes value
        int value = s.compareTo(cRoot.value);

        // Found the string
        if (value == 0){
            return true;
        }
        // Value is smaller
        else if (0 > value){
            found = searchR(cRoot.left, s);
        }
        // Value is larger
        else if (0 < value){
            found = searchR(cRoot.right, s);
        }
        return found;
    }

    /**
     * Prints out an in-order traversal of the strBST 
     */
    public void print(){
        // Call recursive traversal method
        inorderTraversal(root);
    }

    /**
     * Recursively search for a string from the StrBST
     * 
     * @param cRoot Root to recurse from
     */
    private void inorderTraversal(Node cRoot) {
        // If we can travel down a root
        if (cRoot != null) {

            // Perform in-order traversal on the left side of the root
            inorderTraversal(cRoot.left);

            // Print out the root + value, left root and right root
            System.out.print("Root: " + cRoot.value + " | ");

            System.out.print("Left: ");
            if (cRoot.left != null)
                System.out.print(cRoot.left.value + " | ");
            else
                System.out.print("null | ");

            System.out.print("Right: ");
            if (cRoot.right != null)
                System.out.print(cRoot.right.value);
            else
                System.out.print("null");

            System.out.println();

            // Perform in-order traversal on the right side of the root
            inorderTraversal(cRoot.right);
        }
    }
}