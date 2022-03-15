package org.licho.brain.concrete.step;

import org.licho.brain.concrete.AbsStepProperty;
import org.licho.brain.concrete.ElementPropertyRow;
import org.licho.brain.concrete.ExpressionPropertyRow;
import org.licho.brain.concrete.IntelligentObjectXml;
import org.licho.brain.concrete.StringHelper;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.simioEnums.BOMActionRules;

import java.util.Objects;

/**
 *
 */
public class ConsumeStepProperty extends AbsStepProperty<ConsumeStepDefinition> {
    private EnumPropertyRow ownerType;
    private ElementPropertyRow ownerObject;
    private EnumPropertyRow consumptionType;
    private ElementPropertyRow materialName;
    private ExpressionPropertyRow quantity;
    private EnumPropertyRow accrueMaterialCosts;

    public ConsumeStepProperty(Class<ConsumeStepDefinition> cl, String name) {
        super(cl, name);
    }

    @Override
    protected void initProperties() {
        this.ownerType = (EnumPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "OwnerType"));
        this.ownerObject = (ElementPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "OwnerType"));
        this.consumptionType = (EnumPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "OwnerType"));
        this.materialName = (ElementPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "OwnerType"));
        this.quantity = (ExpressionPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "OwnerType"));
        this.accrueMaterialCosts = (EnumPropertyRow) this.properties.search(t -> Objects.equals(t.Name(), "OwnerType"));
    }

    public EnumPropertyRow getOwnerType() {
        return this.ownerType;
    }

    public ElementPropertyRow getOwnerObject() {
        return this.ownerObject;
    }

    public EnumPropertyRow getConsumptionType() {
        return this.consumptionType;
    }

    public ElementPropertyRow getMaterialName() {
        return this.materialName;
    }

    public ExpressionPropertyRow getQuantity() {
        return this.quantity;
    }

    public EnumPropertyRow getAccrueMaterialCosts() {
        return this.accrueMaterialCosts;
    }

    @Override
    public IntelligentObjectProperty GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 42 && StringHelper.equalsLocal(name, "Owner")) {
            return this.getOwnerType();
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }


    @Override
    public void LoadOldDefaultValuesForLoadFrom(IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 93) {
            this.getConsumptionType().StringValue(BOMActionRules.BillOfMaterials.toString());
        }
        super.LoadOldDefaultValuesForLoadFrom(intelligentObjectXml);
    }

    @Override
    public void execute(TokenRunSpace tokenRunSpace) {

    }
}
