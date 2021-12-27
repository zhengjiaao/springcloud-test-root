/**
 * @Company: 上海数慧系统技术有限公司
 * @Department: 数据中心
 * @Author: 郑家骜[ào]
 * @Email: zhengja@dist.com.cn
 * @Date: 2021-11-18 12:55
 * @Since:
 */
package com.zja.task.jpa;

import org.springframework.cloud.task.listener.TaskExecutionListener;
import org.springframework.cloud.task.repository.TaskExecution;

/**
 * 实现 TaskExecutionListener方式监听
 */
//@Component // 开启 single-instance-enabled: true 需要注释掉
public class TaskListener implements TaskExecutionListener {

    /**
     * 在任务启动: 在将 TaskExecution 存储到 TaskRepository 之前
     * @param taskExecution
     */
    @Override
    public void onTaskStartup(TaskExecution taskExecution) {
        System.out.println("onTaskStartup");
    }

    /**
     * 在任务结束时: 在更新 TaskRepository 中的 TaskExecution 条目并标记任务的最终状态之前
     * @param taskExecution
     */
    @Override
    public void onTaskEnd(TaskExecution taskExecution) {
        System.out.println("onTaskEnd");
    }

    /**
     * 任务失败: 在任务抛出未处理的异常时调用 onTaskEnd 方法之前
     * @param taskExecution
     * @param throwable
     */
    @Override
    public void onTaskFailed(TaskExecution taskExecution, Throwable throwable) {
        System.out.println("onTaskFailed");
    }
}
