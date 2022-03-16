package win.leizhang.java8example.juc.delay;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.DelayQueue;

/**
 * 模拟一个使用DelayQueue的场景
 * 这里模拟的是订单下达之后，如果一直都还没支付，也就是停留在创建状态的话，就将其改成取消状态。
 *
 * @author lanjerry
 * @date 2019/2/14 15:28
 */
public class TestDelayQueue {

    /**
     * 初始化延迟队列
     */
    static DelayQueue<Order> queue = new DelayQueue<>();

    public static void main(String[] args) throws InterruptedException {

        //加入订单DD2019021401
        producer("DD2019021401");

        //停顿5秒，方便测试效果
        Thread.sleep(5000);

        //加入订单DD2019021402
        producer("DD2019021402");

        //执行消费
        consumer();
    }

    /**
     * 生产者
     *
     * @param orderNo 订单编号
     * @author lanjerry
     * @date 2019/2/14 15:35
     */
    private static void producer(String orderNo) {
        Order order = new Order();
        order.setOrderNo(orderNo);
        order.setStatus("待付款");
        order.setCreatedTime(LocalDateTime.now());
        queue.add(order);
        System.out.println(String.format("时间：%s，订单：%s加入队列", order.getCreatedTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), order.getOrderNo()));
    }

    /**
     * 消费者
     *
     * @author lanjerry
     * @date 2019/2/14 15:36
     */
    private static void consumer() {
        try {
            while (true) {
                Order order = queue.take();
                order.setStatus("已取消");
                System.out.println(String.format("时间：%s，订单：%s已过期", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")), order.getOrderNo()));
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}