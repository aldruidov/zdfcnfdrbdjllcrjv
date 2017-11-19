/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj;

import java.util.Collection;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Properties;
import java.util.UUID;
import jayeson.lib.datastructure.Record;
import jayeson.lib.datastructure.SoccerEvent;
import jayeson.lib.datastructure.SoccerEventLiveState;
import jayeson.lib.recordfetcher.DeltaCrawlerSession;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 *
 * @author User
 */
public class VoddsBettingMachine {
    private static final Logger logger = LogManager.getLogger(VoddsBettingMachine.class);

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
//        UnityAPI unityAPI = new UnityAPI();
//
//        String company = "<company>";
//        String targettype = "<targettype>";
//        String market = "<market>";
//        String eventid = "<eventid>";
//        String oddid = "<oddid>";
//
//        float targetodd = 0.0f;
//        float gold = 0.0f;
//        Boolean acceptbetterodd = false;
//        Boolean autoStakeAdjustment = false;
//        int homeScore = -1;
//        int awayScore = -1;
//
//        String url = "http://biweb-unity-test.olesportsresearch.com";
//        String username = "unity_group54";
//        String password = "9amutV5Uks";
//        String accessToken;
//        String reqId;

        // get user credit
//        accessToken = unityAPI.getAccessToken(username, password);
//        reqId = UUID.randomUUID().toString();
//        unityAPI.getUserCredit(url, username, accessToken, reqId);
        
        Properties systemProps = System.getProperties();
        // setup key stores for secure connections
        systemProps.put("javax.net.ssl.trustStore", "conf/client-92.ts");
        systemProps.put("javax.net.ssl.keyStore", "conf/client-92.ks");
        systemProps.put("javax.net.ssl.trustStorePassword", "password");
        systemProps.put("javax.net.ssl.keyStorePassword", "password");
        //setup the configuration file
        systemProps.put("deltaCrawlerSessionConfigurationFile", "conf/deltaCrawlerSession.json");        
       
        DeltaCrawlerSession cs = new DeltaCrawlerSession();
        logger.debug("connecting...");
        cs.connect();
        cs.waitConnection();
        
        //cs.connect("unity_group54", "9amutV5Uks", "ssl://datafeed-01.olesportsresearch.com:61617?connectionTimeout=60000");

        EventHandler dft = new EventHandler();
        cs.addDeltaEventHandler(dft);

        try {
                Thread.sleep(5000);
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
        logger.debug("disconnecting and exit...");
        cs.disconnect();
        System.out.println("Exit");
        System.exit(0);        
                
    /*while (true) {
            // this call will block if the connection to the feed fails. 
            // If auto reconnection is enabled, it will automatically reconnect to the feed for you
            cs.waitConnection();
            Collection<SoccerEvent> events = cs.getAllEvents();

            System.out.println("-------------------"+events.size()+" events------------------------------------------------------------------");
            for (SoccerEvent e : events) {
                    Collection<Record> rs = e.getRecords();	

                    if (rs.size() == 0) continue;

                    System.out.println(String.format("Id %s \t-\t Host %s \t-\t Guest %s \t-\t League %s", e.getEventId(), e.getHost(), e.getGuest(), e.getLeague()));
                    SoccerEventLiveState state = e.getLiveState();


                    System.out.println(String.format("LiveState Information---- Starttime: %d, Source: %s, Duration: %d, Score %d-%d -", state.getStartTime(), state.getSource(), state.getDuration(), state.getHostPoint(), state.getGuestPoint()));
                    System.out.println(state.getSegment());

                    Map<String, String> oids = e.getAllOriginalEventIds();
                    System.out.println("Original event ids");
                    for (Entry<String, String> et : oids.entrySet()) {
                            System.out.print(et.getKey()+" - "+et.getValue()+" ");
                    }
                    System.out.println();


                    System.out.println("\n");
            }


            try {


            } catch (Exception ex) {
                    System.out.println("Exception");
                    ex.printStackTrace();
            }

            try {
                    Thread.sleep(5000);
            } catch (Exception ex) {

            }
    }     */   

        // get bet ticket
//        accessToken = unityAPI.getAccessToken(username, password);
//        reqId = UUID.randomUUID().toString();
//        unityAPI.getBetTicket(url, username, accessToken, reqId, company, targettype, market, eventid, oddid);
//
//        // place bet (be carefully, this is action place bet)
//        accessToken = unityAPI.getAccessToken(username, password);
//        reqId = UUID.randomUUID().toString();
//        unityAPI.placeBet(url, username, accessToken, reqId, company, targettype, market, eventid, oddid, targetodd,
//                gold, acceptbetterodd, autoStakeAdjustment, homeScore, awayScore);
    }
    
}
