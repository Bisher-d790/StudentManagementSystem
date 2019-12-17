package Entities;

public class Grade {

    private GradeLetter gradeLetter;
    private float finalGrade;
    private float midterm;
    private float project;
    private float exam;

    public Grade(float midterm, float project, float exam) {
        this.midterm = midterm;
        this.project = project;
        this.exam = exam;
    }

    public float getExamMark() {
        return exam;
    }

    public float getMidtermMark() {
        return midterm;
    }

    public float getProjectMark() {
        return project;
    }

    public float getFinalGrade() {
        return finalGrade;
    }

    public GradeLetter getGradeLetter() {
        return gradeLetter;
    }

    public void setExamMark(float exam) {
        this.exam = exam;
    }

    public void setMidtermMark(float midterm) {
        this.midterm = midterm;
    }

    public void setProjectMark(float project) {
        this.project = project;
    }

    public void setFinalGrade(float finalGrade) {
        this.finalGrade = finalGrade;
    }

    public void setGradeLetter(GradeLetter gradeLetter) {
        this.gradeLetter = gradeLetter;
    }

    @Override
    public String toString() {
        return "Grade{" +
                ", midterm=" + midterm +
                ", project=" + project +
                ", exam=" + exam +
                "\t|\ngradeLetter=" + gradeLetter +
                ", finalGrade=" + finalGrade +
                '}';
    }
}
