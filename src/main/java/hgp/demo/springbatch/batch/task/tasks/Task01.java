package hgp.demo.springbatch.batch.task.tasks;

import hgp.demo.springbatch.batch.annotation.OrderTask;
import hgp.demo.springbatch.batch.task.AbstractTask;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;


@Component
@OrderTask(jobName = "sampleJob",stepName = "tasklet01",order = 1)
public class Task01 extends AbstractTask {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("tasklet01 开始执行任务...........");
        return RepeatStatus.FINISHED;
    }
}
