package Day2;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Challenge {
    public static void main(String[] args) {
        List<List<Integer>> lists = new ArrayList<>();
        int safe = 0;

        String filePath = new File("src\\Day2\\challenge2-input.txt").getAbsolutePath();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.trim().split("\\s+");
                List<Integer> list = new ArrayList<>();
                for (String part : parts) {
                    list.add(Integer.parseInt(part));
                }
                lists.add(list);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        for (List<Integer> list : lists) {
            if (isSafe(list) == 1 || isSafeDampened(list) == 1) {
                safe++;
            }
        }

        System.out.println(safe);
    }

    public static int isSafe(List<Integer> list) {
        if (list.get(0) < list.get(1)) {
            return checkIncreasing(list);
        } else {
            return checkDecreasing(list);
        }
    }

    public static int isSafeDampened(List<Integer> list) {
        for (int i = 0; i < list.toArray().length; i++) {
            List<Integer> dampenedList = new ArrayList<>(list);
            dampenedList.remove(i);
            if (isSafe(dampenedList) == 1) {
                return 1;
            }
        }
        return 0;
    }

    public static int checkIncreasing(List<Integer> list) {
        for (int i = 1; i < list.toArray().length; i++) {
            if (!(list.get(i) - list.get(i - 1) <= 3) || list.get(i) - list.get(i - 1) <= 0) {
                return 0;
            }
        }
        return 1;
    }

        public static int checkDecreasing(List<Integer> list) {
            for (int i = 1; i < list.toArray().length; i++) {
                if (!(list.get(i) - list.get(i - 1) >= -3) || list.get(i) - list.get(i - 1) >= 0) {
                    return 0;
                }
            }
            return 1;
        }
    }