package com.apps.quantitymeasurement;

import java.util.function.DoubleBinaryOperator;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    // Internal enum to centralize arithmetic logic
    private enum ArithmeticOperation {
        ADD((a, b) -> a + b),
        SUBTRACT((a, b) -> a - b),
        DIVIDE((a, b) -> {
            if (b == 0.0) throw new ArithmeticException("Divide by zero");
            return a / b;
        });

        private final DoubleBinaryOperator operation;
        ArithmeticOperation(DoubleBinaryOperator operation) { this.operation = operation; }
        public double compute(double a, double b) { return operation.applyAsDouble(a, b); }
    }

    public Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value)) throw new IllegalArgumentException("Invalid input");
        this.value = value;
        this.unit = unit;
    }

    // Centralized validation logic
    private void validateArithmetic(Quantity<U> other) {
        if (other == null || this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException("Incompatible categories");
        }
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        validateArithmetic(other);
        double result = ArithmeticOperation.ADD.compute(unit.convertToBaseUnit(value), 
                                                        other.unit.convertToBaseUnit(other.value));
        return new Quantity<>(targetUnit.convertFromBaseUnit(result), targetUnit);
    }

    public double divide(Quantity<U> other) {
        validateArithmetic(other);
        return ArithmeticOperation.DIVIDE.compute(unit.convertToBaseUnit(value), 
                                                  other.unit.convertToBaseUnit(other.value));
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity<?> that = (Quantity<?>) o;
        if (this.unit.getClass() != that.unit.getClass()) return false;
        return Double.compare(this.unit.convertToBaseUnit(this.value), 
                             ((IMeasurable)that.unit).convertToBaseUnit((Double)that.value)) == 0;
    }
}
