package budaev.model;

import budaev.view.TemperatureViewInterface;

public interface TemperatureModelInterface {
    void convertFromCelsius(double temperature, String scale);

    void convertFromKelvin(double temperature, String scale);

    void convertFromFahrenheit(double temperature, String scale);

    double getTemperature();

    void register(TemperatureViewInterface observer);

    void unregister(TemperatureViewInterface observer);

    void updateObservers();
}