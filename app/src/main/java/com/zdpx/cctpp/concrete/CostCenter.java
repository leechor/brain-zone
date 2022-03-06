package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.simioEnums.ElementScope;

/**
 *
 */
public class CostCenter extends AbsIntelligentPropertyObject {
    private ElementPropertyRow parentCostCenter;
    private ExpressionPropertyRow initialCost;
    private ExpressionPropertyRow initialCostRate;
    private IntelligentObjectProperty dataSource;
    private IntelligentObjectProperty category;
    private IntelligentObjectProperty dataItem;

    protected CostCenter(CostCenterDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new CostCenterElementRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }

    public ExpressionPropertyRow getInitialCost() {
        return this.initialCost;
    }

    public ExpressionPropertyRow getInitialCostRate() {
        return this.initialCostRate;
    }

    public IntelligentObjectProperty getDataSource() {
        return this.dataSource;
    }

    public IntelligentObjectProperty getCategory() {
        return this.category;
    }

    public IntelligentObjectProperty getDataItem() {
        return this.dataItem;
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.parentCostCenter = (ElementPropertyRow) this.properties.get(index++);
        this.initialCost = (ExpressionPropertyRow) this.properties.get(index++);
        this.initialCostRate = (ExpressionPropertyRow) this.properties.get(index++);
        this.dataSource = this.properties.get(index++);
        this.category = this.properties.get(index++);
        this.dataItem = this.properties.get(index++);
        return index;
    }

    @Override
    protected AbsDefinition DefaultDefinition() {
        return CostCenterDefinition.Instance;
    }

    @Override
    public int IconIndex() {
        return 17;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public String ProjectItemName() {
        return null;
    }

    @Override
    public String ItemName() {
        return null;
    }

    @Override
    public String ItemTypeName() {
        return null;
    }

    @Override
    public String GetNameForKey(Object param0) {
        return null;
    }

    @Override
    public String GetDisplayNameForKey(Object param0) {
        return null;
    }

    @Override
    public String SearchableValueFor(Object param0) {
        return null;
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }
}
