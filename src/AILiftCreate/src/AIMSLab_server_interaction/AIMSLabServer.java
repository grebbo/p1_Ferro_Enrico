package AIMSLab_server_interaction;

import java.io.File;

/**
 * This is a stub class, that imitates the output of the AIMSLabServer and substitutes its role for testing aims.
 * Implemented as a singleton.
 *
 * @author Enrico Ferro
 */
public class AIMSLabServer {
    private static AIMSLabServer instance;
    private static String projectJsonPath = "1400_1700_CFSX";

    private AIMSLabServer(){    }

    public static AIMSLabServer getInstance(){
        if (instance == null) {
            instance = new AIMSLabServer();
        }
        return instance;
    }

    /**
     * This method substitutes the json creation process. A local file is used instead and returned.
     *
     * @return json file to be analyzed
     */
    public File getJsonFromServer(){
        return new File(projectJsonPath);
    }
}
