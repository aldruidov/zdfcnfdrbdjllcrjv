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
    private static final Logger logger = LogManager.getLogger(VoddsBettingMachine.class.getName());

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        Properties systemProps = System.getProperties();
        // setup key stores for secure connections
        systemProps.put("javax.net.ssl.trustStore", "conf/client-92.ts");
        systemProps.put("javax.net.ssl.keyStore", "conf/client-92.ks");
        systemProps.put("javax.net.ssl.trustStorePassword", "password");
        systemProps.put("javax.net.ssl.keyStorePassword", "password");
        //setup the configuration file
        systemProps.put("deltaCrawlerSessionConfigurationFile", "conf/deltaCrawlerSession.json");        
        systemProps.put("logback.configurationFile", "conf/logback.xml");
       
        DeltaCrawlerSession cs = new DeltaCrawlerSession();
        logger.debug("connecting...");
        cs.connect();
        cs.waitConnection();
        
        //cs.connect("unity_group54", "9amutV5Uks", "ssl://datafeed-01.olesportsresearch.com:61617?connectionTimeout=60000");

        EventHandler dft = new EventHandler();
        cs.addDeltaEventHandler(dft);

        try {
                Thread.sleep(15000);
        } catch (InterruptedException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
        }
        
        logger.debug("disconnecting and exit...");
        cs.disconnect();
        System.out.println("Exit");
        System.exit(0);   

        // --------
//        String url = "http://biweb-unity-test.olesportsresearch.com";
//        String username = "unity_group54";
//        String password = "9amutV5Uks";
//        String accessToken;
//        String reqId;
//        
//        UnityAPI unityAPI = new UnityAPI();
//
//        // get user credit
//        accessToken = unityAPI.getAccessToken(username, password);
//        System.out.println("accessToken: " + accessToken);
//        //logger.debug("accessToken: %s", accessToken);
//        reqId = UUID.randomUUID().toString();
//        unityAPI.getUserCredit(url, username, accessToken, reqId); 
//        
//        String company = "SBO"; //  SoccerEventLiveState.source
//        String targettype = "over"; // ??
//        String market = "LIVE"; // Record.OddType - LIVE, TODAY, EARLY
//        String eventid = "b943e6c486201748"; //  Record.eventId
//        String oddid = "14342"; // Record.oddId
//
//        float targetodd = 1.2f;
//        float gold = 999999.0f;
//        Boolean acceptbetterodd = false;
//        Boolean autoStakeAdjustment = false;
//        int homeScore = -1;
//        int awayScore = -1;     
//        
//        // get bet ticket
//        reqId = UUID.randomUUID().toString();
//        unityAPI.getBetTicket(url, username, accessToken, reqId, company, targettype, market, eventid, oddid);        
//        
//        // place bet (be carefully, this is action place bet)
//        reqId = UUID.randomUUID().toString();
//        unityAPI.placeBet(url, username, accessToken, reqId, company, targettype, market, eventid, oddid, targetodd,
//                gold, acceptbetterodd, autoStakeAdjustment, homeScore, awayScore);        
//                   

    }
    
}
