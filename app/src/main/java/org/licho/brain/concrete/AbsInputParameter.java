package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.UnitType;
import org.licho.brain.resource.Image;

import java.util.Objects;

/**
 *
 */
public abstract class AbsInputParameter implements INotifyPropertyChanged, IGridObject, IOwner, ISearch,
        IItemDescriptor {
    private final IntelligentObjectDefinition parent;
    private String name;
    private UnitType unitType;
    private int unitSubType;

    protected AbsInputParameter(IntelligentObjectDefinition parent) {
        this.parent = parent;
        this.name = this.parent.GetUniqueName("InputParameter");
//        this.method_6(-1);
    }

    public static AbsInputParameter readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                            IntelligentObjectDefinition parent) {
        ExpressionParameter expressionParameter = ExpressionParameter.readXmlExpressionParameter(xmlReader,
                intelligentObjectXml, parent);
        if (expressionParameter != null) {
            return expressionParameter;
        }
        return null;

    }

    @Override
    public String GetGridObjectClassName() {
        return null;
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
    public GridItemProperties GetGridPropertyItemList(GridItemProperties param0, GridObjectDefinition param1) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int param0) {
        return new String[0];
    }

    @Override
    public Object Item() {
        return null;
    }

    @Override
    public String Name() {
        return this.name;
    }

    public void Name(String value) {
        if (Objects.equals(this.name, value)) {
            return;
        }
        String param = this.name;
        this.name = value;
        this.parent.updateAbsInputParameter(this, param);
        this.triggerPropertyChangedEventName("Name");
    }

    private void triggerPropertyChangedEventName(String name) {
//        if (this.propertyChangedEventHandler_0 != null)
//        {
//            this.propertyChangedEventHandler_0(this, new PropertyChangedEventArgs(name));
//        }
    }

    @Override
    public String Group() {
        return null;
    }

    @Override
    public int GroupImportance() {
        return 0;
    }

    @Override
    public String DisplayName() {
        return null;
    }

    @Override
    public String ObjectType() {
        return null;
    }

    @Override
    public String Category() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 0;
    }

    @Override
    public int StateIconIndex() {
        return 0;
    }

    @Override
    public Image Icon() {
        return null;
    }

    @Override
    public void Rename(String newName) {

    }

    @Override
    public boolean CanRenameTo(String newName, StringBuffer failureReason) {
        return false;
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

    public abstract IListener ChildObjectChangeListener();

    protected void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, this.XmlElementName(), (XmlReader attr) ->
        {
            String[] _tmp = new String[1];
            SomeXmlOperator.readXmlAttributeString(attr, "Name", t -> _tmp[0] = t);
            if (!Strings.isNullOrEmpty(_tmp[0]) && !StringHelper.equalsLocal(this.Name(), _tmp[0])) {
                if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.Parent() != null) {
                    this.Name(this.Parent().GetUniqueName(_tmp[0], false));
                } else {
                    this.Name(_tmp[0]);
                }
            }
            SomeXmlOperator.readEnumAttribute(attr, "UnitType", this::UnitType, UnitType.class);
            String units = xmlReader.GetAttribute("Units");
            if (units != null) {
                this.UnitSubType(AboutUnit.getUnitLevel(this.UnitType(), units));
            }
            if (this.UnitSubType() == -1) {
                this.UnitSubType(0);
            }
        }, (XmlReader body) -> this.ReadXmlBody(xmlReader, intelligentObjectXml));
    }

    private boolean ReadXmlBody(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return false;
    }

    private int UnitSubType() {
        return this.unitSubType;
    }

    private void UnitSubType(int value) {
        this.unitSubType = value;
    }

    private UnitType UnitType() {
        return this.unitType;
    }

    private void UnitType(UnitType value) {
        this.unitType = value;
    }


    private IntelligentObjectDefinition Parent() {
        return this.parent;
    }

    protected abstract String XmlElementName();
}
