package budaev.shape_main;

import budaev.shape.Shape;

import java.util.Comparator;

public class PerimeterComparator implements Comparator<Shape> {
    @Override
    public int compare(Shape shape1, Shape shape2) {
        return (int) (shape2.getPerimeter() - shape1.getPerimeter());
    }
}
