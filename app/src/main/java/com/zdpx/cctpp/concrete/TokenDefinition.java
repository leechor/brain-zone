package com.zdpx.cctpp.concrete;

import com.google.common.base.Strings;
import com.zdpx.cctpp.annotations.ElementFunctionReferenceReturnType;
import com.zdpx.cctpp.annotations.UnitClass;
import com.zdpx.cctpp.concrete.annotation.BaseElementFunction;
import com.zdpx.cctpp.concrete.annotation.ElementFunction;
import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.AbsBaseRunSpace;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.resource.Image;
import com.zdpx.cctpp.simioEnums.ElementScope;
import com.zdpx.cctpp.utils.simu.IListData;
import com.zdpx.cctpp.utils.simu.system.IBindingList;

import java.util.Objects;

/**
 *
 */
public class TokenDefinition extends AbsDefinition implements INotifyPropertyChanged, IOwner, ISearch, IItemDescriptor,
        IListData {
    private IntelligentObjectDefinition parent;
    private Guid guid;
    public static final String name = "Token";
    public static final Guid DefaultGuid = new Guid("08106CA5-0813-4514-AD74-104AAA9F3088");

    public static TokenDefinition Instance = new TokenDefinition(TokenDefinition.name, TokenDefinition.DefaultGuid,
            null);

    private TokenDefinition(String name, Guid guid, IntelligentObjectDefinition parent) {
        super(name);
        this.guid = guid;
        this.parent = parent;
        BaseStatePropertyObject superStateProperty = new BaseStatePropertyObject("ReturnValue", false, false);
        superStateProperty.value = 0.0;
        superStateProperty.Description(EngineResources.Token_ReturnValueState_Description);
        super.getStateDefinitions().addStateProperty(superStateProperty);
    }

    public static TokenDefinition readXmlToken(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                               IntelligentObjectDefinition intelligentObjectDefinition) {
        TokenDefinition tokenDefinition = null;
        if (Objects.equals(xmlReader.Name(), "Token")) {
            tokenDefinition = new TokenDefinition(null, null, null);
            intelligentObjectDefinition.getTokens().add(tokenDefinition);
            tokenDefinition.readXml(xmlReader, intelligentObjectXml, intelligentObjectDefinition);
        }
        return tokenDefinition;
    }

    public static TokenDefinition createTokenDefinition(String name,
                                                        IntelligentObjectDefinition intelligentObjectDefinition) {
        return new TokenDefinition(name, Guid.NewGuid(), intelligentObjectDefinition);
    }


    private void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                         IntelligentObjectDefinition intelligentObjectDefinition) {
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Token", (XmlReader attr) ->
        {
            String attribute = attr.GetAttribute("Name");
            if (!Strings.isNullOrEmpty(attribute) && !StringHelper.equalsLocal(this.Name(), attribute)) {
                if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Two && this.parent != null) {
                    this.Name(this.parent.GetUniqueName(attribute, false));
                    return;
                }
                this.Name(attribute);
            }
        }, (XmlReader body) -> this.getStateDefinitions().readXml(xmlReader, intelligentObjectXml));
    }


    @Override
    public boolean IsJustAFactory() {
        return true;
    }

    @Override
    public Class<?> ElementType() {
        return null;
    }

    @Override
    public Class<?> RunSpaceType() {
        return null;
    }

    public Guid getGuid() {
        return this.guid;
    }

    public IntelligentObjectDefinition Parent() {
        return this.parent;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Token(this, name, ElementScope.Private);
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return TokenDefinition.Instance;
    }

    @Override
    public Object Item() {
        return this;
    }

    @Override
    public String Group() {
        return "Tokens";
    }

    @Override
    public int GroupImportance() {
        return 0;
    }

    @Override
    public String DisplayName() {
        return this.Name();
    }

    @Override
    public String ObjectType() {
        return "Token";
    }

    @Override
    public String Category() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 6;
    }

    @Override
    public int StateIconIndex() {
        return -1;
    }

    @Override
    public Image Icon() {
        return null;
    }

    @Override
    public void Rename(String newName) {
        this.Name(name);
    }

    @Override
    public boolean CanRenameTo(String newName, StringBuffer failureReason) {
        return NameValid.isValid(newName, failureReason) &&
                (this.Name().equals(newName) || this.parent.IsValidIdentifier(newName, failureReason));
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

    @Override
    public IBindingList ListData() {
        return super.getStateDefinitions().StateProperties;
    }

    @Override
    public boolean getIsReadOnly() {
        return false;
    }

    @UnitClass(UnitType.Time)
    @BaseElementFunction("TimeCreated")
    public static double smethod_7(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        if (runSpace instanceof TokenRunSpace) {
            TokenRunSpace associateObject = (TokenRunSpace) runSpace;
            return associateObject.getTimeNow();
        }
        return 0.0;
    }

    // Token: 0x0600558C RID: 21900 RVA: 0x001AC9A4 File Offset: 0x001AABA4
    @BaseElementFunction("TimeInProcess")
    @UnitClass(UnitType.Time)
    public static double TimeInProcess(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        if (runSpace instanceof TokenRunSpace) {
            TokenRunSpace associateObject = (TokenRunSpace) runSpace;
            return absBaseRunSpace.getSomeRun().TimeNow() - associateObject.getTimeNow();
        }
        return 0.0;
    }

    // Token: 0x0600558D RID: 21901 RVA: 0x001AC9DC File Offset: 0x001AABDC
    @BaseElementFunction("AssociatedObject")
    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    public static ExpressionValue smethod_9(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        if (runSpace instanceof TokenRunSpace) {
            TokenRunSpace associateObject = (TokenRunSpace) runSpace;
            return ExpressionValue.from(associateObject.AssociatedObjectRunSpace());
        }
        return null;
    }

    // Token: 0x0600558E RID: 21902 RVA: 0x001ACA0C File Offset: 0x001AAC0C
    @ElementFunction("TableRow")
    public static double smethod_10(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        if (runSpace instanceof TokenRunSpace) {
            TokenRunSpace associateObject = (TokenRunSpace) runSpace;
            return (double) (associateObject.tableRowReferences.getTableRow() + 1);
        }
        return 0.0;
    }

    // Token: 0x0600558F RID: 21903 RVA: 0x001ACA40 File Offset: 0x001AAC40
    @ElementFunction("TableRowCount")
    public static double smethod_11(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        if (runSpace instanceof TokenRunSpace) {
            TokenRunSpace associateObject = (TokenRunSpace) runSpace;
            return associateObject.tableRowReferences.getTableRowCount();
        }
        return 0.0;
    }

    // Token: 0x06005590 RID: 21904 RVA: 0x001ACA74 File Offset: 0x001AAC74
    @ElementFunction("HasAssociatedObject")
    public static double smethod_12(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        if (runSpace instanceof TokenRunSpace) {
            TokenRunSpace associateObject = (TokenRunSpace) runSpace;
            if (associateObject.AssociatedObjectRunSpace() != null) {
                return 1.0;
            }
        }
        return 0.0;
    }

    @Override
    public boolean IsOwnedBy(GridObjectDefinition parent) {
        return this.parent == parent;
    }

    public void initProperties() {
        if (super.getPropertyDefinitions().get(0).getClass() == ExpressionPropertyDefinition.class) {
            super.getPropertyDefinitions().Insert(0, super.createReportStatistics());
            super.getPropertyDefinitions().Remove(1);
            for (AbsPropertyObject absPropertyObject : super.getAssociatedInstances()) {
                Token token = (Token) absPropertyObject;
                token.properties.Insert(0,
                        super.getPropertyDefinitions().get(0).CreateInstance(token.properties));
                token.properties.Remove(1);
            }
        }
    }
}
