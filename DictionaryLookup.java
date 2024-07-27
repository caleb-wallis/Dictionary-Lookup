import java.io.File;  // Import the File class to handle files
import java.io.FileNotFoundException;  // Import this class to handle file errors
import java.util.Scanner; // Import the Scanner class to read a text file

/**
 * Allows users to interact with a dictionary
 */
public class DictionaryLookup{

    // Dictionary binary search tree to store the dictionary
    DictionaryBST dictionary = new DictionaryBST();

    // Scanner for user inputs
    static Scanner inputReader = new Scanner(System.in); 

    // Files that the dictionary is stored in
    File dictionaryFile = new File("CS-Dictionary-full-shuffled.txt");
    //File dictionaryFile = new File("CS-Dictionary-small-shuffled.txt");

    /**
     * This is the main method of the program.
     * 
     * It loads the dictionary from a file.
     * It allows the user to interact/choose what they want to do with the dictionary.
     * 
     * @param args The command-line arguments passed to the program.
     *             These arguments are used to configure and customize
     *             the behavior of the program.
     */
    public static void main(String[] args) {
        DictionaryLookup dictLookup = new DictionaryLookup();

        // Load in the dictionary
        System.out.println("Loading dictionary from file ...\n");
        dictLookup.loadDictionary();

        System.out.println("Welcome to the Dictionary Lookup!");

        // Until the program ends/exits
        while(true){
            System.out.println("\nPlease enter a number to indicate what you would like to do");
            System.out.println("        (1) Search for a word/phrase in the dictionary");
            System.out.println("        (2) Print a given word/phrase and it's definition");
            System.out.println("        (3) Add a word/phrase and definition to the dictionary");
            System.out.println("        (4) Remove a word/phrase and definition from the dictionary");
            System.out.println("        (5) Print all of the words/phrases and their definitions, in alphabetical order");
            System.out.println("        (6) Exit");

            System.out.println("");
            System.out.println("Please enter a number between 1 and 6:");

            // Read the users input
            String option = inputReader.nextLine(); 
            System.out.println("");

            // Run a method based on what the user decided to do
            switch(option)
            {
                case "1":
                    dictLookup.search();
                    break;

                case "2":
                    dictLookup.print();
                    break;

                case "3":
                    dictLookup.add();
                    break;

                case "4":
                    dictLookup.remove();
                    break;

                case "5":
                    dictLookup.printAll();
                    break;

                case "6":
                    inputReader.close();
                    System.exit(0);

                default:
                    System.out.println("Not a valid input/option");
                    continue;
            }

            // Wait until the user decides to continue
            System.out.println("");
            System.out.println("Press Enter to Continue");
            inputReader.nextLine();

        }
    }
    
    /**
     * Searches for a string in the binary search tree
     */
    private void search(){
        System.out.println("Searching the dictionary ...\n");

        // Get the word/phrase to search for
        System.out.println("Please enter a word/phrase to search for");
        String words = inputReader.nextLine();

        // Display whether the word/phrase exists in the dictionary
        boolean found = dictionary.search(words);
        System.out.println("The word/phrase " + words + " exists in the dictionary: " + found);
    }

    /**
     * Prints a given word/phrase and it's definition
     */
    private void print(){
        System.out.println("Printing dictionary item ...\n");

        // Get the word/phrase to search for
        System.out.println("Please enter a word/phrase to print");
        String words = inputReader.nextLine();

        System.out.println("");

        // Display the value and definition of the searched word/phrase
        dictionary.printDictionaryItem(words);
        System.out.println("");
    }

    /**
     * Adds a word/phrase and definition to the dictionary 
     */
    private void add(){
        System.out.println("Adding item to the dictionary ...\n");

        // Get the word/phrase/value to add
        System.out.println("Please enter a word/phrase");
        String value = inputReader.nextLine();

        // Get the definition to add
        System.out.println("Thank you, now please enter the definition");
        String def = inputReader.nextLine();

        // Insert the value and definition to the dictionary
        dictionary.insert(value, def);
        System.out.println("Thank you, your item has been added to the dictionary");
    }

    /**
     * Removes a word/phrase and definition from the dictionary
     */
    private void remove(){
        System.out.println("Removing item from the dictionary ...\n");

        // Get the word/phrase/value to remove
        System.out.println("Please enter a word/phrase to remove");
        String words = inputReader.nextLine();

        // Remove the value and its definition from the dictionary
        dictionary.remove(words);
        System.out.println("Thank you, that item has been removed if it existed");

    }

    /**
     * Prints all of the words/phrases and their definitions, in alphabetical order
     */
    private void printAll(){
        System.out.println("Printing the full dictionary ...");
        dictionary.printDictionary();
    }

    /**
     * Loads in a dictionary from a file
     */
    private void loadDictionary(){
        try {
            // Scanner to read the file
            Scanner myReader = new Scanner(dictionaryFile);

            // While there a lines in the file to read
            while (myReader.hasNextLine()) {
                // Get the line
                String dict = myReader.nextLine();
                // Split up the line
                String[] dictArray = dict.split(":");

                // If in 2 parts (value, definition)
                if (dictArray.length >= 2) {
                    // Add the value and definition into the DictionaryBST
                    dictionary.insert(dictArray[0], dictArray[1]);
                }       
            }
            // Close the reader
            myReader.close();
        } 

        // In case the file is not found
        catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }
    }
}
