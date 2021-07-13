package budaev.range_main;

import budaev.range.Range;

public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(1.1, 8.2);

        double number = 0.3;

        if (range1.isInside(number)) {
            System.out.printf("Число %.2f находится в диапозоне от %.2f до %.2f%n", number, range1.getFrom(), range1.getTo());
        } else {
            System.out.printf("Число %.2f не находится в диапозоне от %.2f до %.2f%n", number, range1.getFrom(), range1.getTo());
        }

        Range range2 = new Range(0.2, 7.3);

        range2.setFrom(3.1);
        range2.setTo(6.5);

        System.out.printf("Длина диапозона от %.2f до %.2f = %.2f%n", range2.getFrom(), range2.getTo(), range2.getLength());

        Range intersection = range1.getIntersection(range2);

        if (intersection != null) {
            System.out.printf("Диапозон пересечения интервалов = от %.2f до %.2f%n", intersection.getFrom(), intersection.getTo());
        } else {
            System.out.println("Пересечений нет.");
        }

        Range[] union = range1.getUnion(range2);
        for (Range range : union) {
            System.out.printf("Диапозон объединения интервалов = от %.2f до %.2f%n", range.getFrom(), range.getTo());
        }

        Range[] complement = range1.getComplement(range2);

        if (complement == null) {
            System.out.println("Диапозон разности отсутствует.");
        } else {
            for (Range range : complement) {
                System.out.printf("Диапозон разности интервалов = от %.2f до %.2f%n", range.getFrom(), range.getTo());
            }
        }
    }
}