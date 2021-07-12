public class RangeMain {
    public static void main(String[] args) {
        Range range1 = new Range(3.1, 7.2);

        double number = 0.3;

        if (range1.isInside(number)) {
            System.out.printf("Число %.2f находится в диапозоне от %.2f до %.2f%n", number, range1.getFrom(), range1.getTo());
        } else {
            System.out.printf("Число %.2f не находится в диапозоне от %.2f до %.2f%n", number, range1.getFrom(), range1.getTo());
        }

        Range range2 = new Range(0.2, 7.3);

        range2.setFrom(5.22);
        range2.setTo(9.5);

        System.out.printf("Длина диапозона от %.2f до %.2f = %.2f%n", range2.getFrom(), range2.getTo(), range2.getLength());

        Range intersectionRange = range1.getRangesIntersection(range2);

        if (intersectionRange != null) {
            System.out.printf("Диапозон пересечения интервалов = от %f до %f", intersectionRange.getFrom(), intersectionRange.getTo());
        } else {
            System.out.println("Пересечений нет.");
        }
    }
}