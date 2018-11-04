package hgp.demo.springbatch.batch.task;


import hgp.demo.springbatch.batch.annotation.OrderProcessor;
import hgp.demo.springbatch.batch.helper.OrderTaskHelper;
import hgp.demo.springbatch.batch.model.OrderTaskBean;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.annotation.Annotation;

public abstract class CustomItemProcessor<O, T> implements ItemProcessor<O, T>, ApplicationContextAware {
    private ItemReader itemReader;

    private ItemWriter itemWriter;

    public abstract ItemReader getItemReader();

    public abstract ItemWriter getItemWriter();

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        Annotation[] annotations = this.getClass().getAnnotations();
        for (Annotation annotation: annotations) {
            if(annotation instanceof OrderProcessor){
                OrderProcessor orderProcessor = (OrderProcessor) annotation;
                OrderTaskBean orderTaskBean = new OrderTaskBean(orderProcessor.stepName(), orderProcessor.order(), null,this);
                OrderTaskHelper.put(orderProcessor.jobName(),orderTaskBean);
            }
        }
    }
}
