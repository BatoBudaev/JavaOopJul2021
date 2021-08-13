package budaev.array_list_home;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.*;

public class ArrayListHome {
    public static void main(String[] args) throws FileNotFoundException {
        try (Scanner scanner = new Scanner(new FileInputStream("ArrayListHome/src/input.txt"))) {
            ArrayList<String> linesList = new ArrayList<>();

            while (scanner.hasNextLine()) {
                linesList.add(scanner.nextLine());
            }

            for (String line : linesList) {
                System.out.println(line);
            }
        }

        //2
        ArrayList<Integer> numbersList = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 8, 9));
        numbersList.removeIf(number -> number % 2 == 0);

        System.out.println(numbersList);

        //3
        ArrayList<Integer> numbersList2 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> newNumbersList = new ArrayList<>();

        for (Integer number : numbersList2) {
            if (!newNumbersList.contains(number)) {
                newNumbersList.add(number);
            }
        }

        System.out.println(newNumbersList);
    }
}