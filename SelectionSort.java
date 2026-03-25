import java.io.*;
import java.util.*;

public class SelectionSort {
    
    // The core Selection Sort logic (Your Person 1 Task)
    public static void sort(int[] arr) {
        int n = arr.length;
        for (int i = 0; i < n - 1; i++) {
            int minIdx = i;
            for (int j = i + 1; j < n; j++) {
                if (arr[j] < arr[minIdx]) minIdx = j;
            }
            int temp = arr[minIdx];
            arr[minIdx] = arr[i];
            arr[i] = temp;
        }
    }

    public static void main(String[] args) {
        // List of all 8 files Anthony provided
        String[] files = {
            "nearly_1000-2.txt", "duplicates_1000-3.txt", "random_1000-2.txt", "reverse_1000-2.txt",
            "duplicates_10000-2.txt", "random_10000-2.txt", "reverse_10000-2.txt", "nearly_10000-2.txt"
        };

        for (String fileName : files) {
            int[] data = loadFile(fileName);
            if (data == null) continue;

            long startTime = System.nanoTime();
            sort(data);
            long endTime = System.nanoTime();

            double ms = (endTime - startTime) / 1_000_000.0;
            System.out.println("File: " + fileName + " | Time: " + ms + " ms | Success: " + isSorted(data));

            // Create the output file: originalName_sorted.txt
            saveToFile(fileName, data);
        }
    }

    private static void saveToFile(String originalName, int[] sortedData) {
        try {
            String newName = originalName.replace(".txt", "_sorted.txt");
            BufferedWriter writer = new BufferedWriter(new FileWriter(newName));
            for (int val : sortedData) {
                writer.write(val + "\n");
            }
            writer.close();
        } catch (IOException e) {
            System.out.println("Error saving " + originalName);
        }
    }

    private static int[] loadFile(String fileName) {
        try {
            Scanner sc = new Scanner(new File(fileName));
            List<Integer> list = new ArrayList<>();
            while (sc.hasNextInt()) list.add(sc.nextInt());
            return list.stream().mapToInt(i -> i).toArray();
        } catch (FileNotFoundException e) {
            System.out.println("Missing file: " + fileName);
            return null;
        }
    }

    public static boolean isSorted(int[] arr) {
        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) return false;
        }
        return true;
    }
}
