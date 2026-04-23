public class RentalAgreement {
    private String agreementNumber;
    private Booking booking;
    private String terms;
    private double totalAmount;

    public RentalAgreement(String agreementNumber, Booking booking, String terms, double totalAmount) {
        this.agreementNumber = agreementNumber;
        this.booking = booking;
        this.terms = terms;
        this.totalAmount = totalAmount;
    }

    public String getAgreementNumber() {
        return agreementNumber;
    }

    public void setAgreementNumber(String agreementNumber) {
        this.agreementNumber = agreementNumber;
    }

    public Booking getBooking() {
        return booking;
    }

    public void setBooking(Booking booking) {
        this.booking = booking;
    }

    public String getTerms() {
        return terms;
    }

    public void setTerms(String terms) {
        this.terms = terms;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "RentalAgreement{" +
                "agreementNumber='" + agreementNumber + '\'' +
                ", booking=" + booking +
                ", terms='" + terms + '\'' +
                ", totalAmount=" + totalAmount +
                '}';
    }
}