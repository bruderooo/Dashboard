package logicLayer.lights;

public class Light {

    private boolean isOn;

    public Light(boolean isOn) {
        this.isOn = isOn;
    }

    public void switchLight() {
        if (this.isOn) {
            System.out.println("Wlaczone");
        } else {
            System.out.println("Wylacczone");
        }
        this.isOn = !this.isOn;
    }

}
