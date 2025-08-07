package pl.przyklad.nauka.uczen;

import jakarta.persistence.EntityNotFoundException;
import org.springframework.cache.annotation.Cacheable;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class UczenService {
	private final UczenRepository repository;

	private final UczenMapper mapper;

	public List<UczenResponse> findAll() {
		return mapper.mapToDto(repository.findAll());
	}

	public UczenResponse findById(Integer id) {
		Uczen uczen = findByIdOrThrowException(id);
		return mapper.mapToDto(uczen);
	}
	@Cacheable("UczenByKlasa")
	public List<UczenResponse> findByKlasa(String klasa) {
		 var uczniowie = repository.findByKlasaContaining(klasa);
	        return mapper.mapToDto(uczniowie);
	}
	private Uczen findByIdOrThrowException(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Student with given id '%d' does not exist", id)));
	}

	public UczenResponse save(UczenRequest request) {
		Uczen uczen = mapper.mapTo(request);
		uczen = repository.save(uczen);
		return mapper.mapToDto(uczen);
	}

	public UczenResponse update(Integer id, UczenRequest request) {
		Uczen uczen = findByIdOrThrowException(id);
		uczen = mapper.mapTo(uczen, request);
		uczen = repository.save(uczen);
		return mapper.mapToDto(uczen);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
