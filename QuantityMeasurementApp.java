package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double val, LengthUnit from, LengthUnit to) {
        return new Length(val, from).convertTo(to);
    }

    public static void main(String[] args) {
        // UC8: Refactored Equality check
        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        Length twelveInches = new Length(12.0, LengthUnit.INCHES);
        System.out.println("1 ft == 12 in: " + oneFoot.equals(twelveInches));

        // UC8: Refactored Conversion
        Length yardToInches = demonstrateLengthConversion(1.0, LengthUnit.YARDS, LengthUnit.INCHES);
        System.out.println("1 Yard in Inches: " + yardToInches);
        
        // UC8: Refactored Addition
        Length sum = oneFoot.add(twelveInches, LengthUnit.YARDS);
        System.out.println("1 ft + 12 in in Yards: " + sum);
    }
}
