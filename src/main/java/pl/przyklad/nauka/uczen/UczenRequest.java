package pl.przyklad.nauka.uczen;

import jakarta.validation.constraints.NotBlank;
import lombok.Builder;
import lombok.Data;

/*
 * @RequiredArgsConstructor - pozwala na wygenerowanie konstruktora z wymaganymi argumentami czyli oznaczonymi jako final 
 * @NoArgsConstructor - pozwala na wygenerowanie konstruktora bez argumentowego
 * @Data - pozwala na wygenerowanie seterów i geterów i konstruktorów itp. Zastępuje adnotacje:
 * @Getter, @Setter, @RequiredArgsConstructor, @ToString, @EqualsAndHashCode, @Value`
 * @Builder - dzieki tej adnotacji możemy wygenerować wzorzec bilder, ktory pozwala na łatwe tworzenie obiektów
 * */
@Data
/*
 * @Builder - sposob tworzenia obiektów poprzez łańcuchowe wywołanie metod
 * ustawiających pola. Zamiast tworzyć konstruktor z wieloma parametrami Uczen u
 * = Uczen.builder().imie("Anna").nazwisko("Kowalska").build();
 */
@Builder
public class UczenRequest {
	/*
	 * ADNOTACJA mówiąca, że znak nie może być pusty. Dokładniej mówiąc MUSI
	 * ZAWIERAĆ CO NAJMIEJ 1 ZNAK zawierać co najmniej 1 znak niebędący spacją.
	 */
	@NotBlank
	private String imie;

	@NotBlank
	private String nazwisko;
}
/*
 * record - pozwala na definiowanie klas , którym głównym celem jest
 * przechowywanie danych Rekordy w Javie są specjalnym rodzajem klasy, która
 * jest immutable (nie mutowana). Oznacza to, że po utorzeniu instacji takiej
 * klasy nie można zmieniać wartości jej pól.
 */

//public record UczenRequest( @NotBlank String imie, @NotBlank String nazwisko ) {}
