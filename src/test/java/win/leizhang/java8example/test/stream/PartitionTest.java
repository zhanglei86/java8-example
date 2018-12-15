package win.leizhang.java8example.test.stream;

import org.junit.Test;
import win.leizhang.java8example.bo.Dish;
import win.leizhang.java8example.func.PrimeNumbersCollector;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.Consumer;
import java.util.stream.IntStream;

import static java.util.Comparator.comparingInt;
import static java.util.stream.Collectors.*;
import static win.leizhang.java8example.func.PrimeNumbersCollector.isPrime;

/**
 * Created by zealous on 2018/12/15.
 */
public class PartitionTest {

    @Test
    public void testPartitioning() {
        // Dishes partitioned by vegetarian
        Map<Boolean, List<Dish>> map1 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian));

        // Vegetarian Dishes by type
        Map<Boolean, Map<Dish.Type, List<Dish>>> map2 = Dish.menu.stream().collect(partitioningBy(Dish::isVegetarian, groupingBy(Dish::getType)));

        // Most caloric dishes by vegetarian
        Object o3 = Dish.menu.stream().collect(
                partitioningBy(Dish::isVegetarian, collectingAndThen(
                        maxBy(comparingInt(Dish::getCalories)),
                        Optional::get))
        );
    }

    @Test
    public void testPartitionPrimeNumbers() {
        System.out.println("Numbers partitioned in prime and non-prime");
        // 1.partitionPrimes
        Map<Boolean, List<Integer>> map1 = partitionPrimes(100);
        // 2.partitionPrimesWithCustomCollector
        Map<Boolean, List<Integer>> map2 = partitionPrimesWithCustomCollector(100);
    }

    @Test
    public void testCollectorHarness() {
        System.out.println("Partitioning done in");
        execute(PartitionTest::partitionPrimes);
        execute(PartitionTest::partitionPrimesWithCustomCollector);
    }

    private static Map<Boolean, List<Integer>> partitionPrimes(int n) {
        return IntStream.rangeClosed(2, n).boxed()
                .collect(partitioningBy(candidate -> isPrime(candidate)));
    }

    private static Map<Boolean, List<Integer>> partitionPrimesWithCustomCollector(int n) {
        return IntStream.rangeClosed(2, n).boxed().collect(new PrimeNumbersCollector());
    }

    private static long execute(Consumer<Integer> primePartitioner) {
        long fastest = Long.MAX_VALUE;
        for (int i = 0; i < 10; i++) {
            long start = System.nanoTime();
            primePartitioner.accept(1_000_000);
            long duration = (System.nanoTime() - start) / 1_000_000;
            if (duration < fastest) fastest = duration;
            System.out.println("done in " + duration);
        }
        return fastest;
    }
}
