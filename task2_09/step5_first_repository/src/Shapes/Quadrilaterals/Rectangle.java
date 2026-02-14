package Shapes.Quadrilaterals;

public class Rectangle extends Quadrilateral {
    String name;
    public Rectangle() {
        name = "Четырехугольник";
    }
    @Override
    public String toString() {
        return name;
    }
}
