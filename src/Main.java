import manager.BankManager;
import model.Account;
import model.AccountInfo;
import model.Address;
import java.util.Timer;
import java.util.TimerTask;
import java.math.BigDecimal;
import java.time.LocalDate;



public class Main {
    static BankManager bankManager = new BankManager();
    public static void main(String[] args) {

        // Account1
        Account account1 = new Account();
        AccountInfo accountInfo1 = new AccountInfo();
        accountInfo1.setAccount_no("1");
        accountInfo1.setName("ABC");
        accountInfo1.setDateOfopening(LocalDate.of(2019,04,19));
        account1.setAccount_no(accountInfo1);
        Address address1 = new Address();
        address1.setCity("X3");
        address1.setStreet_address("MNO");
        address1.setPincode("877829");
        address1.setState("f5");
        account1.setAddress(address1);
        account1.setPhone_no("8721871912");
        account1.setBalance(new BigDecimal(0.0));
        account1.setAadhar("809128301283");

        //Account2
        Account account2 = new Account();
        AccountInfo accountInfo2 = new AccountInfo();
        accountInfo2.setAccount_no("2");
        accountInfo2.setName("DEF");
        accountInfo2.setDateOfopening(LocalDate.of(2018,11,21));
        account2.setAccount_no(accountInfo2);
        Address address2 = new Address();
        address2.setCity("P3");
        address2.setStreet_address("PQR");
        address2.setPincode("277829");
        address2.setState("a5");
        account2.setAddress(address2);
        account2.setPhone_no("8721871913");
        account2.setBalance(new BigDecimal(0.0));
        account2.setAadhar("808128301283");

        //Account3
        Account account3 = new Account();
        AccountInfo accountInfo3 = new AccountInfo();
        accountInfo3.setAccount_no("3");
        accountInfo3.setName("GHI");
        accountInfo3.setDateOfopening(LocalDate.of(2015,11,21));
        account3.setAccount_no(accountInfo3);
        Address address3 = new Address();
        address3.setCity("P4");
        address3.setStreet_address("TUV");
        address3.setPincode("277830");
        address3.setState("a8");
        account3.setAddress(address3);
        account3.setPhone_no("8721871914");
        account3.setBalance(new BigDecimal(0.0));
        account3.setAadhar("808128301284");

        bankManager.addAccount(account1);
        bankManager.addAccount(account2);
        bankManager.addAccount(account3);

        bankManager.deposit("1",100);
        bankManager.deposit("2",50);
        bankManager.deposit("3",150);

        bankManager.withdraw("1",20);
        bankManager.withdraw("2",30);
        bankManager.withdraw("3",70);

        assert account1.getBalance().longValue() == 80;
        assert account2.getBalance().longValue() == 20;
        assert account3.getBalance().longValue() == 80;

        Timer timer1 = new Timer();
        TimerTask task1 = new CheckAccounts();

        timer1.schedule(task1,0, 1000*24*60*60);

        Timer timer2 = new Timer();
        TimerTask task2 = new AddInterest();
        timer2.schedule(task2,0,1000L*60*60*24*30);
    }
    static class CheckAccounts extends TimerTask
    {

        @Override
        public void run() {
            bankManager.levyChargeIfBalance0();
            //System.out.println("Running");
        }
    }
    static class AddInterest extends TimerTask
    {

        @Override
        public void run() {
            bankManager.rateOfInterestAtTheEndOfTheMonth();
        }
    }

}