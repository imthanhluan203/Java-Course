package Section16.ImmutableChallenge.dto;

public class Transaction {
    private int rountingNumber;
    private long transactionId;
    private int customerId;
    private double amount;

    public Transaction(int rountingNumber, long transactionId, int customerId, double amount) {
        this.rountingNumber = rountingNumber;
        this.transactionId = transactionId;
        this.customerId = customerId;
        this.amount = amount;
    }

    public int getRountingNumber() {
        return rountingNumber;
    }

    public void setRountingNumber(int rountingNumber) {
        this.rountingNumber = rountingNumber;
    }

    public long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(long transactionId) {
        this.transactionId = transactionId;
    }

    public int getCustomerId() {
        return customerId;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "%015d:%020d:%015d:%012.2f"
                .formatted(rountingNumber,customerId,transactionId,amount);
    }
}
