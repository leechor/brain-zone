package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.enu.Enum36;

/**
 *
 */
public class PropertyDefinitionModel {

	private PropertyDefinitionModel(AbsDefinition absDefinition, AbsIntelligentPropertyObject absIntelligentPropertyObject, Enum36 enum36)
	{
		this.AbsDefinition = absDefinition;
		this.AbsIntelligentPropertyObject = absIntelligentPropertyObject;
		this.enum36 = enum36;
	}

	public static PropertyDefinitionModel create(AbsDefinition absDefinition)
	{
		return new PropertyDefinitionModel(absDefinition, null, Enum36.Zero);
	}

	public static PropertyDefinitionModel createZero(AbsDefinition absDefinition, AbsIntelligentPropertyObject absIntelligentPropertyObject)
	{
		return new PropertyDefinitionModel(absDefinition, absIntelligentPropertyObject, Enum36.Zero);
	}

	public static PropertyDefinitionModel createOne(AbsDefinition absDefinition, AbsIntelligentPropertyObject absIntelligentPropertyObject)
	{
		return new PropertyDefinitionModel(absDefinition, absIntelligentPropertyObject, Enum36.One);
	}

	public static PropertyDefinitionModel create(PropertyDefinitionModel propertyDefinitionModel, AbsDefinition absDefinition)
	{
		return new PropertyDefinitionModel(absDefinition, propertyDefinitionModel.AbsIntelligentPropertyObject, propertyDefinitionModel.enum36);
	}

	public static PropertyDefinitionModel create()
	{
		return new PropertyDefinitionModel(null, null, Enum36.Two);
	}

	 boolean method_0()
	{
		return this.enum36 == Enum36.Zero && this.AbsDefinition == null;
	}

	 boolean method_1()
	{
		return this.enum36 == Enum36.Zero || this.enum36 == Enum36.Two || this.enum36 == Enum36.One;
	}

	public final AbsDefinition AbsDefinition;

	public final AbsIntelligentPropertyObject AbsIntelligentPropertyObject;

	public final Enum36 enum36;

	public static final PropertyDefinitionModel PropertyDefinitionModelZero = PropertyDefinitionModel.create(null);

	public static final PropertyDefinitionModel PropertyDefinitionModelThree = new PropertyDefinitionModel(null, null,
                                                                                                                     Enum36.Three);

	public static final PropertyDefinitionModel PropertyDefinitionModelFour = new PropertyDefinitionModel(null, null,
                                                                                                                     Enum36.Four);

}
