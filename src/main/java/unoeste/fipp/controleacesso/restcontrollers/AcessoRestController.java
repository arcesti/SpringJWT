package unoeste.fipp.controleacesso.restcontrollers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
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

    @PostMapping("/valida")
    public ResponseEntity<Object> validar(@RequestHeader("Authorization") String token) {
        if (token == null || !token.startsWith("Bearer ")) {
            return ResponseEntity.badRequest().body("Token não fornecido ou inválido.");
        }

        String tokenValue = token.replace("Bearer ", "");

        if (acessoService.validarToken(tokenValue)) {
            return ResponseEntity.ok("Token válido!");
        } else {
            return ResponseEntity.badRequest().body("Token inválido ou expirado.");
        }
    }
}
