package MisEspaciosApi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"followers\"")
public class Follower {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_follower")
    private Long idFollower;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = false)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_followed", referencedColumnName = "id_user", nullable = false)
    private User followed;

    public Follower() {}

    // Getters y Setters
    public Long getIdFollower() {
        return idFollower;
    }

    public void setIdFollower(Long idFollower) {
        this.idFollower = idFollower;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getFollowed() {
        return followed;
    }

    public void setFollowed(User followed) {
        this.followed = followed;
    }
}
