package Section7;

public class BankAccount {
    private int accountNumber;
    private int accountBallance;
    private String customerName;
    private  String email;
    private String number;
    public BankAccount(){
        this(7102,9999,"Nam Thuan","ntluan21@gmail.com","0367102730");
        System.out.println("Defaul constructor is called");
    }
    public BankAccount(int accountNumber, int accountBallance, String customerName, String email, String number) {
        this.accountNumber = accountNumber;
        this.accountBallance = accountBallance;
        this.customerName = customerName;
        this.email = email;
        this.number = number;
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(int accountNumber) {
        this.accountNumber = accountNumber;
    }

    public int getAccountBallance() {
        return accountBallance;
    }

    public void setAccountBallance(int accountBallance) {
        this.accountBallance = accountBallance;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }
    public void depositing(int number){
        this.accountBallance += number;
    }
    public void withdrawing(int number){
        if(number < 0 || this.accountBallance - number < 0){
            System.out.println("Invalid value");
            return;
        }
        this.accountBallance -= number;
    }
    public void describe(){
        System.out.println("Account Number:" + this.accountNumber);
        System.out.println("Account Balance:" + this.accountBallance);
        System.out.println("Customer Name:" + this.customerName);
        System.out.println("Email:" + this.email);
        System.out.println("Number:" + this.number);
    }
}
