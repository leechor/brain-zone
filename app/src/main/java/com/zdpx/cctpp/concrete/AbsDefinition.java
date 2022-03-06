package com.zdpx.cctpp.concrete;

import com.google.common.base.Strings;
import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.annotations.DynamicUnitClass;
import com.zdpx.cctpp.annotations.ElementFunctionReferenceReturnType;
import com.zdpx.cctpp.annotations.PopulationFunction;
import com.zdpx.cctpp.annotations.ResourceFunction;
import com.zdpx.cctpp.annotations.UnitClass;
import com.zdpx.cctpp.concrete.annotation.AbsBaseElementFunction;
import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.ExpressionValue;
import com.zdpx.cctpp.enu.ProductComplexityLevel;
import com.zdpx.cctpp.enu.UnitType;
import org.springframework.core.annotation.MergedAnnotations;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 *
 */
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
        PropertyDefinitionFacade basicLogic = new PropertyDefinitionFacade();
        basicLogic.Name(EngineResources.CategoryName_BasicLogic);
        basicLogic.Description(EngineResources.CategoryName_BasicLogic);
        basicLogic.InitiallyExpanded(true);

        PropertyDefinitionFacade processLogic = new PropertyDefinitionFacade();
        processLogic.Name(EngineResources.CategoryName_ProcessLogic);
        processLogic.Description(EngineResources.CategoryName_ProcessLogic);
        processLogic.InitiallyExpanded(true);

        PropertyDefinitionFacade crossingLogic = new PropertyDefinitionFacade();
        crossingLogic.Name(EngineResources.CategoryName_CrossingLogic);
        crossingLogic.Description(EngineResources.CategoryName_CrossingLogic);
        crossingLogic.InitiallyExpanded(true);

        PropertyDefinitionFacade transportLogic = new PropertyDefinitionFacade();
        transportLogic.Name(EngineResources.CategoryName_TransportLogic);
        transportLogic.Description(EngineResources.CategoryName_TransportLogic);
        transportLogic.InitiallyExpanded(true);

        PropertyDefinitionFacade travelLogic = new PropertyDefinitionFacade();
        travelLogic.Name(EngineResources.CategoryName_TravelLogic);
        travelLogic.Description(EngineResources.CategoryName_TravelLogic);
        travelLogic.InitiallyExpanded(true);

        PropertyDefinitionFacade routingLogic = new PropertyDefinitionFacade();
        routingLogic.Name(EngineResources.CategoryName_RoutingLogic);
        routingLogic.Description(EngineResources.CategoryName_RoutingLogic);
        routingLogic.InitiallyExpanded(true);

        PropertyDefinitionFacade population = new PropertyDefinitionFacade();
        population.Name(EngineResources.CategoryName_Population);
        population.Description(EngineResources.CategoryName_Population);
        population.InitiallyExpanded(false);

        PropertyDefinitionFacade resultsClassification = new PropertyDefinitionFacade();
        resultsClassification.Name(EngineResources.CategoryName_ResultsClassification);
        resultsClassification.Description(EngineResources.CategoryName_ResultsClassification);
        resultsClassification.InitiallyExpanded(false);

        PropertyDefinitionFacade stoppingConditions = new PropertyDefinitionFacade();
        stoppingConditions.Name(EngineResources.CategoryName_StoppingConditions);
        stoppingConditions.Description(EngineResources.CategoryName_StoppingConditions);
        stoppingConditions.InitiallyExpanded(false);

        PropertyDefinitionFacade inputFlowControl = new PropertyDefinitionFacade();
        inputFlowControl.Name(EngineResources.CategoryName_InputFlowControl);
        inputFlowControl.Description(EngineResources.CategoryName_InputFlowControl);
        inputFlowControl.InitiallyExpanded(true);

        PropertyDefinitionFacade financials = new PropertyDefinitionFacade();
        financials.Name(EngineResources.CategoryName_Financials);
        financials.Description(EngineResources.CategoryName_Financials);
        financials.InitiallyExpanded(false);

        PropertyDefinitionFacade financialsTransportCosts = new PropertyDefinitionFacade();
        financialsTransportCosts.Name(EngineResources.CategoryName_FinancialsTransportCosts);
        financialsTransportCosts.Parent(financials);
        financialsTransportCosts.Description(EngineResources.CategoryDescription_FinancialsTransportCosts);
        financialsTransportCosts.InitiallyExpanded(false);

        PropertyDefinitionFacade financialsResourceCosts = new PropertyDefinitionFacade();
        financialsResourceCosts.Name(EngineResources.CategoryName_FinancialsResourceCosts);
        financialsResourceCosts.Parent(financials);
        financialsResourceCosts.Description(EngineResources.CategoryDescription_FinancialsResourceCosts);
        financialsResourceCosts.InitiallyExpanded(false);

        PropertyDefinitionFacade advancedOptions = new PropertyDefinitionFacade();
        advancedOptions.Name(EngineResources.CategoryName_AdvancedOptions);
        advancedOptions.Description(EngineResources.CategoryName_AdvancedOptions);
        advancedOptions.InitiallyExpanded(false);

        PropertyDefinitionFacade advancedOptionsTokenActions = new PropertyDefinitionFacade();
        advancedOptionsTokenActions.Name(EngineResources.CategoryName_AdvancedOptionsTokenActions);
        advancedOptionsTokenActions.Parent(advancedOptions);
        advancedOptionsTokenActions.Description(EngineResources.CategoryDescription_TokenActionOptions);
        advancedOptionsTokenActions.InitiallyExpanded(false);

        this.propertyDefinitions.addPropertyDefinition(basicLogic);
        this.propertyDefinitions.addPropertyDefinition(processLogic);
        this.propertyDefinitions.addPropertyDefinition(crossingLogic);
        this.propertyDefinitions.addPropertyDefinition(transportLogic);
        this.propertyDefinitions.addPropertyDefinition(travelLogic);
        this.propertyDefinitions.addPropertyDefinition(routingLogic);
        this.propertyDefinitions.addPropertyDefinition(population);
        this.propertyDefinitions.addPropertyDefinition(resultsClassification);
        this.propertyDefinitions.addPropertyDefinition(stoppingConditions);
        this.propertyDefinitions.addPropertyDefinition(inputFlowControl);
        this.propertyDefinitions.addPropertyDefinition(financials);
        this.propertyDefinitions.addPropertyDefinition(financialsTransportCosts);
        this.propertyDefinitions.addPropertyDefinition(financialsResourceCosts);
        this.propertyDefinitions.addPropertyDefinition(advancedOptions);
        this.propertyDefinitions.addPropertyDefinition(advancedOptionsTokenActions);
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

    public String GetGridObjectClassName() {
        return String.format(EngineResources.ElementDefinition_ClassName, this.Name());
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
                                        throw new IllegalArgumentException(String.format("Function {0} does not have " +
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
                                        logger.error(e.toString());
                                        return null;
                                    }
                                };
                            } else {
                                throw new IllegalArgumentException(String.format("Function %s does not have a " +
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
                                throw new IllegalArgumentException(String.format("Have two functions called %s", name));
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
            logger.error(e.toString());
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
            } catch (InvocationTargetException | InstantiationException | IllegalAccessException | NoSuchMethodException e) {
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
            logger.error("get NameToFunctionMap error.");
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
    public String[] DisplayedValuesNeeded(int param0) {
        if (param0 >= 1000 && param0 < 1000000) {
            return null;
        }
        if (param0 >= 1000000) {
            BaseStatePropertyObject stateDefinition = this.getStateDefinitions().StateProperties.values.get(param0 - 1000000);
            return stateDefinition.GetListValues();
        }
        return super.DisplayedValuesNeeded(param0);
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
