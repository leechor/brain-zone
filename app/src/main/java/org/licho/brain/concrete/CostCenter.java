package org.licho.brain.concrete;

import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;

/**
 * The <b>CostCenter</b> may be used to define an identifiable area of responsibility where costs are allocated or
 * incurred.
 * Cost centers may be linked in a hierarchical structure for cost accounting and reporting purposes, whereby costs
 * are automatically rolled up through the cost center hierarchy.
 * <p>
 * Costs can be manually assigned to a Cost Center element by using an Assign Step. The Cost is updated by assigning
 * a value to CostCenter.Cost and the Rate are updated by assigning a value to CostCenter.Cost.Rate
 */
public class CostCenter extends AbsIntelligentPropertyObject {
    /**
     * The name of a cost center to roll up this element's cost center into. If a Parent Cost Center is not
     * explicitly specified, then costs will be automatically rolled up into the parent object containing this cost
     * center.
     */
    private ElementPropertyRow parentCostCenter;
    /**
     * The initial value of this element's Cost state, which stores the total cost that has been allocated to the
     * cost center.
     */
    private ExpressionPropertyRow initialCost;
    /**
     * The initial value of this element's Cost.Rate parameter, which indicates a cost per unit time that is to be
     * accrued to the cost center.
     */
    private ExpressionPropertyRow initialCostRate;
    private IntelligentObjectProperty dataSource;
    private IntelligentObjectProperty category;
    private IntelligentObjectProperty dataItem;
    // // TODO: 2022/3/20 new Report Statistics

    protected CostCenter(CostCenterDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new CostCenterElementRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }

    public ElementPropertyRow getParentCostCenter() {
        return this.parentCostCenter;
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
