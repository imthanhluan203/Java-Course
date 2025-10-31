package Section7.ChallengeInheritance;

public class Main {
    public static void main(String[] args) {
        Employee Tim = new Employee("Tim","11/11/1985","01/01/2020");
        SalariedEmployee Luan = new SalariedEmployee("Luan","13/01/2003","13/01/2025", 1000,false);
        System.out.println(Luan.collectPay());
        Luan.retire();
        System.out.println(Luan);
        System.out.println(Luan.collectPay());
        System.out.println("Age = "+ Luan.getAge());
        System.out.println("Pay = "+ Luan.collectPay());
    }
}
