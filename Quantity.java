package com.apps.quantitymeasurement;

public class Quantity<U extends IMeasurable> {
    private final double value;
    private final U unit;

    public Quantity(double value, U unit) {
        if (unit == null || !Double.isFinite(value))
            throw new IllegalArgumentException("Invalid input");
        this.value = value;
        this.unit = unit;
    }

    public Quantity<U> convertTo(U targetUnit) {
        double baseValue = this.unit.convertToBaseUnit(this.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(baseValue), targetUnit);
    }

    public Quantity<U> add(Quantity<U> other, U targetUnit) {
        double sumInBase = this.unit.convertToBaseUnit(this.value) + 
                           other.unit.convertToBaseUnit(other.value);
        return new Quantity<>(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Quantity<?> that = (Quantity<?>) o;
        // Runtime check to prevent comparing 1 kg to 1 foot
        if (this.unit.getClass() != that.unit.getClass()) return false;
        return Double.compare(this.unit.convertToBaseUnit(this.value), 
                             ((IMeasurable)that.unit).convertToBaseUnit((Double)that.value)) == 0;
    }

    @Override
    public String toString() { return value + " " + unit; }
}
