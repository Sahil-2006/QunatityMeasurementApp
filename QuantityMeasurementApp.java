package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {

    public static <U extends IMeasurable> boolean demonstrateEquality(Quantity<U> q1, Quantity<U> q2) {
        return q1.equals(q2);
    }

    public static void main(String[] args) {
        // Volume Demonstration (UC11)
        Quantity<VolumeUnit> oneLitre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> thousandMl = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        System.out.println("1 L == 1000 mL? " + demonstrateEquality(oneLitre, thousandMl)); // true

        // Addition Example
        Quantity<VolumeUnit> sum = oneLitre.add(thousandMl, VolumeUnit.LITRE);
        System.out.println("Sum of 1L and 1000mL: " + sum); // 2.0 LITRE

        // Category Safety Check
        Quantity<LengthUnit> oneFoot = new Quantity<>(1.0, LengthUnit.FEET);
        System.out.println("Is 1L == 1ft? " + oneLitre.equals(oneFoot)); // false
    }
}
