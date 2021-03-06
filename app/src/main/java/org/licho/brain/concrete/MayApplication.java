package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.fixed.FixedRunSpace;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.ConstraintType;
import org.licho.brain.enu.Enum42;
import org.licho.brain.enu.UnitType;
import org.licho.brain.utils.simu.ITrace;

import java.text.MessageFormat;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class MayApplication implements ITrace {

    public final ProfileRecordWrapper ProfileRecords;
    private boolean traceFlag;
    private ActiveModel activeModel;
    private SomeRun someRun;
    private FixedRunSpace fixedRunSpace;
    private boolean changeNaNToZeroInExpressions;
    private boolean disableRandomness;
    private Map<Class<?>, Cache> cache = new HashMap<>();
    private ISimulationRun simulationRun;
    private ConstraintLogWrapper constraintLogWrapper;
    private NodeEntryAssociatedObjectRunSpaceOperator nodeEntryAssociatedObjectRunSpaceOperator;
    private boolean isFastForward;
    private int int_4;
    private int numberOfTokensInSystem;
    private boolean bool_2;
    private Map<Breakpoint, BreakpointExpressionWrapper> breakpointExpressionWrappersMap = new HashMap<>();
    private final ProcessPropertyElementRunSpace[] processPropertyElementRunSpaces =
            new ProcessPropertyElementRunSpace[201];
    private int processPropertyElementLastIndex;
    private Supplier<Boolean> func_0;

    public MayApplication(ActiveModel activeModel, RunSetup runSetup, int specificReplicationNumber,
                          IntelligentObject intelligentObject,
                          ISimulationRun simulationRun, ActiveModel.RunModel runModel) {
        this(activeModel, runSetup, specificReplicationNumber, intelligentObject, simulationRun, runModel, null, null
                , null, null, null, null, null, null,
                false);

    }

    public MayApplication(ActiveModel activeModel, RunSetup runSetup, int specificReplicationNumber,
                          IntelligentObject intelligentObject
            , ISimulationRun simulationRun, ActiveModel.RunModel runModel,
                          ResourceUsageLogWrapper resourceUsageLogWrapper,
                          ResourceStateLogWrapper resourceStateLogWrapper,
                          ResourceCapacityLogWrapper resourceCapacityLogWrapper,
                          ConstraintLogWrapper constraintLogWrapper,
                          TransporterUsageLogWrapper transporterUsageLogWrapper,
                          StateObservationLogWrapper stateObservationLogWrapper,
                          TallyObservationLogWrapper tallyObservationLogWrapper,
                          MaterialUsageLogWrapper materialUsageLogWrapper, boolean param14) {

        this.ProfileRecords = null;
    }

    public boolean IsFastForward() {
        return this.isFastForward;
    }


    public void IsFastForward(boolean value) {
        this.isFastForward = value;
    }


    public ActiveModel ActiveModel() {
        return this.activeModel;
    }

    public Cache getCache(Class<?> type) {
        Cache result = this.cache.get(type);
        return result;
    }

    @Override
    public boolean TraceFlag() {
        return this.traceFlag || this.ActiveModel().TraceFlag();
    }


    public void TraceFlag(boolean value) {
        this.traceFlag = value;
    }

    @Override
    public void runtimeErrorHandler(IRunSpace param0, AbsPropertyObject param1, IntelligentObjectProperty param2,
                                    String param3) {

    }

    @Override
    public void runtimeErrorHandler(double param0, IRunSpace param1, AbsPropertyObject param2,
                                    IntelligentObjectProperty param3, String param4) {

    }

    public SomeRun getSomeRun() {
        return this.someRun;
    }

    public FixedRunSpace getFixedRunSpace() {
        return this.fixedRunSpace;
    }

    public boolean isChangeNaNToZeroInExpressions() {
        return this.changeNaNToZeroInExpressions;
    }

    public boolean DisableRandomness() {
        return this.disableRandomness;
    }

    public UnitConversions UnitConversions() {
        if (this.ActiveModel() != null) {
            return this.ActiveModel().getRunSetup().UnitConversions();
        }
        if (this.simulationRun != null) {
            return this.simulationRun.UnitConversions();
        }
        return null;
    }

    public double getInitValue(double unitCarry, UnitType unitType) {
        if (this.ActiveModel() == null) {
            return unitCarry;
        }

        var runSetup = this.ActiveModel().getRunSetup();
        return switch (unitType) {
            case Time -> runSetup.getTimeInitValue(unitCarry);
            case TravelRate -> runSetup.getTravelRateInitValue(unitCarry);
            case Length -> runSetup.getLengthInitValue(unitCarry);
            case Currency -> runSetup.getCurrencyInitValue(unitCarry);
            case CurrencyPerTimeUnit -> runSetup.getCurrencyPerTimeUnitInitValue(unitCarry);
            case Volume -> runSetup.getVolumeInitValue(unitCarry);
            case Weight -> runSetup.getWeightinitValue(unitCarry);
            case VolumeFlowRate -> runSetup.getVolumeFlowRateInitValue(unitCarry);
            case WeightFlowRate -> runSetup.getWeightFlowRateinitValue(unitCarry);
            case TravelAcceleration -> runSetup.getTravelAccelerationInitValue(unitCarry);
            default -> unitCarry;
        };
    }

    public String getUnitDescription(UnitType unitType) {
        if (this.ActiveModel() != null) {
            final RunSetup runSetup = this.ActiveModel().getRunSetup();
            return switch (unitType) {
                case Time -> runSetup.getTimeUnitDescription();
                case TravelRate -> runSetup.getTravelRateUnitDescription();
                case Length -> runSetup.getLengthUnitDescription();
                case Currency -> runSetup.getCurrencyUnitDescription();
                case CurrencyPerTimeUnit -> runSetup.getCurrencyPerTimeUnitUnitDescription();
                case Volume -> runSetup.getVolumeUnitDescription();
                case Weight -> runSetup.getWeightUnitDescription();
                case VolumeFlowRate -> runSetup.getVolumeFlowRateUnitDescription();
                case WeightFlowRate -> runSetup.getWeightFlowRateUnitDescription();
                case TravelAcceleration -> runSetup.getTravelAccelerationUnitDescription();
                default -> "";
            };
        } else {
            return switch (unitType) {
                case Time -> AboutUnit.getUnitPerDescription(UnitType.Time, 0);
                case TravelRate -> AboutUnit.getUnitPerDescription(UnitType.TravelRate, 0);
                case Length -> AboutUnit.getUnitPerDescription(UnitType.Length, 0);
                case Currency -> AboutUnit.getUnitPerDescription(UnitType.Currency, CurrencyWrapper.unitType);
                case CurrencyPerTimeUnit -> AboutUnit.getUnitPerDescription(UnitType.CurrencyPerTimeUnit,
                        CurrencyWrapper.level);
                case Volume -> AboutUnit.getUnitPerDescription(UnitType.Volume, 0);
                case Weight -> AboutUnit.getUnitPerDescription(UnitType.Weight, 0);
                case VolumeFlowRate -> AboutUnit.getUnitPerDescription(UnitType.VolumeFlowRate,
                        AboutUnit.getUnitTypeIndex(0, 0));
                case WeightFlowRate -> AboutUnit.getUnitPerDescription(UnitType.WeightFlowRate,
                        AboutUnit.getUnitTypeIndex(0, 0));
                case TravelAcceleration -> AboutUnit.getUnitPerDescription(UnitType.TravelAcceleration,
                        AboutUnit.getUnitTypeIndex(0,
                        0));
                default -> "";
            };
        }
    }

    @Override
    public void traceMethod(TokenRunSpace tokenRunSpace, Enum42 enum42, String param2) {
        this.traceMethod(this.getSomeRun().TimeNow(), tokenRunSpace, enum42, param2);
    }

    @Override
    public void traceMethod(double timeNow, TokenRunSpace tokenRunSpace, Enum42 enum42, String param) {
        // TODO: 2022/1/17 
    }

    public Object getConstraintRecord(IntelligentObjectRunSpace intelligentObjectRunSpace,
                                      ConstraintType constraintType, String runspaceName, String description) {
        if (this.constraintLogWrapper != null) {
            return this.constraintLogWrapper.getConstraintRecord((EntityRunSpace) intelligentObjectRunSpace,
                    constraintType,
                    runspaceName, description, this.getSomeRun().getNowTime());
        }
        return null;
    }

    public void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning,
                                      TokenRunSpace tokenRunSpace, String param3, String param4, String param5) {
        this.runtimeWarningHandler(intelligentObjectRunSpace, warning, this.getSomeRun().TimeNow(), tokenRunSpace,
                param3, param4, param5);
    }

    public void runtimeWarningHandler(IntelligentObjectRunSpace intelligentObjectRunSpace, Warning warning,
                                      double param2, TokenRunSpace tokenRunSpace, String param4, String param5,
                                      String param6) {
        // TODO: 2022/1/20 
    }

    public void method_16(Object constraintRecord) {
        if (this.constraintLogWrapper != null) {
            this.constraintLogWrapper.method_1(constraintRecord, this.getSomeRun().getNowTime());
        }
    }

    public NodeEntryAssociatedObjectRunSpaceOperator getNodeEntryAssociatedObjectRunSpaceOperator() {
        if (this.nodeEntryAssociatedObjectRunSpaceOperator == null) {
            this.nodeEntryAssociatedObjectRunSpaceOperator = new NodeEntryAssociatedObjectRunSpaceOperator();
        }
        return this.nodeEntryAssociatedObjectRunSpaceOperator;
    }

    public void method_20() {
        this.getSomeRun().method_21();
        this.int_4 = 0;
        this.getFixedRunSpace().method_45();
        for (BreakpointExpressionWrapper breakpointExpressionWrapper : this.breakpointExpressionWrappersMap.values()) {
            breakpointExpressionWrapper.count = 0;
        }
        if (this.getFixedRunSpace().TraceFlag()) {
            this.getFixedRunSpace().traceMethod(null, Enum42.Zero, EngineResources.Trace_SystemInitializationCompleted);
        }
    }

    public void numberOfTokensInSystemIncrement() {
        this.numberOfTokensInSystem++;
        if (this.numberOfTokensInSystem == 100000) {
            this.method_61();
        }
    }

    public void numberOfTokensInSystemDecrement() {
        this.numberOfTokensInSystem--;
    }

    public int getNumberOfTokensInSystem() {
        return this.numberOfTokensInSystem;
    }

    private void method_61() {
        if (!this.bool_2) {
            this.runtimeWarningHandler(null, new Warning("Model_HighNumberOfTokensInSystem"), null,
                    MessageFormat.format(EngineResources.Warning_Model_HighNumberOfTokensInSystem_Heading, 100000),
                    EngineResources.Warning_Model_HighNumberOfTokensInSystem_Content, this.getTokenCountMessage());
            this.bool_2 = true;
        }
    }


    private String getTokenCountMessage() {
        // TODO: 2022/2/7 
        return "Top token counts by process definition location:\\n{0}\\n\\nTop token counts by process runtime " +
                "location:\\n{1}\\n";
    }


    public void addProcessPropertyElementRunSpace(TokenRunSpace tokenRunSpace,
                                                  ProcessPropertyElementRunSpace processPropertyElementRunSpace) {
        this.processPropertyElementRunSpaces[this.processPropertyElementLastIndex] = processPropertyElementRunSpace;
        this.processPropertyElementLastIndex++;
        if (this.processPropertyElementLastIndex >= 200) {
            RuntimeErrorFullMessageDetails.reportError(tokenRunSpace,
                    MessageFormat.format(EngineResources.Error_ProcessStackOverflow,
                            IntStream.range(this.processPropertyElementLastIndex - 10,
                                            this.processPropertyElementLastIndex)
                                    .boxed()
                                    .sorted(Collections.reverseOrder())
                                    .map(i -> this.processPropertyElementRunSpaces[i])
                                    .map(ProcessPropertyElementRunSpace::HierarchicalDisplayName)
                                    .collect(Collectors.joining(System.lineSeparator())))
            );
        }
    }

    public RunOperatorWrapper getRunOperatorWrapper() {
        return this.getSomeRun().getRunOperatorWrapper();
    }

    public void removeLastProcessPropertyElement() {
        this.processPropertyElementLastIndex--;
        this.processPropertyElementRunSpaces[this.processPropertyElementLastIndex] = null;
    }

    public boolean method_3() {
        return this.func_0 == null || this.func_0.get();
    }
}
