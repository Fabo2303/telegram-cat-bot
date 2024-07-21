package org.example;

import io.github.cdimascio.dotenv.Dotenv;
import org.telegram.telegrambots.meta.api.objects.Update;

import static org.example.TelegramBotContent.*;

import java.util.Map;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public static final Dotenv dotenv = Dotenv.load();
    public static final String NAME = dotenv.get("NAME_BOT");
    public static final String TOKEN = dotenv.get("TOKEN_BOT");
    private boolean state = false;
    private String user = "";

    public MyFirstTelegramBot() {
        super(NAME, TOKEN);
    }

    @Override
    public void onUpdateEventReceived(Update update) {
        String message = getMessageText().toLowerCase();
        String button = getCallbackQueryButtonKey();
        if (message.equals("/start") && !state) {
            sendTextMessageAsync("Bienvenido ingrese su nombre de usuario");
            state = true;
        }

        if (state && (message.contains("nombre") || message.contains("llamo") || message.contains("soy"))) {
            setUserGlory(0);
            sendTextMessageAsync(welcome(message), Map.of("Comenzar", Buttons.BUTTON_W.name()));
        }

        if (button.equals(Buttons.BUTTON_W.name())) {
            sendPhotoMessageAsync("step_1_pic.webp");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Hackear la nevera", Buttons.STEP_BUTTON_1.name()));
        }

        if (button.equals(Buttons.STEP_BUTTON_1.name())) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic.jpg");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("¡Tomar una salchicha! +20 de fama", Buttons.STEP_BUTTON_2.name(), "¡Tomar un pescado! +20 de fama",
                            Buttons.STEP_BUTTON_2.name(), "¡Tirar una lata de pepinillos! +20 de fama", Buttons.STEP_BUTTON_2.name()));
        }

        if (button.equals(Buttons.STEP_BUTTON_2.name())) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic.jpg");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("¡Hackear al robot aspiradora!", Buttons.STEP_BUTTON_3.name()));
        }

        if (button.equals(Buttons.STEP_BUTTON_3.name())) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic.jpg");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of("Enviar al robot aspirador a por comida! +30 de fama", Buttons.STEP_BUTTON_4.name(),
                            "Dar un paseo en el robot aspirador! +30 de fama", Buttons.STEP_BUTTON_4.name(),
                            "¡Huir del robot aspirador! +30 de fama", Buttons.STEP_BUTTON_4.name()));
        }

        if (button.equals(Buttons.STEP_BUTTON_4.name())) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic.jpg");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Colocarse la GoPro +40 puntos de fama", Buttons.STEP_BUTTON_5.name()));
        }

        if (button.equals(Buttons.STEP_BUTTON_5.name())) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic.jpg");
            sendTextMessageAsync(STEP_6_TEXT, Map.of("Encender la GoPro +40 de fama", Buttons.STEP_BUTTON_6.name()));
        }

        if (button.equals(Buttons.STEP_BUTTON_6.name())) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic.jpg");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Hackear la computadora +50 de fama", Buttons.STEP_BUTTON_7.name()));
        }

        if (button.equals(Buttons.STEP_BUTTON_7.name())) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic.jpg");
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Ver Final", Buttons.STEP_BUTTON_8.name()));
        }

        if (button.equals(Buttons.STEP_BUTTON_8.name())) {
            addUserGlory(50);
            setUserGlory(280);
            sendPhotoMessageAsync("final_pic.jpg");
            sendTextMessageAsync(FINAL_TEXT);
            sendTextMessageAsync(goodbye());
        }
    }

    public String welcome(String message) {
        user = getUser(message);
        user = user.replace(user.charAt(0), (char) (user.charAt(0) - 32));
        return "¡Excelente!\n Usuario: " + user + "\nPuntos: " + getUserGlory() + "\nNecesitarás 280 puntos para ganar";
    }

    public String goodbye() {
        return "¡FELICIDADES " + user.toUpperCase() + " POR COMPLETAR LA MISIÓN 😻!\nTienes un total de " + getUserGlory() +" puntos ";
    }

    public String getUser(String phrase) {
        String[] words = phrase.split(" ");
        int count = words.length;
        return words.length <= 4 ? words[count - 1] : words[count];
    }
}