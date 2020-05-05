package logicLayer.radio;

import java.util.ArrayList;

public class Radio {
    private int volume;
    private Double frequency;
    private ArrayList<Double> Channels;
    private MusicDisc currentDisc;

    public Radio() {
        volume = 10;
    }

    public int getVolume() {
        return volume;
    }

    public Double getFrequency() {
        return frequency;
    }

    public void frequencyUp() {
        try {
            if (frequency < 110.0) frequency += 0.01;
            else {
                throw new FrequencyException(frequency);
            }
        } catch (FrequencyException fe) {
            frequency = fe.getOpposite();
        }
    }

    public void frequencyDown() {
        try {
            if (frequency > 86.0) frequency -= 0.01;
            else {
                throw new FrequencyException(frequency);
            }
        } catch (FrequencyException fe) {
            frequency = fe.getOpposite();
        }
    }

    public void loadDisc(MusicDisc disc) {
        currentDisc = disc;
    }

    public void volumeUp() {
        try {
            if (volume < 50) volume++;
            else {
                throw new VolumeException(volume);
            }
        } catch (VolumeException ve) {
            System.out.println("Nie mozna zwiekszyc glosnosci powyzej " + ve.getValue());
        }
    }

    public void volumeDown() {
        try {
            if (volume > 0) volume--;
            else {
                throw new VolumeException(volume);
            }
        } catch (VolumeException ve) {
            System.out.println("Nie mozna zmniejszyc glosnosci ponizej " + ve.getValue());
        }
    }
}
