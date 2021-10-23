package budaev.temperature.model;

public class FahrenheitScale implements TemperatureScale {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature * 1.8 + 32;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * (5.0 / 9);
    }

    @Override
    public String toString() {
        return "Фаренгейт";
    }
}