package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.brainEnums.ElementScope;

/**
 *
 */
public class Material extends AbsIntelligentPropertyObject {
    private NumericDataPropertyRow initialQuantity;
    private EnumPropertyRow allocationRankingRule;
    private ExpressionPropertyRow alllocationRankingExpression;
    private RepeatStringPropertyRow billOfMaterials;
    private ExpressionPropertyRow costPerUnit;
    private BooleanPropertyRow logMaterialUsage;
    public Properties properties;

    public Material(MaterialDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.initialQuantity = (NumericDataPropertyRow) this.properties.get(index++);
        this.allocationRankingRule = (EnumPropertyRow) this.properties.get(index++);
        this.alllocationRankingExpression = (ExpressionPropertyRow) this.properties.get(index++);
        this.billOfMaterials = (RepeatStringPropertyRow) this.properties.get(index++);
        this.costPerUnit = (ExpressionPropertyRow) this.properties.get(index++);
        this.logMaterialUsage = (BooleanPropertyRow) this.properties.get(index++);
        return index;
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new MaterialElementRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }

    public NumericDataPropertyRow getInitialQuantity() {
        return this.initialQuantity;
    }

    public EnumPropertyRow getAllocationRankingRule() {
        return this.allocationRankingRule;
    }

    public ExpressionPropertyRow getAlllocationRankingExpression() {
        return this.alllocationRankingExpression;
    }

    public RepeatStringPropertyRow getBillOfMaterials() {
        return this.billOfMaterials;
    }

    public ExpressionPropertyRow getCostPerUnit() {
        return this.costPerUnit;
    }

    public BooleanPropertyRow getLogMaterialUsage() {
        return this.logMaterialUsage;
    }


    @Override
    protected AbstractGridObjectDefinition DefaultDefinition() {
        return MaterialDefinition.Definition;
    }

    @Override
    public int IconIndex() {
        return 3;
    }
}
