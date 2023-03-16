package org.licho.brain.concrete;

import cn.hutool.core.date.DateTime;
import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.exception.SimioRuntimeException;
import org.licho.brain.concrete.fake.CancellationTokenSource;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.ExperimentConstraintsDefinition;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.StatisticConfidenceIntervalType;
import org.licho.brain.enu.UnitType;
import org.licho.brain.enu.WarningNotificationLevel;
import org.licho.brain.event.EventArgs;
import org.licho.brain.event.EventHandler;
import org.licho.brain.event.IEvent;
import org.licho.brain.resource.Image;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.api.IItemDescriptor;
import org.licho.brain.utils.simu.IReselect;
import org.licho.brain.utils.simu.IUnit;
import org.licho.brain.utils.simu.system.Color;
import org.licho.brain.utils.simu.system.IBindingList;
import org.licho.brain.utils.simu.system.ICancelAddNew;
import org.licho.brain.utils.simu.system.IDisposable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 *
 */

public class ActiveModel implements IDisposable, INotifyPropertyChanged, IGridObject, IItemDescriptor, IUnit,
        IReselect {
    private static final Image agentImage = EngineResources.AgentModelLarge;
    private static final Image entityImage = EngineResources.EntityModelLarge;
    private static final Image fixedImage = EngineResources.FixedModelLarge;
    private static final Image linkImage = EngineResources.LinkModelLarge;
    private static final Image nodeImage = EngineResources.NodeModelLarge;
    private static final Image transporterImage = EngineResources.TransporterModelLarge;
    private static final String interactiveScenarioName = EngineResources.InteractiveScenarioName;
    public BaseProjectDefinition projectDefinition;

    private BindingList<ExperimentConstraintsDefinition> experimentConstraints = new BindingList<>();

    private Action<ExperimentConstraintsDefinition> action_4;
    private ActiveModel masterModel;
    private ActiveModel compareToModel;


    public BaseProjectDefinition parentProjectDefinition;
    private SimioApplicationContext applicationContext;
    private RunSetup activateRunSetup;
    public MayApplication MayApplication;
    public List<RunSetup> runSetupList = new ArrayList<>();
    private boolean traceFlag;

    private UUID interactiveResultSetId;
    private DateTime interactiveResultSetTimeStampUtc;

    private IntelligentObjectDefinition intelligentObjectDefinition;
    // system call by instead of Boolean
    private CancellationTokenSource cancellationTokenSource;
    private Errors errors;
    private boolean noRunnable;
    private String masterModelStr;
    private DataAttribute dataAttribute;
    private DataAttribute dataAttributeOther;
    private LogWrapper runLogWrapper;
    private LogWrapper runPlanLogWrapper;
    private String CompareToModelstr;
    private PropertiesTypeList propertiesTypeList;
    private TableTargetPerformanceSummaryResults tableTargetPerformanceSummaryResults;
    private String selectedSelectedReport;
    private String classification;
    private String planningReport;
    public boolean haveReport;
    private int highRiskThreshold;
    private int mediumRiskThreshold = 80;
    private Color highRiskColor = Color.Empty();
    private Color mediumRiskColor = Color.Empty();
    private Color lowRiskColor = Color.Empty();
    private int interactiveVersion = -1;
    private int planResultsVersion = -1;
    private int riskResultsVersion = -1;
    private String publishResultsUrl;
    private String publishResultsName;
    private String publishResultsDescription;
    private DashboardReports dashboardReports;
    private List<Warning> suppressedWarnings;
    ErrorHandler errorHandler;
    private final ActionRun actionRun = new ActionRun(null);
    private Breakpoints breakpoints;
    private EventHandler<EventArgs> runnableChangeEventHandler;
    private EventHandler<EventArgs> resetBindingsEventHandler;
    private Image icon;
    private MayApplication application;
    private EventHandler<EventArgs> pauseRunHandler;
    private boolean normalRun;
    private CalendarItem calendarItem;
    private EventHandler<RunStatusEventArgs> runStatusEventHandler;
    private EventHandler<CancelEventArgs> cancelEventHandler;
    private EventHandler<MayApplication> action29Handler;
    private Enum45 enum45;
    private String string_4;
    private EventHandler<ActiveModel> projectViewNameChangeEvent;
    private EventHandler<EventArgs> startEvent;
    private EventHandler<EventArgs> stopEvent;

    public IGridObject changeObject(IGridObject gridObject) {
        if (gridObject == this.getDefinition().instance) {
            return this;
        }
        // TODO: 2022/1/6
//        if (this._eventHandler != null) {
//            ActiveModel.ChangeModelEventArgs changeModelEventArgs = new ActiveModel.ChangeModelEventArgs(gridObject);
//            this._eventHandler(this, changeModelEventArgs);
//            if (changeModelEventArgs.getGridObjectOld() != null) {
//                return changeModelEventArgs.getGridObjectOld();
//            }
//        }
        return gridObject;
    }

    public void addRunnableChangeEventHandler(IEvent<EventArgs> value) {
        EventHandler.subscribe(this.runnableChangeEventHandler, value);
    }

    public void removeRunnableChangeEventHandler(IEvent<EventArgs> value) {
        EventHandler.unSubscribe(this.runnableChangeEventHandler, value);
    }

    public void addResetBindingsEventHandler(IEvent<EventArgs> value) {
        EventHandler.subscribe(this.resetBindingsEventHandler, value);
    }

    public void removeResetBindingsEventHandler(IEvent<EventArgs> value) {
        EventHandler.unSubscribe(this.resetBindingsEventHandler, value);
    }

    public BindingList<Breakpoint> getBreakpoints() {
        if (this.breakpoints == null) {
            this.breakpoints = new Breakpoints();
        }
        return this.breakpoints;
    }

    public void PauseRun() {
        if (!this.canPauseRun()) {
            return;
        }
        this.application.getSomeRun().markNotRun(null);
        this.pauseRun();
    }

    public boolean canPauseRun() {
        return this.beRun() && !this.HaveCancellationTokenSource();
    }

    public boolean beRun() {
        return this.HaveCancellationTokenSource() || (this.application != null && this.application.getSomeRun().beRun());
    }

    private void pauseRun() {
        if (this.getRunModel() != RunModel.AnimatePlan) {
            this.resetTable();
            this.resetVersion();
        }
        if (this.getRunModel() == ActiveModel.RunModel.RunPlan) {
            this.getRunPlanLogWrapper().reset();
        } else if (this.getRunModel() == ActiveModel.RunModel.Run) {
            this.getRunLogWrapper().reset();
        }
        if (this.pauseRunHandler != null) {
            this.pauseRunHandler.fire(this, EventArgs.Empty);
        }
    }

    private void resetVersion() {
        if (this.application != null && this.application.getFixedRunSpace() != null) {
            if (this.getRunModel() == RunModel.RunPlan) {
                this.resetStatistics(this.getDateAttributeOther().Data());
                this.getTableTargetPerformanceSummaryResults().flush();
                this.getPropertiesTypeList().reloadTableDataOuter();
                this.planResultsVersion = this.getDefinition().getPlanResultsVersion();
            } else {
                this.resetStatistics(this.getDataAttribute().Data());
                this.interactiveVersion = this.getDefinition().getInteractiveVersion();
            }
        }
        if (this.cancellationTokenSource != null) {
            this.riskResultsVersion = this.getDefinition().getRiskResultsVersion();
        }

    }

    private void resetStatistics(BindingList<Statistic> listToFill) {
        AboutReport aboutReport = new AboutReport(ActiveModel.interactiveScenarioName, 0);
        this.application.getFixedRunSpace().GetReportedStatistics(aboutReport);
        listToFill.clear();
        for (Statistic statistic : aboutReport.getStatistics()) {
            if (statistic.method_2() && !Double.isNaN(statistic.Value())) {
                listToFill.Add(new Statistic(statistic.Identifier(), 0, statistic.Value(), statistic.UnitType(), this));
            }
        }
        listToFill.ResetBindings();
    }

    private void resetTable() {
        if (this.application != null && this.application.getFixedRunSpace() != null) {
            if (this.application.getFixedRunSpace().tablesStatesWrappers != null) {
                for (TablesStatesWrapper tablesStatesWrapper :
                        this.application.getFixedRunSpace().tablesStatesWrappers) {
                    if (tablesStatesWrapper != null) {
                        tablesStatesWrapper.States().reset(tablesStatesWrapper, this.getRunModel());
                    }
                }
            }
            for (Table table : this.getDefinition().Tables().getValues()) {
                TargetResults.Enum68 enum68 = (this.getRunModel() == RunModel.RunPlan) ?
                        (TargetResults.Enum68.One) : (TargetResults.Enum68.Zero);
                int version = (this.getRunModel() == ActiveModel.RunModel.RunPlan) ?
                        this.getDefinition().getPlanResultsVersion() :
                        this.getDefinition().getInteractiveVersion();
                if (table.TargetResults().method_7(this.application.getFixedRunSpace(), enum68, version)) {
                    table.ResetBindings();
                }
            }
        }
    }

    public ActiveModel.RunModel getRunModel() {
        return null;
    }

    public void run(SomeRun.RunEventHandler triggerRunEventHandler, RunModel runModel, int maxValue) {
        this.run(RunStatus.Two, triggerRunEventHandler, runModel, Integer.MAX_VALUE, false, false);

    }

    private void run(RunStatus runStatus, SomeRun.RunEventHandler triggerRunEventHandler, RunModel model, int maxSome,
                     boolean bool_0,
                     boolean isFastForward) {
        this.normalRun = runStatus == RunStatus.NormalRun;
        if (this.canModify()) {
            if (this.normalRun && !this.haveCalendarItem()) {
                this.method_144();
            } else if (!this.normalRun && this.haveCalendarItem()) {
                this.method_146();
            }
        }

        if (maxSome == Integer.MAX_VALUE) {
            maxSome = switch (runStatus) {
                case NormalRun -> (this.getRunSetup() != null ?
                        this.getRunSetup().CalendarEventsBetweenCallbacksForInteractiveRun() : 32);
                case FastRun -> 10000;
                case Two -> 50000;
            };
        }

        if (this.beRun()) {
            this.application.getSomeRun().method_27(maxSome);
            this.triggerRunStatusEvent(runStatus);
            return;
        }

        boolean flag = false;
        if (!this.canModify()) {
            flag = this.method_54(this.getRunSetup().SpecificReplicationNumber(), model, isFastForward);
            if (!flag) {
                return;
            }
        }
        this.triggerRunStatusEvent(runStatus);
    }

    private boolean method_54(int specificReplicationNumber, RunModel model, boolean isFastForward) {
        if (this.application != null) {
            this.method_58();
        }
        if (this.haveCancelEvent()) {
            this.method_51();
            this.application = new MayApplication(this, this.getRunSetup(), specificReplicationNumber, null, null,
                    model);
            this.application.IsFastForward(isFastForward);
            if (model == RunModel.RunPlan) {
                this.disposeRunPlanLogWrapper();
            }
            if (model == RunModel.Run) {
                this.disposeRunLogWrapper();
            }

            this.triggerAction29Handler(this.application);
            this.application.method_20();
            if (this.normalRun) {
                this.method_144();
            }

            this.getDataAttribute().close();
            if (model == ActiveModel.RunModel.RunPlan) {
                this.method_104();
                this.getTableTargetPerformanceSummaryResults().clear();
                this.getPropertiesTypeList().clear();
                this.getDateAttributeOther().close();
            }
            this.method_53();
            this.method_124();
            return true;
        }
        return false;
    }

    private void method_124() {
        // TODO: 2022/2/7
    }

    private void method_53() {
        // TODO: 2022/2/7
    }

    private void method_104() {
        for (Table table : this.getDefinition().Tables().getValues()) {
            table.TargetResults().init();
            table.Data().Rows().ResetBindings();
        }
    }

    private void triggerAction29Handler(MayApplication mayApplication) {
        if (this.action29Handler != null) {
            this.action29Handler.fire(null, mayApplication);
        }
    }

    private void disposeRunLogWrapper() {
        this.getRunLogWrapper().dispose();

    }

    private void disposeRunPlanLogWrapper() {
        this.getRunPlanLogWrapper().dispose();

    }


    private boolean haveCancelEvent() {
        if (this.cancelEventHandler != null) {
            CancelEventArgs cancelEventArgs = new CancelEventArgs(false);
            this.cancelEventHandler.fire(this, cancelEventArgs);
            if (cancelEventArgs.Cancel()) {
                this.method_51();
                return false;
            }
        }
        return true;
    }

    private void method_51() {
        if (this.HaveCancellationTokenSource()) {
            return;
        }

        this.getDefinition().method_428();
        if (this.projectDefinition != null && this.projectDefinition.activeModels != null) {
            List<ActiveModel> source = this.projectDefinition.activeModels;
            for (ActiveModel activeModel : source.stream().filter(activeModel -> activeModel != this &&
                    !activeModel.Runnable()).toList()) {
                activeModel.getDefinition().method_428();
            }
        }
    }

    private void method_58() {
        // TODO: 2022/2/7
    }

    private void triggerRunStatusEvent(RunStatus runStatus) {
        if (this.runStatusEventHandler != null) {
            this.runStatusEventHandler.fire(this, new ActiveModel.RunStatusEventArgs(runStatus));
        }
    }

    private void method_144() {
//        if (this.calendarItem == null) {
//            this.calendarItem = this.application.getRunOperatorWrapper().createCalendarItem(false);
//            this.calendarItem.bool_1 = false;
//			CalendarItem runOperator = this.calendarItem;
//
//        }
    }

    private void method_146() {
        // TODO: 2022/2/7
    }

    private boolean haveCalendarItem() {
        return this.calendarItem != null;
    }

    public void stop(boolean throwError) {
        if (this.HaveCancellationTokenSource()) {
            this.cancel();
            return;
        }

        try {
            if (this.application != null) {
                try {
                    if (this.application.getFixedRunSpace() != null) {
                        IntelligentObjectRunSpace intelligentObjectRunSpace = this.application.getFixedRunSpace();

                        intelligentObjectRunSpace.method_42(t -> t.executeToken(t), false, false);
                    }
                } catch (Exception ex) {
                    if (!throwError) {
                        throw ex;
                    }
                }
                if (this.notRun()) {
                    this.pauseRun();
                }
            }
        } finally {
            if (this.beRun()) {
                this.PauseRun();
            }
            try {
                this.method_58();
            } catch (Exception ex) {
                if (!throwError) {
                    throw ex;
                }
            }
        }
    }

    public boolean notRun() {
        return !this.beRun();
    }

    private void cancel() {
        if (this.HaveCancellationTokenSource()) {
            this.cancellationTokenSource.Cancel();
        }
    }

    public boolean isRunnableAndNotTokenCancel() {
        return this.Runnable() && !this.HaveCancellationTokenSource();
    }

    public void reset(RunModel runModel) {
        // TODO: 2022/2/8 
    }

    public boolean canStep() {
        // TODO: 2022/2/8 
        return false;
    }

    public boolean canRun() {
        // TODO: 2022/2/8 
        return true;
    }

    public boolean canFastForward() {
        // TODO: 2022/2/8
        return true;
    }

    public boolean isOneStepping() {
        return this.enum45 == ActiveModel.Enum45.One;
    }

    public void method_66(String message) {
        if (this.enum45 == ActiveModel.Enum45.One) {
            this.enum45 = ActiveModel.Enum45.Two;
            this.string_4 = message;
        }
    }

    public void addProjectViewNameChangeEvent(IEvent<ActiveModel> value) {
        EventHandler.subscribe(this.projectViewNameChangeEvent, value);
    }

    public void removeProjectViewNameChangeEvent(IEvent<ActiveModel> value) {
        EventHandler.unSubscribe(this.projectViewNameChangeEvent, value);
    }

    public void addCancelEvent(IEvent<CancelEventArgs> value) {
        EventHandler.subscribe(this.cancelEventHandler, value);
    }

    public void removeCancelEvent(IEvent<CancelEventArgs> value) {
        EventHandler.unSubscribe(this.cancelEventHandler, value);
    }

    public void addStartEvent(IEvent<EventArgs> value) {
        EventHandler.subscribe(this.startEvent, value);
    }

    public void removeStartEvent(IEvent<EventArgs> value) {
        EventHandler.unSubscribe(this.startEvent, value);
    }

    public void addStopEvent(IEvent<EventArgs> value) {
        EventHandler.subscribe(this.stopEvent, value);
    }

    public void removeStopEvent(IEvent<EventArgs> value) {
        EventHandler.unSubscribe(this.stopEvent, value);
    }

    public void reRegisterEventByType(IntelligentObjectDefinition intelligentObjectDefinition,
                                      ObjectClass objectClass, String name, boolean visible) {
        switch (objectClass) {
            case Object -> {
            }
            case Fixed -> this.reRegisterEvent(new FixedDefinition(name, this, intelligentObjectDefinition));
            case Entity -> this.reRegisterEvent(new EntityDefinition(name, this, intelligentObjectDefinition));
            case Transporter -> this.reRegisterEvent(new TransporterDefinition(name, this,
                    intelligentObjectDefinition));
            case Link -> this.reRegisterEvent(new LinkDefinition(name, this,
                    (LinkDefinition) intelligentObjectDefinition));
            case Agent -> this.reRegisterEvent(new AgentDefinition(name, this, intelligentObjectDefinition));
            case Node -> this.reRegisterEvent(new NodeDefinition(name, this, intelligentObjectDefinition));
        }
        this.getDefinition().resetNameCollections();
        String userName = "licho";
        if (!Strings.isNullOrEmpty(userName)) {
            this.getDefinition().Author(userName);
        }
        this.initInstance();
        if (intelligentObjectDefinition == null) {
            this.Runnable(this.getDefinition().DefaultRunnable());
        } else {
            this.Runnable(intelligentObjectDefinition.activeModel.Runnable());
        }
        if (visible && this.getDefinition().getChildrenObject().isEmpty()) {
            IntelligentObject intelligentObject =
                    this.getDefinition().createIntelligentObject(EntityDefinition.create(),
                            EngineResources.DefaultEntityInstanceName, ElementScope.Public);
            if (intelligentObject != null) {
                intelligentObject.getSizeStateGridItemProperties().setHeight(0.5);
                intelligentObject.getSizeStateGridItemProperties().setLength(0.5);
                intelligentObject.getSizeStateGridItemProperties().setWidth(0.5);
            }
        }
    }

    public enum RunModel {
        NotRun, // 0
        Run, // 1
        Second, // 2
        RunPlan, // 3
        AnimatePlan // 4
    }

    public enum RunStatus {
        NormalRun, // 0
        FastRun, // 1
        Two
    }

    public enum Enum45 {
        None,
        Two, One // 1
    }

    @FunctionalInterface
    public interface ErrorHandler {
        void apply(double errorTime, IRunSpace runSpaceContext, AbsPropertyObject propertyBagInstance,
                   IntelligentObjectProperty propertyInstance, String message);
    }


    public ActiveModel(SimioApplicationContext applicationContext) {
        this(null, applicationContext);
    }

    public ActiveModel(BaseProjectDefinition baseProjectDefinition) {
        this.projectDefinition = baseProjectDefinition;
        this.setActivateRunSetup(new RunSetup(this));
        this.TraceFlag(false);
        this.normalRun = true;
        this.errorHandler = this::handleError;
    }

    private ActiveModel(BaseProjectDefinition parentProjectDefinition,
                        SimioApplicationContext applicationContext) {
        this.parentProjectDefinition = parentProjectDefinition;
        this.setActivateRunSetup(new RunSetup(this));
        this.traceFlag = false;
        this.errorHandler = this::handleError;
    }

    private void handleError(double unitCarry, IRunSpace runSpace, AbsPropertyObject absPropertyObject,
                             IntelligentObjectProperty intelligentObjectProperty, String param4) {
        StringBuilder error = new StringBuilder();
        if (RuntimeErrorFullMessageDetails.IsError(this.actionRun, unitCarry, runSpace, absPropertyObject,
                intelligentObjectProperty, param4, error)) {
            System.out.println(error);
            throw new SimioRuntimeException(error.toString());
        }
    }

    private void setActivateRunSetup(RunSetup runSetup) {
        this.activateRunSetup = runSetup;
    }

    public ActiveModel(IntelligentObjectDefinition currentModel) {
        this(null, null);
        this.reRegisterEvent(currentModel);

    }

    public boolean TraceFlag() {
        return this.traceFlag;
    }

    public void TraceFlag(boolean value) {
        this.traceFlag = value;
    }

    public WarningNotificationLevel WarningNotificationLevel() {
        return this.getRunSetup().WarningNotificationLevel();
    }

    public void WarningNotificationLevel(WarningNotificationLevel value) {
        this.getRunSetup().WarningNotificationLevel(value);
    }


    private void updateInteractiveResultState() {
        this.interactiveResultSetId = UUID.randomUUID();
        this.interactiveResultSetTimeStampUtc = DateTime.now();
    }

    public String Name() {
        if (this.getDefinition() != null) {
            return this.getDefinition().Name();
        }
        return "";
    }

    public void Name(String value) {

    }

    public IntelligentObjectDefinition getDefinition() {
        return this.intelligentObjectDefinition;
    }

    private void reRegisterEvent(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.unRegisterEvent();
        this.intelligentObjectDefinition = intelligentObjectDefinition;
        this.registerEvent();
    }

    private void registerEvent() {
    }

    private void unRegisterEvent() {
        if (this.intelligentObjectDefinition != null) {
//            this.intelligentObjectDefinition.Event_42 -= this.eventHandler_0;
//            this.intelligentObjectDefinition.Event_43 -= this.eventHandler_1;
//            this.intelligentObjectDefinition.Event_44 -= this.eventHandler_2;
//            this.intelligentObjectDefinition.Event_45 -= this.eventHandler_3;
//            this.intelligentObjectDefinition.Event_46 -= this.eventHandler_4;
//            this.intelligentObjectDefinition.Event_48 -= this.eventHandler_6;
//            this.intelligentObjectDefinition.ElementEvent_7 -= this.eventHandler_7;
//            this.intelligentObjectDefinition.Event_8 -= this.eventHandler_8;
//            this.intelligentObjectDefinition.Event_10 -= this.eventHandler_9;
//            this.intelligentObjectDefinition.Event_11 -= this.eventHandler_10;
//            this.intelligentObjectDefinition.Event_12 -= this.eventHandler_11;
//            this.intelligentObjectDefinition.Event_19 -= this.eventHandler_12;
//            this.intelligentObjectDefinition.Event_29 -= this.eventHandler_13;
//            this.intelligentObjectDefinition.Event_30 -= this.zMqfzacSaGn;
//            this.intelligentObjectDefinition.Event_49 -= this.eventHandler_14;
//            this.intelligentObjectDefinition.Event_50 -= this.eventHandler_15;
//            this.intelligentObjectDefinition.Event_53 -= this.eventHandler_16;
//            this.intelligentObjectDefinition.Event_41 -= this.eventHandler_17;
        }
    }

    public static ActiveModel readXmlModel(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                           BaseProjectDefinition baseProjectDefinition, ActiveModel activeModel,
                                           ActiveModel.Enum46 enum46,
                                           BaseProjectDefinition.IntelligentObjectXmlReader intelligentObjectXmlReader,
                                           BaseProjectDefinition.ExperimentConstraintsXmlReader experimentConstraintsXmlReader) {
        if (Objects.equals(xmlReader.Name(), "Model")) {
            IntelligentObjectDefinition objectDefinition = null;
            ActiveModel activeM = null;
            String definitionName = xmlReader.GetAttribute("Definition");

            if (!Strings.isNullOrEmpty(definitionName)) {
                if (enum46 == Enum46.Zero) {
                    Guid guid = intelligentObjectXml.getGuidByName(definitionName);
                    ActiveModel am = baseProjectDefinition.activeModels.stream()
                            .filter(model -> model.getDefinition() != null &&
                                    model.getDefinition().getGuid() == guid)
                            .findFirst()
                            .orElse(null);

                    if (am != null) {
                        objectDefinition = intelligentObjectXml.readIntelligentObjectDefinitionByName(
                                definitionName, null, baseProjectDefinition, true, false);
                        objectDefinition.setGuid(Guid.NewGuid());
                    } else {
                        objectDefinition = intelligentObjectXml.readIntelligentObjectDefinitionByName(
                                definitionName, null, baseProjectDefinition, false, false);
                    }

                    int num = 1;
                    String name = objectDefinition.Name();
                    while (baseProjectDefinition.activeModels.stream()
                            .anyMatch(model -> Objects.equals(model.getDefinition().Name(), name))) {
                        objectDefinition.Name(name + num);
                        num++;
                    }
                } else {
                    objectDefinition = intelligentObjectXml.readIntelligentObjectDefinitionByName(definitionName,
                            null, baseProjectDefinition, false, false);
                }

                if (objectDefinition != null) {
                    activeM = objectDefinition.activeModel;
                }
            }

            if (objectDefinition != null) {
                if (activeM == null) {
                    activeM = new ActiveModel(baseProjectDefinition);
                }
                activeM.reRegisterEvent(objectDefinition);
                objectDefinition.activeModel = activeM;
                if (objectDefinition.instance == null) {
                    activeM.initInstance();
                    objectDefinition.flashStateFalse();
                }
                activeM.MasterModel(activeModel);
                if (baseProjectDefinition != null) {
                    baseProjectDefinition.addActiveModelAndLimitOuter(activeM);
                }
            }
            if (activeM != null) {
                activeM.readXmlDefinitionModel(xmlReader, intelligentObjectXml, baseProjectDefinition,
                        intelligentObjectXmlReader, experimentConstraintsXmlReader);
            }
            return activeM;
        }
        return null;
    }

    private void readXmlDefinitionModel(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                        BaseProjectDefinition baseProjectDefinition,
                                        BaseProjectDefinition.IntelligentObjectXmlReader intelligentObjectXmlReader,
                                        BaseProjectDefinition.ExperimentConstraintsXmlReader experimentConstraintsXmlReader) {
        try (var a = this.getDefinition().createModelOperatorCounter()) {
            SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Model", attr -> {
                SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "Runnable", this::Runnable);
                if (this.activateRunSetup == null) {
                    this.activateRunSetup = new RunSetup(this);
                }
                SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "Trace", this::TraceFlag);
                SomeXmlOperator.readXmlAttributeString(xmlReader, "MasterModel", t -> this.masterModelStr = t);
            }, body -> {
                if (this.getRunSetup().readXMlRunSetup(xmlReader, intelligentObjectXml)) {
                    return true;
                }

                if (SomeXmlOperator.xmlReaderElementOperator(body, "SelectedReport", null,
                        reportBody -> SomeXmlOperator.readXMLText(reportBody, t -> this.selectedSelectedReport = t))) {
                    return true;
                }

                if (SomeXmlOperator.xmlReaderElementOperator(body, "SelectedOperationalPlanningReport", reportAttr ->
                        SomeXmlOperator.readXmlAttributeString(reportAttr, "Classification",
                                this::Classification), reportBody -> SomeXmlOperator.readXMLText(reportBody,
                        this::SelectedOperationalPlanningReport))) {
                    return true;
                }

                if (SomeXmlOperator.xmlReaderElementOperator(body, "TargetSettings", attr -> {
                    SomeXmlOperator.readXmlIntAttribute(xmlReader, "HighRiskThreshold", this::HighRiskThreshold);
                    SomeXmlOperator.readXmlIntAttribute(xmlReader, "MediumRiskThreshold", this::MediumRiskThreshold);
                    SomeXmlOperator.readAttributeColorOperator(xmlReader, "HighRiskColor", this::HighRiskColor);
                    SomeXmlOperator.readAttributeColorOperator(xmlReader, "MediumRiskColor",
                            this::MediumRiskColor);
                    SomeXmlOperator.readAttributeColorOperator(xmlReader, "LowRiskColor", this::LowRiskColor);
                }, null)) {
                    return true;
                }

                if (Objects.equals(xmlReader.Name(), "RunnableInstance")) {
                    intelligentObjectXml.RunnableInstance(xmlReader.ReadOuterXml(), this);
                    return true;
                }

                return SomeXmlOperator.xmlReaderElementOperator(body, "Icon", null,
                        iconBody -> SomeXmlOperator.readXmlIcon(iconBody, intelligentObjectXml, this::Icon)) ||
                        SomeXmlOperator.xmlReaderElementOperator(body, "SuppressedWarnings", null, warningsBody -> {
                            Warning warning = Warning.xmlReaderWarning(warningsBody);
                            if (warning != null) {
                                intelligentObjectXml.addWarning(this, warning);
                                return true;
                            }
                            return false;
                        }) ||
                        SomeXmlOperator.xmlReaderElementOperator(body, "InteractiveStatistics", null,
                                interactiveStatisticsReader -> {
                                    this.interactiveVersion = 0;
                                    return SomeXmlOperator.xmlReaderElementOperator(interactiveStatisticsReader,
                                            "FileRef", attr -> {
                                                String attribute = attr.GetAttribute("Name");
                                                this.getDataAttribute().setStream(intelligentObjectXml.getFiles().OpenReadStream(attribute));
                                            }, null);
                                }) ||
                        this.getRunLogWrapper().readXmlResourceUsageLog(body, intelligentObjectXml) ||
                        SomeXmlOperator.xmlReaderElementOperator(body, "OperationalPlanningResults",
                                planResultsReader -> {
                                    this.planResultsVersion = 0;
                                    this.riskResultsVersion = 0;
                                    SomeXmlOperator.readXmlBooleanAttribute(planResultsReader,
                                            "PlanResultsAreOutOfDate", b -> this.planResultsVersion = b ? -2 : 0);
                                    SomeXmlOperator.readXmlBooleanAttribute(planResultsReader,
                                            "RiskResultsAreOutOfDate", b -> this.riskResultsVersion = b ? -2 : 0);
                                    SomeXmlOperator.readXmlAttributeString(planResultsReader, "CompareToModel", t ->
                                            this.CompareToModelstr = t);
                                },
                                planResultsBody -> this.getRunPlanLogWrapper().readXmlResourceUsageLog(planResultsBody,
                                        intelligentObjectXml) ||
                                        SomeXmlOperator.xmlReaderElementOperator(planResultsBody, "PlanStatistics",
                                                null,
                                                planStatisticsReader -> SomeXmlOperator.xmlReaderElementOperator(planStatisticsReader, "FileRef", attr -> {
                                                    String attribute = attr.GetAttribute("Name");
                                                    this.dataAttributeOther.setStream(intelligentObjectXml.getFiles().OpenReadStream(attribute));
                                                }, null)) ||
                                        SomeXmlOperator.xmlReaderElementOperator(body, "PublishResultsSettings",
                                                attr -> {
                                                    SomeXmlOperator.readXmlAttributeString(attr, "Url", t ->
                                                            this.publishResultsUrl = t);
                                                    SomeXmlOperator.readXmlAttributeString(attr, "Name", t ->
                                                            this.publishResultsName = t);
                                                    SomeXmlOperator.readXmlAttributeString(attr, "Description", t ->
                                                            this.publishResultsDescription = t);
                                                }, null) ||
                                        SomeXmlOperator.xmlReaderElementOperator(body, "Experiments", null, expBody -> {
                                            String readOuterXml = expBody.ReadOuterXml();
                                            intelligentObjectXml.Experiments(readOuterXml, this,
                                                    baseProjectDefinition, experimentConstraintsXmlReader);
                                            return true;
                                        }) ||
                                        this.getDashboardReports().readXml(xmlReader, intelligentObjectXml) ||
                                        (intelligentObjectXmlReader != null && intelligentObjectXmlReader.apply(this,
                                                body, intelligentObjectXml)));
            });
        }
        this.haveReport = false;
    }

    private DashboardReports getDashboardReports() {
        if (this.dashboardReports == null) {
            this.dashboardReports = new DashboardReports(this);
        }
        return this.dashboardReports;
    }


    public void LowRiskColor(Color value) {
        if (this.lowRiskColor != value) {
            this.lowRiskColor = value;
            this.triggerRiskChangeEvent();
        }
    }

    public void MediumRiskColor(Color value) {
        if (this.mediumRiskColor != value) {
            this.mediumRiskColor = value;
            this.triggerRiskChangeEvent();
        }
    }

    public void HighRiskColor(Color value) {
        if (this.highRiskColor != value) {
            this.highRiskColor = value;
            this.triggerRiskChangeEvent();
        }
    }

    private void triggerRiskChangeEvent() {
        // TODO: 2021/12/18
    }

    public void MediumRiskThreshold(int value) {
        if (this.mediumRiskThreshold != value) {
            this.mediumRiskThreshold = value;
            this.triggerRiskChangeEvent();
        }
    }

    public void HighRiskThreshold(int value) {
        if (this.highRiskThreshold != value) {
            this.highRiskThreshold = value;
            this.triggerRiskChangeEvent();
        }
    }

    public String SelectedOperationalPlanningReport() {
        return Objects.requireNonNullElse(this.planningReport, "Resource Dispatch List");
    }

    public void SelectedOperationalPlanningReport(String value) {
        this.planningReport = value;
        this.haveReport = true;
        if (this.parentProjectDefinition != null) {
            this.parentProjectDefinition.isInited = true;
        }
    }


    public void Classification(String value) {
        this.classification = value;
    }

    public ActiveModel MasterModel() {
        return this.masterModel;
    }

    public void MasterModel(ActiveModel value) {
        this.masterModel = value;
    }

    public boolean haveMasterModel() {
        return this.masterModel != null;
    }

    public void initInstance() {
        this.getDefinition().instance =
                (IntelligentObject) this.getDefinition().CreateInstance(this.getDefinition().Name());
        this.getDefinition().instance.properties.init();
    }


    @Override
    public Object getItem() {
        return null;
    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public String Group() {
        return this.getDefinition().ObjectClass().toString();
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
        return -1;
    }

    @Override
    public int SateIconIndex() {
        return -1;
    }

    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public void Rename(String newName) {
        this.Name(newName);
    }

    @Override
    public boolean canRenameTo(String newName, String failureReason) {
        return false;
    }


    public IntelligentObjectDefinition getIntelligentObjectFacility() {
        return intelligentObjectDefinition;
    }

    public void setIntelligentObjectFacility(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.intelligentObjectDefinition = intelligentObjectDefinition;
    }

    public RunSetup getRunSetup() {
        return activateRunSetup;
    }

    public void setRunSetup(RunSetup activateRunSetup) {
        this.activateRunSetup = activateRunSetup;
    }

    public boolean method_26() {
        return this.HaveCancellationTokenSource() || this.MayApplication != null;
    }

    private boolean HaveCancellationTokenSource() {
        return this.cancellationTokenSource != null;
    }

    public Errors getErrors() {
        if (this.errors == null) {
            this.errors = new Errors(this);
        }
        return this.errors;
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
    public int GetUnitsFor(UnitType unitType) {
        final RunSetup runSetup = this.getRunSetup();
        return switch (unitType) {
            case Time -> runSetup.getTimeLevel();
            case TravelRate -> runSetup.getTravelRateLevel();
            case Length -> runSetup.getLength();
            case Currency -> this.getDefinition().UnitFilter().CurrencyFilter().getUnitTypeIndex();
            case CurrencyPerTimeUnit -> AboutUnit.getUnitTypeIndex(this.getDefinition()
                    .UnitFilter().CurrencyFilter().getUnitTypeIndex(), runSetup.getTimeLevel());
            case Volume -> runSetup.getVolumeLevel();
            case Weight -> runSetup.getWeightLevel();
            case VolumeFlowRate -> runSetup.getVolumeFlowRateLevel();
            case WeightFlowRate -> runSetup.getWeightFlowRateLevel();
            case TravelAcceleration -> ActiveModel.getUnitTypeByLevel(runSetup.getTravelRateLevel());
            default -> 0;
        };
    }

    private static int getUnitTypeByLevel(int level) {
        return switch (level) {
            case 0 -> AboutUnit.getUnitTypeIndex(0, 0);
            case 1 -> AboutUnit.getUnitTypeIndex(0, 2);
            case 2 -> AboutUnit.getUnitTypeIndex(1, 0);
            case 3 -> AboutUnit.getUnitTypeIndex(4, 2);
            case 4 -> AboutUnit.getUnitTypeIndex(4, 1);
            case 5 -> AboutUnit.getUnitTypeIndex(6, 0);
            case 6 -> AboutUnit.getUnitTypeIndex(0, 1);
            case 7 -> AboutUnit.getUnitTypeIndex(4, 0);
            case 8 -> AboutUnit.getUnitTypeIndex(7, 0);
            default -> 0;
        };
    }


    @Override
    public StatisticConfidenceIntervalType ConfidenceLevel() {
        return null;
    }

    @Override
    public UnitConversions UnitConversions() {
        return null;
    }

    @Override
    public void Dispose() {

    }

    public boolean canModify() {
        return this.HaveCancellationTokenSource() || this.MayApplication != null;
    }

    public Image Icon() {
        if (this.icon == null && this.getDefinition() != null) {
            return ActiveModel.getImageByObjectType(this.getDefinition().ObjectClass());
        }
        return this.icon;
    }


    public void Icon(Image value) {

    }

    private static Image getImageByObjectType(ObjectClass objectClass) {
        return switch (objectClass) {
            case Fixed -> ActiveModel.fixedImage;
            case Entity -> ActiveModel.entityImage;
            case Transporter -> ActiveModel.transporterImage;
            case Link -> ActiveModel.linkImage;
            case Agent -> ActiveModel.agentImage;
            case Node -> ActiveModel.nodeImage;
            default -> null;
        };
    }

    public boolean Runnable() {
        return !this.noRunnable;
    }

    public void Runnable(boolean value) {
        boolean flag = !value;
        if (this.noRunnable != flag) {
            this.noRunnable = flag;
            this.triggerRunnableChangeEvent();
        }
    }

    public void limit(ActiveModel activeModel) {
        this.getDefinition().limit(activeModel);
    }

    private void triggerRunnableChangeEvent() {
        EventHandler.fire(this.runnableChangeEventHandler, this, EventArgs.Empty);
    }

    public void resetDataBindings() {
        try {
            if (this.getDataAttribute().haveData()) {
                this.getDataAttribute().Data().ResetBindings();
            }
            if (this.getDateAttributeOther().haveData()) {
                this.getDateAttributeOther().Data().ResetBindings();
            }
        } catch (Exception ignored) {
        }
        this.getDefinition().flushResourceLogExpressionTable();
        this.getRunPlanLogWrapper().ResetBindings();
        this.getRunLogWrapper().ResetBindings();
        this.ResetBindingsEvent();
    }

    private void ResetBindingsEvent() {
        EventHandler.fire(this.resetBindingsEventHandler, this, EventArgs.Empty);
    }

    private LogWrapper getRunLogWrapper() {
        if (this.runPlanLogWrapper == null) {
            this.runPlanLogWrapper = new LogWrapper(this);
        }
        return this.runPlanLogWrapper;
    }

    private LogWrapper getRunPlanLogWrapper() {
        if (this.runLogWrapper == null) {
            this.runLogWrapper = new LogWrapper(this);
        }
        return this.runLogWrapper;
    }

    private DataAttribute getDateAttributeOther() {
        if (this.dataAttributeOther == null) {
            this.dataAttributeOther = new DataAttribute(this);
        }
        return this.dataAttributeOther;
    }

    private DataAttribute getDataAttribute() {
        if (this.dataAttribute == null) {
            this.dataAttribute = new DataAttribute(this);
        }
        return this.dataAttribute;
    }

    public void flush() {
        this.getDefinition().flashStateFalse();
        if (this.masterModelStr != null) {
            this.masterModel = this.parentProjectDefinition.get(this.masterModelStr);
            this.masterModelStr = null;
        }
        if (this.CompareToModelstr != null) {
            this.compareToModel = this.parentProjectDefinition.get(this.CompareToModelstr);
            this.CompareToModelstr = null;
        }
        this.getTableTargetPerformanceSummaryResults().flush();
        this.getPropertiesTypeList().reloadTableDataOuter();
    }

    private PropertiesTypeList getPropertiesTypeList() {
        if (this.propertiesTypeList == null) {
            var tables = this.getDefinition().Tables();
            this.propertiesTypeList =
                    new PropertiesTypeList(tables.values.stream().
                            filter(t -> t.Schema().Targets().Count() != 0).findFirst().orElse(null));
        }
        return this.propertiesTypeList;
    }

    private TableTargetPerformanceSummaryResults getTableTargetPerformanceSummaryResults() {
        if (this.tableTargetPerformanceSummaryResults == null) {
            this.tableTargetPerformanceSummaryResults = new TableTargetPerformanceSummaryResults(this);
        }
        return this.tableTargetPerformanceSummaryResults;
    }

    public ExperimentConstraintsDefinition addExperimentConstraints() {
        ExperimentConstraintsDefinition constraints = new ExperimentConstraintsDefinition(this.getExperimentName(),
                this);
        this.Experiments().add(constraints);
        int index = this.Experiments().values.indexOf(constraints);
        ((IBindingList) constraints).AddNew();
        ((IBindingList) constraints).AddNew();
        ((ICancelAddNew) constraints).CancelNew(1);
        if (this.parentProjectDefinition != null) {
            this.parentProjectDefinition.inited();
        }
        this.method_86(constraints, index);
        return constraints;
    }

    private void method_86(ExperimentConstraintsDefinition constraints, int index) {
        // TODO: 2021/12/20 
    }

    private String getExperimentName() {
        int num = 1;
        StringBuilder text = new StringBuilder();
        String name;
        do {
            name = MessageFormat.format("Experiment%d", num++);
        }
        while (!this.containExperimentConstraint(name, text));
        return name;
    }

    boolean containExperimentConstraint(String name, StringBuilder error) {
        if (experimentConstraints.values.stream().anyMatch(t -> t.Name().equals(name))) {
            return true;
        }
        error.append(MessageFormat.format(EngineResources.ErrorRenamingExperimentToExistingName, name));
        return false;
    }

    public BindingList<ExperimentConstraintsDefinition> Experiments() {
        return this.experimentConstraints;
    }

    public void recordWarning(Warning warning, IntelligentObject intelligentObject) {
        if (intelligentObject != null) {
            this.getDefinition().recordWarning(warning, intelligentObject);
        } else {
            if (this.suppressedWarnings == null) {
                this.suppressedWarnings = new ArrayList<>();
            }
            this.suppressedWarnings.add(warning.clone());
        }
        if (this.parentProjectDefinition != null) {
            this.parentProjectDefinition.inited();
        }
    }

    public enum Enum46 {
        Zero,
        One
    }

    public class ActiveModelRunnableChangeEvent extends EventArgs {

    }

    public class RunStatusEventArgs extends EventArgs {
        public RunStatusEventArgs(ActiveModel.RunStatus runStatus) {
            this.runStatus = runStatus;
        }

        public ActiveModel.RunStatus method_0() {
            return this.runStatus;
        }

        private ActiveModel.RunStatus runStatus;
    }

}


