package Shapes.Triangles;

public class IsoscelesTriangle extends Triangle{
    String name;
    public IsoscelesTriangle() {
        name = "Равнобедренный треугольник";
    }
    @Override
    public String toString() {
        return name;
    }
}
