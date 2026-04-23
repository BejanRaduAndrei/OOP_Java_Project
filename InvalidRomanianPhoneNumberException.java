public class InvalidRomanianPhoneNumberException extends RuntimeException {
    public InvalidRomanianPhoneNumberException(String invalidPhoneNumber) {
        super("Numar de telefon invalid pentru Romania: " + invalidPhoneNumber + ". Formate acceptate: 07XXXXXXXX, +40XXXXXXXXX, 0040XXXXXXXXX.");
    }
}
