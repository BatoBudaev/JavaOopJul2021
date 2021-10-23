package budaev.temperature.model;

import budaev.temperature.view.TemperatureView;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModel implements TemperatureModelInterface {
    private double temperature;
    private final List<TemperatureView> listeners;
    private final TemperatureScale[] temperatureScales;

    public TemperatureModel(TemperatureScale[] temperatureScales) {
        listeners = new ArrayList<>();
        this.temperatureScales = temperatureScales;
    }

    @Override
    public void convertTemperature(double temperature, int scaleFromIndex, int scaleToIndex) {
        this.temperature = temperatureScales[scaleToIndex].convertFromCelsius(temperatureScales[scaleFromIndex].convertToCelsius(temperature));

        updateObservers();
    }


    @Override
    public double getTemperature() {
        return temperature;
    }

    public ArrayList<String> getTemperatureScaleNames() {
        ArrayList<String> TemperatureScaleNames = new ArrayList<>();

        for (TemperatureScale s : temperatureScales) {
            TemperatureScaleNames.add(s.toString());
        }

        return TemperatureScaleNames;
    }

    @Override
    public void register(TemperatureView listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void unregister(TemperatureObserver listener) {
        listeners.remove(listener);
    }

    @Override
    public void updateObservers() {
        for (TemperatureView o : listeners) {
            o.update(temperature);
        }
    }
}