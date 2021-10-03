package budaev.controller;

import budaev.model.TemperatureModelInterface;
import budaev.view.TemperatureView;
import budaev.view.TemperatureViewInterface;

public class TemperatureController implements TemperatureControllerInterface {
    TemperatureModelInterface model;

    public TemperatureController(TemperatureModelInterface model) {
        this.model = model;
        TemperatureViewInterface temperatureView = new TemperatureView(this);
        model.register(temperatureView);
        temperatureView.start();
    }

    @Override
    public void convertTemperature(double temperature, String scaleFrom, String scaleTo) {
        switch (scaleFrom) {
            case "Цельсия" -> model.convertFromCelsius(temperature, scaleTo);
            case "Кельвин" -> model.convertFromKelvin(temperature, scaleTo);
            case "Фаренгейт" -> model.convertFromFahrenheit(temperature, scaleTo);
        }
    }
}
