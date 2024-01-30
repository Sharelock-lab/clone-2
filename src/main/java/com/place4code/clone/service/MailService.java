package com.place4code.clone.service;

import com.place4code.clone.model.User;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.AllArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.thymeleaf.context.Context;
import org.thymeleaf.spring6.SpringTemplateEngine;

import java.util.Locale;

@AllArgsConstructor
@Service
public class MailService {

    private final SpringTemplateEngine templateEngine;

    private final JavaMailSender mailSender;


    public void sendEmail(final String to,
                          final String subject,
                          final String text) throws MessagingException {

        final MimeMessage mimeMessage = mailSender.createMimeMessage();
        final String ENCODING = "UTF-8";
        final MimeMessageHelper message = new MimeMessageHelper(mimeMessage, ENCODING);
        message.setTo(to);
        message.setFrom("tester@gmail.com");
        message.setSubject(subject);
        message.setText(text, true);
        mailSender.send(mimeMessage);
    }

    public void sendEmailFromTemplate(final User user,
                                      final String templateName,
                                      final String subject) throws MessagingException {
        final Context context = new Context(Locale.ENGLISH);
        context.setVariable("user", user);
        context.setVariable("url", "http://localhost:8080");
        final String content = templateEngine.process(templateName, context);
        sendEmail(user.getEmail(), subject, content);
    }

    public void sendActivationEmail(final User user) {
        try {
            sendEmailFromTemplate(user, "email/activation", "Aktywuj swoje konto");
        } catch (final MessagingException e) {
            throw new RuntimeException("Nie można wysłać wiadomości e-mail: " + e.getMessage());
        }
    }

    public void sendResetPasswordEMail(User user) {
        try {
            sendEmailFromTemplate(user, "email/reset", "Ustal nowe hasło");
        } catch (final MessagingException e) {
            throw new RuntimeException("Nie można wysłać wiadomości e-mail: " + e.getMessage());
        }
    }
}
