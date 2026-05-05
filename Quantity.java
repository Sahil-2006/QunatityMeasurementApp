package com.apps.quantitymeasurement;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input: value must be finite and unit non-null"); //
        }
        this.value = value;
        this.unit = unit;
    }

    public double getValue() { return value; }
    public U getUnit() { return unit; }

    // UC5: Unit Conversion
    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = this.unit.convertToBaseUnit(this.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(baseValue), targetUnit);
    }

    // UC7: Addition with Target Unit
    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double sumInBase = this.unit.convertToBaseUnit(this.value) + 
                           other.unit.convertToBaseUnit(other.value); //
        return new Quantity<>(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    // UC12: Subtraction
    public Quantity<U> subtract(Quantity<U> other, U targetUnit) {
        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException("Cross-category subtraction prevented"); //
        }
        double diffInBase = this.unit.convertToBaseUnit(this.value) - 
                            other.unit.convertToBaseUnit(other.value); //
        return new Quantity<>(targetUnit.convertFromBaseUnit(diffInBase), targetUnit);
    }

    // UC12: Division (returns a dimensionless ratio)
    public double divide(Quantity<U> other) {
        if (this.unit.getClass() != other.unit.getClass()) {
            throw new IllegalArgumentException("Cross-category division prevented"); //
        }
        double divisorBase = other.unit.convertToBaseUnit(other.value);
        if (divisorBase == 0) {
            throw new ArithmeticException("Division by zero"); //
        }
        return this.unit.convertToBaseUnit(this.value) / divisorBase; //
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true; //
        if (o == null || getClass() != o.getClass()) return false; //
        Quantity<?> that = (Quantity<?>) o;
        if (this.unit.getClass() != that.unit.getClass()) return false; //
        return Double.compare(this.unit.convertToBaseUnit(this.value), 
                             ((IMeasurable)that.unit).convertToBaseUnit((Double)that.value)) == 0; //
    }

    @Override
    public String toString() { return value + " " + unit; }
}
