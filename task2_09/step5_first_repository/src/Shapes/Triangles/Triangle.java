package Shapes.Triangles;

import Shapes.Figure;

public class Triangle extends Figure {
    String name;
    public Triangle() {
        name = "Треугольник";
    }
    @Override
    public String toString() {
        return name;
    }
}

