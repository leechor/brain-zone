package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;

import java.text.MessageFormat;

/**
 *
 */
public class ElementReferenceStatePropertyObject extends BaseStatePropertyObject {
    private AbsDefinition absDefinition;

    public ElementReferenceStatePropertyObject(String name, boolean isReadOnly, boolean IsPrivate) {
        super(name, isReadOnly, IsPrivate);
    }

    public ElementReferenceStatePropertyObject(String name, boolean isReadOnly, boolean IsPrivate,
                                               AbsDefinition absDefinition) {
        this(name, isReadOnly, IsPrivate);
        this.absDefinition = absDefinition;
    }

    public AbsDefinition ElementType() {
        return this.absDefinition;
    }

    private void changePropertyIconIndex(AbsDefinition absDefinition) {
        this.absDefinition = absDefinition;
        super.propertyChanged("IconIndex");
    }

    @Override
    public String GetGridObjectClassName() {
        if (this.absDefinition != null) {
            return MessageFormat.format(EngineResources.TypedElementReferenceState_ClassName,
                    this.absDefinition.ExpressionIdentifier());
        }
        return EngineResources.ElementReferenceState_ClassName;
    }

    @Override
    public String GetGridObjectDescription() {
        return EngineResources.ElementReferenceState_ClassDescription;
    }

    @Override
    protected boolean SupportsDimensions() {
        return true;
    }

    @Override
    protected boolean SupportsUnitType() {
        return false;
    }

    @Override
    protected boolean SupportsDisplayFormat() {
        return false;
    }


    @Override
    public int IconIndex() {
        if (this.absDefinition instanceof IntelligentObjectDefinition) {
            return 15;
        }
        return 14;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        super.GetGridPropertyItemList(gridItemProperties, gridObjectDefinition);
        gridItemProperties.removeIf(t -> StringHelper.equalsLocal(t.DisplayName(),
                EngineResources.State_PropertyName_InitialStateValue));
        return gridItemProperties;
    }

    @Override
    public BaseStateIGridItemPropertyObject CreateInstance(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        return new ElementReferenceStateGridItemPropertyObject(this, stateIGridItemPropertyObjectList);
    }

    @Override
    public Class<?> TableType() {
        return String.class;
    }

    @Override
    protected Boolean WriteOutValueInXml() {
        return false;
    }
}
