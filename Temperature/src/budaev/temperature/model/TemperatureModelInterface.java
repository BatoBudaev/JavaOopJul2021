package budaev.temperature.model;

import java.util.ArrayList;

public interface TemperatureModelInterface {
    double getTemperature();

    ArrayList<String> getTemperatureScaleNames();

    void convertTemperature(double temperature, int scaleFrom, int scaleTo);

    void register(TemperatureListener observer);

    void unregister(TemperatureListener observer);

    void updateObservers();
}