package budaev.lambda_main;

import budaev.lambda_person.Person;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Person> persons = Arrays.asList(
                new Person("Ivan", 10),
                new Person("Petr", 12),
                new Person("Olga", 25),
                new Person("Vladimir", 33),
                new Person("Irina", 23),
                new Person("Anna", 25),
                new Person("Mikhail", 44),
                new Person("Ivan", 70),
                new Person("Sergey", 52)
        );

        // А

        List<String> uniqueNamesList = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.toList());

        System.out.println(uniqueNamesList);

        // Б

        String uniqueNamesWithFormat = uniqueNamesList.stream()
                .collect(Collectors.joining(", ", "Имена: ", "."));

        System.out.println(uniqueNamesWithFormat);

        // В

        Double youngPersonsAverageAge = persons.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.averagingInt(Person::getAge));

        System.out.println("Средний возраст людей младше 18: " + youngPersonsAverageAge);

        // Г

        Map<String, Double> averageAgeMap = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)));

        System.out.println(averageAgeMap);

        // Д

        String names = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        System.out.println(names);
    }
}