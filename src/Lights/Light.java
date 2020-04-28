package Lights;

public abstract class Light {

    private boolean isOn;

    public Light(boolean isOn) {
        this.isOn = isOn;
    }

    public void switchLight() {
        this.isOn = !this.isOn;
    }

}
