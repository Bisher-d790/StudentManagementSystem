package Entities;

import java.util.ArrayList;

public class Course {

    private String name;
    private String code;

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

    @Override
    public String toString() {
        return "Course: " +
                "name= '" + name + '\'' +
                ", code= '" + code + '\'';
    }
}
