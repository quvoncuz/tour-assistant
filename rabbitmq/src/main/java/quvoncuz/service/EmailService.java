package quvoncuz.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@RequiredArgsConstructor
public class EmailService {

    private final JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    public String from;

    @Async
    public void send(String mail, String subject, String text){
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(mail);
            message.setSubject(subject);
            message.setText(text);
            message.setFrom(from);
            mailSender.send(message);
        } catch (Exception e){
            log.error("Email yuborishda xato: {}", e.getMessage());
            throw new RuntimeException("Email yuborib bo'lmadi");
        }
    }

    public void sendToMany(List<String> emails, String subject, String text) {
        emails.forEach(email -> send(email, subject, text));
    }

}
