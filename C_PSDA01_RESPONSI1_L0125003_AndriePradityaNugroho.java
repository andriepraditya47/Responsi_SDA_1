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
            System.out.print(root.name + " ");
            printlist(root.right);
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

    System.out.println("input kode (ADD, DELETE, UPDATE) dan nama:");
    String input = userInput.nextLine();
    String[] parts = input.split(" ", 2);
    
    switch (parts[0]){
      case "ADD":
        break;
      case "DELETE":
        break;
      case "UPDATE":
        break;
    }
  }
}
