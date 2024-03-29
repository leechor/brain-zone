package org.licho.brain.concrete.entity;

import org.licho.brain.concrete.Agent;
import org.licho.brain.concrete.BooleanPropertyRow;
import org.licho.brain.concrete.ElementPropertyRow;
import org.licho.brain.concrete.ExpressionPropertyRow;
import org.licho.brain.concrete.FixedDefinition;
import org.licho.brain.concrete.GridItemProperties;
import org.licho.brain.concrete.GridItemProperty;
import org.licho.brain.concrete.GridObjectDefinition;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.IntelligentObjectXml;
import org.licho.brain.concrete.MayApplication;
import org.licho.brain.concrete.Population;
import org.licho.brain.concrete.PropertyOperator_0;
import org.licho.brain.concrete.SomeXmlOperator;
import org.licho.brain.concrete.SequencePropertyInTableRow;
import org.licho.brain.concrete.VolumeUnitGridItemPropertyObject;
import org.licho.brain.concrete.WeightUnitGridItemPropertyObject;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.enu.EntityType;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.PropertyGridFeel;
import org.licho.brain.brainEnums.ElementScope;

import java.util.Map;

/**
 * Entities are part of an object model and can have their own intelligent behavior. THey can make decisions,
 * reject request,decide to take a rest, etc. Entities have object definitions just like the other objects in the model.
 * Examples of Entity objects customers, parts or work pieces.
 * <p></p>
 * Adds behaviors for modeling objects that can follow a work flow in the system, including the ability to use a network
 * of links to move between objects, the ability to visit, enter, and exit locations within other objects through nodes,
 * and the ability to be picked up, carried, and dropped off by transporter objects. An Entity object
 * can be dynamically created and destroyed and can move into and out of Fixed objects, Entities can have multiple
 * graphical symbols, An example of an Entity model is the default ModelEntity that is automatically added with the
 * first
 * Fixed model of the Project.
 * <p>
 * When talking about the location type of an Entity, we are referring to the location of the Entity's leading edge.
 * </p>
 * <p></p>
 * Entity do not flow through processes, as Tokens do. which might execute a process. when the process is executed,
 * a Token is created that flows through the steps of a process.
 */
public class Entity extends Agent {

    /**
     * The entity object to be transferred. Specified as either the object associated with the executing
     * token, the parent object, or as a specific object reference.
     */
    private EntityType entityType;

    /**
     * the time that the entity was created in hours.
     */
    private Long timeCreated;

    private ElementPropertyRow initialNetwork;
    private EnumPropertyRow networkTurnaroundMethod;
    private ExpressionPropertyRow initialPriority;
    private SequencePropertyInTableRow initialSequence;
    private BooleanPropertyRow canTransferInAndOutOfObjects;
    private ExpressionPropertyRow dueDateExpression;
    private ExpressionPropertyRow ganttVisibilityExpression;
    private boolean haveVolumeDefaultValue;
    private double times = 1.0;


    public Entity(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
    }

    public ElementPropertyRow getInitialNetwork() {
        return this.initialNetwork;
    }

    public EnumPropertyRow getNetworkTurnaroundMethod() {
        return this.networkTurnaroundMethod;
    }

    public ExpressionPropertyRow getInitialPriority() {
        return this.initialPriority;
    }

    public SequencePropertyInTableRow getInitialSequence() {
        return this.initialSequence;
    }

    public BooleanPropertyRow getCanTransferInAndOutOfObjects() {
        return this.canTransferInAndOutOfObjects;
    }

    public ExpressionPropertyRow getDueDateExpression() {
        return this.dueDateExpression;
    }

    public ExpressionPropertyRow getGanttVisibilityExpression() {
        return this.ganttVisibilityExpression;
    }

    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.initialNetwork = (ElementPropertyRow) this.properties.get(index++);
        this.networkTurnaroundMethod = (EnumPropertyRow) this.properties.get(index++);
        this.initialPriority = (ExpressionPropertyRow) this.properties.get(index++);
        this.initialSequence = (SequencePropertyInTableRow) this.properties.get(index++);
        this.canTransferInAndOutOfObjects = (BooleanPropertyRow) this.properties.get(index++);
        this.dueDateExpression = (ExpressionPropertyRow) this.properties.get(index++);
        this.ganttVisibilityExpression = (ExpressionPropertyRow) this.properties.get(index++);
        return index;
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        EntityRunSpace runSpace = new EntityRunSpace(sourceIntelligentObjectRunSpace, application, this);
        runSpace.setPopulation(new Population(runSpace));
        return runSpace;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                  GridObjectDefinition gridObjectDefinition) {
        super.getPropertyItemList(gridItemProperties, gridObjectDefinition);

        GridItemProperty property =
                gridItemProperties.getGridItemPropertyByName(EngineResources.CategoryName_PhysicalCharacteristics);

        var volumeProperty = new GridItemProperty(EngineResources.VolumePropertyDisplayName, property,
                1000000 + this.getVolumeUnitGridItemProperties().StatePropertyObject.index,
                this.getVolumeUnitGridItemPropertiesInitialValue(), null, PropertyGridFeel.edit,
                EngineResources.VolumePropertyDescription,
                new PropertyOperator_0<>(Double.class, this::getVolumeUnitGridItemPropertiesInitialValue,
                        this::setVolumeUnitGridItemPropertiesInitialValue));
        volumeProperty.ComplexityLevel(ProductComplexityLevel.Advanced);
        gridItemProperties.add(volumeProperty);

        var weightDensityProperty = new GridItemProperty(EngineResources.WeightDensityPropertyDisplayName, property,
                1000000 + this.getWeightUnitGridItemProperties().StatePropertyObject.index, this.getTimes(), 1.0,
                PropertyGridFeel.edit, EngineResources.WeightDensityPropertyDescription,
                new PropertyOperator_0<>(Double.class, this::getTimes, this::setTimes));
        weightDensityProperty.ComplexityLevel((ProductComplexityLevel.Advanced));
        gridItemProperties.add(weightDensityProperty);
        return gridItemProperties;
    }

    @Override
    protected void HandleSizeChanged() {
        this.update();
        super.HandleSizeChanged();
    }

    public VolumeUnitGridItemPropertyObject getVolumeUnitGridItemProperties() {
        return (VolumeUnitGridItemPropertyObject) this.StateIGridItemPropertyObjectList.get(11);
    }

    public WeightUnitGridItemPropertyObject getWeightUnitGridItemProperties() {
        return (WeightUnitGridItemPropertyObject) this.StateIGridItemPropertyObjectList.get(12);
    }

    public Double getVolumeUnitGridItemPropertiesInitialValue() {
        if (this.haveVolumeDefaultValue) {
            return this.getVolumeUnitGridItemProperties().InitialValue();
        }
        return null;
    }

    public void setVolumeUnitGridItemPropertiesInitialValue(Double value) {
        if (value == null) {
            this.haveVolumeDefaultValue = false;
        } else {
            this.haveVolumeDefaultValue = true;
            this.getVolumeUnitGridItemProperties().InitialValue(value);
        }
        this.update();
    }

    private void update() {
        if (!this.haveVolumeDefaultValue) {
            this.getVolumeUnitGridItemProperties().InitialValue(super.getSizeStateGridItemProperties().getArea());
        }
        this.getWeightUnitGridItemProperties().InitialValue(this.getVolumeUnitGridItemProperties().InitialValue() * this.getTimes());
    }

    public double getTimes() {
        return this.times;
    }

    public void setTimes(double value) {
        this.times = value;
        this.update();
    }

    @Override
    protected void ReadAttributesFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, Map<String
            , String> savedAttributes) {
        super.ReadAttributesFromXml(xmlReader, intelligentObjectXml, savedAttributes);
        SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "DefaultEntity", (Boolean t) -> {
            if (t && super.Parent() instanceof FixedDefinition) {
                ((FixedDefinition) super.Parent()).DefaultEntity(this);
            }
        });
        SomeXmlOperator.readXmlDoubleAttribute(xmlReader, "Volume", this::setVolumeUnitGridItemPropertiesInitialValue);
        SomeXmlOperator.readXmlDoubleAttribute(xmlReader, "Density", this::setTimes);
    }


}
