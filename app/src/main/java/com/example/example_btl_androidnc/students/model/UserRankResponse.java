package com.example.example_btl_androidnc.students.model;

import com.example.example_btl_androidnc.students.model.Rank;
import com.example.example_btl_androidnc.students.model.Users;

import java.io.Serializable;

public class UserRankResponse implements Serializable {
    private Users user;
    private Rank rank;

    public UserRankResponse(Users users, Rank rank) {
        this.user = users;
        this.rank = rank;
    }

    public UserRankResponse() {
    }

    @Override
    public String toString() {
        return "UserRankResponse{" +
                "users=" + user +
                ", rank=" + rank +
                '}';
    }

    public Users getUsers() {
        return user;
    }

    public void setUsers(Users users) {
        this.user = users;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
