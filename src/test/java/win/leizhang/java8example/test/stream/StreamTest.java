package win.leizhang.java8example.test.stream;

import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 流
 * Created by zealous on 2018/8/1.
 */
public class StreamTest {

    public StreamTest() {
    }

    @Test
    public void testSimple() {

        //首先,创建一个1-6乱序的List
        List<Integer> lists = Arrays.asList(4, 3, 6, 1, 5, 2);
        // 数据
        lists.forEach(elem -> System.out.println("elem: " + elem));

        // 流，终端操作/读1次后，就不能用了
        Stream<Integer> stream = lists.stream();

        // 最小值
        Optional<Integer> min = stream.min(Integer::compareTo);
        if (min.isPresent()) {
            System.out.println("min=" + min.get());//1
        }

        // 最大值
        lists.stream().max(Integer::compareTo).ifPresent(System.out::println);//6
        // 总
        System.out.println("count= " + lists.stream().count());//6
        // 排序
        lists.stream().sorted().forEach(elem -> System.out.print(elem + " "));

        // 过滤
        System.out.println();
        System.out.print("只剩下>3的元素");
        lists.stream()
                .filter(elem -> elem > 3)
                .forEach(elem -> System.out.print(elem + " "));

        // 过滤2
        System.out.println();
        System.out.print("只剩下>0且<4的，要排序");
        lists.stream()
                .filter(elem -> elem > 0)
                .filter(elem -> elem < 4)
                .sorted(Comparator.reverseOrder())
                //.sorted()
                .forEach(elem -> System.out.print(elem + " "));

        // 再次看看数据
        System.out.println();
        lists.forEach(elem -> System.out.print(elem + " "));

        System.out.println("ok");
    }

    @Test
    public void testSimple2() {
        // 集合
        List<String> list1 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");
        List<Integer> list2 = Arrays.asList(3, 2, 2, 3, 7, 3, 5);

        // Collectors
        List<String> filterList1 = list1.stream().filter(str -> !str.isEmpty()).collect(Collectors.toList());
        String mergedString = list1.stream().filter(str -> !str.isEmpty()).collect(Collectors.joining(", "));

        // foreach, limit
        Random random = new Random();
        random.ints().limit(10).sorted().forEach(System.out::println);

        // map
        // 去重复，算平方数
        List<Integer> squaresList = list2.stream().map(i -> i * i).distinct().collect(Collectors.toList());

        // filter
        long count1 = list1.stream().filter(str -> str.isEmpty()).count();
        long count2 = list1.stream().filter(str -> str.length() == 2).count();

        // 统计
        IntSummaryStatistics stats = list2.stream().mapToInt((x) -> x).summaryStatistics();
        System.out.println("列表中最大的数 : " + stats.getMax());
        System.out.println("列表中最小的数 : " + stats.getMin());
        System.out.println("所有数之和 : " + stats.getSum());
        System.out.println("平均数 : " + stats.getAverage());

        System.out.println("ok");
    }

    @Test
    public void testOpt() {
        List<String> list1 = Arrays.asList("abc", "", "bc", "efg", "abcd", "", "jkl");

        // 并行流,parallel
        Stream<String> stream1 = list1.parallelStream();
        // 顺序流,
        Stream<String> stream2 = list1.stream().sequential();
        // 无序流,
        Stream<String> stream3 = list1.stream().unordered();

        // 流的转换，是否是并行流
        if (stream3.parallel().isParallel()) System.out.println("Yes, parallel");

        // 迭代器,普通
        Iterator<String> iter = list1.iterator();
/*
        while (iter.hasNext()) {
            System.out.println(iter.next());
        }
*/

        // 迭代器,并行
        Spliterator<String> iter2 = list1.stream().spliterator();
        // t1
        //while (iter2.tryAdvance(System.out::println)) ;
        // t2,分成2部分
        Spliterator<String> iter3 = iter2.trySplit();
        if (iter3 != null) {
            iter3.forEachRemaining(System.out::println);
        }
        System.out.println("---");
        iter2.forEachRemaining(System.out::println);


        System.out.println("ok");
    }

    @Test
    public void testParallel() {
        //List<Integer> list3 = Arrays.asList(1, 2, 3, 4, 5, 6);
        List<Integer> list3 = Arrays.asList(1, 2, 3);

        // 求和
        Integer sum1 = list3.stream().reduce(0, (a, b) -> a + b);
        System.out.println("普通流，和为:" + sum1);

        Optional<Integer> sum2 = list3.parallelStream().reduce((a, b) -> a + b);
        if (sum2.isPresent()) System.out.println("并行流，和为:" + sum2.get());

        // 积
        Optional<Integer> product1 = list3.stream().reduce((a, b) -> a * b);
        if (product1.isPresent()) System.out.println("普通流，积为:" + product1.get());

        // 一般来说，应用到并行流的任何操作都必须是符合缩减操作的三个约束条件，无状态，不干预，关联性！
        // 这就能确保在并行流上执行操作的结果和在顺序流上执行的结果是相同的。
        Integer productP1 = list3.parallelStream().reduce(1, (a, b) -> a * b);
        System.out.println("并行流，积1为:" + productP1);
        // 在reduce()的这个版本当中，accumulator被称为累加器，combiner被称为合成器，combiner定义的函数将accumulator提到的两个值合并起来。
        Integer productP2 = list3.parallelStream().reduce(1, (a, b) -> a * b, (a, b) -> a * b);
        System.out.println("并行流，积2为:" + productP2);
        Integer productP3 = list3.parallelStream().reduce(1, (a, b) -> a * (b * 2), (a, b) -> a * b);
        System.out.println("并行流，积3为:" + productP3);
        Integer productP4 = list3.parallelStream().reduce(1, (a, b) -> a * (b * 2), (a, b) -> a * (b * 2));
        System.out.println("并行流，积4为:" + productP4);
        // TODO reduce()部分，需要深入理解

        // 积3, 用映射来处理
        Stream<Integer> productP3Map = list3.parallelStream().map((a) -> a * 2);
        Integer productP3Map2 = productP3Map.reduce(1, (a, b) -> a * b);
        System.out.println("并行流，用映射，积3为:" + productP3Map2);

        System.out.println("ok");
    }

    @Test
    public void testWang() {
        List<HeroPlayerGold> lists = new ArrayList<>();
        lists.add(new HeroPlayerGold("盖伦", "RNG1", 100));
        lists.add(new HeroPlayerGold("诸葛亮", "RNG2", 300));
        lists.add(new HeroPlayerGold("露娜", "RNG3", 300));
        lists.add(new HeroPlayerGold("狄仁杰", "RNG4", 500));
        lists.add(new HeroPlayerGold("牛头", "RNG5", 500));

        //计算团队经济
        int teamMoney = lists.stream()
                .map(gamer -> new Gold(gamer.getGoldNum()))
                .mapToInt(Gold::getGoldNum)
                .reduce(0, (a, b) -> a + b);
        System.out.println("团队经济：" + teamMoney);

        //计算团队经济2
        double teamMoney2 = lists.stream()
                .map(gamer -> new Gold(gamer.getGoldNum()))
                .mapToDouble(Gold::getGoldNum)
                .reduce(0, (a, b) -> a + b);
        System.out.println("团队经济2：" + teamMoney2);

        //计算两个C位的经济和
        lists.stream().filter(gamer -> "RNG2".equals(gamer.getPlayer()) || "RNG4".equals(gamer.getPlayer()))
                .map(player -> new Gold(player.getGoldNum()))
                .mapToInt(Gold::getGoldNum)
                .reduce((a, b) -> a + b)
                .ifPresent(System.out::println);

        // 收集1
        List<HeroPlayerGold> playerGolds = lists.stream()
                .map(gamer -> new HeroPlayerGold(gamer.getPlayer(), gamer.getGoldNum()))
                .collect(Collectors.toList());
        playerGolds.forEach(System.out::println);

        // 收集2
        Set<Hero> playerGolds2 = lists.stream()
                .map(gamer -> new Hero(gamer.getHero()))
                .collect(Collectors.toSet());
        playerGolds2.forEach(System.out::println);

        System.out.println("---");

        // supplier, accumulator, combiner
        lists.stream().collect(
                () -> new HashSet<>()
                , (set, elem) -> set.add(elem)
                , (setA, setB) -> setA.add(setB)
        )
                .forEach(System.out::println);

        System.out.println("---");
        // 简化
        lists.stream().collect(
                HashSet::new
                , HashSet::add
                , HashSet::addAll
        )
                .forEach(System.out::println);

        System.out.println("ok");
    }

    @Test
    public void testFlatMap() {
        List<String> citys = Arrays.asList("GuangZhou ShangHai", "GuangZhou ShenZhen", "ShangHai ShenZhen", "BeiJing ShangHai", "GuangZhou BeiJing", "ShenZhen BeiJing");

        //note1
        citys.stream().map(mCities -> Arrays.stream(mCities.split(" ")))
                .forEach(System.out::println);
        System.out.println("---");

        //流里面的元素还是一个数组
        citys.stream().map(mCities -> Arrays.stream(mCities.split(" ")))
                .forEach(cities -> cities.forEach(city -> System.out.print(city + ", ")));
        System.out.println("---");

        //直接一个flatMap()就把数组合并到映射流里面了
        citys.stream().flatMap(mCities -> Arrays.stream(mCities.split(" ")))
                //.distinct()
                .forEach(city -> System.out.print(city + ", "));

    }

    @Test
    public void testGroupBy() {
        List<HeroPlayerGold> lists = new ArrayList<>();
        lists.add(new HeroPlayerGold("李白", "RNG1", 100));
        lists.add(new HeroPlayerGold("李白", "RNG1", 100));
        lists.add(new HeroPlayerGold("苏轼", "RNG2", 300));
        lists.add(new HeroPlayerGold("白居易", "RNG3", 500));
        lists.add(new HeroPlayerGold("苏轼", "RNG2", 300));
        lists.add(new HeroPlayerGold("李白", "RNG1", 100));

        Map<String, List<HeroPlayerGold>> map1 = lists.stream()
                .collect(Collectors.groupingBy(HeroPlayerGold::getHero));
        Map<String, IntSummaryStatistics> map2 = lists.stream()
                .collect(Collectors.groupingBy(HeroPlayerGold::getPlayer, Collectors.summarizingInt(HeroPlayerGold::getGoldNum)));

        System.out.println("ok");
    }

}

// 玩家使用的英雄以及当前获得的金币数
class HeroPlayerGold {
    private String hero;
    private String player;
    private int goldNum;

    public HeroPlayerGold(String player, int goldNum) {
        this.player = player;
        this.goldNum = goldNum;
    }

    public HeroPlayerGold(String hero, String player, int goldNum) {
        this.hero = hero;
        this.player = player;
        this.goldNum = goldNum;
    }

    public String getHero() {
        return hero;
    }

    public void setHero(String hero) {
        this.hero = hero;
    }

    public String getPlayer() {
        return player;
    }

    public void setPlayer(String player) {
        this.player = player;
    }

    public int getGoldNum() {
        return goldNum;
    }

    public void setGoldNum(int goldNum) {
        this.goldNum = goldNum;
    }

    @Override
    public String toString() {
        return "HeroPlayerGold{" +
                "hero='" + hero + '\'' +
                ", player='" + player + '\'' +
                ", goldNum=" + goldNum +
                '}';
    }
}

class Gold {
    private int goldNum;

    public Gold(int goldNum) {
        this.goldNum = goldNum;
    }

    public int getGoldNum() {
        return goldNum;
    }

}

class Hero {
    private String heroName;

    public Hero(String heroName) {
        this.heroName = heroName;
    }

    public String getHeroName() {
        return heroName;
    }

    public void setHeroName(String heroName) {
        this.heroName = heroName;
    }

    @Override
    public String toString() {
        return "Hero{" +
                "heroName='" + heroName + '\'' +
                '}';
    }
}