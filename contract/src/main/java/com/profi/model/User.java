package com.profi.model;

import com.profi.api.IContract;

import java.util.ArrayList;
import java.util.List;

public class User {
    private String name;
    private String password;
    private String email;
    private String FIO;
    private String region;
    private String role = IContract.Role.USER;
    private String company;
    private boolean block = false;
    private List<String> supplyRegions = new ArrayList<>();
    private boolean approved = false;


    public User() {
    }

    public User(String name, String password, String email, String FIO, String region, String role, String company) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.FIO = FIO;
        this.region = region;
        this.role = role;
        this.company = company;
    }

    public String getName() {
        return name;
    }

    public boolean isBlock() {
        return block;
    }

    public String getCompany() {
        return company;
    }

    public void setCompany(String company) {
        this.company = company;
    }

    public void setBlock(boolean block) {
        this.block = block;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public boolean isApproved() {
        return approved;
    }

    public void setApproved(boolean approved) {
        this.approved = approved;
    }

    public String getFIO() {
        return FIO;
    }

    public void setFIO(String FIO) {
        this.FIO = FIO;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public List<String> getSupplyRegions() {
        return supplyRegions;
    }

    public void setSupplyRegions(List supplyRegions) {
        this.supplyRegions = supplyRegions;
    }
}