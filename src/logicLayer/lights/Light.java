package logicLayer.lights;

public class Light {
    private boolean isOn;

    public Light() { this.isOn = false; }

    public boolean isOn() {
        return isOn;
    }

    public void switchLight() {
        isOn = !isOn;
    }

    public void switchOn() {
        isOn = true;
    }

    public void switchOff() {
        isOn = false;
    }

}
