package com.github.sstdz.mytelegramtestbots;

import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;

public class SimpleEchoBot  extends TelegramLongPollingBot{


    public void onUpdateReceived(Update update) {
    }

    public String getBotUsername() {
        return "SSTDZEchoBot";
    }

    public String getBotToken() {
        return "12345:tokenone";
    }
}
