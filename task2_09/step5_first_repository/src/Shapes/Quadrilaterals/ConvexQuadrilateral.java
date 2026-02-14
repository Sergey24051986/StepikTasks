package Shapes.Quadrilaterals;

public class ConvexQuadrilateral extends Quadrilateral {
    String name;
    public ConvexQuadrilateral() {
        name = "Выпуклый четырехугольник";
    }
    @Override
    public String toString() {
        return name;
    }
}
