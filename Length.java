package com.apps.quantitymeasurement;

public class Length {
    private final double value;
    private final LengthUnit unit;

    public Length(double value, LengthUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input: value must be finite and unit non-null");
        }
        this.value = value;
        this.unit = unit;
    }

    /**
     * Delegates conversion to the standalone enum methods.
     */
    public Length convertTo(LengthUnit targetUnit) {
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Length(convertedValue, targetUnit);
    }

    /**
     * UC7: Adds two lengths and returns result in a specified target unit.
     */
    public Length add(Length thatLength, LengthUnit targetUnit) {
        if (thatLength == null || targetUnit == null) {
            throw new IllegalArgumentException("Operand and target unit must not be null");
        }
        double sumInBase = (this.value * this.unit.getConversionFactor()) + 
                           (thatLength.value * thatLength.unit.getConversionFactor());
        return new Length(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length that = (Length) o;
        return Double.compare(this.unit.convertToBaseUnit(this.value), 
                             that.unit.convertToBaseUnit(that.value)) == 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}
