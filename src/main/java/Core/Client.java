package Core;

import java.math.BigInteger;

public class Client {

	private String idNum;
	private int afm;
	private String fullName;
	private String status; // individual, student, freelancer
	private String address;
	private long phoneNum;
	private String email;

	public Client(String idnum, int afm, String fullName, String address, String status,long phoneNum, String email) {
		this.idNum = idnum;
		this.afm = afm;
		this.fullName = fullName;
		this.address = address;
		this.status = status;
		this.phoneNum = phoneNum;
		this.email = email;
	}

	public String getIdNum() {
		return idNum;
	}

	public void setIdNum(String idNum) {
		this.idNum = idNum;
	}

	public int getAfm() {
		return afm;
	}

	public void setAfm(int afm) {
		this.afm = afm;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public long getPhoneNum() {
		return phoneNum;
	}

	public void setPhoneNum(long phoneNum) {

	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

}
