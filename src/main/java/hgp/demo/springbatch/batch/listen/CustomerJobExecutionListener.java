package hgp.demo.springbatch.batch.listen;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * 对整个job 开始和结束的监听
 * @author:gp.huang
 * @since:2018-10-30 17:50
 */
public class CustomerJobExecutionListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        //TODO do something before job
        System.out.println("job before........");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        //TODO do something after job
        System.out.println("job after........");
    }

}
