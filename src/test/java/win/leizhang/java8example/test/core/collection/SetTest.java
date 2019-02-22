package win.leizhang.java8example.test.core.collection;

import com.google.common.collect.Sets;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.collections4.Predicate;
import org.apache.commons.collections4.SetUtils;
import org.apache.commons.collections4.functors.TruePredicate;
import org.apache.commons.lang3.tuple.Pair;
import org.junit.Test;

import java.util.*;
import java.util.stream.Collectors;

import static org.junit.Assert.assertTrue;


/**
 * Created by zealous on 2019-02-22.
 */
public class SetTest {

    private static Set<String> st1 = new HashSet<>();
    private static Set<String> st2 = new HashSet<>();

    static {
        st1.add("a");
        st1.add("b");
        st1.add("c");
        st1.add("d");

        st2.add("a");
        st2.add("d");
    }

    @Test
    public void testAll() {
        Set<String> st3 = new HashSet<>();
        Set<String> st4 = null;

        // 是否。。
        // 并
        boolean b1 = st1.addAll(st2);
        // 差
        boolean b2 = st1.removeAll(st2);
        // 交
        boolean b3 = st1.retainAll(st2);//st3,st4

        System.out.println("ok");
    }

    @Test
    public void test2() {
        // guava方式
        //交
        Set<String> intersectionSet1 = Sets.intersection(st1, st2);
        //差
        Set<String> subtractSet1 = Sets.difference(st1, st2);
        //并
        Set<String> unionSet1 = Sets.union(st1, st2);

        // common方式
        //交
        Collection<String> intersectionSet2 = CollectionUtils.intersection(st1, st2);
        //差
        Collection<String> subtractSet2 = CollectionUtils.subtract(st1, st2);
        //并
        Collection<String> unionSet2 = CollectionUtils.union(st1, st2);

        System.out.println("ok");
    }

    // 对称差，通常表示为AΔB, 或A⊕B.
    @Test
    public void test3() {
        // 例如：集合{1,2,3}和{3,4}的对称差为{1,2,4}。
/*
        // guava方式
        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 3, 4));
        Set<Integer> b = new HashSet<>(Arrays.asList(3, 4, 5, 6));
        Sets.SetView<Integer> result = Sets.symmetricDifference(a,b);
        System.out.println(result);
*/

        // common方式
        Set<Integer> a = new HashSet<>(Arrays.asList(1, 2, 5));
        Set<Integer> b = new HashSet<>(Arrays.asList(1, 2, 3));
        SetUtils.SetView<Integer> result = SetUtils.disjunction(a, b);
        assertTrue(result.toSet().contains(5) && result.toSet().contains(3));
    }

    // 对称差2
    // 上述的两个方法都不能标注哪些元素属于第一个集合，哪个属于第二个集合
    @Test
    public void test4() {
        final List<String> first = Arrays.asList("bbb", "ccc", "dddd", "aaa");
        final List<String> second = Arrays.asList("aaa", "zzz", "ccc");
        System.out.println(disjunction2(first, second, TruePredicate.truePredicate()));
    }

    /**
     * 模仿collection4中的方法改进的
     *
     * @param first  1
     * @param second 2
     * @param p      3
     * @return
     */
    private static <O> Pair<Collection<O>, Collection<O>> disjunction2(final Collection<? extends O> first,
                                                                       final Collection<? extends O> second,
                                                                       final Predicate<O> p) {
        final List<O> firstList = first.stream()
                .filter(e -> p.evaluate(e))
                .collect(Collectors.toList());

        final List<O> secondList = second.stream()
                .filter(e -> !firstList.remove(e))
                .collect(Collectors.toList());
        return Pair.of(firstList, secondList);
    }
}
