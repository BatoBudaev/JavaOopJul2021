package budaev.model;

import budaev.view.TemperatureViewInterface;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModel implements TemperatureModelInterface {
    private double temperature;
    private final List<TemperatureViewInterface> Listeners;

    public TemperatureModel() {
        Listeners = new ArrayList<>();
    }

    @Override
    public void convertFromCelsius(double temperature, String scale) {
        if (scale.equals("Кельвин")) {
            this.temperature = temperature + 273.15;
        } else if (scale.equals("Фаренгейт")) {
            this.temperature = temperature * 1.8 + 32;
        }

        updateObservers();
    }

    @Override
    public void convertFromKelvin(double temperature, String scale) {
        if (scale.equals("Цельсия")) {
            this.temperature = temperature - 273.15;
        } else if (scale.equals("Фаренгейт")) {
            this.temperature = (temperature - 273.15) * 1.8 + 32;
        }

        updateObservers();
    }

    @Override
    public void convertFromFahrenheit(double temperature, String scale) {
        if (scale.equals("Цельсия")) {
            this.temperature = (temperature - 32) * (5. / 9);
        } else if (scale.equals("Кельвин")) {
            this.temperature = (temperature - 32) * (5. / 9) + 273.15;
        }

        updateObservers();
    }

    @Override
    public double getTemperature() {
        return temperature;
    }

    @Override
    public void register(TemperatureViewInterface listener) {
        if (!Listeners.contains(listener)) {
            Listeners.add(listener);
        }
    }

    @Override
    public void unregister(TemperatureViewInterface listener) {
        Listeners.remove(listener);
    }

    @Override
    public void updateObservers() {
        for (TemperatureViewInterface o : Listeners) {
            o.update(temperature);
        }
    }
}