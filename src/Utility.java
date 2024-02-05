import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;

public class Utility {
    Scanner s = new Scanner(System.in);
    private File file;
    private BufferedWriter writer;

    public void open(String fileName) throws IOException {
        file = new File(fileName);

        // Open file
        try {
            // Check if file already exists
            if(!file.exists()) {
                System.out.println("File doesn't exist. Creating a new file.");
                file.createNewFile();
            }

            // Read file contents
            try(BufferedReader bReader = new BufferedReader(new FileReader(file))) {
                String line;
                while ((line = bReader.readLine()) != null) {
                    System.out.println(line);
                }
            }

            writeInFile();

        } catch (IOException e) {
            System.out.println("An error occured while creating or writing to the file.");
            e.printStackTrace();
        }
    
    }

    public void writeInFile() throws IOException {
        System.out.println("Do you want to make an input? (yes/no): ");
            String inputChoice = s.nextLine().toLowerCase();

            if (inputChoice.equals("yes") || inputChoice.equals("y")) {
                writer = new BufferedWriter(new FileWriter(file, true));
                System.out.println("Enter your input: ");
                String userInput = s.nextLine();
                writer.write(userInput + "\n");
            } else {
                close();
            }
    }

    public void close() throws IOException {
        if(writer!= null) {
            writer.close();
        }
    }

    public void help() throws IOException {
        System.out.println("Available commands: ");
        System.out.println("open -> opens a file and gives an option to write in it. ");
        System.out.println("close -> closes the currently opened file.");
        System.out.println("save -> saves the changes into currently opened file.");
        System.out.println("help -> lists all instructions");
    }

    public void save() throws IOException {
        if(writer != null) {
            writer.flush();
            writer.close();
            writer = null;
            System.out.println("File save successfully!");
        }
    }

    public void exit() {
        System.exit(0);
    }

    public void login() throws IOException {
        System.out.println("Enter username: ");
        String usernameInput = s.nextLine();

        System.out.println("Enter password: ");
        String passwordInput = s.nextLine();

        try(BufferedReader reader = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] credentials = line.split(",");
                if(credentials[0].equals(usernameInput) && credentials[1].equals(passwordInput)) {
                    System.out.println("Welcome, " + usernameInput + "!");
                    return;
                }
            }
        }
        System.out.println("Wrong username or password.");
        exit();
    }
}
