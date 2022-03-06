package com.zdpx.cctpp.concrete;

import com.google.common.base.Strings;
import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.ExpressionResultType;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.resource.Image;

import java.util.Objects;

/**
 *
 */
public class ExpressionFunction implements INotifyPropertyChanged, IGridObject, IOwner, ISearch, IItemDescriptor {

    private IntelligentObjectDefinition parent;
    private String name;
    private UnitType unitType;
    private Boolean publicAttr;
    private String expression;
    private IExpression iExpression;
    private StringBuilder string_5;
    private String description;

    public static ExpressionFunction readXmlExpressionFunction(XmlReader xmlReader,
                                                               IntelligentObjectXml intelligentObjectXml,
                                                               IntelligentObjectDefinition parent) {
        if (Objects.equals(xmlReader.Name(), "ExpressionFunction")) {
            ExpressionFunction xmlExpressionFunction = new ExpressionFunction();
            parent.ExpressionFunctions().add(xmlExpressionFunction);
            xmlExpressionFunction.readXml(xmlReader, intelligentObjectXml);
            return xmlExpressionFunction;
        }
        return null;
    }

    private void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "ExpressionFunction", (XmlReader attr) -> {
            String[] _name = new String[1];
            SomeXmlOperator.readXmlAttributeString(attr, "Name", t -> _name[0] = t);
            if (!Strings.isNullOrEmpty(_name[0]) && !StringHelper.equalsLocal(this.Name(), _name[0])) {
                if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.Parent() != null) {
                    this.Name(this.Parent().GetUniqueName(_name[0], false));
                } else {
                    this.Name(_name[0]);
                }
            }

            SomeXmlOperator.readEnumAttribute(attr, "ReturnType", this::ReturnType, ExpressionResultType.class);
            SomeXmlOperator.readEnumAttribute(attr, "UnitType", this::UnitType, UnitType.class);
            SomeXmlOperator.readXmlBooleanAttribute(attr, "Public", this::Public);
        }, (XmlReader body) ->
                SomeXmlOperator.xmlReaderElementOperator(body, "Expression", null,
                        t -> SomeXmlOperator.readXMLText(xmlReader, this::Expression)) ||
                        SomeXmlOperator.xmlReaderElementOperator(body, "Description", null,
                                t -> SomeXmlOperator.readXMLText(t, this::Description)));

    }

    public String Description() {
        return this.description;
    }

    public void Description(String value) {
        this.description = value;
    }

    public String Expression() {
        if (this.iExpression != null) {
            return this.iExpression.toString();
        }
        return this.expression;
    }

    public void Expression(String value) {
        this.iExpression = null;
        this.string_5 = null;
        this.expression = value;
        this.iExpression = ExpressionCalculate.createExpression(this.expression, this.parent, true, true, true,
                this.string_5);
        if (this.iExpression == null) {
            this.parent.PropertyChangeError(this, EngineResources.ExpressionFunctionExpression);
            return;
        }
        if (this.iExpression != null) {
            this.parent.PropertyChange(this, EngineResources.ExpressionFunctionExpression);
        }

    }

    private void Public(Boolean value) {
        this.publicAttr = value;
        this.parent.updateExpressionFunctionRecursion(this);

    }

    public UnitType UnitType() {
        return this.unitType;
    }

    public void UnitType(UnitType value) {
        this.unitType = value;
    }

    private void ReturnType(ExpressionResultType value) {

    }


    public IntelligentObjectDefinition Parent() {
        return this.parent;
    }

    public void Parent(IntelligentObjectDefinition value) {
        this.parent = value;
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
        return null;
    }

    public void Name(String value) {
        if (Objects.equals(this.name, value)) {
            return;
        }
        String param = this.name;
        this.name = value;
        this.parent.updateObjectName(this, param);
        this.triggerPropertyChangedEventHandler("Name");

    }

    private void triggerPropertyChangedEventHandler(String name) {
        // TODO: 2022/1/5 send eventhandler
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

    @Override
    public void SubmitToSearch(ItemEditPolicy itemEditPolicy, ActiveModel activeModel) {

    }

    public IListener createExpressionActionListener() {
        return null;
    }

    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return this.parent == parent;
    }
}
