package bot;

import lombok.SneakyThrows;
import org.telegram.telegrambots.api.methods.send.SendMessage;
import org.telegram.telegrambots.api.objects.Update;
import org.telegram.telegrambots.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.api.objects.replykeyboard.buttons.KeyboardRow;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import service.impl.WeatherServiceImpl;

import java.util.List;

public class Bot extends TelegramLongPollingBot {

    ReplyKeyboardMarkup replyKeyboardMarkup = new ReplyKeyboardMarkup();

    @SneakyThrows
    @Override
    public void onUpdateReceived(Update update) {
        var city = update.getMessage().getText();

        WeatherServiceImpl weatherService = new WeatherServiceImpl();

        var sendMessage = new SendMessage();
        sendMessage.enableMarkdown(true);

        sendMessage.setChatId(update.getMessage().getChatId());
        sendMessage.setText(weatherService.getByCityName(city));

        KeyboardRow firstKeyboardRow = new KeyboardRow();
        firstKeyboardRow.add("Kiev");

        KeyboardRow secondKeyboardRow = new KeyboardRow();
        secondKeyboardRow.add("Mariupol");

        sendMessage.setReplyMarkup(replyKeyboardMarkup);

        replyKeyboardMarkup.setKeyboard(List.of(firstKeyboardRow, secondKeyboardRow));
        replyKeyboardMarkup.setSelective(true);
        replyKeyboardMarkup.setResizeKeyboard(true);

        execute(sendMessage);
    }

    @Override
    public String getBotUsername() {
        return "WeatherTomCatBot";
    }

    @Override
    public String getBotToken() {
        return "1130624546:AAFyWZKu3s8LEeCLph1VrZQvxMJgosigyak";
    }
}
