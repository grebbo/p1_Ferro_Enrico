import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;

/**
 * Created by Enrico on 14/05/2017.
 */

//gives all the methods of the Jackson json parser library to the Project class
public class Parser {
    //attributes
    private ObjectMapper objectMapper;

    //methods
    public void Parser(){objectMapper = new ObjectMapper();}

    //from json file to Project class structure
    public Project parseProject2Components(String projectJson) {
        Project project = null;
        try {
            project = objectMapper.readValue(projectJson, Project.class);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return project;
    }

    //from given Project to json, in order to be exported
    public String parseComponents2Project(Project project){
        String projectJson = "";

        return projectJson;
    }
}
