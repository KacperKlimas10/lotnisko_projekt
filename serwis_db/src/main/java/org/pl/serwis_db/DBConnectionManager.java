package org.pl.serwis_db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnectionManager {
    private static final String URL = "jdbc:postgresql://localhost:5432/system_lotnisko";
    private static final String USER = "ekipa";
    private static final String PASSWORD = "1234";

    // mozna to wywalic potem (podobno nowsze wersje JDBC nie wymagaja tego stepu)
    static {
        try {
            Class.forName("org.postgresql.Driver");
        } catch (ClassNotFoundException e) {
            throw new RuntimeException("PostgreSQL Driver not found!", e);
        }
    }

    // metoda do proby laczenia z baza danych
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }

    // nwm czy cos jeszcze tu bedzie potrzebne ide spac
}
