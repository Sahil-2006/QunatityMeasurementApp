package com.apps.quantitymeasurement;

/**
 * A generic class representing length measurements with unit-to-unit conversion,
 * equality checks, and addition capabilities.
 */
public class Length {
    private final double value;
    private final LengthUnit unit;

    /**
     * Nested enum representing different length units and their conversion factors 
     * relative to inches (the base unit).
     */
    public enum LengthUnit {
        FEET(12.0),
        INCHES(1.0),
        YARDS(36.0),
        CENTIMETERS(0.393701);

        private final double conversionFactor;

        LengthUnit(double conversionFactor) {
            this.conversionFactor = conversionFactor;
        }

        public double getConversionFactor() {
            return conversionFactor;
        }
    }

    public Length(double value, LengthUnit unit) {
        if (unit == null) throw new IllegalArgumentException("Unit cannot be null");
        this.value = value;
        this.unit = unit;
    }

    /**
     * Normalizes the value to the base unit (inches) with rounding for 
     * deterministic equality checks.
     */
    private double convertToBaseUnit() {
        return Math.round((value * unit.getConversionFactor()) * 100.0) / 100.0;
    }

    /**
     * UC5: Converts this length instance to a target unit.
     */
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double valueInInches = value * unit.getConversionFactor();
        double convertedValue = valueInInches / targetUnit.getConversionFactor();
        return new Length(Math.round(convertedValue * 100.0) / 100.0, targetUnit);
    }

    /**
     * UC6: Adds another Length to this one. 
     * Result unit defaults to the unit of the first operand.
     */
    public Length add(Length thatLength) {
        return add(thatLength, this.unit);
    }

    /**
     * UC7: Adds two lengths with an explicitly specified target unit.
     */
    public Length add(Length thatLength, LengthUnit targetUnit) {
        if (thatLength == null || targetUnit == null) {
            throw new IllegalArgumentException("Operand and target unit must not be null");
        }
        double totalInInches = (this.value * this.unit.getConversionFactor()) + 
                             (thatLength.value * thatLength.unit.getConversionFactor());
        double finalValue = totalInInches / targetUnit.getConversionFactor();
        return new Length(Math.round(finalValue * 100.0) / 100.0, targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length that = (Length) o;
        return Double.compare(this.convertToBaseUnit(), that.convertToBaseUnit()) == 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}
