/**
 * Created by Enrico on 13/05/2017.
 */
public class Installer extends User {

    //attributes
    private String installerID;

    //methods
    public Installer(String name, String installerID){
        super(name);
        this.installerID = installerID;
    }

    public String getInstallerID() {return installerID;}
}
