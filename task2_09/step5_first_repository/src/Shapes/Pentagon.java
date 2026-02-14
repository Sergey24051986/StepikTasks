package Shapes;

public class Pentagon extends Figure {
    String name;
    int numberOfSide;
    public Pentagon() {
        name = "Пятиугольник";
        numberOfSide = 5;
    }
    @Override
    public String toString() {
        return name;
    }
}
