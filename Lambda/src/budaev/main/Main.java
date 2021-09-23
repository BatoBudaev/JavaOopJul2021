package budaev.main;

import budaev.person.Person;

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

        //А

        String uniqueNames = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", "));

        System.out.println(uniqueNames);

        //Б

        String uniqueNamesWithFormat = persons.stream()
                .map(Person::getName)
                .distinct()
                .collect(Collectors.joining(", ", "Имена: ", ""));

        System.out.println(uniqueNamesWithFormat);

        //В

        Double youngPersonsAverageAge = persons.stream()
                .filter(p -> p.getAge() < 18)
                .collect(Collectors.averagingInt(Person::getAge)
                );

        System.out.println(youngPersonsAverageAge);

        //Г

        Map<String, Double> personsMap = persons.stream()
                .collect(Collectors.groupingBy(Person::getName, Collectors.averagingInt(Person::getAge)
                ));

        System.out.println(personsMap);

        //Д

        String names = persons.stream()
                .filter(p -> p.getAge() >= 20 && p.getAge() <= 45)
                .sorted((p1, p2) -> p2.getAge() - p1.getAge())
                .map(Person::getName)
                .collect(Collectors.joining(", "));

        System.out.println(names);
    }
}