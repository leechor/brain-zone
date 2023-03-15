package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.node.Node;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.CapacityType;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.PropertyGroupClass;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.api.FacilityLocation;
import org.licho.brain.api.FacilitySize;
import org.licho.brain.api.IIntelligentObject;
import org.licho.brain.utils.simu.ICaption;
import org.licho.brain.utils.simu.IField;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * A base object with the optional ability to be seized, released, and follow an availability schedule.
 * <p></p>
 * The <b>Intelligent objects</b> are built by modelers and then may be reused in multiple modeling projects.
 * Objects can be stored in libraries and easily shared. A beginning modeler may prefer to use pre-built objects
 * from libraries; however the system is designed to make it easy for even beginning modelers to build their own
 * intelligent objects for use in building hierarchical models. Standard Library objects are all derived
 * Intelligent Objects.
 */

public class IntelligentObject extends AbsIntelligentPropertyObject implements IIntelligentObject, IField, ICaption {

    private static final String DEFINITION_NAME = "DefinitionName";
    public List<Node> nodes;
    private boolean setSizing;

    protected Location location = new Location();
    private ElementPropertyRow parentCostCenter;
    private ExpressionPropertyRow initialCost;
    private ExpressionPropertyRow initialCostRate;
    private ExpressionPropertyRow resourceIdleCostRate;
    private ExpressionPropertyRow resourceCostPerUse;
    private ExpressionPropertyRow resourceUsageCostRate;
    private ExpressionPropertyRow displayName;
    private ExpressionPropertyRow initialCapacity;
    private ExpressionPropertyRow rankingExpression;
    private BooleanPropertyRow logResourceUsage;
    private IntelligentObjectProperty displayCategory;
    private ColorPropertyRow displayColor;
    private EnumPropertyRow capacityType;
    private SchedulePropertyRow workSchedule;
    private RepeatStringPropertyRow workDayExceptions;
    private RepeatStringPropertyRow workPeriodExceptions;
    private EnumPropertyRow rankingRule;
    private SelectionRulePropertyRow dynamicSelectionRule;
    private StatePropertyRow currentSizeIndex;
    private EnumPropertyRow enumProperty;
    private List<Warning> suppressedWarnings;
    private List<Size> sizes;

    public IntelligentObject(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name, scope);
        this.nodes = new ArrayList<>();
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

    public ExpressionPropertyRow getResourceIdleCostRate() {
        return this.resourceIdleCostRate;
    }

    public ExpressionPropertyRow getResourceCostPerUse() {
        return this.resourceCostPerUse;
    }

    public ExpressionPropertyRow getResourceUsageCostRate() {
        return this.resourceUsageCostRate;
    }

    public BooleanPropertyRow getLogResourceUsage() {
        return this.logResourceUsage;
    }

    public ExpressionPropertyRow getDisplayName() {
        return this.displayName;
    }

    public IntelligentObjectProperty getDisplayCategory() {
        return this.displayCategory;
    }

    public ColorPropertyRow getDisplayColor() {
        return this.displayColor;
    }

    public EnumPropertyRow getCapacityType() {
        return this.capacityType;
    }

    public SchedulePropertyRow getWorkSchedule() {
        return this.workSchedule;
    }

    public RepeatStringPropertyRow getWorkDayExceptions() {
        return this.workDayExceptions;
    }

    public RepeatStringPropertyRow getWorkPeriodExceptions() {
        return this.workPeriodExceptions;
    }

    public ExpressionPropertyRow getInitialCapacity() {
        return this.initialCapacity;
    }

    public EnumPropertyRow getRankingRule() {
        return this.rankingRule;
    }

    public ExpressionPropertyRow getRankingExpression() {
        return this.rankingExpression;
    }

    public SelectionRulePropertyRow getDynamicSelectionRule() {
        return this.dynamicSelectionRule;
    }

    public StatePropertyRow getCurrentSizeIndex() {
        return this.currentSizeIndex;
    }


    public SizeStateGridItemPropertyObject getSizeStateGridItemProperties() {
        try {
            return (SizeStateGridItemPropertyObject) this.getGridItemPropertiesList().get(1);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new IntelligentObjectRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }

    void BindPropertyInstanceReferencesInit() {
        this.BindPropertyInstanceReferences(0);
    }

    @Override
    public Object PropertyUpdated(IntelligentObjectProperty intelligentObjectProperty, boolean param1,
                                  Object target) {
        if (super.Parent() != null && intelligentObjectProperty == this.logResourceUsage) {
            super.Parent().triggerPropertyUpdated(this);
        }
        return super.PropertyUpdated(intelligentObjectProperty, param1, target);
    }

    public CapacityType CapacityType() {
        return (CapacityType) this.getCapacityType().GetObjectValue();
    }

    public boolean isEqual() {
        return ((IntelligentObjectDefinition) this.assignerDefinition).IntelligentObject == this;
    }

    @Override
    public Properties GetPropertyGroupInstance(PropertyGroupClass propertyGroupClass) {
        if (propertyGroupClass == PropertyGroupClass.Main) {
            return this.properties;
        }
        return super.GetPropertyGroupInstance(propertyGroupClass);
    }

    @Override
    protected AbsDefinition DefaultDefinition() {
        return null;
    }

    public EnumPropertyRow getEnumPropertyRow() {
        return this.enumProperty;
    }

    public FacilitySize getFacilitySize() {
        Size size = this.getSizeStateGridItemProperties().getSize();
        return new FacilitySize(size.getLength(), size.getWidth(), size.getHeight());
    }

    public void setFacilitySize(FacilitySize size) {
        this.getSizeStateGridItemProperties().setSize(size.getLength(), size.getWidth(), size.getHeight());
    }

    public void propertyChanged(BaseStateIGridItemPropertyObject baseSomeIGridItemProperties) {
        if (baseSomeIGridItemProperties == this.getSizeStateGridItemProperties() && !this.setSizing) {
            this.HandleSizeChanged();
        }
    }

    protected void HandleSizeChanged() {
        if (super.getIntelligentObjectFacility() != null) {
            super.getIntelligentObjectFacility().propertyChanged(this);
        }
    }

    @Override
    public void LoadOldDefaultValuesForLoadFrom(IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() < 53) {
            for (IntelligentObjectProperty objectProperty : this.getProperties().values) {
                if (!(objectProperty.getStringPropertyDefinition() instanceof ElementPropertyDefinition definitionInfo)) {
                    continue;
                }
                if (definitionInfo.getType() == NetworkProperty.class
                        && Strings.isNullOrEmpty(definitionInfo.DefaultString())
                        || definitionInfo.DefaultString().equalsIgnoreCase("null")) {
                    objectProperty.StringValue("Entity.CurrentNetwork");
                }
            }
        }
    }

    @Override
    protected void InstanceNameChanged(String oldName, String newName) {
        super.InstanceNameChanged(oldName, newName);
        if (super.Parent() != null) {
            super.Parent().onNameChanged(this, oldName);
        }
        this.nodes.forEach((Node node) -> node.updateObjectName(oldName));
    }

    public String getDisplayNameAndBaseName() {
        return this.getDisplayName().getStringValue() != null ? this.getDisplayName().getStringValue() : super.Name();
    }

    @Override
    protected boolean IsDerivedName() {
        return !this.CanChangeName();
    }

    public boolean CanChangeName() {
        return true;
    }

    public void setLocation(Location location) {
        this.InitializeFacilityLocation(location.x, location.y, location.z);
    }

    @Override
    public boolean CanRenameTo(String newName, StringBuffer failureReason) {
        return false;
    }

    @Override
    public String getCaptionFor(String name) {
        var tmp = this.assignerDefinition.getPropertyDefinitions().findStringPropertyDefinitionInfoByName(name);
        if (tmp instanceof RepeatingPropertyDefinition stringPropertyDefinitionInfoByName) {
            IntelligentObjectProperty intelligentObjectProperty =
                    this.properties.get(stringPropertyDefinitionInfoByName.overRidePropertyIndex);
            if (intelligentObjectProperty != null && intelligentObjectProperty.IsAReference()) {
                return intelligentObjectProperty.StringValue();
            }
        }
        return null;
    }

    @Override
    public boolean FieldIsEditable(String name) {
        return false;
    }

    @Override
    public String getTypeName() {
        return null;
    }

    @Override
    public FacilityLocation Location() {
        return new FacilityLocation(this.location.getX(), this.location.getY(), this.location.getZ());
    }

    @Override
    public void Location(FacilityLocation location) {
        this.setLocalLocation(new Location(location.getX(), location.getY(), location.getZ()));
    }

    @Override
    public FacilitySize getSize() {
        Size size = this.getSizeStateGridItemProperties().getSize();
        return new FacilitySize(size.getLength(), size.getLength(), size.getLength());
    }

    @Override
    public void setSize(FacilitySize size) {
        this.getSizeStateGridItemProperties().setSize(size.getLength(), size.getWidth(), size.getHeight());
    }

    protected void WriteAttributesToXml(XmlWriter xmlWriter, CommonItems commonItems) {
    }


    protected void WriteBodyToXml(XmlWriter param0, CommonItems param1) {
    }

    protected boolean getTrackReadInObjectRenames() {
        return true;
    }

    @Override
    protected void ReadAttributesFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                         Map<String, String> attributes) {
        SomeXmlOperator.readXmlLocationAttribute(xmlReader, "Location", t -> this.location = t);
    }

    @Override
    protected boolean ReadBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return super.ReadBodyFromXml(xmlReader, intelligentObjectXml) ||
                this.readXml(xmlReader, intelligentObjectXml) ||
                SomeXmlOperator.xmlReaderElementAll(xmlReader, "IndexedSizes", null,
                        t -> {
                            this.sizes = new ArrayList<>();
                            return null;
                        },
                        t -> SomeXmlOperator.xmlReaderElementOperator(t, "IndexedSize",
                                x -> {
                                    String attribute = x.GetAttribute("Size");
                                    if (!Strings.isNullOrEmpty(attribute)) {
                                        this.sizes.add(Size.valueOf(attribute));
                                    }
                                }, null), null) ||
                intelligentObjectXml.readXml(xmlReader, this);
    }

    @Override
    protected void readFromXmlDone(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                   Map<String, String> savedAttributes) {
        String size = savedAttributes.get("SIZE");
        if (!Strings.isNullOrEmpty(size)) {
            String[] array = size.split(" ");
            if (array.length == 3) {
                double length = Double.parseDouble(array[0]);
                double width = Double.parseDouble(array[1]);
                double height = Double.parseDouble(array[2]);

                this.getSizeStateGridItemProperties().setSize(length, width, height);
            }
        }
        super.ReadFromXmlDone(xmlReader, intelligentObjectXml, savedAttributes);
    }

    public static IntelligentObject readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                            IntelligentObjectDefinition intelligentObjectDefinition) {
        IntelligentObject intelligentObject = IntelligentObject.readXmlTypeAttribute(xmlReader, intelligentObjectXml,
                intelligentObjectDefinition);
        String name = xmlReader.Name();
        if (intelligentObject != null) {
            intelligentObject.readXmlAttribute(xmlReader, intelligentObjectXml);
            return intelligentObject;
        }
        intelligentObjectXml.addWarning(MessageFormat.format(EngineResources.LoadWarning_CouldNotLoadObjectType, name));
        return null;
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.parentCostCenter = (ElementPropertyRow) this.properties.get(index++);
        this.initialCost = (ExpressionPropertyRow) this.properties.get(index++);
        this.initialCostRate = (ExpressionPropertyRow) this.properties.get(index++);
        this.resourceIdleCostRate = (ExpressionPropertyRow) this.properties.get(index++);
        this.resourceCostPerUse = (ExpressionPropertyRow) this.properties.get(index++);
        this.resourceUsageCostRate = (ExpressionPropertyRow) this.properties.get(index++);
        this.logResourceUsage = (BooleanPropertyRow) this.properties.get(index++);
        this.displayName = (ExpressionPropertyRow) this.properties.get(index++);
        this.displayCategory = this.properties.get(index++);
        this.displayColor = (ColorPropertyRow) this.properties.get(index++);
        this.capacityType = (EnumPropertyRow) this.properties.get(index++);
        this.workSchedule = (SchedulePropertyRow) this.properties.get(index++);
        this.workDayExceptions = (RepeatStringPropertyRow) this.properties.get(index++);
        this.workPeriodExceptions = (RepeatStringPropertyRow) this.properties.get(index++);
        this.initialCapacity = (ExpressionPropertyRow) this.properties.get(index++);
        this.rankingRule = (EnumPropertyRow) this.properties.get(index++);
        this.rankingExpression = (ExpressionPropertyRow) this.properties.get(index++);
        this.dynamicSelectionRule = (SelectionRulePropertyRow) this.properties.get(index++);
        this.currentSizeIndex = (StatePropertyRow) this.properties.get(index++);
        return index;
    }

    protected boolean ShowSizeListIndex() {
        return true;
    }

    protected boolean ShowSizeProperty() {
        return true;
    }

    protected boolean ShowPositionProperty() {
        return true;
    }

    @Override
    public int IconIndex() {
        return -1;
    }

    @Override
    public String GetNameForKey(Object target) {
        if (target == IntelligentObject.DEFINITION_NAME) {
            return "DefinitionName";
        }
        return super.GetNameForKey(target);
    }

    @Override
    public String GetDisplayNameForKey(Object target) {
        if (target == IntelligentObject.DEFINITION_NAME) {
            return "Definition Name";
        }
        return super.GetDisplayNameForKey(target);
    }

    @Override
    public String SearchableValueFor(Object target) {
        if (target == IntelligentObject.DEFINITION_NAME) {
            return super.ObjectType();
        }
        return super.SearchableValueFor(target);
    }

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {
        super.SubmitToSearch(itemEditPolicy, activeModel);
        itemEditPolicy.method_0(this, IntelligentObject.DEFINITION_NAME, activeModel);
    }

    public boolean sameGuid(IntelligentObject intelligentObject) {
        return ((IntelligentObjectDefinition) this.assignerDefinition).sameGuid((IntelligentObjectDefinition) intelligentObject.assignerDefinition);
    }

    public List<Size> getSizes() {
        if (this.sizes == null) {
            this.sizes = new ArrayList<>();
        }
        return this.sizes;
    }

    public Size createSize() {
        return new Size(this.getSizeStateGridItemProperties().getLength(),
                this.getSizeStateGridItemProperties().getWidth(), this.getSizeStateGridItemProperties().getHeight());
    }


    public void setParentCostCenter(ElementPropertyRow parentCostCenter) {
        this.parentCostCenter = parentCostCenter;
    }

    public void setLocalLocation(Location location) {
        this.InitializeFacilityLocation(location.getX(), location.getY(), location.getZ());
    }

    public void InitializeFacilityLocation(double x, double y, double z) {

    }

    private String getLocationString() {
        return MessageFormat.format(EngineResources.LocationValueFormat, this.location.x, this.location.y,
                this.location.z);
    }

    public void updateDefinition(IntelligentObjectDefinition intelligentObjectDefinition) {
        // TODO: 2021/11/30
    }

    public void addWarning(Warning warning) {
        if (this.suppressedWarnings == null) {
            this.suppressedWarnings = new ArrayList<>();
        }

        this.suppressedWarnings.add(warning.clone());
        ActiveModel activeModel = ((IntelligentObjectDefinition) this.assignerDefinition).activeModel;
        if (activeModel != null && activeModel.parentProjectDefinition != null) {
            activeModel.parentProjectDefinition.inited();
        }
    }

    boolean containsWarning(Warning warning) {
        return this.suppressedWarnings != null && this.suppressedWarnings.contains(warning);
    }

    void bindWarnings(List<Warning> returnedWarnings) {
        if (this.suppressedWarnings == null) {
            return;
        }
        for (Warning warning : this.suppressedWarnings) {
            Warning warning2 = warning.clone();
            warning2.bindToInstance(this.InstanceName());
            returnedWarnings.add(warning2);
        }
    }

    void clearWarnings() {
        this.suppressedWarnings = null;
    }

    public static IntelligentObject readXmlTypeAttribute(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                                         IntelligentObjectDefinition intelligentObjectDefinition) {

        String name = xmlReader.Name();
        ObjectClass objectClass = null;

        try {
            objectClass = ObjectClass.valueOf(name);
        } catch (IllegalArgumentException ignored) {
        }
        if (objectClass != null) {
            String type = xmlReader.GetAttribute("Type");
            if (!Strings.isNullOrEmpty(type)) {
                IntelligentObjectDefinition definition =
                        intelligentObjectXml.readIntelligentObjectDefinitionByName(type,
                                intelligentObjectDefinition.activeModel, null, false, false);
                if (definition != null) {
                    LinkDefinition linkDefinition = (LinkDefinition) definition;
                    if (linkDefinition == null) {
                        if (intelligentObjectXml.getMultiFile()) {
                            intelligentObjectDefinition.getInternalReference().updateSameIntelligentObjectDefinition(definition);
                        }
                        return intelligentObjectDefinition.createIntelligentObject(definition,
                                intelligentObjectDefinition.GetUniqueName("Object"), ElementScope.Private,
                                IntelligentObjectDefinition.Enum5.One);
                    }
                    if (intelligentObjectXml.method_17() && intelligentObjectXml.haveOutXml()) {
                        intelligentObjectXml.method_13();
                        return null;
                    }
                    Link link = Link.readXml(xmlReader, intelligentObjectXml, linkDefinition,
                            intelligentObjectDefinition);
                    if (link != null) {
                        link.InstanceName(intelligentObjectDefinition.GetUniqueName("Object"));
                    }
                    return link;
                }
            }
        }
        return null;
    }

    private boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        if (xmlReader.Name() == "AssociatedNodes") {
            String s = xmlReader.ReadOuterXml();
            Map<String, NodeDefinition> nameToDefs = new HashMap<>();
            try (StringReader stringReader = new StringReader(s)) {
                try (XmlReader reader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
                    reader.MoveToContent();
                    SomeXmlOperator.xmlReaderElementOperator(reader, "AssociatedNodes", null,
                            (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(body, "Node",
                                    (XmlReader nodeattr) -> {
                                        String attribute = nodeattr.GetAttribute("Name");
                                        if (attribute != null) {
                                            String[] array = Node.nameArray(attribute);
                                            String attribute2 = nodeattr.GetAttribute("Type");
                                            nameToDefs.put(array[0],
                                                    (NodeDefinition) intelligentObjectXml.readIntelligentObjectDefinitionByName(attribute2,
                                                            ((IntelligentObjectDefinition) this.assignerDefinition).activeModel, null,
                                                            false, false));
                                        }
                                    }, null));
                }
            }
            IntelligentObjectDefinition intelligentObjectDefinition =
                    (IntelligentObjectDefinition) this.assignerDefinition;
            for (NodeClassProperty nodeClassProperty : intelligentObjectDefinition.TransferPoints()) {
                NodeDefinition nodeDefinition = nameToDefs.get(nodeClassProperty.InstanceName());
                super.Parent().setNodeLocation(nodeClassProperty, -1, this, nodeDefinition, ElementScope.Private);
            }
            try (StringReader stringReader = new StringReader(s)) {
                try (XmlReader innerReader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
                    innerReader.MoveToContent();
                    SomeXmlOperator.xmlReaderElementOperator(innerReader, "AssociatedNodes", null, (XmlReader body) ->
                    {
                        boolean result = false;
                        String name = innerReader.GetAttribute("Name");
                        if (name != null) {
                            String[] array = Node.nameArray(name);
                            for (Node node : this.nodes) {
                                String[] instanceName = Node.nameArray(node.InstanceName());
                                if (Objects.equals(instanceName[0], array[0])) {
                                    node.readXmlAttribute(innerReader, intelligentObjectXml);
                                    result = true;
                                }
                            }
                        }
                        return result;
                    });
                }
            }
            return true;
        }
        return false;
    }


    public void setSize(double length, double height, double width) {
        this.setSizing = true;
        this.getSizeStateGridItemProperties().setLength(length);
        this.getSizeStateGridItemProperties().setHeight(height);
        this.getSizeStateGridItemProperties().setWidth(width);
        this.HandleSizeChanged();
        this.setSizing = false;
    }
}
