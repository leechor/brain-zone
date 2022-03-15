package org.licho.brain.concrete;

/**
 *
 */
public class SizeStateGridItemPropertyObject extends PhysicalCharacteristicsGridItemPropertyObject {
    public SizeStateGridItemPropertyObject(SizeStatePropertyObject stateProperty, StateIGridItemPropertyObjectList propertiesList) {
        super(stateProperty, propertiesList);
    }

    public void setSize(double length, double width, double height) {
        this.setLength(length);
        this.setWidth(width);
        this.setHeight(height);
    }

    double getHeight() {
        return super.getPropertyValuesByIndex(2);
    }

    public void setLength(double param0) {
        super.setPropertyValuesByIndex(0, param0);
    }

    double getWidth() {
        return super.getPropertyValuesByIndex(1);
    }


    public void setWidth(double param0) {
        super.setPropertyValuesByIndex(1, param0);
    }

    public void setHeight(double param0) {
        super.setPropertyValuesByIndex(2, param0);
    }

    double getLength() {
        return super.getPropertyValuesByIndex(0);
    }

    public double getArea() {
        return this.getLength() * this.getWidth() * this.getHeight();
    }

    public Size getSize() {
        return new Size(this.getLength(), this.getWidth(), this.getHeight());
    }

}
