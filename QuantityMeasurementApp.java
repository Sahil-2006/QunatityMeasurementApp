package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    /**
     * Method 1: Takes raw numeric value and two units.
     */
    public static Length demonstrateLengthConversion(double value, Length.LengthUnit fromUnit, Length.LengthUnit toUnit) {
        Length sourceLength = new Length(value, fromUnit);
        return sourceLength.convertTo(toUnit);
    }

    /**
     * Method 2 (Overloaded): Takes an existing object and target unit.
     */
    public static Length demonstrateLengthConversion(Length length, Length.LengthUnit toUnit) {
        return length.convertTo(toUnit);
    }

    public static void main(String[] args) {
        // Example conversions
        System.out.println("1.0 FEET to INCHES: " + demonstrateLengthConversion(1.0, Length.LengthUnit.FEET, Length.LengthUnit.INCHES));
        System.out.println("3.0 YARDS to FEET: " + demonstrateLengthConversion(3.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET));
        
        Length hundredCm = new Length(100.0, Length.LengthUnit.CENTIMETERS);
        System.out.println("100.0 CM to INCHES: " + demonstrateLengthConversion(hundredCm, Length.LengthUnit.INCHES));
    }
}
