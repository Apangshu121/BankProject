package repository;

import model.Account;
import model.Transaction;

import java.util.HashMap;
import java.util.List;

public class BankRepository {
    public static HashMap<String, Account> bankMap = new HashMap<>();
    public static HashMap<String, List<Transaction>> transactions = new HashMap<>();

}
