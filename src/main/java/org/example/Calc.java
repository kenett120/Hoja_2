package org.example;

/**
 * Interface for calculator operations
 */
public interface Calc {
    /**
     * Evaluates a postfix expression
     * @param input Postfix expression as a string
     * @return Result of the evaluation
     */
    double operate(String input);
}



