package com.apps.quantitymeasurement;

/**
 * API Wrapper and demonstration class for the Quantity Measurement Application.
 */
public class QuantityMeasurementApp {

    public static boolean demonstrateLengthEquality(Length l1, Length l2) {
        return l1.equals(l2);
    }

    public static Length demonstrateLengthConversion(double val, Length.LengthUnit from, Length.LengthUnit to) {
        return new Length(val, from).convertTo(to);
    }

    public static Length demonstrateLengthAddition(Length l1, Length l2, Length.LengthUnit target) {
        return l1.add(l2, target);
    }

    public static void main(String[] args) {
        // UC4: Feet to Inch Comparison
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);
        Length twelveInches = new Length(12.0, Length.LengthUnit.INCHES);
        System.out.println("1 ft == 12 in: " + oneFoot.equals(twelveInches));

        // UC5: Yard to Foot Conversion
        Length yardToFeet = demonstrateLengthConversion(1.0, Length.LengthUnit.YARDS, Length.LengthUnit.FEET);
        System.out.println("1 Yard converted to Feet: " + yardToFeet);

        // UC7: Addition with explicit target unit
        Length sumInYards = demonstrateLengthAddition(oneFoot, twelveInches, Length.LengthUnit.YARDS);
        System.out.println("1 ft + 12 in in Yards: " + sumInYards);
    }
}
