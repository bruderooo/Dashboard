package logicLayer.sensors;

public interface Sensor {

    int GOOD = 0;
    int CHECK = 1;
    int BAD = 2;

    double getValue();

    int status();
}
