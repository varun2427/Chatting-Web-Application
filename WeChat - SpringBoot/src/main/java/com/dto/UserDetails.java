package com.dto;

import java.util.Arrays;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.Table;

@Entity
@Table(name = "user_details")
public class UserDetails {
	
	@Id
    @GeneratedValue
    @Column(name = "id")
    private int id;

    @Column(name = "user_id")
    private int user_id;

//    @Lob
//    @Column(name = "user_image",length = 1000)
//    private byte[] userImage;
    
    @Lob
    @Column(name = "user_image")
    private String  userImage;

    @Column(name = "About")
    private String about;


    public UserDetails() {
    }

    public UserDetails(int id, int user_id, String userImage, String about) {
        this.id = id;
        this.user_id = user_id;
        this.userImage = userImage;
        this.about = about;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getUserId() {
        return user_id;
    }

    public void setUserId(int user_id) {
        this.user_id = user_id;
    }

    public String getUserImage() {
        return userImage;
    }

    public void setUserImage(String userImage) {
        this.userImage = userImage;
    }

    public String getAbout() {
        return about;
    }

    public void setAbout(String about) {
        this.about = about;
    }

	@Override
	public String toString() {
		return "UserDetails [id=" + id + ", user_id=" + user_id + ", userImage=" + userImage + ", about="
				+ about + "]";
	}
    
    
}
