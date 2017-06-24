package AIMSLab_server_interaction;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;

/**
 * This class models the Installer user, extending the User class.
 *
 * @author Enrico Ferro
 */

public class Installer extends User {
    /**
     * ID of the installer
     */
    @JsonProperty("id")
    private String installerID;

    /**
     * Constructor
     */
    public Installer(String name, String surname, String address, String installerID, String username, String password){
        super(name, surname, address, username, password);
        this.installerID = installerID;
    }
}
