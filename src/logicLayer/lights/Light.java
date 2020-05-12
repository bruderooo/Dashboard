package logicLayer.lights;

public class Light {
    private boolean isOn;

    public Light() { this.isOn = false; }

    public void switchLight() {
        isOn = !isOn;
    }

}
