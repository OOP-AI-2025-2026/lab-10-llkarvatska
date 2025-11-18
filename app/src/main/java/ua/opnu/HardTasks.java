package ua.opnu;

import ua.opnu.util.Customer;
import ua.opnu.util.DataProvider;
import ua.opnu.util.Order;
import ua.opnu.util.Product;

import java.util.*;
import java.util.stream.Collectors;

public class HardTasks {

    private final List<Customer> customers = DataProvider.customers;
    private final List<Order> orders = DataProvider.orders;
    private final List<Product> products = DataProvider.products;

    public static void main(String[] args) {
        HardTasks tasks = new HardTasks();

        // Для перевірки виводу — раскоментуй потрібні рядки:

        // Завдання 1
        // Objects.requireNonNull(tasks.getBooksWithPrice()).forEach(System.out::println);

        // Завдання 2
        // Objects.requireNonNull(tasks.getOrdersWithBabyProducts()).forEach(System.out::println);

        // Завдання 3
        // Objects.requireNonNull(tasks.applyDiscountToToys()).forEach(System.out::println);

        // Завдання 4
        // System.out.println(tasks.getCheapestBook().get());

        // Завдання 5
        // Objects.requireNonNull(tasks.getRecentOrders()).forEach(System.out::println);

        // Завдання 6
        // DoubleSummaryStatistics stats = tasks.getBooksStats();
        // System.out.printf("count=%d, avg=%f, max=%f, min=%f, sum=%f\n",
        //        stats.getCount(), stats.getAverage(), stats.getMax(), stats.getMin(), stats.getSum());

        // Завдання 7
        // tasks.getOrdersProductsMap().forEach((id, count) -> System.out.println(id + " : " + count));

        // Завдання 8
        // tasks.getProductsByCategory().forEach((cat, list) -> System.out.println(cat + " : " + list));
    }

    //  ЗАВДАННЯ 1
    public List<Product> getBooksWithPrice() {
        return products.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.getCategory()))
                .filter(p -> p.getPrice() > 100)
                .toList();
    }

    //  ЗАВДАННЯ 2
    public List<Order> getOrdersWithBabyProducts() {
        return orders.stream()
                .filter(order -> order.getProducts().stream()
                        .anyMatch(p -> "Baby".equalsIgnoreCase(p.getCategory())))
                .toList();
    }

    //  ЗАВДАННЯ 3
    public List<Product> applyDiscountToToys() {
        return products.stream()
                .filter(p -> "Toys".equalsIgnoreCase(p.getCategory()))
                .peek(p -> p.setPrice(p.getPrice() * 0.5))
                .toList();
    }

    //  ЗАВДАННЯ 4
    public Optional<Product> getCheapestBook() {
        return products.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.getCategory()))
                .min(Comparator.comparing(Product::getPrice));
    }

    //  ЗАВДАННЯ 5
    public List<Order> getRecentOrders() {
        return orders.stream()
                .sorted(Comparator.comparing(Order::getOrderDate).reversed())
                .limit(3)
                .toList();
    }

    //  ЗАВДАННЯ 6
    public DoubleSummaryStatistics getBooksStats() {
        return products.stream()
                .filter(p -> "Books".equalsIgnoreCase(p.getCategory()))
                .collect(Collectors.summarizingDouble(Product::getPrice));
    }

    // ЗАВДАННЯ 7
    public Map<Integer, Integer> getOrdersProductsMap() {
        return orders.stream()
                .collect(Collectors.toMap(
                        Order::getId,
                        o -> o.getProducts().size()
                ));
    }

    //  ЗАВДАННЯ 8
    public Map<String, List<Integer>> getProductsByCategory() {
        return products.stream()
                .collect(Collectors.groupingBy(
                        Product::getCategory,
                        Collectors.mapping(Product::getId, Collectors.toList())
                ));
    }
}
