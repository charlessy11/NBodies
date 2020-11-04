package com.company;

public class CelestialBody {
    String name;
    double mass;
    int xCoordinate;
    int yCoordinate;
    double xDirection;
    double yDirection;
    int size;
    //constructor
    public CelestialBody(String name, double mass, int xCoordinate, int yCoordinate, double xDirection, double yDirection, int size)
    {
        this.name = name;
        this.mass = mass;
        this.xCoordinate = xCoordinate;
        this.yCoordinate = yCoordinate;
        this.xDirection = xDirection;
        this.yDirection = yDirection;
        this.size = size;
    }
    @Override
    public String toString() {
        return "Celestial Body {name=" + this.name + ", mass=" + this.mass + ", xCoordinate=" + this.xCoordinate + ", yCoordinate=" + this.yCoordinate + ", xDirection=" + this.xDirection + ", yDirection=" + this.yDirection + "size=" + this.size + "}";
    }
    //getters
    public String getName()
    {
        return this.name;
    }
    public double getMass()
    {
        return this.mass;
    }
    public int getxCoordinate()
    {
        return this.xCoordinate;
    }
    public int getyCoordinate()
    {
        return this.yCoordinate;
    }
    public double getxDirection()
    {
        return this.xDirection;
    }
    public double getyDirection()
    {
        return this.yDirection;
    }
    public int getSize()
    {
        return this.size;
    }
    //setters
    public void setName(String name) {
        this.name = name;
    }
    public void setMass(double mass) {
        this.mass = mass;
    }
    public void setxCoordinate(int xCoordinate) {
        this.xCoordinate = xCoordinate;
    }
    public void setyCoordinate(int yCoordinate) {
        this.yCoordinate = yCoordinate;
    }
    public void setxDirection(double xDirection) {
        this.xDirection = xDirection;
    }
    public void setyDirection(double yDirection) {
        this.yDirection = yDirection;
    }
    public void setSize(int size) {
        this.size = size;
    }
}
