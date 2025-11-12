package Section16.ImmutableChallenge;

import Section16.ImmutableChallenge.bank.Bank;
import Section16.ImmutableChallenge.bank.BankAccount;
import Section16.ImmutableChallenge.bank.BankCustomer;

public class Main {
    public static void main(String[] args) {
        Bank bank = new Bank(3214567);
        bank.addCustomer("Joe",500.00,10000.00);

        BankCustomer joe = bank.getCustomer("000000010000000");
        System.out.println(joe);
        if(bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING,200)){
            System.out.println(joe);
        }
        if(bank.doTransaction(joe.getCustomerId(), BankAccount.AccountType.CHECKING,-300)){
            System.out.println(joe);
        }
        BankAccount checking = joe.getAccount(BankAccount.AccountType.CHECKING);
        var transactions = checking.getTransactions();
        transactions.forEach((k,v)->System.out.println(k + ":" + v));

        transactions.forEach((k,v)->System.out.println(k + ":" + v));
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().clear();
        System.out.println("-".repeat(30));
        joe.getAccount(BankAccount.AccountType.CHECKING).getTransactions().forEach(
                (k,v)->System.out.println(k + ":" + v)
        );
    }
}
