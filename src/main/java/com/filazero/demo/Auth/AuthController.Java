import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("${api-endpoint}/auth")
public class AuthController {

    @GetMapping("/login")
    public String login(Authentication authentication) {
        if (authentication == null || !authentication.isAuthenticated()) {
            return "No se detectaron credenciales de autenticación válidas. Por favor, use Basic Auth.";
        }
        
        return "Usuario autenticado exitosamente: " + authentication.getName() 
                + " con roles: " + authentication.getAuthorities();
    }
}