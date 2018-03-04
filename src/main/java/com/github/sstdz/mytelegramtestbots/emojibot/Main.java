package com.github.sstdz.mytelegramtestbots.emojibot;

import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {

    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new EmojiBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
