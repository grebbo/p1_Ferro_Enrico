import java.util.ArrayList;

/**
 * Created by Enrico on 13/05/2017.
 */
public abstract class User {

    //attributes
    private String name;
    private ArrayList<Project> activeProjects;

    //methods
    public User(String name){
        this.name = name;
    }

    public String getName() {return name;}
    public ArrayList<Project> getActiveProjects() {return activeProjects;}

    public void addProject(Project newProject){
        activeProjects.add(newProject);
    }


}
