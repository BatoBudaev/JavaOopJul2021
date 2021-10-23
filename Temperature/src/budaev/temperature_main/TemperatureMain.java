package budaev.temperature_main;

import budaev.temperature.controller.TemperatureController;
import budaev.temperature.model.*;
import budaev.temperature.view.SwingTemperatureView;
import budaev.temperature.view.TemperatureView;

public class TemperatureMain {
    public static void main(String[] args) {
        TemperatureScale[] temperatureScales = {
                new CelsiusScale(),
                new KelvinScale(),
                new FahrenheitScale()
        };

        TemperatureModelInterface model = new TemperatureModel(temperatureScales);
        TemperatureView temperatureView = new SwingTemperatureView();
        new TemperatureController(model, temperatureView);
    }
}