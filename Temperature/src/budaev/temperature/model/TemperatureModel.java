package budaev.temperature.model;

import budaev.temperature.view.TemperatureViewInterface;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModel implements TemperatureModelInterface {
    private double temperature;
    private final List<TemperatureViewInterface> listeners;
    private final String[] temperatureScales = {
            "Цельсия",
            "Кельвин",
            "Фаренгейт"
    };

    public TemperatureModel() {
        listeners = new ArrayList<>();
    }

    @Override
    public void convertTemperature(double temperature, String scaleFrom, String scaleTo) {
        Celsius celsius = new Celsius();
        Kelvin kelvin = new Kelvin();
        Fahrenheit fahrenheit = new Fahrenheit();

        switch (scaleFrom) {
            case "Цельсия" -> this.temperature = celsius.convertToCelsius(temperature);
            case "Кельвин" -> this.temperature = kelvin.convertToCelsius(temperature);
            case "Фаренгейт" -> this.temperature = fahrenheit.convertToCelsius(temperature);
        }

        switch (scaleTo) {
            case "Цельсия" -> this.temperature = celsius.convertFromCelsius(this.temperature);
            case "Кельвин" -> this.temperature = kelvin.convertFromCelsius(this.temperature);
            case "Фаренгейт" -> this.temperature = fahrenheit.convertFromCelsius(this.temperature);
        }

        updateObservers();
    }


    @Override
    public double getTemperature() {
        return temperature;
    }

    public String[] getTemperatureScales() {
        return temperatureScales;
    }

    @Override
    public void register(TemperatureViewInterface listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void unregister(TemperatureViewInterface listener) {
        listeners.remove(listener);
    }

    @Override
    public void updateObservers() {
        for (TemperatureViewInterface o : listeners) {
            o.update(temperature);
        }
    }
}