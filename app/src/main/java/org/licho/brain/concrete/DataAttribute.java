package org.licho.brain.concrete;

import org.licho.brain.utils.simu.IUnit;

/**
 *
 */
public class DataAttribute {
    private IUnit unit;
    private BindingList<Statistic> datas;
    private InOutputStream stream;

    public DataAttribute(IUnit unit) {
        this.unit = unit;
    }

    public boolean haveData() {
        return (this.datas != null && this.datas.values.size() > 0) || this.stream != null;
    }

    public BindingList<Statistic> Data() {
        if (this.datas == null) {
            this.datas = new BindingList<>();
            this.datas.AllowNew(false);
            this.datas.AllowEdit(false);
            this.datas.AllowRemove(false);
            this.datas.RaiseListChangedEvents(true);
        }
        this.addData();
        return this.datas;
    }

    private void addData() {
        // TODO: 2021/12/17 
    }

    public void setStream(InOutputStream openReadStream) {
        this.stream = openReadStream;

    }

    public void close() {
        this.closeStream();
        if (this.datas != null) {
            this.datas.clear();
        }
    }

    private void closeStream() {
        if (this.stream != null) {
            this.stream.close();
            this.stream = null;
        }
    }
}
