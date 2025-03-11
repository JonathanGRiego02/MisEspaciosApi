package MisEspaciosApi.models;

import jakarta.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "\"places\"")
public class Place {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_place") // Asegura que se use el nombre exacto de la tabla SQL
    private Long idPlace;

    @Column(name = "name_place", length = 50)
    private String namePlace;

    @Column(name = "desc_place", length = 250)
    private String descPlace;

    @Column(name = "pos_x", precision = 9, scale = 6)
    private BigDecimal pos_x;

    @Column(name = "pos_y", precision = 9, scale = 6)
    private BigDecimal pos_y;

    @Column(name = "likes")
    private Integer likes;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = true) // Asegura la FK
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_type", referencedColumnName = "id_type", nullable = true) // Asegura la FK
    private PlaceTypes placeTypes;

    public Place() {}

    // Getters y Setters
    public Long getIdPlace() { return idPlace; }
    public void setIdPlace(Long idPlace) { this.idPlace = idPlace; }

    public String getNamePlace() { return namePlace; }
    public void setNamePlace(String namePlace) { this.namePlace = namePlace; }

    public String getDescPlace() { return descPlace; }
    public void setDescPlace(String descPlace) { this.descPlace = descPlace; }

    public BigDecimal getpos_x() { return pos_x; }
    public void setpos_x(BigDecimal pos_x) { this.pos_x = pos_x; }

    public BigDecimal getpos_y() { return pos_y; }
    public void setpos_y(BigDecimal pos_y) { this.pos_y = pos_y; }

    public Integer getLikes() { return likes; }
    public void setLikes(Integer likes) { this.likes = likes; }

    public User getUser() { return user; }
    public void setUser(User user) { this.user = user; }

    public PlaceTypes getPlaceType() { return placeTypes; }
    public void setPlaceType(PlaceTypes placeTypes) { this.placeTypes = placeTypes; }
}
