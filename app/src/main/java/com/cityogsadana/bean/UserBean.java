package com.cityogsadana.bean;

import com.google.gson.annotations.SerializedName;

import java.io.Serializable;

/**
 * Created by macbookpro on 14/10/17.
 */

public class UserBean implements Serializable {


    private String name;
    @SerializedName("country")
    private String country;
    @SerializedName("userId")
    private String user_id;
    private String gender;
    private String email;
    private String phone;
    private String address;
    @SerializedName("isEmailVerify")
    private String is_email_verified;
    private String profile_pic;
    @SerializedName("numberOfTrue")
    private String self_result;
    private LevelBean level;



    public String getSelf_result() {
        return self_result;
    }

    public void setSelf_result(String self_result) {
        this.self_result = self_result;
    }

    public String getProfile_pic() {
        return profile_pic;
    }

    public void setProfile_pic(String profile_pic) {
        this.profile_pic = profile_pic;
    }

    public String getIs_email_verified() {
        return is_email_verified;
    }

    public void setIs_email_verified(String is_email_verified) {
        this.is_email_verified = is_email_verified;
    }

    public String getAddress() {
        return address;
    }

    public String getCountry() {
        return country;
    }

    public String getEmail() {
        return email;
    }

    public String getGender() {
        return gender;
    }

    public String getUser_id() {
        return user_id;
    }

    public String getPhone() {
        return phone;
    }

    public String getName() {
        return name;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void setUser_id(String user_id) {
        this.user_id = user_id;
    }

    public LevelBean getLevel() {
        return level;
    }

    public void setLevel(LevelBean level) {
        this.level = level;
    }

}
