package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.entity.Entity;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fixed.Fixed;
import org.licho.brain.concrete.node.Node;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.element.Network;
import org.licho.brain.simioEnums.ElementScope;
import org.licho.brain.simuApi.FacilityLocation;
import org.licho.brain.simuApi.ILinkNetworks;
import org.licho.brain.simuApi.ILinkObject;
import org.licho.brain.simuApi.ILinkVertices;
import org.licho.brain.simuApi.INetworkElementObject;
import org.licho.brain.simuApi.INodeObject;
import org.licho.brain.utils.simu.IReselect;
import org.licho.brain.utils.simu.LinkType;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

/**
 * Adds behaviors for modeling fixed objects that are pathways for entity/transporter movement,
 * <p>A link object has a length which may be separated into equally spaced locations(cells),
 * must have a start node and end node, and is a member of one or more networks,
 * <p>A {@link Link} object transfers an Entity over a pathway defined by a polyline
 * connecting two {@link Node} objects. {@link Link} control and position is determined by the leading edge of an
 * Entity.
 * <p/>As soon as an Entity's leading edge moves onto a link, the {@link Entity} and its speed are controlled by the
 * logic built
 * into that Link, When the Entity's leading edge moves off the link(perhaps onto a connected link), the Entity is no
 * longer controlled by the initial Link even if the trailing edge of the Entity remains on the initial Link. Control
 * transfers to the subsequent Link as the Entity's leading edge transfers.
 * <p></p>A traveling entity is considered Accumulated on a link if the entity has reached the end of the link and
 * has been
 * stopped there without being engaged to the link, or if the entity's leading edge collided with the trailing edge of
 * an entity in front of it on the link, and the entity has accumulated behind that entity without being engaged to
 * the link.
 * Once flagged as Accumulated, the entity will continue to be considered Accumulated until either its leading edge
 * leaves the link or the collision situation is cleared. *
 */
public class Link extends Fixed implements ILinkObject, IReselect {
    private EnumPropertyRow type;
    private ExpressionPropertyRow initialTravelerCapacity;
    private EnumPropertyRow trafficDirectionRule;
    private EnumPropertyRow initialDesiredDirection;
    private EnumPropertyRow entryRankingRule;
    private ExpressionPropertyRow entryRankingExpression;
    private ExpressionPropertyRow initialDesiredSpeed;
    private EnumPropertyRow entityAlignment;
    private EnumPropertyRow cellSpacingType;
    private NumericDataPropertyRow numberOfCells;
    private NumericDataPropertyRow cellSize;
    private EnumPropertyRow autoAlignCells;
    private ExpressionPropertyRow selectionWeight;
    private BooleanPropertyRow drawnToScale;
    private NumericDataPropertyRow logicalLength;
    private EnumPropertyRow crossSectionShape;
    private ExpressionPropertyRow trapezoidRatio;
    public List<Network> networkProperties;
    public LinkType linkType;

    public Node beginNode;
    public Node endNode;
    private LinkVertices linkVertices;
    private LinkNetworks networks;
    private double distance;
    private int locationsCount;
    public Location[] Locations;
    public double[] distances;
    public Direction[] Directions;

    public EnumPropertyRow getType() {
        return this.type;
    }

    public ExpressionPropertyRow getInitialTravelerCapacity() {
        return this.initialTravelerCapacity;
    }

    public EnumPropertyRow getTrafficDirectionRule() {
        return this.trafficDirectionRule;
    }

    public EnumPropertyRow getInitialDesiredDirection() {
        return this.initialDesiredDirection;
    }

    public EnumPropertyRow getEntryRankingRule() {
        return this.entryRankingRule;
    }

    public ExpressionPropertyRow getEntryRankingExpression() {
        return this.entryRankingExpression;
    }

    public ExpressionPropertyRow getInitialDesiredSpeed() {
        return this.initialDesiredSpeed;
    }

    public EnumPropertyRow getEntityAlignment() {
        return this.entityAlignment;
    }

    public EnumPropertyRow getCellSpacingType() {
        return this.cellSpacingType;
    }

    public NumericDataPropertyRow getNumberOfCells() {
        return this.numberOfCells;
    }

    public NumericDataPropertyRow getCellSize() {
        return this.cellSize;
    }

    public EnumPropertyRow getAutoAlignCells() {
        return this.autoAlignCells;
    }

    public ExpressionPropertyRow getSelectionWeight() {
        return this.selectionWeight;
    }

    public BooleanPropertyRow getDrawnToScale() {
        return this.drawnToScale;
    }

    public NumericDataPropertyRow getLogicalLength() {
        return this.logicalLength;
    }

    public EnumPropertyRow getCrossSectionShape() {
        return this.crossSectionShape;
    }

    public ExpressionPropertyRow getTrapezoidRatio() {
        return this.trapezoidRatio;
    }

    public Link(LinkDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
        this.networkProperties = new ArrayList<>(1);
        super.getSizeStateGridItemProperties().setHeight(definition.DefaultHeight());
        super.getSizeStateGridItemProperties().setWidth(definition.DefaultWidth());
    }

    public static Link readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                               LinkDefinition linkDefinition, IntelligentObjectDefinition intelligentObjectDefinition) {
        Node start = null;
        Node end = null;
        String startName = xmlReader.GetAttribute("Start");
        if (!Strings.isNullOrEmpty(startName)) {
            startName = Link.getName(startName, intelligentObjectXml);
            start = intelligentObjectDefinition.getNodeByName(startName);
        }
        String endName = xmlReader.GetAttribute("End");
        if (!Strings.isNullOrEmpty(endName)) {
            endName = Link.getName(endName, intelligentObjectXml);
            end = intelligentObjectDefinition.getNodeByName(endName);
        }
        if (start != null && end != null) {
            return intelligentObjectDefinition.addLink(linkDefinition, start, end, ElementScope.Private);
        }
        return null;

    }

    private static String getName(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.method_17()) {
            if (!Node.isContainAt(name)) {
                return intelligentObjectXml.getObjectName(name);
            }
            String[] na = Node.nameArray(name);
            if (na.length == 2) {
                String objectName = intelligentObjectXml.getObjectName(na[1]);
                return Node.formatName(na[0], objectName);
            }
        }
        return name;

    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.type = (EnumPropertyRow) this.properties.get(index++);
        this.initialTravelerCapacity = (ExpressionPropertyRow) this.properties.get(index++);
        this.trafficDirectionRule = (EnumPropertyRow) this.properties.get(index++);
        this.initialDesiredDirection = (EnumPropertyRow) this.properties.get(index++);
        this.entryRankingRule = (EnumPropertyRow) this.properties.get(index++);
        this.entryRankingExpression = (ExpressionPropertyRow) this.properties.get(index++);
        this.initialDesiredSpeed = (ExpressionPropertyRow) this.properties.get(index++);
        this.entityAlignment = (EnumPropertyRow) this.properties.get(index++);
        this.cellSpacingType = (EnumPropertyRow) this.properties.get(index++);
        this.numberOfCells = (NumericDataPropertyRow) this.properties.get(index++);
        this.cellSize = (NumericDataPropertyRow) this.properties.get(index++);
        this.autoAlignCells = (EnumPropertyRow) this.properties.get(index++);
        this.selectionWeight = (ExpressionPropertyRow) this.properties.get(index++);
        this.drawnToScale = (BooleanPropertyRow) this.properties.get(index++);
        this.logicalLength = (NumericDataPropertyRow) this.properties.get(index++);
        this.crossSectionShape = (EnumPropertyRow) this.properties.get(index++);
        this.trapezoidRatio = (ExpressionPropertyRow) this.properties.get(index++);
        return index;
    }


    @Override
    protected boolean ShowPositionProperty() {
        return false;
    }

    @Override
    protected boolean ShowSizeListIndex() {
        return false;
    }

    @Override
    public INodeObject Begin() {
        return this.beginNode;
    }

    @Override
    public INodeObject End() {
        return this.endNode;
    }

    @Override
    public ILinkVertices InteriorVertices() {

        if (this.linkVertices == null) {
            this.linkVertices = new LinkVertices(this);
        }
        return this.linkVertices;
    }

    @Override
    public ILinkNetworks Networks() {

        if (this.networks == null) {
            this.networks = new LinkNetworks(this);
        }
        return this.networks;
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObject,
                                                        MayApplication application) {
        return new LinkRunSpace(sourceIntelligentObject, application, this);
    }

    @Override
    public void RemoveExistingRunSpace(IntelligentObjectRunSpace intelligentObject) {

    }

    @Override
    public Object PropertyUpdated(IntelligentObjectProperty intelligentObjectProperty, boolean error,
                                  Object param2) {
        return null;
    }

    public void calculateDistance() {
        this.distance = this.getDistance(2);
        super.getSizeStateGridItemProperties().setLength(this.distance);
    }

    private double getDistance(int fractional) {
        this.locationsCount = 1;
        if (this.Locations != null && this.Locations.length > 0) {
            this.locationsCount += this.Locations.length;
        }
        this.distances = new double[this.locationsCount];
        this.Directions = new Direction[this.locationsCount];
        double resultDistance;
        if (this.locationsCount == 1) {
            Direction direction = this.endNode.location.sub(this.beginNode.location);
            double distance = direction.distance();
            this.distances[0] = distance;
            resultDistance = distance;
            if (distance > 0.0) {
                this.Directions[0] = direction.divide(distance);
            }
        } else {
            Direction direction = this.Locations[0].sub(this.beginNode.location);
            double distance = direction.distance();
            this.distances[0] = distance;
            resultDistance = distance;
            if (distance > 0.0) {
                this.Directions[0] = direction.divide(distance);
            }

            int lastIndex = this.Locations.length - 1;
            direction = this.endNode.location.sub(this.Locations[lastIndex]);
            distance = direction.distance();
            resultDistance += distance;
            if (distance > 0.0) {
                this.Directions[this.Locations.length] = direction.divide(distance);
            }

            if (lastIndex > 0) {
                for (int i = 0; i < lastIndex; i++) {
                    direction = this.Locations[i + 1].sub(this.Locations[i]);
                    distance = direction.distance();
                    resultDistance += distance;
                    this.distances[i + 1] = this.distances[i] + distance;
                    if (distance > 0.0) {
                        this.Directions[i + 1] = direction.divide(distance);
                    }
                }
            }
        }

        var d = Math.pow(10, fractional);
        resultDistance = Math.round(resultDistance * d) / d;
        if (this.locationsCount > 0) {
            this.distances[this.locationsCount - 1] = resultDistance;
        }

        double num4 = this.getDrawnToScale().getExpressionResult(null, null).toDouble();
        if (num4 == 0.0) {
            double num5 = this.getLogicalLength().getExpressionResult(null, null).toDouble();
            double num6 = num5 / resultDistance;
            for (int j = 0; j < this.locationsCount; j++) {
                this.distances[j] *= num6;
            }
            return num5;
        }
        return resultDistance;
    }

    private static class LinkVertices implements ILinkVertices {

        private final Link link;

        LinkVertices(Link link) {
            this.link = link;
        }

        @Override
        public void setAt(int index, FacilityLocation vertex) {

        }

        @Override
        public void insertAt(int index, FacilityLocation vertex) {

        }

        @Override
        public void insertRange(int index, Iterable<FacilityLocation> vertex) {

        }

        @Override
        public int Count() {
            return 0;
        }

        @Override
        public FacilityLocation getByIndex(int index) {
            return null;
        }

        @Override
        public Iterator<FacilityLocation> iterator() {
            return null;
        }
    }

    private static class LinkNetworks implements ILinkNetworks {

        private final Link link;

        LinkNetworks(Link link) {
            this.link = link;
        }

        @Override
        public int size() {
            return 0;
        }

        @Override
        public boolean isEmpty() {
            return false;
        }

        @Override
        public boolean contains(Object o) {
            return false;
        }

        @Override
        public Iterator<INetworkElementObject> iterator() {
            return null;
        }

        @Override
        public Object[] toArray() {
            return new Object[0];
        }

        @Override
        public <T> T[] toArray(T[] a) {
            return null;
        }

        @Override
        public boolean add(INetworkElementObject iNetworkElementObject) {
            return false;
        }

        @Override
        public boolean remove(Object o) {
            return false;
        }

        @Override
        public boolean containsAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean addAll(Collection<? extends INetworkElementObject> c) {
            return false;
        }

        @Override
        public boolean removeAll(Collection<?> c) {
            return false;
        }

        @Override
        public boolean retainAll(Collection<?> c) {
            return false;
        }

        @Override
        public void clear() {

        }
    }

}
