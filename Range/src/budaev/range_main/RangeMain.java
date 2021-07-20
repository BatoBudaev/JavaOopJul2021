package budaev.range_main;

import budaev.range.Range;

public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(1.1, 8.2);

        double number = 0.3;

        if (range1.isInside(number)) {
            System.out.printf("Число %.2f находится в диапозоне %s%n", number, range1);
        } else {
            System.out.printf("Число %.2f не находится в диапозоне %s%n", number, range1);
        }

        Range range2 = new Range(0.2, 7.3);

        range2.setFrom(3.2);
        range2.setTo(6.5);

        System.out.printf("Длина диапозона %s = %.2f%n", range2, range2.getLength());

        Range intersection = range1.getIntersection(range2);

        if (intersection != null) {
            System.out.printf("Диапозон пересечения интервалов %s%n", intersection);
        } else {
            System.out.println("Пересечений нет.");
        }

        System.out.print("Диапозон объединения интервалов ");
        Range.printArray(range1.getUnion(range2));

        System.out.print("Диапозон разности интервалов ");
        Range.printArray(range1.getDifference(range2));
    }
}