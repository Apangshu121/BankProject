package model;
import java.time.LocalDate;
public class AccountInfo {
    private String account_no;
    // LocalDate solves the timezone issues and it is also immutable.
    private LocalDate dateOfopening;
    private String name;

    public String getAccount_no() {
        return account_no;
    }

    public void setAccount_no(String account_no) {
        this.account_no = account_no;
    }

    public LocalDate getDateOfopening() {
        return dateOfopening;
    }

    public void setDateOfopening(LocalDate dateOfopening) {
        this.dateOfopening = dateOfopening;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}

