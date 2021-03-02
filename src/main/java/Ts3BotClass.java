import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.event.*;
import com.github.theholywaffle.teamspeak3.api.wrapper.Client;
import org.telegram.telegrambots.meta.api.methods.send.SendMessage;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Ts3BotClass {
    Credentials credentials = new Credentials();
    static final TS3Config config = new TS3Config();
    static final TS3Query query = new TS3Query(config);
    static final TS3Api api = query.getApi();
    


    public void initBot(){
        Map<Integer, String> onlineUsers = new HashMap<>();

        TelegramBotClass telegramBotClass = new TelegramBotClass();

        config.setHost(credentials.ts3_server);
        config.setEnableCommunicationsLogging(true);


        query.connect();

        api.login(credentials.ts3_query_name, credentials.ts3_query_password);
        api.selectVirtualServerById(1, credentials.ts3_nickname);
        api.sendChannelMessage("PutPutBot is online!");
        List<Client> clients = api.getClients();
        for(Client client : clients){
            onlineUsers.put(client.getId(), client.getNickname());
        }



        api.registerAllEvents();

        // Register the event listener
        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                super.onTextMessage(e);
            }

            @Override
            public void onClientJoin(ClientJoinEvent e) {
                SendMessage message = new SendMessage();
                message.setText(e.getClientNickname() + " joined!");
                message.setChatId("-321702114");
                try {
                    telegramBotClass.execute(message);
                } catch (TelegramApiException telegramApiException) {
                    telegramApiException.printStackTrace();
                }
                onlineUsers.put(e.getClientId(), e.getClientNickname());

            }

            @Override
            public void onClientLeave(ClientLeaveEvent e) {
                SendMessage message = new SendMessage();

                String nickname = onlineUsers.get(e.getClientId());
                message.setText(nickname + " left!");

                message.setChatId("-321702114");
                onlineUsers.remove(e.getClientId());
                try {
                    telegramBotClass.execute(message);
                } catch (TelegramApiException telegramApiException) {
                    telegramApiException.printStackTrace();
                }
            }
        });





    } //initBot



} //class

