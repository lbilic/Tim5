package ftn.isamrs.tim5.service;

import ftn.isamrs.tim5.model.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.mail.javamail.JavaMailSender;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Random;

@Service
public class EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private Environment env;

    private static final String URL = "http://localhost:8080/api/activate";//?id_for_activation=";
    private static final String EMAIL_USERNAME = "spring.mail.username";

    @Async
    public void sendActivationMail(Account account) throws MailException, InterruptedException, MessagingException {
        String activationString = generateActivationString();
        account.setActivationId(activationString);

        MimeMessage message = javaMailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setTo(account.getEmail());
        helper.setFrom(env.getProperty(EMAIL_USERNAME));
        helper.setSubject("Account confirmation");
        helper.setText("<html><body>Dear " + account.getName() + ",\nYou can activate your account by clicking on the link below\n"
                        + "<a href='http://localhost:8080/api/activate?activationId=" + activationString + "' id='id_link'> link </a></body></html>", true);
        javaMailSender.send(message);
    }

    private String generateActivationString() {
        String SALTCHARS = "ABCDEFGHIJKLMNOPQRSTUVWXYZ1234567890";
        StringBuilder salt = new StringBuilder();
        Random rnd = new Random();
        while (salt.length() < 18) {
            int index = (int) (rnd.nextFloat() * SALTCHARS.length());
            salt.append(SALTCHARS.charAt(index));
        }
        String saltStr = salt.toString();
        return saltStr;
    }
}
