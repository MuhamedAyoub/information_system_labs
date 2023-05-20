import java.util.ArrayList;
import java.util.Arrays;

public class RemoveDuplicates {

    public static ArrayList<String> removeDupli(ArrayList<String> list) {
        ArrayList<String> result = new ArrayList<>();

        for (String str : list) {
            if (!result.contains(str)) {
                result.add(str);
            }
        }
        return result;
    }


	public static void main(String[] args) {
		
		long startExecussionTime = System.nanoTime();
        ArrayList<String> list = new ArrayList<>(Arrays.asList(EnglishWords.words));
        
        ArrayList<String> result = removeDupli(list);
     
		long endExecussionTime = System.nanoTime();
        long executionTime = endExecussionTime - startExecussionTime;
        System.out.println("Teacher program Execution time: " + executionTime/1000000 + " milisecond");

    }
    

}
