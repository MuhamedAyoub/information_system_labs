import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Advanced.Dictionary dictionary = new Advanced.Dictionary();
        // Populate the dictionary with words and definitions
        dictionary.addWord("apple", "A fruit with a crisp and juicy flesh.");
        dictionary.addWord("banana", "An elongated curved fruit with a yellow or green skin.");

        Advanced.SpellChecker spellChecker = new Advanced.SpellChecker(dictionary);

        Scanner scanner = new Scanner(System.in);
        String word;

        do {
            System.out.print("Enter a word (or 'quit' to exit): ");
            word = scanner.nextLine();

            if (word.equalsIgnoreCase("quit")) {
                break;
            }

            if (spellChecker.isWordSpelledCorrectly(word)) {
                System.out.println("Correct spelling!");
                String definition = spellChecker.getDefinition(word);
                System.out.println("Definition: " + definition);
            } else {
                System.out.println("Incorrect spelling!");

                String closestMatch = spellChecker.getClosestMatch(word);
                if (closestMatch != null) {
                    System.out.println("Did you mean '" + closestMatch + "'?");
                }
            }
        } while (true);

        scanner.close();
    }
}
