package Day1;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Challenge1 {
    public static void main(String[] args) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        int totalDistance = 0;
        int similarityScore = 0;

        String filePath = new File("src\\Day1\\Challenge1-list.txt").getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                list1.add(Integer.parseInt(parts[0]));
                list2.add(Integer.parseInt(parts[1]));
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        Collections.sort(list1);
        Collections.sort(list2);

        for (int i = 0; i < list1.toArray().length; i++) {
            totalDistance += Math.abs(list1.get(i) - list2.get(i));
            similarityScore += list1.get(i) * Collections.frequency(list2, list1.get(i));
        }

        System.out.println(totalDistance);
        System.out.println(similarityScore);
    }
}
