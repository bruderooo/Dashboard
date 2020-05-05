package logicLayer.radio;

public class VolumeException extends Exception {
    private int value;

    public VolumeException(int value) {
        this.value = value;
    }

    public String getValue() {
        return Integer.toString(value);
    }
}
