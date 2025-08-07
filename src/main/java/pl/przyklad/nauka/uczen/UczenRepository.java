package pl.przyklad.nauka.uczen;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

public interface UczenRepository extends JpaRepository<Uczen,Integer>{
    List<Uczen> findByKlasaContaining(String klasa);
}
