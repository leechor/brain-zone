package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;

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
	public  String getObjectClassName()
	{
		return EngineResources.SequenceProperty_ClassName;
	}

	@Override
	public  String getObjectDescription()
	{
		return EngineResources.SequenceProperty_ClassDescription;
	}

	@Override
	  IValueProvider ValueProvider()
	{
			return new SequencePropertyValueProvider(super.NullNullString());
		}
}
