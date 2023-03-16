package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;

import java.text.MessageFormat;

/**
 *
 */
public class ElementReferenceStatePropertyObject extends BaseStatePropertyObject {
    private AbstractGridObjectDefinition abstractGridObjectDefinition;

    public ElementReferenceStatePropertyObject(String name, boolean isReadOnly, boolean IsPrivate) {
        super(name, isReadOnly, IsPrivate);
    }

    public ElementReferenceStatePropertyObject(String name, boolean isReadOnly, boolean IsPrivate,
                                               AbstractGridObjectDefinition abstractGridObjectDefinition) {
        this(name, isReadOnly, IsPrivate);
        this.abstractGridObjectDefinition = abstractGridObjectDefinition;
    }

    public AbstractGridObjectDefinition ElementType() {
        return this.abstractGridObjectDefinition;
    }

    private void changePropertyIconIndex(AbstractGridObjectDefinition abstractGridObjectDefinition) {
        this.abstractGridObjectDefinition = abstractGridObjectDefinition;
        super.propertyChanged("IconIndex");
    }

    @Override
    public String getObjectClassName() {
        if (this.abstractGridObjectDefinition != null) {
            return MessageFormat.format(EngineResources.TypedElementReferenceState_ClassName,
                    this.abstractGridObjectDefinition.ExpressionIdentifier());
        }
        return EngineResources.ElementReferenceState_ClassName;
    }

    @Override
    public String getObjectDescription() {
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
        if (this.abstractGridObjectDefinition instanceof IntelligentObjectDefinition) {
            return 15;
        }
        return 14;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                  GridObjectDefinition gridObjectDefinition) {
        super.getPropertyItemList(gridItemProperties, gridObjectDefinition);
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
