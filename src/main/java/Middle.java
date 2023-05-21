import java.util.*;

public class Middle {
    // Grouping 7 TreeMap
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
/*  countWords Question 3 */
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
    /*  Sort Map Question 3 */


    public static Map<Integer, String> sortMapValues(Map<Integer, String> inputMap) {
        Map<Integer, String> sortedMap = new LinkedHashMap<>();

        List<Map.Entry<Integer, String>> entryList = new ArrayList<>(inputMap.entrySet());
        entryList.sort(Map.Entry.comparingByValue());

        for (Map.Entry<Integer, String> entry : entryList) {
            sortedMap.put(entry.getKey(), entry.getValue());
        }

        return sortedMap;
    }
    // getTopWords 5
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


// 2 Reverse Linked List
        LinkedList<String> stringList = new LinkedList<>();
        // "apple", "banana", "carrot", "cat", "dog", "elephant", "fish"
        stringList.addFirst("apple");
        stringList.addFirst("banana");
        stringList.addFirst("carrot");
        stringList.addFirst("cat");
        stringList.addFirst("dog");
        stringList.addFirst("elephant");
        stringList.addFirst("fish");
        Collections.sort(stringList);
        System.out.println(stringList);
        Collections.reverse(stringList);
        System.out.println(stringList);


        // -------> 3
        ArrayList<String> list = new ArrayList<>(List.of(EnglishWords.words));
        Set<String> result = new HashSet<>(RemoveDuplicates.removeDupli(list));
        String[] words = result.toArray(new String[0]);
        Map<String, Integer> wordCount = countWords(words);
        for (Map.Entry<String, Integer> entry : wordCount.entrySet()) {
            System.out.println(entry.getKey() + " -->  " + entry.getValue());
        }

//----------> sortMapValues 4

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
/// -----> Top words 5
        String text2 = "Le Lorem Ipsum est simplement du faux texte employé dans la composition et la mise en page avant impression. Le Lorem Ipsum est le faux texte standard de l'imprimerie depuis les années 1500, quand un imprimeur anonyme assembla ensemble des morceaux de texte pour réaliser un livre spécimen de polices de texte. Il n'a pas fait que survivre cinq siècles, mais s'est aussi adapté à la bureautique informatique, sans que son contenu n'en soit modifié. Il a été popularisé dans les années 1960 grâce à la vente de feuilles Letraset contenant des passages du Lorem Ipsum, et, plus récemment, par son inclusion dans des applications de mise en page de texte, comme Aldus PageMaker.";
        String text = "This is a sample text. It contains multiple words, some of which may repeat. The goal is to find the most frequently occurring words.";
        int n = 3;
        List<String> topWords = getTopWords(text, n);
        System.out.println("Top " + n + " words:");
        for (String word : topWords) {
            System.out.println(word);
        }
// -----> End Top words

// Re arrange 6
        ArrayList<Integer> numbers = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6, 7, 8, 9, 10));
        ArrayList<Integer> rearrangedNumbers = rearrangeNumbers(numbers);
        System.out.println("Rearranged Numbers:");
        for (Integer number : rearrangedNumbers) {
            System.out.println(number);
        }
// Rearrange End
  // Grouping 7
        Set<String> strings = new HashSet<>(Arrays.asList("apple", "banana", "carrot", "cat", "dog", "elephant", "fish","watermelon","onion","car","bus"));
        Map<Character, List<String>> groupedStrings = groupStrings(strings);

        for (Map.Entry<Character, List<String>> entry : groupedStrings.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }

  // Grouping end

        long endExecutionTime = System.nanoTime();
        long executionTime = endExecutionTime - startExecutionTime;
        System.out.println("Program Execution Time: " + executionTime / 1000000 + " milisecond");
    }
}


