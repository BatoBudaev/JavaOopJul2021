package budaev.temperature.view;

import budaev.temperature.controller.TemperatureControllerInterface;
import budaev.temperature.model.TemperatureObserver;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class SwingTemperatureView implements TemperatureView {
    private TemperatureControllerInterface controller;
    private JFrame frame;
    private JLabel label;
    private ArrayList<String> temperatureScaleNames;

    public SwingTemperatureView() {

    }

    public void setController(TemperatureControllerInterface controller) {
        this.controller = controller;
    }

    public void setTemperatureScalesNames(ArrayList<String> temperatureScaleNames) {
        this.temperatureScaleNames = temperatureScaleNames;
    }

    @Override
    public void start() {
        SwingUtilities.invokeLater(() -> {
            frame = new JFrame("Перевод температуры");
            frame.setSize(400, 200);
            frame.setLocationRelativeTo(null);
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setMinimumSize(new Dimension(400, 200));
            frame.setVisible(true);

            JPanel panel = new JPanel();
            GroupLayout groupLayout = new GroupLayout(panel);
            groupLayout.setAutoCreateGaps(true);
            groupLayout.setAutoCreateContainerGaps(true);
            panel.setLayout(groupLayout);


            JTextField textField = new JTextField(20);
            label = new JLabel("Температура");

            String[] namesArray = temperatureScaleNames.toArray(new String[0]);

            JComboBox<String> comboBox1 = new JComboBox<>(namesArray);
            JComboBox<String> comboBox2 = new JComboBox<>(namesArray);

            JButton button = new JButton("Перевести");
            button.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(textField.getText());

                    controller.convertTemperature(temperature, comboBox1.getSelectedIndex(), comboBox2.getSelectedIndex());
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "Необходимо указать число", "Ошибка", JOptionPane.ERROR_MESSAGE);
                }
            });

            groupLayout.setHorizontalGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(textField)
                            .addComponent(button)
                            .addComponent(label))
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.CENTER)
                            .addComponent(comboBox1)
                            .addComponent(comboBox2))
            );

            groupLayout.setVerticalGroup(groupLayout.createSequentialGroup()
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(textField)
                            .addComponent(comboBox1))
                    .addComponent(button)
                    .addGroup(groupLayout.createParallelGroup(GroupLayout.Alignment.BASELINE)
                            .addComponent(label)
                            .addComponent(comboBox2))
            );

            frame.add(panel);
        });
    }

    @Override
    public void update(double temperature) {
        label.setText(String.valueOf(temperature));
    }
}