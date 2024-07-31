import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class StudentDatabaseGUI extends JFrame {
    private StudentDatabase database;
    private JTextField nameField;
    private JTextField dateOfBirthField;
    private JTextField matricNumberField;
    private JTextField departmentField;
    private JTextField levelField;
    private JTextArea outputArea;

    public StudentDatabaseGUI() {
        database = new StudentDatabase();

        nameField = new JTextField(20);
        dateOfBirthField = new JTextField(10);
        matricNumberField = new JTextField(10);
        departmentField = new JTextField(20);
        levelField = new JTextField(10);
        outputArea = new JTextArea(10, 30);

        JButton addButton = new JButton("Add Student");
        addButton.addActionListener(new AddStudentListener());

        JButton removeButton = new JButton("Remove Student");
        removeButton.addActionListener(new RemoveStudentListener());

        JButton searchButton = new JButton("Search Student");
        searchButton.addActionListener(new SearchStudentListener());

        JButton writeButton = new JButton("Write to File");
        writeButton.addActionListener(new WriteToFileListener());

        JPanel inputPanel = new JPanel();
        inputPanel.add(new JLabel("Name:"));
        inputPanel.add(nameField);
        inputPanel.add(new JLabel("Date of Birth:"));
        inputPanel.add(dateOfBirthField);
        inputPanel.add(new JLabel("Matric Number:"));
        inputPanel.add(matricNumberField);
        inputPanel.add(new JLabel("Department:"));
        inputPanel.add(departmentField);
        inputPanel.add(new JLabel("Level:"));
        inputPanel.add(levelField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addButton);
        buttonPanel.add(removeButton);
        buttonPanel.add(searchButton);
        buttonPanel.add(writeButton);

        add(inputPanel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.CENTER);
        add(new JScrollPane(outputArea), BorderLayout.SOUTH);

        setSize(400, 300);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }

    private class AddStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            try {
                String name = nameField.getText();
                String dateOfBirth = dateOfBirthField.getText();
                String matricNumber = matricNumberField.getText();
                String department = departmentField.getText();
                String level = levelField.getText();
                database.addStudent(new Student(name, dateOfBirth, matricNumber, department, level));
                outputArea.append("Added student " + matricNumber + "\n");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(null, "Invalid input");
            }
        }
    }

    private class RemoveStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String matricNumber = matricNumberField.getText();
            database.removeStudent(matricNumber);
            outputArea.append("Removed student " + matricNumber + "\n");
        }
    }

    private class SearchStudentListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String matricNumber = matricNumberField.getText();
            Student student = database.searchStudent(matricNumber);
            if (student != null) {
                outputArea.append("Found student " + matricNumber + ": " + student.getName() + ", " + student.getDateOfBirth() + ", " + student.getDepartment() + ", " + student.getLevel() + "\n");
            } else {
                outputArea.append("Student not found\n");
            }
        }
    }

    private class WriteToFileListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            database.writeToFile(
                    "students.txt");
            outputArea.append("Written to file\n");
        }
    }

    public static void main(String[] args) {
        new StudentDatabaseGUI();{
        }
    }
}
