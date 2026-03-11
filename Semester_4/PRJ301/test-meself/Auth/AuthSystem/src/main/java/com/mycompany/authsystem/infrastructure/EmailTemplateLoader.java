/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.authsystem.infrastructure;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.ServletContext;

/**
 *
 * @author datto
 */
public class EmailTemplateLoader {
    private static final String VERIFICATION_TEMPLATE = "/email-templates/verification-email.html";
    private static final String GOOGLE_WELCOME_MAIL_PAGE = "/email-templates/google_welcome.html";
    
    public static String loadVerificationTemplate(
            String fullName, String verficationCode, ServletContext context) throws IOException {
        String template = loadTemplate(VERIFICATION_TEMPLATE, context);
        
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("{{FULL_NAME}}", fullName);
        placeholders.put("{{VERIFICATION_CODE}}", verficationCode);
        
        return replacePlaceholder(template, placeholders);
    }

    public static String loadGoogleTemplate(
            String fullName, String email, String accountType, ServletContext context) throws IOException {
        String template = loadTemplate(GOOGLE_WELCOME_MAIL_PAGE, context);
        
        Map<String, String> placeholders = new HashMap<>();
        placeholders.put("{{FULL_NAME}}", fullName);
        placeholders.put("{{EMAIl}}", email);
        placeholders.put("{{ACCOUNT}}", accountType);
        
        return replacePlaceholder(template, placeholders);
    }
    
    private static String loadTemplate(String templatePath, ServletContext context)
            throws IOException {
        StringBuilder content = new StringBuilder();

        InputStream inputStream = context.getResourceAsStream(templatePath);

        if (inputStream != null) {
            try ( BufferedReader br = new BufferedReader(
                    new InputStreamReader(inputStream, StandardCharsets.UTF_8))) {
                String line;
                while ((line = br.readLine()) != null) {
                    content.append(line).append("\n");
                }
            }
        } else {
            System.out.println("Template file not found: " + templatePath);
            return getDefaultTemplate();
        }

        return content.toString();
    }
    
    private static String replacePlaceholder(String template, Map<String, String> placeholders) {
        String result = template;
        
        for (Map.Entry<String, String> entry : placeholders.entrySet()) {
            result = result.replace(entry.getKey(), entry.getValue());
        }
        
        return result;
    }

    private static String getDefaultTemplate() {
        return "<!DOCTYPE html>"
                + "<html lang='en'>"
                + "  <head>"
                + "    <meta charset='UTF-8' />"
                + "    <link rel='stylesheet' href='style.css' />"
                + "    <title>Email</title>"
                + "  </head>"
                + "  <body>"
                + "  <h1>User Authentication System</h1>"
                + "  <p>This is a notification email.</p>"
                + "  </body>"
                + "</html>";
    }
}
