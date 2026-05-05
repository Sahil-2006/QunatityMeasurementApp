package com.apps.quantitymeasurement;

import java.util.Objects;

/**
 * A generic class representing length measurements with unit-to-unit conversion 
 * and addition capabilities.
 */
public class Length {
    private final double value;
    private final LengthUnit unit;

    /**
     * Enum containing units and their conversion factors relative to inches.
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
     * Normalizes value to the base unit (inches) for internal operations.
     */
    private double convertToBaseUnit() {
        return this.value * this.unit.getConversionFactor();
    }

    /**
     * UC5: Explicitly converts this length to a target unit.
     */
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) throw new IllegalArgumentException("Target unit cannot be null");
        double valueInInches = this.value * this.unit.getConversionFactor();
        double convertedValue = valueInInches / targetUnit.getConversionFactor();
        return new Length(Math.round(convertedValue * 100.0) / 100.0, targetUnit);
    }

    /**
     * UC6: Adds another Length object to this one. 
     * Result is in the unit of the first operand (this instance).
     */
    public Length add(Length thatLength) {
        if (thatLength == null) throw new IllegalArgumentException("Operand cannot be null");
        
        // Sum values in the base unit (inches)
        double totalInInches = this.convertToBaseUnit() + thatLength.convertToBaseUnit();
        
        // Convert sum back to the unit of the first operand
        double finalValue = totalInInches / this.unit.getConversionFactor();
        
        return new Length(Math.round(finalValue * 100.0) / 100.0, this.unit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length that = (Length) o;
        // Compare values after rounding to 2 decimal places in base unit
        return Double.compare(Math.round(this.convertToBaseUnit() * 100.0) / 100.0, 
                             Math.round(that.convertToBaseUnit() * 100.0) / 100.0) == 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}
