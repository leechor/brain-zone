package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.DataFormat;

/**
 *
 */
public class DayPatternPropertyDefinition extends StringPropertyDefinition {

    public DayPatternPropertyDefinition(String name) {
        super(name);
        this.defaultString = super.NullNullString();
        this.dataFormat = DataFormat.List;
    }
    
    	public IntelligentObjectProperty CreateInstance(Properties properties)
	{
		return new DayPatternPropertyRow(this, properties);
	}

	public  String GetGridObjectClassName()
	{
		return EngineResources.DayPatternProperty_ClassName;
	}

	public  String GetGridObjectDescription()
	{
		return EngineResources.DayPatternProperty_ClassDescription;
	}

	public  GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition)
	{
//		return super.method_2(gridItemProperties, gridObjectDefinition);
        return null;
	}
}
