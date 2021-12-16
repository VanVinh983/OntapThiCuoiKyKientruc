package com.example.sinhvienservice.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.io.Serializable;

@Entity
public class SinhVien implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String address;
    private Long idFaculty;

    public SinhVien(String name, String address, Long idFaculty) {
        this.name = name;
        this.address = address;
        this.idFaculty = idFaculty;
    }

    public SinhVien() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Long getIdFaculty() {
        return idFaculty;
    }

    public void setIdFaculty(Long idFaculty) {
        this.idFaculty = idFaculty;
    }

    @Override
    public String toString() {
        return "SinhVien{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", idFaculty=" + idFaculty +
                '}';
    }
}
