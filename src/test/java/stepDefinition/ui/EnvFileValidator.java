package stepDefinition.ui;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class EnvFileValidator {

    public static boolean isEnvFileValid() {
        String filePath = ".env";
        boolean usernameFound = false;
        boolean passwordFound = false;

        try (BufferedReader reader = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = reader.readLine()) != null) {
                // Check for the presence of APP_USERNAME and APP_PASSWORD, and ensure they are not empty
                if (line.startsWith("SAUCE_USERNAME=") && !line.split("=")[1].trim().isEmpty()) {
                    usernameFound = true;
                }
                if (line.startsWith("SAUCE_PASSWORD=") && !line.split("=")[1].trim().isEmpty()) {
                    passwordFound = true;
                }
            }
        } catch (IOException e) {
            System.err.println("Error reading .env file: " + e.getMessage());
            return false;
        }

        return usernameFound && passwordFound;
    }
}
