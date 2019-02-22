package win.leizhang.java8example.test.lambda;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * 方法引用
 * Created by zealous on 2018/8/1.
 */
public class TestMethodQuote {

    @Test
    public void testBase() {
        List<String> names = Arrays.asList("Google", "Runoob", "Taobao", "Baidu", "Tencent");
        names.forEach(System.out::println);
    }

    @Test
    public void testQuote() {
        // 构造器引用
        final Car car = Car.create(Car::new);
        final List<Car> cars = Arrays.asList(car);

        cars.forEach(car::follow);
        cars.forEach(Car::collide);
        cars.forEach(Car::repair);

        // 特定对象的方法引用
        final Car police = Car.create(Car::new);
        cars.forEach(police::follow);

        System.out.println("ok");
    }

    /**
     * interface
     */
    @FunctionalInterface
    public interface Supplier<T> {
        T get();
    }


}


class Car {
    //Supplier是jdk1.8的接口，这里和lamda一起使用了
    public static Car create(final TestMethodQuote.Supplier<Car> supplier) {
        return supplier.get();
    }

    public static void collide(final Car car) {
        System.out.println("Collided " + car.toString());
    }

    public void follow(final Car another) {
        System.out.println("Following the " + another.toString());
    }

    public void repair() {
        System.out.println("Repaired " + this.toString());
    }
}
