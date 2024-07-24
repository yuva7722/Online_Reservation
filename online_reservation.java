import java.util.*;
import java.io.*;
public class Online_Train_ReservationSystem{

    static Scanner sc=new Scanner(System.in);
    static Map<String, String> users=new HashMap<>();
    static Map<String, Reservation> reservations=new HashMap<>();
    static int pnrCounter=1000;

    public static void main(String[] args){
        System.out.println("Welcome to the Online Reservation System!");
        while (true){
            System.out.println("\nMain Menu:");
            System.out.println("1.Login");
            System.out.println("2.Register");
            System.out.println("3.Exit");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            sc.nextLine(); 

            switch (choice){
                case 1:
                    if (login()){
                        mainMenu();
                    } else{
                        System.out.println("It's an Invalid login! Please try again.");
                    }
                    break;
                case 2:
                    register();
                    break;
                case 3:
                    System.out.println("Thank you for using Service");
                    return;
                default:
                    System.out.println("Invalid choice! Try again.");
            }
        }
    }

    static void register(){
        System.out.print("Enter new Login ID: ");
        String loginId=sc.nextLine();
        if (users.containsKey(loginId)){
            System.out.println("Login ID already exists.Please choose a different ID.");
            return;
        }
        System.out.print("Enter new Password: ");
        String password=sc.nextLine();

        users.put(loginId, password);
        System.out.println("Registration successful! You can now log in with your new ID and password.");
    }

    static boolean login(){
        System.out.print("Enter Login ID: ");
        String loginId=sc.nextLine();
        System.out.print("Enter Password: ");
        String password=sc.nextLine();

        if (users.containsKey(loginId)&&users.get(loginId).equals(password)){
            System.out.println("Login successful!");
            return true;
        } else{
            return false;
        }
    }

    static void mainMenu(){
        while (true){
            System.out.println("\nUser Menu:");
            System.out.println("1.Make Reservation");
            System.out.println("2.Cancel Reservation");
            System.out.println("3.Logout");
            System.out.print("Enter your choice: ");
            int choice=sc.nextInt();
            sc.nextLine();

            switch (choice){
                case 1:
                    makeReservation();
                    break;
                case 2:
                    cancelReservation();
                    break;
                case 3:
                    System.out.println("Logging out...");
                    return;
                default:
                    System.out.println("Invalid choice.Please try again.");
            }
        }
    }

    static void makeReservation(){
        System.out.print("Enter your name: ");
        String name=sc.nextLine();
        System.out.print("Enter train number: ");
        String trainNumber=sc.nextLine();
        System.out.print("Enter train name: ");
        String trainName=sc.nextLine();
        System.out.print("Enter class type: ");
        String classType=sc.nextLine();
        System.out.print("Enter date of journey (dd-mm-yyyy): ");
        String dateOfJourney=sc.nextLine();
        System.out.print("Enter from place: ");
        String fromPlace=sc.nextLine();
        System.out.print("Enter destination: ");
        String destination=sc.nextLine();

        int pnrNumber=pnrCounter++;
        Reservation reservation=new Reservation(pnrNumber, name, trainNumber, trainName, classType, dateOfJourney, fromPlace, destination);
        reservations.put(String.valueOf(pnrNumber), reservation);

        System.out.println("Reservation successful! Your PNR number is "+pnrNumber);
    }

    static void cancelReservation(){
        System.out.print("Enter your PNR number: ");
        String pnrNumber=sc.nextLine();

        if (reservations.containsKey(pnrNumber)){
            Reservation reservation=reservations.remove(pnrNumber);
            System.out.println("Reservation with PNR number "+pnrNumber+" has been cancelled.");
            System.out.println("Details: "+reservation);
        } else{
            System.out.println("Invalid PNR number!");
        }
    }
}

class Reservation{
    int pnrNumber;
    String name;
    String trainNumber;
    String trainName;
    String classType;
    String dateOfJourney;
    String fromPlace;
    String destination;

    public Reservation(int pnrNumber, String name, String trainNumber, String trainName, String classType, String dateOfJourney, String fromPlace, String destination){
        this.pnrNumber=pnrNumber;
        this.name=name;
        this.trainNumber=trainNumber;
        this.trainName=trainName;
        this.classType=classType;
        this.dateOfJourney=dateOfJourney;
        this.fromPlace=fromPlace;
        this.destination=destination;
    }

    @Override
    public String toString(){
        return "PNR Number: "+pnrNumber+"\n"+
               "Name: "+name+"\n"+
               "Train Number: "+trainNumber+"\n"+
               "Train Name: "+trainName+"\n"+
               "Class Type: "+classType+"\n"+
               "Date of Journey: "+dateOfJourney+"\n"+
               "From: "+fromPlace+"\n"+
               "To: "+destination;
    }
}
