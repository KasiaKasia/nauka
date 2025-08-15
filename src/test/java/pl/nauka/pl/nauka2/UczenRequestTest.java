package pl.nauka.pl.nauka2;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import java.util.stream.Stream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import jakarta.validation.Validation;
import jakarta.validation.Validator;
import pl.przyklad.nauka.uczen.UczenRequest;
public class UczenRequestTest {	
	
    private final Validator validator = Validation.buildDefaultValidatorFactory()
            .getValidator();
    
    
    @Test
    void imieeIsNull() {
        var request = UczenRequest.builder()
                .imie(null)
                .build();
        //act
        var violations = validator.validate(request);
        assertThat(violations).isNotEmpty();
    }
    
    /*
     * @ParameterizedTest oznacza, że ten sam test wykona się kilka razy z różnymi danymi wejściowymi.
     * */
    @ParameterizedTest
    @MethodSource("przypadkiTestoweImion")
    void validateImie(String imie, boolean expected) {
        var request = UczenRequest.builder()
                .imie(imie)
                .nazwisko("Doe")
                .build();
        var violations = validator.validate(request);

        assertThat(violations.isEmpty()).isEqualTo(expected);
    }

    @ParameterizedTest
    @MethodSource("przypadkiTestoweImion")
    void validateNazwisko(String nazwisko, boolean expected){
        var request = UczenRequest.builder()
                .imie("John")
                .nazwisko(nazwisko)
                .build();
        var violations = validator.validate(request);

       assertThat(violations.isEmpty()).isEqualTo(expected);
    }
    private static Stream<Arguments> przypadkiTestoweImion() {
    	// Stream – to "strumień" elementów, czyli sekwencja danych, które można przetwarzać jedna po drugiej.
        return Stream.of(
                Arguments.of(null, false), // oznacza: imię = null, oczekujemy, że walidacja nie przejdzie (false).
                Arguments.of("", false),
                Arguments.of("TestName", true)
        );
    }

}
