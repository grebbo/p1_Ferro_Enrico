package AIMSLab_server_interaction;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

/**
 * This is a stub class, that imitates the output of the AIMSLabServer and substitutes its role for testing aims.
 * Implemented as a singleton.
 *
 * @author Enrico Ferro
 */
public class AIMSLabServer {
    /**
     * @attribute instance -> for the singleton construct.
     */
    private static AIMSLabServer instance;

    private static String projectJsonPath;
    private static String projectFolderPath = "./project_jsons/";
    private Map<Integer, File> projects;

    /**
     * Singleton class constructor.
     */
    private AIMSLabServer(){
        projects = new HashMap<>();
        projectJsonPath = projectFolderPath + "symm_1200_1200";
    }

    /**
     * Method used to retrieve the only instance of the class.
     * @return Returns the only instance of the singleton class
     */
    public static AIMSLabServer getInstance(){
        if (instance == null) {
            instance = new AIMSLabServer();
        }
        return instance;
    }

    /**
     * This method substitutes the json creation process. A local file is used instead and returned.
     * In the real version of the method, it is intended to generate a json representing the project
     * given the measures in input.
     *
     * @param measures the measures list input for the project
     * @return json file to be analyzed
     */
    public File createProjectFromMeasures(ArrayList<String> measures){
        return new File(projectJsonPath);
    }

    /**
     * This method substitutes the searching project process. It requires an id and returns the
     * corresponding project json if found, throws an error exception otherwise.
     *
     * @param id id of the project requested
     * @return The method returns the project json if found
     * @throws Exception generic exception notifying an error during searching
     */
    public File retrieveProjectByID(int id) throws Exception{
        if (searchProject(id)){
            return projects.get(id);
        }
        else
            throw new Exception("Error: project not found.");
    }

    /**
     * Simple method to search for a project by id. Returns true if there is a project with the
     * specified id, false otherwise.
     *
     * @param id project id requested
     * @return true if project found, false otherwise
     */
    public boolean searchProject(int id) {
           if (projects.containsKey(id)) {
               return true;
           }
           return false;
    }

    /**
     * This method substites the saving process. First it verifies if the project is in the server.
     * Depending on this updates or adds the project file in the map.
     *
     * @param project file of the project we want to add
     * @param id project id
     */
    public void saveProject(File project, int id) {
        if (searchProject(id)){
            System.out.println("... project updated.");
        }
        else {
            System.out.println("...project added.");
        }
        projects.put(id, project);
    }

    /**
     * Debugging method. It lets to change the standard rendered json project.
     * @param symm is symmetric project ?
     * @param cen is a project with cen-type doors ?
     * @param shutters n° of shutters
     */
    public void setStandardProject(boolean symm, boolean cen, int shutters) {
        String filePath;
        if ( symm ) {
            filePath = "symm_1200_1200";
        }
        if (cen) {
            switch (shutters) {
                case 2:
                    filePath = "door_CEN2";
                    break;
                case 4:
                    filePath = "door_CEN4";
                    break;
                case 6:
                    filePath = "door_CEN6";
                    break;
                default:
                    filePath = "asymm_1000_1400";
                    break;
            }
        } else {
            switch (shutters) {
                case 1:
                    filePath = "door_TEL1";
                    break;
                case 2:
                    filePath = "door_TEL2";
                    break;
                case 3:
                    filePath = "door_TEL3";
                    break;
                default:
                    filePath = "asymm_1000_1400";
                    break;
            }
        }
        projectJsonPath = projectFolderPath + filePath;
    }
}
