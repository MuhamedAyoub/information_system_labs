import java.util.*;
public class Advanced {

    public static class SpellChecker {
        private int calculateLevenshteinDistance(String word1, String word2) {
            int[][] dp = new int[word1.length() + 1][word2.length() + 1];

            for (int i = 0; i <= word1.length(); i++) {
                dp[i][0] = i;
            }

            for (int j = 0; j <= word2.length(); j++) {
                dp[0][j] = j;
            }

            for (int i = 1; i <= word1.length(); i++) {
                for (int j = 1; j <= word2.length(); j++) {
                    if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        int replaceCost = dp[i - 1][j - 1] + 1;
                        int deleteCost = dp[i - 1][j] + 1;
                        int insertCost = dp[i][j - 1] + 1;

                        dp[i][j] = Math.min(replaceCost, Math.min(deleteCost, insertCost));
                    }
                }
            }

            return dp[word1.length()][word2.length()];
        }
        private Dictionary dictionary;
        public String getClosestMatch(String word) {
            String closestMatch = null;
            int smallestDistance = Integer.MAX_VALUE;

            for (String dictWord : dictionary.getWords()) {
                int distance = calculateLevenshteinDistance(word, dictWord);

                if (distance < smallestDistance) {
                    smallestDistance = distance;
                    closestMatch = dictWord;
                }
            }

            return closestMatch;
        }

        public SpellChecker(Dictionary dictionary) {
            this.dictionary = dictionary;
        }

        public boolean isWordSpelledCorrectly(String word) {
            return dictionary.isWordSpelledCorrectly(word);
        }

        public String getDefinition(String word) {
            return dictionary.getDefinition(word);
        }
    }

    public static class Dictionary {
        private Map<String, String> wordDefinitions;

        public Dictionary() {
            wordDefinitions = new HashMap<>();
        }

        public void addWord(String word, String definition) {
            wordDefinitions.put(word.toLowerCase(), definition);
        }

        public boolean isWordSpelledCorrectly(String word) {
            return wordDefinitions.containsKey(word.toLowerCase());
        }

        public String getDefinition(String word) {
            return wordDefinitions.get(word.toLowerCase());
        }
        public Set<String> getWords() {
            return wordDefinitions.keySet();
        }
    }
    public void main(String[] args) {
        Dictionary dictionary = new Dictionary();
        // Populate the dictionary with words and definitions
        dictionary.addWord("apple", "A fruit with a crisp and juicy flesh.");
        dictionary.addWord("banana", "An elongated curved fruit with a yellow or green skin.");

        SpellChecker spellChecker = new SpellChecker(dictionary);

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
            }
        } while (true);

        scanner.close();
    }

}
