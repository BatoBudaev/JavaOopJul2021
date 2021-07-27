package budaev.shape_main;

import budaev.shape.*;

import java.util.Arrays;

public class ShapeMain {
    public static void main(String[] args) {
        Shape[] shapes = new Shape[]{
                new Square(2),
                new Rectangle(5, 10),
                new Triangle(1, 2, 3, 7, 5, 10),
                new Circle(8),
                new Square(5)
        };

        Shape maxAreaShape = getMaxArea(shapes);
        System.out.println("Максимальная площадь: " + maxAreaShape.toString() + " площадью = " + maxAreaShape.getArea());

        Shape secondPerimeterShape = getSecondPerimeter(shapes);
        System.out.println("Второй периметр: " + secondPerimeterShape.toString() + " периметром = " + secondPerimeterShape.getPerimeter());

        System.out.println("Хэш таблица фигур:");
        for (Shape shape : shapes) {
            System.out.println(shape.toString() + " - " + shape.hashCode());
        }

        System.out.println(shapes[2].equals(shapes[4]));
    }

    public static Shape getMaxArea(Shape[] shapes) {
        Arrays.sort(shapes, new AreaComparator());

        return shapes[0];
    }

    public static Shape getSecondPerimeter(Shape[] shapes) {
        Arrays.sort(shapes, new PerimeterComparator());

        return shapes[1];
    }
}