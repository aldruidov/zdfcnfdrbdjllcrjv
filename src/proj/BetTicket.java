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
public class BetTicket {
    private String actionMessage;
    private String pivotValue;
    private int homeScore;
    private int awayScore;
    private double currentOdd;
    private double minStake;
    private double maxStake;
    private String reqId;
    private int actionStatus;    

    public BetTicket(String actionMessage, String pivotValue, int homeScore, int awayScore, double currentOdd, double minStake, double maxStake, String reqId, int actionStatus) {
        this.actionMessage = actionMessage;
        this.pivotValue = pivotValue;
        this.homeScore = homeScore;
        this.awayScore = awayScore;
        this.currentOdd = currentOdd;
        this.minStake = minStake;
        this.maxStake = maxStake;
        this.reqId = reqId;
        this.actionStatus = actionStatus;
    }

    @Override
    public String toString() {
        return "BetTicket{" + "actionMessage=" + actionMessage + ", pivotValue=" + pivotValue + ", homeScore=" + homeScore + ", awayScore=" + awayScore + ", currentOdd=" + currentOdd + ", minStake=" + minStake + ", maxStake=" + maxStake + ", reqId=" + reqId + ", actionStatus=" + actionStatus + '}';
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
     * @return the pivotValue
     */
    public String getPivotValue() {
        return pivotValue;
    }

    /**
     * @param pivotValue the pivotValue to set
     */
    public void setPivotValue(String pivotValue) {
        this.pivotValue = pivotValue;
    }

    /**
     * @return the homeScore
     */
    public int getHomeScore() {
        return homeScore;
    }

    /**
     * @param homeScore the homeScore to set
     */
    public void setHomeScore(int homeScore) {
        this.homeScore = homeScore;
    }

    /**
     * @return the awayScore
     */
    public int getAwayScore() {
        return awayScore;
    }

    /**
     * @param awayScore the awayScore to set
     */
    public void setAwayScore(int awayScore) {
        this.awayScore = awayScore;
    }

    /**
     * @return the currentOdd
     */
    public double getCurrentOdd() {
        return currentOdd;
    }

    /**
     * @param currentOdd the currentOdd to set
     */
    public void setCurrentOdd(double currentOdd) {
        this.currentOdd = currentOdd;
    }

    /**
     * @return the minStake
     */
    public double getMinStake() {
        return minStake;
    }

    /**
     * @param minStake the minStake to set
     */
    public void setMinStake(double minStake) {
        this.minStake = minStake;
    }

    /**
     * @return the maxStake
     */
    public double getMaxStake() {
        return maxStake;
    }

    /**
     * @param maxStake the maxStake to set
     */
    public void setMaxStake(double maxStake) {
        this.maxStake = maxStake;
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
}
