package pl.przyklad.nauka.uczen;
 
import lombok.Data;

@Data
public class UczenResponse {
	private Integer id;
	private String imie;
	private String nazwisko;
	private String klasa;
}
//public record UczenResponse( Integer id,  String imie,  String nazwisko ) {}
