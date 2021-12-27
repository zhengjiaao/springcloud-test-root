/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-18 13:26
 * @Since:
 */
package com.zja.task.ram;

import org.springframework.cloud.task.listener.annotation.AfterTask;
import org.springframework.cloud.task.listener.annotation.BeforeTask;
import org.springframework.cloud.task.listener.annotation.FailedTask;
import org.springframework.cloud.task.repository.TaskExecution;
import org.springframework.stereotype.Component;

/**
 * 注解方式监听器
 */
@Component
public class TaskListener2 {

    /**
     * 在任务启动: @BeforeTask 在将 TaskExecution 存储到 TaskRepository 之前
     * @param taskExecution
     */
    @BeforeTask
    public void onTaskStartup(TaskExecution taskExecution) {
        System.out.println("TaskListener2 onTaskStartup");
        //设置退出消息 优先级 onTaskEnd > onTaskFailed > onTaskStartup , 但如果onTaskStartup没有退出，则ExitMessage会被onTaskEnd覆盖
        taskExecution.setExitMessage("onTaskStartup EXIT MESSAGE");
    }

    /**
     * 在任务结束时: @AfterTask 在 TaskRepository 中更新 TaskExecution 条目之前，标记任务的最终状态
     * @param taskExecution
     */
    @AfterTask
    public void onTaskEnd(TaskExecution taskExecution) {
        System.out.println("TaskListener2 onTaskEnd");
        taskExecution.setExitMessage("onTaskEnd EXIT MESSAGE");
    }

    /**
     * 任务失败: @FailedTask 在任务引发未处理的异常时调用@AfterTask 方法之前
     * @param taskExecution
     * @param throwable
     */
    @FailedTask
    public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
        System.out.println("TaskListener2 onTaskFailed");
        taskExecution.setExitMessage("onTaskStartup EXIT MESSAGE");
    }

}
