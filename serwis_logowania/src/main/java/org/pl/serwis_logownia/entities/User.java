package org.pl.serwis_logownia.entities;

import lombok.*;
import java.util.List;

@RequiredArgsConstructor
@Getter
@Setter
public class User {
    Long ID_Użytkownika;
    String Imię;
    String Nazwisko;
    String Login;
    String Hasło;
    List<Role> Rola;
}