package win.leizhang.java8example.test.stream;

import org.junit.Test;
import win.leizhang.java8example.bo.Dish;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

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
}
