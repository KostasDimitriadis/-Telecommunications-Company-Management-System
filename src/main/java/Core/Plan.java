package Core;

public abstract class Plan {

    private int specialPass;
    private String provider;
    private int freeTalk; //in minutes
    private double planCost;

    public Plan(int specialPass, String provider, int freeTalk, double planCost) {
        this.specialPass = specialPass;
        this.provider = provider;
        this.freeTalk = freeTalk;
        this.planCost = planCost;
    }
    public int getSpecialPass() {
        return specialPass;
    }
    public void setSpecialPass(int specialPass) {
        this.specialPass = specialPass;
    }
    public String getProvider() {
        return provider;
    }
    public void setProvider(String provider) {
        this.provider = provider;
    }
    public int getFreeTalk() {
        return freeTalk;
    }
    public void setFreeTalk(int freeTalk) {
        this.freeTalk = freeTalk;
    }
    public double getPlanCost() {
        return planCost;
    }
    public void setPlanCost(double planCost) {
        this.planCost = planCost;
    }

}
