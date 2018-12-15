package win.leizhang.java8example.test.stream;

import org.junit.Test;
import win.leizhang.java8example.bo.Dish;
import win.leizhang.java8example.bo.Transaction2;

import java.util.*;
import java.util.function.BinaryOperator;

import static java.util.stream.Collectors.*;


/**
 * Created by zealous on 2018/12/15.
 */
public class Chap6Test {

    @Test
    public void testGrouping() {
        // 1.Dishes grouped by type
        Map<Dish.Type, List<Dish>> map1 = Dish.menu.stream().collect(groupingBy(Dish::getType));

        // 2.Dish names grouped by type
        Map<Dish.Type, List<String>> map2 = Dish.menu.stream().collect(groupingBy(Dish::getType, mapping(Dish::getName, toList())));

        // 3.Dish tags grouped by type
        //Map<Dish.Type, Set<String>> map3 = Dish.menu.stream().collect(groupingBy(Dish::getType, flatMapping(dish -> dishTags.get( dish.getName() ).stream(), toSet())));

        // 4.Caloric dishes grouped by type
        //Map<Dish.Type, List<Dish>> map4 = Dish.menu.stream().filter(dish -> dish.getCalories() > 500).collect(groupingBy(Dish::getType));
        //Map<Dish.Type, List<Dish>> map4 = Dish.menu.stream().collect(groupingBy(Dish::getType, filtering(dish -> dish.getCalories() > 500, toList())));

        // 5.Dishes grouped by caloric level
        Map<Dish.CaloricLevel, List<Dish>> map5 = Dish.menu.stream().collect(groupingBy(Chap6Test::whichCaloricLevel));

        // 6.Dishes grouped by type and caloric level
        Map<Dish.Type, Map<Dish.CaloricLevel, List<Dish>>> map6 = Dish.menu.stream().collect(groupingBy(Dish::getType, groupingBy(Chap6Test::whichCaloricLevel)));

        // 7.Count dishes in groups
        Map<Dish.Type, Long> map7 = Dish.menu.stream().collect(groupingBy(Dish::getType, counting()));

        // 8.Most caloric dishes by type
        Map<Dish.Type, Optional<Dish>> map8 = Dish.menu.stream().collect(
                groupingBy(Dish::getType, reducing((Dish d1, Dish d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2))
        );

        // 8.WithoutOpl
        Map<Dish.Type, Dish> map82 = Dish.menu.stream().collect(
                groupingBy(Dish::getType, collectingAndThen(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2), Optional::get))
        );

        // 9.Sum calories by type
        Map<Dish.Type, Integer> map9 = Dish.menu.stream().collect(groupingBy(Dish::getType, summingInt(Dish::getCalories)));

        // 10.Caloric levels by type
        Map<Dish.Type, Set<Dish.CaloricLevel>> map10 = Dish.menu.stream().collect(groupingBy(Dish::getType, mapping(Chap6Test::whichCaloricLevel, toSet())));
    }

    private static Dish.CaloricLevel whichCaloricLevel(Dish dish) {
        if (dish.getCalories() <= 400) return Dish.CaloricLevel.DIET;
        else if (dish.getCalories() <= 700) return Dish.CaloricLevel.NORMAL;
        else return Dish.CaloricLevel.FAT;
    }

    @Test
    public void testGroupingTxn2() {
        // groupImperatively
        Map<Transaction2.Currency, List<Transaction2>> transactionsByCurrencies = new HashMap<>();
        for (Transaction2 transaction : Transaction2.txns2) {
            Transaction2.Currency currency = transaction.getCurrency();
            List<Transaction2> transactionsForCurrency = transactionsByCurrencies.get(currency);
            if (transactionsForCurrency == null) {
                transactionsForCurrency = new ArrayList<>();
                transactionsByCurrencies.put(currency, transactionsForCurrency);
            }
            transactionsForCurrency.add(transaction);
        }
        System.out.println(transactionsByCurrencies);

        // groupFunctionally
        Map<Transaction2.Currency, List<Transaction2>> transactionsByCurrencies2 = Transaction2.txns2.stream().collect(groupingBy(Transaction2::getCurrency));
        System.out.println(transactionsByCurrencies2);
    }

    @Test
    public void testReducing() {
        System.out.println("Total calories in menu");
        // 1.calculateTotalCalories
        int i1 = Dish.menu.stream().collect(reducing(0, Dish::getCalories, (Integer i, Integer j) -> i + j));
        // 2.WithMethodReference
        int i2 = Dish.menu.stream().collect(reducing(0, Dish::getCalories, Integer::sum));
        // 3.WithoutCollectors
        int i3 = Dish.menu.stream().map(Dish::getCalories).reduce(Integer::sum).get();
        // 4.UsingSum
        int i4 = Dish.menu.stream().mapToInt(Dish::getCalories).sum();
    }

    @Test
    public void testSummarizing() {
        // 1.howManyDishes
        long l1 = Dish.menu.stream().collect(counting());
        // 2.findMostCaloricDish
        Dish dish2 = Dish.menu.stream().collect(reducing((d1, d2) -> d1.getCalories() > d2.getCalories() ? d1 : d2)).get();
        // 3.findMostCaloricDishUsingComparator
        Comparator<Dish> dishCaloriesComparator = Comparator.comparingInt(Dish::getCalories);
        BinaryOperator<Dish> moreCaloricOf = BinaryOperator.maxBy(dishCaloriesComparator);
        Dish dish3 = Dish.menu.stream().collect(reducing(moreCaloricOf)).get();
        // 4.calculateTotalCalories
        int i4 = Dish.menu.stream().collect(summingInt(Dish::getCalories));
        // 5.calculateAverageCalories
        Double d5 = Dish.menu.stream().collect(averagingInt(Dish::getCalories));
        // 6.calculateMenuStatistics
        IntSummaryStatistics i6 = Dish.menu.stream().collect(summarizingInt(Dish::getCalories));
        // 7.getShortMenu
        String str7 = Dish.menu.stream().map(Dish::getName).collect(joining());
        // 8.getShortMenuCommaSeparated
        String str8 = Dish.menu.stream().map(Dish::getName).collect(joining(", "));
    }
}
