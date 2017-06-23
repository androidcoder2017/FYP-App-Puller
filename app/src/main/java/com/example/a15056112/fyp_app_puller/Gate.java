package com.example.a15056112.fyp_app_puller;

import java.io.Serializable;

/**
 * Created by 15056112 on 20/6/2017.
 */

public class Gate implements Serializable{
    private String gateName;
    private String id;
    private String terminalName;

    public Gate(){

    }

    public Gate(String gateName, String id, String terminalName) {
        this.gateName = gateName;
        this.id = id;
        this.terminalName = terminalName;
    }

    public String getGateName() {
        return gateName;
    }

    public void setGateName(String gateName) {
        this.gateName = gateName;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTerminalName() {
        return terminalName;
    }

    public void setTerminalName(String terminalName) {
        this.terminalName = terminalName;
    }
}
