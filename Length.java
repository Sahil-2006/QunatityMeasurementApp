package com.apps.quantitymeasurement;

public class Length {
    private final double value;
    private final LengthUnit unit;

    // Enum to represent different length units and their conversion factors
    // Base unit is inches
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

    private double convertToBaseUnit() {
        return value * unit.getConversionFactor();
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Length length = (Length) o;
        // Compare based on converted values to base unit
        return Double.compare(Math.round(this.convertToBaseUnit() * 100.0) / 100.0, 
                             Math.round(length.convertToBaseUnit() * 100.0) / 100.0) == 0;
    }

    public static void main(String[] args) {
        // Yard to Feet Comparison
        Length length1 = new Length(1.0, LengthUnit.YARDS);
        Length length2 = new Length(3.0, LengthUnit.FEET);
        System.out.println("1 Yard == 3 Feet? " + length1.equals(length2));

        // Centimeters to Inches Comparison
        Length length3 = new Length(100.0, LengthUnit.CENTIMETERS);
        Length length4 = new Length(39.3701, LengthUnit.INCHES);
        System.out.println("100 cm == 39.3701 in? " + length3.equals(length4));
    }
}
