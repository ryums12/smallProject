package com.ryums.bookmark.utils;

import org.springframework.ui.ModelMap;

public class UtilMethod {

    public ModelMap setType(String type) {
        ModelMap modelMap = new ModelMap();
        modelMap.put("type", type);
        return modelMap;
    }
}
