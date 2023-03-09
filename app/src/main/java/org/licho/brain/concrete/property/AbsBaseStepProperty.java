package org.licho.brain.concrete.property;

import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.AbsBaseStepDefinition;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.AbsStepDefinition;
import org.licho.brain.concrete.ActiveModel;
import org.licho.brain.concrete.BooleanPropertyRow;
import org.licho.brain.concrete.Breakpoint;
import org.licho.brain.concrete.BreakpointWrapper;
import org.licho.brain.concrete.EntryPointPropertyRow;
import org.licho.brain.concrete.ExitPointPropertyRow;
import org.licho.brain.concrete.ExpressionAction;
import org.licho.brain.concrete.ExpressionCalculate;
import org.licho.brain.concrete.GridItemProperties;
import org.licho.brain.concrete.GridObjectDefinition;
import org.licho.brain.concrete.Guid;
import org.licho.brain.concrete.IExpression;
import org.licho.brain.concrete.IListener;
import org.licho.brain.concrete.IntelligentObjectDefinition;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.IntelligentObjectXml;
import org.licho.brain.concrete.MissingStepDefinition;
import org.licho.brain.concrete.ProcessProperty;
import org.licho.brain.concrete.Properties;
import org.licho.brain.concrete.ReportErrorWrapper;
import org.licho.brain.concrete.SomeXmlOperator;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.XmlSettings;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.UnitType;
import org.licho.brain.utils.simu.IBreakpoint;
import org.licho.brain.utils.simu.system.Color;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public abstract class AbsBaseStepProperty extends AbsPropertyObject implements IBreakpoint {
    public EntryPointPropertyRow EntryPointPropertyRow;
    public ExitPointPropertyRow PrimaryExitPointProperty;
    public ExitPointPropertyRow SecondExitPointProperty;
    public ExitPointPropertyRow ThirdExitPointProperty;
    public ProcessProperty ProcessProperty;
    public int index;

    private AbsBaseStepProperty.StepExclusionExpression stepExclusionExpression;
    private Breakpoint breakpoint;
    private BooleanPropertyRow booleanProperty;
    private Color color;

    public AbsBaseStepProperty(GridObjectDefinition definition, String name) {
        super(definition, name);
    }

    public abstract void execute(TokenRunSpace tokenRunSpace);

    protected void ReadAttributesFromXml(XmlReader xmlReader) {
    }

    ;


    protected String TypeName() {
        return this.objectDefinition.Name();
    }

    protected String getName() {
        return this.TypeName();
    }

    public static AbsBaseStepProperty readXmlStep(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                                  ProcessProperty processProperty,
                                                  List<ProcessProperty.StepConnInfo> stepConnInfo,
                                                  Action<Integer> assignAction) {
        if (Objects.equals(xmlReader.Name(), "Step")) {
            String outerXml = xmlReader.ReadOuterXml();
            int num = AbsBaseStepProperty.readXml(outerXml);
            try (StringReader stringReader = new StringReader(outerXml)) {
                try (XmlReader reader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
                    reader.MoveToContent();
                    int[] stepID = new int[]{-1};
                    SomeXmlOperator.readXmlIntAttribute(reader, "ID", (Integer int_0) ->
                            stepID[0] = int_0);
                    String type = reader.GetAttribute("Type");
                    String name = reader.GetAttribute("Name");
                    String[] stepReadableType = new String[]{type};
                    SomeXmlOperator.readXmlAttributeString(reader, "ReadableType", (String string_0) ->
                            stepReadableType[0] = string_0);
                    AbsStepDefinition absStepDefinition = AbsStepDefinition.getAbsStepDefinition(type);
                    if (absStepDefinition == null) {
                        Guid empty = Guid.TryParse(type);
                        if (empty != null) {
                            intelligentObjectXml.addGuidWarnings(MessageFormat.format(EngineResources.LoadWarning_CouldNotFindStep,
                                    stepReadableType[0]));
                        } else {
                            intelligentObjectXml.addWarning(MessageFormat.format(EngineResources.LoadWarning_CouldNotFindStep,
                                    stepReadableType[0]));
                        }
                        if (num == 2) {
                            absStepDefinition =
                                    AbsBaseStepDefinition.definition.Instance(MissingBranchedStepDefinition.class);
                        } else {
                            absStepDefinition = AbsBaseStepDefinition.definition.Instance(MissingStepDefinition.class);
                        }
                    }

                    AbsBaseStepProperty absBaseStepProperty =
                            processProperty.createAbsBaseStepProperty(absStepDefinition, name);
                    if (assignAction != null) {
                        assignAction(stepID[0]);
                    }
                    absBaseStepProperty.readXml(reader, intelligentObjectXml, stepConnInfo);
                    return absBaseStepProperty;
                }
            }
        }
        return null;
    }

    protected void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                           List<ProcessProperty.StepConnInfo> stepConnInfo) {
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Step", (XmlReader attr) -> {
            this.ReadAttributesFromXml(attr);
            if (intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Zero) {
                SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "Breakpoint", b -> {
                    if (b) {
                        this.SetBreakpoint();
                    }
                });
            }

            SomeXmlOperator.readXmlAttributeString(xmlReader, "Description", this::Description);
            SomeXmlOperator.readAttributeColorOperator(xmlReader, "Color", c -> this.color = c);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "ExclusionExpression", e ->
                    this.ExclusionExpression(this.GetPropertyValueFixup(intelligentObjectXml, e))
            );
        }, (XmlReader body) -> {
            if (this.ReadBodyFromXml(body, intelligentObjectXml)) {
                return true;
            }

            if ("Breakpoint".equals(body.Name()) && intelligentObjectXml.Mode() == IntelligentObjectXml.ModeType.Zero) {
                this.breakpoint = Breakpoint.readXmlBreakpoint(body, intelligentObjectXml, this);
                if (this.breakpoint != null) {
                    this.Parent().activeModel.getBreakpoints().add(this.breakpoint);
                }
                return true;
            }


            return SomeXmlOperator.xmlReaderElementOperator(body, "Exit", (XmlReader exitAttr) -> {
                String name = exitAttr.GetAttribute("Name");
                var exitType = AbsBaseStepProperty.LocalExitType.None;
                class Inner {
                    AbsBaseStepProperty.LocalExitType exitType;
                    int nextId;
                }
                Inner inner = new Inner();
                SomeXmlOperator.readEnumAttribute(exitAttr, "Type", t -> inner.exitType = t,
                        AbsBaseStepProperty.LocalExitType.class);
                inner.nextId = -1;
                SomeXmlOperator.readXmlIntAttribute(exitAttr, "NextID", t -> inner.nextId = t);
                if (inner.nextId == -1) {
                    ExitPointPropertyRow exitPointPropertyRow = null;
                    if (name != null) {
                        exitPointPropertyRow =
                                (ExitPointPropertyRow) this.properties.getIntelligentObjectProperty(name);
                    }

                    if (exitPointPropertyRow == null && inner.exitType != AbsBaseStepProperty.LocalExitType.None) {
                        Properties properties = this.properties;
                        exitPointPropertyRow = (ExitPointPropertyRow) properties.search(t -> {
                            ExitPointPropertyRow eppr = (ExitPointPropertyRow) t;
                            if (eppr != null) {
                                if (eppr == this.PrimaryExitPointProperty && inner.exitType == LocalExitType.Primary) {
                                    return true;
                                }

                                if (eppr == this.PrimaryExitPointProperty && inner.exitType == LocalExitType.Alternate) {
                                    return true;
                                }
                            }
                            return false;
                        });
                    }

                    if (exitPointPropertyRow != null) {
                        ProcessProperty.StepConnInfo sc = new ProcessProperty.StepConnInfo();
                        sc.ExitPointPropertyRow = exitPointPropertyRow;
                        sc.stepID = inner.nextId;
                        stepConnInfo.add(sc);
                    }
                }
            }, null);
        });
    }

    protected boolean ReadBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return super.readXmlProperties(xmlReader, intelligentObjectXml);
    }

    private String GetPropertyValueFixup(IntelligentObjectXml intelligentObjectXml, String exclusionExpression) {
        return super.Parent().GetPropertyValueFixup(intelligentObjectXml, exclusionExpression);
    }

    private static void assignAction(int stepID) {

    }

    private static int readXml(String outerXml) {
        int[] numexits = new int[]{1};
        try (StringReader stringReader = new StringReader(outerXml)) {
            try (XmlReader xmlReader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
                xmlReader.MoveToContent();
                SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Step", null,
                        (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(body, "Exit",
                                (XmlReader exitAttr) ->
                                        SomeXmlOperator.readXmlAttributeString(exitAttr, "Type", (String string_0) ->
                                        {
                                            if (Objects.equals(string_0, LocalExitType.Alternate.toString())) {
                                                numexits[0] = 2;
                                            }
                                        }), null));
            }
        }
        return numexits[0];
    }

    @Override
    public String getObjectClassName() {
        return null;
    }

    @Override
    public String getObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int index) {
        return new String[0];
    }

    @Override
    public String ObjectName() {
        return null;
    }

    @Override
    public void ObjectName(String objectName) {

    }

    public void SetBreakpoint() {
        if (this.breakpoint != null) {
            return;
        }
        this.breakpoint = new Breakpoint(this);
        if (super.Parent() != null && !super.Parent().activeModel.getBreakpoints().values.contains(this.breakpoint)) {
            super.Parent().activeModel.getBreakpoints().add(this.breakpoint);
        }
        this.ProcessProperty.method_56(this);
    }

    public BreakpointWrapper ClearBreakpoint() {
        if (this.breakpoint == null) {
            return null;
        }
        if (super.Parent() != null) {
            super.Parent().activeModel.getBreakpoints().values.remove(this.breakpoint);
        }
        BreakpointWrapper result = new BreakpointWrapper(this, this.breakpoint);
        this.breakpoint = null;
        this.ProcessProperty.method_56(this);
        return result;
    }

    public void RestoreBreakpoint(BreakpointWrapper breakpointWrapper) {
        if (this.breakpoint != null) {
            return;
        }
        if (breakpointWrapper.getIBreakpoint() != this) {
            return;
        }
        this.breakpoint = breakpointWrapper.Breakpoint();
        if (super.Parent() != null && !super.Parent().activeModel.getBreakpoints().values.contains(this.breakpoint)) {
            super.Parent().activeModel.getBreakpoints().add(this.breakpoint);
        }
        this.ProcessProperty.method_56(this);
    }

    public void OnBreakpointChanged() {
    }

    public Breakpoint Breakpoint() {
        return this.breakpoint;
    }

    public IListener createExpressionActionListener() {
        if (this.stepExclusionExpression == null) {
            return null;
        }
        return ExpressionAction.createExpressionActionListener(this.stepExclusionExpression.getExpression(),
                enum38 -> {
                    if (enum38 == Enum38.Zero || enum38 == Enum38.Two) {
                        this.ExclusionExpression(this.ExclusionExpression());
                    }
                    if (enum38 == Enum38.One) {
                        this.ExclusionExpression(this.ExclusionExpression());
                        super.propertyChanged(EngineResources.ExclusionExpressionProperty);
                    }
                }, () -> {
                    if (this.getExclusionExpressionError() != null) {
                        this.ExclusionExpression(this.ExclusionExpression());
                    }
                });

    }

    public String ExclusionExpression() {
        if (this.stepExclusionExpression != null) {
            return this.stepExclusionExpression.ExclusionExpression();
        }
        return "";
    }

    public void ExclusionExpression(String value) {
        if (Strings.isNullOrEmpty(value)) {
            this.stepExclusionExpression = null;
        } else {
            this.stepExclusionExpression = new StepExclusionExpression(super.Parent(), value);
        }

        if (this.ProcessProperty != null && this.ProcessProperty.Parent() != null) {
            this.ProcessProperty.Parent().resetTable(255);
            if (this.getExclusionExpressionError() != null) {
                this.ProcessProperty.Parent().PropertyChangeError(this, EngineResources.ExclusionExpressionProperty);
                return;
            }
            this.ProcessProperty.Parent().PropertyChange(this, EngineResources.ExclusionExpressionProperty);
        }
    }

    private String getExclusionExpressionError() {
        if (this.stepExclusionExpression != null) {
            return this.stepExclusionExpression.Error();
        }
        return null;
    }

    public String getNameDisplay() {
        return MessageFormat.format("[{0}] {1}", this.objectDefinition.Name(), this.InstanceName());
    }

    protected void initProperties() {
        this.EntryPointPropertyRow = (EntryPointPropertyRow) this.properties.values.get(0);
        this.PrimaryExitPointProperty = (ExitPointPropertyRow) this.properties.values.get(1);
        Properties properties = this.properties;
        this.booleanProperty = (BooleanPropertyRow) properties.search(t -> "Interruptible".equals(t.Name()));
    }

    public boolean vmethod_3() {
        return true;
    }


    public class StepExclusionExpression {
        private String exclusionExpression;
        private StringBuilder error = new StringBuilder();
        private IExpression expression;

        StepExclusionExpression(IntelligentObjectDefinition intelligentObjectDefinition, String exclusionExpression) {
            this.exclusionExpression = exclusionExpression;
            this.expression = ExpressionCalculate.createExpression(this.exclusionExpression,
                    intelligentObjectDefinition, false, false, true, this.error);
        }

        String ExclusionExpression() {
            return this.exclusionExpression;
        }

        String Error() {
            return this.error.toString();
        }

        IExpression getExpression() {
            return this.expression;
        }

        public double GetExpressionValue(IntelligentObjectRunSpace intelligentObjectRunSpace) {
            if (this.expression != null) {
                var result = this.expression.GetExpressionValue(null,
                        intelligentObjectRunSpace,
                        null,
                        UnitType.Unspecified,
                        0, intelligentObjectRunSpace.getMayApplication().UnitConversions(),
                        true,
                        new ReportErrorWrapper());
                return result.toDouble();
            }
            return 0.0;
        }
    }


    @Override
    public String BreakpointLocation() {
        if (this.ProcessProperty != null) {
            return MessageFormat.format("%s,%s", this.InstanceName(), this.ProcessProperty.InstanceName());
        }
        return this.InstanceName();
    }

    @Override
    public ActiveModel Model() {
        if (super.Parent() != null) {
            return super.Parent().activeModel;
        }
        return null;
    }

    protected enum Enum15 {
        Zero, One, Two, Three
    }

    private enum LocalExitType {
        None,
        Primary,
        Alternate
    }

     public enum Enum16 {

    }
}
