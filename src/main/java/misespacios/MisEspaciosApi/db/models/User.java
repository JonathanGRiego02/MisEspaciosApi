package misespacios.MisEspaciosApi.db.models;

public class User {
    private int idUser;
    private String email;
    private String user;
    private String passwd;
    private String nameUser;
    private String lastNameuser;

    public User() {}

    public User(int idUser, String email, String user, String passwd, String nameUser, String lastNameuser) {
        this.idUser = idUser;
        this.email = email;
        this.user = user;
        this.passwd = passwd;
        this.nameUser = nameUser;
        this.lastNameuser = lastNameuser;
    }

    public int getIdUser() {
        return idUser;
    }

    public void setIdUser(int idUser) {
        this.idUser = idUser;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd;
    }

    public String getNameUser() {
        return nameUser;
    }

    public void setNameUser(String nameUser) {
        this.nameUser = nameUser;
    }

    public String getLastNameuser() {
        return lastNameuser;
    }

    public void setLastNameuser(String lastNameuser) {
        this.lastNameuser = lastNameuser;
    }
}
