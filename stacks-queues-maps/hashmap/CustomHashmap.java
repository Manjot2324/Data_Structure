class CustomHashMap {
    class Node {
        int key, value;
        Node next;
        Node(int key, int value) {
            this.key = key;
            this.value = value;
            this.next = null;
        }
    }

    private Node[] table;
    private int capacity = 1000;

    public CustomHashMap() {
        table = new Node[capacity];
    }

    public void put(int key, int value) {
        int index = key % capacity;
        Node newNode = new Node(key, value);
        if (table[index] == null) {
            table[index] = newNode;
        } else {
            Node current = table[index];
            while (current.next != null) {
                if (current.key == key) {
                    current.value = value;
                    return;
                }
                current = current.next;
            }
            current.next = newNode;
        }
    }

    public int get(int key) {
        int index = key % capacity;
        Node current = table[index];
        while (current != null) {
            if (current.key == key) {
                return current.value;
            }
            current = current.next;
        }
        return -1;
    }

    public void remove(int key) {
        int index = key % capacity;
        Node current = table[index];
        Node prev = null;
        while (current != null) {
            if (current.key == key) {
                if (prev == null) {
                    table[index] = current.next;
                } else {
                    prev.next = current.next;
                }
                return;
            }
            prev = current;
            current = current.next;
        }
    }

    public static void main(String[] args) {
        CustomHashMap map = new CustomHashMap();
        map.put(1, 1);
        map.put(2, 2);
        System.out.println(map.get(1));
        map.remove(1);
        System.out.println(map.get(1));
    }
}
