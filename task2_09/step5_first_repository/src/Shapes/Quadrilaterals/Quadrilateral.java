package Shapes.Quadrilaterals;

import Shapes.Figure;

public abstract class Quadrilateral extends Figure {
    String name;
    public Quadrilateral() {
        super();
        name = "Четырехугольник";
    }
    @Override
    public String toString() {
        return name;
    }
}
