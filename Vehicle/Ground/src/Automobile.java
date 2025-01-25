public class Automobile extends Vehicle {
    private final int loadCapacity;
    private final int seats;
    private final int fuelTankCapacity;
    private final int powerReserve;

    {
        setType("Наземный");
        setSwim(true);
    }

    public Automobile(String name, String type, String fuelType, int weight, int maxSpeed, int loadCapacity, int fuelTankCapacity, int powerReserve, int seats) {
        super(name, fuelType, weight, maxSpeed);
        this.loadCapacity = loadCapacity;
        this.fuelTankCapacity = fuelTankCapacity;
        this.powerReserve = powerReserve;
        this.seats = seats;

    }
    public Automobile(String name, String type, String fuelType, int weight, int maxSpeed, boolean isFly, boolean isSwim, boolean isRide, int loadCapacity, int fuelTankCapacity, int powerReserve, int seats) {
        super(name, fuelType, weight, maxSpeed, isFly, isSwim, isRide);
        this.loadCapacity = loadCapacity;
        this.fuelTankCapacity = fuelTankCapacity;
        this.powerReserve = powerReserve;
        this.seats = seats;

    }

    @Override
    public String toString() {
        return super.toString() +
                "\nГрузоподъемность: " + loadCapacity + " кг." +
                "\nОбъем топливного бака: " + fuelTankCapacity + " л." +
                "\nЗапас хода: " + powerReserve + " км." +
                "\nКоличество сидячих мест: " + seats + "\n";

    }
}
