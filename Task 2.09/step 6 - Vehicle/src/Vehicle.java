public abstract class Vehicle {
    private String name;
    private String type;
    private String fuelType;
    private int weight;
    private int maxSpeed;
    private boolean isFly;
    private boolean isSwim;
    private boolean isRide;

    protected Vehicle(String name, String fuelType, int weight, int maxSpeed) {
        this.name = name;
        this.fuelType = fuelType;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
    }
    protected Vehicle(String name, String fuelType, int weight, int maxSpeed, boolean isFly, boolean isSwim, boolean isRide) {
        this.name = name;
        this.fuelType = fuelType;
        this.weight = weight;
        this.maxSpeed = maxSpeed;
        this.isFly = isFly;
        this.isSwim = isSwim;
        this.isRide = isRide;
    }

    //get
    public String getName() {
        return name;
    }
    public String getType() {
        return type;
    }
    public String getFuelType() {
        return fuelType;
    }
    public int getWeight() {
        return weight;
    }
    public int getMaxSpeed() {
        return maxSpeed;
    }
    public boolean isFly() {
        return isFly;
    }
    public boolean isSwim() {
        return isSwim;
    }
    public boolean isRide() {
        return isRide;
    }

    //set
    public void setName(String name) {this.name = name;}
    public void setType(String type){this.type = type;}
    public void setFuelType(String fuelType) {this.fuelType = fuelType;}
    public void setWeight(int weight) {this.weight = weight;}
    public void setMaxSpeed(int maxSpeed) {this.maxSpeed = maxSpeed;}
    public void setFly(boolean fly){this.isFly = fly;}
    public void setSwim(boolean swim) {this.isSwim = swim;}
    public void setRide(boolean ride) {this.isRide = ride;}

    public void moveForward() {
        System.out.println("(" + name + ")" + "Двигаюсь вперёд");
    }
    public void moveBackward() {
        System.out.println("(" + name + ")" + "Двигаюсь назад");
    }
    public void moveToTheRight() {
        System.out.println("(" + name + ")" + "Поворачиваю направо");
    }
    public void moveToTheLeft() {
        System.out.println("(" + name + ")" + "Поворачиваю налево");
    }

    @Override
    public String toString() {
        return "Название: " + name +
                "\nТип: " + type +
                "\nТип топлива: " + fuelType +
                "\nВес: " + weight + " кг." +
                "\nМаксимальная скорость: " + maxSpeed + " км/ч." +
                "\nУмеет летать: " + (isFly ? "Да" : "Нет") +
                "\nУмеет плавать: " + (isSwim ? "Да" : "Нет") +
                "\nУмеет ездить: " + (isRide ? "Да" : "Нет");
    }
}
