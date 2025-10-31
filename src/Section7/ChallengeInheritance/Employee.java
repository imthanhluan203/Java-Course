package Section7.ChallengeInheritance;

public class Employee extends Worker {
    private long employeeId;
    private String hireDate;
    private static long employeeId_static = 1;
    public Employee(String name, String birthDate, String hireDate) {
        super(name, birthDate);
        this.employeeId = Employee.employeeId_static++;
        this.hireDate = hireDate;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "employeeId=" + employeeId +
                ", hireDate='" + hireDate + '\'' +
                "} " + super.toString();
    }
}
