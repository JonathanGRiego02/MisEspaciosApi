package MisEspaciosApi.models;

import jakarta.persistence.*;

@Entity
@Table(name = "\"users\"")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idUser") // Mantiene el nombre exacto en la BD
    private Long idUser;

    @Column(name = "nickname", nullable = false, unique = true, length = 50)
    private String nickname;

    @Column(name = "passwd", nullable = false, length = 255)
    private String passwd;

    @Column(name = "nameUser", nullable = false, length = 50)
    private String nameUser;

    @Column(name = "surnameUser", nullable = false, length = 50)
    private String surnameUser;

    public User() {}

    // Getters y Setters
    public Long getIdUser() { return idUser; }
    public void setIdUser(Long idUser) { this.idUser = idUser; }

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPasswd() { return passwd; }
    public void setPasswd(String passwd) { this.passwd = passwd; }

    public String getNameUser() { return nameUser; }
    public void setNameUser(String nameUser) { this.nameUser = nameUser; }

    public String getSurnameUser() { return surnameUser; }
    public void setSurnameUser(String surnameUser) { this.surnameUser = surnameUser; }
}
