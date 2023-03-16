package org.licho.brain.concrete;

import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.brainEnums.ElementScope;

/**
 *
 */
public class Container extends AbsIntelligentPropertyObject {
    private ExpressionPropertyRow initialVolumeCapacity;
    private ExpressionPropertyRow initialWeightCapacity;
    private EnumPropertyRow contentsRankingRule;
    private ExpressionPropertyRow contentsRankingExpression;
    private ElementPropertyRow onFullProcess;
    private ElementPropertyRow onEmptyProcess;

    protected Container(ContainerDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new ContainerElementRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }

    public ExpressionPropertyRow getInitialVolumeCapacity() {
        return this.initialVolumeCapacity;
    }

    public ExpressionPropertyRow getInitialWeightCapacity() {
        return this.initialWeightCapacity;
    }

    public EnumPropertyRow getContentsRankingRule() {
        return this.contentsRankingRule;
    }

    public ExpressionPropertyRow getContentsRankingExpression() {
        return this.contentsRankingExpression;
    }

    public ElementPropertyRow getOnFullProcess() {
        return this.onFullProcess;
    }

    public ElementPropertyRow getOnEmptyProcess() {
        return this.onEmptyProcess;
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.initialVolumeCapacity = (ExpressionPropertyRow) this.properties.get(index++);
        this.initialWeightCapacity = (ExpressionPropertyRow) this.properties.get(index++);
        this.contentsRankingRule = (EnumPropertyRow) this.properties.get(index++);
        this.contentsRankingExpression = (ExpressionPropertyRow) this.properties.get(index++);
        this.onFullProcess = (ElementPropertyRow) this.properties.get(index++);
        this.onEmptyProcess = (ElementPropertyRow) this.properties.get(index++);
        return index;
    }

    @Override
    protected AbstractGridObjectDefinition DefaultDefinition() {
        return ContainerDefinition.Instance;
    }

    @Override
    public int IconIndex() {
        return 18;
    }

    @Override
    public String getObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }
}
