package org.licho.brain.concrete.node;

import org.licho.brain.concrete.AbsIntelligentPropertyObject;
import org.licho.brain.concrete.ActiveModel;
import org.licho.brain.concrete.ExpressionPropertyRow;
import org.licho.brain.concrete.GridObjectDefinition;
import org.licho.brain.concrete.IntelligentObject;
import org.licho.brain.concrete.ItemEditPolicy;
import org.licho.brain.concrete.Link;
import org.licho.brain.concrete.LinkObjectInstancePropertyRow;
import org.licho.brain.concrete.NodeClassProperty;
import org.licho.brain.concrete.entity.Entity;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.fixed.Fixed;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.api.FacilitySize;
import org.licho.brain.api.IFixedObject;
import org.licho.brain.api.ILinkObject;
import org.licho.brain.api.ILinkObjects;
import org.licho.brain.api.INodeObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Adds behaviors for modeling fixed objects that are intersection points between link objects or the entry/exit points
 * for visiting an object, {@link Entity Entities} may be picked up/dropped off by a transporter at a node, Users can
 * extend/customize the crossing logic through a node to model network flow and entity pickup/dropOff points.
 * <br>
 * A {@link Node} defines a point in space -- it may constrain movement, but it does not represent any physical space
 * itself. so, for example, a transporter that stops at a node physically remains with its full length on the
 * incoming link until it leaves the node.
 * <br>
 * The Standard Library object nodes have an associated parking area, but by default this also does not represent
 * a physical location, Internally parking is represented as a station, so it may have constrained capacity and
 * may be animated.
 */
public class Node extends Fixed implements INodeObject {
    public final List<Link> outLinkList;
    public final List<Link> inLinkList;
    private ExpressionPropertyRow _expressionProperty10;
    private ExpressionPropertyRow _expressionProperty13;
    private LinkObjectInstancePropertyRow hhxMaHxuFso;
    private EnumPropertyRow _enumProperty8;
    private EnumPropertyRow _enumProperty7;
    private EnumPropertyRow _enumProperty6;
    private EnumPropertyRow _enumProperty5;
    private ExpressionPropertyRow _expressionProperty12;
    private ExpressionPropertyRow _expressionProperty11;
    private EnumPropertyRow _enumProperty4;

    public IntelligentObject IntelligentObject;
    public NodeClassProperty NodeClassProperty;


    public Node(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
        this.outLinkList = new ArrayList<>();
        this.inLinkList = new ArrayList<>();
    }

    public static boolean isContainAt(String name) {
        return name != null && name.contains("@");
    }

    public static String[] nameArray(String name) {
        if (name != null) {
            return name.split("@");
        }
        return null;
    }

    public static String formatName(String sourceName, String name) {
        return String.format("%s@%s", sourceName, name);
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this._expressionProperty10 = (ExpressionPropertyRow) this.properties.values.get(index++);
        this._enumProperty4 = (EnumPropertyRow) this.properties.values.get(index++);
        this._expressionProperty11 = (ExpressionPropertyRow) this.properties.values.get(index++);
        this._expressionProperty12 = (ExpressionPropertyRow) this.properties.values.get(index++);
        this._enumProperty5 = (EnumPropertyRow) this.properties.values.get(index++);
        this._enumProperty6 = (EnumPropertyRow) this.properties.values.get(index++);
        this._enumProperty7 = (EnumPropertyRow) this.properties.values.get(index++);
        this._enumProperty8 = (EnumPropertyRow) this.properties.values.get(index++);
        this.hhxMaHxuFso = (LinkObjectInstancePropertyRow) this.properties.values.get(index++);
        this._expressionProperty13 = (ExpressionPropertyRow) this.properties.values.get(index++);
        return index;
    }

    @Override
    protected void OnInitializeFacilityLocation(double x, double y, double z) {

    }

    public String InstanceName() {
        if (this.IntelligentObject != null && this.NodeClassProperty != null) {
            return this.formantInstanceName(this.IntelligentObject.InstanceName());
        }
        return super.InstanceName();
    }

    public void InstanceName(String name) {
        super.InstanceName(name);
    }

    private String formantInstanceName(String name) {
        if (this.NodeClassProperty != null) {
            return this.NodeClassProperty.InstanceName() + "@" + name;
        }
        return null;
    }

    @Override
    public String getTypeName() {
        return null;
    }


    @Override
    public FacilitySize getSize() {
        return null;
    }

    @Override
    public void setSize(FacilitySize size) {

    }

    @Override
    public ILinkObjects getOutboundLinks() {
        return null;
    }

    @Override
    public ILinkObjects getInboundLinks() {
        return null;
    }

    @Override
    public IFixedObject getFixed() {
        return null;
    }

    @Override
    public String ObjectName() {
        return null;
    }

    @Override
    public void ObjectName(String objectName) {

    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    public void updateObjectName(String name) {
        super.propertyChanged(AbsIntelligentPropertyObject.instanceName);
        if (super.Parent() != null && this.IntelligentObject != null && this.NodeClassProperty != null) {
            super.Parent().updateObjectName(this, this.formantInstanceName(name));
        }
    }

    private static class Links implements ILinkObjects {

        @Override
        public ILinkObject getByName(String name) {
            return null;
        }

        @Override
        public int Count() {
            return 0;
        }

        @Override
        public ILinkObject getByIndex(int index) {
            return null;
        }

        @Override
        public Iterator<ILinkObject> iterator() {
            return null;
        }
    }
}
