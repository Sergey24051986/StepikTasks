package Shapes;

public class Circle extends Figure{
    String name;
    public Circle() {name = "Круг";}
    @Override
    public String toString() {
        return name;
    }
}
