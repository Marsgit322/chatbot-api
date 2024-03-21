package com.wcz.chatbot.api.domain.zsxq.model.vo;

public class Group {

    private String group_id;

    private String name;

    private String type;

    public Group() {
    }

    public Group(String group_id, String name, String type) {
        this.group_id = group_id;
        this.name = name;
        this.type = type;
    }

    public void setGroup_id(String group_id){
        this.group_id = group_id;
    }
    public String getGroup_id(){
        return this.group_id;
    }
    public void setName(String name){
        this.name = name;
    }
    public String getName(){
        return this.name;
    }
    public void setType(String type){
        this.type = type;
    }
    public String getType(){
        return this.type;
    }

}