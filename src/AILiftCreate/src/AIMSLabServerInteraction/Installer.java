package AIMSLabServerInteraction;

import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
public class Installer extends User {
    //attributes
    private String installerID;
    private ArrayList<DrawingProject> activeDrawingProjects;

    //methods
    public Installer(String name, String installerID){
        super(name);
        this.installerID = installerID;
    }

}
