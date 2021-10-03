package budaev.temperature_main;

import budaev.controller.TemperatureController;
import budaev.model.TemperatureModel;
import budaev.model.TemperatureModelInterface;

public class TemperatureMain {
    public static void main(String[] args) {
        TemperatureModelInterface model = new TemperatureModel();
        TemperatureController controller = new TemperatureController(model);
    }
}