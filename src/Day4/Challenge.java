package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Challenge {
    public static void main(String[] args) {
        String filePath = new File("src\\Day4\\input.txt").getAbsolutePath();
        char[][] grid = readGridFromFile(filePath);
        String word = "XMAS";
        int count = countOccurrences(grid, word);
        System.out.println("Total occurrences of '" + word + "': " + count);
    }

    // Reads the grid from a file and returns a 2D char array
    public static char[][] readGridFromFile(String filePath) {
        List<String> lines = new ArrayList<>();

        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            System.err.println("Error reading the file: " + e.getMessage());
        }

        // Convert the list of lines to a 2D char array
        char[][] grid = new char[lines.size()][];
        for (int i = 0; i < lines.size(); i++) {
            grid[i] = lines.get(i).toCharArray();  // Convert each line to a char array
        }
        return grid;
    }

    // Counts how many times the word occurs in the grid
    public static int countOccurrences(char[][] grid, String word) {
        int rows = grid.length;
        int cols = grid[0].length;
        int wordLength = word.length();
        int count = 0;

        // Directions for the 8 possible movements
        int[][] directions = {
                {0, 1},  // Right
                {0, -1}, // Left
                {1, 0},  // Down
                {-1, 0}, // Up
                {1, 1},  // Diagonal Down-Right
                {-1, -1}, // Diagonal Up-Left
                {1, -1},  // Diagonal Down-Left
                {-1, 1}   // Diagonal Up-Right
        };

        // Iterate over each cell in the grid
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                // Check each direction
                for (int[] dir : directions) {
                    if (isWordInDirection(grid, r, c, dir[0], dir[1], word)) {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // Checks if the word exists in a specific direction
    public static boolean isWordInDirection(char[][] grid, int startRow, int startCol, int dRow, int dCol, String word) {
        int wordLength = word.length();
        int rows = grid.length;
        int cols = grid[0].length;

        // Check each character in the word
        for (int i = 0; i < wordLength; i++) {
            int newRow = startRow + i * dRow;
            int newCol = startCol + i * dCol;

            // If out of bounds or character doesn't match, return false
            if (newRow < 0 || newRow >= rows || newCol < 0 || newCol >= cols || grid[newRow][newCol] != word.charAt(i)) {
                return false;
            }
        }
        return true;
    }
}
