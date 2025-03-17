import java.util.*;

class InventoryItem {
    String itemName;
    int itemID;
    int quantity;
    double price;
    InventoryItem next;

    public InventoryItem(String itemName, int itemID, int quantity, double price) {
        this.itemName = itemName;
        this.itemID = itemID;
        this.quantity = quantity;
        this.price = price;
        this.next = null;
    }
}

class InventoryList {
    InventoryItem head;

    public InventoryList() {
        head = null;
    }

    public void addItemAtBeginning(String itemName, int itemID, int quantity, double price) {
        InventoryItem newItem = new InventoryItem(itemName, itemID, quantity, price);
        newItem.next = head;
        head = newItem;
    }

    public void addItemAtEnd(String itemName, int itemID, int quantity, double price) {
        InventoryItem newItem = new InventoryItem(itemName, itemID, quantity, price);
        if (head == null) {
            head = newItem;
            return;
        }
        InventoryItem current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newItem;
    }

    public void addItemAtPosition(String itemName, int itemID, int quantity, double price, int position) {
        InventoryItem newItem = new InventoryItem(itemName, itemID, quantity, price);
        if (position == 0) {
            newItem.next = head;
            head = newItem;
            return;
        }
        InventoryItem current = head;
        for (int i = 0; i < position - 1 && current != null; i++) {
            current = current.next;
        }
        if (current == null) return;
        newItem.next = current.next;
        current.next = newItem;
    }

    public void removeItemByItemID(int itemID) {
        if (head == null) return;
        if (head.itemID == itemID) {
            head = head.next;
            return;
        }
        InventoryItem current = head;
        while (current.next != null && current.next.itemID != itemID) {
            current = current.next;
        }
        if (current.next != null) {
            current.next = current.next.next;
        }
    }

    public void updateQuantity(int itemID, int newQuantity) {
        InventoryItem item = searchItemByItemID(itemID);
        if (item != null) {
            item.quantity = newQuantity;
        }
    }

    public InventoryItem searchItemByItemID(int itemID) {
        InventoryItem current = head;
        while (current != null) {
            if (current.itemID == itemID) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public InventoryItem searchItemByItemName(String itemName) {
        InventoryItem current = head;
        while (current != null) {
            if (current.itemName.equalsIgnoreCase(itemName)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }

    public double calculateTotalValue() {
        double totalValue = 0;
        InventoryItem current = head;
        while (current != null) {
            totalValue += current.price * current.quantity;
            current = current.next;
        }
        return totalValue;
    }

    public void sortInventoryByItemName() {
        if (head == null || head.next == null) return;
        head = mergeSortByName(head);
    }

    public void sortInventoryByPrice() {
        if (head == null || head.next == null) return;
        head = mergeSortByPrice(head);
    }

    private InventoryItem mergeSortByName(InventoryItem node) {
        if (node == null || node.next == null) return node;
        InventoryItem middle = getMiddle(node);
        InventoryItem nextOfMiddle = middle.next;
        middle.next = null;
        InventoryItem left = mergeSortByName(node);
        InventoryItem right = mergeSortByName(nextOfMiddle);
        return mergeByName(left, right);
    }

    private InventoryItem mergeByName(InventoryItem left, InventoryItem right) {
        if (left == null) return right;
        if (right == null) return left;
        if (left.itemName.compareTo(right.itemName) < 0) {
            left.next = mergeByName(left.next, right);
            left.next.next = null;
            return left;
        } else {
            right.next = mergeByName(left, right.next);
            right.next.next = null;
            return right;
        }
    }

    private InventoryItem mergeSortByPrice(InventoryItem node) {
        if (node == null || node.next == null) return node;
        InventoryItem middle = getMiddle(node);
        InventoryItem nextOfMiddle = middle.next;
        middle.next = null;
        InventoryItem left = mergeSortByPrice(node);
        InventoryItem right = mergeSortByPrice(nextOfMiddle);
        return mergeByPrice(left, right);
    }

    private InventoryItem mergeByPrice(InventoryItem left, InventoryItem right) {
        if (left == null) return right;
        if (right == null) return left;
        if (left.price < right.price) {
            left.next = mergeByPrice(left.next, right);
            left.next.next = null;
            return left;
        } else {
            right.next = mergeByPrice(left, right.next);
            right.next.next = null;
            return right;
        }
    }

    private InventoryItem getMiddle(InventoryItem node) {
        if (node == null) return node;
        InventoryItem slow = node;
        InventoryItem fast = node;
        while (fast != null && fast.next != null) {
            fast = fast.next.next;
            slow = slow.next;
        }
        return slow;
    }

    public void displayAllItems() {
        InventoryItem current = head;
        if (current == null) {
            System.out.println("Inventory is empty.");
            return;
        }
        while (current != null) {
            System.out.println("Item Name: " + current.itemName + ", Item ID: " + current.itemID + ", Quantity: " + current.quantity + ", Price: " + current.price);
            current = current.next;
        }
    }
}

public class Main {
    public static void main(String[] args) {
        InventoryList inventoryList = new InventoryList();

        // Adding items to the inventory
        inventoryList.addItemAtBeginning("Apple", 101, 50, 1.2);
        inventoryList.addItemAtEnd("Banana", 102, 30, 0.8);
        inventoryList.addItemAtEnd("Orange", 103, 40, 1.5);
        inventoryList.addItemAtEnd("Grapes", 104, 20, 2.5);
        inventoryList.addItemAtPosition("Mango", 105, 25, 1.8, 2);

        // Displaying all items
        System.out.println("Inventory before any changes:");
        inventoryList.displayAllItems();

        // Removing an item by item ID
        inventoryList.removeItemByItemID(102);

        // Updating the quantity of an item
        inventoryList.updateQuantity(103, 60);

        // Searching for an item by item ID and item name
        System.out.println("\nSearching for Item with ID 103:");
        InventoryItem item = inventoryList.searchItemByItemID(103);
        if (item != null) {
            System.out.println("Item Found: " + item.itemName);
        } else {
            System.out.println("Item not found.");
        }

        System.out.println("\nSearching for Item with Name 'Apple':");
        item = inventoryList.searchItemByItemName("Apple");
        if (item != null) {
            System.out.println("Item Found: " + item.itemName);
        } else {
            System.out.println("Item not found.");
        }

        // Calculating total inventory value
        double totalValue = inventoryList.calculateTotalValue();
        System.out.println("\nTotal Inventory Value: " + totalValue);

        // Sorting inventory by item name
        inventoryList.sortInventoryByItemName();
        System.out.println("\nInventory after sorting by Item Name:");
        inventoryList.displayAllItems();

        // Sorting inventory by price
        inventoryList.sortInventoryByPrice();
        System.out.println("\nInventory after sorting by Price:");
        inventoryList.displayAllItems();
    }
}
