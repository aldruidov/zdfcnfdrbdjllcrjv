package proj;

import org.apache.commons.codec.binary.Hex;
import org.apache.http.HttpEntity;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.security.MessageDigest;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

/**
 * Created by DongCatchay on 6/29/2017.
 */
public class UnityAPI {
    public boolean getUserCredit(String url, String username, String accessToken, String reqId) {
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
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(content));
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean getBetTicket(String url, String username, String accessToken, String reqId, String company,
                                String targettype, String market, String eventid, String oddid) {
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
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(content));
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return true;
    }

    public boolean placeBet(String url, String username, String accessToken, String reqId, String company, String targettype,
                            String market, String eventid, String oddid, float targetodd, float gold, Boolean acceptbetterodd,
                            Boolean autoStakeAdjustment, int homeScore, int awayScore) {
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
            System.out.println(response.getStatusLine());
            System.out.println(EntityUtils.toString(content));
        }catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }finally {
            try {
                httpClient.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return true;
    }

    public String getAccessToken(String username, String password) {
        StringBuilder builder;
        DateFormat dateFormat;
        Date time = new Date();

        // combine the input fields
        builder = new StringBuilder();
        builder.append(username).append('_');

        dateFormat = new SimpleDateFormat("dd/MM/yyyy");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0:00"));
        String date1 = dateFormat.format(time);
        builder.append(date1).append('_');

        builder.append(password).append('_');

        dateFormat = new SimpleDateFormat("HH/mm");
        dateFormat.setTimeZone(TimeZone.getTimeZone("GMT-0:00"));
        String date2 = dateFormat.format(time);
        builder.append(date2);

        try {
            byte[] bytesOfMessage = builder.toString().getBytes("utf-8");
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] theDigest = md.digest(bytesOfMessage);

            String result = new String(Hex.encodeHex(theDigest));
            return result;
        } catch (Exception e) {
            return null;
        }
    }
}
