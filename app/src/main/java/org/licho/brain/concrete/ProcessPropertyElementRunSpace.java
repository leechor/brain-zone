package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.TableRowReferences;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.StatisticsDataSourceInfo;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class ProcessPropertyElementRunSpace extends AbsBaseRunSpace {
    private boolean notBeginEndStep = true;
    private BeginStepProperty beginStep;
    private TokenApplication tokenApplication;
    private int numberTokensInProcess;
    private AbsBaseStepProperty[] absBaseStepProperties;
    private List<TokenRunSpace> associatedObjects;

    public ProcessPropertyElementRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                                          MayApplication application,
                                          AbsIntelligentPropertyObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
    }

    public static void executeToken(ProcessPropertyElementRunSpace processPropertyElementRunSpace,
                                    IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (processPropertyElementRunSpace != null) {
            processPropertyElementRunSpace.executeToken(intelligentObjectRunSpace);
        }
    }

    public static double executeToken(ProcessPropertyElementRunSpace processPropertyElementRunSpace,
                                      TokenRunSpace tokenRunSpace,
                                      IntelligentObjectRunSpace intelligentObjectRunSpace) {
        double num;
        if (processPropertyElementRunSpace != null) {
            num = processPropertyElementRunSpace.executeToken(intelligentObjectRunSpace, true);
        } else {
            num = 1.0;
        }
        if (tokenRunSpace != null) {
            tokenRunSpace.ReturnTable().DoubleValue(num);
        }
        return num;
    }

    public double executeToken(IntelligentObjectRunSpace intelligentObjectRunSpace, boolean param1) {
        return this.executeToken(null, intelligentObjectRunSpace, param1);
    }

    private double executeToken(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return this.executeToken(null, intelligentObjectRunSpace, false);
    }

    private double executeToken(TokenRunSpace tokenRunSpace, IntelligentObjectRunSpace intelligentObjectRunSpace,
                                boolean param2) {

        double doubleValue = this.ReturnTable().DoubleValue();
        if (this.notIsBeginEndStep() && this.Enabled().getInitialValue()) {
            if (tokenRunSpace == null) {
                tokenRunSpace = this.createAssociatedObject(this.beginStep, intelligentObjectRunSpace,
                        this.ParentObjectRunSpace, null, null);
                if (param2) {
                    tokenRunSpace.addEnum70One(true);
                }
            } else {
                tokenRunSpace.setBaseStepProperty(this.beginStep);
            }
            tokenRunSpace.beginStepIndex = this.beginStep.index;
            this.executeToken(tokenRunSpace, false);
            doubleValue = tokenRunSpace.ReturnTable().DoubleValue();
        } else if (tokenRunSpace != null) {
            doubleValue = tokenRunSpace.ReturnTable().DoubleValue();
            this.init(tokenRunSpace);
        }
        return doubleValue;

    }

    void executeToken(TokenRunSpace tokenRunSpace, boolean param1) {
        if (tokenRunSpace.beginStepIndex >= 0) {
            AbsBaseStepProperty absBaseStepProperty = this.absBaseStepProperties[tokenRunSpace.beginStepIndex];
            if (param1 && this.traceBreakpoint(tokenRunSpace, absBaseStepProperty)) {
                return;
            }
            this.executeToken(tokenRunSpace, absBaseStepProperty);
            this.processLoopback(tokenRunSpace, 0);
        }
    }

    private void processLoopback(TokenRunSpace tokenRunSpace, int int_0) {
        super.getMayApplication().addProcessPropertyElementRunSpace(tokenRunSpace, this);
        int num = 0;
        while (tokenRunSpace.beginStepIndex >= 0 && tokenRunSpace.getProcessPropertyElementRunSpace() == this) {
            if (num++ > 1000 && !tokenRunSpace.isEnum70One()) {
                int num2 = int_0 + num;
                if (num2 > 200000) {
                    super.runtimeWarningHandler(this.ParentObjectRunSpace, new Warning(MessageFormat.format(
                                    "Process_Loopback_" +
                                            "{0" +
                                            "}", this.myElementInstance.InstanceName())), tokenRunSpace,
                            EngineResources.Warning_Process_Loopback_Heading,
                            MessageFormat.format(EngineResources.Warning_Process_Loopback_Content,
                                    this.HierarchicalDisplayName(),
                                    200000), null);
                }
                this.method_39(tokenRunSpace, int_0 + num);
                super.getMayApplication().removeLastProcessPropertyElement();
                return;
            }
            AbsBaseStepProperty absBaseStepProperty = this.absBaseStepProperties[tokenRunSpace.beginStepIndex];
            if (this.method_45(tokenRunSpace, absBaseStepProperty)) {
                super.getMayApplication().removeLastProcessPropertyElement();
                return;
            }
            this.executeToken(tokenRunSpace, absBaseStepProperty);
        }
        super.getMayApplication().removeLastProcessPropertyElement();
    }

    private boolean method_45(TokenRunSpace tokenRunSpace, AbsBaseStepProperty absBaseStepProperty) {
        // TODO: 2022/2/7 
        return false;
    }

    private void method_39(TokenRunSpace tokenRunSpace, int param1) {
        CalendarItem calendarItem = super.getMayApplication().getRunOperatorWrapper().createCalendarItem();
        calendarItem.delegate4_0 = this::method_40;
        calendarItem.CalendarEventHandlerWrapper = null; //todo maybe wrong
        super.getSomeRun().method_42(calendarItem);

    }

    private void method_40(CalendarItem calendarItem) {
//		Tuple<TokenRunSpace, int> tuple = calendarItem.class826_0 as Tuple<TokenRunSpace, int>;
//		this.processLoopback(tuple.Item1, tuple.Item2);
    }

    private void executeToken(TokenRunSpace tokenRunSpace, AbsBaseStepProperty absBaseStepProperty) {

        tokenRunSpace.beginStepIndex = -1;
        if (super.getMayApplication().ProfileRecords != null && absBaseStepProperty.vmethod_3()) {
            String displayName = this.DisplayName();
            if (!this.ParentObjectRunSpace.parentEmpty()) {
                displayName = this.ParentObjectRunSpace.myElementInstance.objectDefinition.Name() + "." + displayName;
            }
            super.getMayApplication().ProfileRecords.startWatch(String.join("",
                    displayName,
                    ".",
                    absBaseStepProperty.InstanceName(),
                    " [",
                    absBaseStepProperty.DefinitionName(),
                    "]"
            ), absBaseStepProperty);
            absBaseStepProperty.execute(tokenRunSpace);
            if (super.getMayApplication().ProfileRecords != null) {
                super.getMayApplication().ProfileRecords.record();
            }
        } else {
            absBaseStepProperty.execute(tokenRunSpace);
        }
    }

    private boolean traceBreakpoint(TokenRunSpace tokenRunSpace, AbsBaseStepProperty absBaseStepProperty) {
        // TODO: 2022/2/7
        return false;
    }

    public TokenRunSpace createAssociatedObject(AbsBaseStepProperty absBaseStepProperty,
                                                IntelligentObjectRunSpace associatedObjectRunSpace,
                                                IntelligentObjectRunSpace contextObjectRunSpace,
                                                TableRowReferences param3, TokenRunSpace runSpace) {
        TokenRunSpace tokenRunSpace = this.tokenApplication.setTokenRunSpace(this.ParentObjectRunSpace, this,
                associatedObjectRunSpace, contextObjectRunSpace, param3);
        if (runSpace != null) {
            tokenRunSpace.method_17(runSpace);
        }
        tokenRunSpace.setBaseStepProperty(absBaseStepProperty);
        this.addAssociatedObject(tokenRunSpace);
        return tokenRunSpace;
    }

    private void addAssociatedObject(TokenRunSpace tokenRunSpace) {
        this.numberTokensInProcess++;
        if (this.ParentObjectRunSpace.isContainedByEnum10One()) {
            if (this.associatedObjects == null) {
                this.associatedObjects = new ArrayList<TokenRunSpace>();
            }
            this.associatedObjects.add(tokenRunSpace);
        }
    }

    private boolean notIsBeginEndStep() {
        return this.notBeginEndStep;
    }

    public BooleanTableTrace Enabled() {
        return (BooleanTableTrace) this.absBaseTraces[1];
    }


    public DoubleTable ReturnTable() {
        return (DoubleTable) this.absBaseTraces[2];
    }

    @Override
    public StatisticsDataSourceInfo GetStatisticsDataSourceInfo(String param0) {
        return null;
    }

    @Override
    public void runtimeErrorHandler(IRunSpace param0, AbsPropertyObject param1, IntelligentObjectProperty param2,
                                    String param3) {

    }

    @Override
    public void runtimeErrorHandler(double param0, IRunSpace param1, AbsPropertyObject param2,
                                    IntelligentObjectProperty param3, String param4) {

    }

    public void method_37(TokenRunSpace tokenRunSpace, int i) {
        // TODO: 2022/1/11 
    }

    public void init(TokenRunSpace tokenRunSpace) {
        // TODO: 2022/1/17 
    }

    public int getNumberTokensInProcess() {
		return this.numberTokensInProcess;
    }
}
