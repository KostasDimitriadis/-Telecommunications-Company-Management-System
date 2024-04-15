package Core;

import java.time.LocalDate;

public class Contract{

    private Plan plan;
    private String specialPass;
    private int afm;
    private long phoneNum; //10 digits
    private LocalDate startingDate;
    private String duration; //12 or 24 months
    private String discount;
    private double costWithDiscount;
    private String billType; //printed or digital
    private String paymentMethod; //credit card or cash
    private double costOfCancellation; // it is 10% of cost if current date - starting date <=3(mmYyyy1 - mmYyyy2)
    //if its after that then the cost is 0
    private String state;
    private boolean contractActive;

    public Contract(String specialPass,int afm,long phoneNum,LocalDate startingDate,String duration,String discount,
                    double costWithDiscount, String billType, String paymentMethod,double costOfCancellation,String state,
                    boolean contractActive, Plan plan) {

        this.plan = plan;
        this.specialPass = specialPass;
        this.phoneNum = phoneNum;
        this.afm = afm;
        this.startingDate = startingDate;
        this.duration = duration;
        this.discount = discount;
        this.costWithDiscount = costWithDiscount;
        this.billType = billType;
        this.paymentMethod = paymentMethod;
        this.costOfCancellation = costOfCancellation;
        this.state=state;
        this.contractActive = contractActive;

    }

    public Plan getPlan() {
        return plan;
    }

    public void setPlan(Plan plan) {
        this.plan = plan;
    }

    public String getSpecialPass() {
        return specialPass;
    }

    public void setSpecialPass(String specialPass) {
        this.specialPass = specialPass;
    }

    public long getPhoneNum() {
        return phoneNum;
    }

    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }

    public int getAfm() {
        return afm;
    }

    public void setAfm(int afm) {
        this.afm = afm;
    }

    public LocalDate getStartingDate() {
        return startingDate;
    }

    public void setStartingDate(LocalDate startingDate) {
        this.startingDate = startingDate;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getDiscount() {
        return discount;
    }

    public void setDiscount(String discount) {
        this.discount = discount;
    }

    public double getCostWithDiscount() {
        return costWithDiscount;
    }

    public void setCostWithDiscount(double costWithDiscount) {
        this.costWithDiscount = costWithDiscount;
    }

    public String getBillType() {
        return billType;
    }

    public void setBillType(String billType) {
        this.billType = billType;
    }

    public String getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(String paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public double getCostOfCancellation() {
        return costOfCancellation;
    }

    public void setCostOfCancellation(double costOfCancellation) {
        this.costOfCancellation = costOfCancellation;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean isContractActive() {
        return contractActive;
    }

    public void setContractActive(boolean contractActive) {
        this.contractActive = contractActive;
    }

}
