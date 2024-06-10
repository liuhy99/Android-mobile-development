package com.example.myapp2.Bean;

import java.io.Serializable;

public class ViewInfo implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String pd = "";
    private int viewId;


    public String getPd() {
        return pd;
    }

    public void setPd(String pd) {
        this.pd = pd;
    }

    public int getView() {
        return viewId;
    }

    public void setView(int viewId) {
        this.viewId = viewId;
    }
}
