import java.util.*;

class Node {
    String name;
    Node left, right;

    public Node(String item) {
        name = item;
        left = right = null;
    }
}

class BinarySearchTree {
    Node root;

    BinarySearchTree() {
        root = null;
    }

    // INSERT
    Node insert(Node root, String name) {
        if (root == null) {
            root = new Node(name);
            return root;
        }

        if (name.compareTo(root.name) < 0) // Comparing the value of the strings by each char
            root.left = insert(root.left, name);
        else
            root.right = insert(root.right, name);

        return root;
    }
    
    // SEARCH
    Node search(Node root, String name) {
        if (root == null || name.equals(root.name))
            return root;

        if (name.compareTo(root.name) < 0)
            return search(root.left, name);

        return search(root.right, name);
    }

    // Searching the node with the lowest value
    Node minValueNode(Node node) {
        Node current = node;
        while (current.left != null)
            current = current.left;
        return current;
    }
    // Print all node in-order
    void printlist(Node root) {
        if (root != null) {
            printlist(root.left);
            System.out.println(root.name);
            printlist(root.right);
        }
    }
    
    // Print all node in-order with prefix
    void matchinglist(Node root, String keyword) {
      if (root != null) {
          matchinglist(root.left, keyword);
          if (root.name.toLowerCase().contains(keyword.toLowerCase())){
            System.out.println("- " + root.name);
          }
          matchinglist(root.right, keyword);
        }
    }

    // DELETE
    Node deleteNode(Node root, String name) {
        if (root == null)
            return root;

        if (name.compareTo(root.name) < 0)
            root.left = deleteNode(root.left, name);
        else if (name.compareTo(root.name) > 0)
            root.right = deleteNode(root.right, name);
        else {
            if (root.left == null)
                return root.right;
            else if (root.right == null)
                return root.left;

            Node temp = minValueNode(root.right);
            root.name = temp.name;
            root.right = deleteNode(root.right, temp.name);
        }

        return root;
    }
}


public class C_PSDA01_RESPONSI1_L0125003_AndriePradityaNugroho {
  public static void main(String[] args){
    Scanner userInput = new Scanner(System.in);
    HashMap<String, String> map = new HashMap<>();
    BinarySearchTree bst = new BinarySearchTree();
   
    System.out.println("####### CONTACTS BOOK #######");
    
    while(true){
    System.out.println("\nSaved contacts:");
    bst.printlist(bst.root);

      System.out.println("\ninput command and name (use HELP to list all commands):");
      String input = userInput.nextLine();
      
      if (input.startsWith("EXIT")){
        return;
      }
      else if (input.startsWith("HELP")){
          System.out.println("ADD: Input a new contact if not exist yet.");
          System.out.println("DELETE: Delete an existing contact with given name.");
          System.out.println("UPDATE: Update the phone number of specified contact.");
          System.out.println("GET: Print the contact's name with the phone number");
          System.out.println("SEARCH: Print a list of contact's name with the specified keyword.");
          System.out.println("EXIT: Quit the program.");
      }

      else{
        String[] parts = input.split(" ", 2);
        
        if (parts.length < 2){
        System.out.println("Please input contact's name.\n");
        continue;
      }
        Node contactExists = bst.search(bst.root, parts[1]);
      
        switch (parts[0]){
          case "ADD":
            if (contactExists != null){
              System.out.println("Contact already exists.");
              break;
            }
            System.out.println("Input phone numbers:");
            String phone = userInput.nextLine();
            bst.root = bst.insert(bst.root, parts[1]);
            map.put(parts[1], phone);
            break;

          case "DELETE":
            if (contactExists == null){
            System.out.println("Contact does not exist.");
            break;
            }
            map.remove(parts[1]);
            bst.root = bst.deleteNode(bst.root, parts[1]);
            System.out.println("Contact successfully deleted.");
          break;

          case "UPDATE":
            if (contactExists == null){
            System.out.println("Contact does not exist.");
            break;
          }
            System.out.println("Input phone number:");
            String newPhone = userInput.nextLine();
            map.put(parts[1], newPhone);
            break;

          case "GET":
            if(contactExists == null){
            System.out.println("Contact does not exist.");
            break;
            }
            System.out.println("\nName: " + parts[1] + "\nPhone number: " + map.get(parts[1]));
            break;
          case "SEARCH":
            System.out.println("\nSearch results: ");
            bst.matchinglist(bst.root,parts[1]);
            break;
          default:
            System.out.println("Please input valid command.");
            break;
        }
      }
      
    }
  }
}
