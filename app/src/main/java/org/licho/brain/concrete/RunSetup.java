package org.licho.brain.concrete;


import com.google.common.base.Strings;
import org.licho.brain.concrete.fake.TimeSpan;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.enu.RunEndType;
import org.licho.brain.enu.StatisticConfidenceIntervalType;
import org.licho.brain.enu.UnitType;
import org.licho.brain.enu.WarningNotificationLevel;
import org.licho.brain.utils.simu.system.DateTime;
import org.licho.brain.utils.simu.system.ModelLoadAction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.xml.stream.XMLStreamConstants;

/**
 *
 */
public class RunSetup {
    // Class625
    private static Logger logger = LoggerFactory.getLogger(XmlReader.class);


    private final double double_0 = 1E-09;
    public final int int_0 = 32;

    private final int int_1 = 10;
    final double double_1 = 1.0;
    private final int int_2 = 0;
    private final String String_0 = "RunSetup";
    private ActiveModel activeModel;
    private double frameInterval = 2.777777777777778E-05;
    private double endTimeHours;
    private int endTimeUnitSubType;
    private boolean isExecuteRealTime;
    private double realTimeScale = 1.0;
    private double realTimeRunLength = 24.0;
    private DateTime startTime;
    private DateTime endTime;
    private RunEndType runEndType;
    private int calendarEventsBetweenCallbacksForInteractiveRun = 32;

    private CompatibilitySettings compatibilitySettings;
    private boolean disableRandomness;
    private int specificReplicationNumber = 1;
    private ModelLoadAction loadAction;
    private StatisticConfidenceIntervalType riskAnalysisConfidenceLevel = StatisticConfidenceIntervalType.Point95;
    private int int_6 = 10;
    private int riskAnalysisConcurrentReplicationLimit;
    private WarningNotificationLevel warningNotificationLevel = WarningNotificationLevel.ShowPopup;
    private boolean logInteractiveRuntimeData;
    private boolean generateProfileReport;
    private double updateInterval = 1.0;
    private int updateIntervalTimeLevel;
    private UnitConversions unitConversions = new UnitConversions();

    private int timeLevel;
    private int lengthLevel;
    private int travelRateLevel;
    private int travelAccelerationLevel;
    private int currencyLevel;
    private int currencyPerTimeUnitLevel;
    private int volumeLevel;
    private int volumeFlowRateLevel;
    private int weightLevel;
    private int weightFlowRateLevel;
    private String timeUnitDescription;
    private String lengthUnitDescription;
    private String travelRateUnitDescription;
    private String travelAccelerationUnitDescription;
    private String currencyUnitDescription;
    private String currencyPerTimeUnitUnitDescription;
    private String volumeUnitDescription;
    private String volumeFlowRateUnitDescription;
    private String weightUnitDescription;
    private String weightFlowRateUnitDescription;
    private Double timeInitValue = Double.NaN;
    private Double lengthInitValue = Double.NaN;
    private Double travelRateInitValue = Double.NaN;
    private Double travelAccelerationInitValue = Double.NaN;
    private Double currencyInitValue = Double.NaN;
    private Double currencyPerTimeUnitInitValue = Double.NaN;
    private Double volumeInitValue = Double.NaN;
    private Double volumeFlowRateInitValue = Double.NaN;
    private Double WeightinitValue = Double.NaN;
    private Double weightFlowRateinitValue = Double.NaN;
    private int riskAnalysisReplications;

    public RunSetup(ActiveModel activeModel) {
        this.activeModel = activeModel;
        this.activeModel.runSetupList.add(this);
        this.runEndType = RunEndType.EndingTime;
        this.startTime = DateTime.Now();
        this.endTime = this.StartTime().AddDays(1.0);
        this.compatibilitySettings = new CompatibilitySettings(this);
    }

    public CompatibilitySettings CompatibilitySettings() {
        return this.compatibilitySettings;
    }

    public void CompatibilitySettings(CompatibilitySettings value) {
        this.compatibilitySettings = value;
        this.resetTable(255);
    }

    public RunEndType RunEndType() {
        return this.runEndType;
    }

    public void RunEndType(RunEndType value) {
        if (this.runEndType != value) {
            this.runEndType = value;
            if (this.activeModel.method_26()) {
                this.selectEndTime();
                this.activeModel.MayApplication.getSomeRun().method_9(value == RunEndType.Infinite);
            }
            this.resetTable(255);
        }
    }


    public int RiskAnalysisConcurrentReplicationLimit() {
        return this.riskAnalysisConcurrentReplicationLimit;
    }

    public void RiskAnalysisConcurrentReplicationLimit(int value) {
        this.riskAnalysisConcurrentReplicationLimit = value;
    }

    public WarningNotificationLevel WarningNotificationLevel() {
        return this.warningNotificationLevel;
    }

    public void WarningNotificationLevel(WarningNotificationLevel value) {
        this.warningNotificationLevel = value;
    }

    public boolean LogInteractiveRuntimeData() {
        return this.logInteractiveRuntimeData;
    }

    public void LogInteractiveRuntimeData(boolean value) {
        this.logInteractiveRuntimeData = value;
    }

    public boolean GenerateProfileReport() {
        return this.generateProfileReport;
    }

    public void GenerateProfileReport(boolean value) {
        this.generateProfileReport = value;
        this.resetTable(255);
    }

    public double UpdateInterval() {
        return this.updateInterval;
    }

    public void UpdateInterval(double value) {
        this.updateInterval = value;
    }

    public static DateTime calculateStartTime(DateTime dateTime) {
        //todo
        return dateTime;
    }

    public UnitConversions getUnitConversions() {
        return unitConversions;
    }

    public void setUnitConversions(UnitConversions unitConversions) {
        this.unitConversions = unitConversions;
    }

    public DateTime StartTime() {
        return this.startTime;
    }

    public void StartTime(DateTime value) {
        if (this.activeModel.canModify()) {
//            throw new InvalidOperationException();
            logger.error("illegal operator");
            return;
        }
        this.startTime = value;
        if (this.RunEndType() == RunEndType.EndingTime) {
            this.endTimeHours = (this.endTime.sub(this.startTime).TotalHours());
        } else {
            TimeSpan t = TimeSpan.FromHours(this.endTimeHours);
            this.endTime = this.startTime.add(t);
        }
        this.resetTable(255);
    }

    public DateTime EndTime() {
        return this.endTime;
    }

    public void EndTime(DateTime value) {
        this.endTime = value;
        this.endTimeHours = this.endTime.sub(this.startTime).Hours();
        if (this.activeModel.method_26()) {
            this.selectEndTime();
            this.activeModel.MayApplication.getSomeRun().setEndTime(this.endTime);
        }
        this.resetTable(255);
    }

    private void selectEndTime() {
        SomeRun someRun = this.activeModel.MayApplication.getSomeRun();
        if (this.EndTime().compare(someRun.getNowTime()) < 0) {
            this.EndTime(someRun.getNowTime());
        }
    }


    public UnitConversions UnitConversions() {
        return this.unitConversions;
    }

    public boolean readXMlRunSetup(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "RunSetup", attr -> {
            this.compatibilitySettings.init(intelligentObjectXml);
            this.RunEndType(RunEndType.Infinite);
            SomeXmlOperator.readXmlDoubleAttribute(attr, "AnimationInterval", d -> this.FrameInterval(d / 36000.0));
            SomeXmlOperator.readXmlIntAttribute(attr, "CalendarEventsBetweenCallbacksForInteractiveRun",
                    i -> this.calendarEventsBetweenCallbacksForInteractiveRun = i
            );

            String statusTimeUnits = attr.GetAttribute("StatusTimeUnits");
            if (!Strings.isNullOrEmpty(statusTimeUnits)) {
                this.initUnitTime(statusTimeUnits);
            }
            String statusLengthUnits = attr.GetAttribute("StatusLengthUnits");
            if (!Strings.isNullOrEmpty(statusLengthUnits)) {
                int level = AboutUnit.getUnitLevel(UnitType.Length, statusLengthUnits);
                if (level != -1) {
                    this.initUnitLength(level);
                }
            }
            String statusRateUnits = attr.GetAttribute("StatusRateUnits");
            if (!Strings.isNullOrEmpty(statusLengthUnits)) {
                int level = AboutUnit.getUnitLevel(UnitType.TravelRate, statusRateUnits);
                if (level != -1) {
                    this.initUnitTravelRate(level);
                }
            }
            String statusVolumeUnits = attr.GetAttribute("StatusVolumeUnits");
            if (!Strings.isNullOrEmpty(statusVolumeUnits)) {
                int level = AboutUnit.getUnitLevel(UnitType.Volume, statusVolumeUnits);
                if (level != -1) {
                    this.initUnitVolume(level);
                }
            }
            String statusVolumeFlowRateUnits = attr.GetAttribute("StatusVolumeFlowRateUnits");
            if (!Strings.isNullOrEmpty(statusVolumeFlowRateUnits)) {
                int level = AboutUnit.getUnitLevel(UnitType.VolumeFlowRate, statusVolumeFlowRateUnits);
                if (level != -1) {
                    this.initUnitVolumeFlowRate(level);
                }
            }
            String statusMassUnits = attr.GetAttribute("StatusMassUnits");
            if (!Strings.isNullOrEmpty(statusMassUnits)) {
                int level = AboutUnit.getUnitLevel(UnitType.Weight, statusMassUnits);
                if (level != -1) {
                    this.initUnitWeight(level);
                }
            }
            String statusMassFlowRateUnits = attr.GetAttribute("StatusMassFlowRateUnits");
            if (!Strings.isNullOrEmpty(statusMassFlowRateUnits)) {
                int level = AboutUnit.getUnitLevel(UnitType.WeightFlowRate, statusMassFlowRateUnits);
                if (level != -1) {
                    this.initUnitWeightFlowRate(level);
                }
            }
            this.initUnitCurrency();
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "DisableRandomness", this::DisableRandomness);
            SomeXmlOperator.readXmlIntAttribute(xmlReader, "ReplicationNumber", this::SpecificReplicationNumber);
            SomeXmlOperator.readEnumAttribute(xmlReader, "RiskAnalysisConfidenceLevel",
                    this::RiskAnalysisConfidenceLevel, StatisticConfidenceIntervalType.class);
            SomeXmlOperator.readXmlIntAttribute(xmlReader, "RiskAnalysisConcurrentReplicationLimit",
                    this::RiskAnalysisConcurrentReplicationLimit);
            SomeXmlOperator.readEnumAttribute(xmlReader, "WarningNotificationLevel", this::WarningNotificationLevel
                    , WarningNotificationLevel.class);
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "LogInteractiveRuntimeData",
                    this::LogInteractiveRuntimeData);
            SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "GenerateProfileReport", this::GenerateProfileReport);
            SomeXmlOperator.readEnumAttribute(xmlReader, "LoadAction", this::LoadAction, ModelLoadAction.class);
        }, body ->
                SomeXmlOperator.xmlReaderElementOperator(body, "StartDate", null, dateBody -> {
                    if (dateBody.NodeType() == XMLStreamConstants.CHARACTERS) {
                        String s = dateBody.getText();
                        DateTime minValue = DateTime.TryParse(s);
                        if (minValue != null) {
                            this.StartTime(minValue);
                        }
                        return true;
                    }
                    return false;
                }) || SomeXmlOperator.xmlReaderElementOperator(body, "EndDate", null, dateBody -> {
                    if (dateBody.NodeType() == XMLStreamConstants.CHARACTERS) {
                        String s = dateBody.getText();
                        DateTime minValue = DateTime.TryParse(s);
                        if (minValue != null) {
                            this.RunEndType(RunEndType.EndingTime);
                            this.EndTime(minValue);
                        }
                        return true;
                    }
                    return false;
                }) || SomeXmlOperator.xmlReaderElementOperator(body, "RunLength", lengthAttr -> {
                    this.RunEndType(RunEndType.RunLength);
                    String attribute = lengthAttr.GetAttribute("Units");
                    if (!Strings.isNullOrEmpty(attribute)) {
                        this.EndTimeUnitSubType(AboutUnit.getUnitLevel(UnitType.Time, attribute));
                    }
                }, lengthBody -> {
                    if (lengthBody.NodeType() == XMLStreamConstants.CHARACTERS) {
                        String s = lengthBody.getText();
                        try {
                            var value = Double.parseDouble(s);
                            this.EndTimeHours(AboutUnit.smethod_4(UnitType.Time, this.EndTimeUnitSubType(),
                                    this.UnitConversions(), value));
                        } catch (NumberFormatException e) {
                            logger.error(e.toString());
                        }
                        return true;
                    }
                    return false;
                }) || SomeXmlOperator.xmlReaderElementOperator(body, "UpdateInterval", updateIntervalAttr -> {
                    String attribute = updateIntervalAttr.GetAttribute("Units");
                    if (!Strings.isNullOrEmpty(attribute)) {
                        this.setUpdateIntervalTimeLevel(AboutUnit.getUnitLevel(UnitType.Time, attribute));
                    }
                }, updateIntervalBody -> {
                    if (updateIntervalBody.NodeType() == XMLStreamConstants.CHARACTERS) {
                        String s = xmlReader.getText();
                        if (!Strings.isNullOrEmpty(s)) {
                            try {
                                double updateInterval = Double.parseDouble(s);
                                this.UpdateInterval(updateInterval);
                            } catch (NumberFormatException e) {
                                logger.error(e.toString());
                            }
                        }
                        return true;
                    }
                    return false;
                }) || (this.unitConversions != null && this.unitConversions.readXml(body, intelligentObjectXml)) ||
                        (this.compatibilitySettings != null && this.compatibilitySettings.readXml(body,
                                intelligentObjectXml)));
    }


    public int getUpdateIntervalTimeLevel() {
        return this.updateIntervalTimeLevel;
    }

    private void setUpdateIntervalTimeLevel(int unitLevel) {
        this.updateIntervalTimeLevel = unitLevel;
    }

    public int EndTimeUnitSubType() {
        return this.endTimeUnitSubType;
    }

    public void EndTimeUnitSubType(int value) {
        if (this.endTimeUnitSubType != value) {
            double endTimeHours = AboutUnit.smethod_5(UnitType.Time, this.endTimeUnitSubType, null,
                    this.EndTimeHours());
            this.endTimeUnitSubType = value;
            this.EndTimeHours(AboutUnit.smethod_4(UnitType.Time, this.endTimeUnitSubType, null, endTimeHours));
            this.resetTable(255);
        }
    }

    public double EndTimeHours() {
        return this.endTimeHours;
    }

    public void EndTimeHours(double value) {
        this.endTimeHours = value;
        this.EndTime(this.StartTime().add(TimeSpan.FromHours(this.endTimeHours)));
        this.resetTable(255);
    }


    public ModelLoadAction LoadAction() {
        return this.loadAction;
    }

    public void LoadAction(ModelLoadAction value) {
        this.loadAction = value;
        this.resetTable(255);
    }

    public void resetTableAndSet(int replications) {
        this.riskAnalysisReplications = Math.max(1, replications);
        this.resetTable(8);
    }

    public StatisticConfidenceIntervalType RiskAnalysisConfidenceLevel() {
        return this.riskAnalysisConfidenceLevel;
    }

    public void RiskAnalysisConfidenceLevel(StatisticConfidenceIntervalType value) {
        this.riskAnalysisConfidenceLevel = value;
        if (this.activeModel != null && this.activeModel.getIntelligentObjectDefinition() != null) {
            for (Table table : this.activeModel.getIntelligentObjectDefinition().Tables().values) {
                table.resetTable();
            }
        }
        this.resetTable(8);
    }

    public int SpecificReplicationNumber() {
        return this.specificReplicationNumber;
    }

    private void SpecificReplicationNumber(int value) {
        this.specificReplicationNumber = value;
        this.resetTable(255);
    }

    public boolean DisableRandomness() {
        return this.disableRandomness;
    }

    public void DisableRandomness(boolean value) {
        this.disableRandomness = value;
        this.resetTable(255);
    }

    private void resetTable(int resultType) {
        if (this.activeModel != null && this.activeModel.getIntelligentObjectDefinition() != null) {
            this.activeModel.getIntelligentObjectDefinition().resetTable(resultType);
        }
    }


    public double FrameInterval() {
        return Math.max(1E-09, this.frameInterval);
    }

    public void FrameInterval(double value) {
        this.frameInterval = Math.max(1E-09, value);
    }


    public int getTimeLevel() {
        return this.timeLevel;
    }

    public void initUnitTime(int level1) {
        this.timeLevel = level1;
        this.timeUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Time, this.timeLevel);
        this.timeInitValue = Double.NaN;
        this.initUnitCurrency();
        this.activeModel.resetDataBindings();
    }

    public int getLength() {
        return this.lengthLevel;
    }

    public void initUnitLength(int level1) {
        this.lengthLevel = level1;
        this.lengthUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Length, this.lengthLevel);
        this.lengthInitValue = Double.NaN;
        this.activeModel.resetDataBindings();
    }

    public int getTravelRateLevel() {
        return this.travelRateLevel;
    }

    public void initUnitTravelRate(int level1) {
        this.travelRateLevel = level1;
        this.travelRateUnitDescription = AboutUnit.getUnitPerDescription(UnitType.TravelRate, this.travelRateLevel);
        this.travelRateInitValue = Double.NaN;
        this.activeModel.resetDataBindings();
        this.initUnitTravelAcceleration(level1);
    }

    public int getTravelAccelerationLevel() {
        return this.travelAccelerationLevel;
    }

    private void initUnitTravelAcceleration(int level) {
        this.travelAccelerationLevel = level;
        this.travelAccelerationUnitDescription = AboutUnit.getUnitPerDescription(UnitType.TravelAcceleration,
                this.travelAccelerationLevel);
        this.travelAccelerationInitValue = Double.NaN;
        this.activeModel.resetDataBindings();
    }

    public int getCurrencyLevel() {
        return this.currencyLevel;
    }

    public void initUnitCurrency(int level1) {
        this.currencyLevel = level1;
        this.currencyUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Currency, this.currencyLevel);
        this.currencyInitValue = Double.NaN;
        this.activeModel.resetDataBindings();
    }

    public int getCurrencyPerTimeUnit() {
        return this.currencyPerTimeUnitLevel;
    }

    public void initUnitCurrencyPerTimeUnit(int level1) {
        this.currencyPerTimeUnitLevel = level1;
        this.currencyPerTimeUnitUnitDescription = AboutUnit.getUnitPerDescription(UnitType.CurrencyPerTimeUnit,
                this.currencyPerTimeUnitLevel);
        this.currencyPerTimeUnitInitValue = Double.NaN;
        this.activeModel.resetDataBindings();
    }

    public int getVolumeLevel() {
        return this.volumeLevel;
    }

    public void initUnitVolume(int level1) {
        this.volumeLevel = level1;
        this.volumeUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Volume, this.volumeLevel);
        this.volumeInitValue = Double.NaN;
        this.activeModel.resetDataBindings();
    }

    public int getVolumeFlowRateLevel() {
        return this.volumeFlowRateLevel;
    }

    public void initUnitVolumeFlowRate(int level) {
        this.volumeFlowRateLevel = level;
        this.volumeFlowRateUnitDescription = AboutUnit.getUnitPerDescription(UnitType.VolumeFlowRate,
                this.volumeFlowRateLevel);
        this.volumeFlowRateInitValue = Double.NaN;
        this.activeModel.resetDataBindings();
    }

    public int getWeightLevel() {
        return this.weightLevel;
    }

    public void initUnitWeight(int level) {
        this.weightLevel = level;
        this.weightUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Weight, this.weightLevel);
        this.WeightinitValue = Double.NaN;
        this.activeModel.resetDataBindings();
    }

    public int getWeightFlowRateLevel() {
        return this.weightFlowRateLevel;
    }

    public void initUnitWeightFlowRate(int level) {
        this.weightFlowRateLevel = level;
        this.weightFlowRateUnitDescription = AboutUnit.getUnitPerDescription(UnitType.WeightFlowRate,
                this.weightFlowRateLevel);
        this.weightFlowRateinitValue = Double.NaN;
        this.activeModel.resetDataBindings();
    }

    public String getTimeUnitDescription() {
        if (this.timeUnitDescription == null) {
            this.timeUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Time, this.timeLevel);
        }
        return this.timeUnitDescription;
    }

    public void initUnitTime(String level) {
        this.initUnitTime(AboutUnit.getUnitLevel(UnitType.Time, level));
    }

    public String getLengthUnitDescription() {
        if (this.lengthUnitDescription == null) {
            this.lengthUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Length, this.lengthLevel);
        }
        return this.lengthUnitDescription;
    }

    public void initUnitLength(String level) {
        this.initUnitLength(AboutUnit.getUnitLevel(UnitType.Length, level));
    }

    public String getTravelRateUnitDescription() {
        if (this.travelRateUnitDescription == null) {
            this.travelRateUnitDescription = AboutUnit.getUnitPerDescription(UnitType.TravelRate, this.travelRateLevel);
        }
        return this.travelRateUnitDescription;
    }

    public void initUnitTravelRate(String level) {
        this.initUnitTravelRate(AboutUnit.getUnitLevel(UnitType.TravelRate, level));
    }

    public String getTravelAccelerationUnitDescription() {
        if (this.travelAccelerationUnitDescription == null) {
            this.travelAccelerationUnitDescription = AboutUnit.getUnitPerDescription(UnitType.TravelAcceleration,
                    this.travelRateLevel);
        }
        return this.travelAccelerationUnitDescription;
    }

    public void initUnitTravelAcceleration(String level) {
        this.initUnitTravelAcceleration(AboutUnit.getUnitLevel(UnitType.TravelAcceleration, level));
    }

    public String getCurrencyUnitDescription() {
        if (this.currencyUnitDescription == null) {
            this.currencyUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Currency, this.currencyLevel);
        }
        return this.currencyUnitDescription;
    }

    public void initUnitCurrency(String level) {
        this.initUnitCurrency(AboutUnit.getUnitLevel(UnitType.Currency, level));
    }

    public String getCurrencyPerTimeUnitUnitDescription() {
        if (this.currencyPerTimeUnitUnitDescription == null) {
            this.currencyPerTimeUnitUnitDescription = AboutUnit.getUnitPerDescription(UnitType.CurrencyPerTimeUnit,
                    this.currencyPerTimeUnitLevel);
        }
        return this.currencyPerTimeUnitUnitDescription;
    }

    public void initUnitCurrencyPerTimeUnit(String level) {
        this.initUnitCurrencyPerTimeUnit(AboutUnit.getUnitLevel(UnitType.CurrencyPerTimeUnit, level));
    }

    public String getVolumeUnitDescription() {
        if (this.volumeUnitDescription == null) {
            this.volumeUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Volume, this.volumeLevel);
        }
        return this.volumeUnitDescription;
    }

    public void initUnitVolume(String level) {
        this.initUnitVolume(AboutUnit.getUnitLevel(UnitType.Volume, level));
    }

    public String getVolumeFlowRateUnitDescription() {
        if (this.volumeFlowRateUnitDescription == null) {
            this.volumeFlowRateUnitDescription = AboutUnit.getUnitPerDescription(UnitType.VolumeFlowRate,
                    this.volumeFlowRateLevel);
        }
        return this.volumeFlowRateUnitDescription;
    }

    public void initUnitVolumeFlowRate(String level) {
        this.initUnitVolumeFlowRate(AboutUnit.getUnitLevel(UnitType.VolumeFlowRate, level));
    }

    public String getWeightUnitDescription() {
        if (this.weightUnitDescription == null) {
            this.weightUnitDescription = AboutUnit.getUnitPerDescription(UnitType.Weight, this.weightLevel);
        }
        return this.weightUnitDescription;
    }

    public void initUnitWeight(String level) {
        this.initUnitWeight(AboutUnit.getUnitLevel(UnitType.Weight, level));
    }

    public String getWeightFlowRateUnitDescription() {
        if (this.weightFlowRateUnitDescription == null) {
            this.weightFlowRateUnitDescription = AboutUnit.getUnitPerDescription(UnitType.WeightFlowRate,
                    this.weightFlowRateLevel);
        }
        return this.weightFlowRateUnitDescription;
    }

    public void initUnitWeightFlowRate(String level) {
        this.initUnitWeightFlowRate(AboutUnit.getUnitLevel(UnitType.WeightFlowRate, level));
    }

    public double getTimeInitValue(double unitCarry) {
        if (Double.isNaN(this.timeInitValue)) {
            this.timeInitValue = AboutUnit.getInitValue(UnitType.Time, this.timeLevel, this.UnitConversions());
        }
        return unitCarry * this.timeInitValue;
    }

    public double getLengthInitValue(double unitCarry) {
        if (Double.isNaN(this.lengthInitValue)) {
            this.lengthInitValue = AboutUnit.getInitValue(UnitType.Length, this.lengthLevel, this.UnitConversions());
        }
        return unitCarry * this.lengthInitValue;
    }

    public double getTravelRateInitValue(double value) {
        if (Double.isNaN(this.travelRateInitValue)) {
            this.travelRateInitValue = AboutUnit.getInitValue(UnitType.TravelRate, this.travelRateLevel,
                    this.UnitConversions());
        }
        return value * this.travelRateInitValue;
    }

    public double getTravelAccelerationInitValue(double unitCarry) {
        if (Double.isNaN(this.travelAccelerationInitValue)) {
            this.travelAccelerationInitValue = AboutUnit.getInitValue(UnitType.TravelAcceleration,
                    this.travelAccelerationLevel, this.UnitConversions());
        }
        return unitCarry * this.travelAccelerationInitValue;
    }

    public double getCurrencyInitValue(double unitCarry) {
        if (Double.isNaN(this.currencyInitValue)) {
            this.currencyInitValue = AboutUnit.getInitValue(UnitType.Currency, this.currencyLevel,
                    this.UnitConversions());
        }
        return unitCarry * this.currencyInitValue;
    }

    public double getCurrencyPerTimeUnitInitValue(double unitCarry) {
        if (Double.isNaN(this.currencyPerTimeUnitInitValue)) {
            this.currencyPerTimeUnitInitValue = AboutUnit.getInitValue(UnitType.CurrencyPerTimeUnit,
                    this.currencyPerTimeUnitLevel, this.UnitConversions());
        }
        return unitCarry * this.currencyPerTimeUnitInitValue;
    }

    public double getVolumeInitValue(double unitCarry) {
        if (Double.isNaN(this.volumeInitValue)) {
            this.volumeInitValue = AboutUnit.getInitValue(UnitType.Volume, this.volumeLevel, this.UnitConversions());
        }
        return unitCarry * this.volumeInitValue;
    }

    public double getVolumeFlowRateInitValue(double unitCarry) {
        if (Double.isNaN(this.volumeFlowRateInitValue)) {
            this.volumeFlowRateInitValue = AboutUnit.getInitValue(UnitType.VolumeFlowRate, this.volumeFlowRateLevel,
                    this.UnitConversions());
        }
        return unitCarry * this.volumeFlowRateInitValue;
    }

    public double getWeightinitValue(double unitCarry) {
        if (Double.isNaN(this.WeightinitValue)) {
            this.WeightinitValue = AboutUnit.getInitValue(UnitType.Weight, this.weightLevel, this.UnitConversions());
        }
        return unitCarry * this.WeightinitValue;
    }

    public double getWeightFlowRateinitValue(double unitCarry) {
        if (Double.isNaN(this.weightFlowRateinitValue)) {
            this.weightFlowRateinitValue = AboutUnit.getInitValue(UnitType.WeightFlowRate, this.weightFlowRateLevel,
                    this.UnitConversions());
        }
        return unitCarry * this.weightFlowRateinitValue;
    }

    public void initUnitCurrency() {
        int unitType = CurrencyWrapper.unitType;
        if (this.activeModel != null && this.activeModel.getIntelligentObjectDefinition() != null) {
            unitType =
                    this.activeModel.getIntelligentObjectDefinition().UnitFilter().CurrencyFilter().getUnitTypeIndex();
        }
        this.initUnitCurrency(unitType);
        this.initUnitCurrencyPerTimeUnit(AboutUnit.getUnitTypeIndex(unitType, this.getTimeLevel()));
    }

    public int CalendarEventsBetweenCallbacksForInteractiveRun() {
			return this.calendarEventsBetweenCallbacksForInteractiveRun;
    }

    public void CalendarEventsBetweenCallbacksForInteractiveRun(int value) {
        this.calendarEventsBetweenCallbacksForInteractiveRun = value;
    }
}
