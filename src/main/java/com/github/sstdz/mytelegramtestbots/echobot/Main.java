package com.github.sstdz.mytelegramtestbots;

import com.github.sstdz.mytelegramtestbots.echobot.SimpleEchoBot;
import org.telegram.telegrambots.ApiContextInitializer;
import org.telegram.telegrambots.TelegramBotsApi;
import org.telegram.telegrambots.exceptions.TelegramApiRequestException;

public class Main {
    public static void main(String[] args) {
        ApiContextInitializer.init();
        TelegramBotsApi botsApi = new TelegramBotsApi();

        try {
            botsApi.registerBot(new SimpleEchoBot());
        } catch (TelegramApiRequestException e) {
            e.printStackTrace();
        }
    }
}
