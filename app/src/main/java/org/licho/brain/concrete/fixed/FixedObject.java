package org.licho.brain.concrete.fixed;

import org.licho.brain.brainApi.FacilityLocation;
import org.licho.brain.brainApi.FacilitySize;
import org.licho.brain.brainApi.IFixedObject;
import org.licho.brain.brainApi.INodeObject;
import org.licho.brain.brainApi.INodeObjects;
import org.licho.brain.brainApi.IProperties;

import java.util.Iterator;

/**
 *
 */
public class FixedObject implements IFixedObject, INodeObjects {

    private Fixed fixed;

    public FixedObject(Fixed fixed)
	{
		this.fixed = fixed;
	}

    @Override
    public String getTypeName() {
        return null;
    }

    @Override
    public INodeObjects getNodes() {
        return null;
    }

    @Override
    public FacilityLocation Location() {
        return null;
    }

    @Override
    public void Location(FacilityLocation location) {

    }

    @Override
    public FacilitySize getSize() {
        return null;
    }

    @Override
    public void setSize(FacilitySize size) {

    }

    @Override
    public INodeObject getByName(String name) {
        return null;
    }

    @Override
    public String ObjectName() {
        return null;
    }

    @Override
    public void ObjectName(String objectName) {

    }

    @Override
    public IProperties getProperties() {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public INodeObject getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<INodeObject> iterator() {
        return null;
    }
}
