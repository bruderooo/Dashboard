package dataLayer;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import logicLayer.onboardComputer.OnboardComputer;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class SerializationXML {

    public static void writeComputerData(OnboardComputer computer) throws IOException {
        XStream xStream = new XStream(new DomDriver());
        String xmlString = xStream.toXML(computer);
        FileWriter writer = new FileWriter("carData.xml");
        writer.write(xmlString);
        writer.close();
    }

    public static void readComputerData(OnboardComputer computer) {
        XStream xStream = new XStream(new DomDriver());
        File file = new File("../../carData.xml");
        if (file.exists()) {
            OnboardComputer computerCopy = (OnboardComputer)xStream.fromXML(file);
            computer.getOilLevel().setCurrentAmount(computerCopy.getOilLevel().getValue());
            computer.getAccumulator().setCurrentLoad(computerCopy.getAccumulator().getValue());
            computer.getFuel().setFuelAmount(computerCopy.getFuel().getValue());
        }
    }
}
