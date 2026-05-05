package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    // Weight Functionality
    public static boolean demonstrateWeightEquality(Weight w1, Weight w2) {
        return w1.equals(w2);
    }

    public static Weight demonstrateWeightConversion(double val, WeightUnit from, WeightUnit to) {
        return new Weight(val, from).convertTo(to);
    }

    public static Weight demonstrateWeightAddition(Weight w1, Weight w2, WeightUnit target) {
        return w1.add(w2, target);
    }

    public static void main(String[] args) {
        // Weight Demonstration
        Weight oneKg = new Weight(1.0, WeightUnit.KILOGRAM);
        Weight thousandGrams = new Weight(1000.0, WeightUnit.GRAM);
        System.out.println("1 kg == 1000 g: " + oneKg.equals(thousandGrams));

        Weight sumInLbs = oneKg.add(thousandGrams, WeightUnit.POUND);
        System.out.println("1 kg + 1000 g in Pounds: " + sumInLbs);

        // Category Isolation Check
        Length oneFoot = new Length(1.0, LengthUnit.FEET);
        System.out.println("Is 1kg equal to 1ft? " + oneKg.equals(oneFoot)); // Should print false
    }
}
