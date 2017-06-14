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
    private static AIMSLabServer instance;
    private static String projectJsonPath = "1400_1700_CFSX";
    private Map<Integer, File> projects;

    private AIMSLabServer(){
        projects = new HashMap<>();
    }

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
}
