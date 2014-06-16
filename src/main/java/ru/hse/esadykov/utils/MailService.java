package ru.hse.esadykov.utils;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.task.TaskExecutor;
import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Service;

/**
 * Created with IntelliJ IDEA.
 * User: Gleb Kozhukhantsev
 * Date: 15.06.2014
 * Time: 1:33
 * To change this template use File | Settings | File Templates.
 */
@Service
public class MailService {

    public static String SENDER_EMAIL = "bugsmailbunny@gmail.com";

    @Autowired
    private MailSender mailSender;

    @Autowired
    private TaskExecutor taskExecutor;

    public void setTaskExecutor(TaskExecutor taskExecutor) {
        this.taskExecutor = taskExecutor;
    }

    public void setMailSender(MailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendMail(final String from, final String to, final String subject, final String msg) {
        taskExecutor.execute(new Runnable() {
            @Override
            public void run() {
                SimpleMailMessage message = new SimpleMailMessage();
                message.setFrom(from);
                message.setTo(to);
                message.setSubject(subject);
                message.setText(msg);
                mailSender.send(message);
            }
        });

    }
}
