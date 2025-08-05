package pl.przyklad.nauka.uczen;

import org.springframework.stereotype.Component;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/*
 * @Component - adnotacja @Component w Spring oznacza, że klasa jest komponentem zarządzającym przez Springa 
 * Czyli Spring sam utworzy obiekt tej klasy i będzie nim zarządzał jako bean
 * Rejestruje jako Spring Bean 
 * Pozwala wstrzyknąć ten komponent do innych klas (np.: przez @Autowired, konstruktor @Inject)
 * 
 * */
@Component
public class UczenMapper {
	public UczenResponse mapToDto(Uczen uczen) {
		var uczenDto = new UczenResponse();
		uczenDto.setId(uczen.getId());
		uczenDto.setImie(uczen.getImie());
		uczenDto.setNazwisko(uczen.getNazwisko());
		return uczenDto;
	}

	public List<UczenResponse> mapToDto(Collection<Uczen> uczen) {

		// stream()- Tworzy strumień. Czyli sekwencyjne przetwarzanie elementów
		return uczen.stream().map(this::mapToDto) // mapuje na to -> UczenResponse mapToDto Inaczej map(uczen ->
													// mapToDto(uczen)) DTO – Data Transfer Object
				.collect(Collectors.toList()); // Zbiera wszystkie przekształcone obiekty do listy. Zwraca listę
												// List<UczenResponse>.
	}

	public Uczen mapTo(Uczen uczen, UczenRequest request) {
		uczen.setImie(request.getImie());
		uczen.setNazwisko(request.getNazwisko());
		return uczen;
	}

	public Uczen mapTo(UczenRequest request) {
		return mapTo(new Uczen(), request);
	}
}
