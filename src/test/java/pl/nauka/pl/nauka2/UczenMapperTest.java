package pl.nauka.pl.nauka2;

import static pl.nauka.pl.nauka2.UczenTestUtility.*;

import static org.assertj.core.api.Assertions.assertThat;
//import static org.junit.jupiter.api.Assertions.*;
import java.util.List;

import org.junit.jupiter.api.Test;

import pl.przyklad.nauka.uczen.Uczen;
import pl.przyklad.nauka.uczen.UczenMapper;

class UczenMapperTest {
	private final UczenMapper underTest = new UczenMapper();

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

	// metoda testuje mapowanie jednego obiektu Uczen na jego DTO.
	@Test
	void mapToRequest() {
		// arrange - tu powinno być przygotowanie danych i ewentualnych zależności
		// potrzebnych do testów

		// act - tu jest przeprowadzany test. Czyli najcześciej wywołanie jakiejś metody
		var actual = underTest.mapToDto(TOMASZ);

		// assert - sprawdzmy wyniki testów
		assertThat(actual.getId()).isEqualTo(TOMASZ.getId());
		assertThat(actual.getImie()).isEqualTo(TOMASZ.getImie());
		assertThat(actual.getNazwisko()).isEqualTo(TOMASZ.getNazwisko());
	}

	// metoda testuje mapowanie listy uczniów (List<Uczen>) na listę DTO.
	@Test
	void mapToResponseCollection() {
		// arrange

		var uczniowie = List.of(WLADEK, TOMASZ, KATARZYNA);

		// act
		var actuals = underTest.mapToDto(uczniowie);

		// assert
		assertThat(actuals.size()).isEqualTo(uczniowie.size());
		assertThat(actuals).allSatisfy(actual -> {
			var expected = uczniowie.stream().filter(c -> c.getId().equals(actual.getId())).findFirst()
					.orElseThrow(() -> new RuntimeException("Mapped `uczen` not found"));

			assertThat(actual.getImie()).isEqualTo(expected.getImie());
			assertThat(actual.getNazwisko()).isEqualTo(expected.getNazwisko());
		});

	}

	// metoda testuje mapowanie ucznia bez ID
	@Test
	void mapTo() {
		// arrange
		var uczenRequest = Uczen.builder().imie("Władek").nazwisko("Szeran").build();

		// act
		var actual = underTest.mapToDto(uczenRequest);

		// assert
		assertThat(actual.getImie()).isEqualTo(uczenRequest.getImie());
		assertThat(actual.getNazwisko()).isEqualTo(uczenRequest.getNazwisko());
	}
}
