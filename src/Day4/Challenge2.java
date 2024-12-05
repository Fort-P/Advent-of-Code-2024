package Day4;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Challenge2 {
    public static void main(String[] args) {
        String filePath = new File("src\\Day4\\input.txt").getAbsolutePath();
        char[][] grid = readGridFromFile(filePath);
        int count = countXMASOccurrences(grid);
        System.out.println("Total occurrences of 'X-MAS': " + count);
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

    // Counts how many X-MAS occurrences there are in the grid
    public static int countXMASOccurrences(char[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int count = 0;

        // Iterate over each cell in the grid
        for (int r = 1; r < rows - 1; r++) {
            for (int c = 1; c < cols - 1; c++) {
                // We are looking for the center 'A' of the X-MAS
                if (grid[r][c] == 'A') {
                    // Check all 4 possible patterns
                    if (checkPattern(grid, r, c, 1, 1) || // Pattern 1 (M.S / .A. / M.S)
                            checkPattern(grid, r, c, 1, -1) || // Pattern 2 (M.M / .A. / S.S)
                            checkPattern(grid, r, c, -1, -1) || // Pattern 3 (S.S / .A. / M.M)
                            checkPattern(grid, r, c, -1, 1))   // Pattern 4 (S.M / .A. / S.M)
                    {
                        count++;
                    }
                }
            }
        }
        return count;
    }

    // Checks if the 'MAS' or 'SAM' pattern exists in the given diagonal direction (dRow, dCol)
    public static boolean checkPattern(char[][] grid, int r, int c, int dRow, int dCol) {
        int rows = grid.length;
        int cols = grid[0].length;

        // Check for 'M . S' and 'S . M' in the diagonal
        char topLeft = grid[r + dRow][c + dCol];  // First 'M' or 'S'
        char topRight = grid[r - dRow][c + dCol]; // Second 'M' or 'S'
        char bottomLeft = grid[r + dRow][c - dCol];  // 'M' or 'S' below the 'A'
        char bottomRight = grid[r - dRow][c - dCol]; // 'M' or 'S' to the right of 'A'

        // Ensure the pattern matches "MAS" or "SAM"
        return (topLeft == 'M' && topRight == 'S' && bottomLeft == 'M' && bottomRight == 'S') ||
                (topLeft == 'S' && topRight == 'M' && bottomLeft == 'S' && bottomRight == 'M');
    }
}
