package com.zdpx.cctpp.concrete.step;

import com.zdpx.cctpp.concrete.AbsStepProperty;
import com.zdpx.cctpp.concrete.DynamicObjectInstancePropertyRow;
import com.zdpx.cctpp.concrete.ElementPropertyRow;
import com.zdpx.cctpp.concrete.ExitPointPropertyRow;
import com.zdpx.cctpp.concrete.ExpressionPropertyRow;
import com.zdpx.cctpp.concrete.IntelligentObjectXml;
import com.zdpx.cctpp.concrete.TokenRunSpace;
import com.zdpx.cctpp.concrete.entity.EnumPropertyRow;

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
