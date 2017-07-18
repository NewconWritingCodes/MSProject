package edu.zju;

/**
 * Created by liukang on 2017/7/18.
 */
public enum OzWitch {
    WEST("WESTWESTWEST"),
    NORTH("NORTHNORTH"),
    EAST("EASTEAST");
    private String description;

    private OzWitch(String description){
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public static void main(String[] args){
        for(OzWitch ozWitch:OzWitch.values()){
            System.out.println(ozWitch+":"+ozWitch.getDescription());
        }

    }

}
