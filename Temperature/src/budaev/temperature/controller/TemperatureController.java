package budaev.temperature.controller;

import budaev.temperature.model.TemperatureModelInterface;
import budaev.temperature.view.TemperatureView;

public class TemperatureController implements TemperatureControllerInterface {
    private final TemperatureModelInterface model;

    public TemperatureController(TemperatureModelInterface model, TemperatureView view) {
        this.model = model;
        model.register(view);

        view.setController(this);
        view.setTemperatureScalesNames(model.getTemperatureScaleNames());
        view.start();
    }

    @Override
    public void convertTemperature(double temperature, int scaleFromIndex, int scaleToIndex) {
        model.convertTemperature(temperature, scaleFromIndex, scaleToIndex);
    }
}