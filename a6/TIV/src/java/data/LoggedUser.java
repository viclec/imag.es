/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import java.util.Date;

/**
 *
 * @author Antwnis
 */
public class LoggedUser {
    private User user;
    private Boolean logged;
    private Date date;

    public LoggedUser(User user, Boolean logged, Date date) {
        this.user = user;
        this.logged = logged;
        this.date = date;
    }
    
    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getLogged() {
        return logged;
    }

    public void setLogged(Boolean logged) {
        this.logged = logged;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    public String toJson() {
        return "{" + "\"username\":\"" + user.getUserName() +
                "\", \"logged\":\"" + logged + "\", \"date\":\"" + date + "\"}";
    }
    
    
    
    
    
}
