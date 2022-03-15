package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.concrete.property.MissingBranchedStepDefinition;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.simuApi.extensions.IStepDefinition;

import java.lang.reflect.InvocationTargetException;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 */
public abstract class AbsStepDefinition extends GridObjectDefinition {
    private static Map<String, AbsStepDefinition> stepDefinitions;
    private static List<AbsStepDefinition> absStepDefinitions;
    public int level;

    public AbsStepDefinition(String name) {
        super(name);
        this.init();

        if (this.isInterruptible()) {
            this.addInterruptible();
        }

    }

    public synchronized static void init(List<AbsStepDefinition> definitions) {
        AbsStepDefinition.absStepDefinitions = definitions;
        AbsStepDefinition.sortByName();
    }

    private static void sortByName() {
         AbsStepDefinition.absStepDefinitions.sort(Comparator.comparing(GridObjectDefinition::Name));
    }

    protected boolean isInterruptible() {
        return false;
    }

    protected void init() {
        this.level = 2;
        EntryPointPropertyDefinition entryPointPropertyDefinition = new EntryPointPropertyDefinition("Entry");
        entryPointPropertyDefinition.DisplayName(EngineResources.Step_Entry_DisplayName);
        entryPointPropertyDefinition.Description(EngineResources.Step_Entry_Description);
        ExitPointPropertyDefinition exitPointPropertyDefinition = new ExitPointPropertyDefinition("Exit");
        exitPointPropertyDefinition.DisplayName(EngineResources.Step_Exit_DisplayName);
        exitPointPropertyDefinition.Description(EngineResources.Step_Exit_Description);
        PropertyDefinitions propertyDefinitions = super.getPropertyDefinitions();
        propertyDefinitions.add(entryPointPropertyDefinition);
        propertyDefinitions.add(exitPointPropertyDefinition);
    }

    protected void addInterruptible() {
        BooleanPropertyDefinition booleanPropertyDefinition = new BooleanPropertyDefinition("Interruptible");
        booleanPropertyDefinition.DisplayName(EngineResources.Step_Interruptible_DisplayName);
        booleanPropertyDefinition.Description(EngineResources.Step_Interruptible_Description);
        booleanPropertyDefinition.DefaultString("False");
        booleanPropertyDefinition.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        booleanPropertyDefinition.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().add(booleanPropertyDefinition);
    }

    public abstract AbsStepDefinition DefaultDefinition(Class<?> clazz);

    public static AbsStepDefinition getAbsStepDefinition(String type) {
        AbsStepDefinition absStepDefinition = AbsStepDefinition.registerAbsStepDefinitionProcess().get(type);
        if (absStepDefinition != null) {
            return absStepDefinition;
        }
        try {
            if (StepDefinition.StepDefinions != null) {
                IStepDefinition stepDefinition = StepDefinition.StepDefinions.getStepDefinitionByGuid(new Guid(type));
                if (stepDefinition != null) {
                    return new StepDefinition(stepDefinition).DefaultDefinition();
                }
            }
        } catch (Exception ignored) {
        }
        return null;
    }

    public String GetGridObjectClassName() {
        return String.format(EngineResources.StepDefinition_ClassName, this.Name());
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
        for (StringPropertyDefinition stringPropertyDefinition : this.propertyDefinitions.values) {
            if (stringPropertyDefinition.CanDisplayInPropertyGrid()) {
                GridItemProperty gridItemProperty =
                        stringPropertyDefinition.GetGridItemProperties(this.propertyDefinitions);
                gridItemProperty.Parent(gridItemProperties.getGridItemPropertyByName(gridItemProperty.CategoryName()));
                gridItemProperties.add(gridItemProperty);
            }
        }
        return gridItemProperties;
    }

    public EntryPointPropertyDefinition getEntryPointPropertyDefinition() {
        return (EntryPointPropertyDefinition) super.getPropertyDefinitions().values.get(0);
    }

    public ExitPointPropertyDefinition getExitPointPropertyDefinition() {
        return (ExitPointPropertyDefinition) super.getPropertyDefinitions().values.get(1);
    }

    public synchronized static Map<String, AbsStepDefinition> registerAbsStepDefinitionProcess() {
        Map<String, AbsStepDefinition> stepDefinitions;
        if (AbsStepDefinition.stepDefinitions == null) {
            AbsStepDefinition.stepDefinitions = new HashMap<>();
            AdditionFunctionOperator.process(type -> {
                if (type != BeginStepDefinition.class &&
                        type != EndStepDefinition.class &&
                        type != StepDefinition.class && type != MissingStepDefinition.class
                        && type != MissingBranchedStepDefinition.class) {
                    AbsStepDefinition absStepDefinition = null;
                    try {
                        absStepDefinition = (AbsStepDefinition) type.getDeclaredConstructor().newInstance();
                    } catch (InstantiationException | IllegalAccessException | InvocationTargetException | NoSuchMethodException e) {
                        e.printStackTrace();
                    }
                    if (absStepDefinition.DefaultDefinition(type) != null) {
                        AbsStepDefinition.stepDefinitions.put(absStepDefinition.Name(),
                                absStepDefinition.DefaultDefinition(type));
                        if (Objects.equals(absStepDefinition.Name(), "SetRow")) {
                            AbsStepDefinition.stepDefinitions.put("SetTable",
                                    absStepDefinition.DefaultDefinition(type));
                        }
                    }
                }
            });
        }
        stepDefinitions = AbsStepDefinition.stepDefinitions;
        return stepDefinitions;
    }


    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return null;
    }

    public AbsBaseStepProperty createAbsBaseStepProperty(String name, ProcessProperty processProperty) {
        AbsBaseStepProperty absBaseStepProperty = (AbsBaseStepProperty) this.CreateInstance(name);
        absBaseStepProperty.ProcessProperty = processProperty;
        return absBaseStepProperty;
    }
}
