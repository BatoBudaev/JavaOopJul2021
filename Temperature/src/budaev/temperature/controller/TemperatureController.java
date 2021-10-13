package budaev.temperature.controller;

import budaev.temperature.model.TemperatureModelInterface;
import budaev.temperature.view.TemperatureView;
import budaev.temperature.view.TemperatureViewInterface;

public class TemperatureController implements TemperatureControllerInterface {
    private final TemperatureModelInterface model;

    public TemperatureController(TemperatureModelInterface model) {
        this.model = model;
        TemperatureViewInterface temperatureView = new TemperatureView(this, model.getTemperatureScales());
        model.register(temperatureView);
        temperatureView.start();
    }

    @Override
    public void convertTemperature(double temperature, String scaleFrom, String scaleTo) {
        model.convertTemperature(temperature, scaleFrom, scaleTo);
    }
}