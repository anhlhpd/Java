/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package again.reflection;

import myannotation.MyId;
import myannotation.AutoGenerate;
import myannotation.Entity;

/**
 *
 * @author nguye
 */
@Entity(tableName = "students")
public class Student {

    @MyId
    @AutoGenerate
    private int id;
    private String name;
    private String rollNumber;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getRollNumber() {
        return rollNumber;
    }

    public void setRollNumber(String rollNumber) {
        this.rollNumber = rollNumber;
    }

    public Student() {
    }

    public Student(String name, String rollNumber) {
        this.name = name;
        this.rollNumber = rollNumber;
    }

    @Override
    public String toString() {
        return "Student{" + "id=" + id + ", name=" + name + ", rollNumber=" + rollNumber + '}';
    }

}
