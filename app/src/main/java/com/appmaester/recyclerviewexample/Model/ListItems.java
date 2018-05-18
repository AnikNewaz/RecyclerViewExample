package com.appmaester.recyclerviewexample.Model;

public class ListItems {
    private String name;
    private String realname;
    private String team;
    private String firstappear;
    private String createdby;
    private String imgUrl;

    public ListItems(String name, String realname, String team, String firstappear, String createdby, String imgUrl) {
        this.name = name;
        this.realname = realname;
        this.team = team;
        this.firstappear = firstappear;
        this.createdby = createdby;
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public String getRealname() {
        return realname;
    }

    public String getTeam() {
        return team;
    }

    public String getFirstappear() {
        return firstappear;
    }

    public String getCreatedby() {
        return createdby;
    }

    public String getImgUrl() {
        return imgUrl;
    }
}
