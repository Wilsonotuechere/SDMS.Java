  public Student searchStudent(String matricNumber) {
        for (Student student : students) {
            if (student.getMatricNumber().equals(matricNumber)) {
                return student;
            }
        }
        return null;
    }

    public void writeToFile(String filename) {
        try (PrintWriter writer = new PrintWriter(new FileWriter(filename))) {
            for (Student student : students) {
                writer.println(student.getName() + "," + student.getDateOfBirth() + "," + student.getMatricNumber() + "," + student.getDepartment() + "," + student.getLevel());
            }
        } catch (IOException e) {
            System.err.println("Error writing to file: " + e.getMessage());
        }
    }
}
