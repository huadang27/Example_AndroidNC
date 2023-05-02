package com.example.example_btl_androidnc.students.model;

import com.google.firebase.firestore.auth.User;

import java.io.Serializable;

public class UserRankResponse implements Serializable {
    private User user;
    private Rank rank;

    public UserRankResponse(User user, Rank rank) {
        this.user = user;
        this.rank = rank;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Rank getRank() {
        return rank;
    }

    public void setRank(Rank rank) {
        this.rank = rank;
    }
}
