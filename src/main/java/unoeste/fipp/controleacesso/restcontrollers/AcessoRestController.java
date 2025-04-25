package unoeste.fipp.controleacesso.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import unoeste.fipp.controleacesso.services.AcessoService;

@CrossOrigin
@RestController
@RequestMapping("autenticacao")
public class AcessoRestController {
    @Autowired
    AcessoService acessoService;

    @GetMapping("/{nome}/{senha}")
    ResponseEntity<Object> autenticar (@PathVariable String nome, @PathVariable String senha) {
        String token = acessoService.autenticar(nome, senha);
        if(token != null)
            return ResponseEntity.ok(token);
        return ResponseEntity.badRequest().body("Acesso negado!");
    }
}
