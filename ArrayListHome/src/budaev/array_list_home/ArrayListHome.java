package budaev.array_list_home;

import java.io.*;
import java.util.*;

public class ArrayListHome {
    public static void main(String[] args) throws IOException {
        try (BufferedReader reader = new BufferedReader(new FileReader("ArrayListHome/src/input.txt"))) {
            ArrayList<String> linesList = new ArrayList<>();
            String line;

            while ((line = reader.readLine()) != null) {
                linesList.add(line);
            }

            for (String lineForPrint : linesList) {
                System.out.println(lineForPrint);
            }
        } catch (FileNotFoundException e) {
            System.out.println("Файл не найден");
        }

        // 2
        ArrayList<Integer> numbersList1 = new ArrayList<>(Arrays.asList(1, 3, 5, 6, 8, 9));

        for (int i = 0; i < numbersList1.size(); i++) {
            if (numbersList1.get(i) % 2 == 0) {
                numbersList1.remove(i);
                i--;
            }
        }

        System.out.println("Список без четных чисел: " + numbersList1);

        // 3
        ArrayList<Integer> numbersList2 = new ArrayList<>(Arrays.asList(1, 5, 2, 1, 3, 5));
        ArrayList<Integer> numbersListWithoutDuplicates = new ArrayList<>(numbersList2.size());

        for (Integer number : numbersList2) {
            if (!numbersListWithoutDuplicates.contains(number)) {
                numbersListWithoutDuplicates.add(number);
            }
        }

        System.out.println("Список чисел без повторений: " + numbersListWithoutDuplicates);
    }
}