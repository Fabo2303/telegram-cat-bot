package org.example;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.methods.send.SendPhoto;
import org.telegram.telegrambots.meta.api.methods.send.SendVideo;
import org.telegram.telegrambots.meta.api.objects.InputFile;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultiSessionTelegramBot extends TelegramLongPollingBot {
    private static final Logger logger = LoggerFactory.getLogger(MultiSessionTelegramBot.class);
    private final String name;
    private final String token;

    private final ThreadLocal<Update> updateEvent = new ThreadLocal<>();
    private final HashMap<Long, Integer> gloryStorage = new HashMap<>();

    public MultiSessionTelegramBot(String name, String token) {
        this.name = name;
        this.token = token;
    }

    @Override
    public String getBotUsername() {
        return name;
    }

    @Override
    public String getBotToken() {
        return token;
    }

    @Override
    public final void onUpdateReceived(Update updateEvent) {
        this.updateEvent.set(updateEvent);
        onUpdateEventReceived(this.updateEvent.get());
        this.updateEvent.remove();
    }

    public void onUpdateEventReceived(Update updateEvent) {
        // do nothing
    }

    public Long getCurrentChatId() {
        if (updateEvent.get().hasMessage()) {
            return updateEvent.get().getMessage().getFrom().getId();
        }

        if (updateEvent.get().hasCallbackQuery()) {
            return updateEvent.get().getCallbackQuery().getFrom().getId();
        }

        return null;
    }

    public String getMessageText() {
        return updateEvent.get().hasMessage() ? updateEvent.get().getMessage().getText() : "";
    }

    public String getCallbackQueryButtonKey() {
        return updateEvent.get().hasCallbackQuery() ? updateEvent.get().getCallbackQuery().getData() : "";
    }

    public void sendTextMessageAsync(String text) {
        SendMessage message = createMessage(text);
        sendApiMethodAsync(message);
    }

    public void sendTextMessageAsync(String text, Map<String, String> buttons) {
        SendMessage message = createMessage(text, buttons);
        sendApiMethodAsync(message);
    }

    public void sendPhotoMessageAsync(String photoKey) {
        SendPhoto photo = createPhotoMessage(photoKey);
        executeAsync(photo);
    }

    public void sendVideoMessageAsync(String videoKey) {
        SendVideo video = createVideoMessage(videoKey);
        executeAsync(video);
    }

    public SendMessage createMessage(String text) {
        SendMessage message = new SendMessage();
        message.setText(text);
        message.setParseMode("markdown");
        Long chatId = getCurrentChatId();
        message.setChatId(chatId);
        return message;
    }

    public SendMessage createMessage(String text, Map<String, String> buttons) {
        SendMessage message = createMessage(text);
        if (buttons != null && !buttons.isEmpty())
            attachButtons(message, buttons);
        return message;
    }

    private void attachButtons(SendMessage message, Map<String, String> buttons) {
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> keyboard = new ArrayList<>();

        for (Map.Entry<String, String> buttonName : buttons.entrySet()) {
            String buttonValue = buttons.get(buttonName.getKey());

            InlineKeyboardButton button = new InlineKeyboardButton();
            button.setText(buttonName.getKey());
            button.setCallbackData(buttonValue);

            keyboard.add(List.of(button));
        }

        markup.setKeyboard(keyboard);
        message.setReplyMarkup(markup);
    }

    public SendPhoto createPhotoMessage(String name) {
        try {
            SendPhoto photo = new SendPhoto();
            InputFile inputFile = new InputFile();
            var is = ClassLoader.getSystemResourceAsStream("images/" + name);
            inputFile.setMedia(is, name);

            photo.setPhoto(inputFile);
            Long chatId = getCurrentChatId();
            photo.setChatId(chatId);
            return photo;
        } catch (Exception e) {
            logger.error("Error creating photo message", e);
        }
        return null;
    }

    public SendVideo createVideoMessage(String name) {
        try {
            SendVideo photo = new SendVideo();
            InputFile inputFile = new InputFile();
            var is = ClassLoader.getSystemResourceAsStream("images/" + name);
            inputFile.setMedia(is, name);

            photo.setVideo(inputFile);
            Long chatId = getCurrentChatId();
            photo.setChatId(chatId);
            return photo;
        } catch (Exception e) {
            logger.error("Error creating video message", e);
        }
        return null;
    }

    public void setUserGlory(int glories) {
        gloryStorage.put(getCurrentChatId(), glories);
    }

    public int getUserGlory() {
        return gloryStorage.getOrDefault(getCurrentChatId(), 0);
    }

    public void addUserGlory(int glories) {
        gloryStorage.put(getCurrentChatId(), getUserGlory() + glories);
    }
}
