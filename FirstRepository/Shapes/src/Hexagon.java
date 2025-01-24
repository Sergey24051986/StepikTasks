

public class Hexagon extends Figure{
    String name;
    int numberOfSide;
    Hexagon() {
        name = "Шестиугольник";
        numberOfSide = 6;
    }
    @Override
    public String toString() {
        return name + "\nЧисло сторон: " + numberOfSide;
    }
}
