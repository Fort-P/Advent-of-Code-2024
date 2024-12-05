package Day3;

import java.io.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Challenge {
    public static void main(String[] args) {
        String filePath = new File("src\\Day3\\known.txt").getAbsolutePath();
        Pattern doPattern = Pattern.compile("do\\(\\)|don't\\(\\)");
        Pattern mulPattern = Pattern.compile("mul\\((-?\\d+),(-?\\d+)\\)");
        boolean mulEnabled = true;
        int sum = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                Matcher doMatcher = doPattern.matcher(line);
                while (doMatcher.find()) {
                    String command = doMatcher.group();
                    if (command.equals("do()")) {
                        mulEnabled = true;
                    } else if (command.equals("don't()")) {
                        mulEnabled = false;
                    }
                }

                // Now, extract mul(X,Y) only if mul is enabled
                if (mulEnabled) {
                    Matcher mulMatcher = mulPattern.matcher(line);
                    while (mulMatcher.find()) {
                        String fullMatch = mulMatcher.group(0);
                        String x = mulMatcher.group(1);
                        String y = mulMatcher.group(2);
                        // Process mul(X,Y) values
                        System.out.println("Full Match: " + fullMatch);
                        System.out.println("X: " + x + ", Y: " + y);
                    }
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }


        System.out.println(sum);
    }
}
