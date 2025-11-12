package Section16.ImmutableChallenge.bank;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class BankCustomer {
    private static int lastCustomerId = 10_000_000;
    private final String name;
    private int customerId;
    private final List<BankAccount> accounts = new ArrayList<>();

    BankCustomer(String name,double checkingAmount,double savingAmounts) {
        this.name = name;
        this.customerId = lastCustomerId++;
        accounts.add(new BankAccount(BankAccount.AccountType.CHECKING,checkingAmount));
        accounts.add(new BankAccount(BankAccount.AccountType.SAVINGS,savingAmounts));
    }



    public void testCase(){
        //accounts = new ArrayList<>();
    }
    public String getName() {
        return name;
    }

    public String getCustomerId() {
        return "%015d".formatted(customerId);
    }

    public List<BankAccount> getAccounts() {
        return List.copyOf(accounts);
    }
    public BankAccount getAccount(BankAccount.AccountType type){
        for(var account : accounts){
            if(account.getAccountType() == type){
                return account;
            }
        }
        return null;
    }
    @Override
    public String toString() {
        String[] accountString = new String[accounts.size()];
        Arrays.setAll(accountString,i->accounts.get(i).toString());
        return "Customer: %s (id:%015d)%n\t%s%n"
                .formatted(name,customerId,String.join("\n\t",accountString));
    }
}
