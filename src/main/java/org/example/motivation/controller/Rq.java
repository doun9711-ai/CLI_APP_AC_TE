package org.example.motivation.controller;

import java.util.HashMap;
import java.util.Map;

public class Rq {
    private String cmd;

    private Map<String, String> params;

    public String getCmd() {
        return cmd;
    }

    public void setCmd(String cmd) {
        this.cmd = cmd;
    }

    public String getParams(String key) {
        return params.get(key);
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }



    public Rq(String cmd) {
        this.cmd = cmd;

        String[] cmdBits = cmd.split("\\?", 2);

        String actionMethod = cmdBits[0]; // del

        params = new HashMap<>();
        String[] paramBits = null;
        try {
            paramBits = cmdBits[1].split("=");
        } catch (Exception e) {
            System.out.println("명령어 예외 발생");
        }

        String key = paramBits[0];
        String value = paramBits[1];
        System.out.println("key : " + key + ", value : " + value);
        params.put(key, value);
    }


}