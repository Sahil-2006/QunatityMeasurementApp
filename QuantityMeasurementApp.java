package com.apps.quantitymeasurement;

/**
 * QuantityMeasurementApp - UC1: Feet measurement equality
 * 
 * This class is responsible for checking the equality of two numerical values
 * measured in feet in the Quantity Measurement Application.
 */
public class QuantityMeasurementApp {

    // Inner class to represent Feet measurement
    public static class Feet {
        private final double value;

        public Feet(double value) {
            this.value = value;
        }

        /**
         * Override equals() method to compare two Feet objects based on their value
         * 
         * Important Checks:
         * 1. Reference Check: If both references point to the same object, return true
         * 2. Null Check: If the compared object is null, return false
         * 3. Type Check: If the compared object is not of type Feet, return false
         * 4. Value Comparison: Use Double.compare() to compare the double values for equality
         * 
         * @param obj The object to compare with
         * @return true if both Feet objects have the same value, false otherwise
         */
        @Override
        public boolean equals(Object obj) {
            // 1. Reference Check
            if (this == obj) {
                return true;
            }

            // 2. Null Check and 3. Type Check
            if (obj == null || getClass() != obj.getClass()) {
                return false;
            }

            // 4. Value Comparison
            Feet feet = (Feet) obj;
            return Double.compare(feet.value, value) == 0;
        }
    }

    // Main method to demonstrate Feet equality check
    public static void main(String[] args) {
        Feet feet1 = new Feet(1.0);
        Feet feet2 = new Feet(1.0);
        
        System.out.println("Input: 1.0 ft and 1.0 ft");
        System.out.println("Output: Equal (" + feet1.equals(feet2) + ")");
    }
}
