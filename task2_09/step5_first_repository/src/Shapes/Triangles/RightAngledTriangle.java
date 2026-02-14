package Shapes.Triangles;

public class RightAngledTriangle extends Triangle{
    String name;
    public RightAngledTriangle() {
        name = "Прямоугольный треугольник";
    }
    @Override
    public String toString() {
        return name;
    }
}
