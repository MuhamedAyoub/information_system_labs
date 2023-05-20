import java.util.*;

public class Middle {
    public static Map<Character, List<String>> groupStrings(Set<String> strings) {
        Map<Character, List<String>> resultMap = new TreeMap<>();

        for (String str : strings) {
            char firstChar = str.charAt(0);
            List<String> charList = resultMap.getOrDefault(firstChar, new ArrayList<>());
            charList.add(str);
            resultMap.put(firstChar, charList);
        }

        return resultMap;
    }
    public static ArrayList<Integer> rearrangeNumbers(ArrayList<Integer> numbers) {
        LinkedList<Integer> evenList = new LinkedList<>();
        LinkedList<Integer> oddList = new LinkedList<>();

        for (Integer number : numbers) {
            if (number % 2 == 0) {
                evenList.add(number);
            } else {
                oddList.add(number);
            }
        }

        ArrayList<Integer> rearrangedNumbers = new ArrayList<>();
        rearrangedNumbers.addAll(evenList);
        rearrangedNumbers.addAll(oddList);

        return rearrangedNumbers;
    }

    public static Map<String, Integer> countWords(String[] words) {
        Map<String, Integer> wordCount = new HashMap<>();
        for (String word : words) {
            word = word.toLowerCase().replaceAll("[^a-zA-Z ]", "");
            if (!word.isEmpty()) {
                wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
            }
        }
        return wordCount;
    }
    public static Map<Integer, String> sortMapValues(Map<Integer, String> inputMap) {
        Map<Integer, String> sortedMap = new LinkedHashMap<>();

        List<Map.Entry<Integer, String>> entryList = new ArrayList<>(inputMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        for (Map.Entry<Integer, String> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    public static List<String> getTopWords(String text, int n) {

        Map<String, Integer> wordCount = new HashMap<>();
        PriorityQueue<Map.Entry<String, Integer>> pq = new PriorityQueue<>(
                (a, b) -> a.getValue().equals(b.getValue()) ? a.getKey().compareTo(b.getKey()) : b.getValue() - a.getValue()
        );

        String[] words = text.toLowerCase().split("[^a-zA-Z]+");

        for (String word : words) {
            wordCount.put(word, wordCount.getOrDefault(word, 0) + 1);
        }

        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            pq.offer(entry);
        }

        List<String> topWords = new ArrayList<>();
        while (n > 0 && !pq.isEmpty()) {
            topWords.add(pq.poll().getKey());
            n--;
        }

        return topWords;
    }

    public static void main(String[] args) {
        long startExecutionTime = System.nanoTime();
        Set<String> strings = new HashSet<>(Arrays.asList("apple", "banana", "carrot", "cat", "dog", "elephant", "fish"));
        Map<Character, List<String>> groupedStrings = groupStrings(strings);

        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ArrayList<Integer> rearrangedNumbers = rearrangeNumbers(numbers);

// ------->
        ArrayList<String> list = new ArrayList<>(List.of(EnglishWords.words));
        ArrayList<String> result = RemoveDuplicates.removeDupli(list);
        String[] words = result.toArray(new String[0]);
        Map<String, Integer> wordCount = countWords(words);
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

//----------> 4

        Map<Integer, String> inputMap = new HashMap<>();
        inputMap.put(1, "Apple");
        inputMap.put(2, "Banana");
        inputMap.put(3, "Orange");
        inputMap.put(4, "Mango");
        inputMap.put(5, "Pineapple");
        Map<Integer, String> sortedMap = sortMapValues(inputMap);
        System.out.println("Sorted Map:");
        for (Map.Entry<Integer, String> entry : sortedMap.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

        //
/// -----> Top words
        String text = "This is a sample text. It contains multiple words, some of which may repeat. The goal is to find the most frequently occurring words.";
        int n = 3;
        List<String> topWords = getTopWords(text, n);
        System.out.println("Top " + n + " words:");
        for (String word : topWords) {
            System.out.println(word);
        }
// -----> End Top words

// Re arrange
        System.out.println("Rearranged Numbers:");
        for (Integer number : rearrangedNumbers) {
            System.out.println(number);
        }
// Rearrange End
  // Grouping
        for (Map.Entry<Character, List<String>> entry : groupedStrings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

  // Grouping end

        long endExecutionTime = System.nanoTime();
        long executionTime = endExecutionTime - startExecutionTime;
        System.out.println("Program Execution Time: " + executionTime / 1000000 + " milisecond");
    }
}


