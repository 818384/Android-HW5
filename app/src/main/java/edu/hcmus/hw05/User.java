package edu.hcmus.hw05;

public class User {

    private String userID;
    private String userName;
    private String className;
    private int nameAvatar;
    private double pointAverage;


    public User(String userID, String userName, int nameAvatar, String className, double pointAverage) {
        this.userID = userID;
        this.userName = userName;
        this.nameAvatar = nameAvatar;
        this.className = className;
        this.pointAverage = pointAverage;
    }

    public String getUserID()
    {
        return this.userID;
    }

    public int getNameAvatar()
    {
        return this.nameAvatar;
    }

    public String getUserName()
    {
        return this.userName;
    }

    public Double getPointAVG()
    {
        return this.pointAverage;
    }

    public String getClassName()
    {
        return this.className;
    }
}
