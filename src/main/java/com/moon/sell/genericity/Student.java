package com.moon.sell.genericity;

/**
 * @author moonglade on 2019-01-12.
 * @version 1.0
 */
public class Student<T> {
    private T name;

    public Student(T name) {
        this.name = name;
    }

    public T getName() {
        return name;
    }

    public void setName(T name) {
        this.name = name;
    }

    public static void main(String[] args) {
        Student<Integer> student=new Student<>(11);
        System.out.println(student.getName());
    }
}
