package budaev.temperature.view;

import budaev.temperature.controller.TemperatureControllerInterface;

import javax.swing.*;
import java.awt.*;

public class TemperatureView implements TemperatureViewInterface {
    TemperatureControllerInterface controller;
    private JFrame frame;
    private JLabel label;
    private String scaleFrom;
    private String scaleTo;
    String[] temperatureScales;

    public TemperatureView(TemperatureControllerInterface controller, String[] temperatureScales) {
        this.controller = controller;
        this.temperatureScales = temperatureScales;
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
            scaleFrom = temperatureScales[0];
            scaleTo = temperatureScales[0];

            JComboBox<String> comboBox1 = new JComboBox<>(temperatureScales);
            comboBox1.addActionListener(e -> scaleFrom = (String) comboBox1.getSelectedItem());

            JComboBox<String> comboBox2 = new JComboBox<>(temperatureScales);
            comboBox2.addActionListener(e -> scaleTo = (String) comboBox2.getSelectedItem());

            JButton button = new JButton("Перевести");
            button.addActionListener(e -> {
                try {
                    double temperature = Double.parseDouble(textField.getText());

                    controller.convertTemperature(temperature, scaleFrom, scaleTo);
                } catch (NumberFormatException exception) {
                    JOptionPane.showMessageDialog(frame, "Необходимо указать число", "ERROR", JOptionPane.ERROR_MESSAGE);
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