package org.licho.brain.concrete.property;

import com.google.common.base.Strings;
import org.licho.brain.concrete.AbsBaseTrace;
import org.licho.brain.concrete.AbsIntelligentPropertyObject;
import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.Event;
import org.licho.brain.concrete.IRunSpace;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.MayApplication;
import org.licho.brain.concrete.SomeRun;
import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.Warning;
import org.licho.brain.concrete.fake.TableRowReferences;
import org.licho.brain.enu.Enum34;
import org.licho.brain.enu.Enum42;
import org.licho.brain.enu.StatisticsDataSourceInfo;
import org.licho.brain.enu.UnitType;
import org.licho.brain.utils.simu.IAboutReport;
import org.licho.brain.utils.simu.IStatisticsDataSource;

import java.text.MessageFormat;
import java.util.Objects;

/**
 *
 */
public abstract class AbsBaseRunSpace implements IStatisticsDataSource, IRunSpace {

    public Event[] events;
    public AbsBaseTrace[] absBaseTraces;
    public IntelligentObjectRunSpace ParentObjectRunSpace;
    public final TableRowReferences tableRowReferences = new TableRowReferences();

    public AbsIntelligentPropertyObject myElementInstance;
    public IntelligentObjectRunSpace parentObjectRunSpace;


    private MayApplication mayApplication;

    public AbsBaseRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                           MayApplication application, AbsIntelligentPropertyObject intelligentObject) {

    }


    public String getHierarchicalDisplayName() {
        return null;
    }

    public Event[] getEvents() {
        return events;
    }

    public void setEvents(Event[] events) {
        this.events = events;
    }

    public AbsIntelligentPropertyObject getAbsIntelligentObjectProperty() {
        return myElementInstance;
    }

    public void setAbsIntelligentObjectProperty(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        this.myElementInstance = absIntelligentPropertyObject;
    }

    public void Clear(Enum34 enum34) {

    }

    public MayApplication getMayApplication() {
        return mayApplication;
    }

    public void setMayApplication(MayApplication mayApplication) {
        this.mayApplication = mayApplication;
    }


    public void Initialize(boolean initializeState, boolean initializeStatistics) {
        for (AbsBaseTrace absBaseTrace : this.absBaseTraces) {
            if (initializeState) {
                absBaseTrace.InitializeState();
            }
            if (initializeStatistics) {
                absBaseTrace.InitializeStatistics();
            }
        }
    }

    public boolean IsAgentPopulationMember() {
        return false;
    }

    public boolean IsAgentPopulationMemberOnFreeList() {
        return false;
    }

    public boolean IsAgentPopulationStaticParent() {
        return false;
    }

    public boolean CanSkipWaitingForEvent(TokenRunSpace tokenRunSpace, Event event) {
        return false;
    }

    public String Name() {
        return this.myElementInstance.InstanceName();
    }

    public String HierarchicalName() {
        if (this.ParentObjectRunSpace == null || this.ParentObjectRunSpace.parentEmpty()) {
            return this.Name();
        }
        return this.ParentObjectRunSpace.HierarchicalName() + "." + this.Name();
    }

    public String DisplayName() {
        return this.Name();
    }

    public String HierarchicalDisplayName() {
        if (this.ParentObjectRunSpace == null || this.ParentObjectRunSpace.parentEmpty()) {
            return this.DisplayName();
        }
        return this.ParentObjectRunSpace.HierarchicalDisplayName() + "." + this.DisplayName();
    }

    public String Description() {
        return this.myElementInstance.Description();
    }

    public String ConstraintDescription() {
        if (Strings.isNullOrEmpty(this.Description())) {
            return this.myElementInstance.objectDefinition.Name();
        }
        return this.Description();
    }


    public String getDisplayName() {
        String name = this.Name();
        String displayName = this.DisplayName();
        if (!Objects.equals(name, displayName) && displayName != null) {
            return MessageFormat.format("{0} ({1})", displayName, name);
        }
        return name;
    }

    public UnitType DynamicUnits() {
        return UnitType.Unspecified;
    }

    public IntelligentObjectRunSpace ContextObjectRunSpace() {
        return this.ParentObjectRunSpace;
    }

    public IntelligentObjectRunSpace AssociatedObjectRunSpace() {
        return null;
    }

    public IntelligentObjectRunSpace ParentObjectRunSpace() {
        return this.ParentObjectRunSpace;
    }

    public AbsIntelligentPropertyObject MyElementInstance() {
        return this.myElementInstance;
    }

    public TableRowReferences GetTableRowReferences() {
        return this.tableRowReferences;
    }


    public boolean TraceFlag() {
        return this.getMayApplication().TraceFlag();
    }

    public boolean parentEmpty() {
        return this.ParentObjectRunSpace == null;
    }

    public SomeRun getSomeRun() {
        return this.mayApplication.getSomeRun();
    }

    public void NotifySizeChanging(double length, double width, double height) {
    }

    public void setApplication(MayApplication mayApplication) {
        this.mayApplication = mayApplication;
    }

    public void runtimeErrorHandler(IRunSpace runSpace, AbsPropertyObject absPropertyObject,
                                    IntelligentObjectProperty intelligentObjectProperty, String error) {
        this.runtimeErrorHandler(this.getSomeRun().TimeNow(), runSpace, absPropertyObject, intelligentObjectProperty,
                error);
    }

    public void runtimeErrorHandler(double timeNow, IRunSpace runSpace, AbsPropertyObject absPropertyObject,
                                    IntelligentObjectProperty intelligentObjectProperty, String error) {
        this.getMayApplication().runtimeErrorHandler(timeNow, runSpace, absPropertyObject, intelligentObjectProperty,
                error);
    }

    public StatisticsDataSourceInfo GetStatisticsDataSourceInfo(String name) {
        StatisticsDataSourceInfo result = new StatisticsDataSourceInfo();
        IntelligentObjectRunSpace intelligentObjectRunSpace = this.GetStatisticsDataSourceIntelligentObject();
        if (intelligentObjectRunSpace.ParentObjectRunSpace == null) {
            result.contextObjectName = intelligentObjectRunSpace.myElementInstance.DefinitionName();
        } else {
            result.contextObjectName = intelligentObjectRunSpace.Name();
        }
        String text;
        if (name != null) {
            if (intelligentObjectRunSpace.ParentObjectRunSpace != null) {
                text = intelligentObjectRunSpace.Name() + " " + name;
            } else {
                text = name;
            }
            result.name = name;
        } else {
            if (intelligentObjectRunSpace != intelligentObjectRunSpace.ParentObjectRunSpace && intelligentObjectRunSpace.ParentObjectRunSpace != null) {
                text = intelligentObjectRunSpace.Name() + "." + this.Name();
            } else {
                text = this.Name();
            }
            result.name = this.Name();
        }
        result.propertyDefinitionName = intelligentObjectRunSpace.myElementInstance.objectDefinition.Name();
        IntelligentObjectRunSpace objectRunSpace;
        if (intelligentObjectRunSpace.ParentObjectRunSpace != null) {
            objectRunSpace = intelligentObjectRunSpace.ParentObjectRunSpace;
        } else {
            objectRunSpace = intelligentObjectRunSpace;
        }
        if (objectRunSpace != intelligentObjectRunSpace) {
            while (objectRunSpace != null && objectRunSpace.ParentObjectRunSpace != null) {
                result.name = text;
                result.contextObjectName = objectRunSpace.Name();
                result.propertyDefinitionName = objectRunSpace.myElementInstance.objectDefinition.Name();
                text = result.contextObjectName + "." + text;
                objectRunSpace = objectRunSpace.ParentObjectRunSpace;
            }
        }
        return result;
    }

    public IntelligentObjectRunSpace GetStatisticsDataSourceIntelligentObject() {
        return this.ParentObjectRunSpace;
    }


    @Override
    public void traceMethod(TokenRunSpace tokenRunSpace, Enum42 enum42, String param) {
        this.traceMethod(this.getSomeRun().TimeNow(), tokenRunSpace, enum42, param);
    }

    @Override
    public void traceMethod(double timeNow, TokenRunSpace tokenRunSpace, Enum42 enum42, String param) {
        this.getMayApplication().traceMethod(timeNow, tokenRunSpace, enum42, param);

    }

    @Override
    public void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning,
                                      double param2, TokenRunSpace tokenRunSpace, String param4, String param5,
                                      String param6) {
        this.getMayApplication().runtimeWarningHandler(intelligentObjectRunSpace, warning, param2, tokenRunSpace,
                param4, param5, param6);
    }

    @Override
    public void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning,
                                      TokenRunSpace tokenRunSpace, String param3, String param4, String param5) {
        this.runtimeWarningHandler(intelligentObjectRunSpace, warning, this.getSomeRun().TimeNow(), tokenRunSpace,
                param3, param4, param5);
    }

    public void GetReportedStatistics(IAboutReport aboutReport) {
        if (this.ExpressionResultPosive()) {
            for (AbsBaseTrace absBaseTrace : this.absBaseTraces) {
                if (this.ReportStateStatistics(absBaseTrace)) {
                    absBaseTrace.GetReportedStatistics(aboutReport);
                }
            }
        }
    }

    private boolean ReportStateStatistics(AbsBaseTrace absBaseTrace) {
        return true;
    }

    private boolean ExpressionResultPosive() {
        return this.myElementInstance.ExpressionResultPosive(this.ParentObjectRunSpace);
    }

}
