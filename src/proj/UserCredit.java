/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package proj;

/**
 *
 * @author Galimov
 */
public class UserCredit {
    private int actionStatus;
    private String actionMessage;
    private String reqId;
    private String username;
    private double credit;
    private String currency;
    
    public UserCredit(int actionStatus, String actionMessage, String reqId, String username, double credit, String currency) {
        this.actionStatus = actionStatus;
        this.actionMessage = actionMessage;
        this.reqId = reqId;
        this.username = username;
        this.credit = credit;
        this.currency = currency;
    }
    
    @Override
    public String toString() {
        return "UserCredit{" + "actionStatus=" + actionStatus + ", actionMessage=" + actionMessage + ", reqId=" + reqId + ", username=" + username + ", credit=" + credit + ", currency=" + currency + '}';
    }

    /**
     * @return the actionStatus
     */
    public int getActionStatus() {
        return actionStatus;
    }

    /**
     * @param actionStatus the actionStatus to set
     */
    public void setActionStatus(int actionStatus) {
        this.actionStatus = actionStatus;
    }

    /**
     * @return the actionMessage
     */
    public String getActionMessage() {
        return actionMessage;
    }

    /**
     * @param actionMessage the actionMessage to set
     */
    public void setActionMessage(String actionMessage) {
        this.actionMessage = actionMessage;
    }

    /**
     * @return the reqId
     */
    public String getReqId() {
        return reqId;
    }

    /**
     * @param reqId the reqId to set
     */
    public void setReqId(String reqId) {
        this.reqId = reqId;
    }

    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * @return the credit
     */
    public double getCredit() {
        return credit;
    }

    /**
     * @param credit the credit to set
     */
    public void setCredit(double credit) {
        this.credit = credit;
    }

    /**
     * @return the currency
     */
    public String getCurrency() {
        return currency;
    }

    /**
     * @param currency the currency to set
     */
    public void setCurrency(String currency) {
        this.currency = currency;
    }
}
