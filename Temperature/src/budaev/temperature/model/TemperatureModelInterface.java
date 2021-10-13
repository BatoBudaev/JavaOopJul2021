package budaev.temperature.model;

import budaev.temperature.view.TemperatureViewInterface;

public interface TemperatureModelInterface {
    double getTemperature();

    String[] getTemperatureScales();

    void convertTemperature(double temperature, String scaleFrom, String scaleTo);

    void register(TemperatureViewInterface observer);

    void unregister(TemperatureViewInterface observer);

    void updateObservers();
}