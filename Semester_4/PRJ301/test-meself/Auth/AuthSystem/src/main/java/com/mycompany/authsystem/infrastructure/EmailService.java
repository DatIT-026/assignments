/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.authsystem.infrastructure;

import java.util.Properties;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.servlet.ServletContext;

/**
 *
 * @author datto
 */
public class EmailService {

    // Email configuration
    private static final String SMTP_HOST = "smtp.gmail.com";
    private static final String SMTP_PORT = "587";
    private static final String EMAIL_USERNAME = "hahalike654@gmail.com";
    private static final String EMAIL_PASSWORD = "racuhkqfcbrjydvb";
    private static final String FORM_EMAIL = "hahalike654@gmail.com";
    private static final String FORM_NAME = "User Auth System";

    /**
     * Send verification email to new user
     */
    public static boolean sendVerificationEmail(String toEmail, String fullName, String verificationCode, ServletContext context) {
        try {
            String subject = "Welcome to User Authentication System";

            String htmlContent = EmailTemplateLoader.loadVerificationTemplate(fullName, verificationCode, context);
            return sendEmail(toEmail, subject, htmlContent);

        } catch (Exception e) {
            System.out.println("Errot sending verification email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }

    }

    /**
     * Send password reset email
     */
    /**
     * Send google welcome email
     */
    public static boolean sendSocialWelcomeEmail(
            String toEmail, String fullName, String SocialType, ServletContext context) {
        try {
            String subject = "Welcome to User Authentication System";

            String htmlContent = EmailTemplateLoader.loadGoogleTemplate(fullName, toEmail, SocialType, context);

            return sendEmail(toEmail, subject, htmlContent);
        } catch (Exception e) {
            System.out.println("Error sending welcome email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }

    /**
     * Core method to send email using JavaMail API
     */
    private static boolean sendEmail(String toEmail, String subject, String htmlContent) {
        try {
            // configuration SMTP properties
            Properties props = new Properties();
            props.put("mail.smtp.auth", "true");
            props.put("mail.smtp.starttls.enable", "true");
            props.put("mail.smtp.host", SMTP_HOST);
            props.put("mail.smtp.port", SMTP_PORT);
            props.put("mail.smtp.ssl.trust", SMTP_HOST);
            props.put("mail.smtp.ssl.protocols", "TLSv1.2");

            Session session = Session.getInstance(props, new Authenticator() {
                @Override
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(EMAIL_USERNAME, EMAIL_PASSWORD);
                }
            });

            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(FORM_EMAIL, FORM_NAME));
            message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(toEmail));
            message.setSubject(subject);
            message.setContent(htmlContent, "text/html; charset=UTF-8");

            Transport.send(message);

            return true;
        } catch (Exception e) {
            System.out.println("Failed to send email: " + e.getMessage());
            e.printStackTrace();
            return false;
        }
    }
}
