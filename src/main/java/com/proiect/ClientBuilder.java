package com.proiect;

public class ClientBuilder {
    private String name;
    private String email;
    private String cnp;
    private String phoneNumber;

    public ClientBuilder() {}

    public ClientBuilder withName(String name) {
        this.name = name;
        return this;
    }

    public ClientBuilder withEmail(String email) {
        this.email = email;
        return this;
    }

    public ClientBuilder withCnp(String cnp) {
        this.cnp = cnp;
        return this;
    }

    public ClientBuilder withPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
        return this;
    }

    public Client build() {
        return new Client(name, email, cnp, phoneNumber);
    }
}
