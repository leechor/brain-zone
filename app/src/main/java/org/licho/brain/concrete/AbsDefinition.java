package org.licho.brain.concrete;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.licho.brain.IFunction.Action;
import org.licho.brain.annotations.DynamicUnitClass;
import org.licho.brain.annotations.ElementFunctionReferenceReturnType;
import org.licho.brain.annotations.PopulationFunction;
import org.licho.brain.annotations.ResourceFunction;
import org.licho.brain.annotations.UnitClass;
import org.licho.brain.concrete.annotation.AbsBaseElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.UnitType;
import org.springframework.core.annotation.MergedAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public abstract class AbsDefinition extends GridObjectDefinition implements IIdentityName {
    private static Action<Class<?>> newDefinitionFunction;
    private StateDefinitions stateDefinitions;

    private EventDefinitions eventDefinitions;
    static List<AbsDefinition> absDefinitions;

    private static Object lock = new Object();
    private Map<String, FunctionInfo> nameToFunctionMap;
    private static Map<Class<?>, Map<String, FunctionInfo>> typeNameFunctionMap = new HashMap<>();

    public AbsDefinition(String name) {

        super(name);
        this.stateDefinitions = new StateDefinitions(this);
        this.eventDefinitions = new EventDefinitions(this);
        if (this.DefineSchemaInConstructor()) {
            this.initBasicPropertyDefinition();
        }
    }

    protected boolean DefineSchemaInConstructor() {
        return true;
    }

    protected void DefineSchema() {
        this.initBasicPropertyDefinition();
    }

    private void initBasicPropertyDefinition() {
        var basicLogic = new PropertyDefinitionFacade(EngineResources.CategoryName_BasicLogic);
        var processLogic = new PropertyDefinitionFacade(EngineResources.CategoryName_ProcessLogic);
        var crossingLogic = new PropertyDefinitionFacade(EngineResources.CategoryName_CrossingLogic);
        var transportLogic = new PropertyDefinitionFacade(EngineResources.CategoryName_TransportLogic);
        var travelLogic = new PropertyDefinitionFacade(EngineResources.CategoryName_TravelLogic);
        var routingLogic = new PropertyDefinitionFacade(EngineResources.CategoryName_RoutingLogic);
        var population = new PropertyDefinitionFacade(EngineResources.CategoryName_Population, false);
        var resultsClassification =
                new PropertyDefinitionFacade(EngineResources.CategoryName_ResultsClassification, false);
        var stoppingConditions =
                new PropertyDefinitionFacade(EngineResources.CategoryName_StoppingConditions, false);
        var inputFlowControl =
                new PropertyDefinitionFacade(EngineResources.CategoryName_InputFlowControl, false);

        var financials = new PropertyDefinitionFacade(EngineResources.CategoryName_Financials, false);

        var financialsTransportCosts = PropertyDefinitionFacade.builder()
                .name(EngineResources.CategoryName_FinancialsTransportCosts)
                .parent(financials)
                .description(EngineResources.CategoryDescription_FinancialsTransportCosts)
                .initiallyExpanded(false)
                .build();

        var financialsResourceCosts = PropertyDefinitionFacade.builder()
                .name(EngineResources.CategoryName_FinancialsResourceCosts)
                .parent(financials)
                .description(EngineResources.CategoryDescription_FinancialsResourceCosts)
                .initiallyExpanded(false)
                .build();

        var advancedOptions =
                new PropertyDefinitionFacade(EngineResources.CategoryName_AdvancedOptions, false);

        var advancedOptionsTokenActions = PropertyDefinitionFacade.builder()
                .name(EngineResources.CategoryName_AdvancedOptionsTokenActions)
                .parent(advancedOptions)
                .description(EngineResources.CategoryDescription_TokenActionOptions)
                .initiallyExpanded(false)
                .build();

        this.propertyDefinitions.addPropertyDefinitions(basicLogic,
                processLogic,
                crossingLogic,
                transportLogic,
                travelLogic,
                routingLogic,
                population,
                resultsClassification,
                stoppingConditions,
                inputFlowControl,
                financials,
                financialsTransportCosts,
                financialsResourceCosts,
                advancedOptions,
                advancedOptionsTokenActions);
        this.propertyDefinitions.add(this.createReportStatistics());
    }

    protected BooleanPropertyDefinition createReportStatistics() {
        BooleanPropertyDefinition reportStatistics = new BooleanPropertyDefinition("ReportStatistics");
        reportStatistics.DisplayName(EngineResources.Element_ReportStatistics_DisplayName);
        reportStatistics.Description(EngineResources.Element_ReportStatistics_Description);
        reportStatistics.CategoryName(EngineResources.CategoryName_General);
        reportStatistics.DefaultString("True");
        reportStatistics.ComplexityLevel(ProductComplexityLevel.Advanced);
        return reportStatistics;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return null;
    }

    public String getObjectClassName() {
        return MessageFormat.format(EngineResources.ElementDefinition_ClassName, this.Name());
    }

    public StateDefinitions getStateDefinitions() {
        return stateDefinitions;
    }

    public void setStateDefinitions(StateDefinitions stateDefinitions) {
        this.stateDefinitions = stateDefinitions;
    }


    protected Map<String, FunctionInfo> NameToFunctionMap() {
        if (this.nameToFunctionMap != null) {
            return this.nameToFunctionMap;
        }

        try {
            synchronized (AbsDefinition.typeNameFunctionMap) {
                if (this.nameToFunctionMap != null) {
                    return this.nameToFunctionMap;
                }

                Class<?> type = this.getClass();
                if (AbsDefinition.typeNameFunctionMap.containsKey(type)) {
                    this.nameToFunctionMap = AbsDefinition.typeNameFunctionMap.get(type);
                    return this.nameToFunctionMap;
                }

                Map<String, FunctionInfo> nfm = new HashMap<>();
                AbsDefinition.typeNameFunctionMap.put(type, nfm);
                Method[] methods =
                        Arrays.stream(type.getMethods()).filter(t -> Modifier.isStatic(t.getModifiers())).toArray(Method[]::new);

                for (var methodInfo : methods) {
                    Annotation[] customAttributes = methodInfo.getDeclaredAnnotations();
                    AbsDefinition.FunctionInfo functionInfo = AbsDefinition.FunctionInfo.Instance;
                    functionInfo.methodInfo = methodInfo;
                    functionInfo.classType = type;

                    for (var attribute : customAttributes) {
                        if (MergedAnnotations.from(attribute).isPresent(AbsBaseElementFunction.class)) {
                            var att =
                                    MergedAnnotations.from(attribute).get(AbsBaseElementFunction.class).asAnnotationAttributes();
                            String name = att.getString("NameOverride");
                            if (Strings.isNullOrEmpty(name)) {
                                name = methodInfo.getName();
                            }
                            functionInfo.deprecated = att.getBoolean("Deprecated");
                            var arguments = att.getStringArray("Arguments");
                            var localizedDescriptionResourceManager = att.getString(
                                    "LocalizedDescriptionResourceManager");
                            var des = att.getString("Description");
                            if (arguments != null) {
                                functionInfo.arguments = arguments;
                                if (methodInfo.getReturnType() == ExpressionValue.class) {
                                    functionInfo.ArgsExpressionHandler = (t, u, p) -> {
                                        try {
                                            return (ExpressionValue) methodInfo.invoke(null, t, u, p);
                                        } catch (IllegalAccessException | InvocationTargetException e) {
                                            e.printStackTrace();
                                        }
                                        return null;
                                    };
                                } else {
                                    if (methodInfo.getReturnType() == Double.class || methodInfo.getReturnType() == double.class) {
                                        functionInfo.ArgsExpressionHandler = (t, u, p) -> {
                                            try {
                                                return new ExpressionValue((Double) methodInfo.invoke(null, t, u, p));
                                            } catch (IllegalAccessException | InvocationTargetException e) {
                                                e.printStackTrace();
                                            }
                                            return null;
                                        };
                                    } else {
                                        throw new IllegalArgumentException(MessageFormat.format("Function {0} does " +
                                                "not have " +
                                                "a " +
                                                "valid return type for an " +
                                                "ExpressionFunction", methodInfo.getName()));
                                    }
                                }
                            } else if (methodInfo.getReturnType() == ExpressionValue.class) {
                                functionInfo.ExpressionHandler = (t, u) -> {
                                    try {
                                        return (ExpressionValue) methodInfo.invoke(null, t, u);
                                    } catch (IllegalAccessException | InvocationTargetException e) {
                                        e.printStackTrace();
                                        return null;
                                    }
                                };
                            } else if (methodInfo.getReturnType() == Double.class) {
                                functionInfo.ExpressionHandler = (t, u) -> {
                                    try {
                                        return new ExpressionValue((Double) methodInfo.invoke(null, t, u));
                                    } catch (IllegalAccessException | InvocationTargetException e) {
                                        log.error(e.toString());
                                        return null;
                                    }
                                };
                            } else {
                                throw new IllegalArgumentException(MessageFormat.format("Function %s does not have a " +
                                        "valid return type for an " +
                                        "ExpressionFunction", methodInfo.getName()));
                            }
                            String className = null;
                            if (methodInfo.getDeclaringClass() == LinkDefinition.class) {
                                className = "Link";
                            } else if (methodInfo.getDeclaringClass() == NodeDefinition.class) {
                                className = "Node";
                            } else if (methodInfo.getDeclaringClass() == FixedDefinition.class) {
                                className = "Fixed";
                            } else if (methodInfo.getDeclaringClass() == TransporterDefinition.class) {
                                className = "Transporter";
                            } else if (methodInfo.getDeclaringClass() == EntityDefinition.class) {
                                className = "Entity";
                            } else if (methodInfo.getDeclaringClass() == AgentDefinition.class) {
                                className = "Agent";
                            } else if (methodInfo.getDeclaringClass() == IntelligentObjectDefinition.class) {
                                className = "Object";
                            } else if (methodInfo.getDeclaringClass() == TokenDefinition.class) {
                                className = "Token";
                            } else {
                                AbsDefinition absDefinition =
                                        AbsDefinition.getDefinitions().stream().filter(t -> t.getClass() == type).findFirst().orElse(null);
                                if (absDefinition != null) {
                                    className = absDefinition.Name();
                                }
                            }

                            if (className != null && localizedDescriptionResourceManager != null) {
                                String description = "_Description";
//                                        todo absattribute.LocalizedDescriptionResourceManager.GetString(className +
//                                         "_" + name.Replace('.', '_') + "_Description");
                                if (Strings.isNullOrEmpty(description)) {
                                    functionInfo.description = des;
                                } else {
                                    functionInfo.description = description;
                                }

                            } else {
                                functionInfo.description = des;
                            }
                            if (nfm.containsKey(name)) {
                                throw new IllegalArgumentException(MessageFormat.format("Have two functions called " +
                                        "%s", name));
                            }
                            nfm.put(name, functionInfo);
                            this.nameToFunctionMap = nfm;
                        } else if (attribute instanceof UnitClass) {
                            functionInfo.unitType = ((UnitClass) attribute).value();
                        } else if (attribute instanceof DynamicUnitClass) {
                            functionInfo.dynamicUnit = true;
                        } else if (attribute instanceof PopulationFunction) {
                            functionInfo.populationFunction = true;
                        } else if (attribute instanceof ResourceFunction) {
                            functionInfo.resourceFunction = true;
                        } else if (attribute instanceof ElementFunctionReferenceReturnType) {
                            Class<?> tt = (Class<?>)
                                    MergedAnnotations.from(attribute).get(ElementFunctionReferenceReturnType.class)
                                            .asAnnotationAttributes().get("Type");
                            functionInfo.elementFunction = true;
                            if (tt != null) {
                                functionInfo.AbsDefinition =
                                        AbsDefinition.getDefinitions().stream()
                                                .filter(t -> t.getClass() == tt).findFirst().orElse(null);
                                functionInfo.ReturnType = tt;
                            }
                        }
                    }
                }
            }
        } catch (Exception e) {
            log.error(e.toString());
        }
        return this.nameToFunctionMap;
    }

    public FunctionInfoResult findFunctionInfoResult(String name) {
        FunctionInfoResult infoResult = new FunctionInfoResult();
        var functionInfo = this.NameToFunctionMap().get(name);
        if (functionInfo != null) {
            infoResult.ExpressionHandler = functionInfo.ExpressionHandler;
            infoResult.ArgsExpressionHandler = functionInfo.ArgsExpressionHandler;
            infoResult.unitType = functionInfo.unitType;
            infoResult.dynamicUnit = functionInfo.dynamicUnit;
            infoResult.populationFunction = functionInfo.populationFunction;
            infoResult.resourceFunction = functionInfo.resourceFunction;
            infoResult.methodInfo = functionInfo.methodInfo;
            infoResult.classType = functionInfo.classType;
            if (functionInfo.AbsDefinition == null && functionInfo.ReturnType != null) {
                Class<?> type_ = functionInfo.ReturnType;
                AbsDefinition absDefinition = null;
                if (type_ == IntelligentObjectDefinition.class) {
                    absDefinition = IntelligentObjectDefinition.Instance;
                } else if (type_ == FixedDefinition.class) {
                    absDefinition = FixedDefinition.FixedFacility;
                } else if (type_ == LinkDefinition.class) {
                    absDefinition = LinkDefinition.LinkFacility;
                } else if (type_ == NodeDefinition.class) {
                    absDefinition = NodeDefinition.NodeFacility;
                } else if (type_ == AgentDefinition.class) {
                    absDefinition = AgentDefinition.AgentFacility;
                } else if (type_ == EntityDefinition.class) {
                    absDefinition = EntityDefinition.EntityFacility;
                } else if (type_ == TransporterDefinition.class) {
                    absDefinition = TransporterDefinition.transporterFacility;
                }
                if (absDefinition != null) {
                    functionInfo.AbsDefinition = absDefinition;
                    this.NameToFunctionMap().put(name, functionInfo);
                }
            }
            infoResult.AbsDefinition = functionInfo.AbsDefinition;
            infoResult.elementFunction = functionInfo.elementFunction;
        }
        return infoResult;
    }

    public String[] getFunctionArgumentsByName(String name) {
        if (this.NameToFunctionMap().containsKey(name)) {
            AbsDefinition.FunctionInfo functionInfo = this.NameToFunctionMap().get(name);
            return functionInfo.arguments;
        }
        return new String[0];
    }

    public String getFunctionDescriptionByName(String name) {
        if (this.NameToFunctionMap().containsKey(name)) {
            AbsDefinition.FunctionInfo functionInfo = this.NameToFunctionMap().get(name);
            return functionInfo.description;
        }
        return null;
    }

    public static List<AbsDefinition> getDefinitions() {
        List<AbsDefinition> result;
        synchronized (lock) {
            if (AgentDefinition.absDefinitions == null) {
                AbsDefinition.absDefinitions = new ArrayList<>();
                if (AbsDefinition.newDefinitionFunction == null) {
                    AbsDefinition.newDefinitionFunction = AbsDefinition::newDefinition;
                }
            }
            result = AbsDefinition.absDefinitions;
        }
        return result;
    }

    public static AbsDefinition getUserDefinition(String name) {
        return null;
    }

    private static void newDefinition(Class<?> type) {
        if (type != UserDefinedDefinition.class &&
                type != MissingDefinition.class &&
                type != TokenDefinition.class &&
                type != TableStatesDefinition.class &&
                type != IntelligentObjectDefinition.class &&
                !IntelligentObjectDefinition.class.isAssignableFrom(type)) {
            try {
                AbsDefinition absDefinition = (AbsDefinition) type.getDeclaredConstructor().newInstance();
                if (absDefinition.DefaultDefinition() != null) {
                    AbsDefinition.absDefinitions.add(absDefinition.DefaultDefinition());
                }
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException |
                     NoSuchMethodException e) {
                e.printStackTrace();
            } catch (Exception ignored) {
            }
        }
    }

    public Collection<String> getNames() {
        var result = this.NameToFunctionMap();
        if (result != null) {
            return result.keySet();
        } else {
            log.error("get NameToFunctionMap error.");
            return null;
        }
    }

    public abstract Class<?> ElementType();

    public abstract Class<?> RunSpaceType();

    public abstract AbsDefinition DefaultDefinition();


    public BaseStatePropertyObject StateForBinding(String param0) {
        return null;
    }

    public boolean IsValidIdentifier(String name, StringBuffer error) {
        return true;
    }

    public String GetUniqueName(String name) {
        return this.GetUniqueName(name, true);
    }

    public String GetUniqueName(String name, boolean param1) {
        return name;
    }

    @Override
    public boolean IsJustAFactory() {
        return true;
    }

    public boolean IsDynamic() {
        return false;
    }

    public boolean IsResource() {
        return false;
    }

    protected boolean ShouldIncludeInAutoCompleteChoices(BaseStatePropertyObject baseStatePropertyObject) {
        return true;
    }

    public StringPropertyDefinition GetPropertyForLoad(String s1, IntelligentObjectXml intelligentObjectXml) {
        return null;
    }

    public void NotifyStateAdded(StateDefinitions param0, BaseStatePropertyObject param1, int param2) {
    }

    public void NotifyStateRemoved(StateDefinitions param0, BaseStatePropertyObject param1) {
    }

    public void NotifyStateMoved(StateDefinitions param0, BaseStatePropertyObject param1, int param2, int param3) {
    }

    public void NotifyStateReplaced(StateDefinitions param0, int param1, BaseStatePropertyObject param2,
                                    BaseStatePropertyObject param3) {
    }

    public void NotifyStateDefinitionRenamed(BaseStatePropertyObject param0, String param1) {
    }

    public void NotifyStateDefinitionChangedDimensions(BaseStatePropertyObject param0) {
    }

    public void NotifyEventAdded(EventDefinitions param0, EventDefinition param1, int param2) {
    }

    public void NotifyEventRemoved(EventDefinitions param0, EventDefinition param1) {
    }

    public void NotifyEventMoved(EventDefinitions param0, EventDefinition param1, int param2, int param3) {
    }

    public void NotifyEventReplaced(EventDefinitions param0, int param1, EventDefinition param2,
                                    EventDefinition param3) {
    }

    public void NotifyEventDefinitionRenamed(EventDefinition param0, String param1) {
    }

    @Override
    public String[] DisplayedValuesNeeded(int index) {
        if (index >= 1000 && index < 1000000) {
            return null;
        }
        if (index >= 1000000) {
            BaseStatePropertyObject stateDefinition =
                    this.getStateDefinitions().StateProperties.values.get(index - 1000000);
            return stateDefinition.GetListValues();
        }
        return super.DisplayedValuesNeeded(index);
    }


    public EventDefinitions getEventDefinitions() {
        return eventDefinitions;
    }

    public void setEventDefinitions(EventDefinitions eventDefinitions) {
        this.eventDefinitions = eventDefinitions;
    }

    protected static class FunctionInfo {
        public static FunctionInfo Instance = new FunctionInfo();
        public ExpressionHandler ExpressionHandler;

        public ArgsExpressionHandler ArgsExpressionHandler;

        public String[] arguments;

        public String description;

        public boolean deprecated;

        public UnitType unitType;

        public boolean dynamicUnit;

        public boolean populationFunction;

        public boolean elementFunction;

        public boolean resourceFunction;

        public AbsDefinition AbsDefinition;

        public Class<?> ReturnType;

        public Method methodInfo;

        public Class<?> classType;
    }
}
