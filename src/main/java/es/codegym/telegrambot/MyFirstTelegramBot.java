package es.codegym.telegrambot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;
import static es.codegym.telegrambot.TelegramBotContent.*;

import java.util.Map;

public class MyFirstTelegramBot extends MultiSessionTelegramBot {

    public static final String NAME = "Colocar nombre del bot";
    public static final String TOKEN = "Colocar token del bot";
    public static boolean state = false;
    public String user = "";

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
            sendTextMessageAsync(welcome(message), Map.of("Comenzar", "button_w"));
        }

        if (button.equals("button_w")) {
            sendPhotoMessageAsync("step_1_pic.webp");
            sendTextMessageAsync(STEP_1_TEXT, Map.of("Hackear la nevera", "step_button_1"));
        }

        if (button.equals("step_button_1")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_2_pic.jpg");
            sendTextMessageAsync(STEP_2_TEXT,
                    Map.of("¡Tomar una salchicha! +20 de fama", "step_button_2", "¡Tomar un pescado! +20 de fama",
                            "step_button_2", "¡Tirar una lata de pepinillos! +20 de fama", "step_button_2"));
        }

        if (button.equals("step_button_2")) {
            addUserGlory(20);
            sendPhotoMessageAsync("step_3_pic.jpg");
            sendTextMessageAsync(STEP_3_TEXT, Map.of("¡Hackear al robot aspiradora!", "step_button_3"));
        }

        if (button.equals("step_button_3")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_4_pic.jpg");
            sendTextMessageAsync(STEP_4_TEXT,
                    Map.of("Enviar al robot aspirador a por comida! +30 de fama", "step_button_4",
                            "Dar un paseo en el robot aspirador! +30 de fama", "step_button_4",
                            "¡Huir del robot aspirador! +30 de fama", "step_button_4"));
        }

        if (button.equals("step_button_4")) {
            addUserGlory(30);
            sendPhotoMessageAsync("step_5_pic.jpg");
            sendTextMessageAsync(STEP_5_TEXT, Map.of("Colocarse la GoPro +40 puntos de fama", "step_button_5"));
        }

        if (button.equals("step_button_5")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_6_pic.jpg");
            sendTextMessageAsync(STEP_6_TEXT, Map.of("Encender la GoPro +40 de fama", "step_button_6"));
        }

        if (button.equals("step_button_6")) {
            addUserGlory(40);
            sendPhotoMessageAsync("step_7_pic.jpg");
            sendTextMessageAsync(STEP_7_TEXT, Map.of("Hackear la computadora +50 de fama", "step_button_7"));
        }

        if (button.equals("step_button_7")) {
            addUserGlory(50);
            sendPhotoMessageAsync("step_8_pic.jpg");
            sendTextMessageAsync(STEP_8_TEXT, Map.of("Ver Final", "step_button_8"));
        }

        if (button.equals("step_button_8")) {
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
        String welcomeString = """
                    ¡Excelente!
                Usuario:ﾠ""" +
                user
                +
                """

                        Puntos:ﾠ""" +
                getUserGlory()
                +
                """

                        Necesitarás 280 puntos para ganar
                        """;
        return welcomeString;
    }

    public String goodbye() {
        String leaveString = """
                ¡FELICIDADESﾠ""" + user.toUpperCase() + """
                ﾠPOR COMPLETAR LA MISIÓN😻!

                Tienes un total deﾠ""" + getUserGlory() +
                """
                        ﾠpuntos
                        """;
        return leaveString;
    }

    public String getUser(String phrase) {
        String[] words = phrase.split(" ");
        int count = words.length;
        return words.length <= 4 ? words[count - 1] : words[count];
    }

    public static void main(String[] args) throws TelegramApiException {
        TelegramBotsApi telegramBotsApi = new TelegramBotsApi(DefaultBotSession.class);
        telegramBotsApi.registerBot(new MyFirstTelegramBot());
    }
}