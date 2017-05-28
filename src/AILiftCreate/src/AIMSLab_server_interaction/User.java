package AIMSLab_server_interaction;

import com.fasterxml.jackson.annotation.*;

/**
 * Created by Enrico on 13/05/2017.
 */
//abstract class, that model the generic user and makes no distinction between Installer and Validator,
//two of the main user type of the complete system
public abstract class User {
    //attributes
    @JsonProperty("name")
    private String name;
    @JsonProperty("lastname")
    private String surname;
    @JsonProperty("address")
    private String address;
    @JsonProperty("username")
    private String username;
    @JsonProperty("password")
    private String password;
    //methods
    public User(String name, String surname,String address, String username, String password){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.username = username;
        this.password = password;
    }
}
