package carRentalSystem;

public class CustumersConstructor {
    int customerId;
    String firstName, lastName, number;

    public CustumersConstructor(int customerId, String firstName, String lastName, String number) {
        this.customerId = customerId;
        this.firstName = firstName;
        this.lastName = lastName;
        this.number = number;
    }

    public int getCustomerId() {
        return customerId;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getNumber() {
        return number;
    }

    public void setCustomerId(int customerId) {
        this.customerId = customerId;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setNumber(String number) {
        this.number = number;
    }
}
