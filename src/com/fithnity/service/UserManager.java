/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.fithnity.service;

import com.fithnity.entity.User;
import com.fithnity.service.UserInterface;

/**
 *
 * @author Maaouia
 */
public abstract class UserManager implements UserInterface<User> {
    private static User currentUser;

    public static void setCurrentUser(User user) {
        currentUser = user;
    }

    public static User getCurrentUser() {
        return currentUser;
    }

   

   
}
