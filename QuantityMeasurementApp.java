package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        return q1.equals(q2); //
    }

    public static void main(String[] args) {
        // UC11: Volume Example
        Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> thousandMl = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        System.out.println("1 L == 1000 mL? " + demonstrateEquality(oneLitre, thousandMl));

        // UC12: Subtraction Example
        Quantity<LengthUnit> tenFeet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> sixInches = new Quantity<>(6.0, LengthUnit.INCHES);
        System.out.println("10 ft - 6 in (in ft): " + tenFeet.subtract(sixInches, LengthUnit.FEET));

        // UC12: Division Example
        Quantity<WeightUnit> twoKg = new Quantity<>(2.0, WeightUnit.KILOGRAM);
        Quantity<WeightUnit> fiveHundredG = new Quantity<>(500.0, WeightUnit.GRAM);
        System.out.println("2 kg / 500 g: " + twoKg.divide(fiveHundredG)); // Output: 4.0
    }
}
