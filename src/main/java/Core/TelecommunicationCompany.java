package Core;

public class TelecommunicationCompany {

    private int specialPass;
    private String companyName;
    private long phoneNum;
    private String email;

    public TelecommunicationCompany(int specialPass, String companyName, long phoneNum, String email) {
        this.specialPass = specialPass;
        this.companyName = companyName;
        this.phoneNum = phoneNum;
        this.email = email;
    }

    public int getSpecialPass() {
        return specialPass;
    }
    public void setSpecialPass(int specialPass) {
        this.specialPass = specialPass;
    }
    public String getCompanyName() {
        return companyName;
    }
    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }
    public long getPhoneNum() {
        return phoneNum;
    }
    public void setPhoneNum(long phoneNum) {
        this.phoneNum = phoneNum;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }

}
