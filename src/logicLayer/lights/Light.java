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

}
