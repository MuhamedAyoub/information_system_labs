import java.util.*;

public class BasicMethods {

    // Method to calculate the sum of even numbers in an ArrayList
    public static int sumOfEvenNumbers(ArrayList<Integer> numbers) {
        int sum = 0;
        for (int num : numbers) {
            if (num % 2 == 0) {
                sum += num;
            }
        }
        return sum;
    }

    // Method to remove strings of length less than 5 from a LinkedList
    public static LinkedList<String> removeShortStrings(LinkedList<String> strings) {
        LinkedList<String> result = new LinkedList<>();
        for (String str : strings) {
            if (str.length() >= 5) {
                result.add(str);
            }
        }
        return result;
    }

    // Method to find the common elements between two sets
    public static Set<Integer> findCommonElements(Set<Integer> set1, Set<Integer> set2) {
        Set<Integer> commonElements = new HashSet<>();
        for (int num : set1) {
            if (set2.contains(num)) {
                commonElements.add(num);
            }
        }
        return commonElements;
    }

    // Method to count the occurrences of integers in an array
    public static Map<Integer, Integer> countIntegerOccurrences(int[] numbers) {
        Map<Integer, Integer> occurrences = new HashMap<>();
        for (int num : numbers) {
            occurrences.put(num, occurrences.getOrDefault(num, 0) + 1);
        }
        return occurrences;
    }

    public static void main(String[] args) {
        // Testing the methods with example inputs
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 2, 3, 4, 5, 6));
        System.out.println("Sum of even numbers: " + sumOfEvenNumbers(numbersList));

        LinkedList<String> stringsList = new LinkedList<>(Arrays.asList("Hello", "there", "world", "!", "Java"));
        System.out.println("Strings with length >= 5: " + removeShortStrings(stringsList));

        Set<Integer> set1 = new HashSet<>(Arrays.asList(1, 2, 3, 4, 5));
        Set<Integer> set2 = new HashSet<>(Arrays.asList(4, 5, 6, 7, 8));
        System.out.println("Common elements: " + findCommonElements(set1, set2));

        int[] numbersArray = {1, 2, 3, 4, 5, 2, 3, 4, 5, 5};
        System.out.println("Integer occurrences: " + countIntegerOccurrences(numbersArray));
    }
}
