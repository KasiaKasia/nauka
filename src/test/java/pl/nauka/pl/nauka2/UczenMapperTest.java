package pl.nauka.pl.nauka2;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.List;

import org.junit.jupiter.api.Test;

import pl.przyklad.nauka.uczen.Uczen;
import pl.przyklad.nauka.uczen.UczenMapper; 

class UczenMapperTest {
	private final UczenMapper underTest = new UczenMapper();

	@Test
	void test() {
		fail("Not yet implemented");
	}
	// metoda testuje mapowanie jednego obiektu Uczen na jego DTO.
    @Test
    void mapToRequest(){
        // arrange - tu powinno być przygotowanie danych i ewentualnych zależności potrzebnych do testów
        var uczenTest = Uczen.builder()
                .id(123)
                .imie("Tomasz")
                .nazwisko("Holenderski")
                .build();

        // act - tu jest przeprowadzany test. Czyli najcześciej wywołanie jakiejś metody
        var actual = underTest.mapToDto(uczenTest);

        // assert - sprawdzmy wyniki testów
        assertThat(actual.getId()).isEqualTo(uczenTest.getId());
        assertThat(actual.getImie()).isEqualTo(uczenTest.getImie());
        assertThat(actual.getNazwisko()).isEqualTo(uczenTest.getNazwisko());
    }
    
	// metoda testuje mapowanie listy uczniów (List<Uczen>) na listę DTO.
    @Test
    void mapToResponseCollection(){
        //arrange
        var uczenTest1 = Uczen.builder()
                .id(1)
                .imie("Władek")
                .nazwisko("Szeran")
                .build();

        var uczenTest2 = Uczen.builder()
                .id(2)
                .imie("Tomasz")
                .nazwisko("Holenderski")
                .build();

        var uczenTest3 = Uczen.builder()
                .id(3)
                .imie("Katarzyna")
                .nazwisko("Kowalska")
                .build();

        var uczniowie = List.of(uczenTest1, uczenTest2, uczenTest3);

        //act
        var actuals = underTest.mapToDto(uczniowie);

        //assert
        assertThat(actuals.size()).isEqualTo(uczniowie.size());
        assertThat(actuals).allSatisfy(actual->{
            var expected = uczniowie.stream()
                    .filter(c->c.getId().equals(actual.getId()))
                    .findFirst()
                    .orElseThrow(()-> new RuntimeException("Mapped `uczen` not found"));

            assertThat(actual.getImie()).isEqualTo(expected.getImie());
            assertThat(actual.getNazwisko()).isEqualTo(expected.getNazwisko());
        });
        
        
    }
    
	// metoda testuje mapowanie ucznia bez ID
    @Test
    void mapTo(){
        //arrange
        var uczenRequest = Uczen.builder()
        		.imie("Władek")
                .nazwisko("Szeran")
                .build();

        //act
        var actual = underTest.mapToDto(uczenRequest);

        //assert
        assertThat(actual.getImie()).isEqualTo(uczenRequest.getImie());
        assertThat(actual.getNazwisko()).isEqualTo(uczenRequest.getNazwisko());
    }
}
