package MisEspaciosApi;

import MisEspaciosApi.models.Place;
import MisEspaciosApi.models.PlaceTypes;
import MisEspaciosApi.models.User;
import MisEspaciosApi.repository.PlaceRepository;
import MisEspaciosApi.repository.PlaceTypeRepository;
import MisEspaciosApi.repository.UserRepository;
import MisEspaciosApi.models.LoginRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;


import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class ApiController {

    private final UserRepository userRepository;
    private final PlaceTypeRepository placeTypeRepository;
    private final PlaceRepository placeRepository;

    public ApiController(UserRepository userRepository, PlaceTypeRepository placeTypeRepository, PlaceRepository placeRepository) {
        this.userRepository = userRepository;
        this.placeTypeRepository = placeTypeRepository;
        this.placeRepository = placeRepository;
    }

    // GET: Obtener todos los usuarios
    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("Getting users");
        return userRepository.findAll();
    }

    // GET: Obtener todos los tipos de lugares
    @GetMapping("/placeTypes")
    public List<PlaceTypes> getAllPlaceTypes() {
        System.out.println("Getting place types");
        return placeTypeRepository.findAll();
    }

    // GET: Obtener todos los lugares
    @GetMapping("/places")
    public List<Place> getAllPlaces() {
        System.out.println("Getting places");
        return placeRepository.findAll();
    }

    // POST: Validar login
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userRepository.findByNickname(loginRequest.getNickname());
        System.out.println("Solicitud recibida: " + loginRequest.getNickname());

        if (userOptional.isPresent()) {
            User user = userOptional.get();

            // Verificar contraseña hasheada
            if (BCrypt.checkpw(loginRequest.getPasswd(), user.getPasswd())) {
                Map<String, String> response = new HashMap<>();
                response.put("message", "Login exitoso");
                return ResponseEntity.ok(response); // 👈 Retorna JSON
            }
        }

            return ResponseEntity.status(401).body(Map.of("error", "Credenciales incorrectas"));
    }
}
