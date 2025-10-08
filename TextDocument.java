package assignments.command_pattern;

import java.util.*;

interface Command {
    void execute();
    void undo();
}

class TextDocument {
    private StringBuilder text = new StringBuilder();
    private String clipboard = "";

    public TextDocument(String initialText) {
        text.append(initialText);
    }

    public void copy(String word) {
        int index = text.indexOf(word);
        if (index >= 0) clipboard = word;
        else System.out.println("Could not find: " + word);
    }

    public void cut(String word) {
        int index = text.indexOf(word);
        if (index >= 0) {
            clipboard = word;
            text.delete(index, index + word.length());
        } else System.out.println("Could not find: " + word);
    }

    public void paste() {
        text.append(" " + clipboard);
    }

    public String getText() { return text.toString(); }
    public String getClipboard() { return clipboard; }
}

class CopyCommand implements Command {
    private final TextDocument doc;
    private final String word;

    public CopyCommand(TextDocument doc, String word) {
        this.doc = doc;
        this.word = word;
    }

    public void execute() { doc.copy(word); }
    public void undo() { /* nothing to undo for copy */ }
}

class CutCommand implements Command {
    private final TextDocument doc;
    private final String word;

    public CutCommand(TextDocument doc, String word) {
        this.doc = doc;
        this.word = word;
    }

    public void execute() { doc.cut(word); }
    public void undo() { doc.paste(); } // simple reversal
}

class PasteCommand implements Command {
    private final TextDocument doc;
    public PasteCommand(TextDocument doc) { this.doc = doc; }
    public void execute() { doc.paste(); }
    public void undo() { /* remove last paste if needed */ }
}

class CommandHistory {
    private final Stack<Command> history = new Stack<>();
    public void push(Command c) { history.push(c); }
    public Command pop() { return history.isEmpty() ? null : history.pop(); }
}

public class TextEditor {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TextDocument document = new TextDocument("Hello! Welcome to my text editor program!");
        CommandHistory history = new CommandHistory();

        while (true) {
            System.out.println("\nCurrent text: " + document.getText());
            System.out.println("Clipboard: " + document.getClipboard());
            System.out.print("Enter command (copy/cut/paste/undo/exit): ");
            String command = scanner.nextLine().trim().toLowerCase();

            switch (command) {
                case "copy":
                    System.out.print("Word to copy: ");
                    Command copy = new CopyCommand(document, scanner.nextLine());
                    copy.execute();
                    history.push(copy);
                    break;
                case "cut":
                    System.out.print("Word to cut: ");
                    Command cut = new CutCommand(document, scanner.nextLine());
                    cut.execute();
                    history.push(cut);
                    break;
                case "paste":
                    Command paste = new PasteCommand(document);
                    paste.execute();
                    history.push(paste);
                    break;
                case "undo":
                    Command last = history.pop();
                    if (last != null) last.undo();
                    else System.out.println("Nothing to undo.");
                    break;
                case "exit":
                    return;
                default:
                    System.out.println("Unknown command.");
            }
        }
    }
}
