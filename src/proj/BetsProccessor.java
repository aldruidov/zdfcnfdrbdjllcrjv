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
    String url = "http://biweb-unity-test.olesportsresearch.com";
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
