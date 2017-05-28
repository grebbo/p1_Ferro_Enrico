package AIMSLab_server_interaction;

import com.fasterxml.jackson.annotation.*;
import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
//extends the user class, giving more details of the user
public class Installer extends User {
    //attributes
    @JsonProperty("id")
    private String installerID;
    @JsonIgnore
    private ArrayList<DrawingProject> activeDrawingProjects;

    //methods
    public Installer(String name, String surname, String address, String installerID, String username, String password){
        super(name, surname, address, username, password);
        this.installerID = installerID;
    }

    //adds a new project to the ones already active in the installer's profile
    public void addActiveProject(DrawingProject project) {
        activeDrawingProjects.add(project);
    }

}
