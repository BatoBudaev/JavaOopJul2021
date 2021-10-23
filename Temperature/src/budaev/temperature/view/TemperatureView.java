package budaev.temperature.view;

import budaev.temperature.controller.TemperatureControllerInterface;

import java.util.ArrayList;

public interface TemperatureView {
    void start();

    void setController(TemperatureControllerInterface controller);

    void setTemperatureScalesNames(ArrayList<String> temperatureScaleNames);

    void update(double temperature);
}