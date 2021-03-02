import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.api.objects.Message;
import org.telegram.telegrambots.meta.api.objects.Update;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;

import java.util.List;

public class TelegramBotClass extends TelegramLongPollingBot {
    Credentials credentials = new Credentials();


    @Override
    public String getBotUsername() {

        return credentials.telegram_username;
    }

    @Override
    public String getBotToken() {

        return credentials.telegram_token;
    }

    @Override
    public void onUpdateReceived(Update update) {


        if (update.hasMessage() && update.getMessage().hasText()) {
            long chat_id = update.getMessage().getChatId();
            if(update.getMessage().getText().contains("/online")){
                SendMessage message = new SendMessage(); // Create a message object object
                StringBuilder users = new StringBuilder();
                try {

                    List<Client> clients = Ts3BotClass.api.getClients();
                    if(clients.size()>1){
                        for(Client client : clients){
                            if(!client.getNickname().equals("PyBot")){
                                users.append(client.getNickname()).append(", ");
                            }
                        }
                        users = new StringBuilder(users.substring(0, users.length() - 2));

                    } else {
                        users = new StringBuilder("Niemand ist online");
                    }


                    message.setText(users.toString());
                    message.setChatId(String.valueOf(chat_id));

                } catch (Exception e) {
                    e.printStackTrace();

                }

                try {
                    execute(message); // Sending message object to user
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }



        }
    }


}
