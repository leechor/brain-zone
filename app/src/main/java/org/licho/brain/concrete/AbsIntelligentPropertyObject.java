package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.Enum34;
import org.licho.brain.enu.UnitType;
import org.licho.brain.resource.Image;
import org.licho.brain.brainEnums.ElementScope;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;


public abstract class AbsIntelligentPropertyObject extends AbsPropertyObject implements IItemDescriptor {
    Logger logger = LoggerFactory.getLogger(AbsIntelligentPropertyObject.class);
    private static final String GROUP_NAME = MemberExpressionUtils.getMemberExpressionMemberName("Group");
    private static final String PUBLIC = "public";
    protected static String instanceName = "InstanceName";
    private static final String INSTANCE_NAME = "InstanceName";
    public boolean bool_0;
    public int index;
    public int processPropertyIndex;


    private ElementScope scope;
    public EventInfos EventInfos;
    private boolean autoCreated;
    public StateIGridItemPropertyObjectList StateIGridItemPropertyObjectList;


    private BooleanPropertyRow booleanPropertyRow;
    private ElementScope elementScope;

    private static final String MEMBER_EXPRESSION_NAME =
            MemberExpressionUtils.getMemberExpressionMemberName(AbsIntelligentPropertyObject::Group);


    protected AbsIntelligentPropertyObject(GridObjectDefinition definition, String name, ElementScope scope) {
        super(definition, name);
        this.bool_0 = false;
        this.scope = scope;
        this.StateIGridItemPropertyObjectList = new StateIGridItemPropertyObjectList(this);
        this.EventInfos = new EventInfos(this);
        this.BindPropertyInstanceReferences(0);
    }

    public abstract AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace, MayApplication application);

    protected AbsBaseRunSpace GetRunSpaceOutOfParent(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return intelligentObjectRunSpace.AbsBaseRunSpaces[this.processPropertyIndex];
    }

    public String Group() {
        if (this.AutoCreated()) {
            return String.format(EngineResources.AutoCreatedElementGroupLabel, super.DefinitionName());
        }
        return super.DefinitionName();
    }

    protected int BindPropertyInstanceReferences(int index) {
        try {
            this.booleanPropertyRow = (BooleanPropertyRow) this.getProperties().get(index++);
        } catch (Exception e) {
            logger.error("Error binding property");
        }
        return index;
    }


    public ElementScope Scope() {
        return this.elementScope;
    }

    public void Scope(ElementScope value) {
        this.elementScope = value;
        super.propertyChanged(PUBLIC);
    }

    @Override
    public void InstanceName(String name) {
        if (Objects.equals(INSTANCE_NAME, name)) {
            return;
        }

        String preInstanceName = this.InstanceName();
        super.InstanceName(name);
        this.propertyChanged(AbsIntelligentPropertyObject.INSTANCE_NAME);
        if (super.getIntelligentObjectFacility() != null) {
            super.getIntelligentObjectFacility().method_382(this, preInstanceName);
        }
    }

    protected abstract AbsDefinition DefaultDefinition();

    public ElementScope getScope() {
        return scope;
    }

    public void setScope(ElementScope scope) {
        this.scope = scope;
        this.propertyChanged(PUBLIC);
    }

    protected AbsBaseRunSpace getRunSpaceOutOfParent(
            IntelligentObjectRunSpace statisticsDataSourceIntelligentObject) {
        return statisticsDataSourceIntelligentObject.getAbsBaseStatisticsDataSources()[this.index];
    }

    public AbsBaseRunSpace GetRunSpaceRecursionOutOfParent(IntelligentObjectRunSpace intelligentObject) {
        if (intelligentObject == null) {
            return null;
        }

        if (intelligentObject.getAbsIntelligentObjectProperty() == this) {
            return intelligentObject;
        }

        if (intelligentObject.getAbsIntelligentObjectProperty().getObjectDefinition() instanceof IntelligentObjectDefinition) {
            IntelligentObjectDefinition intelligentObjectDefinition = (IntelligentObjectDefinition) intelligentObject
                    .getAbsIntelligentObjectProperty().getObjectDefinition();

            while (intelligentObjectDefinition.getRelation(this.Parent()) != IntelligentObjectDefinition.Relation.Same) {
                intelligentObject = intelligentObject.ParentObjectRunSpace();
                if (intelligentObject == null) {
                    return null;
                }
                intelligentObjectDefinition = (IntelligentObjectDefinition) intelligentObject
                        .getAbsIntelligentObjectProperty().getObjectDefinition();
            }
        }
        return this.getRunSpaceOutOfParent(intelligentObject);
    }


    public void RemoveExistingRunSpace(IntelligentObjectRunSpace intelligentObject) {
        AbsBaseRunSpace absBaseRunSpace = this.GetRunSpaceRecursionOutOfParent(intelligentObject);
        absBaseRunSpace.Clear(Enum34.V_1);
        int num = intelligentObject.getAbsBaseStatisticsDataSources().length - 1;

        if (num > 0) {
            List<AbsBaseRunSpace> absBaseRunSpaces = new ArrayList<>(num);
            for (AbsBaseRunSpace dataSource : intelligentObject.getAbsBaseStatisticsDataSources()) {
                if (dataSource != absBaseRunSpace) {
                    absBaseRunSpaces.add(dataSource);
                }
            }
            intelligentObject.setAbsBaseStatisticsDataSources(absBaseRunSpaces
                    .toArray(new AbsBaseRunSpace[0]));
        }

        intelligentObject.setAbsIntelligentObjectProperty(null);
    }

    public void AddNewRunSpace(IntelligentObjectRunSpace intelligentObject) {
        AbsBaseRunSpace absBaseRunSpace =
                this.CreateRunSpaceWithPopulation(intelligentObject, intelligentObject.getMayApplication());

        if (intelligentObject.getAbsBaseStatisticsDataSources() != null) {
            int num = intelligentObject.getAbsBaseStatisticsDataSources().length;
            AbsBaseRunSpace[] array = new AbsBaseRunSpace[num + 1];
            System.arraycopy(intelligentObject.getAbsBaseStatisticsDataSources(), 0, array, 0, num);
            array[num] = absBaseRunSpace;
            intelligentObject.setAbsBaseStatisticsDataSources(array);
        } else {
            intelligentObject.setAbsBaseStatisticsDataSources(new AbsBaseRunSpace[]{absBaseRunSpace});
        }
    }

    @Override
    public String GetGridObjectClassName() {
        return String.format(EngineResources.ELEMENT_INSTANCE_CLASS_NAME, this.objectDefinition.Name());
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    public Object Item() {
        return this;
    }

    public String Name() {
        return this.InstanceName();
    }


    public int GroupImportance() {
        return 0;
    }

    public String DisplayName() {
        return this.InstanceName();
    }

    public String ObjectType() {
        return this.getDefinitionName();
    }

    public String Category() {
        return null;
    }

    public abstract int IconIndex();

    public Image Icon() {
        return null;
    }

    public int StateIconIndex() {
        return 0;
    }

    public String getTypeName() {
        return this.ObjectType();
    }

    boolean method_17(IntelligentObjectRunSpace intelligentObject) {
        return false;
    }

    public void Rename(String name) {
        this.InstanceName(name);
    }

    public boolean CanRenameTo(String newName, StringBuffer failureReason) {
        StringBuffer error = new StringBuffer();
        return super.validIdentifierFormat(newName, error);
    }


    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int param0) {
        return null;
    }

    public void setBooleanPropertyStringValue(boolean value) {
        if (value) {
            this.booleanPropertyRow.StringValue("True");
        } else {
            this.booleanPropertyRow.StringValue("False");
        }
    }

    boolean stringValueIsFalse() {
        String stringValue = this.booleanPropertyRow.getStringValue();
        return stringValue != null && stringValue.equalsIgnoreCase("False");
    }

    public UnitType getUnitType() {
        return UnitType.Unspecified;
    }

    public Object notifyRemoved() {
        return null;
    }

    public void notifyRestored(Object o) {

    }


    @Override
    public GridObjectDefinition getObjectDefinition() {
        return objectDefinition;
    }

    public void setGridObjectDefinition(GridObjectDefinition gridObjectDefinition) {
        this.objectDefinition = gridObjectDefinition;
    }

    public void setAutoCreated(boolean autoCreated) {
        this.autoCreated = autoCreated;
        this.propertyChanged(AbsIntelligentPropertyObject.MEMBER_EXPRESSION_NAME);
    }

    @Override
    public final IntelligentObjectProperty GetPropertyForLoad(String s1, IntelligentObjectXml intelligentObjectXml) {
        StringPropertyDefinition propertyDefinitionInfoForLoad =
                ((AbsDefinition) this.objectDefinition).GetPropertyForLoad(s1, intelligentObjectXml);
        if (propertyDefinitionInfoForLoad == null) {
            return super.GetPropertyForLoad(s1, intelligentObjectXml);
        }

        if (propertyDefinitionInfoForLoad.getOwner() != null
                && propertyDefinitionInfoForLoad.getOwner().getRepeatingPropertyDefinition() != null) {
            RepeatStringPropertyRow repeatStringPropertyRow = (RepeatStringPropertyRow) this.properties.getValues()
                    .get(propertyDefinitionInfoForLoad.getOwner().getRepeatingPropertyDefinition().getOverRidePropertyIndex());
            
            return repeatStringPropertyRow.getPropertyDescriptors().getValues()
                    .get(repeatStringPropertyRow.getPropertyDescriptors().Count() - 1).getValues()
                    .get(propertyDefinitionInfoForLoad.getOverRidePropertyIndex());
        }
        return this.properties.getValues().get(propertyDefinitionInfoForLoad.getOverRidePropertyIndex());
    }

    protected String getXmlType() {
        return ((AbsDefinition) this.objectDefinition).Name();
    }

    protected String getReadableXmlType() {
        return this.getXmlType();
    }

    protected void WriteBodyToXml(XmlWriter xmlWriter) {

    }

    protected void WriteWholeBodyToXml(XmlWriter xmlWriter, CommonItems commonItems) {

    }

    protected void writeXmlProperties(XmlWriter xmlWriter, CommonItems commonItems) {
        this.properties.writeXmlProperties(xmlWriter, commonItems);
        this.WriteBodyToXml(xmlWriter);
    }

    public void writeXmlElement(XmlWriter xmlWriter, CommonItems commonItems) {

    }

    protected void readAttributesFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, Map<String,
            String> attributes) {

    }

    protected boolean ReadBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return false;
    }

    protected void readFromXmlDone(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, Map<String,
            String> attributes) {

    }

    protected boolean readWholeBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                           String Server1) {
        return this.properties.readXmlProperties(xmlReader, intelligentObjectXml) || this.ReadBodyFromXml(xmlReader,
                intelligentObjectXml);
    }

    protected boolean getTrackReadInObjectRenames() {
        return false;
    }

    protected boolean getSetInstanceNameOnPaste() {
        return true;
    }

    public void readXmlAttribute(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                 IntelligentObjectDefinition intelligentObjectDefinition) {

    }

    public static AbsIntelligentPropertyObject readXmlAbsIntelligentObjectProperty(XmlReader xmlReader,
                                                                                   IntelligentObjectXml intelligentObjectXml,
                                                                                   IntelligentObjectDefinition intelligentObjectDefinition) {
        return null;
    }

    @Override
    public Properties getProperties() {
        return this.properties;
    }

    @Override
    public void setProperties(Properties properties) {
        this.properties = properties;
    }

    public StateIGridItemPropertyObjectList getGridItemPropertiesList() {
        return StateIGridItemPropertyObjectList;
    }

    public void setGridItemPropertiesList(StateIGridItemPropertyObjectList stateIGridItemPropertyObjectList) {
        this.StateIGridItemPropertyObjectList = stateIGridItemPropertyObjectList;
    }

    public boolean AutoCreated() {
        return this.autoCreated;
    }

    public void AutoCreated(boolean value) {
        this.autoCreated = value;
        super.propertyChanged(AbsIntelligentPropertyObject.GROUP_NAME);
    }

    public void readXmlAttribute(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        Map<String, String> savedAttributes = new HashMap<>();
        String elementName = xmlReader.Name();
        SomeXmlOperator.xmlReaderElementAll(xmlReader, xmlReader.Name(), (XmlReader attr) -> {
                    Boolean flag = false;
                    String name = attr.GetAttribute("Name");
                    if (((intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.SetInstanceNameOnPaste()) ||
                            intelligentObjectXml.Mode() != IntelligentObjectXml.ModeType.Two) &&
                            !Strings.isNullOrEmpty(name) && !StringHelper.equalsLocal(name, this.InstanceName())) {
                        if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.Parent() != null) {
                            String uniqueName = this.Parent().GetUniqueName(name, false);
                            if (!Objects.equals(this.InstanceName(), uniqueName)) {
                                this.InstanceName(uniqueName);
                                flag = true;
                            }
                        } else if (!Objects.equals(this.InstanceName(), name)) {
                            this.InstanceName(name);
                            flag = true;
                        }
                    }
                    if (flag) {
                        this.properties.updateStringValue();
                    }
                    if (this.TrackReadInObjectRenames()) {
                        intelligentObjectXml.addNameInstanceNameMap(name, this.InstanceName());
                    }
                    SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "AutoCreated", this::AutoCreated);
                    String scope = attr.GetAttribute("Scope");
                    if (scope.equalsIgnoreCase("public")) {
                        this.Scope(ElementScope.Public);
                    }
                    String description = attr.GetAttribute("Description");
                    if (!Strings.isNullOrEmpty(description)) {
                        this.Description(description);
                    }
                    this.ReadAttributesFromXml(xmlReader, intelligentObjectXml, savedAttributes);

                }, null, (XmlReader body) -> this.ReadWholeBodyFromXml(body, intelligentObjectXml, elementName),
                (XmlReader bodyDone) -> this.ReadFromXmlDone(bodyDone, intelligentObjectXml, savedAttributes));
    }

    protected void ReadFromXmlDone(XmlReader bodydone, IntelligentObjectXml intelligentObjectXml,
                                   Map<String, String> savedAttributes) {

    }

    private boolean ReadWholeBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                         String elementName) {
        return this.properties.readXmlProperties(xmlReader, intelligentObjectXml) || this.ReadBodyFromXml(xmlReader,
                intelligentObjectXml);
    }

    protected void ReadAttributesFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                         Map<String, String> savedAttributes) {

    }

    private boolean TrackReadInObjectRenames() {
        return false;
    }

    protected boolean SetInstanceNameOnPaste() {
        return true;
    }

    public boolean ExpressionResultPosive(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return this.booleanPropertyRow.getExpressionResult(null, intelligentObjectRunSpace, null).toDouble() > 0.0;
    }
}
