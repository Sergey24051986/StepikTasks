package Shapes.Triangles;

public class EquilateralTriangle extends Triangle {
    String name;
    public EquilateralTriangle() {
        name = "Равносторонний треугольник";
    }
    @Override
    public String toString() {
        return name;
    }
}
