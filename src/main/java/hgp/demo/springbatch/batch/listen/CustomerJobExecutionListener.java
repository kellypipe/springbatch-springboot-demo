package hgp.demo.springbatch.batch.listen;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.StepExecution;
import org.springframework.stereotype.Component;

/**
 * 对整个job 开始和结束的监听
 * @creator:gp.huang
 * @since:2018-10-30 17:50
 */
public class CustomerJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        System.out.println("job before........");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        System.out.println("job after........");
    }

}
