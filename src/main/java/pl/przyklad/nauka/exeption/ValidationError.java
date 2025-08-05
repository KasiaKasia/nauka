package pl.przyklad.nauka.exeption;

import lombok.Data;

@Data
public class ValidationError {
	private final String pole;
	private final String informacjaOBledzie;
}
