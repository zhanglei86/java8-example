package win.leizhang.java8example.juc.delay;

import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.util.concurrent.Delayed;
import java.util.concurrent.TimeUnit;

/**
 * 订单类，用于存放订单头信息
 *
 * @author lanjerry
 * @date 2019/2/14 10:52
 */
public class Order implements Delayed {

    /**
     * 单号
     */
    private String orderNo;

    /**
     * 状态
     */
    private String status;

    /**
     * 创建时间
     */
    private LocalDateTime createdTime;

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public LocalDateTime getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(LocalDateTime createdTime) {
        this.createdTime = createdTime;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    /**
     * 过期时间（单位为毫秒，这里表示10秒）
     */
    private static final long expireTime = 10000;

    @Override
    public long getDelay(TimeUnit unit) {
        //消息是否到期（是否可以被读取出来）判断的依据。当返回负数，说明消息已到期，此时消息就可以被读取出来了
        long result = unit.convert(this.createdTime.toInstant(ZoneOffset.of("+8")).toEpochMilli() + expireTime - System.currentTimeMillis(), TimeUnit.MILLISECONDS);
        return result;
    }

    @Override
    public int compareTo(Delayed o) {
        //这里根据取消时间来比较，如果取消时间小的，就会优先被队列提取出来
        int result = this.getCreatedTime().compareTo(((Order) o).getCreatedTime());
        return result;
    }
}