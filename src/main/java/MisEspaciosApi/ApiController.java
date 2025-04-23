package MisEspaciosApi;

import MisEspaciosApi.dto.PlaceDTO;
import MisEspaciosApi.models.Place;
import MisEspaciosApi.models.PlaceType;
import MisEspaciosApi.models.User;
import MisEspaciosApi.models.LoginRequest;
import MisEspaciosApi.repository.PlaceTypeRepository;
import MisEspaciosApi.services.PlaceService;
import MisEspaciosApi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:*")
public class ApiController {

    private final UserService userService;
    private final PlaceTypeRepository placeTypeRepository;
    private final PlaceService placeService;

    // Inyección de los servicios
    public ApiController(UserService userService, PlaceTypeRepository placeTypeRepository, PlaceService placeService) {
        this.userService = userService;
        this.placeTypeRepository = placeTypeRepository;
        this.placeService = placeService;
    }

    // GET

    // All users
    @GetMapping("/users")
    public List<User> getAllUsers() {
        System.out.println("Getting users");
        return userService.getAllUsers();
    }

    // User by nickname
    @GetMapping("/users/{nickname}")
    public ResponseEntity<User> getUserByNickname(@PathVariable String nickname) {
        Optional<User> userOptional = userService.getUserByNickname(nickname);
        if (userOptional.isPresent()) {
            return ResponseEntity.ok(userOptional.get());
        }
        return ResponseEntity.notFound().build();
    }

    // All places
    @GetMapping("/placeTypes")
    public List<PlaceType> getAllPlaceTypes() {
        System.out.println("Getting place types");
        return placeTypeRepository.findAll();
    }

    // Places within the bounds dynamically as the user moves the map
    @GetMapping("/places")
    public ResponseEntity<List<PlaceDTO>> getPlacesInBounds(@RequestParam double ne_lat,
                                                            @RequestParam double ne_lng,
                                                            @RequestParam double sw_lat,
                                                            @RequestParam double sw_lng,
                                                            @RequestParam String username) {
        System.out.println("Getting places within bounds for user: " + username);

        List<PlaceDTO> places = placeService.getPlacesWithinBounds(ne_lat, ne_lng, sw_lat, sw_lng, username);

        if (places.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(places);
    }


    // Places by username
    @GetMapping("/places/username/{username}")
    public ResponseEntity<List<PlaceDTO>> getPlacesByUsername(@PathVariable String username) {
        List<PlaceDTO> places = placeService.GetPlacesByUsername(username);

        if (places.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(places);
    }

    // POST

    // Validate login
    @PostMapping("/login")
    public ResponseEntity<Map<String, Object>> login(@RequestBody LoginRequest loginRequest) {
        Optional<User> userOptional = userService.getUserByNickname(loginRequest.getNickname());
        System.out.println("Solicitud recibida: " + loginRequest.getNickname());

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            if (BCrypt.checkpw(loginRequest.getPasswd(), user.getPasswd())) {
                System.out.println("Login exitoso");

                Map<String, Object> response = new HashMap<>();
                response.put("id_user", user.getid_user());
                response.put("nickname", user.getNickname());

                return ResponseEntity.ok(response);
            }
        }

        return ResponseEntity.status(401).build();
    }

    // Register a new user
    @PostMapping("/users")
    public ResponseEntity<Map<String, Object>> registerUser(@RequestBody User newUser) {
        if (userService.getUserByNickname(newUser.getNickname()).isPresent()) {
            return ResponseEntity.status(400).body(Map.of("error", "El nombre de usuario ya está en uso."));
        }

        if (userService.getUserByEmail(newUser.getEmail()).isPresent()) {
            return ResponseEntity.status(400).body(Map.of("error", "El correo electrónico ya está en uso."));
        }

        if (newUser.getNickname().isEmpty() || newUser.getEmail().isEmpty() || newUser.getPasswd().isEmpty() ||
                newUser.getNameUser().isEmpty() || newUser.getsurname_user().isEmpty()) {
            return ResponseEntity.status(400).body(Map.of("error", "Todos los campos son obligatorios."));
        }

        // Hash the password
        String hashedPassword = BCrypt.hashpw(newUser.getPasswd(), BCrypt.gensalt());
        newUser.setPasswd(hashedPassword);

        // Set defaults
        newUser.setPrivate(false);
        newUser.setProfile_img(null);

        userService.saveUser(newUser);

        // Return user ID and nickname
        return ResponseEntity.status(201).body(Map.of(
                "message", "Usuario registrado con éxito.",
                "id_user", newUser.getid_user(),
                "nickname", newUser.getNickname()
        ));
    }

    // New place
    @PostMapping("/places")
    public ResponseEntity<String> createPlace(@RequestParam String namePlace,
                                              @RequestParam String descPlace,
                                              @RequestParam Long placeTypeId,
                                              @RequestParam String userNickname) {
        // place_type exist
        Optional<PlaceType> placeTypeOptional = placeTypeRepository.findById(placeTypeId);
        if (!placeTypeOptional.isPresent()) {
            return ResponseEntity.status(400).body("Tipo de lugar no encontrado.");
        }

        // user exist
        Optional<User> userOptional = userService.getUserByNickname(userNickname);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(400).body("Usuario no encontrado.");
        }

        // Create new place
        Place newPlace = new Place();
        newPlace.setNamePlace(namePlace);
        newPlace.setDescPlace(descPlace);
        newPlace.setPlaceType(placeTypeOptional.get());
        newPlace.setUser(userOptional.get());

        // Save on the db
        placeService.savePlace(newPlace);

        // Return success
        return ResponseEntity.status(201).body("Lugar creado con éxito.");
    }
}
