package model;
import java.math.BigDecimal;
public class Account {
    private AccountInfo account_info;
    private Address address;
    private String phone_no;
    private BigDecimal balance;
    private String aadhar;

    public AccountInfo getAccount_info() {
        return account_info;
    }

    public void setAccount_no(AccountInfo account_info) {

        if(this.account_info==null)
            this.account_info = account_info;
        else
            System.out.println("Access Denied");
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    public String getPhone_no() {
        return phone_no;
    }

    public void setPhone_no(String phone_no) {
        this.phone_no = phone_no;
    }

    public BigDecimal getBalance() {
        synchronized (this){
            return balance;
        }
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public String getAadhar() {
        return aadhar;
    }

    public void setAadhar(String aadhar) {
        this.aadhar = aadhar;
    }

}

