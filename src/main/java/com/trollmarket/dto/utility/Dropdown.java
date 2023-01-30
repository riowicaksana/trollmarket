package com.trollmarket.dto.utility;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Dropdown {

    @Getter
    @Setter
    private String stringValue;

    @Getter
    @Setter
    private Long longValue;

    @Getter
    @Setter
    private String text;

    public Dropdown(Long longValue, String text) {
        this.longValue = longValue;
        this.text = text;
    }

    public Dropdown(String stringValue, String text) {
        this.stringValue = stringValue;
        this.text = text;
    }

    public static List<Dropdown> getListRole(){
        List<Dropdown> roleListDropdown = new ArrayList<>();
        roleListDropdown.add(new Dropdown("Seller","Seller"));
        roleListDropdown.add(new Dropdown("Buyer","Buyer"));
        roleListDropdown.add(new Dropdown("Admin","Admin"));
        return roleListDropdown;
    }

}
