package fr.chatop.portail.configuration;

public class Constants {

    public static final String UPLOAD_URL = "http://localhost:3001/uploads/";
    public static final String[] WHITE_URLS = {
            "/uploads/**",
            "/api/auth/login",
            "/api/auth/register",
            "/v3/api-docs*/**",
            "/swagger-ui*/**"
    };

}
