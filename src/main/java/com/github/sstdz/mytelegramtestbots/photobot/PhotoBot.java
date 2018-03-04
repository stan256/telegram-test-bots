package com.github.sstdz.mytelegramtestbots.photobot;

import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.methods.send.SendPhoto;
import org.telegram.telegrambots.api.objects.PhotoSize;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardRemove;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class PhotoBot extends TelegramLongPollingBot {
    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String messageText = update.getMessage().getText();
            long chatId = update.getMessage().getChatId();
            System.out.println(messageText);
            System.out.println(messageText.equals("Get photo "));

            if (messageText.equals("/start")) {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText(messageText);
                buildKeyboard(chatId);

                SendPhoto msg = new SendPhoto()
                        .setChatId(chatId)
                        .setPhoto("AgADAgADY6gxG52T4UhqhvL7uSwkryEDMw4ABFuMS3fgKXMHELgEAAEC")
                        .setCaption("Photo");


                try {
                    sendMessage(message);
                    sendPhoto(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (messageText.equals("/pic")) {
                SendPhoto msg = new SendPhoto()
                        .setChatId(chatId)
                        .setPhoto("AgADAgADY6gxG52T4UhqhvL7uSwkryEDMw4ABFuMS3fgKXMHELgEAAEC")
                        .setCaption("Photo");
                try {
                    sendPhoto(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (messageText.equals("Get photo")) {
                SendPhoto msg = new SendPhoto()
                        .setChatId(chatId)
                        .setPhoto("AgADAgADY6gxG52T4UhqhvL7uSwkryEDMw4ABFuMS3fgKXMHELgEAAEC")
                        .setCaption("Photo");

                try {
                    sendPhoto(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else if (messageText.equals("/hide")) {
                SendMessage msg = new SendMessage()
                        .setChatId(chatId)
                        .setText("Keyboard hidden");
                ReplyKeyboardRemove keyboardMarkup = new ReplyKeyboardRemove();
                msg.setReplyMarkup(keyboardMarkup);
                try {
                    sendMessage(msg);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            } else {
                SendMessage message = new SendMessage()
                        .setChatId(chatId)
                        .setText("Unknown command");
                try {
                    sendMessage(message);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        } else if (update.hasMessage() && update.getMessage().hasPhoto()) {
            long chatId = update.getMessage().getChatId();
            List<PhotoSize> photos = update.getMessage().getPhoto();
            PhotoSize max = photos.stream().max(Comparator.comparing(PhotoSize::getFileSize)).orElse(null);

            String fileId = max.getFileId();
            int width = max.getWidth();
            int height = max.getHeight();

            String caption = "file_id: " + fileId + "\nwidth: " + Integer.toString(width) + "\nheight: " + Integer.toString(height);
            SendPhoto msg = new SendPhoto()
                    .setChatId(chatId)
                    .setPhoto(fileId)
                    .setCaption(caption);
            try {
                sendPhoto(msg);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void buildKeyboard(long chatId) {
        SendMessage message = new SendMessage()
                .setChatId(chatId)
                .setText("Here is your keyboard");
        ReplyKeyboardMarkup keyboardMarkup = new ReplyKeyboardMarkup();
        List<KeyboardRow> keyboard = new ArrayList<>();
        KeyboardRow row = new KeyboardRow();
        row.add("Get photo");
        keyboard.add(row);
        keyboardMarkup.setKeyboard(keyboard);
        message.setReplyMarkup(keyboardMarkup);
        try {
            sendMessage(message);
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getBotUsername() {
        return "SStdzPhotoBot";
    }

    @Override
    public String getBotToken() {
        return "522118548:AAEw-liFWtyx2sK8l1-M3nvyfEcIpiOdjAQ";
    }
}
