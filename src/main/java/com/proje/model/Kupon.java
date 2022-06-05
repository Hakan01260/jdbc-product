package com.proje.model;

import java.util.ArrayList;
import java.util.List;

public class Kupon {

    private int kuponId;

    private String kuponName;

    private List<User> users;

    public Kupon() {
    }

    public Kupon(int kuponId, String kuponName) {
        this.kuponId = kuponId;
        this.kuponName = kuponName;
    }

    public int getKuponId() {
        return kuponId;
    }

    public void setKuponId(int kuponId) {
        this.kuponId = kuponId;
    }

    public String getKuponName() {
        return kuponName;
    }

    public void setKuponName(String kuponName) {
        this.kuponName = kuponName;
    }

    public List<User> getUsers() {

        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }

    @Override
    public String toString() {
        return "Kupon{" +
                "kuponId=" + kuponId +
                ", kuponName='" + kuponName + '\'' +
                '}';
    }
}
