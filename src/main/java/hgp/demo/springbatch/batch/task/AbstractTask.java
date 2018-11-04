package hgp.demo.springbatch.batch.task;

import hgp.demo.springbatch.batch.annotation.OrderTask;
import hgp.demo.springbatch.batch.helper.OrderTaskHelper;
import hgp.demo.springbatch.batch.model.OrderTaskBean;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;

/**
 * @author admin
 * @date 2018/9/6
 **/
public abstract class AbstractTask implements ApplicationContextAware,Tasklet {

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Annotation[] annotations = this.getClass().getAnnotations();
        for (Annotation annotation: annotations) {
            if (annotation instanceof OrderTask) {
                OrderTask orderTask = (OrderTask) annotation;
                OrderTaskBean orderTaskBean = new OrderTaskBean(orderTask.stepName(), orderTask.order(), this,null);
                OrderTaskHelper.put(orderTask.jobName(),orderTaskBean);
            }
        }


    }
}
