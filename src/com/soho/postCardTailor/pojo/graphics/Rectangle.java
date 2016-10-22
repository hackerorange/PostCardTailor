package com.soho.postCardTailor.pojo.graphics;

/**
 * 存放裁切框信息
 */
public class Rectangle {
    private Point location;
    private Size size;

    public Rectangle() {
    }

    public Rectangle(Point location, Size size) {

        this.location = location;
        this.size = size;
    }

    public Point getLocation() {
        return location;
    }

    public void setLocation(Point location) {
        this.location = location;
    }

    @Override
    public String toString() {
        return "Rectangle{" +
                "location=" + location +
                ", size=" + size +
                '}';
    }

    public Size getSize() {
        return size;
    }

    public void setSize(Size size) {
        this.size = size;
    }
}
