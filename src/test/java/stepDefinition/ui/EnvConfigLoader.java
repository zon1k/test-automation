package stepDefinition.ui;

import io.github.cdimascio.dotenv.Dotenv;

public class EnvConfigLoader {
    private static final Dotenv dotenv = Dotenv.load();

    public static String getUsername() {
        return dotenv.get("SAUCE_USERNAME");
    }

    public static String getPassword() {
        return dotenv.get("SAUCE_PASSWORD");
    }
}
