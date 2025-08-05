package pl.przyklad.nauka.exeption;
import lombok.Builder;
import lombok.Data;
import java.time.ZonedDateTime;
import java.util.List;
 
import com.fasterxml.jackson.annotation.JsonInclude;

@Builder
@Data
/*
 * @JsonInclude(JsonInclude.Include.NON_NULL) - adnotacja wskazuje , że podczas serlializacji  właściwości obiektu, które mają wartość null powinny zostać pominiete.
 * Czyli, jedynie te właściwości , które mają wartość nienulową powinny być uwzględniane w  wynikowym JSON
 * 
 * */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class ErrorResponse {
    private String wiadomosc;
    private ZonedDateTime dataCzas;
    private List<ValidationError> walidacjaBledu;
    private String sciezkaBledu;
}
