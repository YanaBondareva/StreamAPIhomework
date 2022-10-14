import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) {

        List<String> names = Arrays.asList("Jack", "Connor", "Harry", "George", "Samuel", "John");
        List<String> families = Arrays.asList("Evans", "Young", "Harris", "Wilson", "Davies", "Adamson", "Brown");
        Collection<Person> persons = new ArrayList<>();
        for (int i = 0; i < 10_000_000; i++) {
            persons.add(new Person(
                    names.get(new Random().nextInt(names.size())),
                    families.get(new Random().nextInt(families.size())),
                    new Random().nextInt(100),
                    Sex.values()[new Random().nextInt(Sex.values().length)],
                    Education.values()[new Random().nextInt(Education.values().length)])
            );
        }
        System.out.println("Количество сгенерированных людей: " + persons.size());


        Stream stream = persons.stream().filter(i -> i.getAge() < 18);
        System.out.println("Количество людей младше 18: " + stream.count());

        List<String> sold = persons.stream()
                .filter(i -> i.getSex() == Sex.MAN)
                .filter(i -> i.getAge() > 18 && i.getAge() < 27)
                .map(Person::getFamily).collect(Collectors.toList());
        System.out.println("Количество призывников: " + sold.size());

        List<Person> work = persons.stream()
                .filter(i -> i.getEducation() == Education.HIGHER && i.getAge() > 18)
                .filter(i -> i.getAge() < 60 && i.getSex() == Sex.WOMAN || i.getAge() < 65 && i.getSex() == Sex.MAN)
                .sorted(Comparator.comparing(Person::getFamily)).collect(Collectors.toList());
        System.out.println("Количество работоспособных: " + work.size());

    }


}
