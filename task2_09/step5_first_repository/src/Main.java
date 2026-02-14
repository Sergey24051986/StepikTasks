import Shapes.Figure;
import Shapes.Pentagon;
import Shapes.Quadrilaterals.ConvexQuadrilateral;
import Shapes.Quadrilaterals.Parallelogram;
import Shapes.Quadrilaterals.Trapezoid;
import Shapes.Triangles.EquilateralTriangle;
import Shapes.Triangles.IsoscelesTriangle;
import Shapes.Triangles.RightAngledTriangle;
import Shapes.Triangles.Triangle;

void main() {

    Figure shape = new Triangle();
    Figure shape1 = new ConvexQuadrilateral();
    Figure shape2 = new Parallelogram();
    Figure shape3 = new Trapezoid();
    Figure shape4 = new EquilateralTriangle();
    Figure shape5 = new IsoscelesTriangle();
    Figure shape6 = new RightAngledTriangle();
    Figure shape7 = new Pentagon();

    IO.println(shape + "\n" +
            shape1 + "\n" +
            shape2 + "\n" +
            shape3 + "\n" +
            shape4 + "\n" +
            shape5 + "\n" +
            shape6 + "\n" +
            shape7);
}
