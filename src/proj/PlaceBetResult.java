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
public class PlaceBetResult {
    private int actionStatus;
    private String actionMessage;
    private String reqId;
    private String id;
    private String username;
    private double betAmount;
    private double betOdd;
    private BetStatus betStatus;

    public PlaceBetResult(int actionStatus, String actionMessage, String reqId, String id, String username, double betAmount, double betOdd, BetStatus betStatus) {
        this.actionStatus = actionStatus;
        this.actionMessage = actionMessage;
        this.reqId = reqId;
        this.id = id;
        this.username = username;
        this.betAmount = betAmount;
        this.betOdd = betOdd;
        this.betStatus = betStatus;
    }

    @Override
    public String toString() {
        return "PlaceBetResult{" + "actionStatus=" + actionStatus + ", actionMessage=" + actionMessage + ", reqId=" + reqId + ", id=" + id + ", username=" + username + ", betAmount=" + betAmount + ", betOdd=" + betOdd + ", betStatus=" + betStatus + '}';
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
     * @return the id
     */
    public String getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(String id) {
        this.id = id;
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
     * @return the betAmount
     */
    public double getBetAmount() {
        return betAmount;
    }

    /**
     * @param betAmount the betAmount to set
     */
    public void setBetAmount(double betAmount) {
        this.betAmount = betAmount;
    }

    /**
     * @return the betOdd
     */
    public double getBetOdd() {
        return betOdd;
    }

    /**
     * @param betOdd the betOdd to set
     */
    public void setBetOdd(double betOdd) {
        this.betOdd = betOdd;
    }

    /**
     * @return the betStatus
     */
    public BetStatus getBetStatus() {
        return betStatus;
    }

    /**
     * @param betStatus the betStatus to set
     */
    public void setBetStatus(BetStatus betStatus) {
        this.betStatus = betStatus;
    }
}
