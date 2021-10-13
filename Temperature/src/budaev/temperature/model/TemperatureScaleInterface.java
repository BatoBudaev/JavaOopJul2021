package budaev.temperature.model;

public interface TemperatureScaleInterface {
    double convertFromCelsius(double temperature);

    double convertToCelsius(double temperature);
}

class Celsius implements TemperatureScaleInterface {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature;
    }
}

class Kelvin implements TemperatureScaleInterface {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature + 273.15;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return temperature - 273.15;
    }
}

class Fahrenheit implements TemperatureScaleInterface {
    @Override
    public double convertFromCelsius(double temperature) {
        return temperature * 1.8 + 32;
    }

    @Override
    public double convertToCelsius(double temperature) {
        return (temperature - 32) * (5. / 9);
    }
}