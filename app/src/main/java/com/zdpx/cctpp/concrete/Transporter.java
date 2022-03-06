package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.entity.Entity;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.simioEnums.ElementScope;

/**
 * A <b>Transporter</b> object is a special type of entity that can pickup entity object at a location, carry these
 * entities
 * through a network of links or free space, and then drop the entities off at a destination.
 * <br>
 * A {@code Transporter} object also has the ability to move off of a network while maintaining association with
 * a node on the network.
 */
public class Transporter extends Entity {
    private ExpressionPropertyRow _expressionProperty12;
    private ExpressionPropertyRow _expressionProperty13;
    private ExpressionPropertyRow _expressionProperty14;

    public Transporter(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObject,
                                                 MayApplication application) {
        TransporterRunSpace transporterRunSpace = new TransporterRunSpace(sourceIntelligentObject, application, this);
        transporterRunSpace.setPopulation(new Population(transporterRunSpace));
        return transporterRunSpace;
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this._expressionProperty12 = (ExpressionPropertyRow) this.properties.values.get(index++);
        this._expressionProperty13 = (ExpressionPropertyRow) this.properties.values.get(index++);
        this._expressionProperty14 = (ExpressionPropertyRow) this.properties.values.get(index++);
        return index;
    }

    public ExpressionPropertyRow getExpressionProperty12() {
        return _expressionProperty12;
    }
    public ExpressionPropertyRow getExpressionProperty13() {
        return _expressionProperty13;
    }
    public ExpressionPropertyRow getExpressionProperty14() {
        return _expressionProperty14;
    }
}
