package AIMSLab_server_interaction;

import com.fasterxml.jackson.annotation.*;

/**
 * This class models the generic user and makes no distinction between Installer and Validator.
 *
 * @author Enrico Ferro
 */

public abstract class User {
    /**
     * @attributes
     * general infos about the user
     */
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

    /**
     * @methods
     * constructor
     */
    public User(String name, String surname,String address, String username, String password){
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.username = username;
        this.password = password;
    }
}
