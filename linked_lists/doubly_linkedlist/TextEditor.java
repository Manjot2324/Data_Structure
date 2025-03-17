import java.util.*;

class TextState {
    String text;
    TextState prev;
    TextState next;

    public TextState(String text) {
        this.text = text;
        this.prev = null;
        this.next = null;
    }
}

class TextEditor {
    TextState head;
    TextState tail;
    TextState current;
    int historyLimit;
    int historySize;

    public TextEditor(int historyLimit) {
        this.head = null;
        this.tail = null;
        this.current = null;
        this.historyLimit = historyLimit;
        this.historySize = 0;
    }

    public void addTextState(String text) {
        TextState newState = new TextState(text);

        if (historySize == historyLimit) {
            // Remove the oldest state if the history exceeds the limit
            head = head.next;
            head.prev = null;
            historySize--;
        }

        if (current == null) {
            head = tail = current = newState;
        } else {
            current.next = newState;
            newState.prev = current;
            tail = newState;
            current = newState;
        }
        historySize++;
    }

    public void undo() {
        if (current != null && current.prev != null) {
            current = current.prev;
            System.out.println("Undo: " + current.text);
        } else {
            System.out.println("No more actions to undo.");
        }
    }

    public void redo() {
        if (current != null && current.next != null) {
            current = current.next;
            System.out.println("Redo: " + current.text);
        } else {
            System.out.println("No more actions to redo.");
        }
    }

    public void displayCurrentState() {
        if (current != null) {
            System.out.println("Current text: " + current.text);
        } else {
            System.out.println("No text state available.");
        }
    }
}

public class Main {
    public static void main(String[] args) {
        TextEditor editor = new TextEditor(10);

        editor.addTextState("Hello");
        editor.addTextState("Hello World");
        editor.addTextState("Hello World!");
        editor.addTextState("Hello World!!");

        System.out.println("Current state:");
        editor.displayCurrentState();

        System.out.println("\nUndoing:");
        editor.undo();
        editor.undo();

        System.out.println("\nCurrent state after undo:");
        editor.displayCurrentState();

        System.out.println("\nRedoing:");
        editor.redo();
        editor.redo();

        System.out.println("\nCurrent state after redo:");
        editor.displayCurrentState();

        System.out.println("\nAdding more text:");
        editor.addTextState("Hello World!!!");
        editor.addTextState("Hello Universe");

        System.out.println("\nCurrent state after adding more text:");
        editor.displayCurrentState();
    }
}
