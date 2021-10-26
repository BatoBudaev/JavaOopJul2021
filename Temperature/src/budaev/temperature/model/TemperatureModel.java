package budaev.temperature.model;

import java.util.ArrayList;
import java.util.List;

public class TemperatureModel implements TemperatureModelInterface {
    private double temperature;
    private final List<TemperatureListener> listeners;
    private final TemperatureScale[] temperatureScales;

    public TemperatureModel(TemperatureScale[] temperatureScales) {
        listeners = new ArrayList<>();
        this.temperatureScales = temperatureScales;
    }

    @Override
    public void convertTemperature(double temperature, int scaleFromIndex, int scaleToIndex) {
        this.temperature = temperatureScales[scaleToIndex].convertFromCelsius(temperatureScales[scaleFromIndex].convertToCelsius(temperature));

        updateListeners();
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
    public void register(TemperatureListener listener) {
        if (!listeners.contains(listener)) {
            listeners.add(listener);
        }
    }

    @Override
    public void unregister(TemperatureListener listener) {
        listeners.remove(listener);
    }

    @Override
    public void updateListeners() {
        for (TemperatureListener o : listeners) {
            o.update(temperature);
        }
    }
}