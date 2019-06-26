package hgp.demo.springbatch.batch.listen;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;

/**
 * 对每个step 的监听
 * @author:gp.huang
 * @since:2018-10-30 17:50
 */
public class CustomerStepExecutionListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        System.out.println("step before........");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        System.out.println("step after........");
        return null;
    }
}
