package domain;


import constants.ClientType;
import constants.MaritalStatus;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Student extends Client implements Serializable, GenericEntity {

    private double averageGrade;
    private int lengthOfStudy;
    private boolean stateBudgetFinancing;
    private int numberOfRemainingExams;
    private String university;
    private String faculty;
    private String course;

    public Student() {
    }

    public Student(double averageGrade, int lengthOfStudy, boolean stateBudgetFinancing, int numberOfRemainingExams,
                   String university, String faculty, String course, long id, String JMBG, String lastName,
                   String firstName, Date birthDate, String contactPhone, String address, String email,
                   MaritalStatus maritalStatus, ClientType type) {
        super(id, JMBG, lastName, firstName, birthDate, contactPhone, address, email, maritalStatus, type);
        this.averageGrade = averageGrade;
        this.lengthOfStudy = lengthOfStudy;
        this.stateBudgetFinancing = stateBudgetFinancing;
        this.numberOfRemainingExams = numberOfRemainingExams;
        this.university = university;
        this.faculty = faculty;
        this.course = course;
    }

    public double getAverageGrade() {
        return averageGrade;
    }

    public void setAverageGrade(double averageGrade) {
        this.averageGrade = averageGrade;
    }

    public int getLengthOfStudy() {
        return lengthOfStudy;
    }

    public void setLengthOfStudy(int lengthOfStudy) {
        this.lengthOfStudy = lengthOfStudy;
    }

    public boolean isStateBudgetFinancing() {
        return stateBudgetFinancing;
    }

    public void setStateBudgetFinancing(boolean stateBudgetFinancing) {
        this.stateBudgetFinancing = stateBudgetFinancing;
    }

    public int getNumberOfRemainingExams() {
        return numberOfRemainingExams;
    }

    public void setNumberOfRemainingExams(int numberOfRemainingExams) {
        this.numberOfRemainingExams = numberOfRemainingExams;
    }

    public String getUniversity() {
        return university;
    }

    public void setUniversity(String university) {
        this.university = university;
    }

    public String getFaculty() {
        return faculty;
    }

    public void setFaculty(String faculty) {
        this.faculty = faculty;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    @Override
    public String toString() {
        return super.toString();
    }

    @Override
    public boolean equals(Object obj) {
        return super.equals(obj);
    }

    @Override
    public String getTableName() {
        return "student";
    }

    @Override
    public String getColumnNamesForInsert() {
        return "ClientID, AverageGrade, LengthOfStudy, StateBudgetFinancing,"
                + " NumberOfRemainingExams, University, Faculty, Course";
    }

    @Override
    public String getInsertValues() {
        return super.getId() + ", " + averageGrade + ", " + lengthOfStudy + ", "
                + stateBudgetFinancing + ", " + numberOfRemainingExams + ", '" + university
                + "', '" + faculty + "', '" + course + "'";
    }

    @Override
    public String getInsertValuesUnprepared() {
        return null;
    }

    @Override
    public void prepareStatement(PreparedStatement statement) throws SQLException {

    }

    @Override
    public void setID(Long id) {
        super.setId(id);
    }

    @Override
    public String getSelectCondition() {
        return "ClientID=" + super.getId();
    }

    @Override
    public String getDeleteCondition() {
        return "ClientID=" + super.getId();
    }

    @Override
    public String getDeleteConditionForItem() {
        return null;
    }

    @Override
    public String getUpdateCondition() {
        return "ClientID=" + super.getId();
    }

    @Override
    public String setAttributes() {
        return "ClientID=" + super.getId() + ", AverageGrade=" + averageGrade + ", LengthOfStudy=" + lengthOfStudy
                + ", StateBudgetFinancing=" + stateBudgetFinancing + ", NumberOfRemainingExams=" + numberOfRemainingExams
                + ", University='" + university + "', Faculty='" + faculty + "', Course='" + course + "'";
    }

    @Override
    public String setAttributesDeactivate() {
        return null;
    }

    @Override
    public List<GenericEntity> getList(ResultSet rs) throws Exception {
        List<GenericEntity> list = new ArrayList<>();
        while (rs.next()) {
            Student student = new Student();
            student.setId(rs.getLong("ClientID"));
            student.setAverageGrade(rs.getDouble("AverageGrade"));
            student.setLengthOfStudy(rs.getInt("LengthOfStudy"));
            student.setStateBudgetFinancing(rs.getBoolean("StateBudgetFinancing"));
            student.setNumberOfRemainingExams(rs.getInt("NumberOfRemainingExams"));
            student.setUniversity(rs.getString("University"));
            student.setFaculty(rs.getString("Faculty"));
            student.setCourse(rs.getString("Course"));

            list.add(student);
        }
        return list;
    }

    @Override
    public String getSpecificSelectCondition() {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}
