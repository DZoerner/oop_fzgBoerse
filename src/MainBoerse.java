import java.io.*;
import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Random;
import java.util.Scanner;

public class MainBoerse {
    public static final String FILENAME = "src/data.csv";

    private final Scanner getUserInput = new Scanner(System.in);
    ArrayList<Vehicle> vehicleArrayList = new ArrayList<>();
    private int vehicleId = 0;


    public MainBoerse() {
        loadData();
        addTestData();
        hauptMenue();
    }

    public static void main(String[] args) {
        MainBoerse mainBoerse = new MainBoerse();
    }

    public void hauptMenue() {
        System.out.println("-------------------------------------------------------------------------------------" + "\n" +
                " Fahrzeugbörse Hauptmenü von: [Ihr Vor – und Nachname]" + "\n" +
                "-------------------------------------------------------------------------------------");
        System.out.println("1) Fahrzeug anlegen" + "\n" +
                "2) Fahrzeug bearbeiten" + "\n" +
                "3) Fahrzeug suchen" + "\n" +
                "4) Fahrzeug löschen" + "\n" +
                "0) Börse beenden");
        userSelection();
    }

    private void userSelection() {
        // get users choice from main menu
        Scanner getUserMenuInput = new Scanner(System.in);
        try {
            int choice = getUserMenuInput.nextInt();
            switch (choice) {
                case 0:
                    boerseBeenden(); // checked
                    break;
                case 1:
                    fzgAnlegen();
                    break;
                case 2:
                    fzgBearbeiten();
                    break;
                case 3:
                    fzgSuchen();
                    break;
                case 4:
                    fzgLoeschen();
                    break;
            }
            getUserMenuInput.close();
        } catch (InputMismatchException e) {
            System.out.println("Eingabe nicht erkannt");
            userSelection();
        }
    }

    private void fzgAnlegen() {
        System.out.println("Folgende Fahrzeugtypen sind zulässig:" + "\n" + "1) PKW" + "\n" + "2) LKW" + "\n" + "3) Boot" + "\n" + "4) Motorrad");
        System.out.println("Bitte geben Sie nun die geforderten Parameter ein und bestätigen jeweils mit Enter:");

        // ToDo: Input error handling

        // catching users input
        System.out.print("Fahrzeugtyp: ");
        String userInputVehicleType = getUserInput.next();

        System.out.print("Fahrzeugmarke: ");
        String userInputBrand = getUserInput.next();

        System.out.print("Fahrzeugmodell: ");
        String userInputModel = getUserInput.next();

        System.out.print("Fahrzeugfarbe: ");
        String userInputColor = getUserInput.next();

        System.out.print("Fahrzeugbaujahr: ");
        int userInputConstructionYear = getUserInput.nextInt();

        System.out.print("Fahrzeugpreis: ");
        double userInputPrice = getUserInput.nextDouble();

        //creating a new object
        Vehicle vehicle = new Vehicle(vehicleId, userInputVehicleType, userInputBrand, userInputModel, userInputColor,
                userInputConstructionYear, userInputPrice);
        vehicleId++;

        // store created object in arraylist
        vehicleArrayList.add(vehicle);

        // just for testing
        System.out.println(vehicle.getVehicleId() + " " + vehicle.getVehicleType() + " " + vehicle.getBrand() + " " +
                vehicle.getModel() + " " + vehicle.getColor() + " " + vehicle.getConstructionYear() + " " + "kostet " +
                vehicle.getPrice());

        // maybe own function
        System.out.println("Fahrzeug erfolgreich angelegt. Möchten Sie ein weiteres Fahrzeug eingeben?\n(J)a\n(N)ein");
        String choice = getUserInput.next().toLowerCase();
        if (choice.contentEquals("j")) {
            fzgAnlegen();
        } else {
            hauptMenue();
        }
    }

    // ich habe einfach mal ein paar Kommentare in Zeile 113 eingefügt mal sehen wie es nun weitergehen soll
    System.out.println("Das ist toll");

    // ToDo: REMOVE ME!!!!!
    private void addTestData() {
        Random rnd = new Random(123);
        for (int i = 0; i < 10; i++) {
            Vehicle vehicle = new Vehicle(vehicleId, "PKW", "VW", "Golf " + i, "blau", 1970 + rnd.nextInt(51), 2000 + rnd.nextInt(10000));
            vehicleId++;
            vehicleArrayList.add(vehicle);
        }
        System.out.println("Test Data hinzugefügt");
        // hauptMenue();
    }

    private void fzgBearbeiten() {
        for (Vehicle vehicle : vehicleArrayList) {
            System.out.println(vehicle);
        }

        hauptMenue();
        //TODO refactor
        //Was wird hier ausgegeben?
        //!!!
//        vehicleArrayList.forEach((n) -> System.out.println(n.getVehicleId() + n.getBrand() + n.getModel() + n.getColor() +
//                n.getConstructionYear() + n.getPrice()));
//        System.out.println("Geben Sie die ID des zu bearbeitenden Fahrzeugs ein: ");
//        int userVehicleChoice = getUserInput.nextInt();
//        System.out.println(vehicleArrayList.get(userVehicleChoice));
//        System.out.println("Welche Angabe wollen Sie bearbeiten?\n1)Fahrzeugtyp\n2)Fahrzeugmarke\n3)Fahrzeugmodell\n4)Fahrzeugfarbe\n5)Baujahr\nPreis");
//        try {
//            switch (getUserInput.nextInt()) {
//                case 1:
//                    System.out.println("Geben Sie nun den neuen Wert ein: ");
//                    // vehicleArrayList(userVehicleChoice).setVehicleType(getUserInput.next());
//
//            }
//        } catch (InputMismatchException e) {
//            System.out.println("Eingabe nicht erkannt");
//            fzgBearbeiten();
//        }
    }

    // test for fzgBearbeiten
    // n.setBrand("Audi");
    // System.out.println(n.getBrand());

    private void fzgSuchen() {
        System.out.print("Suche: ");
        String searchString = getUserInput.next();
        // ToDo: Case sensitive?
        vehicleArrayList.stream().filter(v -> v.toString().contains(searchString)).forEach(v -> System.out.println(v));

        System.out.println("Möchten Sie noch einmal suchen?\n(J)a\n(N)ein");
        String choice = getUserInput.next().toLowerCase();
        if (choice.contentEquals("j")) {
            fzgSuchen();
        } else {
            hauptMenue();
        }
    }


    private void fzgLoeschen() {
    }

    private void boerseBeenden() {
        System.out.println("Möchten Sie beenden?\n(J)a\n(N)ein");
        String choice = getUserInput.next().toLowerCase();
        if (choice.contentEquals("j")) {
            saveData();
            System.out.println("Auf Wiedersehen.");
            getUserInput.close();
        } else {
            hauptMenue();
        }
    }

    private void saveData() {
        String dataString = "";
        for (Vehicle v : vehicleArrayList) {
            dataString = dataString + v.toDataString() + "\n";
        }

        try {
            FileWriter fileWriter = new FileWriter(FILENAME);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.print(dataString);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void loadData() {
        try {
            FileReader fileReader = new FileReader(FILENAME);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                String[] split = line.split(",");
                Vehicle vehicle = new Vehicle(Integer.parseInt(split[0]), split[1], split[2], split[3], split[4], Integer.parseInt(split[5]), Double.parseDouble(split[6]));
                vehicleArrayList.add(vehicle);
            }
            bufferedReader.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
