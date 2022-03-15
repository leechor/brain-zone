package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public class SizeTableTrace extends AbsTableTrace<SizeInfo, SizeInfoExpressionConverter> {
    public SizeTableTrace(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties, org.licho.brain.concrete.property.AbsBaseRunSpace absBaseRunSpace) {
        super(baseSomeIGridItemProperties, absBaseRunSpace);
        this.t = SizeInfo.class;
        this.traits = SizeInfoExpressionConverter.class;
    }

    @Override
    public Object TableValue(IUnit unit) {
        return null;
    }

    @Override
    void InitializeStateImpl(Enum23 enum23) {

    }

    @Override
    protected void CopyFromCore(AbsBaseTrace absBaseTrace) {

    }

    public double getLength() {
        return this.initialValue.getLength();
    }

    public void setLength(double length) {
        if (length != this.initialValue.getLength()) {
            this.NotifySizeChanging(length, this.initialValue.getWidth(), this.initialValue.getHeight());
            this.initialValue.setLength(length);
            super.method_7(Enum24.Zero);
        }
    }

    public double getHeight() {
        return this.initialValue.getHeight();
    }

    public void setHeight(double value) {
        if (value != this.initialValue.getWidth()) {
            this.NotifySizeChanging(this.initialValue.getLength(), value, this.initialValue.getHeight());
            this.initialValue.setWidth(value);
            super.method_7(Enum24.Zero);
        }
    }

    public double getWidth() {
        return this.initialValue.getWidth();
    }

    public void setWidth(double value) {
        if (value != this.initialValue.getWidth()) {
            this.NotifySizeChanging(this.initialValue.getLength(), value, this.initialValue.getHeight());
            this.initialValue.setWidth(value);
            super.method_7(Enum24.Zero);
        }
    }

    private void NotifySizeChanging(double length, double width, double height) {
        IntelligentObjectRunSpace intelligentObjectRunSpace =
                (IntelligentObjectRunSpace) super.getAbsBaseRunSpace();
        if (intelligentObjectRunSpace != null) {
            intelligentObjectRunSpace.NotifySizeChanging(length, width, height);
        }
    }


}
