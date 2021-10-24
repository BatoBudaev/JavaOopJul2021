package budaev.temperature.view;

import budaev.temperature.controller.TemperatureControllerInterface;
import budaev.temperature.model.TemperatureListener;

import java.util.ArrayList;

public interface TemperatureView extends TemperatureListener {
    void start();

    void setController(TemperatureControllerInterface controller);

    void setTemperatureScalesNames(ArrayList<String> temperatureScaleNames);
}