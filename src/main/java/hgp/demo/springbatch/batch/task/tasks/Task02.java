package hgp.demo.springbatch.batch.task.tasks;

import hgp.demo.springbatch.batch.annotation.OrderTask;
import hgp.demo.springbatch.batch.task.AbstractTask;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;

/**
 * Task02
 * @author:gp.huang
 * @since:2018-10-30 17:50
 */
@Component
@OrderTask(jobName = "sampleJob",stepName = "tasklet02",order = 2)
public class Task02 extends AbstractTask {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("tasklet02 开始执行任务...........");
        return RepeatStatus.FINISHED;
    }
}
