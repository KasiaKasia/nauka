package pl.nauka.pl.nauka2;

import static pl.nauka.pl.nauka2.UczenTestUtility.*;

import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;

import org.mockito.junit.jupiter.MockitoExtension;

import jakarta.persistence.EntityNotFoundException;
import pl.przyklad.nauka.uczen.UczenMapper;
import pl.przyklad.nauka.uczen.UczenRepository;
import pl.przyklad.nauka.uczen.UczenRequest;
import pl.przyklad.nauka.uczen.UczenService;

import org.mockito.InjectMocks;
import org.mockito.Mock;

@ExtendWith(MockitoExtension.class)
class UczenServiceTest {

	/*
	 * @Mock i @InjectMocks - taka notacja sprawi, że mocki będą wstrzykiwane jako
	 * zależności do obiektu testowanego
	 */
	@Mock
	private UczenRepository repository;

	@Mock
	private UczenMapper mapper;

	@InjectMocks
	private UczenService underTest;

	/*
	 * Metoda oznaczona adnotacją @BeforeAll jest wywoływana TYLKO RAZ przed
	 * uruchomieniem wszystkich testów w danej klasie Może ona zostać użyta do
	 * inicjalizacji środowiska testowego lub innych zasobów, które będą
	 * wykrzystywane przez wyszystkie testy w danej klasie.
	 */
	/*
	 * @BeforeAll void setUp() { repository = mock(UczenRepository.class); mapper =
	 * mock(UczenMapper.class); underTest = new UczenService(repository, mapper); }
	 */

	@Test
	void findById() {
		var id = TOMASZ.getId();
		// kiedy wywołamy metodę findById na repository równą eq(id) to w tedy zwróć
		// Optional.of(TOMASZ)
		// Optional.of - zakłada że wartość nie jest NULL Jeśli przekażesz null, wyrzuci
		// NullPointerException
		when(repository.findById(eq(id))).thenReturn(Optional.of(TOMASZ));
		when(mapper.mapToDto(TOMASZ)).thenReturn(TOMASZ_RESPONSE);

		var uczenResponseTest = underTest.findById(id);
		// usingRecursiveComparison - głebokie porówanie Czyli porównuje wszystkie pola
		// rekurencyjnie (nawet jak ma pola zagnieżdzone obiekty w środku)
		assertThat(uczenResponseTest).usingRecursiveComparison().isEqualTo(TOMASZ_RESPONSE);
	}

	@Test
	void throwExceptionForNotExistingCustomerFindById() {
		var actualException = assertThrows(EntityNotFoundException.class, () -> {
			underTest.findById(1234);
		});

		assertThat(actualException.getMessage()).isEqualTo("Student with given id '1234' does not exist");
	}

	@Test
	void findAll() {
		var uczniowie = List.of(TOMASZ, KATARZYNA);
		var uczenrResponses = List.of(TOMASZ_RESPONSE, KATARZYNA_RESPONSE);

		when(repository.findAll()).thenReturn(uczniowie);
		when(mapper.mapToDto(uczniowie)).thenReturn(uczenrResponses);

		var uczenResponseTest = underTest.findAll();
		// containsExactly - sprawdza, czy kolekcja zawiera dokładnie określone
		// elementy, w podanej kolejności
		assertThat(uczenResponseTest).containsExactly(TOMASZ_RESPONSE, KATARZYNA_RESPONSE);
	}

	@Test
	void save() {
		when(mapper.mapTo(KATARZYNA_REQUEST)).thenReturn(KATARZYNA);
		when(repository.save(KATARZYNA)).thenReturn(KATARZYNA);
		when(mapper.mapToDto(KATARZYNA)).thenReturn(KATARZYNA_RESPONSE);

		var actual = underTest.save(KATARZYNA_REQUEST);

		assertThat(actual).usingRecursiveComparison().isEqualTo(KATARZYNA_RESPONSE);
	}

	@Test
	void update() {
		var id = TOMASZ.getId();
		when(repository.findById(eq(id))).thenReturn(Optional.of(TOMASZ));
		when(mapper.mapTo(TOMASZ, TOMASZ_REQUEST)).thenReturn(TOMASZ);
		when(repository.save(TOMASZ)).thenReturn(TOMASZ);
		when(mapper.mapToDto(TOMASZ)).thenReturn(TOMASZ_RESPONSE);

		var actual = underTest.update(id, TOMASZ_REQUEST);

		assertThat(actual).usingRecursiveComparison().isEqualTo(TOMASZ_RESPONSE);
	}

	@Test
	void throwExceptionForNotExistingCustomerInUpdate() {
		var actualException = assertThrows(EntityNotFoundException.class, () -> {
			underTest.update(1234, UczenRequest.builder().build());
		});
		// Sprawdza, czy komunikat błędu jest dokładnie taki, jak oczekiwano
		assertThat(actualException.getMessage()).isEqualTo("Student with given id '1234' does not exist");
	}

}
