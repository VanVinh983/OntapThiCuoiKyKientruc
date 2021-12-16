package com.example.sinhvienservice.entity;

public class StudentAndFacultyVo {
    private SinhVien sinhVien;
    private Faculty faculty;

    public StudentAndFacultyVo(SinhVien sinhVien, Faculty faculty) {
        this.sinhVien = sinhVien;
        this.faculty = faculty;
    }

    public StudentAndFacultyVo() {
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }

    public Faculty getFaculty() {
        return faculty;
    }

    public void setFaculty(Faculty faculty) {
        this.faculty = faculty;
    }

    @Override
    public String toString() {
        return "StudentAndFacultyVo{" +
                "sinhVien=" + sinhVien +
                ", faculty=" + faculty +
                '}';
    }
}
