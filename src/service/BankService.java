package service;

import model.Account;
import model.Transaction;
import repository.BankRepository;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class BankService {

    Map<String,Account> bank = BankRepository.bankMap;
    Map<String,List<Transaction>> transactions = BankRepository.transactions;
    public void addAccount(Account account)
    {
        bank.put(account.getAccount_info().getAccount_no(),account);
    }

    public List<Account> getZeroBalanceAccounts(){

        List zerobalanceaccounts = new ArrayList<>();

        for(Map.Entry<String,Account> hm:bank.entrySet())
        {
            Account account = hm.getValue();
            BigDecimal balance = account.getBalance();

            if(balance.compareTo(BigDecimal.ZERO)==0)
                zerobalanceaccounts.add(account);
        }

        return  zerobalanceaccounts;
    }

    public void levyChargeIfBalance0() {
        List<Account> zeroBalanceAccounts = getZeroBalanceAccounts();
        BigDecimal levy = new BigDecimal(500);

        for(Account account:zeroBalanceAccounts)
        {
            account.setBalance(account.getBalance().subtract(levy));
        }
    }

    public String getAccountBalance(String account_no){
        synchronized (this){
            Account account = bank.get(account_no);
            BigDecimal balance = account.getBalance();
            return balance.toString();
        }
    }

    public void deposit(String account_no,long value){

        Account account = bank.get(account_no);

        synchronized (this) {
            BigDecimal val = new BigDecimal(value);
            account.setBalance(account.getBalance().add(val));
            saveTransaction(account,account_no,"Credited",false,val);
        }

    }

    public void withdraw(String account_no, long value)
    {
        Account account = bank.get(account_no);
        synchronized (this){
            BigDecimal val = new BigDecimal(value);
            if(val.compareTo(account.getBalance())==1)
                System.out.println("Insufficient balance");
            else{
                account.setBalance(account.getBalance().subtract(val));
                saveTransaction(account,account_no,"Debited",true,val);
            }
        }
    }

    public void saveTransaction(Account account, String account_no,String comment,boolean debited,BigDecimal val)
    {
        Transaction t = new Transaction();
        t.setAccount_no(account_no);
        t.setAmount(val);
        t.setComment("Credited");
        t.setDebit(false);
        t.setBalanceAsOn(account.getBalance());
        t.setDateTimeOfTransaction(LocalDateTime.now());

        if(transactions.containsKey(account_no)){
            transactions.get(account_no).add(t);
        }else{
            List<Transaction> al = new ArrayList<>();
            al.add(t);
            transactions.put(account_no,al);
        }
    }
    public boolean isKYCDone(String account_no)
    {
        Account account = bank.get(account_no);
        String aadhar = account.getAadhar();
        synchronized (this)
        {
            if(aadhar== null)
                return false;
            else
                return true;
        }
    }

    public void rateOfInterestAtTheEndOfTheMonth()
    {
        for(String account_no:bank.keySet())
        {
            List<Transaction> trasactions = transactions.get(account_no);

            BigDecimal interest = trasactions.stream().collect(Collectors.groupingBy(t->t.getDateTimeOfTransaction().toLocalDate()
            ,Collectors.reducing((t1,t2)-> t1.getDateTimeOfTransaction().isAfter(t2.getDateTimeOfTransaction()) ? t1:t2))).values().stream()
                    .filter(Optional::isPresent).map(Optional::get).map(t->t.getBalanceAsOn().multiply(BigDecimal.valueOf(6.0/365))).reduce(BigDecimal.ZERO,BigDecimal::add);

            Account account = bank.get(account_no);
            account.setBalance(account.getBalance().add(interest));
        }
    }

}
