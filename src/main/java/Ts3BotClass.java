import com.github.theholywaffle.teamspeak3.TS3Api;
import com.github.theholywaffle.teamspeak3.TS3Config;
import com.github.theholywaffle.teamspeak3.TS3Query;
import com.github.theholywaffle.teamspeak3.api.TextMessageTargetMode;
import com.github.theholywaffle.teamspeak3.api.event.ClientJoinEvent;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventAdapter;
import com.github.theholywaffle.teamspeak3.api.event.TS3EventType;
import com.github.theholywaffle.teamspeak3.api.event.TextMessageEvent;

public class Ts3BotClass {
    Credentials credentials = new Credentials();
    final TS3Config config = new TS3Config();
    final TS3Query query = new TS3Query(config);
    final TS3Api api = query.getApi();

    public void initBot(){

        config.setHost(credentials.ts3_server);
        config.setEnableCommunicationsLogging(true);
        query.connect();

        api.login(credentials.ts3_query_name, credentials.ts3_query_password);
        api.selectVirtualServerById(1, credentials.ts3_nickname);
        //api.sendChannelMessage("PutPutBot is online!");

        // Get our own client ID by running the "whoami" command
        final int clientId = api.whoAmI().getId();

        // Listen to chat in the channel the query is currently in
        // As we never changed the channel, this will be the default channel of the server
        api.registerAllEvents();

        api.addTS3Listeners(new TS3EventAdapter() {
            @Override
            public void onTextMessage(TextMessageEvent e) {
                super.onTextMessage(e);
            }

            @Override
            public void onClientJoin(ClientJoinEvent e) {
                System.out.println(e.getClientNickname());
            }
        });




    } //initBot
} //class

