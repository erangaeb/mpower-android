package com.mpower.power.pojos;

/**
 * POJO class to keep Drawer item attributes
 *
 * @author erangaeb@gmail.com (eranga herath)
 */
public class DrawerItem {
    String name;
    boolean isSelected;

    public DrawerItem(String name, boolean isSelected) {
        this.name = name;
        this.isSelected = isSelected;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isSelected() {
        return isSelected;
    }

    public void setSelected(boolean selected) {
        isSelected = selected;
    }
}
