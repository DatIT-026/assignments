/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.authsystem.infrastructure;

import com.google.api.client.googleapis.auth.oauth2.GoogleIdToken;
import com.google.api.client.googleapis.auth.oauth2.GoogleIdTokenVerifier;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.gson.GsonFactory;
import java.util.Collections;

/**
 *
 * @author datto
 */
public class GoogleOAuthUtil {
    // client id
    private static String GOOGLE_CLIENT_ID = "649509966409-tlqp0u67ggknss1qp5n2lmov9s1tmjl4.apps.googleusercontent.com";
    
    /**
     * Verify google id token and extract user info
     * 
     * @param idTokenString --> the id token from Google Sign-in
     * @Return GoogleUserInfo
     */
    
    public static GoogleUserInfo verifyGoogleToken(String idTokenString) {
        try {
            GoogleIdTokenVerifier verifier = new GoogleIdTokenVerifier.Builder(
                    new NetHttpTransport(), 
                    GsonFactory.getDefaultInstance() // JWT -> parse
            ).setAudience(Collections.singletonList(GOOGLE_CLIENT_ID)).build(); // verify google id token
            
            GoogleIdToken idToken = verifier.verify(idTokenString);
            
            if (idToken != null) {
                GoogleIdToken.Payload payload = idToken.getPayload();
                
                // extract user info
                String googleId = payload.getSubject();
                String email = payload.getEmail();
                boolean emailVerified = payload.getEmailVerified();
                
                String name = (String) payload.get("name");
                String pictureUrl = (String) payload.get("picture");
                String givenName = (String) payload.get("given_name");
                String familyName = (String) payload.get("family_name");
                
                return new GoogleUserInfo(googleId, email, emailVerified, name, pictureUrl, givenName, familyName);
            }
                    
            return null;
        } catch (Exception e) {
            System.err.println("Error verifying Google token: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    /**
     * Static nested cass to hold user info
     */
    public static class GoogleUserInfo {
        private String GoogleId;
        private String email;
        private boolean emailVerified;
        private String name;
        private String pictureUrl;
        private String givenName;
        private String familyName;

        public GoogleUserInfo(String GoogleId, String email, boolean emailVerified, String name, String pictureUrl, String givenName, String familyName) {
            this.GoogleId = GoogleId;
            this.email = email;
            this.emailVerified = emailVerified;
            this.name = name;
            this.pictureUrl = pictureUrl;
            this.givenName = givenName;
            this.familyName = familyName;
        }

        /**
         * @return the GoogleId
         */
        public String getGoogleId() {
            return GoogleId;
        }

        /**
         * @param GoogleId the GoogleId to set
         */
        public void setGoogleId(String GoogleId) {
            this.GoogleId = GoogleId;
        }

        /**
         * @return the email
         */
        public String getEmail() {
            return email;
        }

        /**
         * @param email the email to set
         */
        public void setEmail(String email) {
            this.email = email;
        }

        /**
         * @return the emailVerified
         */
        public boolean isEmailVerified() {
            return emailVerified;
        }

        /**
         * @param emailVerified the emailVerified to set
         */
        public void setEmailVerified(boolean emailVerified) {
            this.emailVerified = emailVerified;
        }

        /**
         * @return the name
         */
        public String getName() {
            return name;
        }

        /**
         * @param name the name to set
         */
        public void setName(String name) {
            this.name = name;
        }

        /**
         * @return the pictureUrl
         */
        public String getPictureUrl() {
            return pictureUrl;
        }

        /**
         * @param pictureUrl the pictureUrl to set
         */
        public void setPictureUrl(String pictureUrl) {
            this.pictureUrl = pictureUrl;
        }

        /**
         * @return the givenName
         */
        public String getGivenName() {
            return givenName;
        }

        /**
         * @param givenName the givenName to set
         */
        public void setGivenName(String givenName) {
            this.givenName = givenName;
        }

        /**
         * @return the familyName
         */
        public String getFamilyName() {
            return familyName;
        }

        /**
         * @param familyName the familyName to set
         */
        public void setFamilyName(String familyName) {
            this.familyName = familyName;
        }
    
        
    }
    
    
}
