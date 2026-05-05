package com.apps.quantitymeasurement;

import java.util.Objects;

public class Length {
    private final double value;
    private final LengthUnit unit;

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
        this.value = value;
        this.unit = unit;
    }

    /**
     * Private Utility Method: Internal normalization to base unit (inches) 
     * with rounding to two decimal places.
     */
    private double convertToBaseUnit() {
        return Math.round((value * unit.getConversionFactor()) * 100.0) / 100.0;
    }

    /**
     * Public API: Converts this length instance to a target unit.
     */
    public Length convertTo(LengthUnit targetUnit) {
        if (targetUnit == null) {
            throw new IllegalArgumentException("Target unit cannot be null");
        }
        double valueInInches = value * unit.getConversionFactor();
        double convertedValue = valueInInches / targetUnit.getConversionFactor();
        // Return new instance with rounded value for consistency
        return new Length(Math.round(convertedValue * 100.0) / 100.0, targetUnit);
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
