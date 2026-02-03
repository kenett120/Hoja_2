package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Main implements Calc {
    public static void main(String[] args) {
        Main calc = new Main();

        // Option 1: Read operations from file
        calc.processFile("datos.txt");

        // Option 2: Direct operations (uncomment to use)
        // System.out.println("1 2 + = " + calc.operate("1 2 +"));
        // System.out.println("5 3 + 2 * = " + calc.operate("5 3 + 2 *"));
        // System.out.println("10 2 - 4 / = " + calc.operate("10 2 - 4 /"));
    }

    /**
     * Reads a file and processes each line as a postfix operation
     * @param filename Path to the file containing postfix operations
     */
    public void processFile(String filename) {
        List<String> lines = readFile(filename);
        if (lines == null) {
            return;
        }

        for (int i = 0; i < lines.size(); i++) {
            String line = lines.get(i);
            if (!line.isEmpty()) {
                try {
                    double result = operate(line);
                    System.out.println("Line " + (i + 1) + ": " + line + " = " + result);
                } catch (Exception e) {
                    System.out.println("Line " + (i + 1) + ": " + line + " - Error: " + e.getMessage());
                }
            }
        }
    }

    /**
     * Reads all lines from a file
     * @param filename Path to the file to read
     * @return List of lines from the file, or null if error occurs
     */
    public List<String> readFile(String filename) {
        List<String> lines = new ArrayList<>();
        try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
            String line;
            while ((line = reader.readLine()) != null) {
                lines.add(line.trim());
            }
            return lines;
        } catch (IOException e) {
            System.out.println("Error reading file '" + filename + "': " + e.getMessage());
            return null;
        }
    }

    @Override
    public double operate(String input) {
        List<Token> tokens = new Parser().parse(input);
        return new Calculator().operate(tokens);
    }
}