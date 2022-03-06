package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.fake.XmlWriter;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.utils.simu.IBreakpoint;
import com.zdpx.cctpp.utils.simu.system.IDataErrorInfo;
import com.zdpx.cctpp.simuApi.containers.BreakpointExpressionEvaluation;
import com.zdpx.cctpp.simuApi.containers.BreakpointHitCountCondition;

/**
 *
 */
public class Breakpoint implements IDataErrorInfo, IGridObject, INotifyPropertyChanged {


    private IBreakpoint breakpoint;
    private Boolean enabled;
    private BreakpointExpressionEvaluation when;
    private Integer count;
    private BreakpointHitCountCondition countCondition;
    private String expression;

    public Breakpoint(IBreakpoint breakpoint) {
        this.breakpoint = breakpoint;

    }

    public static Breakpoint readXmlBreakpoint(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                               IBreakpoint breakpoint) {
        if ("Breakpoint".equals(xmlReader.Name())) {
            Breakpoint tmp = new Breakpoint(breakpoint);
            tmp.readXmlBreakpoint(xmlReader, intelligentObjectXml);
            return tmp;
        }
        return null;
    }

    private boolean readXmlBreakpoint(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Breakpoint", t -> {
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "Enabled", e -> this.enabled = e);
            SomeXmlOperator.readXmlAttributeString(xmlReader, "Expression", e -> this.expression = e);
            SomeXmlOperator.readEnumAttribute(xmlReader, "When", w -> this.when = w,
                    BreakpointExpressionEvaluation.class);
            SomeXmlOperator.readXmlIntAttribute(xmlReader, "Count", c -> this.count = c);
            SomeXmlOperator.readEnumAttribute(xmlReader, "CountCondition", c -> this.countCondition = c,
                    BreakpointHitCountCondition.class);
        }, null);
    }

    public void writeXmlBreakpoint(XmlWriter xmlWriter) {

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
    public String getErrorByIndex(String propertyName) {
        return null;
    }

    @Override
    public String getError() {
        return null;
    }
}
