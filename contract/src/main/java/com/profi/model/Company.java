package com.profi.model;

import java.util.ArrayList;
import java.util.List;

public class Company {
    private String companyName;
    private List<String> usersInCompany = new ArrayList<>();
    private List<Product> companyShop = new ArrayList<>();

    public Company(String companyName) {
        this.companyName = companyName;
    }

    public Company() {
    }

    public List<Product> getCompanyShop() {
        return companyShop;
    }

    public void addProduct(Product product) {
        this.companyShop.add(product);
    }

    public void setCompanyShop(List<Product> companyShop) {
        this.companyShop = companyShop;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void addUser(String user) {
        this.usersInCompany.add(user);
    }

    public List<String> getUsersInCompany() {
        return usersInCompany;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public void setUsersInCompany(List<String> usersInCompany) {
        this.usersInCompany = usersInCompany;
    }

}
