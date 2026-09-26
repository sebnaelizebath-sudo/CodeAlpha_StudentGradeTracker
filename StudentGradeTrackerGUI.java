import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.ArrayList;

public class StudentGradeTrackerGUI extends JFrame {

    // ==========================================
    // STUDENT CLASS
    // ==========================================

    static class Student {

        String name;
        ArrayList<Integer> marks;

        int total;
        double average;
        String grade;
        String status;

        int highest;
        int lowest;

        int highestSubject;
        int lowestSubject;

        int passedSubjects;
        int failedSubjects;


        public Student(String name, ArrayList<Integer> marks) {

            this.name = name;
            this.marks = marks;

            calculateResult();
        }


        // ==========================================
        // CALCULATE RESULT
        // ==========================================

        private void calculateResult() {

            total = 0;

            highest = marks.get(0);
            lowest = marks.get(0);

            highestSubject = 1;
            lowestSubject = 1;

            passedSubjects = 0;
            failedSubjects = 0;


            for (int i = 0; i < marks.size(); i++) {

                int mark = marks.get(i);

                // Total
                total += mark;


                // Highest
                if (mark > highest) {

                    highest = mark;
                    highestSubject = i + 1;
                }


                // Lowest
                if (mark < lowest) {

                    lowest = mark;
                    lowestSubject = i + 1;
                }


                // Pass / Fail subject
                if (mark >= 40) {

                    passedSubjects++;

                } else {

                    failedSubjects++;
                }
            }


            // Average
            average = (double) total / marks.size();


            // Grade
            if (average >= 90) {

                grade = "A+";

            } else if (average >= 80) {

                grade = "A";

            } else if (average >= 70) {

                grade = "B";

            } else if (average >= 60) {

                grade = "C";

            } else if (average >= 50) {

                grade = "D";

            } else {

                grade = "F";
            }


            // Overall status
            if (failedSubjects == 0) {

                status = "PASS";

            } else {

                status = "FAIL";
            }
        }
    }


    // ==========================================
    // ARRAYLIST FOR ALL STUDENTS
    // ==========================================

    private final ArrayList<Student> students =
            new ArrayList<>();


    // ==========================================
    // GUI COMPONENTS
    // ==========================================

    private JTextField nameField;
    private JTextField subjectCountField;

    private JTextArea marksArea;

    private JTable studentTable;
    private DefaultTableModel tableModel;


    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public StudentGradeTrackerGUI() {

        setTitle("Student Grade Tracker");

        setSize(1100, 750);

        setDefaultCloseOperation(
                JFrame.EXIT_ON_CLOSE
        );

        setLocationRelativeTo(null);


        // ==========================================
        // MAIN PANEL
        // ==========================================

        JPanel mainPanel =
                new JPanel(new BorderLayout(15, 15));

        mainPanel.setBorder(
                BorderFactory.createEmptyBorder(
                        20, 20, 20, 20
                )
        );


        // ==========================================
        // TITLE
        // ==========================================

        JLabel titleLabel =
                new JLabel(
                        "Student Grade Tracker",
                        SwingConstants.CENTER
                );

        titleLabel.setFont(
                new Font(
                        "Arial",
                        Font.BOLD,
                        28
                )
        );


        mainPanel.add(
                titleLabel,
                BorderLayout.NORTH
        );


        // ==========================================
        // LEFT INPUT PANEL
        // ==========================================

        JPanel inputPanel =
                new JPanel(
                        new GridBagLayout()
                );

        inputPanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Enter Student Details"
                )
        );


        GridBagConstraints gbc =
                new GridBagConstraints();

        gbc.insets =
                new Insets(8, 8, 8, 8);

        gbc.fill =
                GridBagConstraints.HORIZONTAL;


        // ------------------------------------------
        // STUDENT NAME
        // ------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0;

        inputPanel.add(
                new JLabel("Student Name:"),
                gbc
        );


        nameField =
                new JTextField(18);


        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1;

        inputPanel.add(
                nameField,
                gbc
        );


        // ------------------------------------------
        // NUMBER OF SUBJECTS
        // ------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;

        inputPanel.add(
                new JLabel("Number of Subjects:"),
                gbc
        );


        subjectCountField =
                new JTextField(18);


        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1;

        inputPanel.add(
                subjectCountField,
                gbc
        );


        // ------------------------------------------
        // MARKS
        // ------------------------------------------

        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTH;

        inputPanel.add(
                new JLabel("Enter Marks:"),
                gbc
        );


        marksArea =
                new JTextArea(10, 18);

        marksArea.setLineWrap(true);

        marksArea.setWrapStyleWord(true);


        JScrollPane marksScrollPane =
                new JScrollPane(marksArea);


        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weighty = 1;

        gbc.fill =
                GridBagConstraints.BOTH;

        inputPanel.add(
                marksScrollPane,
                gbc
        );


        // ------------------------------------------
        // INSTRUCTION
        // ------------------------------------------

        JLabel instructionLabel =
                new JLabel(
                        "<html>Enter one mark per line.<br>" +
                                "Example:<br>" +
                                "89<br>" +
                                "87<br>" +
                                "90<br>" +
                                "87<br>" +
                                "55</html>"
                );


        gbc.gridx = 0;
        gbc.gridy = 3;

        gbc.gridwidth = 2;

        gbc.weighty = 0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        inputPanel.add(
                instructionLabel,
                gbc
        );


        // ------------------------------------------
        // ADD STUDENT BUTTON
        // ------------------------------------------

        JButton addButton =
                new JButton(
                        "Add Student"
                );


        gbc.gridx = 0;
        gbc.gridy = 4;

        gbc.gridwidth = 2;

        gbc.weighty = 0;

        gbc.fill =
                GridBagConstraints.HORIZONTAL;

        inputPanel.add(
                addButton,
                gbc
        );


        mainPanel.add(
                inputPanel,
                BorderLayout.WEST
        );


        // ==========================================
        // TABLE
        // ==========================================

        String[] columns = {

                "No.",

                "Student Name",

                "Subjects",

                "Total",

                "Average",

                "Grade",

                "Status",

                "Highest",

                "Lowest",

                "Passed",

                "Failed"
        };


        tableModel =
                new DefaultTableModel(
                        columns,
                        0
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column
                    ) {

                        return false;
                    }
                };


        studentTable =
                new JTable(tableModel);


        studentTable.setRowHeight(28);


        studentTable.setFont(
                new Font(
                        "Arial",
                        Font.PLAIN,
                        12
                )
        );


        studentTable.getTableHeader()
                .setFont(
                        new Font(
                                "Arial",
                                Font.BOLD,
                                12
                        )
                );


        // ==========================================
        // COLUMN WIDTHS
        // ==========================================

        int[] widths = {

                40,
                120,
                70,
                70,
                80,
                60,
                70,
                70,
                70,
                60,
                60
        };


        for (int i = 0;
             i < widths.length;
             i++) {

            studentTable
                    .getColumnModel()
                    .getColumn(i)
                    .setPreferredWidth(
                            widths[i]
                    );
        }


        JScrollPane tableScrollPane =
                new JScrollPane(
                        studentTable
                );


        JPanel tablePanel =
                new JPanel(
                        new BorderLayout()
                );


        tablePanel.setBorder(
                BorderFactory.createTitledBorder(
                        "Student Records"
                )
        );


        tablePanel.add(
                tableScrollPane,
                BorderLayout.CENTER
        );


        mainPanel.add(
                tablePanel,
                BorderLayout.CENTER
        );


        // ==========================================
        // BOTTOM BUTTON PANEL
        // ==========================================

        JPanel bottomPanel =
                new JPanel(
                        new FlowLayout(
                                FlowLayout.CENTER,
                                10,
                                10
                        )
                );


        JButton viewButton =
                new JButton(
                        "View Result"
                );


        JButton analysisButton =
                new JButton(
                        "Overall Analysis"
                );


        JButton deleteButton =
                new JButton(
                        "Delete Selected"
                );


        JButton clearButton =
                new JButton(
                        "Clear All"
                );


        JButton clearInputButton =
                new JButton(
                        "Clear Input"
                );


        bottomPanel.add(
                viewButton
        );

        bottomPanel.add(
                analysisButton
        );

        bottomPanel.add(
                deleteButton
        );

        bottomPanel.add(
                clearButton
        );

        bottomPanel.add(
                clearInputButton
        );


        mainPanel.add(
                bottomPanel,
                BorderLayout.SOUTH
        );


        // ==========================================
        // BUTTON ACTIONS
        // ==========================================

        addButton.addActionListener(
                e -> addStudent()
        );


        viewButton.addActionListener(
                e -> viewSelectedResult()
        );


        analysisButton.addActionListener(
                e -> showOverallAnalysis()
        );


        deleteButton.addActionListener(
                e -> deleteSelectedStudent()
        );


        clearButton.addActionListener(
                e -> clearAllStudents()
        );


        clearInputButton.addActionListener(
                e -> clearInput()
        );


        // ==========================================
        // ADD MAIN PANEL
        // ==========================================

        add(mainPanel);
    }


    // ==========================================
    // ADD STUDENT
    // ==========================================

    private void addStudent() {

        String name =
                nameField.getText().trim();


        String subjectText =
                subjectCountField.getText().trim();


        String marksText =
                marksArea.getText().trim();


        // ------------------------------------------
        // CHECK NAME
        // ------------------------------------------

        if (name.isEmpty()) {

            showError(
                    "Please enter the student name."
            );

            nameField.requestFocus();

            return;
        }


        // ------------------------------------------
        // CHECK NUMBER OF SUBJECTS
        // ------------------------------------------

        int numberOfSubjects;


        try {

            numberOfSubjects =
                    Integer.parseInt(
                            subjectText
                    );

        } catch (NumberFormatException e) {

            showError(
                    "Please enter a valid number of subjects."
            );

            subjectCountField.requestFocus();

            return;
        }


        if (numberOfSubjects <= 0) {

            showError(
                    "Number of subjects must be greater than 0."
            );

            return;
        }


        // ------------------------------------------
        // CHECK MARKS
        // ------------------------------------------

        if (marksText.isEmpty()) {

            showError(
                    "Please enter the marks."
            );

            return;
        }


        String[] markLines =
                marksText.split(
                        "\\s+"
                );


        if (markLines.length != numberOfSubjects) {

            showError(
                    "You entered "
                            + markLines.length
                            + " marks, but "
                            + numberOfSubjects
                            + " subjects were specified."
            );

            return;
        }


        ArrayList<Integer> marks =
                new ArrayList<>();


        try {

            for (String markText : markLines) {

                int mark =
                        Integer.parseInt(
                                markText
                        );


                if (mark < 0 || mark > 100) {

                    showError(
                            "Each mark must be between 0 and 100."
                    );

                    return;
                }


                marks.add(mark);
            }

        } catch (NumberFormatException e) {

            showError(
                    "Please enter only valid numbers for marks."
            );

            return;
        }


        // ------------------------------------------
        // CREATE STUDENT
        // ------------------------------------------

        Student student =
                new Student(
                        name,
                        marks
                );


        // Add student to ArrayList

        students.add(student);


        // Update table

        updateTable();


        // Clear input

        clearInput();


        JOptionPane.showMessageDialog(
                this,
                "Student added successfully!",
                "Success",
                JOptionPane.INFORMATION_MESSAGE
        );
    }


    // ==========================================
    // UPDATE TABLE
    // ==========================================

    private void updateTable() {

        tableModel.setRowCount(0);


        for (int i = 0;
             i < students.size();
             i++) {

            Student student =
                    students.get(i);


            Object[] row = {

                    i + 1,

                    student.name,

                    student.marks.size(),

                    student.total,

                    String.format(
                            "%.2f",
                            student.average
                    ),

                    student.grade,

                    student.status,

                    student.highest,

                    student.lowest,

                    student.passedSubjects,

                    student.failedSubjects
            };


            tableModel.addRow(row);
        }
    }


    // ==========================================
    // VIEW SELECTED RESULT
    // ==========================================

    private void viewSelectedResult() {

        int selectedRow =
                studentTable.getSelectedRow();


        if (selectedRow == -1) {

            showError(
                    "Please select a student from the table."
            );

            return;
        }


        Student student =
                students.get(
                        selectedRow
                );


        StringBuilder result =
                new StringBuilder();


        result.append(
                "--- Student Result ---\n\n"
        );


        result.append(
                "Student Name: "
        );

        result.append(
                student.name
        );

        result.append(
                "\n"
        );


        result.append(
                "Number of Subjects: "
        );

        result.append(
                student.marks.size()
        );

        result.append(
                "\n\n"
        );


        // ------------------------------------------
        // SUBJECT MARKS
        // ------------------------------------------

        for (int i = 0;
             i < student.marks.size();
             i++) {

            result.append(
                    "Subject "
            );

            result.append(
                    i + 1
            );

            result.append(
                    ": "
            );

            result.append(
                    student.marks.get(i)
            );

            result.append(
                    "\n"
            );
        }


        result.append(
                "\n"
        );


        // ------------------------------------------
        // RESULT DETAILS
        // ------------------------------------------

        result.append(
                "Total Marks: "
        );

        result.append(
                student.total
        );

        result.append(
                "\n"
        );


        result.append(
                "Average Mark: "
        );

        result.append(
                String.format(
                        "%.2f",
    
