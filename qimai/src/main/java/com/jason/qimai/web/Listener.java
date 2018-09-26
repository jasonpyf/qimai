package com.jason.qimai.web;

public interface Listener {
	
    void onSuccess(Object... args);

    void onFailure(Throwable cause);
    
}