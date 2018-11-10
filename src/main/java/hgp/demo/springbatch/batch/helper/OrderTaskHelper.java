package hgp.demo.springbatch.batch.helper;


import hgp.demo.springbatch.batch.model.OrderTaskBean;

import java.util.*;

/**
 * 在springboot 启动时 分别保存每个任务下的所有step.
 */
public class OrderTaskHelper {

    private static Map<String, Set<OrderTaskBean>> orderTasks = new HashMap<>();

    public static void put(String orderName,OrderTaskBean orderTaskBean) {
        Set<OrderTaskBean> orderTaskBeans = orderTasks.get(orderName);
        Set<OrderTaskBean> beans = Optional.ofNullable(orderTaskBeans).orElseGet(TreeSet::new);
        beans.add(orderTaskBean);
        orderTasks.put(orderName, beans);
    }

    public static Optional<Set<OrderTaskBean>> get(String orderName) {
        return Optional.ofNullable(orderTasks.get(orderName));
    }

    public static Map<String, Set<OrderTaskBean>> get() {
        return orderTasks;
    }

    public static Optional<OrderTaskBean> getOrderTask(String orderName,String stepName,Integer order) {
        return Optional.ofNullable(orderTasks.get(orderName)).orElseGet(TreeSet::new).stream().
                filter(orderTaskBean -> orderTaskBean.getStepName().equals(stepName) && orderTaskBean.getOrder().equals(order)).findFirst();
    }
}
