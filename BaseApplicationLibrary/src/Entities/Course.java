package Entities;

import java.util.ArrayList;

public class Course {

    private String name;
    private String code;
    private int midTermFUll;
    private int projectFUll;
    private int examFUll;

    public Course(String name, String code) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }

    public void setExamFUll(int examFUll) {
        this.examFUll = examFUll;
    }

    public void setMidTermFUll(int midTermFUll) {
        this.midTermFUll = midTermFUll;
    }

    public void setProjectFUll(int projectFUll) {
        this.projectFUll = projectFUll;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getExamFUll() {
        return examFUll;
    }

    public int getMidTermFUll() {
        return midTermFUll;
    }

    public int getProjectFUll() {
        return projectFUll;
    }

    @Override
    public String toString() {
        return "Course: " +
                "name= '" + name + '\'' +
                ", code= '" + code + '\'';
    }
}
