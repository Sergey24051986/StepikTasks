package Shapes;

public abstract class Figure {
    String name;
    public Figure() {
        name = "Фигура";
    }
    @Override
    public String toString() {
        return name;
    }
}

