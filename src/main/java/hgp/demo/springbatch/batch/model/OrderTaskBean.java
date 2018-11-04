package hgp.demo.springbatch.batch.model;


import hgp.demo.springbatch.batch.task.AbstractTask;
import hgp.demo.springbatch.batch.task.CustomItemProcessor;
import org.springframework.util.StringUtils;

/**
 * @author admin
 * @date 2018/9/6
 **/
public class OrderTaskBean implements Comparable<OrderTaskBean> {

    private Integer order;

    private String stepName;

    private AbstractTask task;

    private CustomItemProcessor processor;

    public OrderTaskBean(String stepName, Integer order, AbstractTask task,CustomItemProcessor processor) {
        this.order = order;
        this.task = task;
        this.processor = processor;
        this.stepName = StringUtils.isEmpty(stepName) ? "step" + this.order : stepName;
    }

    public void setStepName(String stepName) {
        this.stepName = stepName;
    }

    public CustomItemProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(CustomItemProcessor processor) {
        this.processor = processor;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }

    public String getStepName() {
        return this.stepName;
    }

    public AbstractTask getTask() {
        return task;
    }

    public void setTask(AbstractTask task) {
        this.task = task;
    }

    @Override
    public int hashCode() {
        return this.order.hashCode() + this.stepName.hashCode() + order;
    }

    @Override
    public int compareTo(OrderTaskBean o) {
        if(null == o){
            return 0;
        }
        if (this.order > o.order) {
            return 1;
        }else {
            return -1;
        }
    }
}
