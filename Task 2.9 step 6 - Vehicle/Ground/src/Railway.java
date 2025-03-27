public class Railway extends Vehicle implements PublicTransport{
    private final int wagons;
    private final int seats;

    {
        setType("Железнодорожный");
        setRide(true);
    }

    public Railway(String name, String fuelType, int weight, int maxSpeed, int wagons, int seats) {
        super(name, fuelType, weight, maxSpeed);
        this.wagons = wagons;
        this.seats = seats;
    }

    @Override
    public void openTheDoors() {
        System.out.print("(" + getName() + ")");
        PublicTransport.super.openTheDoors();
    }

    @Override
    public void closeTheDoors() {
        System.out.print("(" + getName() + ")");
        PublicTransport.super.closeTheDoors();
    }

    @Override
    public String toString() {
        return super.toString() +
                "\nВагоны: " + wagons +
                "\nКоличество мест: " + seats + "\n";
    }

}
