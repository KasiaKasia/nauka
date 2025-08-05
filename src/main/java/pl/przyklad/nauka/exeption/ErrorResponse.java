package pl.przyklad.nauka.exeption;
import lombok.Builder;
import lombok.Data;
import java.time.ZonedDateTime;
 

@Builder
@Data
public class ErrorResponse {
    private String message;
    private ZonedDateTime timestamp;
}
