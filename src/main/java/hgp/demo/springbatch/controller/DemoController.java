package hgp.demo.springbatch.controller;

import hgp.demo.springbatch.task.TaskService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/v1/demo")
@Api(value = "demo Controller", description = "demo Controller")
public class DemoController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/task")
    @ApiOperation(value = "demo")
    public String schedulerStart(@RequestParam("processName") String processName) {
        taskService.process();
        return "开始同步数据";
    }
}
