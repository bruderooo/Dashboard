package Lights;

public class HighBeam implements Light {

    private boolean isOn;

    public HighBeam() {
        this.isOn = false;
    }

    @Override
    public void switchLight() {
        this.isOn = !this.isOn;
    }
}
