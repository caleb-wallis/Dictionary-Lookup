/**
 * A Dictionary Binary Search Tree
 */
public class DictionaryBST{

    // Root of the search tree
    DictionaryNode root;

    // Initialize a DictionaryBST object
    public DictionaryBST(){
        root = null;
    }
    
    /**
     * Inserts the string and definition into the binary search tree
     * 
     * @param s String to insert
     * @param def Definition of the string being inserted
     */
    public void insert (String s, String def){
        // Call recursive insertion method
        root = insertR(root, s, def);
    }

    /**
     * Recursively insert a string and its definition to the DictionaryBST
     * 
     * @param cRoot Root to recurse from
     * @param s String to insert
     * @param def Definition of the string being inserted
     * @return The current root if found a place to insert the string
     */
    private DictionaryNode insertR(DictionaryNode cRoot, String s, String def){
        // No nodes so add here
        if (cRoot == null){
            cRoot = new DictionaryNode(s, def);
        }

        // The difference between the string and the current nodes value
        int value = s.compareTo(cRoot.value);

        // Value is smaller
        if (0 > value){
            cRoot.left = insertR(cRoot.left, s, def);
        }
        // Value is larger
        else if (0 < value){
            cRoot.right = insertR(cRoot.right, s, def);
        }
        
        return cRoot;
    }

     /**
     * Removes the string and its definition from the DictionaryBST
     * 
     * @param s String to remove
     */
    public void remove(String s){
        // Call recursive deletion method
        root = removeR(root, s);
    }

     /**
     * Recursively remove a string and its definition from the DictionaryBST
     * 
     * @param cRoot Root to recurse from
     * @param s String to remove
     * @return The current root after attempting to remove the value
     */
    private DictionaryNode removeR(DictionaryNode cRoot, String s){
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
    private String minValue(DictionaryNode cRoot) {
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
     * Recursively search for a string from the DictionaryBST
     * 
     * @param cRoot Root to recurse from
     * @param s String to find
     * @return True if string exists in the DictionaryBST; otherwise false
     */
    private boolean searchR(DictionaryNode cRoot, String s){
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
     * Prints out an in-order traversal of the DictionaryBST 
     */
    public void print(){
        // Call recursive traversal method
        inorderTraversal(root);
    }

    /**
     * Recursively traverse through the DictionaryBST and print out its nodes
     * 
     * @param cRoot Root to recurse from
     */
    private void inorderTraversal(DictionaryNode cRoot) {
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

    /**
     * Prints the value and definition of a given word/phrase (String s).
     * 
     * @param s String to print out itself and its definition
     */
    public void printDictionaryItem(String s){
        printDictionaryItemR(root, s);
    }

    /**
     * Recursively prints the value and definition of a given word/phrase (String s). 
     * 
     * @param cRoot Root to recurse from
     * @param s String to print out itself and its definition
     */
    private void printDictionaryItemR(DictionaryNode cRoot, String s){
        // No nodes so didn't find in the tree
        if (cRoot == null){
            return;
        }

        // The difference between the string and the current nodes value
        int value = s.compareTo(cRoot.value);

        // Found the string
        if (value == 0){
            System.out.println("Value: " + cRoot.value);
            System.out.print("Definition: " + cRoot.definition);
        }
        // Value is smaller
        else if (0 > value){
            printDictionaryItemR(cRoot.left, s);
        }
        // Value is larger
        else if (0 < value){
            printDictionaryItemR(cRoot.right, s);
        }
    }


    /**
     * Print out all the values and definitions from the DictionaryBST 
     */
    public void printDictionary(){
        printDictionaryR(root);
    }

    /**
     * Recursively print out all values and their definitions from the DictionaryBST
     * 
     * @param cRoot Root to recurse from
     */
    private void printDictionaryR(DictionaryNode cRoot) {
        // If we can travel down a root
        if (cRoot != null) {

            // Perform in-order traversal on the left side of the root
            printDictionaryR(cRoot.left);

            // Print out the value and definition
            System.out.println(cRoot.value);
            System.out.println(cRoot.definition);
            System.out.println();

            // Perform in-order traversal on the right side of the root
            printDictionaryR(cRoot.right);
        }
    }
}