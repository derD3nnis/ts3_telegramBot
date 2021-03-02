import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;

public class Main {
    public static void main(String[] args){
        System.out.println("Test");
        Ts3BotClass ts3Bot = new Ts3BotClass();
        ts3Bot.initBot();
        ts3Bot.api.sendChannelMessage("Hi");
        System.out.println(ts3Bot.api.getClients());
        int id = ts3Bot.api.getClientByUId("VI6GuEtsnPI/KZSjUf+VWWb6OyA=").getId();

        // Initialize Api Context

        try {
            TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
            botsApi.registerBot(new TelegramBotClass());
        } catch (TelegramApiException e) {
            e.printStackTrace();
        }

        // Register our bot





    }
}
