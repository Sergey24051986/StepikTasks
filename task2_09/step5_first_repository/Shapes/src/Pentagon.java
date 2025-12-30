

public class Pentagon extends Figure{
    String name;
    int numberOfSide;
    Pentagon() {
        name = "Пятиугольник";
        numberOfSide = 5;
    }
    @Override
    public String toString() {
        return name + "\nЧисло сторон: " + numberOfSide;
    }
}
