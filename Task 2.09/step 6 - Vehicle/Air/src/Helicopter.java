public class Helicopter extends Vehicle implements MoveDownUP {
    private final int maxTakeoffWeight;
    private final int flightRange;
    private final int maxHeight;
    private final int crew;
    private final int passengers;

    {
        setType("Вертолет");
        setFly(true);
        setRide(true);
    }

    public Helicopter(String name, String fuelType, int weight, int maxSpeed, int maxTakeoffWeight, int flightRange, int maxHeight, int crew, int passengers) {
        super(name, fuelType, weight, maxSpeed);
        this.maxTakeoffWeight = maxTakeoffWeight;
        this.flightRange = flightRange;
        this.maxHeight = maxHeight;
        this.crew = crew;
        this.passengers = passengers;
    }
    @Override
    public void moveDown() {
        System.out.println("(" + getName() + ")" + "Снижаюсь");
    }

    @Override
    public void moveUp() {
        System.out.println("(" + getName() + ")" + "Взлетаю");
    }
    @Override
    public String toString() {
        return super.toString() +
                "\nМаксимальная взлетная масса: " + maxTakeoffWeight + " кг." +
                "\nДальность полета: " + flightRange + " км." +
                "\nМаксимальная высота: " + maxHeight + " км." +
                "\nЭкипаж: " + crew + " человек." +
                "\nПассажиры: " + passengers + " человек." + "\n";
    }
}
