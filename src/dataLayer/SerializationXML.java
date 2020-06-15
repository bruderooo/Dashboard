package dataLayer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import logicLayer.onboardComputer.OnboardComputer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;

/**
 * Klasa odpowiedzialna za zapis stanu pojazdu do pliku xml i jego odczyt.
 */
public class SerializationXML {

    /**
     * Metoda zapisujaca dane Klasy OnboardComputer oraz jej pol do pliku carDAta.xml.
     *
     * @param computer komputer pokladowy
     *
     * @throws IOException wyjatek sql
     */
    public static void writeComputerData(OnboardComputer computer) throws IOException {
        XStream xStream = new XStream(new DomDriver());
        String xmlString = xStream.toXML(computer);
        FileWriter writer = new FileWriter("carData.xml");
        writer.write(xmlString);
        writer.close();
    }

    /**
     * Metoda odczytujaca dane z pliku carData.xml i zapisujaca je do instancji Klasy OnboardComputer.
     *
     * @param computer komputer pokladowy
     */
    public static void readComputerData(OnboardComputer computer) {
        XStream xStream = new XStream(new DomDriver());
        File file = new File("carData.xml");
        if (file.exists()) {
            OnboardComputer computerCopy = (OnboardComputer)xStream.fromXML(file);
            computer.getOilLevel().setCurrentAmount(computerCopy.getOilLevel().getValue());
            computer.getAccumulator().setCurrentLoad(computerCopy.getAccumulator().getValue());
            computer.getFuel().setFuelAmount(computerCopy.getFuel().getValue());
            computer.getTotalKilometrage().setRouteLength(computerCopy.getTotalKilometrage().getRouteLength());
            computer.getUserKilometrage().setRouteLength(computerCopy.getUserKilometrage().getRouteLength());

            if (LocalDateTime.now().getDayOfMonth() == computerCopy.getDailyKilometrage().getDate().getDayOfMonth()) {
                computer.getDailyKilometrage().setRouteLength(computerCopy.getDailyKilometrage().getRouteLength());
            } else {
                computer.getDailyKilometrage().setRouteLength(0);
                computer.getDailyKilometrage().setDate(LocalDateTime.now());
            }
        }
    }
}
