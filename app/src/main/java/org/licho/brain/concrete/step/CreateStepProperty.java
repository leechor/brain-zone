package org.licho.brain.concrete.step;

import org.licho.brain.concrete.AbsStepProperty;
import org.licho.brain.concrete.DynamicObjectInstancePropertyRow;
import org.licho.brain.concrete.ElementPropertyRow;
import org.licho.brain.concrete.ExitPointPropertyRow;
import org.licho.brain.concrete.ExpressionPropertyRow;
import org.licho.brain.concrete.IntelligentObjectXml;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.entity.EnumPropertyRow;

import java.util.Objects;

/**
 *
 */
public class CreateStepProperty extends AbsStepProperty<CreateStepDefinition> {
    private EnumPropertyRow createType;
    private ElementPropertyRow sourceEntityObject;
    private DynamicObjectInstancePropertyRow objectInstanceName;
    private ExpressionPropertyRow numberOfObjects;

    public CreateStepProperty(Class<CreateStepDefinition> cl, String name) {
        super(cl, name);
    }

    @Override
    protected void initProperties() {
        this.SecondExitPointProperty = (ExitPointPropertyRow) this.properties.search(t -> Objects.equals(t.Name(),
                "Created"));
        this.createType = (EnumPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "CreateType"));
        this.sourceEntityObject = (ElementPropertyRow) this.properties.search(t -> Objects.equals(t.Name(),
                "SourceEntityObject"));
        this.objectInstanceName =
                (DynamicObjectInstancePropertyRow) this.properties.search(t -> Objects.equals(t.Name(),
                        "ObjectInstanceName"));
        this.numberOfObjects = (ExpressionPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "Created"));
    }

    public EnumPropertyRow getCreateType() {
        return this.createType;
    }

    public ElementPropertyRow getSourceEntityObject() {
        return this.sourceEntityObject;
    }

    public DynamicObjectInstancePropertyRow getObjectInstanceName() {
        return this.objectInstanceName;
    }

    public ExpressionPropertyRow getNumberOfObjects() {
        return this.numberOfObjects;
    }

    @Override
    public void DoVersionUpgrade(IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 33) {
            try {
                if (!this.getCreateType().IsAReference() && !Objects.equals(this.getCreateType().getObjectName(),
                        "NEWOBJECT")) {
                    this.getObjectInstanceName().StringValue("");
                }
            } catch (Exception ignored) {
            }
        }
        super.DoVersionUpgrade(intelligentObjectXml);
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace) {

    }
}
