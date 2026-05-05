package com.apps.quantitymeasurement;

public class QuantityMeasurementApp {
    public static void main(String[] args) {
        // UC11: Volume
        Quantity<VolumeUnit> litre = new Quantity<>(1.0, VolumeUnit.LITRE);
        Quantity<VolumeUnit> ml = new Quantity<>(1000.0, VolumeUnit.MILLILITRE);
        System.out.println("1L == 1000mL? " + litre.equals(ml));

        // UC12/13: Centralized Arithmetic
        Quantity<LengthUnit> feet = new Quantity<>(10.0, LengthUnit.FEET);
        Quantity<LengthUnit> inches = new Quantity<>(6.0, LengthUnit.INCHES);
        System.out.println("10ft - 6in in ft: " + feet.subtract(inches, LengthUnit.FEET));
    }
}
