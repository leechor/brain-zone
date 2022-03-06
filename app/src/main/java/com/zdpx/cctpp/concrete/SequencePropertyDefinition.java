package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.DataFormat;

/**
 *
 */
public class SequencePropertyDefinition extends StringPropertyDefinition {

    public SequencePropertyDefinition(String name) {
        super(name);
        super.DefaultString(super.NullNullString());
        this.dataFormat = DataFormat.List;
    }

	@Override
    public IntelligentObjectProperty CreateInstance(Properties properties)
	{
		return new SequencePropertyInTableRow(this, properties);
	}

	@Override
	public  String GetGridObjectClassName()
	{
		return EngineResources.SequenceProperty_ClassName;
	}

	@Override
	public  String GetGridObjectDescription()
	{
		return EngineResources.SequenceProperty_ClassDescription;
	}

	@Override
	  IValueProvider ValueProvider()
	{
			return new SequencePropertyValueProvider(super.NullNullString());
		}
}
