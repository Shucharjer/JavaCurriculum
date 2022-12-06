import java.util.Objects;
import java.util.Scanner;

public class Student {
    private int id;
    private String name;
    private String gender;
    private String major;
    public Student() {
    }
    //id,name,gender,major四个字段
    public Student(int id, String name, String gender, String major) {
        this.id = id;
        this.name = name;
        this.gender = gender;
        this.major = major;
    }

    /**
     * 获取
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * 设置
     * @param id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * 获取
     * @return name
     */
    public String getName() {
        return name;
    }

    /**
     * 设置
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * 获取
     * @return gender
     */
    public String getGender() {
        return gender;
    }

    /**
     * 设置
     * @param gender
     */
    public void setGender(String gender) {
        this.gender = gender;
    }

    /**
     * 获取
     * @return major
     */
    public String getMajor() {
        return major;
    }

    /**
     * 设置
     * @param major
     */
    public void setMajor(String major) {
        this.major = major;
    }

    public String toString() {
        return "Student{id = " + id + ", name = " + name + ", gender = " + gender + ", major = " + major + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return id == student.id && Objects.equals(name, student.name) && Objects.equals(gender, student.gender) && Objects.equals(major, student.major);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, gender, major);
    }
}
