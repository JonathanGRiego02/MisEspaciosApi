package MisEspaciosApi;

import MisEspaciosApi.dto.PlaceDTO;
import MisEspaciosApi.dto.PlaceTypeDTO;
import MisEspaciosApi.models.Place;
import MisEspaciosApi.models.PlaceType;
import MisEspaciosApi.models.User;
import MisEspaciosApi.models.LoginRequest;
import MisEspaciosApi.repository.PlaceTypeRepository;
import MisEspaciosApi.services.PlaceService;
import MisEspaciosApi.services.PlaceTypeService;
import MisEspaciosApi.services.UserService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.mindrot.jbcrypt.BCrypt;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
@CrossOrigin(origins = "http://localhost:*")
public class ApiController {

    private final UserService userService;
    private final PlaceService placeService;
    private final PlaceTypeService placeTypeService;

    // Inyección de los servicios
    public ApiController(UserService userService, PlaceTypeRepository placeTypeRepository, PlaceService placeService, PlaceTypeService placeTypeService) {
        this.userService = userService;
        this.placeService = placeService;
        this.placeTypeService = placeTypeService;
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

    // All place types by username
    @GetMapping("/placeTypes/{nickname}")
    public ResponseEntity<List<PlaceTypeDTO>> getAllPlaceTypesByUser(@PathVariable String nickname) {
        System.out.println("Getting place types for user: " + nickname);
        List<PlaceTypeDTO> placeTypes = placeTypeService.getPlaceTypesByUserNickname(nickname);

        if (placeTypes.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(placeTypes);
    }

    // Create a new place type for a user
    @PostMapping("/placeTypes")
    public ResponseEntity<String> createPlaceType(@RequestParam String name_type,
                                                  @RequestParam String userNickname) {
        // Find the user by nickname
        Optional<User> userOptional = userService.getUserByNickname(userNickname);

        if (!userOptional.isPresent()) {
            return ResponseEntity.status(400).body("User not found.");
        }

        // Create the new PlaceType
        PlaceType newPlaceType = new PlaceType();
        newPlaceType.setName_type(name_type);
        newPlaceType.setIcon(null); // Icon is not important
        newPlaceType.setUser(userOptional.get());

        // Save it to the database
        placeTypeService.savePlaceType(newPlaceType);

        return ResponseEntity.status(201).body("Place type created successfully.");
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

    // Get the followers count of a user
    @GetMapping("/users/{nickname}/followers")
    public ResponseEntity<Integer> getFollowersCount(@PathVariable String nickname) {
        Optional<User> userOptional = userService.getUserByNickname(nickname);

        if (userOptional.isPresent()) {
            int followersCount = userService.getFollowersCount(userOptional.get().getid_user().intValue());
            return ResponseEntity.ok(followersCount);
        }

        return ResponseEntity.notFound().build();
    }

    // Get public places of a user
    @GetMapping("/places/public/{nickname}")
    public ResponseEntity<List<PlaceDTO>> getPublicPlacesByUser(@PathVariable String nickname) {
        List<PlaceDTO> publicPlaces = placeService.getPublicPlacesByUserNickname(nickname);

        if (publicPlaces.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(publicPlaces);
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

    // Update the user's privacy setting
    @PutMapping("/users/{nickname}/privacy")
    public ResponseEntity<Void> updateUserPrivacy(@PathVariable String nickname, @RequestParam boolean isPrivate) {
        Optional<User> userOptional = userService.getUserByNickname(nickname);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setPrivate(isPrivate); // update the isPrivate field
            userService.saveUser(user); // save the updated user
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.notFound().build();
        }
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
                                              @RequestParam String userNickname,
                                              @RequestParam BigDecimal pos_x,
                                              @RequestParam BigDecimal pos_y) {
        // Check if place type exists
        Optional<PlaceType> placeTypeOptional = placeTypeService.findById(placeTypeId);
        if (!placeTypeOptional.isPresent()) {
            return ResponseEntity.status(400).body("Place type not found.");
        }

        // Check if user exists
        Optional<User> userOptional = userService.getUserByNickname(userNickname);
        if (!userOptional.isPresent()) {
            return ResponseEntity.status(400).body("User not found.");
        }

        // Create new place
        Place newPlace = new Place();
        newPlace.setNamePlace(namePlace);
        newPlace.setDescPlace(descPlace);
        newPlace.setPlaceType(placeTypeOptional.get());
        newPlace.setUser(userOptional.get());
        newPlace.setpos_x(pos_x); // Set latitude
        newPlace.setpos_y(pos_y); // Set longitude
        newPlace.setLikes(0);

        // Save in the database
        placeService.savePlace(newPlace);

        return ResponseEntity.status(201).body("Place created successfully.");
    }

    // DELETE
    @DeleteMapping("/places/{id}")
    public ResponseEntity<String> deletePlace(@PathVariable Long id) {
        if (placeService.deletePlace(id)) {
            return ResponseEntity.ok("Place deleted successfully.");
        } else {
            return ResponseEntity.status(404).body("Place not found.");
        }
    }
}
