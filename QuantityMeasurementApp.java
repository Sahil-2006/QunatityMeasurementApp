package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // UC5: Utility for conversion
    public static Length demonstrateLengthConversion(double value, Length.LengthUnit from, Length.LengthUnit to) {
        return new Length(value, from).convertTo(to);
    }

    // UC6: Utility for addition
    public static Length demonstrateLengthAddition(Length l1, Length l2) {
        return l1.add(l2);
    }

    public static void main(String[] args) {
        // UC6 Examples
        Length oneFoot = new Length(1.0, Length.LengthUnit.FEET);
        Length twelveInches = new Length(12.0, Length.LengthUnit.INCHES);
        
        // 1 ft + 12 in = 2 ft
        System.out.println("Addition (Feet): " + oneFoot.add(twelveInches));
        
        // 12 in + 1 ft = 24 in
        System.out.println("Addition (Inches): " + twelveInches.add(oneFoot));
        
        // 1 yd + 3 ft = 2 yd
        Length oneYard = new Length(1.0, Length.LengthUnit.YARDS);
        Length threeFeet = new Length(3.0, Length.LengthUnit.FEET);
        System.out.println("Addition (Yards): " + oneYard.add(threeFeet));
    }
}
