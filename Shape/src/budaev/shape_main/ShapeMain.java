package budaev.shape_main;

import budaev.shape.*;

import java.util.Arrays;

public class ShapeMain {
    public static void main(String[] args) {
        Shape[] shapes = {
                new Square(2),
                new Rectangle(5, 10),
                new Triangle(1, 2, 3, 7, 5, 10),
                new Circle(8),
                new Square(5)
        };

        System.out.println(shapes[1] + " с шириной " + shapes[1].getWidth() + " с длиной " + shapes[1].getHeight());

        Shape maxAreaShape = getMaxAreaShape(shapes);
        assert maxAreaShape != null;
        System.out.println("Максимальная площадь: " + maxAreaShape + " и площадью = " + maxAreaShape.getArea());

        Shape secondPerimeterShape = getSecondPerimeterShape(shapes);
        assert secondPerimeterShape != null;
        System.out.println("Второй периметр: " + secondPerimeterShape + ", и периметром = " + secondPerimeterShape.getPerimeter());

        System.out.println("Хэш-коды фигур:");

        for (Shape shape : shapes) {
            System.out.println(shape.toString() + " - " + shape.hashCode());
        }

        System.out.println("Сравнение фигур: " + shapes[2].equals(shapes[4]));
    }

    public static Shape getMaxAreaShape(Shape[] shapes) {
        if (shapes.length == 0) {
            return null;
        }

        Arrays.sort(shapes, new AreaComparator());

        return shapes[shapes.length - 1];
    }

    public static Shape getSecondPerimeterShape(Shape[] shapes) {
        if (shapes.length <= 1) {
            return null;
        }

        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[shapes.length - 2];
    }
}