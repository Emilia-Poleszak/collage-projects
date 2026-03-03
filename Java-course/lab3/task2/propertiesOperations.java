import java.io.*;
import java.util.Properties;
import java.util.Scanner;

/**
 * @author Emilia Poleszak
 * @author Anna Czarkowska
 */

public class propertiesOperations {
    public static void addNewObjectsWithScanner(){
        Scanner s = new Scanner(System.in);
        Properties p = new Properties();
        int number, type;
        boolean again = true;
        String name, value;
        while(again){
            System.out.print("Adding new object.\nName: ");
            name = s.nextLine();
            System.out.print("""
            Chose form options:
            1 - String
            2 - int
            3 - double
            4 - boolean
            Type:\s""");
            type = s.nextInt();
            s.nextLine();
            System.out.print("Value: ");
            value = s.nextLine();

            switch (type) {
                case 1: {
                    p.setProperty(name, value);
                    break;
                }
                case 2: {
                    try {
                        int i = Integer.parseInt(value);
                        p.setProperty(name, String.valueOf(i));
                    } catch (NumberFormatException e) {
                        System.out.println("Given value does not match given type.");
                    }
                    break;
                }
                case 3:{
                    try {
                        double d = Double.parseDouble(value);
                        p.setProperty(name, String.valueOf(d));
                    } catch (NumberFormatException e) {
                        System.out.println("Given value does not match given type.");
                    }
                    break;
                }
                case 4: {
                    try {
                        boolean b = Boolean.parseBoolean(value);
                        p.setProperty(name, String.valueOf(b));
                    } catch (NumberFormatException e) {
                        System.out.println("Given value does not match given type.");
                    }
                    break;
                }
                default: {
                    System.out.println("Choose from given options.");
                    break;
                }
            }

            while(true){
                System.out.println("""
                Do you want to add next object?
                1 - yes
                2 - no""");
                try{
                    number = s.nextInt();
                    s.nextLine();
                    if(number == 1) break;
                    else if(number == 2) {
                        again = false;
                        break;
                    }
                    else System.out.println("Choose form given options. Try again.");
                } catch (NumberFormatException e){
                    System.out.println("Try again.");
                }
            }
        }

        try{
            FileWriter file = new FileWriter("data.txt", true);
            p.store(file, "Saved data");
        } catch(FileNotFoundException e) {
            System.out.println("File not found.");
        } catch (IOException e){
            System.out.println("Error.");
        }
    }

    public static void addNewObjectsWithConsole(){
        Console c = System.console();
        if(c == null){
            System.out.println("No console available.");
            return;
        }
        boolean again = true;
        Properties p = new Properties();
        while(again){
            System.out.println("Adding new object.");
            String name = c.readLine("Name: ");
            String type = c.readLine("""
            Chose form options:
            1 - String
            2 - int
            3 - double
            4 - boolean
            Type:\s""");
            String value = c.readLine("Value: ");

            switch (Integer.parseInt(type)) {
                case 1: {
                    p.setProperty(name, value);
                    break;
                }
                case 2: {
                    try {
                        int i = Integer.parseInt(value);
                        p.setProperty(name, String.valueOf(i));
                    } catch (NumberFormatException e) {
                        System.out.println("Given value does not match given type.");
                    }
                    break;
                }
                case 3: {
                    try {
                        double d = Double.parseDouble(value);
                        p.setProperty(name, String.valueOf(d));
                    } catch (NumberFormatException e) {
                        System.out.println("Given value does not match given type.");
                    }
                    break;
                }
                case 4: {
                    try {
                        boolean b = Boolean.parseBoolean(value);
                        p.setProperty(name, String.valueOf(b));
                    } catch (NumberFormatException e) {
                        System.out.println("Given value does not match given type.");
                    }
                    break;
                }
                default: {
                    System.out.println("Choose from given options.");
                    break;
                }
            }

            while(true){
                try{
                    String choice = c.readLine("""
                            Do you want to add next object?\s
                            1 - yes\s
                            2 - no
                            """);
                    if(Integer.parseInt(choice) == 1) break;
                    else if(Integer.parseInt(choice) == 2) {
                        again = false;
                        break;
                    }
                    else System.out.println("Choose form given options. Try again.");
                } catch (NumberFormatException e){
                    System.out.println("Choose again.");
                }
            }
        }

        try{
            FileWriter file = new FileWriter("data.txt", true);
            p.store(file, "Saved data");
        } catch (FileNotFoundException e){
            System.out.println("File not found.");
        } catch (IOException e){
            System.out.println("Error.");
        }
    }

    public static void read(){
        try {
            Scanner file = new Scanner(new File("data.txt"));
            System.out.println("File content:\n");
            while(file.hasNext()){
                System.out.println(file.nextLine());
            }
        } catch(FileNotFoundException e){
            System.out.println("File not found.");
        }
    }

    public static void main(String[] args){
        Scanner s = new Scanner(System.in);
        String choice;
        int number;
        boolean run = true;
        while(run) {
            while(true){
                System.out.println("""
                        Choose one option.
                        1. Add new object using Scanner.
                        2. Add new object using Console.
                        3. Read data from file.
                        4. Exit.""");
                try{
                    choice = s.nextLine();
                    number = Integer.parseInt(choice);
                    if(0 < number && number < 5) break;
                    else System.out.println("Choose form given options.");
                } catch (NumberFormatException e){
                    System.out.println("Error.");
                }
            }

            switch(number){
                case 1: {
                    propertiesOperations.addNewObjectsWithScanner();
                    break;
                }
                case 2: {
                    propertiesOperations.addNewObjectsWithConsole();
                    break;
                }
                case 3: {
                    propertiesOperations.read();
                    break;
                }
                default: run = false;
            }
        }
    }
}
