package proj;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.json.JSONObject;

/**
 *
 * @author Galimov
 */
public class BetsProccessor {
    static final double BET_GOLD_VAL_COEF = 0.03; // доля ставки от текущего баланса
    static final String URL_TESTING = "http://biweb-unity-test.olesportsresearch.com";
    static final String URL_REAL = "http://biweb-unity.olesportsresearch.com";
    static final int ACT_STATUS_SUCCESS = 0;
    
    String url = URL_TESTING;
    String username = "unity_group54";
    String password = "9amutV5Uks";
    String accessToken;
    String reqId;

    private UnityAPI unityAPI;

    public BetsProccessor() {
        unityAPI = new UnityAPI();
        getAccessToken();
    }
    
    private String getAccessToken() {
        accessToken = unityAPI.getAccessToken(username, password);
        System.out.println("accessToken: " + accessToken);
        return accessToken;
    }
    
    public UserCredit getUserCredit() {
        UserCredit result = null;
        reqId = UUID.randomUUID().toString();
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<BasicNameValuePair> formparams = new ArrayList<>();
        UrlEncodedFormEntity entity;

        formparams.add(new BasicNameValuePair("username", username));
        formparams.add(new BasicNameValuePair("accessToken", accessToken));
        formparams.add(new BasicNameValuePair("reqId", reqId));

        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            HttpPost httpPost = new HttpPost(url + "/getusercredit");
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity content = response.getEntity();
//            System.out.println(response.getStatusLine());
//            System.out.println(EntityUtils.toString(content));

            final JSONObject obj = new JSONObject(EntityUtils.toString(content)); 
            UserCredit userCredit = new UserCredit(obj.getInt("actionStatus"), obj.getString("actionMessage"), obj.getString("reqId"), 
                    obj.getString("username"), obj.getDouble("credit"), obj.getString("currency"));
            result = userCredit;
            
        }catch (Exception ex) {
            ex.printStackTrace();
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return result;
    }
    
    public BetTicket getBetTicket(String company, String targettype, String market, String eventid, String oddid) {
        BetTicket result = null;
        reqId = UUID.randomUUID().toString();
        //unityAPI.getBetTicket(url, username, accessToken, reqId, company, targettype, market, eventid, oddid);                
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<BasicNameValuePair> formparams = new ArrayList<>();
        UrlEncodedFormEntity entity;

        formparams.add(new BasicNameValuePair("username", username));
        formparams.add(new BasicNameValuePair("accessToken", accessToken));
        formparams.add(new BasicNameValuePair("reqId", reqId));
        formparams.add(new BasicNameValuePair("company", company));
        formparams.add(new BasicNameValuePair("targettype", targettype));
        formparams.add(new BasicNameValuePair("market", market));
        formparams.add(new BasicNameValuePair("eventid", eventid));
        formparams.add(new BasicNameValuePair("oddid", oddid));
        formparams.add(new BasicNameValuePair("createdTime", String.valueOf(System.currentTimeMillis())));

        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            HttpPost httpPost = new HttpPost(url + "/getbetticket");
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity content = response.getEntity();
            final JSONObject obj = new JSONObject(EntityUtils.toString(content)); 
            BetTicket betTicket;
            int actionStatus = obj.getInt("actionStatus");
            if (actionStatus == ACT_STATUS_SUCCESS) {
                betTicket = new BetTicket(obj.getString("actionMessage"), obj.getString("pivotValue"), 
                        obj.getInt("homeScore"), obj.getInt("awayScore"), obj.getDouble("currentOdd"), 
                        obj.getDouble("minStake"), obj.getDouble("maxStake"), obj.getString("reqId"), obj.getInt("actionStatus"));                
            } else {
                betTicket = new BetTicket(obj.getString("actionMessage"), "", 
                        0, 0, 0., 0., 0., "", obj.getInt("actionStatus"));                
            }
            result = betTicket;
//            System.out.println(response.getStatusLine());
//            System.out.println(EntityUtils.toString(content));
        } catch (Exception ex) {
            ex.printStackTrace();
            //return false;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    public PlaceBetResult placeBet(String company, String targettype,
                            String market, String eventid, String oddid, double targetodd, double gold, Boolean acceptbetterodd,
                            Boolean autoStakeAdjustment, int homeScore, int awayScore) {
        PlaceBetResult result = null;
        reqId = UUID.randomUUID().toString();
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<BasicNameValuePair> formparams = new ArrayList<>();
        UrlEncodedFormEntity entity;

        formparams.add(new BasicNameValuePair("username", username));
        formparams.add(new BasicNameValuePair("accessToken", accessToken));
        formparams.add(new BasicNameValuePair("reqId", reqId));
        formparams.add(new BasicNameValuePair("company", company));
        formparams.add(new BasicNameValuePair("targettype", targettype));
        formparams.add(new BasicNameValuePair("market", market));
        formparams.add(new BasicNameValuePair("eventid", eventid));
        formparams.add(new BasicNameValuePair("oddid", oddid));
        formparams.add(new BasicNameValuePair("targetodd", String.valueOf(targetodd)));
        formparams.add(new BasicNameValuePair("gold", String.valueOf(gold)));
        formparams.add(new BasicNameValuePair("acceptbetterodd", String.valueOf(acceptbetterodd)));
        formparams.add(new BasicNameValuePair("autoStakeAdjustment", String.valueOf(autoStakeAdjustment)));
        formparams.add(new BasicNameValuePair("homeScore", String.valueOf(homeScore)));
        formparams.add(new BasicNameValuePair("awayScore", String.valueOf(awayScore)));
        formparams.add(new BasicNameValuePair("createdTime", String.valueOf(System.currentTimeMillis())));

        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            HttpPost httpPost = new HttpPost(url + "/placebet");
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity content = response.getEntity();
//            System.out.println(response.getStatusLine());
//            System.out.println(EntityUtils.toString(content));
            final JSONObject obj = new JSONObject(EntityUtils.toString(content));    
            int actionStatus = obj.getInt("actionStatus");
            if (actionStatus == ACT_STATUS_SUCCESS) {
                PlaceBetResult placeBetResult = new PlaceBetResult(actionStatus, obj.getString("actionMessage"), 
                        obj.getString("reqId"), obj.getString("id"), obj.getString("username"), 
                        obj.getDouble("betAmount"), obj.getDouble("betOdd"), BetStatus.values()[obj.getInt("betStatus")]);
                result = placeBetResult;
            } else {
                PlaceBetResult placeBetResult = new PlaceBetResult(actionStatus, obj.getString("actionMessage"), 
                        null, null, null, 0., 0., null);
                result = placeBetResult;
            }
            
        }catch (Exception ex) {
            ex.printStackTrace();
            //return false;
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }
    
    /**
     * Определяет размер ставки в зависимости от текущего баланса, мин. и макс. ставки для данной позиции
     * @param minStake
     * @param maxStake
     * @return 
     */
    public double getBetGoldValue(double minStake, double maxStake) {
        double result = -1;
        UserCredit userCredit = getUserCredit();
        result = userCredit.getCredit() * BET_GOLD_VAL_COEF;
        if (result < minStake) {
            result = minStake;
        } else if (result > maxStake) {
            result = maxStake;
        }
        return result;
    }

    public BetTicket getBetStatus(String betId) {
        BetTicket result = null;
        reqId = UUID.randomUUID().toString();
        //unityAPI.getBetTicket(url, username, accessToken, reqId, company, targettype, market, eventid, oddid);                
        
        CloseableHttpClient httpClient = HttpClients.createDefault();
        List<BasicNameValuePair> formparams = new ArrayList<>();
        UrlEncodedFormEntity entity;

        formparams.add(new BasicNameValuePair("username", username));
        formparams.add(new BasicNameValuePair("accessToken", accessToken));
        formparams.add(new BasicNameValuePair("reqId", reqId));
        formparams.add(new BasicNameValuePair("id", betId));
        formparams.add(new BasicNameValuePair("createdTime", String.valueOf(System.currentTimeMillis())));

        try {
            entity = new UrlEncodedFormEntity(formparams, "UTF-8");
            HttpPost httpPost = new HttpPost(url + "/getbetticket");
            httpPost.setEntity(entity);

            CloseableHttpResponse response = httpClient.execute(httpPost);

            HttpEntity content = response.getEntity();
            final JSONObject obj = new JSONObject(EntityUtils.toString(content)); 
            BetTicket betTicket = new BetTicket(obj.getString("actionMessage"), obj.getString("pivotValue"), 
                    obj.getInt("homeScore"), obj.getInt("awayScore"), obj.getDouble("currentOdd"), 
                    obj.getDouble("minStake"), obj.getDouble("maxStake"), obj.getString("reqId"), obj.getInt("actionStatus"));
            result = betTicket;
//            System.out.println(response.getStatusLine());
//            System.out.println(EntityUtils.toString(content));
        } catch (Exception ex) {
            ex.printStackTrace();
            //return false;
        } finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return result;
    }    
    
}
