package interfacesLayer;

import dataLayer.ConnectToSQL;
import dataLayer.SerializationXML;
import javafx.application.Application;
import logicLayer.onboardComputer.OnboardComputer;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws SQLException, IOException {
        if (args.length == 0) {
            Application.launch(Dashboard.class, args);
        } else if ("-c".equals(args[0]) && args.length == 1) {
            OnboardComputer computer = new OnboardComputer();
            SerializationXML.readComputerData(computer);
            ConnectToSQL sql = new ConnectToSQL();
            sql.getAllRoutes(computer.getRoutes());

            Scanner scanner = new Scanner(System.in);
            boolean isAppOn = true;

            while (isAppOn) {
                System.out.println("1. Odczytaj zapisane trasy.\n" +
                        "2. Odczytaj poziom paliwa, oleju, naładowania akumulatora.\n" +
                        "3. Przebiegi liczników.\n" +
                        "4. Aby zakończyć działanie programu.");
                int choice = Integer.parseInt(scanner.nextLine());
                if (choice == 1) {
                    for (int i = 0; i < computer.getRoutes().size(); i++) {
                        System.out.println(computer.getRoutes().get(i).toString());
                    }
                    System.out.println("\nWcisnij enter aby kontynuować.");
                    System.in.read();
                } else if (choice == 2) {
                    System.out.println(computer.getAccumulator().toString() + "\n" +
                            computer.getFuel().toString() + "\n" + computer.getOilLevel().toString() + "\n");
                    System.out.println("\nWcisnij enter aby kontynuować.");
                    System.in.read();
                } else if (choice == 3) {
                    System.out.println("Przebieg całkowity: " + computer.getTotalKilometrage().toString() + "\n" +
                            "Przebieg dzienny: " + computer.getDailyKilometrage().toString() + "\n" +
                            "Przebieg użytkownika: " + computer.getUserKilometrage().toString() + "\n");
                    System.out.println("Wpisz \"usun\" aby zresetować przebieg, wcisnij enter aby kontynuować");
                    if ( scanner.nextLine().equals("usun") ) {
                        computer.resetUserKilometrage();
                    }
                } else if (choice == 4) {
                    isAppOn = false;
                }
                System.out.println("\n\n\n\n\n\n\n\n\n\n");
            }
        } else {
            System.out.println("Podano niepoprawny argument, proszę nie podawać żadnego, aby uruchomić w trybie interfejsu graficznego, lub podać \"-c\" aby uruchomic w trybie tekstowym");
        }
    }
}
