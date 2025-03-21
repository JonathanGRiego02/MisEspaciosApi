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
}
