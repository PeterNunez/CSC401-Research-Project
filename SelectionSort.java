//Pedro Nunez

import java.io.*;
import java.util.*;

public class SelectionSort {
    
    //This is the core Selection Sort logic 
    public static void sort(int[] arr) {
        int n = arr.length;
        
        //Step 1: This for loop will go through the array one index at a time.
        //The i in this loop is the index we are trying to fill with the correct number.
        for (int i = 0; i < n - 1; i++) {
        	
        	//Step 2: With minIdx we will assume the current i is the smallest current number.
            int minIdx = i;
            
            //Step 3: With this nested for loop we then check the next spot with i + 1.
            //We do this so that we can see if a smaller number exist in the whole array..
            for (int j = i + 1; j < n; j++) {
            	
            	//Step 4: In the case that there is a smaller number then we have to update 
            	//our minIdx so that it points to the new, smaller number.
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            
            //Step 5: We pick up the smallest number which is held by minIdx and put it into the location of i..
            int temp = arr[minIdx];
            
            //Step 6: We then move the value that is currently at i out of the way.
            arr[minIdx] = arr[i];
            
            //Step 7: We put the smallest value into the location of i.
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
    	
        //Step 1: All eight data files are here and have to be on the same folder as this class in order to run.
        String[] files = {
            "nearly_1000-2.txt", "duplicates_1000-3.txt", "random_1000-2.txt", "reverse_1000-2.txt",
            "duplicates_10000-2.txt", "random_10000-2.txt", "reverse_10000-2.txt", "nearly_10000-2.txt"
        };

        //Step 2: This makes sure that it loops through each file as required.
        for (String fileName : files) {
            int[] data = loadFile(fileName);
            
            //Step 2.5: If by any chance the file is missing then it would skip it and go to the next one.
            if (data == null) continue;

            //Step 3: We then start the time by using system.nanoTime() in order to get a more precise result.
            long startTime = System.nanoTime();
            
            //Step 4: We then call the sorting logic.
            sort(data);
            
            //Step 5: We stop the timer here and convert our nanoseconds into milliseconds 
            long endTime = System.nanoTime();
            double ms = (endTime - startTime) / 1_000_000.0;
            
            //Step 6: This will be the output.
            //It should show all files and how much time it took for it to be sorted. 
            //I also added a verify function that would print success if it was properly sorted.  
            System.out.println("File: " + fileName + " | Time: " + ms + " ms | Success: " + isSorted(data));

            //Step 7: This makes sure to save our sorted data into a new file (e.g., random_1000-2_sorted.txt).
            saveToFile(fileName, data);
        }
    }

    //This method ensures that a new file is created with it's sorted result.
    private static void saveToFile(String originalName, int[] sortedData) {
        try {
            String newName = originalName.replace(".txt", "_sorted.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(newName));
            for (int val : sortedData) {
                writer.write(val + "\n");
            }
            writer.close();
            
          //An error message will be displayed if the file was able to be unsuccessfully save.
        } catch (IOException e) {
            System.out.println("Error saving " + originalName);
        }
    }

    //This methods reads the text file and should convert the numbers into an integer array
    private static int[] loadFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) list.add(sc.nextInt());
            return list.stream().mapToInt(i -> i).toArray();
            
          //If no file is found then an error message will be displayed.
        } catch (FileNotFoundException e) {
            System.out.println("Missing file: " + fileName);
            return null;
        }
    }

    
    //This Method goes through the array one last time to check if the array is all sorted.
    public static boolean isSorted(int[] arr) {
    	
    	//Step 1: In this for loop we would go from the start of the array all the way to the second last item.
        for (int i = 0; i < arr.length - 1; i++) {
        	
        	//Step 2: We compare the current number with the next number to see if the current number is bigger or smaller than the next number.
            if (arr[i] > arr[i + 1]) return false;
        }
        //Step 3: If everything is sorted then it would return true indicating the array has successfully sorted.
        return true;
    }
}
