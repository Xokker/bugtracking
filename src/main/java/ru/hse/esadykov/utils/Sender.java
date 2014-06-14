package ru.hse.esadykov.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb Kozhukhantsev
 * Date: 15.06.2014
 * Time: 2:03
 * To change this template use File | Settings | File Templates.
 */
public class Sender {
    @Autowired
    public TaskExecutor taskExecutor;

    public Sender(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void doSend(final MailService mailService, final int id, final String email) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                mailService.sendMail("bugsmailbunny@gmail.com", email, "Notification",
                        "Bug " + id + " was modified");
            }
        });
    }
}
