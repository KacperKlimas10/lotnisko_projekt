package org.pl.serwis_logownia;

public enum Role {
    ADMIN("Administrator"),
    PILOT("Pilot"),
    OCHRONA("Ochroniarz"),
    OBSLUGA("Obsluga Klienta");

    private final String description;

    Role(String description){
        this.description = description;
    }

    public String getDescription(){
        return description;
    }
}
