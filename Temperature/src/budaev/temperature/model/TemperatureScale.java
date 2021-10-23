package budaev.temperature.model;

public interface TemperatureScale {
    double convertFromCelsius(double temperature);

    double convertToCelsius(double temperature);
}