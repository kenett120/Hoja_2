package org.example;

import java.util.HashMap;
import java.util.List;

/**
 * Functional interface for binary operations
 */
interface Operation {
    /**
     * Performs a binary operation
     * @param a First operand
     * @param b Second operand
     * @return Result of the operation
     */
    double operate(double a, double b);
}

/**
 * Calculator that evaluates postfix expressions using a stack
 */
public class Calculator {
    private final Stack<Token> stack;

    public Calculator(Stack<Token> stack) {
        this.stack = stack;
    }

    HashMap<String, Operation> operators = new HashMap<>() {{
        put("+", (a, b) -> {
            return a + b;
        });
        put("-", (a, b) -> {
            return a -b;
        });
        put("/", (a, b)-> {
            if (b == 0) throw new ArithmeticException("Division by zero");
            return a / b;
        });
        put("*", (a, b) -> {
            return a * b;
        });

    }};

    /**
     * Evaluates a list of tokens in postfix notation
     * @param tokens List of tokens representing a postfix expression
     * @return Result of the evaluation
     * @throws RuntimeException if expression is invalid
     */
    public double operate(List<Token> tokens) {
        for (int i = 0; i < tokens.size(); i++) {
            Token token = tokens.get(i);
            if (token.type.equals("OPERATOR")) {
                try {
                    Token first = stack.pop();
                    Token second = stack.pop();
                    double newValue = operators.get(token.value).operate(Double.parseDouble(second.value), Double.parseDouble(first.value));
                    stack.push(new Token("OPERAND", Double.toString(newValue)));
                } catch (IllegalArgumentException e) {
                    throw new RuntimeException("Falta de operandos para el operador: " + token.value);
                }
            }

            else if (token.type.equals("OPERAND")) {
                stack.push(token);
            }

        }

        Token result = stack.pop();

        try {
            stack.pop();
            throw new RuntimeException("Error: La expresión tiene operandos de más.");
        } catch (IllegalArgumentException e) {
            // Stack is empty, which is correct - we have exactly one result
        }

        return Double.parseDouble(result.value);
    }
}

