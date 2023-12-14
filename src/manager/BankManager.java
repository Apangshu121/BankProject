package manager;

import model.Account;
import service.BankService;

import java.math.BigDecimal;
import java.util.*;

public class BankManager {
    BankService bankService = new BankService();

    public String getAccountBalance(String account_no)
    {
        return bankService.getAccountBalance(account_no);
    }

    public void withdraw(String account_no, long value)
    {
        bankService.withdraw(account_no,value);
    }

    public void deposit(String account_no, long value)
    {
        bankService.deposit(account_no,value);
    }

    public void addAccount(Account account)
    {
        bankService.addAccount(account);
    }

    public boolean isKYCDone(String account_no)
    {
        return bankService.isKYCDone(account_no);
    }

    public List<Account> getZeroBalanceAccounts(){
        return bankService.getZeroBalanceAccounts();
    }

    public void levyChargeIfBalance0(){
        bankService.levyChargeIfBalance0();
    }

    public void rateOfInterestAtTheEndOfTheMonth() { bankService.rateOfInterestAtTheEndOfTheMonth();}
}
