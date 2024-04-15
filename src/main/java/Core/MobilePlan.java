package Core;

public class MobilePlan extends Plan{

    private int freeSms;
    private double freeGb;

    public MobilePlan(int specialPass, String provider, int freeTalk, double planCost, int freeSms, double freeGb) {
        super(specialPass, provider, freeTalk, planCost);
        this.freeSms = freeSms;
        this.freeGb = freeGb;
    }

    public int getFreeSms() {
        return freeSms;
    }

    public void setFreeSms(int freeSms) {
        this.freeSms = freeSms;
    }

    public double getFreeGb() {
        return freeGb;
    }

    public void setFreeGb(double freeGb) {
        this.freeGb = freeGb;
    }

}
