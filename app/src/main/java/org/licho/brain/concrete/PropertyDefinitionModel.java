package org.licho.brain.concrete;

import org.licho.brain.enu.Enum36;

/**
 *
 */
public class PropertyDefinitionModel {

	private PropertyDefinitionModel(AbstractGridObjectDefinition abstractGridObjectDefinition, AbsIntelligentPropertyObject absIntelligentPropertyObject, Enum36 enum36)
	{
		this.AbstractGridObjectDefinition = abstractGridObjectDefinition;
		this.AbsIntelligentPropertyObject = absIntelligentPropertyObject;
		this.enum36 = enum36;
	}

	public static PropertyDefinitionModel create(AbstractGridObjectDefinition abstractGridObjectDefinition)
	{
		return new PropertyDefinitionModel(abstractGridObjectDefinition, null, Enum36.Zero);
	}

	public static PropertyDefinitionModel createZero(AbstractGridObjectDefinition abstractGridObjectDefinition, AbsIntelligentPropertyObject absIntelligentPropertyObject)
	{
		return new PropertyDefinitionModel(abstractGridObjectDefinition, absIntelligentPropertyObject, Enum36.Zero);
	}

	public static PropertyDefinitionModel createOne(AbstractGridObjectDefinition abstractGridObjectDefinition, AbsIntelligentPropertyObject absIntelligentPropertyObject)
	{
		return new PropertyDefinitionModel(abstractGridObjectDefinition, absIntelligentPropertyObject, Enum36.One);
	}

	public static PropertyDefinitionModel create(PropertyDefinitionModel propertyDefinitionModel, AbstractGridObjectDefinition abstractGridObjectDefinition)
	{
		return new PropertyDefinitionModel(abstractGridObjectDefinition, propertyDefinitionModel.AbsIntelligentPropertyObject, propertyDefinitionModel.enum36);
	}

	public static PropertyDefinitionModel create()
	{
		return new PropertyDefinitionModel(null, null, Enum36.Two);
	}

	 boolean method_0()
	{
		return this.enum36 == Enum36.Zero && this.AbstractGridObjectDefinition == null;
	}

	 boolean method_1()
	{
		return this.enum36 == Enum36.Zero || this.enum36 == Enum36.Two || this.enum36 == Enum36.One;
	}

	public final AbstractGridObjectDefinition AbstractGridObjectDefinition;

	public final AbsIntelligentPropertyObject AbsIntelligentPropertyObject;

	public final Enum36 enum36;

	public static final PropertyDefinitionModel PropertyDefinitionModelZero = PropertyDefinitionModel.create(null);

	public static final PropertyDefinitionModel PropertyDefinitionModelThree = new PropertyDefinitionModel(null, null,
                                                                                                                     Enum36.Three);

	public static final PropertyDefinitionModel PropertyDefinitionModelFour = new PropertyDefinitionModel(null, null,
                                                                                                                     Enum36.Four);

}
