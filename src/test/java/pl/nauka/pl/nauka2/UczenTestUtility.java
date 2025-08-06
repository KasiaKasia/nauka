package pl.nauka.pl.nauka2;

import pl.przyklad.nauka.uczen.Uczen;
import pl.przyklad.nauka.uczen.UczenResponse;

public class UczenTestUtility {
    public static final UczenResponse WLADEK_RESPONSE = utworzUczenResponse(1,"Władek","Szeran");
	public static final UczenResponse TOMASZ_RESPONSE = utworzUczenResponse(2,"Tomasz","Holenderski");
    public static final UczenResponse KATARZYNA_RESPONSE = utworzUczenResponse(3,"Katarzyna","Kowalska");
    
    public static final Uczen WLADEK = Uczen.builder().id(1).imie("Władek").nazwisko("Szeran").build();
    public static final Uczen TOMASZ = Uczen.builder().id(2).imie("Tomasz").nazwisko("Holenderski").build();
    public static final Uczen KATARZYNA = Uczen.builder().id(3).imie("Katarzyna").nazwisko("Kowalska").build();

    public UczenTestUtility() {}
	
	public static UczenResponse utworzUczenResponse(int id, String imie, String nazwisko) {
		var uczenResponse = new UczenResponse();
		uczenResponse.setId(id);
		uczenResponse.setImie(imie);
		uczenResponse.setNazwisko(nazwisko);
		return uczenResponse;
		
	}
}
