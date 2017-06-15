package AIMSLab_server_interaction;

import Stefano_package.cad_project.AiLiftProject2D;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.Scanner;

/**
 * testing class. in the main mathod are grouped all the main scenarios of the software module
 * Created by enric on 28/05/2017.
 */
public class Core {
    //for the first version of the software the project is locally stored and isn't retrieved by the server
    private static DrawingProject drawingProject;

    public static void onNewProjectSelection(){
        //init project
        drawingProject = new DrawingProject();
        //fill the project structure according to the json
        drawingProject = drawingProject.parser.parseJson2DrawingProject(AIMSLabServer.getInstance().createProjectFromMeasures(null));
        //render project (creates jscad file used to render)
        drawingProject.render(new AiLiftProject2D());
    }

    public static String onSaveProjectSelection() {
        String output = drawingProject.parser.parseDrawingProject2Json(drawingProject);
        return output;
    }


    public static void main(String [] args){

        System.out.println("New Project Scenario\nExecuting -onNewProjectSelection- method... ");
        onNewProjectSelection();

        System.out.println("Would you like to see the output ? [y/n]");
        Scanner reader = new Scanner(System.in);
        String choice = reader.next();
        if(choice.equalsIgnoreCase("y")) {
            BufferedReader br = null;
            try {
                br = new BufferedReader(new FileReader("render_output.jscad"));
                String sCurrentLine;
                while ((sCurrentLine = br.readLine()) != null) {
                    System.out.println(sCurrentLine);
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        System.out.println("\n\nSave Project Scenario\nExecuting -onSaveProjectSelection- method... ");
        String output = onSaveProjectSelection();

        System.out.println("Would you like to see the output to be sent to the Database ? [y/n]");
        reader = new Scanner(System.in);
        choice = reader.next();
        if(choice.equalsIgnoreCase("y")) {
            System.out.println(output);
        }
    }
}
