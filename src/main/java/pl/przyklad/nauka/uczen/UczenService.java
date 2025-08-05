package pl.przyklad.nauka.uczen;

import jakarta.persistence.EntityNotFoundException;
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

	private Uczen findByIdOrThrowException(Integer id) {
		return repository.findById(id).orElseThrow(
				() -> new EntityNotFoundException(String.format("Student with given id '%d' does not exist", id)));
	}

	public UczenResponse save(UczenRequest request) {
		Uczen customer = mapper.mapTo(request);
		customer = repository.save(customer);
		return mapper.mapToDto(customer);
	}

	public UczenResponse update(Integer id, UczenRequest request) {
		Uczen customer = findByIdOrThrowException(id);
		customer = mapper.mapTo(customer, request);
		customer = repository.save(customer);
		return mapper.mapToDto(customer);
	}

	public void delete(Integer id) {
		repository.deleteById(id);
	}
}
