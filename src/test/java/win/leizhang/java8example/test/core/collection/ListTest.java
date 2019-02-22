package win.leizhang.java8example.test.core.collection;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by zealous on 2019-02-22.
 */
public class ListTest {

    @Test
    public void testAll() {
        List<String> list1 = new ArrayList<>();
        list1.add("1111");
        list1.add("2222");
        list1.add("3333");

        List<String> list2 = new ArrayList<>();
        list2.add("3333");
        list2.add("4444");
        list2.add("5555");

        // 并集
        //list1.addAll(list2);
        // 交集
        list1.retainAll(list2);
        // 差集
        //list1.removeAll(list2);
        // 无重复并集
        //list2.removeAll(list1);
        //list1.addAll(list2);

        System.out.println("ok");
    }

    @Test
    public void testItem() {
        List<String> list1 = new ArrayList<>();
        list1.add("A");
        list1.add("B");
        list1.add("C");
        list1.add("D");
        list1.forEach(System.out::println);

        // 过滤
        List list2 = list1.stream()
                .filter(s -> s.contains("B") || s.contains("C"))
                .collect(Collectors.toList());

        // 遍历1
        for (String item : list1) {
            System.out.println("item1 :" + item);
        }

        // 遍历2
        list1.forEach(item -> System.out.println("item2 :" + item));

        // 遍历3
        Iterator<String> it = list1.iterator();
        while (it.hasNext()) {
            System.out.println("item3 :" + it.next());
        }

        // 遍历4
        for (int i = 0; i < list1.size(); i++) {
            System.out.println("item" + i + ":" + list1.get(i));
        }

        System.out.println("ok");
    }

}
