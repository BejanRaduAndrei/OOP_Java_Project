public class Invoice {
    private String invoiceNumber;
    private Booking booking;
    private double amount;
    private String issueDate;

    public Invoice(String invoiceNumber, Booking booking, double amount, String issueDate) {
        this.invoiceNumber = invoiceNumber;
        this.booking = booking;
        this.amount = amount;
        this.issueDate = issueDate;
    }

    public String getInvoiceNumber() {
        return invoiceNumber;
    }

    public void setInvoiceNumber(String invoiceNumber) {
        this.invoiceNumber = invoiceNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public String getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(String issueDate) {
        this.issueDate = issueDate;
    }

    @Override
    public String toString() {
        return "Invoice{" +
                "invoiceNumber='" + invoiceNumber + '\'' +
                ", booking=" + booking +
                ", amount=" + amount +
                ", issueDate='" + issueDate + '\'' +
                '}';
    }
}