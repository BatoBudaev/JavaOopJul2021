package budaev.temperature.model;

import java.util.ArrayList;

public interface TemperatureModelInterface {
    double getTemperature();

    ArrayList<String> getTemperatureScaleNames();

    void convertTemperature(double temperature, int scaleFrom, int scaleTo);

    void register(TemperatureObserver observer);

    void unregister(TemperatureObserver observer);

    void updateObservers();
}