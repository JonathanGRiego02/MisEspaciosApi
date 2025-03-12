package MisEspaciosApi.models;

import jakarta.persistence.*;

@Entity
@Table (name = "\"likes\"")
public class Like {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_like")
    private Long idLike;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_place", referencedColumnName = "id_place", nullable = true)
    private Place place;

    public Like() {}

    // Getters y Setters


    public Long getIdLike() {
        return idLike;
    }

    public void setIdLike(Long idLike) {
        this.idLike = idLike;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Place getPlace() {
        return place;
    }

    public void setPlace(Place place) {
        this.place = place;
    }
}
