package budaev.temperature.controller;

import budaev.temperature.model.TemperatureModelInterface;
import budaev.temperature.model.TemperatureListener;
import budaev.temperature.view.TemperatureView;

public class TemperatureController implements TemperatureControllerInterface, TemperatureListener {
    private final TemperatureModelInterface model;
    private final TemperatureView view;


    public TemperatureController(TemperatureModelInterface model, TemperatureView view) {
        this.model = model;
        model.register(this);

        this.view = view;
        view.setController(this);
        view.setTemperatureScalesNames(model.getTemperatureScaleNames());
        view.start();
    }

    @Override
    public void convertTemperature(double temperature, int scaleFromIndex, int scaleToIndex) {
        model.convertTemperature(temperature, scaleFromIndex, scaleToIndex);
    }

    @Override
    public void update(double temperature) {
        view.updateTemperature(temperature);
    }
}