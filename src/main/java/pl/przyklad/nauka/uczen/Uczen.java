package pl.przyklad.nauka.uczen;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import pl.przyklad.nauka.user.User;
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
/*
 * @Entity - oznacza że dana KLASA REPREZENTUJE TABELE w bazie danych Informuje
 * JPA / Hibernate że ta KLASA ma być MAPOWANA na TABELĘ w relacyjnej bazie
 * danych KAŻDE POLE W KLASIE (jeśli NIE JEST OZNACZONE jako @Transient ) jest
 * traktowane jako KOLUMNA W TABELI Dzieki temu możesz zapisywać, odczytywać,
 * usuwać i aktualizować dane w bazie za pomocą obiektów Java
 */
@Entity
@Table(name = "uczniowie")
public class Uczen {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String imie;

	private String nazwisko;
	
    private String klasa;
    
    @OneToOne
    @JoinColumn(name = "username", referencedColumnName = "username")
    private User user;
}
