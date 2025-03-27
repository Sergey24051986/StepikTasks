
public abstract class Quadrilateral extends Figure {
    String name;
    Quadrilateral() {
        name = "Четырехугольник";
    }
    @Override
    public String toString() {
        return name;
    }
}
