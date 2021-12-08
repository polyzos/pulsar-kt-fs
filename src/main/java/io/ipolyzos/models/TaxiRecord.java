package io.ipolyzos.models;

public class TaxiRecord {
    private String vendorID;
    private String tpepPickupDatetime;
    private String tpepDropoffDatetime;
    private String passengerCount;
    private String tripDistance;
    private String ratecodeID;
    private String storeAndFwdFlag;
    private String puLocationID;
    private String poLocationID;
    private String paymentType;
    private String fareAmount;
    private String extra;
    private String mtaTax;
    private String tipAmount;
    private String tollsAmount;
    private String improvementSurcharge;
    private String totalAmount;
    private String congestionSurcharge;

    public TaxiRecord() {}

    public TaxiRecord(String vendorID,
                      String tpepPickupDatetime,
                      String tpepDropoffDatetime,
                      String passengerCount,
                      String tripDistance,
                      String ratecodeID,
                      String storeAndFwdFlag,
                      String puLocationID,
                      String poLocationID,
                      String paymentType,
                      String fareAmount,
                      String extra,
                      String mtaTax,
                      String tipAmount,
                      String tollsAmount,
                      String improvementSurcharge,
                      String totalAmount,
                      String congestionSurcharge) {
        this.vendorID = vendorID;
        this.tpepPickupDatetime = tpepPickupDatetime;
        this.tpepDropoffDatetime = tpepDropoffDatetime;
        this.passengerCount = passengerCount;
        this.tripDistance = tripDistance;
        this.ratecodeID = ratecodeID;
        this.storeAndFwdFlag = storeAndFwdFlag;
        this.puLocationID = puLocationID;
        this.poLocationID = poLocationID;
        this.paymentType = paymentType;
        this.fareAmount = fareAmount;
        this.extra = extra;
        this.mtaTax = mtaTax;
        this.tipAmount = tipAmount;
        this.tollsAmount = tollsAmount;
        this.improvementSurcharge = improvementSurcharge;
        this.totalAmount = totalAmount;
        this.congestionSurcharge = congestionSurcharge;
    }

    public String getVendorID() {
        return vendorID;
    }

    public void setVendorID(String vendorID) {
        this.vendorID = vendorID;
    }

    public String getTpepPickupDatetime() {
        return tpepPickupDatetime;
    }

    public void setTpepPickupDatetime(String tpepPickupDatetime) {
        this.tpepPickupDatetime = tpepPickupDatetime;
    }

    public String getTpepDropoffDatetime() {
        return tpepDropoffDatetime;
    }

    public void setTpepDropoffDatetime(String tpepDropoffDatetime) {
        this.tpepDropoffDatetime = tpepDropoffDatetime;
    }

    public String getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(String passengerCount) {
        this.passengerCount = passengerCount;
    }

    public String getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(String tripDistance) {
        this.tripDistance = tripDistance;
    }

    public String getRatecodeID() {
        return ratecodeID;
    }

    public void setRatecodeID(String ratecodeID) {
        this.ratecodeID = ratecodeID;
    }

    public String getStoreAndFwdFlag() {
        return storeAndFwdFlag;
    }

    public void setStoreAndFwdFlag(String storeAndFwdFlag) {
        this.storeAndFwdFlag = storeAndFwdFlag;
    }

    public String getPuLocationID() {
        return puLocationID;
    }

    public void setPuLocationID(String puLocationID) {
        this.puLocationID = puLocationID;
    }

    public String getPoLocationID() {
        return poLocationID;
    }

    public void setPoLocationID(String poLocationID) {
        this.poLocationID = poLocationID;
    }

    public String getPaymentType() {
        return paymentType;
    }

    public void setPaymentType(String paymentType) {
        this.paymentType = paymentType;
    }

    public String getFareAmount() {
        return fareAmount;
    }

    public void setFareAmount(String fareAmount) {
        this.fareAmount = fareAmount;
    }

    public String getExtra() {
        return extra;
    }

    public void setExtra(String extra) {
        this.extra = extra;
    }

    public String getMtaTax() {
        return mtaTax;
    }

    public void setMtaTax(String mtaTax) {
        this.mtaTax = mtaTax;
    }

    public String getTipAmount() {
        return tipAmount;
    }

    public void setTipAmount(String tipAmount) {
        this.tipAmount = tipAmount;
    }

    public String getTollsAmount() {
        return tollsAmount;
    }

    public void setTollsAmount(String tollsAmount) {
        this.tollsAmount = tollsAmount;
    }

    public String getImprovementSurcharge() {
        return improvementSurcharge;
    }

    public void setImprovementSurcharge(String improvementSurcharge) {
        this.improvementSurcharge = improvementSurcharge;
    }

    public String getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(String totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getCongestionSurcharge() {
        return congestionSurcharge;
    }

    public void setCongestionSurcharge(String congestionSurcharge) {
        this.congestionSurcharge = congestionSurcharge;
    }

    @Override
    public String toString() {
        return "TaxiRecord{" +
                "vendorID='" + vendorID + '\'' +
                ", tpepPickupDatetime='" + tpepPickupDatetime + '\'' +
                ", tpepDropoffDatetime='" + tpepDropoffDatetime + '\'' +
                ", passengerCount='" + passengerCount + '\'' +
                ", tripDistance='" + tripDistance + '\'' +
                ", ratecodeID='" + ratecodeID + '\'' +
                ", storeAndFwdFlag='" + storeAndFwdFlag + '\'' +
                ", puLocationID='" + puLocationID + '\'' +
                ", poLocationID='" + poLocationID + '\'' +
                ", paymentType='" + paymentType + '\'' +
                ", fareAmount='" + fareAmount + '\'' +
                ", extra='" + extra + '\'' +
                ", mtaTax='" + mtaTax + '\'' +
                ", tipAmount='" + tipAmount + '\'' +
                ", tollsAmount='" + tollsAmount + '\'' +
                ", improvementSurcharge='" + improvementSurcharge + '\'' +
                ", totalAmount='" + totalAmount + '\'' +
                ", congestionSurcharge='" + congestionSurcharge + '\'' +
                '}';
    }
}
