package pl.przyklad.nauka.check;
/*
 * Klasa ktora będzie symulowała sprawdzanie stanu naszej aplikacji 
 * */

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DummyCheckController {

    @GetMapping("/bezUwierzytelniania")
    public String checkLiveliness(){
        return "działa URL http://localhost:8081/bezUwierzytelniania bez hasla i nazwy uzytkownika";
    }

    @GetMapping("/bezHaslaINazwyUzytkownika")
    public String checkReadiness(){
        return "działa URL http://localhost:8081/bezUwierzytelniania bez hasla i nazwy uzytkownika";
    }
}
