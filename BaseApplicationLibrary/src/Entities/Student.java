package Entities;

public class Student {

    private String name;
    private String metricNum;

    public Student(String name, String metricNum) {
        this.name = name;
        this.metricNum = metricNum;
    }

    public String getName() {
        return name;
    }

    public String getMetricNum() {
        return metricNum;
    }

    @Override
    public String toString() {
        return "name='" + name + '\'' +
                ", metricNum='" + metricNum + '\'';
    }
}
