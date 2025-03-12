package MisEspaciosApi.models;

import jakarta.persistence.*;
import java.util.Date;

@Entity
@Table(name = "\"comments\"")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_comment")
    private Long idComment;

    @Column(name = "comment", length = 250)
    private String comment;

    @Temporal(TemporalType.DATE)
    @Column(name = "commnet_date")
    private Date comment_date;

    @ManyToOne
    @JoinColumn(name = "id_user", referencedColumnName = "id_user", nullable = true)
    private User user;

    @ManyToOne
    @JoinColumn(name = "id_place", referencedColumnName = "id_place", nullable = true)
    private Place place;

    public Comment() {}

    // Getters y Setters

    public Long getIdComment() {
        return idComment;
    }

    public void setIdComment(Long idComment) {
        this.idComment = idComment;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public Date getComment_date() {
        return comment_date;
    }

    public void setComment_date(Date comment_date) {
        this.comment_date = comment_date;
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
