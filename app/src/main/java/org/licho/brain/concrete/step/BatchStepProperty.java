package org.licho.brain.concrete.step;

import org.licho.brain.concrete.AbsStepProperty;
import org.licho.brain.concrete.ElementPropertyRow;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.entity.EnumPropertyRow;

import java.util.Objects;

/**
 *
 */
public class BatchStepProperty extends AbsStepProperty<BatchStepDefinition> {
    private EnumPropertyRow entityType;
    private ElementPropertyRow entityObject;
    private ElementPropertyRow batchLogicName;
    private EnumPropertyRow category;

    public BatchStepProperty(Class<BatchStepDefinition> cl, String name) {
        super(cl, name);
    }

    @Override
    protected void initProperties() {
        this.entityType = (EnumPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "EntityType"));
        this.entityObject = (ElementPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "EntityObject"));
        this.batchLogicName = (ElementPropertyRow) this.properties.search(t -> Objects.equals(t.Name(),
                "BatchLogicName"));
        this.category = (EnumPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "Category"));
        super.initProperties();
    }

    public EnumPropertyRow getEntityType() {
        return this.entityType;
    }

    public ElementPropertyRow getEntityObject() {
        return this.entityObject;
    }

    public ElementPropertyRow getBatchLogicName() {
        return this.batchLogicName;
    }

    public EnumPropertyRow getCategory() {
        return this.category;
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace) {
// TODO: 2022/2/8 
    }
}
