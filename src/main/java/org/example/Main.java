package org.example;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import java.util.Scanner;

public class Main implements Calc {
    private Stack<Token> stack;

    public Main() {
        // Default to StackArrayList
        this.stack = new StackArrayList<>();
    }

    public void setStackImplementation(Stack<Token> stack) {
        this.stack = stack;
    }

    public static void main(String[] args) {
        Main calc = new Main();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Seleccione la implementación de pila:");
        System.out.println("1. ArrayList");
        System.out.println("2. Vector (Array)");
        System.out.print("Opción: ");

        int option = 0;
        try {
             String input = scanner.nextLine();
             option = Integer.parseInt(input);
        } catch (NumberFormatException e) {
            System.out.println("Opción inválida, usando ArrayList por defecto.");
        }

        if (option == 2) {
            calc.setStackImplementation(new StackArray<>());
            System.out.println("Usando StackArray.");
        } else {
            System.out.println("Usando StackArrayList.");
        }

        // Option 1: Read operations from file
        calc.processFile("datos.txt");
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
        // Create a new instance of the stack for each operation to avoid state pollution
        // We need to create a new instance of the same type as this.stack
        Stack<Token> currentOperationStack;
        if (this.stack instanceof StackArray) {
            currentOperationStack = new StackArray<>();
        } else {
            currentOperationStack = new StackArrayList<>();
        }
        
        return new Calculator(currentOperationStack).operate(tokens);
    }
}