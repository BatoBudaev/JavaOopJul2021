package budaev.temperature_main;

import budaev.temperature.controller.TemperatureController;
import budaev.temperature.model.TemperatureModel;
import budaev.temperature.model.TemperatureModelInterface;

public class TemperatureMain {
    public static void main(String[] args) {
        TemperatureModelInterface model = new TemperatureModel();
        TemperatureController controller = new TemperatureController(model);
    }
}