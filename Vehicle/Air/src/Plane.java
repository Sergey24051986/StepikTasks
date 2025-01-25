public class Plane extends Helicopter {
    private final int maxLandingWeight;
    private final int fuelWeight;

    {
        setType("Самолет");
        setFly(true);
        setRide(true);
    }

    public Plane(String name, String fuelType, int weight, int maxSpeed, int maxTakeoffWeight, int flightRange, int maxHeight, int crew, int passengers, int maxLandingWeight, int fuelWeight) {
        super(name, fuelType, weight, maxSpeed, maxTakeoffWeight, flightRange, maxHeight, crew, passengers);
        this.maxLandingWeight = maxLandingWeight;
        this.fuelWeight = fuelWeight;
    }

    @Override
    public String toString() {
            return super.toString() +
                    "Максимальная посадочная масса: " + maxLandingWeight + " кг." +
                    "\nМасса топлива: " + fuelWeight + " л." + "\n";
    }
}

