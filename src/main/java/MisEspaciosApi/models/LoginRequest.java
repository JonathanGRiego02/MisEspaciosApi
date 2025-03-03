package MisEspaciosApi.models;

public class LoginRequest {
    private String nickname;
    private String passwd;

    public String getNickname() { return nickname; }
    public void setNickname(String nickname) { this.nickname = nickname; }

    public String getPasswd() { return passwd; }
    public void setPasswd(String passwd) { this.passwd = passwd; }
}

