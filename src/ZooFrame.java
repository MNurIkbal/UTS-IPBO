import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ZooFrame extends JFrame {
    private JTextField nameField, ageField, furColorField;
    private JCheckBox canFlyCheckBox;
    private JComboBox<String> animalTypeComboBox;
    private JTextArea logArea;
    private ArrayList<Animal> zooAnimals;

    public ZooFrame() {
        // Frame setup
        setTitle("Digital Zoo Manager");
        setSize(500, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        zooAnimals = new ArrayList<>();

        // Top panel - Form
        JPanel formPanel = new JPanel(new GridLayout(5, 2));

        formPanel.add(new JLabel("Name:"));
        nameField = new JTextField();
        formPanel.add(nameField);

        formPanel.add(new JLabel("Age:"));
        ageField = new JTextField();
        formPanel.add(ageField);

        formPanel.add(new JLabel("Animal Type:"));
        animalTypeComboBox = new JComboBox<>(new String[]{"Generic Mammal", "Generic Bird"});
        formPanel.add(animalTypeComboBox);

        formPanel.add(new JLabel("Fur Color (for Mammal):"));
        furColorField = new JTextField();
        formPanel.add(furColorField);

        formPanel.add(new JLabel("Can Fly? (for Bird):"));
        canFlyCheckBox = new JCheckBox();
        formPanel.add(canFlyCheckBox);

        add(formPanel, BorderLayout.NORTH);

        // Center panel - Log Area
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);

        // Bottom panel - Button
        JButton addButton = new JButton("Add Animal");
        add(addButton, BorderLayout.SOUTH);

        // Button Action
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addAnimal();
            }
        });
    }

    private void addAnimal() {
        try {
            String name = nameField.getText();
            int age = Integer.parseInt(ageField.getText());
            String selectedType = (String) animalTypeComboBox.getSelectedItem();

            Animal newAnimal;

            if ("Generic Mammal".equals(selectedType)) {
                String furColor = furColorField.getText();
                newAnimal = new Mammal(name, age, furColor);
                zooAnimals.add(newAnimal);
                logArea.append("Added a new Mammal! Info: " + newAnimal.getInfo() +
                        ". Sound: " + newAnimal.makeSound() + "\n");
            } else {
                boolean canFly = canFlyCheckBox.isSelected();
                newAnimal = new Bird(name, age, canFly);
                zooAnimals.add(newAnimal);
                logArea.append("Added a new Bird! Info: " + newAnimal.getInfo() +
                        ". Sound: " + newAnimal.makeSound() + "\n");
            }

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error: " + ex.getMessage());
        }
    }


}

