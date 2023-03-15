package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.api.enu.ExitType;
import org.licho.brain.utils.simu.IBreakpoint;

import java.io.StringReader;
import java.text.MessageFormat;
import java.util.List;
import java.util.Objects;

/**
 *
 */
public abstract class AbsStepProperty<T> extends AbsBaseStepProperty implements IBreakpoint {
    private AbsBaseStepProperty.StepExclusionExpression stepExclusionExpression;
    private Breakpoint breakpoint;

    public AbsStepProperty(Class<T> cl, String name) {
        super((GridObjectDefinition) AbsBaseStepDefinition.definition.Instance(cl), name);
    }

    protected Enum15 vmethod_5(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (this.stepExclusionExpression != null) {
            double num = this.stepExclusionExpression.GetExpressionValue(intelligentObjectRunSpace);
            if (num == 1.0 && this.PrimaryExitPointProperty.validProperty()) {
                return Enum15.One;
            }
            if (num == 2.0 && this.SecondExitPointProperty != null && this.SecondExitPointProperty.validProperty()) {
                return Enum15.Two;
            }
        }
        return Enum15.Zero;
    }

    @Override
    public Breakpoint Breakpoint() {
        return this.breakpoint;
    }

    @Override
    public String BreakpointLocation() {
        if (this.ProcessProperty != null) {
            return MessageFormat.format("{0}.{1}", this.ProcessProperty.InstanceName(), this.InstanceName());
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

    @Override
    protected String TypeName() {
        return this.assignerDefinition.Name();
    }

    @Override
    protected String getName() {
        return this.TypeName();
    }

    private static int readXml(String outXml) {
        int[] numexits = new int[]{1};
        try (StringReader stringReader = new StringReader(outXml)) {
            try (XmlReader xmlReader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
                xmlReader.MoveToContent();
                SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Step", null,
                        (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(body, "Exit",
                                (XmlReader exitattr) ->
                                        SomeXmlOperator.readXmlAttributeString(exitattr, "Type", (String string_0) -> {
                                            if (Objects.equals(string_0, ExitType.AlternateExit.toString())) {
                                                numexits[0] = 2;
                                            }
                                        }), null));
            }
        }
        return numexits[0];
    }

    @Override
    protected boolean ReadBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return super.readXmlProperties(xmlReader, intelligentObjectXml);
    }

    @Override
    protected void ReadAttributesFromXml(XmlReader xmlReader) {
    }

    protected void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                           List<ProcessProperty.StepConnInfo> stepConnInfo) {
//        SomeXmlOperator.xmlReaderElementOperator()
    }

}
