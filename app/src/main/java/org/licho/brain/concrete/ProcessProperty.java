package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.entity.EnumPropertyRow;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.element.EventPropertyRow;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.exceptions.InvalidOperationException;
import org.licho.brain.simioEnums.ElementScope;
import org.licho.brain.utils.simu.IEntityProcess;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.IntStream;

/**
 *
 */
public class ProcessProperty extends AbsIntelligentPropertyObject {
    private static Logger logger = LoggerFactory.getLogger(ProcessProperty.class);
    public boolean isOverride;
    public ObjectClass objectClass;
    private boolean bExcludedFromLimits;
    public BaseStateIGridItemPropertyObject BaseSomeIGridItemProperties;
    public List<AbsBaseStepProperty> AbsBaseStepProperties;
    public int processIndex = -1;
    private String category;
    private int beginStepPropertyIndex = -1;
    private int endStepPropertyIndex = -1;
    private boolean excludedFromLimits;

    private TokenClassPropertyRow tokenClassProperty;

    private EventPropertyRow stringProperty;

    private EnumPropertyRow tokenActionOnAssociatedObjectDestroyed;

    private EnumPropertyRow tokenActionOnAssociatedObjectTransferRequested;

    private EnumPropertyRow initiallyEnabled;

    protected ProcessProperty(GridObjectDefinition definition, String name,
                              ElementScope scope) {
        super(definition, name, scope);
    }

    public TokenClassPropertyRow getTokenClassProperty() {
        return this.tokenClassProperty;
    }

    public EventPropertyRow getStringProperty() {
        return this.stringProperty;
    }

    public EnumPropertyRow getTokenActionOnAssociatedObjectDestroyed() {
        return this.tokenActionOnAssociatedObjectDestroyed;
    }

    public EnumPropertyRow getTokenActionOnAssociatedObjectTransferRequested() {
        return this.tokenActionOnAssociatedObjectTransferRequested;
    }

    public EnumPropertyRow isInitiallyEnabled() {
        return this.initiallyEnabled;
    }


    public ProcessProperty(String name, ElementScope elementScope) {
        super(ProcessDefinition.processDefinition, name, elementScope);
        this.AbsBaseStepProperties = new ArrayList<>(4);
        this.isOverride = false;
        this.BaseSomeIGridItemProperties = this.StateIGridItemPropertyObjectList.get(0);
    }


    @Override
    public AbsBaseRunSpace CreateRunSpaceWithPopulation(IntelligentObjectRunSpace sourceIntelligentObjectRunSpace,
                                                        MayApplication application) {
        return new ProcessPropertyElementRunSpace(sourceIntelligentObjectRunSpace, application, this);
    }

    public boolean ExcludedFromLimits() {
        return this.excludedFromLimits;
    }

    public void ExcludedFromLimits(boolean excludedFromLimits) {
        this.excludedFromLimits = excludedFromLimits;
    }

    public void clearIndexs() {
        this.beginStepPropertyIndex = -1;
        this.endStepPropertyIndex = -1;
    }

    @Override
    protected boolean InstanceNameChanging(String oldName, String newName) {
        if (super.Parent() != null) {
            List<IEntityProcess> entityProcesses = new ArrayList<>();
            super.Parent().GetInterfaceProcessInformation(entityProcesses);
            IEntityProcess process =
                    entityProcesses.stream().filter((IEntityProcess entityProcess) -> Objects.equals(entityProcess.Name(), newName))
                            .findFirst().orElse(null);
            if (process != null && (this.processIndex == -1 || process.Index() != this.processIndex)) {
                return false;
            }
        }
        return super.InstanceNameChanging(oldName, newName);
    }

    @Override
    protected int BindPropertyInstanceReferences(int index) {
        index = super.BindPropertyInstanceReferences(index);
        this.tokenClassProperty = (TokenClassPropertyRow) this.properties.get(index++);
        this.stringProperty = (EventPropertyRow) this.properties.get(index++);
        this.tokenActionOnAssociatedObjectDestroyed = (EnumPropertyRow) this.properties.get(index++);
        this.tokenActionOnAssociatedObjectTransferRequested = (EnumPropertyRow) this.properties.get(index++);
        this.initiallyEnabled = (EnumPropertyRow) this.properties.get(index++);
        return index;
    }

    @Override
    protected AbsBaseRunSpace GetRunSpaceOutOfParent(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return intelligentObjectRunSpace.ProcessPropertyElementRunSpaces[this.processPropertyIndex];
    }

    @Override
    public void RemoveExistingRunSpace(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        AbsBaseRunSpace absBaseRunSpace = super.GetRunSpaceRecursionOutOfParent(intelligentObjectRunSpace);
        int num = intelligentObjectRunSpace.ProcessPropertyElementRunSpaces.length - 1;
        if (num > 0) {
            List<ProcessPropertyElementRunSpace> propertyElementRunSpaces = new ArrayList<>(num);
            for (int i = 0; i <= num; i++) {
                ProcessPropertyElementRunSpace processPropertyElementRunSpace =
                        intelligentObjectRunSpace.ProcessPropertyElementRunSpaces[i];
                if (processPropertyElementRunSpace != absBaseRunSpace) {
                    propertyElementRunSpaces.add(processPropertyElementRunSpace);
                }
            }
            intelligentObjectRunSpace.ProcessPropertyElementRunSpaces =
                    propertyElementRunSpaces.toArray(ProcessPropertyElementRunSpace[]::new);
            return;
        }
        intelligentObjectRunSpace.ProcessPropertyElementRunSpaces = null;
    }

    @Override
    public void AddNewRunSpace(IntelligentObjectRunSpace intelligentObjectRunSpace) {
        ProcessPropertyElementRunSpace processPropertyElementRunSpace =
                (ProcessPropertyElementRunSpace) this.CreateRunSpaceWithPopulation(intelligentObjectRunSpace,
                        intelligentObjectRunSpace.getMayApplication());
        if (intelligentObjectRunSpace.ProcessPropertyElementRunSpaces != null) {
            int num = intelligentObjectRunSpace.ProcessPropertyElementRunSpaces.length + 1;
            ProcessPropertyElementRunSpace[] array = intelligentObjectRunSpace.ProcessPropertyElementRunSpaces.clone();
            array[num - 1] = processPropertyElementRunSpace;
            intelligentObjectRunSpace.ProcessPropertyElementRunSpaces = array;
        } else {
            intelligentObjectRunSpace.ProcessPropertyElementRunSpaces = new ProcessPropertyElementRunSpace[1];
            intelligentObjectRunSpace.ProcessPropertyElementRunSpaces[0] = processPropertyElementRunSpace;
        }
        processPropertyElementRunSpace.Initialize(true, true);
    }

    public <T, R> R createAbsBaseStepProperty(String name, Class<T> cl) {
        return (R) this.createAbsBaseStepPropertyDefault(name, cl);
    }

    public <T> AbsBaseStepProperty createAbsBaseStepPropertyDefault(String name, Class<T> cl) {
        return this.createAbsBaseStepProperty((AbsStepDefinition) AbsBaseStepDefinition.definition.Instance(cl), name);
    }

    public AbsBaseStepProperty createAbsBaseStepProperty(AbsStepDefinition absStepDefinition, String name) {
        this.bExcludedFromLimits = false;
        AbsBaseStepProperty absBaseStepProperty = absStepDefinition.createAbsBaseStepProperty(name, this);
        this.AbsBaseStepProperties.add(absBaseStepProperty);
        absBaseStepProperty.Parent(super.Parent());
        absBaseStepProperty.properties.init();
        if (super.Parent() != null) {
            super.Parent().method_320(absBaseStepProperty);
        }
        return absBaseStepProperty;
    }

    public BeginStepProperty getBeginStepProperty() {
        if (this.beginStepPropertyIndex == -1) {
            List<AbsBaseStepProperty> absBaseStepProperties = this.AbsBaseStepProperties;

            this.beginStepPropertyIndex = IntStream.range(0, absBaseStepProperties.size())
                    .filter(i -> absBaseStepProperties.get(i) instanceof BeginStepProperty)
                    .findFirst()
                    .orElse(-1);
        }
        if (this.beginStepPropertyIndex != -1) {
            return (BeginStepProperty) this.AbsBaseStepProperties.get(this.beginStepPropertyIndex);
        }
        return null;
    }

    private AbsBaseStepProperty getEndStepProperty() {
        if (this.endStepPropertyIndex == -1) {
            List<AbsBaseStepProperty> list = this.AbsBaseStepProperties;
            this.endStepPropertyIndex = IntStream.range(0, list.size())
                    .filter(i -> list.get(i).getClass() == EndStepProperty.class)
                    .findFirst()
                    .orElse(-1);
        }
        if (this.endStepPropertyIndex != -1) {
            return (EndStepProperty) this.AbsBaseStepProperties.get(this.endStepPropertyIndex);
        }
        return null;
    }

    public static ProcessProperty readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                          IntelligentObjectDefinition intelligentObjectDefinition, Enum50 enum50) {
        ProcessProperty processProperty = null;
        try {
            if (Objects.equals(xmlReader.Name(), "Process")) {
                StringBuffer processName = new StringBuffer(xmlReader.GetAttribute("Name"));
                StringBuffer interfaceProcessId = new StringBuffer(xmlReader.GetAttribute("InterfaceProcessID"));
                if (!Strings.isNullOrEmpty(interfaceProcessId.toString())) {
                    intelligentObjectDefinition.DoInterfaceProcessNameFixup(intelligentObjectXml, processName,
                            interfaceProcessId);
                }

                if (enum50 == ProcessProperty.Enum50.Zero) {
                    Boolean[] isOverride = new Boolean[]{false};
                    SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "IsOverride",
                            (Boolean bool_0) -> isOverride[0] = bool_0);

                    if (isOverride[0]) {
                        ProcessProperty property =
                                intelligentObjectDefinition.processProperties.stream()
                                        .filter((ProcessProperty processProperty1) -> Objects.equals(processProperty1.InstanceName(), processName.toString()))
                                        .findFirst()
                                        .orElse(null);
                        if (property != null) {
                            processProperty = intelligentObjectDefinition.overProcessPropertyFalse(property);
                        }
                    }

                    if (!Strings.isNullOrEmpty(interfaceProcessId.toString()) && processProperty == null) {
                        String[] interfaceProcessIds = interfaceProcessId.toString().split("\\.");
                        if (interfaceProcessIds.length == 2) {
                            ObjectClass objectClass = ObjectClass.Object;
                            try {
                                ObjectClass.valueOf(interfaceProcessIds[0]);
                                int num =
                                        IntelligentObjectDefinition.getObjectClassProcessTypeIndexByName(objectClass,
                                                interfaceProcessIds[1]);
                                if (num != -1) {
                                    processProperty = intelligentObjectDefinition.overProcessProperty(objectClass, num);
                                }
                            } catch (IllegalArgumentException e) {
                                logger.error(e.toString());
                            }
                        }
                    }

                    if (processProperty == null) {
                        processProperty = intelligentObjectDefinition.createProcessProperty();
                    }

                    if (!Strings.isNullOrEmpty(processName.toString()) && !StringHelper.equalsLocal(processProperty.InstanceName(),
                            processName.toString())) {
                        processProperty.InstanceName(ProcessProperty.generalName(processName.toString(),
                                intelligentObjectDefinition));
                    }
                    processProperty.readxml(xmlReader, intelligentObjectXml);
                }
            }
        } catch (Exception e) {
            if (processProperty != null) {
                intelligentObjectDefinition.updateProcessProperty(processProperty);
                processProperty = null;
            }
        }
        if (processProperty != null) {
            processProperty.resetTableAndNotify();
        }
        return processProperty;
    }

    private void readxml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        xmlReader.MoveToContent();
        Map<Integer, Integer> stepMap = new HashMap<>();
        List<StepConnInfo> stepConnInfo = new ArrayList<>();
        int[] beginStepID = new int[]{-1};
        boolean[] bExcludedFromLimits = new boolean[]{false};
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Process", (XmlReader attr) ->
                {
                    String scope = attr.GetAttribute("Scope");
                    if (scope.equalsIgnoreCase("Public")) {
                        this.Scope(ElementScope.Public);
                    }
                    String category = attr.GetAttribute("Category");
                    if (!Strings.isNullOrEmpty(category)) {
                        this.setCategory(category);
                    }
                    String description = attr.GetAttribute("Description");
                    if (!Strings.isNullOrEmpty(description)) {
                        this.Description(description);
                    }
                    SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "ExcludedFromLimits", (Boolean bool_0) ->
                    {
                        bExcludedFromLimits[0] = bool_0;
                    });
                    String beginStepId = attr.GetAttribute("BeginStepID");
                    if (!Strings.isNullOrEmpty(beginStepId)) {
                        beginStepID[0] = Integer.parseInt(beginStepId);
                    }
                    if (beginStepID[0] != -1) {
                        StepConnInfo tmp = new StepConnInfo();
                        tmp.ExitPointPropertyRow = this.getBeginStepProperty().PrimaryExitPointProperty;
                        tmp.stepID = beginStepID[0];
                        stepConnInfo.add(tmp);
                    }
                },
                (XmlReader body) -> this.readXmlProperties(xmlReader, intelligentObjectXml) ||
                        SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Steps", null,
                                (XmlReader stepsBody) -> AbsBaseStepProperty.readXmlStep(xmlReader,
                                        intelligentObjectXml, this, stepConnInfo, (Integer int_0) ->
                                                stepMap.put(int_0, this.AbsBaseStepProperties.size() - 1)) != null));
        boolean flag = false;
        for (StepConnInfo connInfo : stepConnInfo) {
            Integer num = stepMap.get(connInfo.stepID);
            if (num != null) {
                if (num >= 0 && num < this.AbsBaseStepProperties.size()) {
                    AbsBaseStepProperty absBaseStepProperty = this.AbsBaseStepProperties.get(num);
                    if (absBaseStepProperty != null) {
                        connInfo.ExitPointPropertyRow.addEntryPointProperty(absBaseStepProperty);
                    }
                }
            } else {
                flag = true;
            }
        }
        if (flag) {
            intelligentObjectXml.addWarning(String.format("Process '{0}' of object '{1}' had step connections that " +
                    "could not be resolved.", this.InstanceName(), super.Parent().Name()));
        }
        this.processSteps();
        this.ExcludedFromLimits(bExcludedFromLimits[0]);


    }

    private void processSteps() {
        boolean[] reachables = new boolean[this.AbsBaseStepProperties.size()];
        this.addStepAction((AbsBaseStepProperty absBaseStepProperty) -> {
            int num = this.AbsBaseStepProperties.indexOf(absBaseStepProperty);
            reachables[num] = true;
        });

        for (int i = reachables.length - 1; i >= 0; i--) {
            if (!reachables[i]) {
                AbsBaseStepProperty absBaseStepProperty = this.AbsBaseStepProperties.get(i);
                if (absBaseStepProperty != this.getBeginStepProperty() && absBaseStepProperty != this.getEndStepProperty()) {
                    this.restorePointProperty(absBaseStepProperty, false);
                }
            }
        }
    }

    public Object restorePointProperty(AbsBaseStepProperty absBaseStepProperty, boolean b) {
        if (absBaseStepProperty == null || absBaseStepProperty == this.getBeginStepProperty() || absBaseStepProperty == this.getEndStepProperty() || !this.AbsBaseStepProperties.contains(absBaseStepProperty)) {
            throw new IllegalArgumentException();
        }
        List<ProcessProperty.PointPropertyRowWrapper> pointPropertyRowWrappers =
                new ArrayList<>();
        this.removePrimaryExitPointProperty(absBaseStepProperty, b, Enum49.Two,
                pointPropertyRowWrappers);
        this.resetTableAndNotify();
        return pointPropertyRowWrappers;
    }

    private void removePrimaryExitPointProperty(AbsBaseStepProperty absBaseStepProperty, boolean param1,
                                                Enum49 enum49, List<PointPropertyRowWrapper> restoreList) {

        restoreList.add(new ProcessProperty.PointPropertyRowWrapper(absBaseStepProperty, this));
        EntryPointPropertyRow entryPointPropertyRow = null;
        if (enum49 == ProcessProperty.Enum49.Two) {
            this.removeExitPointPropertyRow(absBaseStepProperty.PrimaryExitPointProperty, param1, enum49, restoreList);
        } else {
            entryPointPropertyRow = absBaseStepProperty.PrimaryExitPointProperty.entryPointPropertyRow;
            if (entryPointPropertyRow != null) {
                entryPointPropertyRow.ExitPointPropertyRows.remove(absBaseStepProperty.PrimaryExitPointProperty);
                absBaseStepProperty.PrimaryExitPointProperty.entryPointPropertyRow = null;
                if (entryPointPropertyRow.AbsBaseStepProperty == absBaseStepProperty) {
                    entryPointPropertyRow = null;
                }
            }
        }
        for (ExitPointPropertyRow exitPointPropertyRow :
                absBaseStepProperty.EntryPointPropertyRow.ExitPointPropertyRows) {
            exitPointPropertyRow.entryPointPropertyRow = entryPointPropertyRow;
            if (entryPointPropertyRow != null) {
                entryPointPropertyRow.ExitPointPropertyRows.add(exitPointPropertyRow);
            }
        }
        absBaseStepProperty.EntryPointPropertyRow.ExitPointPropertyRows.clear();
        this.removeExitPointPropertyRow(absBaseStepProperty.SecondExitPointProperty, param1, Enum49.Two,
                restoreList);
        this.removeExitPointPropertyRow(absBaseStepProperty.ThirdExitPointProperty, param1, Enum49.Two,
                restoreList);
        this.AbsBaseStepProperties.remove(absBaseStepProperty);
        if (super.Parent() != null) {
            super.Parent().triggerEvent27(absBaseStepProperty);
        }
        this.excludedFromLimits = false;

    }

    private void removeExitPointPropertyRow(ExitPointPropertyRow exitPointPropertyRow, boolean param1, Enum49 enum49,
                                            List<PointPropertyRowWrapper> restoreList) {
        if (exitPointPropertyRow != null && exitPointPropertyRow.entryPointPropertyRow != null) {
            exitPointPropertyRow.entryPointPropertyRow.ExitPointPropertyRows.remove(exitPointPropertyRow);
            if (param1 && exitPointPropertyRow.entryPointPropertyRow.ExitPointPropertyRows.size() == 0) {
                this.removePrimaryExitPointProperty(exitPointPropertyRow.entryPointPropertyRow.AbsBaseStepProperty,
                        param1, enum49, restoreList);
            }
            exitPointPropertyRow.entryPointPropertyRow = null;
        }
    }


    private void addStepAction(Action<AbsBaseStepProperty> stepAction) {
        this.addStepActionVisitedList(this.getBeginStepProperty(), stepAction,
                new ArrayList<AbsBaseStepProperty>());

    }

    private void addStepActionVisitedList(AbsBaseStepProperty absBaseStepProperty,
                                          Action<AbsBaseStepProperty> stepAction,
                                          ArrayList<AbsBaseStepProperty> visitedList) {
        if (!visitedList.contains(absBaseStepProperty)) {
            stepAction.apply(absBaseStepProperty);
            visitedList.add(absBaseStepProperty);
            if (absBaseStepProperty.PrimaryExitPointProperty != null && absBaseStepProperty.PrimaryExitPointProperty.entryPointPropertyRow != null) {
                this.addStepActionVisitedList(absBaseStepProperty.PrimaryExitPointProperty.entryPointPropertyRow.AbsBaseStepProperty, stepAction, visitedList);
            }
            if (absBaseStepProperty.SecondExitPointProperty != null && absBaseStepProperty.SecondExitPointProperty.entryPointPropertyRow != null) {
                this.addStepActionVisitedList(absBaseStepProperty.SecondExitPointProperty.entryPointPropertyRow.AbsBaseStepProperty, stepAction, visitedList);
            }
        }
    }


    protected boolean readXmlProperties(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return this.properties.readXmlProperties(xmlReader, intelligentObjectXml);
    }


    private void setCategory(String category) {
        String old = this.category;
        this.category = category;
        this.firePropertyUpdate(old);

    }

    private void firePropertyUpdate(String old) {
        // TODO: 2022/1/10 
    }

    private static String generalName(String processName, IntelligentObjectDefinition intelligentObjectDefinition) {
        String[] retName = new String[]{processName};
        int num = 1;
        while (intelligentObjectDefinition.processProperties.stream().anyMatch((ProcessProperty process) -> Objects.equals(process.Name(), retName[0]))) {
            retName[0] = processName + "_" + num;
            num++;
        }
        return retName[0];
    }


    @Override
    protected AbsDefinition DefaultDefinition() {
        return ProcessDefinition.processDefinition;
    }

    @Override
    public int IconIndex() {
        return 0;
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

    public void method_56(AbsBaseStepProperty absBaseStepProperty) {
        this.resetTableAndNotify();
    }

    private void resetTableAndNotify() {
        // TODO: 2022/1/11 
    }

    public enum Enum50 {
        Zero
    }

    public enum Enum49 {
        Zero,
        Two
    }

    public static class StepConnInfo {
        public ExitPointPropertyRow ExitPointPropertyRow;
        public int stepID;
    }

    public class PointPropertyRowWrapper {
        public PointPropertyRowWrapper(AbsBaseStepProperty absBaseStepProperty, ProcessProperty processProperty) {
            this.absBaseStepProperty = absBaseStepProperty;
            this.processProperty = processProperty;
            this.exitPointPropertyRow = this.absBaseStepProperty.PrimaryExitPointProperty.entryPointPropertyRow;
            if (this.absBaseStepProperty.SecondExitPointProperty != null) {
                this.secondExitPointPropertyRow =
                        this.absBaseStepProperty.SecondExitPointProperty.entryPointPropertyRow;
            }
            if (this.absBaseStepProperty.ThirdExitPointProperty != null) {
                this.thirdExitPointPropertyRow = this.absBaseStepProperty.ThirdExitPointProperty.entryPointPropertyRow;
            }
            this.exitPointPropertyRows =
                    new ArrayList<ExitPointPropertyRow>(this.absBaseStepProperty.EntryPointPropertyRow.ExitPointPropertyRows);
        }

        public void restore() throws InvalidOperationException {
            if (this.restored) {
                throw new InvalidOperationException("Can't restore more than once!");
            }
            this.restored = true;
            this.absBaseStepProperty.PrimaryExitPointProperty.addEntryPointProperty(this.exitPointPropertyRow);
            if (this.secondExitPointPropertyRow != null) {
                this.absBaseStepProperty.SecondExitPointProperty.addEntryPointProperty(this.secondExitPointPropertyRow);
            }
            if (this.thirdExitPointPropertyRow != null) {
                this.absBaseStepProperty.ThirdExitPointProperty.addEntryPointProperty(this.thirdExitPointPropertyRow);
            }
            for (ExitPointPropertyRow pointPropertyRow : this.exitPointPropertyRows) {
                pointPropertyRow.addEntryPointProperty(this.absBaseStepProperty.EntryPointPropertyRow);
            }
            this.processProperty.AbsBaseStepProperties.add(this.absBaseStepProperty);
            if (this.processProperty.Parent() != null) {
                this.processProperty.Parent().triggerRestoreAbsBaseStepEvent(this.absBaseStepProperty);
            }
        }

        private AbsBaseStepProperty absBaseStepProperty;
        private EntryPointPropertyRow exitPointPropertyRow;
        private EntryPointPropertyRow secondExitPointPropertyRow;
        private EntryPointPropertyRow thirdExitPointPropertyRow;
        private List<ExitPointPropertyRow> exitPointPropertyRows;
        private ProcessProperty processProperty;
        private boolean restored;

    }
}
