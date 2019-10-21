package com.learn.gof.factory;

/**
 * 电视机类
 *
 * @author Colm
 * @create 2019/10/21
 */
public class TVSet {
    private String brand;
    private double size;
    private String color;

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
