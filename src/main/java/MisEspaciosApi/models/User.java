package MisEspaciosApi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"users\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_user") // Mantiene el nombre exacto en la BD
    private Long id_user;

    @Column(name = "nickname", nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(name = "email", nullable = false, unique = true, length = 50)
    private String email;

    @Column(name = "passwd", nullable = false, length = 255)
    private String passwd;

    @Column(name = "name_user", nullable = false, length = 50)
    private String nameUser;

    @Column(name = "surname_user", nullable = false, length = 50)
    private String surname_user;

    @Column(name = "private", nullable = false)
    private boolean isPrivate;

    @Column(name = "profile_img", nullable = true, length = 255)
    private String profile_img;

    public User() {}

    // Getters y Setters
    public Long getid_user() { return id_user; }
    public void setid_user(Long id_user) { this.id_user = id_user; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPasswd() { return passwd; }
    public void setPasswd(String passwd) { this.passwd = passwd; }

    public String getNameUser() { return nameUser; }
    public void setNameUser(String nameUser) { this.nameUser = nameUser; }

    public String getsurname_user() { return surname_user; }
    public void setsurname_user(String surname_user) { this.surname_user = surname_user; }

    public String getEmail() {return email;}
    public void setEmail(String email) {this.email = email;}

    public boolean isPrivate() {return isPrivate;}
    public void setPrivate(boolean aPrivate) {isPrivate = aPrivate;}

    public String getProfile_img() {
        return profile_img;
    }
    public void setProfile_img(String profile_img) {
        this.profile_img = profile_img;
    }
}
