package hgp.demo.springbatch.batch.config;


import hgp.demo.springbatch.batch.helper.OrderTaskHelper;
import hgp.demo.springbatch.batch.listen.CustomerStepExecutionListener;
import hgp.demo.springbatch.batch.model.OrderTaskBean;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.job.builder.SimpleJobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.*;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;

/**
 * @author admin
 * @date 2018/9/6
 **/
@Configuration
@EnableBatchProcessing
@ComponentScan(basePackageClasses = CustomBatchConfigurer.class)
public class SimpleBatchJobConfig implements SchedulingConfigurer {

    @Autowired
    public StepBuilderFactory stepBuilderFactory;
    @Autowired
    public JobBuilderFactory jobBuilderFactory;

    @Bean(destroyMethod = "shutdown")
    public ScheduledExecutorService taskExecutor() {
        return new ScheduledThreadPoolExecutor(20);
    }

    public List<Step> jobSteps(String jobName) {
        Optional<Set<OrderTaskBean>> sample = OrderTaskHelper.get(jobName);
        List<Step> list = new ArrayList<>();
        sample.orElseGet(TreeSet::new).stream().iterator().forEachRemaining(orderTaskBean -> {
            if(null != orderTaskBean.getTask()){
                list.add(stepBuilderFactory.get(orderTaskBean.getStepName())
                        .tasklet(orderTaskBean.getTask()).build());
            }else {
                list.add(stepBuilderFactory.get(orderTaskBean.getStepName())
                        .listener(new CustomerStepExecutionListener())
                        .chunk(10000)
                        .reader(orderTaskBean.getProcessor().getItemReader())
                        .processor(orderTaskBean.getProcessor())
                        .writer(orderTaskBean.getProcessor().getItemWriter())
                        .build());
            }
        });
        return list;
    }

    @Bean(name = "sampleJob")
    @Lazy
    public Job getSampleJob(Optional<String> jobName) throws Exception {
        return getJob(jobName.orElseGet(()-> "sampleJob"));
    }

    private Job getJob(String jobName) throws Exception {

        List<Step> steps = jobSteps(jobName);
        if (steps.isEmpty()) {
            throw new Exception("No found any steps");
        }
        SimpleJobBuilder jobBuilder = jobBuilderFactory.get(jobName)
                .incrementer(new RunIdIncrementer()).start(steps.get(0));

        if (steps.size() > 1) {
            for (int i = 1;i<steps.size();i++) {
                jobBuilder.next(steps.get(i));
            }
        }

        return jobBuilder.build();
    }

    @Override
    public void configureTasks(ScheduledTaskRegistrar scheduledTaskRegistrar) {
        scheduledTaskRegistrar.setScheduler(taskExecutor());
    }
}
