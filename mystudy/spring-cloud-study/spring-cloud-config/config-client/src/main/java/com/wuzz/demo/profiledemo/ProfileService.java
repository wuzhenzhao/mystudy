package com.wuzz.demo.profiledemo;

/**
 * Create with IntelliJ IDEA
 * User: Wuzhenzhao
 * Date: 2019/3/13
 * Time: 18:13
 * Description:
 */
public class ProfileService {

    private String profile;

    public ProfileService(String profile) {
        this.profile = profile;
    }

    @Override
    public String toString() {
        return "ProfileService{" +
                "profile='" + profile + '\'' +
                '}';
    }
}
