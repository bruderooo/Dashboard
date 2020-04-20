package RadioFiles;

public class FrequencyException extends Exception {
    Double frequency;

    public FrequencyException(Double value) {
        frequency = value;
    }

    public double getOpposite(){
        if ( frequency == 110.0 ){
            return 86.0;
        } else return 110.0;
    }
}
