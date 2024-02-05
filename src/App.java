import java.io.IOException;
import java.util.Scanner;
public class App {
    public static void main(String[] args) throws Exception {
        Scanner s  = new Scanner(System.in);
        Utility util = new Utility();

        util.login();
        
        String command;

        boolean continueLoop = true;

        while (continueLoop) {
            System.out.println("Command: ");
            command = s.nextLine();
            switch (command) {
                case "open":
                    System.out.println("Which file do you want to open?: ");
                    String userFile = s.nextLine();
                    util.open(userFile);
                    break;
                case "close":
                    util.close();
                    break;
                case "help":
                    util.help();
                    break;
                case "save":
                    util.save();
                    break;
                case "exit":
                    continueLoop = false;
                    util.exit();
                    break;
                default:
                    break;
            }
        }
    } 
}
