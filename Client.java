public class Client {
    private String name;
    private String email;
    private String cnp;
    private String phoneNumber;

    public Client(String name, String email, String cnp , String phoneNumber) {
        this.name = name;
        this.email = email;
        this.cnp = cnp;
        assignValidatedPhoneNumber(phoneNumber);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCnp() {
        return cnp;
    }

    public void setCnp(String cnp) {
        this.cnp = cnp;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        assignValidatedPhoneNumber(phoneNumber);
    }

    private void assignValidatedPhoneNumber(String phoneNumber) {
        if (!isValidRomanianPhoneNumber(phoneNumber)) {
            throw new InvalidRomanianPhoneNumberException(phoneNumber);
        }
        this.phoneNumber = normalizePhoneNumber(phoneNumber);
    }

    private boolean isValidRomanianPhoneNumber(String phoneNumber) {
        if (phoneNumber == null) {
            return false;
        }
        String normalized = normalizePhoneNumber(phoneNumber);
        return normalized.matches("^0[0-9]{9}$");
    }

    private String normalizePhoneNumber(String phoneNumber) {
        String normalized = phoneNumber.replace(" ", "").replace("-", "");
        if (normalized.startsWith("+40") && normalized.length() == 12) {
            return "0" + normalized.substring(3);
        }
        if (normalized.startsWith("0040") && normalized.length() == 13) {
            return "0" + normalized.substring(4);
        }
        return normalized;
    }

    @Override
    public String toString() {
        return "Client{" +
                "name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", cnp='" + cnp + '\'' +
                ", phoneNumber='" + phoneNumber + '\'' +
                '}';
    }
}
