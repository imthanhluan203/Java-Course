package Section7;

public class Customer {
    private String name;
    private int creditLimit;
    public Customer(){
        this("Nguyen Thanh Luan",3_000_000,"foryou@gmail.com");
        System.out.println("Default constructor is called");
    }
    public Customer(String name,String email){
        this(name,333,email);
        System.out.println("Name and Email constructor is called");
    }
    public Customer(String name, int creditLimit, String email) {
        this.name = name;
        this.creditLimit = creditLimit;
        this.email = email;
    }

    private String email;

    public String getName() {
        return name;
    }

    public int getCreditLimit() {
        return creditLimit;
    }

    public String getEmail() {
        return email;
    }
    public void describe(){
        System.out.println("Your Name: "+this.name);
        System.out.println("Your Credit Limit: "+this.creditLimit);
        System.out.println("Your Email: "+this.email);
    }
}
