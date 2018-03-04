package com.github.sstdz.mytelegramtestbots.emojibot;

import com.vdurmont.emoji.EmojiParser;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.exceptions.TelegramApiException;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class EmojiBot extends TelegramLongPollingBot {

    @Override
    public void onUpdateReceived(Update update) {
        if (update.hasMessage() && update.getMessage().hasText()) {
            String firstName = update.getMessage().getChat().getFirstName();
            String lastName = update.getMessage().getChat().getLastName();
            String userName = update.getMessage().getChat().getUserName();
            long id = update.getMessage().getChat().getId();
            String messageText = update.getMessage().getText();
            long chat_id = update.getMessage().getChatId();
            String answer = messageText;

            String emAnswer = EmojiParser.parseToUnicode("Here is a smile emoji: :smile:\n\n Here is alien emoji: :alien:");

            SendMessage message = new SendMessage()
                    .setChatId(chat_id)
                    .setText(emAnswer);

            log(firstName, lastName, Long.toString(id), messageText, answer);
            try {
                execute(message);
            } catch (TelegramApiException e) {
                e.printStackTrace();
            }
        }
    }

    private void log(String firstName, String lastName, String userId, String txt, String botAnswer) {
        System.out.println("\n ----------------------------");
        DateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
        Date date = new Date();
        System.out.println(dateFormat.format(date));
        System.out.println("Message from " + firstName + " " + lastName + ". (id = " + userId + ") \n Text - " + txt);
        System.out.println("Bot answer: \n Text - " + botAnswer);
    }

    @Override
    public String getBotUsername() {
        return "SSTDZNewEmojiBot";
    }

    @Override
    public String getBotToken() {
        return "547998968:AAFJx_39FGuuEUzwncg4Fw3G9dXK786dUYU";
    }
}
