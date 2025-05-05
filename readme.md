# ISEP Calculator

A Java calculator that evaluates mathematical expressions with `+`, `-`, `*`, unary minus, and parentheses. Developed using Test-Driven Development (TDD) and Clean Code practices.

## Prerequisites

* Java JDK 8 or higher
* Maven (or any IDE with Maven support)

## Build & Test

```bash
git clone https://github.com/MstrDzn/isep-calculator.git
cd isep-calculator
mvn clean test
```

## Usage

```java
import org.isep.cleancode.calculator.Calculator;

Calculator calc = new Calculator();
System.out.println(calc.evaluateMathExpression("2*(3+4)- -5")); // 19.0
```

## Project Structure

```
src/
├─ main/java/org/isep/cleancode/calculator/Calculator.java
└─ test/java/org/isep/cleancode/calculator/CalculatorTest.java
```

## Clean Code & TDD

* Step-by-step implementation with unit tests (Red–Green–Refactor)
* Small, focused methods with clear names
* Early returns to minimize nesting
