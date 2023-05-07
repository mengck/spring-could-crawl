package com.mengck.crawl.user;


import com.mengck.common.domain.User;


public class UserFactory {

    private static volatile User user;

    public static User instance(){
        if(user == null){
            synchronized (User.class){
                if(user ==null){
                    user = new User();
                }
            }
        }
        return user;
    }
}
