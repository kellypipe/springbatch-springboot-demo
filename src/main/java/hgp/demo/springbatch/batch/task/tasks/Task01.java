package hgp.demo.springbatch.batch.task.tasks;

import hgp.demo.springbatch.batch.annotation.OrderTask;
import hgp.demo.springbatch.batch.task.AbstractTask;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.stereotype.Component;


/**
 * springbatch 另外提供了 Tasklet 接口：不用很明显的按 读取数据，处理数据，写入数据这3个步骤执行；可以将所有的逻辑都包含在 Tasklet 中；
 * @author:gp.huang
 * @since:2018-10-30 17:50
 */
@Component
@OrderTask(jobName = "sampleJob",stepName = "tasklet01",order = 1)
public class Task01 extends AbstractTask {
    @Override
    public RepeatStatus execute(StepContribution stepContribution, ChunkContext chunkContext) throws Exception {
        System.out.println("tasklet01 开始执行任务...........");
        //处理自己的业务逻辑
        return RepeatStatus.FINISHED;
    }
}
