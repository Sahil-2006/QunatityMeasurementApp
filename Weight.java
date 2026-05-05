package com.apps.quantitymeasurement;

public class Weight {
    private final double value;
    private final WeightUnit unit;

    public Weight(double value, WeightUnit unit) {
        if (unit == null || !Double.isFinite(value)) {
            throw new IllegalArgumentException("Invalid input: value must be finite and unit non-null");
        }
        this.value = value;
        this.unit = unit;
    }

    public Weight convertTo(WeightUnit targetUnit) {
        double baseValue = this.unit.convertToBaseUnit(this.value);
        double convertedValue = targetUnit.convertFromBaseUnit(baseValue);
        return new Weight(convertedValue, targetUnit);
    }

    public Weight add(Weight thatWeight) {
        return add(thatWeight, this.unit);
    }

    public Weight add(Weight thatWeight, WeightUnit targetUnit) {
        if (thatWeight == null || targetUnit == null) {
            throw new IllegalArgumentException("Operand and target unit must not be null");
        }
        double sumInBase = (this.value * this.unit.getConversionFactor()) + 
                           (thatWeight.value * thatWeight.unit.getConversionFactor());
        return new Weight(targetUnit.convertFromBaseUnit(sumInBase), targetUnit);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        // Strict category check: ensures Weight cannot be compared to Length
        if (o == null || getClass() != o.getClass()) return false;
        Weight that = (Weight) o;
        return Double.compare(this.unit.convertToBaseUnit(this.value), 
                             that.unit.convertToBaseUnit(that.value)) == 0;
    }

    @Override
    public String toString() {
        return String.format("%.2f %s", value, unit);
    }
}
