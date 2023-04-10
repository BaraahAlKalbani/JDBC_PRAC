package org.branch154;

/**
 * The Student class represents a student with an ID, name, and email address.
 */
public class Student {
    private int id;
    private String name;
    private String email;

    /**
     * Constructs a new Student object with the given ID, name, and email address.
     * @param id the ID of the student
     * @param name the name of the student
     * @param email the email address of the student
     */
    public Student(int id, String name, String email) {
        this.id = id;
        this.name = name;
        this.email = email;
    }

    /**
     * Returns the ID of the student.
     * @return the ID of the student
     */
    public int getId() {
        return id;
    }

    /**
     * Returns the name of the student.
     * @return the name of the student
     */
    public String getName() {
        return name;
    }

    /**
     * Returns the email address of the student.
     * @return the email address of the student
     */
    public String getEmail() {
        return email;
    }
}
