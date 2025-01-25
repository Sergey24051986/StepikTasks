public class Under extends Above implements MoveDownUP {
    private final int underwaterMaxSpeed;
    private final int displacementUnderwater;
    private final int maxDepthOfImmersion;
    private final int workDepthOfImmersion;
    private final int autonomy;

    {
        setType("Подводный");
        setSwim(true);
        setSwimUnderwater(true);
    }

    public Under(String name, String fuelType, int weight, int maxSpeed, int displacement, int underwaterMaxSpeed, int crew, int displacementUnderwater, int maxDepthOfImmersion, int workDepthOfImmersion, int autonomy) {
        super(name, fuelType, weight, maxSpeed, displacement, crew);
        this.underwaterMaxSpeed = underwaterMaxSpeed;
        this.displacementUnderwater = displacementUnderwater;
        this.maxDepthOfImmersion = maxDepthOfImmersion;
        this.workDepthOfImmersion = workDepthOfImmersion;
        this.autonomy = autonomy;
    }
    @Override
    public String toString() {
        return super.toString() +
                "Максимальная подводная скорость: " + underwaterMaxSpeed + " км/ч." +
                "\nВодоизмещение подводное: " + displacementUnderwater + " кг." +
                "\nМаксимальная глубина погружения: " + maxDepthOfImmersion + " м." +
                "\nРабочая глубина погружения: " + workDepthOfImmersion + " м." +
                "\nАвтономность: " + autonomy + " дней."  + "\n";
    }
    @Override
    public void moveDown() {
        System.out.println("(" + getName() + ")" + "Погружаюсь");
    }

    @Override
    public void moveUp() {
        System.out.println("(" + getName() + ")" + "Всплываю");
    }
}
