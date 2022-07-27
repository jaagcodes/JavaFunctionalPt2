package functional.streams;


import functional.streams.model.Person;
import functional.streams.model.Product;

import java.time.LocalDate;
import java.time.Period;
import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class App {
    public static void main( String[] args ) {

        Person p1 = new Person(1, "Luis", LocalDate.of(2002, 8, 20));
        Person p2 = new Person(2, "Ana", LocalDate.of(1987, 10, 5));
        Person p3 = new Person(3, "Alberto", LocalDate.of(2013, 3, 7));
        Person p4 = new Person(4, "Luisa", LocalDate.of(1999, 6, 22));
        Person p5 = new Person(5, "Sonia", LocalDate.of(1989, 1, 1));

        Product pr1 = new Product(1, "Ceviche", 15.0);
        Product pr2 = new Product(2, "Chilaquiles", 25.50);
        Product pr3 = new Product(3, "Bandeja Paisa", 35.50);
        Product pr4 = new Product(4, "Ceviche", 15.0);

        List<Person> people = Arrays.asList(p1,p2,p3,p4,p5);
        List<Product> products = Arrays.asList(pr1,pr2,pr3,pr4);

        // Lambda // Method reference
        // list.forEach(System.out::println);
        //products.forEach(p -> System.out.println(p));
        //System.out.println();
        //persons.forEach(System.out::println);

        // 1-Filter (param: Predicate)
        List<Person> filteredList1 =  people.stream()
                                             .filter(p -> getAge(p.getDob()) >= 18)
                                             .collect(Collectors.toList());
        App.printList(filteredList1);

        // 2-Map (param: Function)
        List<Integer> filteredList2 = people.stream()
                .map(p -> getAge(p.getDob()))
                .collect(Collectors.toList());
        App.printList(filteredList2);

        // 3-Sorted (param: Comparator)
        Comparator<Person> byNameAsc = (Person o1, Person o2) -> o1.getName().compareTo(o2.getName());
        Comparator<Person> byNameDesc = (Person o1, Person o2) -> o2.getName().compareTo(o1.getName());
        Comparator<Person> bybirthDate = (Person o1, Person o2) -> o1.getDob().compareTo(o2.getDob());

        List<Person> filteredList3= people.stream()
                                            .sorted(bybirthDate)
                                            .collect(Collectors.toList());
        App.printList(filteredList3);


        // 4-Match (param: Predicate)
        Predicate<Person> startsWithPredicate = person -> person.getName().startsWith("J");
        // .anyMatch(pedicate): Evalua todo el stream, termina en la coincidencia. Verifica si hay alguna coincidencia y retorna true
        boolean rpta1 =  people.stream()
                                .anyMatch(p-> p.getName().startsWith("L"));
        System.out.println("rpta1 = " + rpta1);

        // .allMatch(predicate): Evalua todo el stream bajo la condicion
        boolean rpta2 = people.stream()
                .allMatch(p -> p.getName().startsWith("L"));
        System.out.println("rpta2 = " + rpta2);
        
        // .noneMatch(predicate): Evalua todo el stream bajo la condicion
        boolean rpta3 = people.stream()
                                .noneMatch(startsWithPredicate);
        System.out.println("rpta3 = " + rpta3);

        // 5-Limit/Skip
        int pageNumber = 2;
        int pageSize = 2;
        List<Person> filteredList4 = people.stream()
                .skip(pageNumber * pageSize)
                .limit(pageSize)
                .collect(Collectors.toList());
        App.printList(filteredList4);

        // 6-Collectors
        // GroupBy
        Map<Double, List<Product>> collect1 =  products.stream()
                                                        .filter(p -> p.getPrice() > 20)
                                                        .collect(Collectors.groupingBy(Product::getPrice));
        System.out.println("collect1 = " + collect1);

        // Counting
        Map<String, Long> collect2 =  products.stream()
                .collect(Collectors.groupingBy(
                        Product::getName, Collectors.counting()
                   )
                );
        System.out.println("collect2 = " + collect2);

        //Agrupando por nombre producto y sumando
        Map<String, Double> collect3 = products.stream()
                .collect(Collectors.groupingBy(
                        Product::getName, Collectors.summingDouble(Product::getPrice)
                ));
        System.out.println("collect3 = " + collect3);

        //Obteniendo suma y resumen
        DoubleSummaryStatistics statistics = products.stream()
                                                        .collect(Collectors.summarizingDouble(Product::getPrice));
        System.out.println("statistics = " + statistics);
        System.out.println("average price = " + statistics.getAverage());

        // 7- reduce
        Optional<Double> suma =  products.stream()
                .map(Product::getPrice)
                .reduce(Double::sum);
        System.out.println("suma.get() = " + suma.get());
    }

    public static int getAge(LocalDate dob){
        return Period.between(dob, LocalDate.now()).getYears();
    }

    public static void printList(List<?> list){
        list.forEach(System.out::println);
    }
}
