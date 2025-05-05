package org.isep.cleancode.calculator;

import java.util.ArrayList;
import java.util.List;

public class Calculator {
    /**
     * Evaluates a mathematical expression containing +, -, *, parentheses, and unary minus.
     * @param expression input string representing the math expression
     * @return computed result as double
     */
    public double evaluateMathExpression(String expression) {
        // Remove all whitespace
        String expr = expression.replace(" ", "");

        // 1) Handle surrounding parentheses if they enclose the entire expression
        if (expr.startsWith("(")) {
            int depth = 0;
            for (int i = 0; i < expr.length(); i++) {
                if (expr.charAt(i) == '(') depth++;
                else if (expr.charAt(i) == ')') depth--;

                // If depth returns to zero before the end, not a full enclosure
                if (depth == 0) {
                    if (i == expr.length() - 1) {
                        // Strip outer parentheses and recurse
                        return evaluateMathExpression(expr.substring(1, expr.length() - 1));
                    }
                    break;
                }
            }
        }

        // 2) Handle unary minus
        if (expr.startsWith("-")) {
            return -evaluateMathExpression(expr.substring(1));
        }

        // 3) Top-level addition
        List<String> addParts = splitTopLevel(expr, '+');
        if (addParts.size() > 1) {
            double sum = 0;
            for (String part : addParts) {
                sum += evaluateMathExpression(part);
            }
            return sum;
        }

        // 4) Top-level subtraction
        List<String> subParts = splitTopLevel(expr, '-');
        if (subParts.size() > 1) {
            double result = evaluateMathExpression(subParts.get(0));
            for (int i = 1; i < subParts.size(); i++) {
                result -= evaluateMathExpression(subParts.get(i));
            }
            return result;
        }

        // 5) Top-level multiplication
        List<String> mulParts = splitTopLevel(expr, '*');
        if (mulParts.size() > 1) {
            double product = 1;
            for (String part : mulParts) {
                product *= evaluateMathExpression(part);
            }
            return product;
        }

        // 6) Parse simple number (integer or floating-point)
        return Double.parseDouble(expr);
    }

    /**
     * Splits the expression at the given operator only at the top parenthesis level.
     * @param expr      cleaned expression
     * @param op        operator character to split on
     * @return list of substrings between top-level operators
     */
    private List<String> splitTopLevel(String expr, char op) {
        List<String> parts = new ArrayList<>();
        int depth = 0, last = 0;
        for (int i = 0; i < expr.length(); i++) {
            char c = expr.charAt(i);
            if (c == '(')      depth++;
            else if (c == ')') depth--;
            else if (c == op && depth == 0) {
                parts.add(expr.substring(last, i));
                last = i + 1;
            }
        }
        parts.add(expr.substring(last));
        return parts;
    }
}
