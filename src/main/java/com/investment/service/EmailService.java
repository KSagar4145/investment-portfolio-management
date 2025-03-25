package com.investment.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.MailException;
import org.springframework.mail.MailSendException;
import org.springframework.mail.MailSender;
import org.springframework.stereotype.Service;

import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;

@Service
public class EmailService {



    private JavaMailSender javaMailSender;

	public void sendOtpVerificationEmail(String email, String otp) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, "utf-8");

            String subject ="Verify OTP";
            String text ="Your Verification code is : "+otp;
            
          
            mimeMessageHelper.setSubject(subject);
            mimeMessageHelper.setText(text);
            mimeMessageHelper.setFrom("your-email@gmail.com");
            mimeMessageHelper.setTo(email);

            javaMailSender.send(mimeMessage);
        } catch (MailException | MessagingException e) {
            throw new MailSendException(e.getMessage());
        }
    }
}
