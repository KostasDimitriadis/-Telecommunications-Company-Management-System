package Core;

public class LandlinePlan extends Plan{

    private int speed; //Mbps
    private String lineType; //ADSL or VDSL

    public LandlinePlan(int specialPass, String provider, int freeTalk, double planCost, int speed,
                        String lineType) {
        super(specialPass, provider, freeTalk, planCost);
        this.speed = speed;
        this.lineType = lineType;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public String getLineType() {
        return lineType;
    }

    public void setLineType(String lineType) {
        this.lineType = lineType;
    }

}
