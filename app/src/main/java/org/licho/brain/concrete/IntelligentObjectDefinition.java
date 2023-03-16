package org.licho.brain.concrete;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.licho.brain.IFunction.Action;
import org.licho.brain.annotations.ElementFunctionReferenceReturnType;
import org.licho.brain.annotations.UnitClass;
import org.licho.brain.api.FacilityLocation;
import org.licho.brain.api.IDayPatterns;
import org.licho.brain.api.IElementObject;
import org.licho.brain.api.IElementObjects;
import org.licho.brain.api.IErrors;
import org.licho.brain.api.IEventDefinition;
import org.licho.brain.api.IEventDefinitions;
import org.licho.brain.api.IExperiments;
import org.licho.brain.api.IExportDataConnectors;
import org.licho.brain.api.IExternalNodes;
import org.licho.brain.api.IFacility;
import org.licho.brain.api.IFunctionTables;
import org.licho.brain.api.IImportDataConnectors;
import org.licho.brain.api.IIntelligentObject;
import org.licho.brain.api.IIntelligentObjects;
import org.licho.brain.api.IModel;
import org.licho.brain.api.INamedList;
import org.licho.brain.api.INodeObject;
import org.licho.brain.api.IPlan;
import org.licho.brain.api.IProperties;
import org.licho.brain.api.IRateTables;
import org.licho.brain.api.IResourceCapacityLog;
import org.licho.brain.api.IResourceStateLog;
import org.licho.brain.api.IRunSetup;
import org.licho.brain.api.IStateDefinition;
import org.licho.brain.api.IStateDefinitions;
import org.licho.brain.api.ITables;
import org.licho.brain.api.IWorkSchedules;
import org.licho.brain.brainEnums.ElementScope;
import org.licho.brain.brainEnums.QueueRanking;
import org.licho.brain.brainEnums.SwitchNumericConditions;
import org.licho.brain.brainEnums.ValidObjectType;
import org.licho.brain.brainEnums.ViewNetworksMode;
import org.licho.brain.brainEnums.WorkPeriodExceptionType;
import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.annotation.ElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.concrete.fake.TimeSpan;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.node.Node;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.concrete.property.AbsBaseStepProperty;
import org.licho.brain.concrete.property.ExpressionValue;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.element.Network;
import org.licho.brain.enu.AgentInterfaceProcess;
import org.licho.brain.enu.CapacityType;
import org.licho.brain.enu.EntityInterfaceProcess;
import org.licho.brain.enu.Enum38;
import org.licho.brain.enu.FixedInterfaceProcess;
import org.licho.brain.enu.IEnumMask;
import org.licho.brain.enu.IconIndex;
import org.licho.brain.enu.LinkInterfaceProcess;
import org.licho.brain.enu.NodeInterfaceProcess;
import org.licho.brain.enu.NumericDataType;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.ObjectInterfaceProcess;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.PropertyGroupClass;
import org.licho.brain.enu.ScheduleType;
import org.licho.brain.enu.TransporterInterfaceProcess;
import org.licho.brain.enu.UnitType;
import org.licho.brain.event.EventArgs;
import org.licho.brain.event.EventHandler;
import org.licho.brain.event.IEvent;
import org.licho.brain.utils.simu.IAdvancedProperties;
import org.licho.brain.utils.simu.IConstraintLog;
import org.licho.brain.utils.simu.IContextBound;
import org.licho.brain.utils.simu.IEntityProcess;
import org.licho.brain.utils.simu.IListeners;
import org.licho.brain.utils.simu.IResourceUsageLog;
import org.licho.brain.utils.simu.ITargetResults;
import org.licho.brain.utils.simu.ITransporterUsageLog;
import org.licho.brain.utils.simu.system.DateTime;
import org.licho.brain.utils.simu.system.IDisposable;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * general object definition
 */
@Slf4j
public class IntelligentObjectDefinition extends AbsDefinition
        implements IFacility, IIntelligentObjects, IModel, IRunSetup, IPlan {

    private static final String name = "IntelligentObject";
    protected static Guid classGuid = new Guid("{9990707F-BF75-4dd6-A150-A814B74A0EB0}");

    private static Converter<ChangeDescription, String> converterChangeDescription;
    private static boolean updatingLibrary = false;
    private static int[] standard;
    public ActiveModel activeModel;
    public List<IntelligentObject> childrenObject;
    public List<Node> nodes;

    // from super interface
    private IElementObjects ielements;
    private IExperiments iexperiments;
    private IAdvancedProperties iadvancedProperties;
    IExperiments experiments;
    //

    private int revision;
    private InternalReference internalReference;

    private Tables tables;
    private NameUtil nameUtil;
    public List<NodeClassProperty> transferPoints;
    private RateTables rateTables;
    private Lists lists;
    private ExpressionFunctions expressionFunctions;
    private InputParameters inputParameters;
    public IntelligentObjectDefinition parent;
    public List<IntelligentObjectDefinition> childrenInstances;
    private boolean resourceLogic;
    private String author;
    private double version;
    private boolean checkBaseForAdvancedProperties;
    public IntelligentObject instance;
    private String keywords;
    private String categories;
    private UnitFilter unitFilter;

    public final String AllocationQueue = "AllocationQueue";

    public final String InitialCapacity = "InitialCapacity()";
    public final String Objects = "Objects";
    public final String External = "External";
    public final String Definition = "Definition";
    public final String UsedObjects = "UsedObjects";
    public final String UsedObject = "UsedObject";

    public Network NetworkProperty;

    public List<IntelligentObjectDefinition> childrenDefinitions;
    private int interactiveResultsVersion;
    private int experimentResultsVersion;
    private int planResultsVersion;
    private int riskResultsVersion;
    private int aboutLocationNum;
    private boolean inUpdating;
    private int countFlag;
    private IntelligentObjectDefinition.SomeStatus someStatus = SomeStatus.Zero;
    public BindingList<AbsIntelligentPropertyObject> elements;

    private ViewNetworksMode viewNetworksMode;
    private WorkSchedulesUtils workSchedulesUtils;
    private ChangeoverMatrices changeoverMatrices;
    private FunctionTables functionTables;
    private ResourceLogExpressions resourceLogExpressions;
    private ResourceLogExpression resourceLogExpression;
    private Action<ResourceLogExpression> resourceLogExpressionAction;
    private Tokens tokens;
    private Guid guid;
    private ActionRun actionCount;
    private int count;
    private List<ChangeStringReplacement> changeStringReplacements = new ArrayList<>();

    public static final IntelligentObjectDefinition Instance =
            new IntelligentObjectDefinition(IntelligentObjectDefinition.name, null,
                    IntelligentObjectDefinition.classGuid);
    public List<ProcessProperty> processProperties;
    private List<Token> tokenProperties;
    private boolean bool_0;
    private boolean isChanged;
    private int definitionCount;

    private List<ChangeDescription> changeDescriptions = new ArrayList<>();

    @SuppressWarnings("unused")
    private static Guid[] standardLibraryUUID = new Guid[]{
            new Guid("{585953CA-C744-444b-92D9-8AF032F3E9A1}"),
            new Guid("{0D40FBF0-8206-443f-B771-65F85633A2F6}"),
            new Guid("{1D0CCF22-61F9-4fc0-9B3C-01D17E71402D}"),
            new Guid("{5F44138F-A5F7-40d1-AB9A-EEA7C5F0254E}"),
            new Guid("{1BC342DC-9D96-49f2-AE4A-AF8F05416564}"),
            new Guid("{35BA214C-D634-4a12-BA95-572F131B3BAB}"),
            new Guid("{6DF5A963-685F-49fb-901C-3683E5D8C754}"),
            new Guid("{91316EC9-4805-41e3-A4FF-DBEFFE5BC1EE}"),
            new Guid("{4AD0D0CE-2B18-4968-BC54-562ED46AC8A5}"),
            new Guid("{840E1952-0A07-4fce-AE96-9D4DDAA9E1EF}"),
            new Guid("{5F89278E-BFE6-4867-97C5-3C24C83C5DA7}"),
            new Guid("{078E9330-0F90-4770-A474-3CB06FAE1DB5}"),
            new Guid("{93D39DA5-5BA1-4ae1-A526-15BDB27D48E4}"),
            new Guid("{FF39356C-A4F9-412c-BB3D-14BAF4DC5D11}"),
            new Guid("{205487E1-5E9E-4b70-B80B-A6C333F7F872}")
    };

    private IContextBound animationSetup;
    private IContextBound defaultAdditionalSymbol;
    private IContextBound dashboard;
    private IContextBound contextBound3;
    private CountLimitClass countLimitClass;
    private boolean bool_7;
    private boolean availableInExpress;
    private int canUseInExpressVersion = -1;
    private String protection;
    private List<Location> locations = new ArrayList<>();
    private int int_9;


    private EventHandler<EventArgs> definitionRemovedEvent;
    private EventHandler<DefinitionNameChangeEventArgs> definitionNameChangeEvent;
    private EventHandler<ChangesLocationDirectionEventArgs> changeLocationDirectionHandler;
    private EventHandler<AfterChangesLocationDirectionEventArgs> afterChangeLocationDirectionEventHandler;
    private EventHandler<ElementEventArgs> elementEvenHandler;
    private EventHandler<IntelligentObjectPropertyEventArgs> propertyErrorHandler;
    private EventHandler<GridObjectEventArgs> propertyErrorEventHandler;
    private EventHandler<GridObjectEventArgs> propertyChangeEvent;
    private EventHandler<IntelligentObjectPropertyEventArgs> objectPropertyChangeEvent;
    private EventHandler<EventArgs> resourceLogicChangedEvent;
    private EventHandler<IntelligentObject> propertyUpdatedEvent;
    private EventHandler<IntelligentObjectDefinition.DefinitionNameChangedEventArgs> definitionNameChangedEvent;
    private EventHandler<IntelligentObjectDefinition.GridObjectInsertEventArgs> insertGridObjectEventHandler;
    private EventHandler<IntelligentObjectDefinition.ElementEventArgs> processPropertyEventHandler;

    private boolean bool_6;

    public IntelligentObjectDefinition(String name) {
        super(name);
    }

    public IntelligentObjectDefinition(String name, ActiveModel activeModel, Guid guid) {
        super(name);
        this.unitFilter = new UnitFilter(this);
        this.initIntelligentObjectDefinition(activeModel, guid, true, null);

        try (var a = this.createDefinitionOperator()) {
            this.DefineSchema();
        }
    }

    public IntelligentObjectDefinition(String name, ActiveModel activeModel,
                                       IntelligentObjectDefinition parentDefinition) {
        super(name);
        try (var ignored = this.createDefinitionOperator()) {
            this.unitFilter = new UnitFilter(this);
            if (parentDefinition == null) {
                parentDefinition = this.createDefaultParentDefinition();
            }
            this.initIntelligentObjectDefinition(activeModel, Guid.NewGuid(), false, parentDefinition);
            if (!parentDefinition.notHaveParent()) {
                parentDefinition.childrenInstances.add(this);
            }
            this.InheritMembers(parentDefinition);
            if (parentDefinition.notHaveParent()) {
                this.ResourceLogic(parentDefinition.ResourceLogic());
            }
        }
    }


    public void addDefinitionRemovedEvent(IEvent<EventArgs> value) {
        EventHandler.subscribe(this.definitionRemovedEvent, value);
    }

    public void removeDefinitionRemovedEvent(IEvent<EventArgs> value) {
        EventHandler.unSubscribe(this.definitionRemovedEvent, value);
    }

    public void addDefinitionNameChangeEvent(IEvent<DefinitionNameChangeEventArgs> value) {
        EventHandler.subscribe(this.definitionNameChangeEvent, value);
    }

    public void removeDefinitionNameChangeEvent(IEvent<DefinitionNameChangeEventArgs> value) {
        EventHandler.unSubscribe(this.definitionNameChangeEvent, value);
    }

    public void addChangeLocationDirectionHandler(IEvent<ChangesLocationDirectionEventArgs> value) {
        EventHandler.subscribe(this.changeLocationDirectionHandler, value);
    }

    public void removeChangeLocationDirectionHandler(IEvent<ChangesLocationDirectionEventArgs> value) {
        EventHandler.unSubscribe(this.changeLocationDirectionHandler, value);
    }

    public void addAfterChangeLocationDirectionEventHandler(IEvent<AfterChangesLocationDirectionEventArgs> value) {
        EventHandler.subscribe(this.afterChangeLocationDirectionEventHandler, value);
    }

    public void removeAfterChangeLocationDirectionEventHandler(IEvent<AfterChangesLocationDirectionEventArgs> value) {
        EventHandler.unSubscribe(this.afterChangeLocationDirectionEventHandler, value);
    }

    public void addElementEvenHandler(IEvent<ElementEventArgs> value) {
        EventHandler.subscribe(this.elementEvenHandler, value);
    }

    public void removeElementEvenHandler(IEvent<ElementEventArgs> value) {
        EventHandler.unSubscribe(this.elementEvenHandler, value);
    }

    public void addPropertyErrorHandler(IEvent<IntelligentObjectPropertyEventArgs> value) {
        EventHandler.subscribe(this.propertyErrorHandler, value);
    }

    public void removePropertyErrorHandler(IEvent<IntelligentObjectPropertyEventArgs> value) {
        EventHandler.unSubscribe(this.propertyErrorHandler, value);
    }

    public void addPropertyErrorEventHandler(IEvent<GridObjectEventArgs> value) {
        EventHandler.subscribe(this.propertyErrorEventHandler, value);
    }

    public void removePropertyErrorEventHandler(IEvent<GridObjectEventArgs> value) {
        EventHandler.unSubscribe(this.propertyErrorEventHandler, value);
    }

    public void addPropertyChangeEvent(IEvent<GridObjectEventArgs> value) {
        EventHandler.subscribe(this.propertyChangeEvent, value);
    }

    public void removePropertyChangeEvent(IEvent<GridObjectEventArgs> value) {
        EventHandler.unSubscribe(this.propertyChangeEvent, value);
    }

    public void addObjectPropertyChangeEvent(IEvent<IntelligentObjectPropertyEventArgs> value) {
        EventHandler.subscribe(this.objectPropertyChangeEvent, value);
    }

    public void removeObjectPropertyChangeEvent(IEvent<IntelligentObjectPropertyEventArgs> value) {
        EventHandler.unSubscribe(this.objectPropertyChangeEvent, value);
    }

    public void addResourceLogicChangedEvent(IEvent<EventArgs> value) {
        EventHandler.subscribe(this.resourceLogicChangedEvent, value);
    }

    public void removeResourceLogicChangedEvent(IEvent<EventArgs> value) {
        EventHandler.unSubscribe(this.resourceLogicChangedEvent, value);
    }

    public static IntelligentObjectDefinition readXmlDefinition(XmlReader xmlReader,
                                                                IntelligentObjectXml intelligentObjectXml,
                                                                SimioProjectDefinition simioProjectDefinition,
                                                                Enum8 enum8) {
        String name = xmlReader.Name();
        if (!name.endsWith("Definition")) {
            return null;
        }

        String preName = name.substring(0, name.length() - "Definition".length());
        String attributeName = xmlReader.GetAttribute("Name");
        String baseClass = xmlReader.GetAttribute("BaseClass");
        IntelligentObjectDefinition baseClassDefinition = null;

        if (!Strings.isNullOrEmpty(baseClass)) {
            baseClassDefinition = intelligentObjectXml.readIntelligentObjectDefinitionByName(baseClass,
                    null, null, false, false);
            if (baseClassDefinition == null) {
                return null;
            }
        }

        ObjectClass objectClass = ObjectClass.valueOf(preName);

        IntelligentObjectDefinition objectDefinition = null;
        if (objectClass != ObjectClass.Object && !Strings.isNullOrEmpty(attributeName)) {
            objectDefinition = switch (objectClass) {
                case Fixed -> new FixedDefinition(attributeName, null, baseClassDefinition);
                case Entity -> new EntityDefinition(attributeName, null, baseClassDefinition);
                case Transporter -> new TransporterDefinition(attributeName, null, baseClassDefinition);
                case Link -> new LinkDefinition(attributeName, null, (LinkDefinition) baseClassDefinition);
                case Agent -> new AgentDefinition(attributeName, null, baseClassDefinition);
                case Node -> new NodeDefinition(attributeName, null, baseClassDefinition);
                default -> throw new IllegalStateException("Unexpected value: " + objectClass);
            };
        }

        if (objectDefinition != null) {
            if (objectDefinition.activeModel == null) {
                objectDefinition.activeModel = new ActiveModel(objectDefinition);
                objectDefinition.activeModel.parentProjectDefinition = simioProjectDefinition;
            }
            intelligentObjectXml.initContextBound(objectDefinition);
            objectDefinition.readXmlDefinition(xmlReader, intelligentObjectXml, enum8);
            objectDefinition.activeModel.initInstance();
            return objectDefinition;
        }
        return null;
    }

    @SuppressWarnings("rawtypes")
    public static int getObjectClassProcessTypeIndexByName(ObjectClass objectClass, String interfaceProcessId) {
        Class<? extends Enum> enumType = IntelligentObjectDefinition.getProcessType(objectClass);
        Enum[] names = enumType.getEnumConstants();
        if (names != null) {
            for (int i = 0; i < names.length; i++) {
                if (StringHelper.equalsLocal(interfaceProcessId, names[i].name())) {
                    return i;
                }
            }
        }
        return -1;
    }

    private void settingNetworkProperty() {
        this.NetworkProperty = NetworkDefinition.createInstance(NetworkDefinition.name);
        this.NetworkProperty.Parent(this);
        this.NetworkProperty.bool_0 = true;
        this.NetworkProperty.Scope(ElementScope.Private);
        this.NetworkProperty.properties.init();
    }

    private void initIntelligentObjectDefinition(ActiveModel activeModel, Guid guid, boolean param2,
                                                 IntelligentObjectDefinition parent) {
        this.activeModel = activeModel;
        this.guid = guid;
        this.childrenObject = new ArrayList<>();
        this.nodes = new ArrayList<>();
        this.elements = new BindingList<>();
        this.childrenInstances = new ArrayList<>(2);
        this.processProperties = new ArrayList<>(2);
        this.tokenProperties = new ArrayList<>(2);
        this.transferPoints = new ArrayList<>(2);
        this.parent = parent;
        this.changeoverMatrices = new ChangeoverMatrices(this);
        this.functionTables = new FunctionTables(this);
        this.resourceLogExpressions = new ResourceLogExpressions(this);
        this.settingNetworkProperty();
        this.bool_0 = param2;
    }

    private void InheritMembers(IntelligentObjectDefinition parentDefinition) {

        PropertyDefinitions propertyDefinitions = super.getPropertyDefinitions();
        StateDefinitions stateDefinitions = super.getStateDefinitions();
        EventDefinitions eventDefinitions = super.getEventDefinitions();
        for (StringPropertyDefinition stringPropertyDefinition :
                parentDefinition.getPropertyDefinitions().getValues()) {
            propertyDefinitions.addDefinition(stringPropertyDefinition);
        }
        for (PropertyDefinitionFacade propertyDefinitionFacade :
                parentDefinition.getPropertyDefinitions().getPropertyDefinitionList()) {
            propertyDefinitions.addPropertyDefinition(propertyDefinitionFacade);
        }
        for (BaseStatePropertyObject baseStatePropertyObject :
                parentDefinition.getStateDefinitions().StateProperties.getValues()) {
            stateDefinitions.addBaseStateProperty(baseStatePropertyObject);
        }
        for (Object obj : parentDefinition.getEventDefinitions()) {
            EventDefinition eventDefinition = (EventDefinition) obj;
            eventDefinitions.addEventDefinition(eventDefinition);
        }
        for (AbsIntelligentPropertyObject absIntelligentPropertyObject : parentDefinition.elements.getValues()) {
            this.addAbsIntelligentPropertyObject(absIntelligentPropertyObject);
        }
        for (AbsListProperty item : parentDefinition.Lists().getValues()) {
            this.Lists().Add(item);
        }
        for (Table table : parentDefinition.Tables().getValues()) {
            this.Tables().Add(table);
        }
        for (ChangeoverMatrix changeoverMatrix : parentDefinition.getChangeoverMatrices().getValues()) {
            this.getChangeoverMatrices().Add(changeoverMatrix);
        }
        for (DayPattern dayPattern : parentDefinition.getWorkSchedulesUtils().DayPatterns().getValues()) {
            this.getWorkSchedulesUtils().DayPatterns().Add(dayPattern);
        }
        for (WorkSchedule workSchedule : parentDefinition.getWorkSchedulesUtils().getWorkSchedules().getValues()) {
            this.getWorkSchedulesUtils().getWorkSchedules().Add(workSchedule);
        }
        for (ExpressionFunction expressionFunction : parentDefinition.ExpressionFunctions().getValues()) {
            this.ExpressionFunctions().Add(expressionFunction);
        }
        for (AbsInputParameter absInputParameter : parentDefinition.InputParameters().getValues()) {
            this.InputParameters().Add(absInputParameter);
        }
        for (RateTable rateTable : parentDefinition.RateTables().getValues()) {
            this.RateTables().Add(rateTable);
        }
        for (ResourceLogExpression logExpression : parentDefinition.getResourceLogExpressions().getValues()) {
            this.getResourceLogExpressions().Add(logExpression);
        }
        for (int i = 0; i < parentDefinition.processProperties.size(); i++) {
            this.InsertProcessProperty(parentDefinition.processProperties.get(i), i);
        }
        for (NodeClassProperty nodeClassProperty : parentDefinition.transferPoints) {
            this.addTransferPoint(nodeClassProperty);
        }
        for (UserFunction functionTable : parentDefinition.getFunctionTables().getValues()) {
            this.getFunctionTables().Add(functionTable);
        }
        for (TokenDefinition tokenDefinition : parentDefinition.getTokens().getValues()) {
            this.getTokens().Add(tokenDefinition);
        }
        for (IntelligentObject intelligentObject : parentDefinition.getChildrenObject()) {
            this.getChildrenObject().add(intelligentObject);
            if (intelligentObject instanceof Link link) {
                this.NetworkProperty.addLink(link);
            }
        }
        for (Node item : parentDefinition.getNodes()) {
            this.getNodes().add(item);
        }
    }

    private void addAbsIntelligentPropertyObject(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        this.elements.Add(absIntelligentPropertyObject);
        this.addProcessPropertyByName(absIntelligentPropertyObject.InstanceName(), absIntelligentPropertyObject);
    }

    public IntelligentObjectDefinition createDefaultParentDefinition() {
        return null;
    }


    public void method_382(AbsIntelligentPropertyObject absIntelligentPropertyObject, String name) {

    }

    public boolean sameIntelligentObjectFacility(IntelligentObjectDefinition intelligentObjectDefinition) {
        return this.getGuid() == intelligentObjectDefinition.getGuid();
    }


    public List<Node> getNodes() {
        return this.nodes;
    }

    public List<NodeClassProperty> TransferPoints() {
        return this.transferPoints;
    }

    public ObjectClass ObjectClass() {
        return ObjectClass.Object;
    }

    public RateTables RateTables() {
        if (this.rateTables == null) {
            this.rateTables = new RateTables(this);
        }
        return this.rateTables;
    }

    public Lists Lists() {
        if (this.lists == null) {
            this.lists = new Lists(this);
        }
        return this.lists;
    }

    @Override
    public Tables Tables() {
        if (this.tables == null) {
            this.tables = new Tables(this);
        }
        return this.tables;
    }

    public ExpressionFunctions ExpressionFunctions() {
        if (this.expressionFunctions == null) {
            this.expressionFunctions = new ExpressionFunctions(this);
        }
        return this.expressionFunctions;
    }

    public InputParameters InputParameters() {
        if (this.inputParameters == null) {
            this.inputParameters = new InputParameters(this);
        }
        return this.inputParameters;
    }

    @Override
    protected boolean DefineSchemaInConstructor() {
        return false;
    }

    public boolean DefaultRunnable() {
        return false;
    }

    @Override
    public Class<?> ElementType() {
        return null;
    }

    @Override
    public Class<?> RunSpaceType() {
        return null;
    }

    @Override
    public boolean IsJustAFactory() {
        return this.notHaveParent();
    }

    @Override
    public List<AbsPropertyObject> AllAssociatedInstancesOfThisPropertyBagList() {
        List<AbsPropertyObject> list = new ArrayList<>(super.getAssociatedInstances());
        if (this.childrenInstances != null) {
            list = this.childrenInstances.stream()
                    .map(IntelligentObjectDefinition::AllAssociatedInstancesOfThisPropertyBagList)
                    .flatMap(Collection::stream).collect(Collectors.toList());
        }
        return list;
    }

    public boolean ResourceLogic() {
        if (this.parent == null || this.parent.notHaveParent()) {
            return this.resourceLogic;
        }
        return this.parent != null && this.parent.ResourceLogic();
    }

    public void ResourceLogic(boolean value) {
        if (this.parent == null || this.parent.notHaveParent()) {
            this.resourceLogic = value;
            if (this.resourceLogic) {
                this.processWhenResourceLogic();
            } else {
                this.processWhenNotResourceLogic();
            }

            if (this.resourceLogic != value) {
                this.triggerEventHandler_30();
                this.resetTable(255);
            }
        }
    }

    private void triggerEventHandler_30() {
        EventHandler.fire(this.resourceLogicChangedEvent, this, EventArgs.Empty);
    }

    @Override
    protected void OnDescriptionChanged() {
        this.resetTable(0, false);
        super.OnDescriptionChanged();
    }

    public String Author() {
        if (Strings.isNullOrEmpty(this.author)) {
            return "LICHO";
        }
        return this.author;
    }

    public void Author(String value) {
        this.author = value;
        this.resetTable(0, false);
    }

    public double Version() {
        return this.version;
    }

    public void Version(double value) {
        this.version = value;
        this.resetTable(0, false);
    }

    public boolean CheckBaseForAdvancedProperties() {
        return this.checkBaseForAdvancedProperties;
    }

    public void CheckBaseForAdvancedProperties(boolean value) {
        this.checkBaseForAdvancedProperties = value;
    }

    @Override
    public String Name() {
        return super.Name();
    }

    @Override
    public void Name(String value) {
        String name = this.Name();
        super.Name(value);
        if (this.instance != null) {
            this.instance.InstanceName(this.Name());
        }
        this.sendDefinitionNameChangeEvent(name);
    }

    @Override
    public AbsDefinition DefaultDefinition() {
        return null;
    }

    public String Keywords() {
        return this.keywords;
    }

    public void Keywords(String value) {
        this.keywords = value;
        this.resetTable(0, false);
    }

    public String Categories() {
        return this.categories;
    }

    public void Categories(String value) {
        this.categories = value;
        this.resetTable(0, false);
    }

    protected boolean ShowCreatedDestroyingProcesses() {
        return false;
    }

    public IconIndex AutoCompleteChoiceIconIndex() {
        return IconIndex.SEVEN;
    }

    public IFacility Facility() {
        return this;
    }

    ITables ModelTables() {
        return this.Tables();
    }

    IRateTables ModelRateTables() {
        return this.RateTables();
    }


    public IResourceStateLog ResourceStateLog() {
        if (this.activeModel != null) {
//            return this.activeModel
        }
        return null;
    }

    public IResourceCapacityLog ResourceCapacityLog() {
        if (this.activeModel != null) {
//            return this.activeModel
        }
        return null;
    }

    public IResourceUsageLog ResourceUsageLog() {
        return null;
    }

    public IConstraintLog ConstraintLog() {
        return null;
    }


    public ITransporterUsageLog TransporterUsageLog() {
        return null;
    }

    public ITargetResults TargetResults() {
        return null;
    }

    public IIntelligentObjects IntelligentObjects() {
        return this;
    }


    public ViewNetworksMode getViewNetworksMode() {
        return this.viewNetworksMode;
    }

    public void setViewNetworksMode(ViewNetworksMode viewNetworksMode) {
        this.viewNetworksMode = viewNetworksMode;
    }

    public WorkSchedulesUtils getWorkSchedulesUtils() {
        if (this.workSchedulesUtils == null) {
            this.workSchedulesUtils = new WorkSchedulesUtils(this);
        }
        return this.workSchedulesUtils;
    }

    public ChangeoverMatrices getChangeoverMatrices() {
        return this.changeoverMatrices;
    }

    public FunctionTables getFunctionTables() {
        return this.functionTables;
    }

    public ResourceLogExpressions getResourceLogExpressions() {
        return this.resourceLogExpressions;
    }

    public ResourceLogExpression getResourceLogExpression() {
        return this.resourceLogExpression;
    }

    public void processResourceLogExpressionAction(ResourceLogExpression resourceLogExpression) {
        this.resourceLogExpression = resourceLogExpression;
        if (this.resourceLogExpressionAction != null) {
            this.resourceLogExpressionAction.apply(resourceLogExpression);
        }
    }

    public Tokens getTokens() {
        if (this.tokens == null) {
            this.tokens = new Tokens(this);
        }
        return this.tokens;
    }

    public Guid getGuid() {
        return this.guid;
    }

    IDisposable createModelOperatorCounter() {
        return new ModelOperatorCounter(this);
    }

    private boolean atUpdating() {
        return this.count > 0;
    }

    private void enter() {
        this.count++;
        if (this.parent != null) {
            this.parent.enter();
        }
    }

    private void exit() {
        this.count--;
        if (this.parent != null) {
            this.parent.exit();
        }
    }


    private ActionRun getActionRun() {
        if (this.actionCount == null) {
            this.actionCount = new ActionRun(null);
        }
        return this.actionCount;
    }


    @Override
    public DateTime StartTime() {
        if (this.activeModel != null) {
            return this.activeModel.getRunSetup().StartTime();
        }
        // should min time
        return DateTime.Now();
    }

    @Override
    public void StartTime(DateTime value) {
        if (this.activeModel != null) {
            this.activeModel.getRunSetup().StartTime(value);
        }
    }

    public DateTime EndingTime() {
        if (this.activeModel != null) {
            return this.activeModel.getRunSetup().EndTime();
        }
        return DateTime.Now();
    }

    public void EndingTime(DateTime value) {
        if (this.activeModel != null) {
            this.activeModel.getRunSetup().EndTime(value);
        }
    }

    public IPropertyDefinitions PropertyDefinitions() {
        return super.getPropertyDefinitions();
    }

    public IStateDefinitions StateDefinitions() {
        return super.getStateDefinitions();
    }

    public IEventDefinitions EventDefinitions() {
        return super.getEventDefinitions();
    }

    public IElementObjects Elements() {
        return this.ielements;
    }

    @Override
    public String ExpressionIdentifier() {
        if (this.Name().equalsIgnoreCase(IntelligentObjectDefinition.name)) {
            return "Object";
        }
        return super.ExpressionIdentifier();
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new IntelligentObject(this, name, ElementScope.Private);
    }

    private void sendDefinitionRemovedEvent() {
        EventHandler.fire(this.definitionRemovedEvent, this, new EventArgs());
    }

    private void sendDefinitionNameChangeEvent(String name) {
        EventHandler.fire(this.definitionNameChangeEvent, this, new DefinitionNameChangeEventArgs(this, name));
    }

    public void resetTable(int resultType) {
        this.resetTable(resultType, true);
    }

    private void resetTable(int resultType, boolean param1) {
        if (this.atUpdating()) {
            return;
        }

        for (Table table : this.Tables().getValues()) {
            table.resetTable(this.getInteractiveVersion(), this.getPlanResultsVersion(), this.getRiskResultsVersion());
        }

        if (IEnumMask.contains(resultType, ResultType.InteractiveResults)) {
            this.interactiveResultsVersion++;
        }

        if (IEnumMask.contains(resultType, ResultType.ExperimentResults)) {
            this.interactiveResultsVersion++;
        }

        if (IEnumMask.contains(resultType, ResultType.PlanResults)) {
            this.interactiveResultsVersion++;
        }

        if (IEnumMask.contains(resultType, ResultType.RiskResults)) {
            this.interactiveResultsVersion++;
        }

        if (!this.isChanged) {
            this.isChanged = true;
            this.revision++;
        }

        if (param1 &&
                !this.getActionRun().empty() && this.activeModel != null &&
                this.activeModel.getDefinition() == this &&
                this.activeModel.parentProjectDefinition != null) {
            for (ActiveModel model : this.activeModel.parentProjectDefinition.ActiveModels) {
                if ((model != this.activeModel || this.activeModel.getDefinition() != this) &&
                        model.getDefinition() != null) {
                    model.getDefinition().resetChildrenTable(this);
                }
            }
        }
    }

    private void resetChildrenTable(IntelligentObjectDefinition intelligentObjectDefinition) {
        if (this.getInternalReference().contains(intelligentObjectDefinition)) {
            this.resetTable(0, false);
        }
    }

    int getInteractiveVersion() {
        return this.interactiveResultsVersion;
    }

    public int getExperimentResults() {
        return this.experimentResultsVersion;
    }


    public int getPlanResultsVersion() {
        return this.planResultsVersion;
    }

    public int getRiskResultsVersion() {
        return this.riskResultsVersion;
    }


    private void processWhenNotResourceLogic() {
        PropertyDefinitions definitions = super.getPropertyDefinitions();
        definitions.findStringPropertyDefinitionInfoByName("ResourceIdleCostRate").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("ResourceCostPerUse").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("ResourceUsageCostRate").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("LogResourceUsage").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("DisplayCategory").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("DisplayColor").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("CapacityType").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("InitialCapacity()").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("WorkSchedule").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("WorkDayExceptions").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("WorkPeriodExceptions").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("RankingRule").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("RankingExpression").SetLocalVisible(false, definitions);
        definitions.findStringPropertyDefinitionInfoByName("DynamicSelectionRule").SetLocalVisible(false, definitions);
    }

    private void processWhenResourceLogic() {
        PropertyDefinitions definitions = super.getPropertyDefinitions();
        definitions.findStringPropertyDefinitionInfoByName("ResourceIdleCostRate").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("ResourceCostPerUse").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("ResourceUsageCostRate").SetLocalVisible(true
                , definitions);
        definitions.findStringPropertyDefinitionInfoByName("LogResourceUsage").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("DisplayCategory").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("DisplayColor").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("CapacityType").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("InitialCapacity()").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("WorkSchedule").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("WorkDayExceptions").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("WorkPeriodExceptions").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("RankingRule").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("RankingExpression").SetLocalVisible(true,
                definitions);
        definitions.findStringPropertyDefinitionInfoByName("DynamicSelectionRule").SetLocalVisible(Boolean.TRUE,
                definitions);
    }


    public void setGuid(Guid guid) {
        this.guid = guid;
    }

    public int getRevision() {
        return revision;
    }

    public void setRevision(int versionIndex) {
        this.revision = versionIndex;
    }

    protected void GetAdditionalProperties(GridItemProperties gridItemProperties,
                                           GridObjectDefinition gridObjectDefinition) {
    }

    public void setLocation(NodeClassProperty nodeClassProperty, Location location) {
    }

    public void setInstanceName(NodeClassProperty nodeClassProperty, String instanceName) {
    }

    @Override
    public PropertyDefinitions GetPropertyDefinitions(PropertyGroupClass propertyGroupClass) {
        if (propertyGroupClass == PropertyGroupClass.Main) {
            return this.propertyDefinitions;
        }
        return super.GetPropertyDefinitions(propertyGroupClass);
    }

    public InternalReference getInternalReference() {
        if (this.internalReference == null) {
            this.internalReference = new InternalReference(this);
        }
        return this.internalReference;
    }


    public boolean isDuplicateIdentifier(String identifier, StringBuffer error) {
        if (this.getInternalReference().getIntelligentObjectDefinition(identifier) != null || identifier.equals(this.Name())) {
            error.append(MessageFormat.format(EngineResources.ErrorDuplicateIdentifier, identifier, "Object class"));
            return false;
        }
        return true;
    }

    public void instanceNameChanged(String oldName, String newName, AbsPropertyObject propertyObject) {
        // TODO: 2021/11/17
    }

    public void method_417(AbsPropertyObject propertyObject, String name) {
    }

    public void method_418(AbsPropertyObject propertyObject, String name) {
    }

    @Override
    protected void DefineSchema() {
        super.DefineSchema();
        ElementPropertyDefinition parentCostCenter = new ElementPropertyDefinition("ParentCostCenter",
                CostCenter.class);

        parentCostCenter.DisplayName(EngineResources.IntelligentObject_ParentCostCenter_DisplayName);
        parentCostCenter.Description(EngineResources.IntelligentObject_ParentCostCenter_Description);
        parentCostCenter.DefaultString("");
        parentCostCenter.CategoryName(EngineResources.CategoryName_Financials);
        parentCostCenter.RequiredValue(false);
        parentCostCenter.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(parentCostCenter);

        ExpressionPropertyDefinition initialCost = new ExpressionPropertyDefinition("InitialCost");
        initialCost.DisplayName(EngineResources.IntelligentObject_InitialCost_DisplayName);
        initialCost.Description(EngineResources.IntelligentObject_InitialCost_Description);
        initialCost.DefaultString("0.0");
        initialCost.CategoryName(EngineResources.CategoryName_Financials);
        initialCost.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Currency);
        initialCost.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(initialCost);

        ExpressionPropertyDefinition initialCostRate = new ExpressionPropertyDefinition("InitialCostRate");
        initialCostRate.DisplayName(EngineResources.IntelligentObject_InitialCostRate_DisplayName);
        initialCostRate.Description(EngineResources.IntelligentObject_InitialCostRate_Description);
        initialCostRate.DefaultString("0.0");
        initialCostRate.CategoryName(EngineResources.CategoryName_Financials);
        initialCostRate.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.CurrencyPerTimeUnit);
        initialCostRate.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(initialCostRate);

        ExpressionPropertyDefinition resourceIdleCostRate = new ExpressionPropertyDefinition("ResourceIdleCostRate");
        resourceIdleCostRate.DisplayName(EngineResources.IntelligentObject_ResourceIdleCostRate_DisplayName);
        resourceIdleCostRate.Description(EngineResources.IntelligentObject_ResourceIdleCostRate_Description);
        resourceIdleCostRate.DefaultString("0.0");
        resourceIdleCostRate.CategoryName(EngineResources.CategoryName_FinancialsResourceCosts);
        resourceIdleCostRate.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.CurrencyPerTimeUnit);
        resourceIdleCostRate.ComplexityLevel(ProductComplexityLevel.Advanced);

        super.getPropertyDefinitions().Add(resourceIdleCostRate);
        ExpressionPropertyDefinition resourceCostPerUse = new ExpressionPropertyDefinition("ResourceCostPerUse");
        resourceCostPerUse.DisplayName(EngineResources.IntelligentObject_ResourceCostPerUse_DisplayName);
        resourceCostPerUse.Description(EngineResources.IntelligentObject_ResourceCostPerUse_Description);
        resourceCostPerUse.DefaultString("0.0");
        resourceCostPerUse.CategoryName(EngineResources.CategoryName_FinancialsResourceCosts);
        resourceCostPerUse.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Currency);
        resourceCostPerUse.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(resourceCostPerUse);

        ExpressionPropertyDefinition resourceUsageCostRate = new ExpressionPropertyDefinition("ResourceUsageCostRate");
        resourceUsageCostRate.DisplayName(EngineResources.IntelligentObject_ResourceUsageCostRate_DisplayName);
        resourceUsageCostRate.Description(EngineResources.IntelligentObject_ResourceUsageCostRate_Description);
        resourceUsageCostRate.DefaultString("0.0");
        resourceUsageCostRate.CategoryName(EngineResources.CategoryName_FinancialsResourceCosts);
        resourceUsageCostRate.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.CurrencyPerTimeUnit);
        resourceUsageCostRate.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(resourceUsageCostRate);

        BooleanPropertyDefinition logResourceUsage = new BooleanPropertyDefinition("LogResourceUsage");
        logResourceUsage.DisplayName(EngineResources.IntelligentObject_LogResourceUsage_DisplayName);
        logResourceUsage.Description(EngineResources.IntelligentObject_LogResourceUsage_Description);
        logResourceUsage.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        logResourceUsage.DefaultString("False");
        logResourceUsage.CanReferenceParent(false);
        logResourceUsage.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(logResourceUsage);

        ExpressionPropertyDefinition displayName = new ExpressionPropertyDefinition("DisplayName");
        displayName.DisplayName(EngineResources.IntelligentObject_DisplayName_DisplayName);
        displayName.Description(EngineResources.IntelligentObject_DisplayName_Description);
        displayName.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        displayName.DefaultString("");
        displayName.RequiredValue(false);
        displayName.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(displayName);

        StringPropertyDefinition displayCategory = new StringPropertyDefinition("DisplayCategory");
        displayCategory.DisplayName(EngineResources.IntelligentObject_DisplayCategory_DisplayName);
        displayCategory.Description(EngineResources.IntelligentObject_DisplayCategory_Description);
        displayCategory.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        displayCategory.DefaultString("");
        displayCategory.RequiredValue(false);
        displayCategory.SwitchNumericProperty(logResourceUsage);
        displayCategory.SwitchNumericValue(1.0);
        displayCategory.SwitchNumericCondition(SwitchNumericConditions.Equal);
        displayCategory.CanReferenceParent(false);
        displayCategory.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(displayCategory);

        ColorPropertyDefinition displayColor = new ColorPropertyDefinition("DisplayColor");
        displayColor.DisplayName(EngineResources.IntelligentObject_DisplayColor_DisplayName);
        displayColor.Description(EngineResources.IntelligentObject_DisplayColor_Description);
        displayColor.CategoryName(EngineResources.CategoryName_AdvancedOptions);
        displayColor.DefaultString("");
        displayColor.RequiredValue(false);
        displayColor.SwitchNumericProperty(logResourceUsage);
        displayColor.SwitchNumericValue(1.0);
        displayColor.SwitchNumericCondition(SwitchNumericConditions.Equal);
        displayColor.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(displayColor);

        EnumPropertyDefinition capacityType = new EnumPropertyDefinition("CapacityType",
                CapacityType.class);
        capacityType.Description(EngineResources.IntelligentObject_CapacityType_Description);
        capacityType.DisplayName(EngineResources.IntelligentObject_CapacityType_DisplayName);
        capacityType.CategoryName(EngineResources.CategoryName_ProcessLogic);
        capacityType.DefaultString(CapacityType.Fixed.toString());
        capacityType.ComplexityLevel(ProductComplexityLevel.Basic);
        super.getPropertyDefinitions().Add(capacityType);

        SchedulePropertyDefinition workSchedule = new SchedulePropertyDefinition("WorkSchedule",
                ScheduleType.CapacitySchedule);
        workSchedule.Description(EngineResources.IntelligentObject_WorkSchedule_Description);
        workSchedule.DisplayName(EngineResources.IntelligentObject_WorkSchedule_DisplayName);
        workSchedule.CategoryName(EngineResources.CategoryName_ProcessLogic);
        workSchedule.DefaultString("");
        workSchedule.RequiredValue(false);
        workSchedule.SwitchNumericProperty(capacityType);
        workSchedule.SwitchNumericValue(1.0);
        workSchedule.SwitchNumericCondition(SwitchNumericConditions.Equal);
        workSchedule.ComplexityLevel(ProductComplexityLevel.Basic);
        super.getPropertyDefinitions().Add(workSchedule);

        RepeatingPropertyDefinition workDayExceptions = new RepeatingPropertyDefinition("WorkDayExceptions", this);
        workDayExceptions.DisplayName(EngineResources.IntelligentObject_WorkDayExceptions_DisplayName);
        workDayExceptions.Description(EngineResources.IntelligentObject_WorkDayExceptions_Description);
        workDayExceptions.ParentPropertyDefinition(workSchedule);
        workDayExceptions.RequiredValue(false);
        workDayExceptions.ComplexityLevel(ProductComplexityLevel.Advanced);
        DateTimePropertyDefinition dateTimePropertyDefinition = new DateTimePropertyDefinition("WorkDayExceptionsDate");
        dateTimePropertyDefinition.DisplayName(EngineResources.IntelligentObject_WorkDayExceptions_WorkDayExceptionsDate_DisplayName);
        dateTimePropertyDefinition.Description(EngineResources.IntelligentObject_WorkDayExceptions_WorkDayExceptionsDate_Description);
        dateTimePropertyDefinition.CategoryName(EngineResources.CategoryName_ExceptionDetails);
        dateTimePropertyDefinition.RequiredValue(true);
        dateTimePropertyDefinition.method_61(false);
        dateTimePropertyDefinition.ComplexityLevel(ProductComplexityLevel.Advanced);
        DayPatternPropertyDefinition WorkDayExceptionsDayPattern = new DayPatternPropertyDefinition(
                "WorkDayExceptionsDayPattern");
        WorkDayExceptionsDayPattern.DisplayName(EngineResources.IntelligentObject_WorkDayExceptions_WorkDayExceptionsDayPattern_DisplayName);
        WorkDayExceptionsDayPattern.Description(EngineResources.IntelligentObject_WorkDayExceptions_WorkDayExceptionsDayPattern_Description);
        WorkDayExceptionsDayPattern.CategoryName(EngineResources.CategoryName_ExceptionDetails);
        WorkDayExceptionsDayPattern.DefaultString("");
        WorkDayExceptionsDayPattern.RequiredValue(false);
        WorkDayExceptionsDayPattern.ComplexityLevel(ProductComplexityLevel.Advanced);
        StringPropertyDefinition WorkDayExceptionsDescription = new StringPropertyDefinition(
                "WorkDayExceptionsDescription");
        WorkDayExceptionsDescription.DisplayName(EngineResources.IntelligentObject_WorkDayExceptions_WorkDayExceptionsDescription_DisplayName);
        WorkDayExceptionsDescription.Description(EngineResources.IntelligentObject_WorkDayExceptions_WorkDayExceptionsDescription_Description);
        WorkDayExceptionsDescription.CategoryName(EngineResources.CategoryName_ExceptionDetails);
        WorkDayExceptionsDescription.ComplexityLevel(ProductComplexityLevel.Advanced);
        workDayExceptions.propertyDefinitions.add(dateTimePropertyDefinition);
        workDayExceptions.propertyDefinitions.add(WorkDayExceptionsDayPattern);
        workDayExceptions.propertyDefinitions.add(WorkDayExceptionsDescription);
        super.getPropertyDefinitions().Add(workDayExceptions);

        RepeatingPropertyDefinition workPeriodExceptions = new RepeatingPropertyDefinition("WorkPeriodExceptions",
                this);
        workPeriodExceptions.DisplayName(EngineResources.IntelligentObject_WorkPeriodExceptions_DisplayName);
        workPeriodExceptions.Description(EngineResources.IntelligentObject_WorkPeriodExceptions_Description);
        workPeriodExceptions.ParentPropertyDefinition(workSchedule);
        workPeriodExceptions.RequiredValue(false);
        workPeriodExceptions.ComplexityLevel(ProductComplexityLevel.Advanced);
        DateTimePropertyDefinition WorkPeriodExceptionsStartTime = new DateTimePropertyDefinition(
                "WorkPeriodExceptionsStartTime");
        WorkPeriodExceptionsStartTime.DisplayName(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsStartTime_DisplayName);
        WorkPeriodExceptionsStartTime.Description(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsStartTime_Description);
        WorkPeriodExceptionsStartTime.RequiredValue(true);
        WorkPeriodExceptionsStartTime.CategoryName(EngineResources.CategoryName_ExceptionDetails);
        WorkPeriodExceptionsStartTime.ComplexityLevel(ProductComplexityLevel.Advanced);
        DateTimePropertyDefinition WorkPeriodExceptionsEndTime = new DateTimePropertyDefinition(
                "WorkPeriodExceptionsEndTime");
        WorkPeriodExceptionsEndTime.DisplayName(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsEndTime_DisplayName);
        WorkPeriodExceptionsEndTime.Description(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsEndTime_Description);
        WorkPeriodExceptionsEndTime.RequiredValue(true);
        WorkPeriodExceptionsEndTime.CategoryName(EngineResources.CategoryName_ExceptionDetails);
        WorkPeriodExceptionsEndTime.ComplexityLevel(ProductComplexityLevel.Advanced);
        EnumPropertyDefinition WorkPeriodExceptionsExceptionType = new EnumPropertyDefinition(
                "WorkPeriodExceptionsExceptionType", WorkPeriodExceptionType.class);
        WorkPeriodExceptionsExceptionType.DisplayName(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsType_DisplayName);
        WorkPeriodExceptionsExceptionType.Description(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsType_Description);
        WorkPeriodExceptionsExceptionType.RequiredValue(true);
        WorkPeriodExceptionsExceptionType.CategoryName(EngineResources.CategoryName_ExceptionDetails);
        WorkPeriodExceptionsExceptionType.DefaultString(WorkPeriodExceptionType.Downtime.toString());
        WorkPeriodExceptionsExceptionType.ComplexityLevel(ProductComplexityLevel.Advanced);
        NumericDataPropertyDefinition workPeriodExceptionsValue = new ExpressionPropertyDefinition(
                "WorkPeriodExceptionsValue");
        workPeriodExceptionsValue.DefaultString("0.0");
        workPeriodExceptionsValue.DisplayName(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsValue_DisplayName);
        workPeriodExceptionsValue.Description(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsValue_Description);
        workPeriodExceptionsValue.RequiredValue(true);
        workPeriodExceptionsValue.CategoryName(EngineResources.CategoryName_ExceptionDetails);
        workPeriodExceptionsValue.ParentPropertyDefinition(WorkPeriodExceptionsExceptionType);
        workPeriodExceptionsValue.SwitchNumericProperty(WorkPeriodExceptionsExceptionType);
        workPeriodExceptionsValue.SwitchNumericCondition(SwitchNumericConditions.Equal);
        workPeriodExceptionsValue.setException(WorkPeriodExceptionType.GeneralException.toString());
        workPeriodExceptionsValue.ComplexityLevel(ProductComplexityLevel.Advanced);
        NumericDataPropertyDefinition workPeriodExceptionsCostMultiplier
                = new ExpressionPropertyDefinition("WorkPeriodExceptionsCostMultiplier");
        workPeriodExceptionsCostMultiplier.DefaultString("1.0");
        workPeriodExceptionsCostMultiplier.DisplayName(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsCostMultiplier_DisplayName);
        workPeriodExceptionsCostMultiplier.Description(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsCostMultiplier_Description);
        workPeriodExceptionsCostMultiplier.RequiredValue(true);
        workPeriodExceptionsCostMultiplier.CategoryName(EngineResources.CategoryName_ExceptionDetails);
        workPeriodExceptionsCostMultiplier.ParentPropertyDefinition(WorkPeriodExceptionsExceptionType);
        workPeriodExceptionsCostMultiplier.SwitchNumericProperty(WorkPeriodExceptionsExceptionType);
        workPeriodExceptionsCostMultiplier.SwitchNumericCondition(SwitchNumericConditions.Equal);
        workPeriodExceptionsCostMultiplier.setException(WorkPeriodExceptionType.GeneralException.toString());
        workPeriodExceptionsCostMultiplier.ComplexityLevel(ProductComplexityLevel.Advanced);
        StringPropertyDefinition workPeriodExceptionsDescription = new StringPropertyDefinition(
                "WorkPeriodExceptionsDescription");
        workPeriodExceptionsDescription.DisplayName(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsDescription_DisplayName);
        workPeriodExceptionsDescription.Description(EngineResources.IntelligentObject_WorkPeriodExceptions_WorkPeriodExceptionsDescription_Description);
        workPeriodExceptionsDescription.CategoryName(EngineResources.CategoryName_ExceptionDetails);
        workPeriodExceptionsDescription.ComplexityLevel(ProductComplexityLevel.Advanced);
        workPeriodExceptions.propertyDefinitions.add(WorkPeriodExceptionsStartTime);
        workPeriodExceptions.propertyDefinitions.add(WorkPeriodExceptionsEndTime);
        workPeriodExceptions.propertyDefinitions.add(WorkPeriodExceptionsExceptionType);
        workPeriodExceptions.propertyDefinitions.add(workPeriodExceptionsValue);
        workPeriodExceptions.propertyDefinitions.add(workPeriodExceptionsCostMultiplier);
        workPeriodExceptions.propertyDefinitions.add(workPeriodExceptionsDescription);
        super.getPropertyDefinitions().Add(workPeriodExceptions);
        ExpressionPropertyDefinition initialCapacity = new ExpressionPropertyDefinition("InitialCapacity()");
        initialCapacity.Description(EngineResources.IntelligentObject_InitialCapacity_Description);
        initialCapacity.DisplayName(EngineResources.IntelligentObject_InitialCapacity_DisplayName);
        initialCapacity.CategoryName(EngineResources.CategoryName_ProcessLogic);
        initialCapacity.DefaultString("1");
        initialCapacity.SwitchNumericProperty(capacityType);
        initialCapacity.SwitchNumericValue(0.0);
        initialCapacity.SwitchNumericCondition(SwitchNumericConditions.Equal);
        initialCapacity.ComplexityLevel(ProductComplexityLevel.Basic);
        super.getPropertyDefinitions().Add(initialCapacity);
        EnumPropertyDefinition rankingRule = new EnumPropertyDefinition("RankingRule", QueueRanking.class);
        rankingRule.Description(EngineResources.IntelligentObject_RankingRule_Description);
        rankingRule.DisplayName(EngineResources.IntelligentObject_RankingRule_DisplayName);
        rankingRule.CategoryName(EngineResources.CategoryName_ProcessLogic);
        rankingRule.stringValues = new String[]{
                "First In First Out",
                "Last In First Out",
                "Smallest Value First",
                "Largest Value First"
        };
        rankingRule.DefaultString("First In First Out");
        rankingRule.ComplexityLevel(ProductComplexityLevel.Basic);
        super.getPropertyDefinitions().Add(rankingRule);
        ExpressionPropertyDefinition rankingExpression = new ExpressionPropertyDefinition("RankingExpression");
        rankingExpression.Description(EngineResources.IntelligentObject_RankingExpression_Description);
        rankingExpression.DisplayName(EngineResources.IntelligentObject_RankingExpression_DisplayName);
        rankingExpression.ParentPropertyDefinition(rankingRule);
        rankingExpression.SwitchNumericProperty(rankingRule);
        rankingExpression.SwitchNumericValue(1.0);
        rankingExpression.SwitchNumericCondition(SwitchNumericConditions.GreaterThan);
        rankingExpression.RequiredValue(false);
        rankingExpression.ComplexityLevel(ProductComplexityLevel.Basic);
        super.getPropertyDefinitions().Add(rankingExpression);
        SelectionRulePropertyDefinition dynamicSelectionRule = new SelectionRulePropertyDefinition(
                "DynamicSelectionRule");
        dynamicSelectionRule.Description(EngineResources.IntelligentObject_DynamicSelectionRule_Description);
        dynamicSelectionRule.DisplayName(EngineResources.IntelligentObject_DynamicSelectionRule_DisplayName);
        dynamicSelectionRule.CategoryName(EngineResources.CategoryName_ProcessLogic);
        dynamicSelectionRule.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(dynamicSelectionRule);
        StatePropertyDefinition currentSizeIndex = new StatePropertyDefinition("CurrentSizeIndex");
        currentSizeIndex.Description(EngineResources.IntelligentObject_CurrentSizeIndex_Description);
        currentSizeIndex.DisplayName(EngineResources.IntelligentObject_CurrentSizeIndex_DisplayName);
        currentSizeIndex.CategoryName(EngineResources.CategoryName_PhysicalCharacteristics);
        currentSizeIndex.RequiredValue(false);
        currentSizeIndex.DefaultString("");
        currentSizeIndex.ComplexityLevel(ProductComplexityLevel.Advanced);
        super.getPropertyDefinitions().Add(currentSizeIndex);
        BaseStatePropertyObject TraceFlag = new BaseStatePropertyObject("TraceFlag", false, true,
                NumericDataType.Boolean);
        TraceFlag.Description(EngineResources.IntelligentObject_TraceFlag_Description);
        TraceFlag.value = 1.0;
        super.getStateDefinitions().addStateProperty(TraceFlag);
        SizeStatePropertyObject Size = new SizeStatePropertyObject("Size", false, false);
        Size.Description(EngineResources.IntelligentObject_Size_Description);
        Size.value = 1.0;
        super.getStateDefinitions().addStateProperty(Size);
        QueueStateObject<Allocation> AllocationQueue = new QueueStateObject<>("AllocationQueue", false, false);
        AllocationQueue.Description(EngineResources.IntelligentObject_AllocationQueue_Description);
        AllocationQueue.CanRemove(true);
        super.getStateDefinitions().addStateProperty(AllocationQueue);
        BaseStatePropertyObject CurrentCapacity = new BaseStatePropertyObject("CurrentCapacity()", false, false,
                NumericDataType.Integer);
        CurrentCapacity.Description(EngineResources.IntelligentObject_CurrentCapacity_Description);
        super.getStateDefinitions().addStateProperty(CurrentCapacity);
        CostStatePropertyObject Cost = new CostStatePropertyObject("Cost", false, false);
        Cost.Description(EngineResources.IntelligentObject_CostState_Description);
        Cost.UnitType(UnitType.Currency);
        Cost.updateParameter(0, EngineResources.IntelligentObject_CostRateParameter_Description);
        super.getStateDefinitions().addStateProperty(Cost);
        EventDefinition CapacityChanged = new EventDefinition("CapacityChanged", false);
        CapacityChanged.Description(EngineResources.IntelligentObject_CapacityChanged_Description);
        super.getEventDefinitions().method_3(CapacityChanged);
        this.createProcessProperties(ObjectClass.Object);
        this.processWhenNotResourceLogic();
    }

    public String checkNameMultiple(String instanceName) {
        return this.getNameUtil().checkNameMultiple(instanceName);
    }

    public void addChangeStringReplacements(String regularExpressionPattern, String replacementString) {
        if (!Strings.isNullOrEmpty(regularExpressionPattern)) {
            this.changeStringReplacements.add(new ChangeStringReplacement(this.getRevision(),
                    regularExpressionPattern, replacementString));
        }
    }

    private List<ChangeStringReplacement> findAllAfterVersion(int version) {
        return this.changeStringReplacements.stream().filter(t -> t.Version() > version).collect(Collectors.toList());
    }

    public void updateNewVersion(IntelligentObjectDefinition objectDefinition) {
        IntelligentObjectDefinition parent = this.parent;
        List<ChangeStringReplacement> replacements = objectDefinition.findAllAfterVersion(parent.getRevision());
        this.processChangeStringReplacement(replacements);
    }

    private void processChangeStringReplacement(List<ChangeStringReplacement> replacements) {
        ChangeStringReplacement.replaceAll(this, replacements);
        this.processIntelligentObjectDefinitionList(
                (IntelligentObjectDefinition t) -> t.processChangeStringReplacement(replacements));
    }

    private void processIntelligentObjectDefinitionList(Action<IntelligentObjectDefinition> action) {
        if (this.childrenInstances != null) {
            for (IntelligentObjectDefinition obj : this.childrenInstances) {
                action.apply(obj);
            }
        }
    }


    public boolean haveChanged() {
        return this.isChanged;
    }

    public void unChanged() {
        if (this.isChanged) {
            this.isChanged = false;
            this.revision--;
        }
    }

    public void enChanged() {
        this.isChanged = false;
    }


    private NameUtil getNameUtil() {
        if (this.nameUtil == null) {
            this.nameUtil = new NameUtil();
        }
        return this.nameUtil;
    }


    public String getLastChangeDescription() {
        ChangeDescription changeDescription = this.getLastChange();
        if (changeDescription != null) {
            return changeDescription.Description();
        }
        return null;
    }

    private ChangeDescription getLastChange() {
        if (this.haveChanged() && this.changeDescriptions.size() > 0) {
            ChangeDescription changeDescription = this.changeDescriptions.get(this.changeDescriptions.size() - 1);
            if (changeDescription.Version() == this.getRevision()) {
                return changeDescription;
            }
        }
        return null;
    }

    public Iterable<String> getChangeDescriptionsAfterVersion(int version) {
        return this.changeDescriptions.stream()
                .filter(changeDescription -> changeDescription.Version() > version)
                .map(IntelligentObjectDefinition::getChangeDescription).collect(Collectors.toList());
    }

    private static String getChangeDescription(ChangeDescription changeDescription) {
        return changeDescription.Description();
    }

    @Override
    public void DestroyInstance(AbsPropertyObject absPropertyObject) {
        super.DestroyInstance(absPropertyObject);
        if (super.getAssociatedInstances().size() == 1 && super.getAssociatedInstances().get(0) == this.instance) {
            this.sendDefinitionRemovedEvent();
        }
    }

    public int getAboutLocationNum() {
        return this.aboutLocationNum;
    }


    private void IncrementAboutLocationNum() {
        this.aboutLocationNum++;
    }


    public void setLocationDirection(IntelligentObject intelligentObject, Location location, Direction direction) {
        this.notifyChangeLocationDirection(intelligentObject, location, direction);
        if (!this.actionCountEmpty()) {
            intelligentObject.nodes.forEach(node -> node.setLocation(node.location.add(direction)));
        }

        if (this.activeModel != null && this.activeModel.parentProjectDefinition != null) {
            this.activeModel.parentProjectDefinition.inited();
        }
        this.IncrementAboutLocationNum();
        this.notifyAfterChangeLocationDirection(intelligentObject, location, direction);
    }


    private void notifyChangeLocationDirection(IntelligentObject intelligentObject, Location location,
                                               Direction direction) {

        EventHandler.fire(this.changeLocationDirectionHandler, this,
                new ChangesLocationDirectionEventArgs(intelligentObject, location
                        , direction));
    }

    private void notifyAfterChangeLocationDirection(org.licho.brain.concrete.IntelligentObject intelligentObject,
                                                    Location location, Direction direction) {
        EventHandler.fire(this.afterChangeLocationDirectionEventHandler, this,
                new AfterChangesLocationDirectionEventArgs(intelligentObject, location, direction));
    }

    private boolean actionCountEmpty() {
        return this.actionCount.empty();
    }

    @BaseElementFunction("Capacity")
    @ResourceFunction
    public static double Capacity(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CurrentCapacity();
        }
        return 0.0;
    }

    @BaseElementFunction("Capacity.Initial")
    @ResourceFunction
    public static double capacityInitial(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().InitialCapacity();
        }
        return 0.0;
    }

    @BaseElementFunction("Capacity.Previous")
    @ResourceFunction
    public static double capacityPrevious(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityPrevious();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Capacity.Next")
    public static double capacityNext(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityNext();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Capacity.Average")
    public static double capacityAverage(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityAverage();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Capacity.Minimum")
    public static double capacityMinimum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityMinimum();
        }
        return 0.0;
    }

    @BaseElementFunction("Capacity.Maximum")
    @ResourceFunction
    public static double capacityMaximum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityMaximum();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Capacity.Allocated")
    public static double capacityAllocated(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityAllocated();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction(value = "Capacity.AllocatedTo", Arguments = "owner")
    public static double capacityAllocatedTo(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                             ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        IntelligentObjectRunSpace owner = null;
        try {
            owner = (IntelligentObjectRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (owner == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.HierarchicalDisplayName(), "Capacity.AllocatedTo"));
        }
        return owner.getAllocatedTo(intelligentObjectRunSpace);
    }

    @BaseElementFunction("Capacity.Allocated.Average")
    @ResourceFunction
    public static double capacityAllocatedAverage(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityAllocatedAverage();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Capacity.Allocated.Minimum")
    public static double capacityAllocatedMinimum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityAllocatedMinimum();
        }
        return 0.0;
    }

    @BaseElementFunction("Capacity.Allocated.Maximum")
    @ResourceFunction
    public static double capacityAllocatedMaximum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityAllocatedMaximum();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Capacity.Allocated.Total")
    public static double capacityAllocatedTotal(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityAllocatedTotal();
        }
        return 0.0;
    }

    @BaseElementFunction("Capacity.Remaining")
    @ResourceFunction
    public static double capacityRemaining(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityRemaining();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Capacity.Utilized")
    public static double capacityUtilized(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityUtilized();
        }
        return 0.0;
    }

    @BaseElementFunction("Capacity.Utilized.Average")
    @ResourceFunction
    public static double capacityUtilizedAverage(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityUtilizedAverage();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Capacity.Utilized.Minimum")
    public static double capacityUtilizedMinimum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityUtilizedMinimum();
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction("Capacity.Utilized.Maximum")
    public static double capacityUtilizedMaximum(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityUtilizedMaximum();
        }
        return 0.0;
    }

    @BaseElementFunction("Capacity.ScheduledUtilization")
    @ResourceFunction
    public static double capacityScheduledUtilization(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityScheduledUtilization();
        }
        return 0.0;
    }

    @UnitClass(UnitType.Currency)
    @BaseElementFunction("Capacity.IdleCost")
    @ResourceFunction
    public static double capacityIdleCost(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityIdleCost();
        }
        return 0.0;
    }

    @BaseElementFunction("Capacity.UsageCostCharged")
    @ResourceFunction

    @UnitClass(UnitType.Currency)

    public static double capacityUsageCostCharged(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityUsageCostCharged();
        }
        return 0.0;
    }


    @UnitClass(UnitType.Time)

    @ResourceFunction
    @BaseElementFunction("Capacity.TimeOfLastChange")
    public static double capacityTimeOfLastChange(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityTimeOfLastChange();
        }
        return Double.NaN;
    }

    @BaseElementFunction("Capacity.TimeOfNextChange")
    @ResourceFunction

    @UnitClass(UnitType.Time)

    public static double capacityTimeOfNextChange(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityTimeOfNextChange();
        }
        return Double.NaN;
    }

    @BaseElementFunction("Capacity.TimeSinceLastChange")
    @UnitClass(UnitType.Time)
    @ResourceFunction
    public static double capacityTimeSinceLastChange(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityTimeSinceLastChange();
        }
        return Double.NaN;
    }

    @BaseElementFunction("Capacity.TimeUntilNextChange")
    @UnitClass(UnitType.Time)
    @ResourceFunction
    public static double capacityTimeUntilNextChange(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return intelligentObjectRunSpace.getRunSpaceWrapper().CapacityTimeUntilNextChange();
        }
        return Double.NaN;
    }

    @ResourceFunction
    @BaseElementFunction("ResourceOwners")
    public static double resourceOwners(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.resourceOwnersNumberItems(absBaseRunSpace, runSpace);
    }

    @ResourceFunction
    @BaseElementFunction("ResourceOwners.NumberItems")
    public static double resourceOwnersNumberItems(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return (double) intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersNumberItems().size();
        }
        return 0.0;
    }

    @BaseElementFunction("ResourceOwners.FirstItem")
    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    @ResourceFunction
    public static ExpressionValue resourceOwnersFirstItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null && intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersNumberItems().size() > 0) {
            return ExpressionValue.from(intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersNumberItems().get(0).getResourceOwnersFirstItem());
        }
        return null;
    }

    @ResourceFunction
    @BaseElementFunction("ResourceOwners.LastItem")
    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    public static ExpressionValue resourceOwnersLastItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null && intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersNumberItems().size() > 0) {
            return ExpressionValue.from(intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersNumberItems().get(intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersNumberItems().size() - 1).
                    getResourceOwnersFirstItem());
        }
        return null;
    }

    @ResourceFunction
    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    @BaseElementFunction(value = "ResourceOwners.ItemAtIndex", Arguments = "index")
    public static ExpressionValue resourceOwnersItemAtIndex(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                            ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        int num;
        try {
            num = expressionValues[0].toInt();
        } catch (Exception ignored) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.HierarchicalDisplayName(), "ResourceOwners.ItemAtIndex"));
        }
        if (intelligentObjectRunSpace.getRunSpaceWrapper() == null || intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersNumberItems().size() < num || num < 1) {
            return null;
        }
        return ExpressionValue.from(intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersNumberItems().get(num - 1).getResourceOwnersFirstItem());
    }

    @ResourceFunction
    @BaseElementFunction(value = "ResourceOwners.IndexOfItem", Arguments = "owner")
    public static double resourceOwnersIndexOfItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                   ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        IntelligentObjectRunSpace owner = null;

        try {
            owner = (IntelligentObjectRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }

        if (owner == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.HierarchicalDisplayName(), "ResourceOwners.IndexOfItem"));
        }
        if (intelligentObjectRunSpace.getRunSpaceWrapper() != null) {
            return (double) (intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersIndexOfItem(owner) + 1);
        }
        return 0.0;
    }

    @ResourceFunction
    @BaseElementFunction(value = "ResourceOwners.Contains", Arguments = "owner")
    public static double resourceOwnersContains(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                ExpressionValue[] param2) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        IntelligentObjectRunSpace owner = null;

        try {
            owner = (IntelligentObjectRunSpace) param2[0].getAbsBaseRunSpace();
        } catch (Exception e) {
        }

        if (owner == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.HierarchicalDisplayName(), "ResourceOwners.Contains"));
        }
        if (intelligentObjectRunSpace.getRunSpaceWrapper() == null) {
            return 0.0;
        }

        if (intelligentObjectRunSpace.getRunSpaceWrapper().ResourceOwnersIndexOfItem(owner) >= 0) {
            return 1.0;
        }
        return 0.0;
    }

    @BaseElementFunction("ID")
    public static double getID(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        return (double) intelligentObjectRunSpace.ID();
    }


    @UnitClass(UnitType.Time)
    @BaseElementFunction("TimeNow")
    public static double TimeNow(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return absBaseRunSpace.getSomeRun().TimeNow();
    }

    @BaseElementFunction("SeizedResources")
    public static double seizedResources(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.seizedResourcesNumberItems(absBaseRunSpace, runSpace);
    }

    @BaseElementFunction("SeizedResources.NumberItems")
    public static double seizedResourcesNumberItems(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.SeizedResourcesNumberItems() != null) {
            return (double) intelligentObjectRunSpace.SeizedResourcesNumberItems().size();
        }
        return 0.0;
    }


    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    @BaseElementFunction("SeizedResources.FirstItem")
    public static ExpressionValue seizedResourcesFirstItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.SeizedResourcesNumberItems() != null && intelligentObjectRunSpace.SeizedResourcesNumberItems().size() > 0) {
            return ExpressionValue.from(intelligentObjectRunSpace.SeizedResourcesNumberItems().get(0).getOwner());
        }
        return null;
    }

    @BaseElementFunction("SeizedResources.LastItem")
    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    public static ExpressionValue seizedResourcesLastItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.SeizedResourcesNumberItems() != null && intelligentObjectRunSpace.SeizedResourcesNumberItems().size() > 0) {
            return ExpressionValue.from(intelligentObjectRunSpace.SeizedResourcesNumberItems()
                    .get(intelligentObjectRunSpace.SeizedResourcesNumberItems().size() - 1).getOwner());
        }
        return null;
    }


    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    @BaseElementFunction(value = "SeizedResources.ItemAtIndex", Arguments = "index")
    public static ExpressionValue seizedResourcesItemAtIndex(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                             ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        int num;
        try {
            num = expressionValues[0].toInt();
        } catch (Exception ignored) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.Name(), "SeizedResources.ItemAtIndex"));
        }
        if (intelligentObjectRunSpace.SeizedResourcesNumberItems() == null || intelligentObjectRunSpace.SeizedResourcesNumberItems().size() < num || num < 1) {
            return null;
        }
        return ExpressionValue.from(intelligentObjectRunSpace.SeizedResourcesNumberItems().get(num - 1).getOwner());
    }

    @BaseElementFunction(value = "SeizedResources.IndexOfItem", Arguments = "resource")
    public static double seizedResourcesIndexOfItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                    ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        IntelligentObjectRunSpace owner = null;
        try {
            owner = (IntelligentObjectRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (owner == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.Name(), "SeizedResources.IndexOfItem"));
        }
        if (intelligentObjectRunSpace.SeizedResourcesNumberItems() != null) {
            return (double) (intelligentObjectRunSpace.SeizedResourcesIndexOfItem(owner) + 1);
        }
        return 0.0;
    }

    @BaseElementFunction(value = "SeizedResources.Contains", Arguments = "resource")
    public static double seizedResourcesContains(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                 ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        IntelligentObjectRunSpace owner = null;
        try {
            owner = (IntelligentObjectRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (owner == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.Name(), "SeizedResources.Contains"));
        }
        if (intelligentObjectRunSpace.SeizedResourcesNumberItems() == null) {
            return 0.0;
        }
        if (intelligentObjectRunSpace.SeizedResourcesIndexOfItem(owner) >= 0) {
            return 1.0;
        }
        return 0.0;
    }

    @BaseElementFunction(value = "SeizedResources.CapacityOwnedOf", Arguments = "resource")
    public static double seizedResourcesCapacityOwnedOf(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                        ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        IntelligentObjectRunSpace owner = null;
        try {
            owner = (IntelligentObjectRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (owner == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.
                            Name(), "SeizedResources.CapacityOwnedOf"));
        }
        return intelligentObjectRunSpace.getAllocatedTo(owner);
    }

    @BaseElementFunction("Location.X")
    @UnitClass(UnitType.Length)
    public static double locationX(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        return intelligentObjectRunSpace.Location().x;
    }


    @UnitClass(UnitType.Length)
    @BaseElementFunction("Location.Y")
    public static double locationY(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.Location().y;
    }

    @BaseElementFunction("Location.Z")
    @UnitClass(UnitType.Length)
    public static double locationZ(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        return intelligentObjectRunSpace.Location().z;
    }


    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    @BaseElementFunction("Location.Parent")
    public static ExpressionValue locationParent(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        return ExpressionValue.from(intelligentObjectRunSpace.CurrentLocationParent());
    }

    @BaseElementFunction(value = "DirectDistanceTo.Object", Arguments = "object")
    @UnitClass(UnitType.Length)
    public static double directDistanceToObject(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        IntelligentObjectRunSpace owner = null;
        try {
            owner = (IntelligentObjectRunSpace) expressionValues[0].getAbsBaseRunSpace();
        } catch (Exception ignored) {
        }
        if (owner == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.
                            HierarchicalDisplayName(), "DirectDistanceTo.Object"));
        }
        return intelligentObjectRunSpace.getDistanceTo(owner);
    }


    @UnitClass(UnitType.Length)
    @BaseElementFunction(value = "DirectDistanceTo.Location", Arguments = {"x", "y", "z"})
    public static double directDistanceToLocation(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                  ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        double x;
        double y;
        double z;
        try {
            x = expressionValues[0].toInt();
            y = expressionValues[1].toInt();
            z = expressionValues[2].toInt();
        } catch (Exception ignored) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.
                            Name(), "DirectDistanceTo.Location"));
        }
        return intelligentObjectRunSpace.DirectDistanceToLocation(new Location(x, y, z));
    }

    @BaseElementFunction("Elements.NumberItems")
    public static double elementsNumberItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        if (intelligentObjectRunSpace.AbsBaseRunSpaces != null) {
            return (double) intelligentObjectRunSpace.AbsBaseRunSpaces.length;
        }
        return 0.0;
    }


    @ElementFunctionReferenceReturnType(AbsDefinition.class)

    @BaseElementFunction("Elements.FirstItem")
    public static ExpressionValue elementsFirstItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        if (intelligentObjectRunSpace.AbsBaseRunSpaces != null && intelligentObjectRunSpace.AbsBaseRunSpaces.length > 0) {
            return ExpressionValue.from(intelligentObjectRunSpace.AbsBaseRunSpaces[0]);
        }
        return null;
    }


    @ElementFunctionReferenceReturnType(AbsDefinition.class)
    @BaseElementFunction("Elements.LastItem")
    public static ExpressionValue elementsLastItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        if (intelligentObjectRunSpace.AbsBaseRunSpaces != null && intelligentObjectRunSpace.AbsBaseRunSpaces.length > 0) {
            return ExpressionValue.from(intelligentObjectRunSpace.AbsBaseRunSpaces[intelligentObjectRunSpace.AbsBaseRunSpaces.length - 1]);
        }
        return null;
    }


    @ElementFunctionReferenceReturnType(AbsDefinition.class)
    @BaseElementFunction(value = "Elements.ItemAtIndex", Arguments = "index")
    public static ExpressionValue elementsItemAtIndex(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                      ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        int num;
        try {
            num = expressionValues[0].toInt();
        } catch (Exception ignored) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.
                            Name(), "Elements.ItemAtIndex"));
        }
        if (intelligentObjectRunSpace.AbsBaseRunSpaces == null || intelligentObjectRunSpace.AbsBaseRunSpaces.length < num || num < 1) {
            return null;
        }
        return ExpressionValue.from(intelligentObjectRunSpace.AbsBaseRunSpaces[num - 1]);
    }

    @BaseElementFunction(value = "Elements.IndexOfItem", Arguments = "element")
    public static double elementsIndexOfItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                             ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        AbsBaseRunSpace element = null;
        try {
            element = expressionValues[0].getAbsBaseRunSpace();
            if (element instanceof IntelligentObjectRunSpace) {
                element = null;
            }
        } catch (Exception ignored) {
        }
        if (element == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.Name(), "Elements.IndexOfItem"));
        }
        if (intelligentObjectRunSpace.AbsBaseRunSpaces != null) {
            return (double) (Arrays.asList(intelligentObjectRunSpace.AbsBaseRunSpaces).indexOf(element) + 1);
        }
        return 0.0;
    }

    @BaseElementFunction(value = "Elements.Contains", Arguments = "element")
    public static double elementsContains(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                          ExpressionValue[] param2) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        AbsBaseRunSpace element = null;
        try {
            element = param2[0].getAbsBaseRunSpace();
            if (element instanceof IntelligentObjectRunSpace) {
                element = null;
            }
        } catch (Exception ignored) {
        }
        if (element == null) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.Name(), "Elements.Contains"));
        }
        if (intelligentObjectRunSpace.AbsBaseRunSpaces != null && Arrays.asList(intelligentObjectRunSpace.AbsBaseRunSpaces).contains(element)) {
            return 1.0;
        }
        return 0.0;
    }


    @ElementFunction("Capacity.CurrentOwners")
    @ResourceFunction
    public static double capacityCurrentOwners(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.resourceOwnersNumberItems(absBaseRunSpace, runSpace);
    }

    @ResourceFunction
    @ElementFunction("Capacity.CurrentOwners.NumberItems")
    public static double capacityCurrentOwners_NumberItems(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.resourceOwnersNumberItems(absBaseRunSpace, runSpace);
    }

    @ResourceFunction
    @ElementFunction("Capacity.CurrentOwners.FirstItem")
    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    public static ExpressionValue capacityCurrentOwnersFirstItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.resourceOwnersFirstItem(absBaseRunSpace, runSpace);
    }

    @ResourceFunction
    @ElementFunction("Capacity.CurrentOwners.LastItem")
    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)
    public static ExpressionValue capacityCurrentOwnersLastItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.resourceOwnersLastItem(absBaseRunSpace, runSpace);
    }

    @ResourceFunction

    @ElementFunctionReferenceReturnType(IntelligentObjectDefinition.class)

    @ElementFunction(value = "Capacity.CurrentOwners.ItemAtIndex", Arguments = "index")
    public static ExpressionValue capacityCurrentOwnersItemAtIndex(AbsBaseRunSpace absBaseRunSpace,
                                                                   IRunSpace runSpace,
                                                                   ExpressionValue[] expressionValues) {
        return IntelligentObjectDefinition.resourceOwnersItemAtIndex(absBaseRunSpace, runSpace, expressionValues);
    }


    @ElementFunction(value = "Capacity.CurrentOwners.IndexOfItem", Arguments = "owner")
    @ResourceFunction
    public static double capacityCurrentOwnersIndexOfItem(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                          ExpressionValue[] expressionValues) {
        return IntelligentObjectDefinition.resourceOwnersIndexOfItem(absBaseRunSpace, runSpace, expressionValues);
    }


    @ElementFunction("Capacity.UnitsOwned")
    public static double capacityUnitsOwned(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace param2 = (IntelligentObjectRunSpace) absBaseRunSpace;
        IntelligentObjectRunSpace intelligentObjectRunSpace = (runSpace != null) ?
                runSpace.AssociatedObjectRunSpace() : null;
        if (intelligentObjectRunSpace == null) {
            return 0.0;
        }
        return intelligentObjectRunSpace.getAllocatedTo(param2);
    }


    @ElementFunction("SeizedObjects")
    public static double SeizedObjects(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        if (intelligentObjectRunSpace.SeizedResourcesNumberItems() != null) {
            return (double) intelligentObjectRunSpace.SeizedResourcesNumberItems().size();
        }
        return 0.0;
    }


    @ElementFunction("SeizedObjects.Count")
    public static double seizedObjectsCount(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.SeizedObjects(absBaseRunSpace, runSpace);
    }


    @ElementFunction("SeizedObjects.LastID")
    public static double seizedObjectsLastId(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.SeizedResourcesNumberItems() != null && intelligentObjectRunSpace.SeizedResourcesNumberItems().size() > 0) {
            return (double) intelligentObjectRunSpace.SeizedResourcesNumberItems()
                    .get(intelligentObjectRunSpace.SeizedResourcesNumberItems().size() - 1).getOwner().ID();
        }
        return 0.0;
    }


    @ElementFunction("SeizedObjects.FirstID")
    public static double seizedObjectsFirstId(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.SeizedResourcesNumberItems() != null && intelligentObjectRunSpace.SeizedResourcesNumberItems().size() > 0) {
            return (double) intelligentObjectRunSpace.SeizedResourcesNumberItems().get(0).getOwner().ID();
        }
        return 0.0;
    }


    @ElementFunction(value = "SeizedResources.CapacityUnitsOwned", Arguments = "resource")
    public static double seizedResourcesCapacityUnitsOwned(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                           ExpressionValue[] expressionValues) {
        return IntelligentObjectDefinition.seizedResourcesCapacityOwnedOf(absBaseRunSpace, runSpace, expressionValues);
    }


    @ElementFunction(value = "SeizedObjects.CapacityUnitsOwned", Arguments = "seizedObjectID")
    public static double SeizedObjectsCapacityUnitsOwned(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                         ExpressionValue[] expressionValues) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        try {
            expressionValues[0].toDouble();
        } catch (Exception ignored) {
            throw new IllegalArgumentException(MessageFormat.format(EngineResources.Error_InvalidOrUndefinedFunctionArguments,
                    intelligentObjectRunSpace.Name(), "SeizedObjects.CapacityUnitsOwned"));
        }
        return intelligentObjectRunSpace.getAllocatedTo(expressionValues[0].toInt());
    }

    @ElementFunction("ParentRunSpaceID")
    public static double ParentRunSpaceID(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        if (intelligentObjectRunSpace.CurrentParentObjectRunSpace() != null) {
            return (double) intelligentObjectRunSpace.CurrentParentObjectRunSpace().ID();
        }
        return 0.0;
    }


    @ElementFunction("NumberOwned")
    public static double NumberOwned(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.capacityUnitsOwned(absBaseRunSpace, runSpace);
    }


    @ElementFunction(value = "SeizedObjects.UnitsOwned", Arguments = "objectID")
    public static double SeizedObjectsUnitsOwned(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace,
                                                 ExpressionValue[] param2) {
        return IntelligentObjectDefinition.SeizedObjectsCapacityUnitsOwned(absBaseRunSpace, runSpace, param2);
    }


    @ElementFunction("LastCapacity")

    public static double LastCapacity(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.capacityPrevious(absBaseRunSpace, runSpace);
    }


    @ElementFunction("NumberWaitingAllocation")
    public static double NumberWaitingAllocation(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        return (double) intelligentObjectRunSpace.AllocationQueue().NumberInQueue();
    }


    @ElementFunction("IsTransporter")
    public static double IsTransporter(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        if (absBaseRunSpace instanceof TransporterRunSpace) {
            return 1.0;
        }
        return 0.0;
    }


    @ElementFunction("NumberBusy")
    public static double NumberBusy(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.capacityAllocated(absBaseRunSpace, runSpace);
    }


    @ElementFunction("AverageNumberBusy")
    public static double AverageNumberBusy(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.capacityAllocatedAverage(absBaseRunSpace, runSpace);
    }


    @ElementFunction("MinimumNumberBusy")
    public static double MinimumNumberBusy(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.capacityAllocatedMinimum(absBaseRunSpace, runSpace);
    }


    @ElementFunction("MaximumNumberBusy")
    public static double MaximumNumberBusy(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.capacityAllocatedMaximum(absBaseRunSpace, runSpace);
    }


    @ElementFunction("RemainingCapacity")
    public static double RemainingCapacity(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.capacityRemaining(absBaseRunSpace, runSpace);
    }


    @ElementFunction("SeizedListCount")
    public static double SeizedListCount(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return IntelligentObjectDefinition.SeizedObjects(absBaseRunSpace, runSpace);
    }


    @ElementFunction("DisableRandomness")
    public static double DisableRandomness(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;
        if (intelligentObjectRunSpace.getMayApplication().DisableRandomness()) {
            return 1.0;
        }
        return 0.0;
    }


    @ElementFunction("Elements.NumberItems.Containers")
    public static double ElementsNumberItemsContainers(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        IntelligentObjectRunSpace intelligentObjectRunSpace = (IntelligentObjectRunSpace) absBaseRunSpace;

        if (intelligentObjectRunSpace.AbsBaseRunSpaces != null) {
            int num = 0;
            for (AbsBaseRunSpace baseRunSpace : intelligentObjectRunSpace.AbsBaseRunSpaces) {
                if (baseRunSpace instanceof ContainerElementRunSpace) {
                    num++;
                }
            }
            return (double) num;
        }
        return 0.0;
    }

    @Override
    public String toString() {
        return this.Name();
    }

    public String GetDisplayString(ProductComplexityLevel productComplexityLevel) {
        return this.toString();
    }


    public IElementObject createElement(String typeName) {
        return null;
    }

    public List<IntelligentObject> getChildrenObject() {
        return this.childrenObject;
    }


    @Override
    public IntelligentObject createObject(String type, FacilityLocation location) {
        return null;
    }

    @Override
    public IntelligentObject createLink(String type, INodeObject startNode,
                                        INodeObject endNode,
                                        Iterable<FacilityLocation> points) {
        return null;
    }

    @Override
    public void remote(IIntelligentObject facilityObject) {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public void setName(String name) {

    }

    @Override
    public IProperties Properties() {
        return null;
    }

    @Override
    public IStateDefinition StateDefinition() {
        return null;
    }

    @Override
    public IEventDefinition EventDefinition() {
        return null;
    }

    @Override
    public IFacility getFacility() {
        return null;
    }

    @Override
    public IElementObjects getElements() {
        return null;
    }

    @Override
    public INamedList NamedLists() {
        return null;
    }

    @Override
    public IFunctionTables FunctionTables() {
        return null;
    }

    @Override
    public IRateTables RatedTables() {
        return null;
    }

    @Override
    public IDayPatterns DayPatterns() {
        return null;
    }

    @Override
    public IWorkSchedules WorkSchedules() {
        return null;
    }

    @Override
    public IExternalNodes ExternalNodes() {
        return null;
    }

    @Override
    public IExperiments Experiments() {
        return null;
    }

    @Override
    public IErrors Errors() {
        return null;
    }

    @Override
    public IRunSetup RunSetup() {
        return null;
    }

    @Override
    public IPlan Plan() {
        return null;
    }

    @Override
    public IImportDataConnectors ImportDataConnectors() {
        return null;
    }

    @Override
    public IExportDataConnectors ExportDataConnectors() {
        return null;
    }

    @Override
    public void BulkUpdate(Consumer<IModel> action) {

    }

    @Override
    public IResourceStateLog getResourceStateLog() {
        return null;
    }

    @Override
    public IResourceCapacityLog getResourceCapacityLog() {
        return null;
    }

    @Override
    public DateTime EndTime() {
        return null;
    }

    @Override
    public void EndTime(DateTime endTime) {

    }

    @Override
    public TimeSpan WarmupPeriod() {
        // TODO: 2021/11/20
        return null;
    }

    @Override
    public void WarmupPeriod(TimeSpan warmupPeriod) {
    }

    public UnitFilter UnitFilter() {
        return this.unitFilter;
    }


    public Boolean HasAdvancedProperties() {
        final int foundMine = 0;
        final int foundInherited = 1;
        boolean[] value = new boolean[2];
        this.propertyDefinitions.process((StringPropertyDefinition stringPropertyDefinition) ->
        {
            if (stringPropertyDefinition.IsOwnedBy(this)) {
                if (stringPropertyDefinition.ComplexityLevel() == ProductComplexityLevel.Advanced) {
                    value[foundMine] = true;
                }
            } else if (stringPropertyDefinition.GetComplexityLevel(this.propertyDefinitions) == ProductComplexityLevel.Advanced) {
                value[foundInherited] = true;
            }
        });
        return value[foundMine] || (this.CheckBaseForAdvancedProperties() && value[foundInherited]);
    }

    @Override
    public boolean IsValidIdentifier(String name, StringBuffer error) {
        return this.getNameUtil().IsValidIdentifier(name, error);
    }

    @Override
    public String GetUniqueName(String name, boolean param1) {
        return this.getNameUtil().method_7(name, param1);
    }

    @Override
    public String GetUniqueName(String param0) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IIntelligentObject getByIndex(int index) {
        return null;
    }

    @Override
    public IIntelligentObject getByName(String name) {
        return null;
    }

    @Override
    public Iterator<IIntelligentObject> iterator() {
        return null;
    }

    protected void createProcessProperties(ObjectClass objectClass) {
        Class<?> enumType = IntelligentObjectDefinition.getProcessType(objectClass);

        for (int i = 0; i < enumType.getEnumConstants().length; i++) {
            final int index = i;
            this.createProcessProperty(IntelligentObjectDefinition.getProcessStep(objectClass, index),
                    ElementScope.Private,
                    processProperty -> {
                        processProperty.processIndex = index;
                        processProperty.objectClass = objectClass;
                    },
                    false);
        }
    }

    private ProcessProperty createProcessProperty(String name, ElementScope scope, Action<ProcessProperty> action,
                                                  boolean autoCreated) {
        ProcessProperty processProperty = (ProcessProperty) ProcessDefinition.processDefinition.CreateInstance(name);
        processProperty.Scope(scope);
        processProperty.AutoCreated(autoCreated);
        this.initProperty(processProperty);
        processProperty.properties.init();
        processProperty.createStepProperty("Begin", BeginStepDefinition.class);
        processProperty.createStepProperty("End", EndStepDefinition.class);
        if (action != null) {
            action.apply(processProperty);
        }

        EventHandler.fire(this.elementEvenHandler, this, new ElementEventArgs(processProperty));
        this.addProcessProperty(processProperty, this.processProperties.size() - 1);
        return processProperty;
    }

    private void addProcessProperty(ProcessProperty processProperty, int lastIndex) {
        this.addProcessPropertyByName(processProperty.InstanceName(), processProperty);
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subclass) ->
        {
            subclass.InsertProcessProperty(processProperty, lastIndex);
        });
        this.flashState();
        this.resetTable(255);
    }

    private void triggerDefinitionChildrenNameChangedEvent(Action<IntelligentObjectDefinition> action) {
        if (this.childrenDefinitions != null) {
            for (IntelligentObjectDefinition obj : this.childrenDefinitions) {
                action.apply(obj);
            }
        }
    }

    public void InsertProcessProperty(ProcessProperty processProperty, int index) {
        this.processProperties.add(index, processProperty);
        EventHandler.fire(this.processPropertyEventHandler, this, new ElementEventArgs(processProperty));
        this.reassignProcessPropertyIndex();
        this.addProcessProperty(processProperty, index);
    }

    private void reassignProcessPropertyIndex() {
        int num = 0;
        for (ProcessProperty processProperty : this.processProperties) {
            processProperty.index = num++;
        }
    }


    private void addProcessPropertyByName(String instanceName, Object processProperty) {
        this.getNameUtil().addObjectByName(name, processProperty);
        if (processProperty instanceof ISearch search) {
            if (this.activeModel != null && this.activeModel.getDefinition() == this && this.activeModel.parentProjectDefinition != null) {
                this.activeModel.parentProjectDefinition.getItemEditPolicy().method_0(search, ItemEditPolicy.name,
                        this.activeModel);
            }
        }
    }

    private void initProperty(ProcessProperty processProperty) {
        if (this.processProperties.contains(processProperty)) {
            return;
        }
        processProperty.index = this.processProperties.size();
        this.processProperties.add(processProperty);
        processProperty.objectClass = this.ObjectClass();
        processProperty.Parent(this);
        this.getNameUtil().addObjectByName(processProperty.InstanceName(), processProperty);
        processProperty.properties.init();
        this.injectRunSpaceToProcessProperty(processProperty);
    }

    private void injectRunSpaceToProcessProperty(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        this.processRunSpace((IntelligentObjectRunSpace runSpace) ->
        {
            runSpace.injectRunSpace(absIntelligentPropertyObject);
        });

    }

    private void processRunSpace(Action<IntelligentObjectRunSpace> updateAction) {
        if (this.can()) {
            updateAction.apply(this.activeModel.MayApplication.getFixedRunSpace());
        }
        for (IntelligentObjectDefinition intelligentObjectDefinition : this.getSiblingObjectDefinitionsInProject()) {
            if (intelligentObjectDefinition.can()) {
                updateAction.apply(intelligentObjectDefinition.activeModel.MayApplication.getFixedRunSpace());
            }
        }
    }

    private List<IntelligentObjectDefinition> getSiblingObjectDefinitionsInProject() {
        List<IntelligentObjectDefinition> result = new ArrayList<>();
        if (this.activeModel != null && this.activeModel.parentProjectDefinition != null) {
            for (ActiveModel activeModel : this.activeModel.parentProjectDefinition.ActiveModels) {
                if ((activeModel != this.activeModel || this.activeModel.getDefinition() != this)
                        && activeModel.getDefinition() != null) {
                    result.add(activeModel.getDefinition());
                }
            }
        }
        return result;
    }

    private boolean can() {
        return this.activeModel != null && this.activeModel.canModify();
    }

    public static String getProcessStep(ObjectClass objectClass, int index) {
        Class<?> enumType = IntelligentObjectDefinition.getProcessType(objectClass);
        return enumType.getEnumConstants()[index].toString();
    }

    private static Class<? extends Enum> getProcessType(ObjectClass objectClass) {
        return switch (objectClass) {
            case Object -> ObjectInterfaceProcess.class;
            case Fixed -> FixedInterfaceProcess.class;
            case Entity -> EntityInterfaceProcess.class;
            case Transporter -> TransporterInterfaceProcess.class;
            case Link -> LinkInterfaceProcess.class;
            case Agent -> AgentInterfaceProcess.class;
            case Node -> NodeInterfaceProcess.class;
        };
    }

    public void method_320(AbsBaseStepProperty absBaseStepProperty) {
        // TODO: 2021/11/25 
    }

    protected <T extends AbsIntelligentPropertyObject> T addElement(T instance, ElementScope scope) {
        return (T) this.addElement_(instance, scope, false);

    }

    private AbsIntelligentPropertyObject addElement_(AbsIntelligentPropertyObject absIntelligentPropertyObject,
                                                     ElementScope scope, boolean autoCreated) {
        absIntelligentPropertyObject.Scope(scope);
        absIntelligentPropertyObject.AutoCreated(autoCreated);
        this.initElement(absIntelligentPropertyObject);
//        if (this.elementEvenHandler != null) {
//            this.elementEvenHandler(this,
//                    new IntelligentObjectDefinition.ElementEventArgs(absIntelligentPropertyObject));
//        }
        this.addElement(absIntelligentPropertyObject, this.elements.getValues().size() - 1);
        return absIntelligentPropertyObject;
    }

    private void initElement(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        absIntelligentPropertyObject.index = this.elements.getValues().size();
        absIntelligentPropertyObject.Parent(this);
        this.elements.add(absIntelligentPropertyObject);
        this.getNameUtil().addObjectByName(absIntelligentPropertyObject.InstanceName(), absIntelligentPropertyObject);
        absIntelligentPropertyObject.properties.init();
        this.injectRunSpaceToProcessProperty(absIntelligentPropertyObject);
    }

    private void addElement(AbsIntelligentPropertyObject propertyObject, int index) {
        this.addProcessPropertyByName(propertyObject.InstanceName(), propertyObject);
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition intelligentObjectDefinition) ->
        {
            intelligentObjectDefinition.InsertElement(propertyObject, index);
        });
        this.flashState();
        this.resetTable(255);
    }

    private void InsertElement(AbsIntelligentPropertyObject intelligentPropertyObject, int index) {
        this.elements.Insert(index, intelligentPropertyObject);
        EventHandler.fire(this.elementEvenHandler, this, new ElementEventArgs(intelligentPropertyObject));
        for (int i = index; i < this.elements.getValues().size(); i++) {
            this.elements.getValues().get(i).index = i;
        }
        this.addElement(intelligentPropertyObject, index);
    }

    protected void GetInterfaceProcessInformation(Collection<IEntityProcess> infoList) {
        List<Integer> list = new ArrayList<>();
        if (!this.ShowCreatedDestroyingProcesses()) {
            list.add(1);
            list.add(2);
        }
        if (!this.ResourceLogic()) {
            list.add(3);
            list.add(6);
            list.add(4);
            list.add(5);
        }
        this.injectEntityProcessToObjectClass(infoList, ObjectInterfaceProcess.class, ObjectClass.Object, list);
    }

    protected void injectEntityProcessToObjectClass(Collection<IEntityProcess> infoList, Class<?> entiryProcess,
                                                    ObjectClass objectClass) {
        this.injectEntityProcessToObjectClass(infoList, entiryProcess, objectClass, null);
    }

    protected boolean IsImplicitlySubclassedFrom(IntelligentObjectDefinition intelligentObjectDefinition) {
        return intelligentObjectDefinition.getGuid() == IntelligentObjectDefinition.classGuid;
    }

    protected void injectEntityProcessToObjectClass(Collection<IEntityProcess> infoList,
                                                    Class<?> entityInterfaceProcess, ObjectClass objectClass,
                                                    List<Integer> list) {
        for (Object indexEnum : entityInterfaceProcess.getEnumConstants()) {
            Enum<?> e = (Enum<?>) indexEnum;
            int index = e.ordinal();
            if (list == null || !list.contains(index)) {
                infoList.add(new IntelligentObjectDefinition.EntityProcess(index, e.name(), objectClass));
            }
        }
    }

    void DoInterfaceProcessNameFixup(IntelligentObjectXml intelligentObjectXml, StringBuffer oldName,
                                     StringBuffer newName) {
        if (intelligentObjectXml.FileVersion() < 21 && "Object.OnInitialized".equals(newName)) {
            oldName.append("OnRunInitialized");
            newName.append("Object.OnRunInitialized");
            return;
        }
        if (intelligentObjectXml.FileVersion() < 23 && "Object.OnEvaluatingAllocation".equals(newName)) {
            oldName.append("OnEvaluatingSeizeRequest");
            newName.append("Object.OnEvaluatingSeizeRequest");
        }
    }

    public String GetPropertyValueFixup(IntelligentObjectXml intelligentObjectXml, String s) {
        if (intelligentObjectXml.FileVersion() < 21) {
            s = s.replaceAll("(^|[^\\w_])(?i:OnInitialized)([^\\w_]|$)", "$1OnRunInitialized$2");
        }
        if (intelligentObjectXml.FileVersion() < 23) {
            s = s.replaceAll("(^|[^\\w_])(?i:OnEvaluatingAllocation)([^\\w_]|$)", "$1OnEvaluatingSeizeRequest$2");
        }
        return s;
    }

    public boolean sameGuid(IntelligentObjectDefinition intelligentObjectDefinition) {
        return this.getGuid() == intelligentObjectDefinition.getGuid();
    }

    public void flashState(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.flashState();
    }

    public void flashStateFalse() {
        try (var a = this.createModelOperatorCounter()) {
            this.flashState(false);
        }
    }

    private void flashState(boolean b) {
        if (this.updatingStatus()) {
            return;
        }
        boolean flag = false;
        do {
            switch (this.someStatus) {
                case Zero:
                    this.someStatus = SomeStatus.One;
                    this.propertyListenHandle(IListener::RefreshIfInError, Enum6.Zero, b);
                    this.actionProperty(AbsPropertyObject::changeNameProperty);
                    switch (this.someStatus) {
                        case One:
                            this.someStatus = SomeStatus.Zero;
                            flag = false;
                            break;
                        case Two:
                            this.someStatus = SomeStatus.Zero;
                            b = false;
                            flag = true;
                            break;
                        case Three:
                            this.someStatus = SomeStatus.Zero;
                            b = true;
                            flag = true;
                            break;
                    }
                    break;
                case One:
                    if (b) {
                        this.someStatus = SomeStatus.Three;
                    } else {
                        this.someStatus = SomeStatus.Two;
                    }
                    break;
                case Two:
                    if (b) {
                        this.someStatus = SomeStatus.Three;
                    }
                    break;
            }
        } while (flag);
    }

    private boolean updatingStatus() {
        if (this.updating()) {
            this.inUpdating = true;
            return true;
        }
        return false;
    }

    private boolean updating() {
        return this.countFlag > 0 || (this.parent != null && this.parent.updating());
    }

    private void flashState() {
        this.flashState(true);
    }

    private void propertyListenHandle(Action<IListener> propertyAction, Enum6 enum6) {
        for (Table table : this.Tables().getValues()) {
            propertyAction.apply(table);
            propertyAction.apply(table.Schema());
        }

        this.propertyInstanceAction(properties -> {
            if (enum6 == Enum6.One || (properties.propertyObject != null && properties.propertyObject.IsOwnedBy(this))) {
                properties.propertyInstanceAction(intelligentObjectProperty -> {
                    propertyAction.apply(intelligentObjectProperty);
                    if (intelligentObjectProperty instanceof IListeners) {
                        IListeners listeners = (IListeners) intelligentObjectProperty;
                        for (var listener : listeners.Listeners()) {
                            propertyAction.apply(listener);
                        }
                    }
                    intelligentObjectProperty.expressionListerHandler(propertyAction);
                });
            }
        });

        for (ProcessProperty processProperty : this.processProperties) {
            if (enum6 == Enum6.Zero || processProperty.IsOwnedBy(this)) {
                for (AbsBaseStepProperty absBaseStepProperty : processProperty.AbsBaseStepProperties) {
                    IListener listener = absBaseStepProperty.createExpressionActionListener();
                    if (listener != null) {
                        propertyAction.apply(listener);
                    }
                }
            }
        }

        for (ChangeoverMatrix changeoverMatrix : this.getChangeoverMatrices().getValues()) {
            if (enum6 == Enum6.Zero || changeoverMatrix.IsOwnedBy(this)) {
                propertyAction.apply(changeoverMatrix);
            }
        }

        for (BaseStatePropertyObject baseStatePropertyObject :
                super.getStateDefinitions().StateProperties.getValues()) {
            if (enum6 == Enum6.Zero || baseStatePropertyObject.IsOwnedBy(this)) {
                propertyAction.apply(baseStatePropertyObject);
            }
        }

        for (Table table : this.Tables().getValues()) {
            if (enum6 == Enum6.Zero || table.IsOwnedBy(this)) {
                table.Schema().actionTableStatesDefinition(propertyAction::apply);
            }
        }

        for (WorkSchedule workSchedule : this.getWorkSchedulesUtils().getWorkSchedules().getValues()) {
            propertyAction.apply(workSchedule);
            if (enum6 == Enum6.Zero || workSchedule.IsOwnedBy(this)) {
                propertyAction.apply(workSchedule);
                for (WorkPeriodException workPeriodException : workSchedule.WorkPeriodExceptions().getValues()) {
                    if (enum6 == Enum6.Zero || workPeriodException.IsOwnedBy(this)) {
                        for (IListener listener : workPeriodException.createExpressionActionListener()) {
                            if (listener != null) {
                                propertyAction.apply(listener);
                            }
                        }
                    }
                }
            }
        }

        for (DayPattern dayPattern : this.getWorkSchedulesUtils().DayPatterns().getValues()) {
            if (enum6 == Enum6.Zero || dayPattern.IsOwnedBy(this)) {
                for (WorkPeriod workPeriod : dayPattern.WorkPeriods().getValues()) {
                    if (enum6 == Enum6.Zero || workPeriod.IsOwnedBy(this)) {
                        for (IListener listener : workPeriod.createExpressionActionListener()) {
                            if (listener != null) {
                                propertyAction.apply(listener);
                            }
                        }
                    }
                }
            }
        }

        for (ResourceLogExpression logExpression : this.getResourceLogExpressions().getValues()) {
            if (enum6 == Enum6.Zero || logExpression.IsOwnedBy(this)) {
                propertyAction.apply(logExpression);
            }
        }

        for (ExpressionFunction expressionFunction : this.ExpressionFunctions().getValues()) {
            if (enum6 == Enum6.Zero || expressionFunction.IsOwnedBy(this)) {
                IListener listener = expressionFunction.createExpressionActionListener();
                if (listener != null) {
                    propertyAction.apply(listener);
                }
            }
        }

        for (AbsInputParameter absInputParameter : this.InputParameters().getValues()) {
            if (enum6 == Enum6.Zero || absInputParameter.IsOwnedBy(this)) {
                IListener childObjectChangeListener = absInputParameter.ChildObjectChangeListener();
                if (childObjectChangeListener != null) {
                    propertyAction.apply(childObjectChangeListener);
                }
            }
        }

        super.getPropertyDefinitions().process(propertyAction::apply);
        super.getPropertyDefinitions().processOverridesListener(propertyAction::apply);

        if (this.getAnimationSetup() instanceof IListener) {
            propertyAction.apply((IListener) (this.getAnimationSetup()));
        }

        if (this.getAnimationSetup() instanceof IListener) {
            propertyAction.apply((IListener) (this.getDefaultAdditionalSymbol()));
        }
    }

    private IContextBound getContextBound3() {
        return this.contextBound3;
    }

    public IContextBound getDashboard() {
        return this.dashboard;
    }

    public IContextBound getDefaultAdditionalSymbol() {
        return this.defaultAdditionalSymbol;
    }

    public IContextBound getAnimationSetup() {
        return this.animationSetup;
    }


    private void propertyInstanceAction(Action<Properties> action) {
        this.actionProperty(propertyObject -> action.apply(propertyObject.properties));

        for (Table table : this.Tables().getValues()) {
            try (var a = table.Data().dispose()) {
                for (Properties properties : table.Data().Rows().getValues()) {
                    action.apply(properties);
                }
            }
        }
    }

    private void actionProperty(Action<AbsPropertyObject> action) {
        for (ProcessProperty processProperty : this.processProperties) {
            action.apply(processProperty);
            for (AbsBaseStepProperty absBaseStepProperty : processProperty.AbsBaseStepProperties) {
                action.apply(absBaseStepProperty);
            }
        }
        for (IntelligentObject intelligentObject : this.childrenObject) {
            action.apply(intelligentObject);
        }
        for (AbsIntelligentPropertyObject absIntelligentPropertyObject : this.elements.getValues()) {
            action.apply(absIntelligentPropertyObject);
        }
        for (Node node : this.nodes) {
            action.apply(node);
        }
        for (NodeClassProperty nodeClassProperty : this.transferPoints) {
            action.apply(nodeClassProperty);
        }
        for (AbsListProperty absListProperty : this.Lists().getValues()) {
            action.apply(absListProperty);
        }
        if (this.instance != null) {
            action.apply(this.instance);
        }
    }


    public boolean haveChildrenNode(IntelligentObjectDefinition intelligentObjectDefinition) {
        List<AbsPropertyObject> source = super.getAssociatedInstances();
        var num = source.stream()
                .filter(t -> !(t instanceof Node) || ((Node) t).NodeClassProperty == null)
                .filter(propertyObject -> intelligentObjectDefinition.getRelation(propertyObject.Parent()) == Relation.Same)
                .count();
        return num > 0;
    }

    public Relation getRelation(IntelligentObjectDefinition intelligentObjectDefinition) {
        if (intelligentObjectDefinition != null) {
            if (intelligentObjectDefinition.sameGuid(this)) {
                if (this.getRevision() != intelligentObjectDefinition.getRevision()) {
                    return Relation.DiffVersion;
                }
                return Relation.Same;
            }

            IntelligentObjectDefinition objectDefinition = this.parent;
            if (objectDefinition != null) {
                return objectDefinition.getRelation(intelligentObjectDefinition);
            }
            if (this.IsImplicitlySubclassedFrom(intelligentObjectDefinition)) {
                return Relation.Same;
            }
        }
        return Relation.None;
    }

    public void removeNameDefinition(IntelligentObjectDefinition intelligentObjectDefinition) {
        // TODO: 2021/11/30 
    }

    public boolean sameGuidAndVersion(IntelligentObjectDefinition intelligentObjectDefinition) {
        return this.sameGuid(intelligentObjectDefinition) && this.getRevision() == intelligentObjectDefinition.getRevision();

    }

    public boolean isVersionBigger(IntelligentObjectDefinition intelligentObjectDefinition) {
        return intelligentObjectDefinition != null &&
                intelligentObjectDefinition != this &&
                intelligentObjectDefinition.getGuid() == this.getGuid() &&
                intelligentObjectDefinition.getRevision() > this.getRevision();
    }

    public void method_83(IntelligentObjectDefinition objectDefinition,
                          IntelligentObjectDefinition intelligentObjectDefinition, boolean b) {
    }

    public boolean propertyVersionDifferent(IntelligentObjectDefinition intelligentObjectDefinition) {
        return super.getAssociatedInstances().stream()
                .anyMatch(propertyObject -> intelligentObjectDefinition.getRelation(propertyObject.Parent()) == Relation.DiffVersion);
    }

    public void changeGuidVersionIndex(IntelligentObjectDefinition definition, String name) {
    }

    public void changeNameSome(IntelligentObjectDefinition definition) {
    }

    public void changeNameSome(IntelligentObjectDefinition intelligentObjectDefinition, String string_0) {
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectLibraryDefinitionChange(intelligentObjectDefinition, Enum38.One));
        }
        this.flashState();
    }

    public void NotifyIntelligentObjectPropertyErrorEvent(IntelligentObjectProperty intelligentObjectProperty) {
        this.NotifyIntelligentObjectPropertyErrorEventInner(intelligentObjectProperty);
    }

    private void NotifyIntelligentObjectPropertyErrorEventInner(IntelligentObjectProperty intelligentObjectProperty) {
        if (!this.updatingStatus()) {
            EventHandler.fire(this.propertyErrorHandler, this,
                    new IntelligentObjectDefinition.IntelligentObjectPropertyEventArgs(intelligentObjectProperty));
        }
    }

    public void PropertyChangeError(IGridObject gridObject, String name) {
        this.propertyChangeError(gridObject, name);
    }

    private void propertyChangeError(IGridObject gridObject, String name) {
        if (this.updatingStatus()) {
            return;
        }

        EventHandler.fire(this.propertyErrorEventHandler, this,
                new IntelligentObjectDefinition.GridObjectEventArgs(gridObject,
                        name));
    }

    public void PropertyChange(IGridObject gridObject, String propertyName) {
        this.propertyChange(gridObject, propertyName);
    }

    private void propertyChange(IGridObject gridObject, String propertyName) {
        if (this.updatingStatus()) {
            return;
        }

        EventHandler.fire(this.propertyChangeEvent, this,
                new IntelligentObjectDefinition.GridObjectEventArgs(gridObject,
                        propertyName));
    }

    public void SubmitToSearch(Action<ISearch> action) {
        this.actionProperty(action::apply);
        for (var propertyDefinition : this.PropertyDefinitions()) {
            StringPropertyDefinition stringPropertyDefinition = (StringPropertyDefinition) propertyDefinition;
            action.apply(stringPropertyDefinition);
        }
        for (IStateDefinition stateDefinition : this.StateDefinitions()) {
            BaseStatePropertyObject baseStatePropertyObject = (BaseStatePropertyObject) stateDefinition;
            action.apply(baseStatePropertyObject);
        }
        for (IEventDefinition eventDefinition : this.EventDefinitions()) {
            EventDefinition definition = (EventDefinition) eventDefinition;
            action.apply(definition);
        }
        for (TokenDefinition tokenDefinition : this.getTokens().getValues()) {
            action.apply(tokenDefinition);
        }
        for (Table table : this.Tables().getValues()) {
            action.apply(table);
        }
        for (RateTable rateTable : this.RateTables().getValues()) {
            action.apply(rateTable);
        }
        for (DayPattern dayPattern : this.getWorkSchedulesUtils().DayPatterns().getValues()) {
            action.apply(dayPattern);
        }
        for (WorkSchedule workSchedule : this.getWorkSchedulesUtils().getWorkSchedules().getValues()) {
            action.apply(workSchedule);
        }
        for (ChangeoverMatrix changeoverMatrix : this.getChangeoverMatrices().getValues()) {
            action.apply(changeoverMatrix);
        }
        for (UserFunction userFunction : this.getFunctionTables().getValues()) {
            action.apply(userFunction);
        }
        for (ExpressionFunction expressionFunction : this.ExpressionFunctions().getValues()) {
            action.apply(expressionFunction);
        }
        for (AbsInputParameter absInputParameter : this.InputParameters().getValues()) {
            action.apply(absInputParameter);
        }
    }

    public void limit(ActiveModel activeModel) {
        if (this.countLimitClass != null) {
            this.countLimitClass.limit(activeModel);
        }
    }

    public void flushResourceLogExpressionTable() {
        for (Table table : this.Tables().getValues()) {
            table.resetRowsBindings();
        }
        for (ResourceLogExpression expression : this.getResourceLogExpressions().getValues()) {
            expression.propertyDisplayNameChange();
        }
    }

    public void NotifyIntelligentObjectPropertyChangeEvent(IntelligentObjectProperty intelligentObjectProperty) {
        this.NotifyIntelligentObjectPropertyChangeEventInner(intelligentObjectProperty);
    }

    private void NotifyIntelligentObjectPropertyChangeEventInner(IntelligentObjectProperty intelligentObjectProperty) {
        if (!this.updatingStatus()) {
            EventHandler.fire(this.objectPropertyChangeEvent, this,
                    new IntelligentObjectDefinition.IntelligentObjectPropertyEventArgs(intelligentObjectProperty));
        }
    }

    public void recordWarning(Warning warning) {
        String instanceNames = warning.removeInstanceNamesPeek();
        if (instanceNames != null) {
            IntelligentObject intelligentObject = this.getObjectsByName(instanceNames);
            if (intelligentObject == null) {
                intelligentObject = this.getNodeByName(instanceNames);
            }
            if (intelligentObject != null) {
                this.recordWarning(warning, intelligentObject);
            }
        }
    }

    void recordWarning(Warning warning, org.licho.brain.concrete.IntelligentObject intelligentObject) {
        intelligentObject.addWarning(warning);
        this.bool_7 = true;
    }

    public Node getNodeByName(String instanceNames) {
        return (Node) this.getNameUtil().getObjects(instanceNames).stream()
                .filter(Node.class::isInstance)
                .findFirst()
                .orElse(null);
    }

    private IntelligentObject getObjectsByName(String instanceNames) {
        return (IntelligentObject) this.getNameUtil().getObjects(instanceNames).stream()
                .filter(IntelligentObject.class::isInstance)
                .filter(t -> !(t instanceof Node))
                .findFirst()
                .orElse(null);
    }

    public void readXmlDefinition(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, Enum8 enum8) {
        var inner = new Object() {
            public final IntelligentObjectXml readContext = intelligentObjectXml;
            public final IntelligentObjectDefinition.Enum8 createType = enum8;
            private boolean x;
            public final Action<Boolean> action = (Boolean b) -> {
                IntelligentObjectDefinition.this.ResourceLogic(b);
                x = true;
            };
        };

        try (var a = inner.readContext.createFacadeWrapper()) {
            var id = xmlReader.GetAttribute("Id");
            var revision = xmlReader.GetAttribute("Revision");
            boolean[] bAvailableInExpress = new boolean[]{false};
            int[] canUseInExpressVersion = new int[]{-1};

            SomeXmlOperator.readXmlBooleanAttribute(xmlReader, "AvailableForExpress", b -> bAvailableInExpress[0] = b);
            SomeXmlOperator.readXmlIntAttribute(xmlReader, "AvailableForExpressRevision",
                    i -> canUseInExpressVersion[0] = i);

            IContextBound[] array = new IContextBound[]{
                    this.getAnimationSetup(),
                    this.getDefaultAdditionalSymbol(),
                    this.getDashboard()
            };

            IDisposable[] contextBounds = Arrays.stream(array).filter(java.util.Objects::nonNull)
                    .map(IContextBound::LoadTransaction).toArray(IDisposable[]::new);

            if (inner.createType == Enum8.Zero) {
                try (var _1 = this.getActionRun().disposable()) {
                    try (var _2 = this.createDefinitionOperator()) {
                        SomeXmlOperator.xmlReaderElementOperator(xmlReader, xmlReader.Name(), (XmlReader attr) -> {
                            var author = attr.GetAttribute("Author");
                            if (!Strings.isNullOrEmpty(author)) {
                                this.Author(author);
                            }

                            var description = attr.GetAttribute("Description");
                            if (!Strings.isNullOrEmpty(description)) {
                                this.Author(description);
                            }

                            SomeXmlOperator.readXmlDoubleAttribute(attr, "Version", this::Version);
                            boolean haveGrandpa = this.parent == null || this.parent.notHaveParent();
                            if (haveGrandpa) {
                                inner.x = false;
                                SomeXmlOperator.readXmlBooleanAttribute(attr, "ResourceLogic", inner.action);
                                if (!inner.x) {
                                    this.ResourceLogic(false);
                                }
                            }

                            SomeXmlOperator.readXmlBooleanAttribute(attr, "CheckBaseForAdvancedProperties", c ->
                                    this.checkBaseForAdvancedProperties = c);
                            this.ReadAttributesFromXml(attr, inner.readContext);
                        }, body -> SomeXmlOperator.xmlReaderElementOperator(body, "Keywords", null,
                                keywordBody -> SomeXmlOperator.readXMLText(keywordBody, this::Keywords)) ||
                                SomeXmlOperator.xmlReaderElementOperator(body, "Categories", null,
                                        catBody -> SomeXmlOperator.readXMLText(catBody,
                                                this::Categories)) ||
                                this.readXmlProtectedContent(body, inner.readContext, inner.createType) ||
                                this.readXmlProtection(body, inner.readContext));
                    }
                }
                this.actionProperty(t -> DoVersionUpgrade(inner.readContext));
            } else {
                xmlReader.Skip();
            }

            for (IDisposable disposable : contextBounds) {
                disposable.Dispose();
            }

            if (!Strings.isNullOrEmpty(id)) {
                this.setGuid(new Guid(id));
            }

            if (!Strings.isNullOrEmpty(revision)) {
                try {
                    int result = Integer.parseInt(revision);
                    this.setRevision(result);
                } catch (NumberFormatException e) {
                    log.error("revision convert to int error");
                }
            }

            this.availableInExpress = bAvailableInExpress[0];
            this.canUseInExpressVersion = canUseInExpressVersion[0];
            this.interactiveResultsVersion = 0;
            this.experimentResultsVersion = 0;
            this.planResultsVersion = 0;
            this.riskResultsVersion = 0;
            this.resetNameCollections();
            this.isChanged = false;
        }
    }

    void resetNameCollections() {
        this.getNameUtil().clear();
        for (String name_ : super.getNames()) {
            this.addProcessPropertyByName(name_, this.getBuiltInFunction());
        }
        this.registerSomeTypeObjectsName(object -> this.addProcessPropertyByName(object.InstanceName(), object));
        for (StringPropertyDefinition stringPropertyDefinition : super.getPropertyDefinitions().getValues()) {
            this.addProcessPropertyByName(stringPropertyDefinition.Name(), stringPropertyDefinition);
        }
        for (BaseStatePropertyObject baseStatePropertyObject :
                super.getStateDefinitions().StateProperties.getValues()) {
            this.addProcessPropertyByName(baseStatePropertyObject.Name(), baseStatePropertyObject);
        }
        for (Object obj : super.getEventDefinitions()) {
            EventDefinition eventDefinition = (EventDefinition) obj;
            this.addProcessPropertyByName(eventDefinition.Name(), eventDefinition);
        }
        for (ExpressionFunction expressionFunction : this.ExpressionFunctions().getValues()) {
            this.addProcessPropertyByName(expressionFunction.Name(), expressionFunction);
        }
        for (AbsInputParameter absInputParameter : this.InputParameters().getValues()) {
            this.addProcessPropertyByName(absInputParameter.Name(), absInputParameter);
        }
        for (UserFunction functionTable : this.getFunctionTables().getValues()) {
            this.addProcessPropertyByName(functionTable.Name(), functionTable);
        }
        for (RateTable rateTable : this.RateTables().getValues()) {
            this.addProcessPropertyByName(rateTable.Name(), rateTable);
        }
        for (ChangeoverMatrix changeoverMatrix : this.getChangeoverMatrices().getValues()) {
            this.addProcessPropertyByName(changeoverMatrix.Name(), changeoverMatrix);
        }
        for (DayPattern dayPattern : this.getWorkSchedulesUtils().DayPatterns().getValues()) {
            this.addProcessPropertyByName(dayPattern.Name(), dayPattern);
        }
        for (WorkSchedule workSchedule : this.getWorkSchedulesUtils().getWorkSchedules().getValues()) {
            this.addProcessPropertyByName(workSchedule.Name(), workSchedule);
        }
        for (NodeClassProperty nodeClassProperty : this.transferPoints) {
            this.addProcessPropertyByName(nodeClassProperty.InstanceName(), nodeClassProperty);
        }
        for (AbsListProperty absListProperty : this.Lists().getValues()) {
            this.addProcessPropertyByName(absListProperty.Name(), absListProperty);
        }
        for (Table table : this.Tables().getValues()) {
            this.addProcessPropertyByName(table.Name(), table.getTableProperty());
        }
    }

    public void registerSomeTypeObjectsName(Action<AbsIntelligentPropertyObject> action) {
        for (ProcessProperty processProperty : this.processProperties) {
            action.apply(processProperty);
        }
        for (IntelligentObject intelligentObject : this.childrenObject) {
            action.apply(intelligentObject);
        }
        for (AbsIntelligentPropertyObject absIntelligentPropertyObject : this.elements.getValues()) {
            action.apply(absIntelligentPropertyObject);
        }
        for (Node node : this.nodes) {
            action.apply(node);
        }
    }

    private IntelligentObjectDefinition.BuiltInFunction getBuiltInFunction() {
        return FunctionProvider.InstanceStatic(IntelligentObjectDefinition.BuiltInFunction.class);
    }

    private void DoVersionUpgrade(IntelligentObjectXml readContext) {
    }

    private boolean readXmlProtection(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Protection", attr -> {
            var attribute = xmlReader.GetAttribute("Challenge");
            if (!Strings.isNullOrEmpty(attribute)) {
                this.protection = attribute;
            }
        }, null) ||
                this.getInternalReference().readXml(xmlReader, intelligentObjectXml) ||
                this.UnitFilter().readXml(xmlReader, intelligentObjectXml) ||
                SomeXmlOperator.xmlReaderElementOperator(xmlReader,
                        "ChangeDescriptions", null, (XmlReader body) -> {
                            ChangeDescription changeDescription = ChangeDescription.readXml(xmlReader);
                            if (changeDescription != null) {
                                this.changeDescriptions.add(changeDescription);
                                return true;
                            }
                            return false;
                        }) ||
                SomeXmlOperator.xmlReaderElementOperator(xmlReader, "ChangeStringReplacements", null,
                        (XmlReader body) -> {
                            ChangeStringReplacement changeStringReplacement =
                                    ChangeStringReplacement.xmlReader(xmlReader);
                            if (changeStringReplacement != null) {
                                this.changeStringReplacements.add(changeStringReplacement);
                                return true;
                            }
                            return false;
                        }) ||
                super.getPropertyDefinitions().readXml(xmlReader, intelligentObjectXml, this) ||
                super.getStateDefinitions().readXml(xmlReader, intelligentObjectXml) ||
                super.getEventDefinitions().readXml(xmlReader, intelligentObjectXml) ||
                this.ExpressionFunctions().readXml(xmlReader, intelligentObjectXml) ||
                this.InputParameters().xmlReaderInputParameters(xmlReader, intelligentObjectXml) ||
                SomeXmlOperator.xmlReaderElementOperator(xmlReader, "UsedObjects", null,
                        (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(body, "UsedObject",
                                (XmlReader usedAttr) -> {
                                    String attribute = usedAttr.GetAttribute("Name");
                                    IntelligentObjectDefinition intelligentObjectDefinition =
                                            intelligentObjectXml.readIntelligentObjectDefinitionByName(attribute,
                                                    this.activeModel, null, false, true);
                                    if (intelligentObjectDefinition != null) {
                                        this.getInternalReference().updateSameIntelligentObjectDefinition(intelligentObjectDefinition);
                                    }
                                }, null)) ||
                SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Elements", null,
                        (XmlReader body) -> AbsIntelligentPropertyObject.readXmlAbsIntelligentObjectProperty(xmlReader,
                                intelligentObjectXml, this) != null) ||
                SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Processes", null,
                        (XmlReader body) -> ProcessProperty.readXml(xmlReader, intelligentObjectXml, this,
                                ProcessProperty.Enum50.Zero) != null) ||
                SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Objects", null, (XmlReader body) ->
                {
                    IntelligentObject intelligentObject = instance.readXml(xmlReader, intelligentObjectXml
                            , this);
                    if (intelligentObject != null) {
                        return true;
                    }
                    return intelligentObjectXml.xmlReaderOutXmlOperator(xmlReader,
                            (XmlReader x) -> instance.readXml(x, intelligentObjectXml, this) != null);
                }) ||
                this.Lists().readXml(xmlReader, intelligentObjectXml) ||
                this.getTokens().readXml(xmlReader, intelligentObjectXml) ||
                SomeXmlOperator.xmlReaderElementOperator(xmlReader, "External", null,
                        (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(body, "TransferPoints", null,
                                (XmlReader tpBody) -> NodeClassProperty.readXml(tpBody, intelligentObjectXml,
                                        this, NodeClassProperty.Enum27.Zero) != null) ||
                                SomeXmlOperator.xmlReaderElementOperator(body, "TransferPointOverrides", null,
                                        (XmlReader tpoBody) -> SomeXmlOperator.xmlReaderElementOperator(tpoBody,
                                                "TransferPointOverride", (XmlReader overRideAttr) ->
                                                {
                                                    // TODO: 2022/1/17
                                                }, null)) ||
                                intelligentObjectXml.readAdditionalSymbol(xmlReader, this)) ||
                this.Tables().readXml(xmlReader, intelligentObjectXml) ||
                this.getFunctionTables().readXml(xmlReader, intelligentObjectXml) ||
                this.RateTables().readXml(xmlReader, intelligentObjectXml) ||
                this.getWorkSchedulesUtils().readXml(xmlReader, intelligentObjectXml, this) ||
                this.getChangeoverMatrices().readXml(xmlReader, intelligentObjectXml) ||
                this.getResourceLogExpressions().readXml(xmlReader) ||
                intelligentObjectXml.vmethod_5(xmlReader, this);


    }


    private boolean readXmlProtectedContent(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                            Enum8 enum8) {
        var inner = new Object() {
            public final XmlReader reader = xmlReader;
            public final IntelligentObjectXml readContext = intelligentObjectXml;
            public final IntelligentObjectDefinition.Enum8 createType = enum8;
            public boolean x;
            public final Action<Boolean> action =
                    (Boolean b) -> {
                        IntelligentObjectDefinition.this.ResourceLogic(b);
                        x = true;
                    };
        };

//        return SomeXmlOperator.xmlReaderElementOperator(inner.reader, "ProtectedContent", null,
//                (XmlReader body)
//                        -> SomeXmlOperator.xmlReaderElementOperator(inner.reader, "FileRef",
//                        (XmlReader fileattr) -> {
//                            var attribute = inner.reader.GetAttribute("Name");
//                            if (!Strings.isNullOrEmpty(attribute)) {
//                                try (InOutputStream stream = inner.readContext.getFiles().OpenReadStream(attribute)) {
//                                    if (stream == null)
//                                    {return;}
//
//                                    try()
//                                }
//                            }
//
//                        }
        return true;
    }

    protected void ReadAttributesFromXml(XmlReader attr, IntelligentObjectXml readContext) {
    }

    StringPropertyDefinition findStringPropertyDefinitionInfoByName(String name,
                                                                    PropertyDefinitions propertyDefinitions,
                                                                    IntelligentObjectXml intelligentObjectXml) {
        StringPropertyDefinition propertyDefinitionForLoad = this.GetPropertyForLoad(name, intelligentObjectXml);
        if (propertyDefinitionForLoad != null) {
            return propertyDefinitionForLoad;
        }
        return propertyDefinitions.findStringPropertyDefinitionInfoByName(name);
    }

    public void updateObjectName(RateTable rateTable, String name) {
        this.updateObjectName(name, rateTable.Name(), rateTable);
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectRateTableChange(rateTable, Enum38.One));
        }
        this.flashState();
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subClass) ->
                subClass.updateObjectName(rateTable, name));
    }

    public void updateObjectName(ExpressionFunction expressionFunction, String name) {
        this.updateObjectName(name, expressionFunction.Name(), expressionFunction);
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectExpressionFunctionChange(expressionFunction, Enum38.One));
        }
        this.flashState();
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subClass) ->
        {
            subClass.updateObjectName(expressionFunction, name);
        });
    }

    private void propertyListenHandle_1(Action<IListener> propertyAction) {
        this.propertyListenHandle_1(propertyAction, Enum6.Zero);
    }

    private void propertyListenHandle_1(Action<IListener> propertyAction, Enum6 enum6) {
        this.propertyListenHandle(propertyAction, enum6, true);
    }

    private void propertyListenHandle(Action<IListener> propertyAction, IntelligentObjectDefinition.Enum6 enum6,
                                      boolean b) {
        this.propertyListenHandle(propertyAction, enum6);
    }

    private boolean noUpdating() {
        return !this.updating();
    }

    private void updateObjectName(String oldName, String newName, Object target) {
        this.getNameUtil().updateObjectName(oldName, newName, target);
        ISearch search = (ISearch) target;
        if (search != null && this.activeModel != null && this.activeModel.getDefinition() == this && this.activeModel.projectDefinition != null) {
            this.activeModel.projectDefinition.getItemEditPolicy().method_0(search, ItemEditPolicy.name,
                    this.activeModel);
        }

    }

    public void updateExpressionFunctionRecursion(ExpressionFunction expressionFunction) {
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectExpressionFunctionChange(expressionFunction, Enum38.Two));
        }
        this.flashState();
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subClass) ->
                subClass.updateExpressionFunctionRecursion(expressionFunction));
    }

    public void updateAbsInputParameter(AbsInputParameter absInputParameter, String name) {
        this.updateObjectName(name, absInputParameter.Name(), absInputParameter);
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectInputParameterChange(absInputParameter, Enum38.One));
        }
        this.flashState();
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subClass) ->
                subClass.updateAbsInputParameter(absInputParameter, name));

    }

    public ProcessProperty overProcessPropertyFalse(ProcessProperty property) {
        return this.overProcessProperty(property, false);
    }


    public ProcessProperty overProcessProperty(ProcessProperty processProperty, boolean updateConfig) {
        return this.overProcessProperty(processProperty, null, updateConfig);
    }

    public ProcessProperty overProcessProperty(ProcessProperty original, ProcessProperty overrideProperty,
                                               boolean updateConfig) {
        boolean noOverride = overrideProperty == null;
        if (noOverride && this.processProperties.get(original.processPropertyIndex) != original) {
            return null;
        }
        this.RemoveErrorByObject(original);
        ProcessProperty processProperty = this.processProperties.get(original.processPropertyIndex);
        if (noOverride) {
            overrideProperty = new ProcessProperty(original.InstanceName(), original.Scope());
            overrideProperty.processPropertyIndex = original.processPropertyIndex;
            overrideProperty.Parent(this);
            overrideProperty.isOverride = true;
            overrideProperty.properties.init();
            overrideProperty.processIndex = processProperty.processIndex;
            overrideProperty.objectClass = processProperty.objectClass;
        }
        if (noOverride) {
            overrideProperty.createStepProperty("Begin", BeginStepProperty.class);
            overrideProperty.createStepProperty("End", EndStepProperty.class);
        }
        if (updateConfig) {
            this.updateConfig(processProperty, overrideProperty);
        }
        this.processProperties.add(original.processPropertyIndex, overrideProperty);
        this.buildOverrideProcessProperty(original, overrideProperty);
        return overrideProperty;
    }

    private void buildOverrideProcessProperty(ProcessProperty source, ProcessProperty overrideProcessProperty) {
        this.removeObjectByName(source.InstanceName(), source);
        this.RemoveRelationExistingRunSpace(source);
        this.addProcessPropertyByName(overrideProcessProperty.InstanceName(), overrideProcessProperty);
        if (!IntelligentObjectDefinition.updatingLibrary) {
            this.UpdateForParentObjectMemberElementChange(source);
        }
        this.injectRunSpaceToProcessProperty(overrideProcessProperty);
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition objectDefinition) ->
        {
            objectDefinition.OverrideProcessProperty(source, overrideProcessProperty);
        });
        this.flashState();
        this.triggerEventHandler_25(source, overrideProcessProperty);

    }

    private void triggerEventHandler_25(ProcessProperty source, ProcessProperty overrideProcessProperty) {
//        if (this.eventHandler_25 != null)
//        {
//            this.eventHandler_25(this, new IntelligentObjectDefinition.ProcessPropertyDoubleEventArgs(source,
//            overrideProcessProperty));
//        }
        // TODO: 2022/1/6
    }

    private void OverrideProcessProperty(ProcessProperty source, ProcessProperty processProperty) {
        if (this.processProperties.get(source.processPropertyIndex) == source) {
            this.processProperties.add(source.processPropertyIndex, processProperty);
            this.buildOverrideProcessProperty(source, processProperty);
        }
    }

    private void UpdateForParentObjectMemberElementChange(AbsIntelligentPropertyObject intelligentPropertyObject) {
        this.propertyListenHandle_1((IListener listener) ->
                listener.UpdateForParentObjectMemberElementChange(intelligentPropertyObject, Enum38.Zero));
    }

    private void RemoveRelationExistingRunSpace(AbsIntelligentPropertyObject absIntelligentPropertyObject) {
        this.processRunSpace((IntelligentObjectRunSpace runSpace) ->
                runSpace.RemoveChildrenExistingRunSpace(absIntelligentPropertyObject));

    }

    private void removeObjectByName(String instanceName, Object target) {
        this.getNameUtil().removeObjectByName(name, target);
        ISearch search = (ISearch) target;
        if (search != null && this.activeModel != null && this.activeModel.getDefinition() == this && this.activeModel.projectDefinition != null) {
            this.activeModel.projectDefinition.getItemEditPolicy().remove(search);
        }

    }

    private void updateConfig(ProcessProperty source, ProcessProperty overrideProperty) {
        StringBuilder stringBuilder = new StringBuilder();
//        using (XmlWriter xmlWriter = XmlWriter.Create(stringBuilder, XmlSettings.xmlWriterSettings_0))
//    {
//        source.writeXml(xmlWriter, new CommonItems());
//    }
//    using (StringReader stringReader = new StringReader(stringBuilder.ToString()))
//    {
//        IntelligentObjectXml param2 = new IntelligentObjectXml((IntelligentObjectXml.ModeType)0);
//        using (XmlReader xmlReader = XmlReader.Create(stringReader, XmlSettings.Pure))
//    {
//        	    overrideProcessProperty.readxml(xmlReader, param2);
//        	}
//    }
        // TODO: 2022/1/6
    }

    private void RemoveErrorByObject(ProcessProperty processProperty) {
        processProperty.AbsBaseStepProperties.forEach(t -> this.activeModel.getErrors().RemoveErrorByObject(t));
        this.activeModel.getErrors().RemoveErrorByObject(processProperty);
    }

    public ProcessProperty overProcessProperty(ObjectClass objectClass, int offset) {
        int index = IntelligentObjectDefinition.getObjectClassIndex(objectClass, offset);
        ProcessProperty processProperty = this.processProperties.get(index);
        return this.overProcessPropertyFalse(processProperty);
    }

    private static int getObjectClassIndex(ObjectClass objectClass, int offset) {
        if (IntelligentObjectDefinition.standard == null) {
            IntelligentObjectDefinition.standard = new int[7];
            IntelligentObjectDefinition.standard[0] = 0;
            int[] standardArray = IntelligentObjectDefinition.standard;
            int index = 1;
            ObjectClass[] objectClasses = new ObjectClass[1];
            standardArray[index] = IntelligentObjectDefinition.getEnumSum(objectClasses);
            IntelligentObjectDefinition.standard[6] = IntelligentObjectDefinition.getEnumSum(ObjectClass.Object,
                    ObjectClass.Fixed);
            IntelligentObjectDefinition.standard[4] = IntelligentObjectDefinition.getEnumSum(ObjectClass.Object,
                    ObjectClass.Fixed);
            int[] standard = IntelligentObjectDefinition.standard;
            int num2 = 5;
            ObjectClass[] interfaceProcessObjectClasses2 = new ObjectClass[1];
            standard[num2] = IntelligentObjectDefinition.getEnumSum(interfaceProcessObjectClasses2);
            IntelligentObjectDefinition.standard[2] = IntelligentObjectDefinition.getEnumSum(ObjectClass.Object,
                    ObjectClass.Agent);
            IntelligentObjectDefinition.standard[3] = IntelligentObjectDefinition.getEnumSum(ObjectClass.Object,
                    ObjectClass.Agent,
                    ObjectClass.Entity);
        }
        return IntelligentObjectDefinition.standard[objectClass.ordinal()] + offset;
    }

    public static int getEnumSum(ObjectClass... objectClasses) {
        int num = 0;
        for (ObjectClass objectClass : objectClasses) {
            Class<? extends Enum> enumType = IntelligentObjectDefinition.getProcessType(objectClass);
            num += enumType.getEnumConstants().length;
        }
        return num;
    }

    public ProcessProperty createProcessProperty() {
        return this.createProcessProperty(super.GetUniqueName("Process"), ElementScope.Private, false);
    }

    public ProcessProperty createProcessProperty(String name, ElementScope elementScope, boolean autoCreated) {
        return this.createProcessProperty(name, elementScope, null, autoCreated);
    }

    public void triggerRestoreAbsBaseStepEvent(AbsBaseStepProperty absBaseStepProperty) {
// TODO: 2022/1/12 
    }

    public void triggerEvent27(AbsBaseStepProperty absBaseStepProperty) {
        // TODO: 2022/1/12
    }

    public void updateProcessProperty(ProcessProperty processProperty) {
        if (processProperty.Parent() == this) {
            if (!processProperty.isOverride) {
                this.destroyInstance(processProperty, true);
                this.updateProcessProperty(processProperty, true);
                return;
            }
            if (processProperty.isOverride && processProperty.processIndex != -1) {
                this.getProcessPropertyHierarchy(processProperty);
            }
        }
    }

    public IntelligentObjectDefinition.IProcessPropertyHierarchy getProcessPropertyHierarchy(ProcessProperty processProperty) {
        ProcessProperty property = this.getParentProcessProperty(processProperty);
        return new IntelligentObjectDefinition.ProcessPropertyHierarchy(this, processProperty, property);
    }

    private ProcessProperty getParentProcessProperty(ProcessProperty processProperty) {
        this.RemoveErrorByObject(processProperty);
        ProcessProperty parentProcessProperty =
                this.parent.processProperties.get(processProperty.processPropertyIndex);
        this.processProperties.add(processProperty.processPropertyIndex, parentProcessProperty);
        this.buildOverrideProcessProperty(processProperty, parentProcessProperty);
        this.resetAllPropertiesErrors(parentProcessProperty);
        return parentProcessProperty;
    }

    private void resetAllPropertiesErrors(ProcessProperty processProperty) {
        processProperty.AbsBaseStepProperties.forEach(this::resetAllPropertiesErrors);
        resetAllPropertiesErrors(processProperty);
    }

    private void resetAllPropertiesErrors(IGridObject gridObject) {
        if (this.updatingStatus()) {
            return;
        }
        if (this.activeModel != null) {
            this.activeModel.getErrors().ResetAllPropertiesError(gridObject);
        }
    }

    private void updateProcessProperty(ProcessProperty property, boolean param1) {
        ProcessProperty processProperty = this.processProperties.get(property.processPropertyIndex);
        if (processProperty != property) {
            this.destroyInstance(processProperty, param1);
        }
        this.processProperties.remove(processProperty);
        this.removeObjectByName(processProperty.InstanceName(), processProperty);
        this.reassignProcessPropertyIndex();
        this.UpdateForParentObjectMemberElementChange(processProperty);
        // TODO: 2022/1/12 
//        if (this.eventHandler_7 != null) {
//            this.eventHandler_7(this, new IntelligentObjectDefinition.ElementEventArgs(property));
//        }
        this.recursiveUpdateProcessProperty(property, param1);
    }

    private void recursiveUpdateProcessProperty(ProcessProperty processProperty, boolean param1) {
        this.removeObjectByName(processProperty.InstanceName(), processProperty);
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subclass) ->
        {
            subclass.updateProcessProperty(processProperty, param1);
        });
        this.resetTable(255);
    }

    private void destroyInstance(ProcessProperty processProperty, boolean param1) {
        if (processProperty.Parent() == this) {
            if (param1) {
                processProperty.DestroyInstance();
            }
            this.RemoveRelationExistingRunSpace(processProperty);
            if (param1) {
                processProperty.assignerDefinition.DestroyInstance(processProperty);
            }
            processProperty.Parent(null);
        }
    }

    public IntelligentObject createIntelligentObject(IntelligentObjectDefinition intelligentObjectDefinition,
                                                     String name,
                                                     ElementScope elementScope, Enum5 enum5) {

        if (!this.equalOrChild(intelligentObjectDefinition)) {
            return null;
        }
        intelligentObjectDefinition =
                this.getInternalReference().updateSameIntelligentObjectDefinition(intelligentObjectDefinition);
        if (intelligentObjectDefinition instanceof NodeDefinition nodeDefinition) {
            return this.createNode(nodeDefinition, name, elementScope);
        }
        IntelligentObject intelligentObject = (IntelligentObject) intelligentObjectDefinition.CreateInstance(name);
        this.addIntelligentObject(intelligentObject, true);
        this.triggerChangeRestoreData(intelligentObject);
        this.triggerSubmitSearchAndBreakpoint(intelligentObject);
        this.addLinkRecursive(intelligentObject, this.getChildrenObject().size() - 1);
        if (enum5 == IntelligentObjectDefinition.Enum5.Zero) {
            for (NodeClassProperty nodeClassProperty : intelligentObjectDefinition.transferPoints) {
                this.setNodeLocation(nodeClassProperty, -1, intelligentObject, null);
            }
        }
        intelligentObject.Scope(elementScope);
        return intelligentObject;

    }

    void setNodeLocation(NodeClassProperty nodeClassProperty, int index, IntelligentObject intelligentObject,
                         NodeDefinition nodeDefinition) {
        this.setNodeLocation(nodeClassProperty, index, intelligentObject, nodeDefinition, ElementScope.Public);
    }

    void setNodeLocation(NodeClassProperty nodeClassProperty, int index, IntelligentObject intelligentObject,
                         NodeDefinition nodeDefinition, ElementScope elementScope) {
        String name = this.getNodeClassName(nodeClassProperty, intelligentObject);
        Node node = this.createNode(name, nodeClassProperty, index, elementScope, intelligentObject, nodeDefinition);
        Location location = ((IntelligentObjectDefinition) intelligentObject.assignerDefinition).
                getLocation(nodeClassProperty).add(this.getDirection(node)).add(intelligentObject.location);
        node.setLocation(location);
    }

    private Direction getDirection(Node node) {
        IntelligentObjectDefinition.DirectionEventArgs directionEventArgs =
                new IntelligentObjectDefinition.DirectionEventArgs(node);
        // TODO: 2022/1/15
//        if (this.changeDirectionHandler != null) {
//            this.changeDirectionHandler(this, directionEventArgs);
//        }
        return directionEventArgs.getDirection();
    }

    public Location getLocation(NodeClassProperty nodeClassProperty) {
        if (nodeClassProperty.Parent() == this) {
            return nodeClassProperty.Location();
        }
        if (nodeClassProperty.index >= 0 && nodeClassProperty.index < this.locations.size()) {
            Location location = this.locations.get(nodeClassProperty.index);
            if (location != null) {
                return location;
            }
        }
        if (this.parent != null) {
            return this.parent.getLocation(nodeClassProperty);
        }
        return nodeClassProperty.Location();
    }

    private String getNodeClassName(NodeClassProperty nodeClassProperty, IntelligentObject intelligentObject) {
        return nodeClassProperty.InstanceName() + "@" + intelligentObject.InstanceName().toString();
    }

    public Node createNode(String name, NodeClassProperty nodeClassProperty, int index, ElementScope elementScope,
                           IntelligentObject intelligentObject, NodeDefinition nodeDefinition) {
        IntelligentObjectDefinition intelligentObjectDefinition = nodeClassProperty.Parent();
        NodeDefinition definition = (NodeDefinition)
                intelligentObjectDefinition.getInternalReference().updateSameIntelligentObjectDefinition((nodeDefinition == null) ? nodeClassProperty.NodeClass() : nodeDefinition);
        if (definition != null) {
            this.getInternalReference().transportDefinitions(definition, null);
        }
        if (definition == null) {
            definition = NodeDefinition.create();
        }
        Node node = (Node) definition.CreateInstance(name);
        node.Scope(elementScope);
        node.IntelligentObject = intelligentObject;
        node.NodeClassProperty = nodeClassProperty;
        if (nodeClassProperty.getPropertyGridObjectOperator() != null) {
            nodeClassProperty.getPropertyGridObjectOperator().updatePropertyStringValue(node.properties);
        }
        this.addNode(node, true);
        if (intelligentObject != null) {
            if (index >= 0) {
                intelligentObject.nodes.add(index, node);
            } else {
                intelligentObject.nodes.add(node);
            }
        }
        this.triggerChangeRestoreData(node);
        this.triggerSubmitSearchAndBreakpoint(node);
        this.addNodeRecursive(node, this.getNodes().size() - 1);
        return node;
    }

    private void addLinkRecursive(IntelligentObject intelligentObject, int index) {
        this.addProcessPropertyByName(intelligentObject.InstanceName(), intelligentObject);
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition intelligentObjectDefinition) ->
                intelligentObjectDefinition.addLink(intelligentObject, index));
        this.flashState();
        this.resetTable(255);
    }

    private void addLink(IntelligentObject intelligentObject, int index) {
        this.getChildrenObject().add(index, intelligentObject);
        Link link = (Link) intelligentObject;
        if (link != null) {
            this.NetworkProperty.addLink(link);
        }
        for (int i = index; i < this.getChildrenObject().size(); i++) {
            this.getChildrenObject().get(i).processPropertyIndex = i;
        }
        this.addLinkRecursive(intelligentObject, index);
    }

    private void addIntelligentObject(IntelligentObject intelligentObject, boolean initProperty) {
        intelligentObject.processPropertyIndex = this.childrenObject.size();
        this.childrenObject.add(intelligentObject);
        intelligentObject.Parent(this);
        this.getNameUtil().addObjectByName(intelligentObject.InstanceName(), intelligentObject);
        if (initProperty) {
            intelligentObject.properties.init();
        }
        this.injectRunSpaceToProcessProperty(intelligentObject);
    }

    public IntelligentObject createIntelligentObject(IntelligentObjectDefinition intelligentObjectDefinition,
                                                     String name,
                                                     ElementScope elementScope) {
        return this.createIntelligentObject(intelligentObjectDefinition, name, elementScope,
                IntelligentObjectDefinition.Enum5.Zero);
    }

    private IntelligentObject createNode(NodeDefinition nodeDefinition, String name, ElementScope elementScope) {
        if (!this.equalOrChild(nodeDefinition)) {
            return null;
        }
        nodeDefinition = (NodeDefinition) this.getInternalReference()
                .updateSameIntelligentObjectDefinition(nodeDefinition);
        Node node = (Node) nodeDefinition.CreateInstance(name);
        node.Scope(elementScope);
        this.addNode(node, true);
        this.triggerChangeRestoreData(node);
        this.triggerSubmitSearchAndBreakpoint(node);
        this.addNodeRecursive(node, this.getNodes().size() - 1);
        return node;
    }

    private void triggerSubmitSearchAndBreakpoint(IntelligentObject intelligentObject) {
        // TODO: 2022/1/12 
    }

    private void triggerChangeRestoreData(IntelligentObject intelligentObject) {
        this.triggerChangeRestoreData(intelligentObject, null);
    }

    private void triggerChangeRestoreData(IntelligentObject intelligentObject, Map<Guid, List<Object>> restoreData) {
        // TODO: 2022/1/15
//        if (this.changeRestoreData != null) {
//            this.changeRestoreData(this,
//                    new IntelligentObjectDefinition.IntelligentObjecRestoreDatatEventArgs(intelligentObject,
//                    restoreData));
//        }
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition t) ->
                t.triggerChangeRestoreData(intelligentObject, null));
    }

    private void addNodeRecursive(Node node, int index) {
        this.addProcessPropertyByName(node.InstanceName(), node);
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition objectDefinition) ->
        {
            objectDefinition.addNode(node, index);
        });
        this.flashState();
        this.resetTable(255);
    }

    private void addNode(Node node, boolean b) {
        node.processPropertyIndex = this.nodes.size();
        this.nodes.add(node);
        node.Parent(this);
        this.getNameUtil().addObjectByName(node.InstanceName(), node);
        if (b) {
            node.properties.init();
        }
        this.injectRunSpaceToProcessProperty(node);
    }


    private void addNode(Node node, int index) {
        this.getNodes().add(index, node);
        for (int i = index; i < this.getNodes().size(); i++) {
            this.getNodes().get(i).processPropertyIndex = i;
        }
        this.addNodeRecursive(node, index);
    }

    private boolean equalOrChild(IntelligentObjectDefinition intelligentObjectDefinition) {
        return intelligentObjectDefinition.getRelation(this) != Relation.Same &&
                !intelligentObjectDefinition.containChild(this);
    }

    public boolean containChild(IntelligentObjectDefinition intelligentObjectDefinition) {
        for (IntelligentObject intelligentObject : this.childrenObject) {
            IntelligentObjectDefinition objectObjectDefinition =
                    (IntelligentObjectDefinition) intelligentObject.assignerDefinition;
            if (objectObjectDefinition.sameGuid(intelligentObjectDefinition) ||
                    objectObjectDefinition.containChild(intelligentObjectDefinition)) {
                return true;
            }
        }
        return false;
    }

    public void NotifyAddLink(Network network, Link link) {
        // TODO: 2022/1/12 
    }

    public Link addLink(LinkDefinition linkDefinition, Node start, Node end, ElementScope scope) {
        if (!this.equalOrChild(linkDefinition)) {
            return null;
        }

        if (start.Parent() != this || end.Parent() != this) {
            return null;
        }

        linkDefinition = (LinkDefinition) this.getInternalReference()
                .updateSameIntelligentObjectDefinition(linkDefinition);
        Link link = (Link) linkDefinition.CreateInstance(super.GetUniqueName(linkDefinition.Name()));
        this.addLink(link);
        link.Scope(scope);
        link.beginNode = start;
        link.endNode = end;
        if (start != null) {
            link.beginNode = start;
            link.endNode = end;
            if (this.NetworkProperty != null) {
                this.NetworkProperty.addLink(link);
            }
        }

        link.properties.init();
        link.calculateDistance();
        this.initLinkRunSpace(link);
        this.method_229(this.NetworkProperty);
        this.triggerChangeRestoreData(link);
        this.triggerSubmitSearchAndBreakpoint(link);
        this.addLinkRecursive(link, this.getChildrenObject().size() - 1);
        return link;
    }

    private void method_229(Network network) {
        this.processRunSpace((IntelligentObjectRunSpace runSpace) ->
                runSpace.method_66(network));
    }

    private void initLinkRunSpace(Link link) {
        this.processRunSpace((IntelligentObjectRunSpace runSpace) ->
                runSpace.initLinkRunSpace(link));
    }

    private void addLink(Link link) {
        link.processPropertyIndex = this.childrenObject.size();
        this.childrenObject.add(link);
        link.Parent(this);
        this.getNameUtil().addObjectByName(link.InstanceName(), link);
        ;
        if (link.beginNode != null) {
            this.NetworkProperty.addLink(link);
        }
        this.injectRunSpaceToProcessProperty(link);
    }

    public TransporterList getTransporterList(String name) {
//        return this.method_259 < TransporterList > ((TransporterList) TransporterListDefinition
//        .TransporterListDefinition0.CreateInstance(name == null ? super.GetUniqueName("TransporterList") : name));
        return null;
    }

    public ObjectList getObjectList(String name) {
        return null;
    }

    public NodeList getNodeList(String name) {
        return null;
    }

    public StringList getStringList(Object o) {
        return null;
    }


    public boolean valid(ValidObjectType validObjectType) {
        return switch (validObjectType) {
            case Object -> true;
            case FixedObject -> this instanceof FixedDefinition;
            case Link -> this instanceof LinkDefinition;
            case Node -> this instanceof NodeDefinition;
            case Agent -> this instanceof AgentDefinition;
            case Entity -> this instanceof EntityDefinition;
            case Transporter -> this instanceof TransporterDefinition;
        };
    }

    public void updateInstanceName(AbsListProperty absListProperty, String instanceName) {
        this.updateObjectName(instanceName, absListProperty.InstanceName(), absListProperty);
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectListChange(absListProperty, Enum38.One));
        }
        this.flashState();
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subClass) ->
        {
            subClass.updateInstanceName(absListProperty, instanceName);
        });
    }

    public NodeClassProperty updateNodeClassProperty() {
        GridObjectDefinition gridObjectDefinition = ExternalNodeDefinition.Instance;
        String node = "Node";
        int num = ++this.int_9;
        NodeClassProperty nodeClassProperty =
                (NodeClassProperty) gridObjectDefinition.CreateInstance(node + String.valueOf(num));
        nodeClassProperty.index = this.transferPoints.size();
        nodeClassProperty.Parent(this);
        this.addTransferPoint(nodeClassProperty);
        this.flashNodeClassPropertyState(nodeClassProperty);
        return nodeClassProperty;
    }

    private void flashNodeClassPropertyState(NodeClassProperty nodeClassProperty) {

    }

    private void addTransferPoint(NodeClassProperty nodeClassProperty) {
        this.addTransferPoint(nodeClassProperty, this.transferPoints.size(), null);
    }

    private void addTransferPoint(NodeClassProperty nodeClassProperty, int transferPointsCount,
                                  List<NodePointInfo> infos) {
        this.transferPoints.add(transferPointsCount, nodeClassProperty);
        for (var e : super.getAssociatedInstances()) {
            var instance = (IntelligentObject) e;
            var parent = instance.Parent();
            if (parent != null) {
                var info = new NodePointInfo();
                if (infos != null) {
                    info = infos.stream().filter(t -> t.IntelligentObject == instance).findFirst().orElse(null);
                }

                if (info.NodeRestoreHandle != null) {
                    if (parent.restoreObject(info.NodeRestoreHandle) instanceof Node restoreNode) {
                        restoreNode.NodeClassProperty = nodeClassProperty;
                        restoreNode.IntelligentObject = info.IntelligentObject;
                        instance.nodes.add(info.index, restoreNode);
                    }
                } else if (info.underlineName != null) {
                    var infoTmp = info;
                    parent.nodes.stream()
                            .filter(node -> StringHelper.equalsLocal(node.InstanceName(), infoTmp.underlineName))
                            .findFirst()
                            .ifPresent(t -> {
                                t.NodeClassProperty = nodeClassProperty;
                                t.IntelligentObject = infoTmp.IntelligentObject;
                                t.InstanceName(infoTmp.instanceName);
                                instance.nodes.add(infoTmp.index, t);
                            });
                } else {
                    parent.setNodeLocation(nodeClassProperty, transferPointsCount, instance,
                            nodeClassProperty.NodeClass());
                }
            }
        }
        this.locations.add(transferPointsCount, null);

    }

    private void addTransferPointForChildren(NodeClassProperty nodeClassProperty, int transferPointsCount,
                                             List<NodePointInfo> infos) {
        this.addProcessPropertyByName(nodeClassProperty.InstanceName(), nodeClassProperty);
        this.processChildrenDefinition((IntelligentObjectDefinition subclass) ->
                subclass.addTransferPoint(nodeClassProperty, transferPointsCount, infos));
        this.flashState();
        this.resetTable(255);
        this.addTransferPointForChildren(nodeClassProperty, transferPointsCount, infos);
    }


    public IntelligentObject restoreObject(NodeRestoreHandle nodeRestoreHandle) {
        if (nodeRestoreHandle instanceof NodeRestoreHandleOperator nodeRestoreHandleOperator) {
            nodeRestoreHandleOperator.getIntelligentObjectDefinition();
            if (nodeRestoreHandleOperator.getIntelligentObjectDefinition() != this) {
                throw new IllegalStateException("Trying to restore an object to a different definition than it " +
                        "was removed from");
            }
            if (nodeRestoreHandleOperator.isAlreadyRestore()) {
                throw new IllegalStateException("Trying to restore an object twice");
            }

            if (nodeRestoreHandleOperator.Instance() instanceof Node node) {
                this.addNode(node, true);
            } else {
                this.addIntelligentObject(nodeRestoreHandleOperator.Instance(), false);
            }

            this.triggerChangeRestoreData(nodeRestoreHandleOperator.Instance(),
                    nodeRestoreHandleOperator.getExternalRestoreData());
            this.triggerSubmitSearchAndBreakpoint(nodeRestoreHandleOperator.Instance());
            if (nodeRestoreHandleOperator.Instance() instanceof Node node) {
                this.addNodeRecursive(node, this.getNodes().size() - 1);
            } else {
                this.addLinkRecursive(nodeRestoreHandleOperator.Instance(), this.getChildrenObject().size() - 1);
            }

            nodeRestoreHandleOperator.Instance().properties.init();
            nodeRestoreHandleOperator.restored();
            if (nodeRestoreHandleOperator.getAdditionalHandles() != null) {
                nodeRestoreHandleOperator.getAdditionalHandles().forEach(this::restoreObject);
            }

            nodeRestoreHandleOperator.initLink();
            return nodeRestoreHandleOperator.Instance();
        }

        return null;
    }

    public Table createTable(String name) {
        Table table = this.crateTable();
        table.Name(name);
        return table;
    }

    public Table crateTable() {
        return this.Tables().AddNew();
    }

    public void updateUserFunctionName(UserFunction userFunction, String name) {
        this.updateObjectName(name, userFunction.Name(), userFunction);
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectLookupTableChange(userFunction, Enum38.One));
        }
        this.flashState();
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subClass) ->
                subClass.updateUserFunctionName(userFunction, name));
    }

    public void updateObjectName(WorkSchedule workSchedule, String name) {
        this.updateObjectName(name, workSchedule.Name(), workSchedule);
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectWorkScheduleChange(workSchedule, Enum38.One));
        }
        this.flashState();
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subClass) ->
                subClass.updateObjectName(workSchedule, name));
    }

    public ChangeoverMatrix newChangeoverMatrix() {
        return this.getChangeoverMatrices().AddNew();
    }

    public void updateObjectName(ChangeoverMatrix changeoverMatrix, String name) {
        this.updateObjectName(name, changeoverMatrix.Name(), changeoverMatrix);
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectChangeoverMatrixChange(changeoverMatrix, Enum38.One));
        }
        this.flashState();
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subClass) ->
                subClass.updateObjectName(changeoverMatrix, name));
    }

    public ResourceLogExpression addResourceLogExpression() {
        return this.getResourceLogExpressions().AddNew();
    }

    public void ProcessResourceLogExpressionAction(ResourceLogExpression resourceLogExpression) {
        this.resourceLogExpression = resourceLogExpression;
        if (this.resourceLogExpressionAction != null) {
            this.resourceLogExpressionAction.apply(resourceLogExpression);
        }
    }

    public void updateObjectName(DayPattern dayPattern, String name) {
        this.updateObjectName(name, dayPattern.Name(), dayPattern);
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectDayPatternChange(dayPattern, Enum38.One));
        }
        this.flashState();
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subClass) ->
                subClass.updateObjectName(dayPattern, name));
    }

    private void insertProperty(PropertyDefinitionFacade propertyDefinitionFacade, int index) {
        super.getPropertyDefinitions().getPropertyDefinitionList().add(index, propertyDefinitionFacade);
        this.insertChildrenProperty(propertyDefinitionFacade, index);
    }


    public void insertChildrenProperty(PropertyDefinitionFacade propertyDefinitionFacade, int index) {
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition subclass) ->
                subclass.insertProperty(propertyDefinitionFacade, index));
        this.resetTable(255);
    }

    protected <T> T addElement(T instance, ElementScope elementScope) {
        return (T) (this.addElement((AbsIntelligentPropertyObject) instance, elementScope, false));
    }

    private AbsIntelligentPropertyObject addElement(AbsIntelligentPropertyObject absIntelligentPropertyObject,
                                                    ElementScope scope, boolean autoCreated) {
        absIntelligentPropertyObject.Scope(scope);
        absIntelligentPropertyObject.AutoCreated(autoCreated);
        this.initElement(absIntelligentPropertyObject);
        // TODO: 2022/1/23 
//		if (this.elementEvenHandler != null)
//		{
//			this.elementEvenHandler(this, new IntelligentObjectDefinition.ElementEventArgs
//			(absIntelligentPropertyObject));
//		}
        this.addElement(absIntelligentPropertyObject, this.elements.size() - 1);
        return absIntelligentPropertyObject;
    }

    public IntelligentObject createEntity(IntelligentObjectDefinition intelligentObjectDefinition,
                                          ElementScope elementScope, Location location) {
        if (intelligentObjectDefinition == null) {
            throw new IllegalArgumentException();
        }
        intelligentObjectDefinition =
                this.getInternalReference().updateSameIntelligentObjectDefinition(intelligentObjectDefinition);
        IntelligentObject intelligentObject = this.createIntelligentObject(intelligentObjectDefinition,
                super.GetUniqueName(intelligentObjectDefinition.Name()), elementScope);
        if (intelligentObject != null) {
            intelligentObject.setLocation(location);
        }
        this.resetTable(255);
        return intelligentObject;
    }


    protected NodeRestoreHandle RemoveObjectInstance(IntelligentObject intelligentObject) {
        if (intelligentObject.Parent() != this) {
            return null;
        }
        this.resetTable(255);
        if (intelligentObject instanceof Node node) {
            if (node.IntelligentObject != null) {
                return null;
            }
            return this.removeNode(node);
        } else {
            if (intelligentObject instanceof Link link) {
                return this.removeLink(link);
            }
            List<IntelligentObjectDefinition.NodeRestoreHandle> nodeRestoreHandles = new ArrayList<>();
            intelligentObject.nodes.forEach((Node n) -> nodeRestoreHandles.add(this.removeNode(n)));
            this.RemoveRelationExistingRunSpace(intelligentObject);
            this.childrenObject.remove(intelligentObject);
            intelligentObject.Parent(null);
            this.getNameUtil().removeObjectByName(intelligentObject.InstanceName(), intelligentObject);
            int num = 0;
            for (IntelligentObject tmp : this.childrenObject) {
                tmp.processPropertyIndex = num++;
            }
            this.UpdateForParentObjectMemberElementChange(intelligentObject);
            this.triggerEvent_10ChildrenDefinitionRecursion(intelligentObject);
            Map<Guid, List<Object>> externalRestoreData =
                    this.triggerGuidTargetsEventRecursion(intelligentObject, IntelligentObjectDefinition.Enum4.One);
            this.removeLinkRecursion(intelligentObject);
            return new IntelligentObjectDefinition.NodeRestoreHandleOperator(this, intelligentObject,
                    externalRestoreData, nodeRestoreHandles);
        }
    }

    private void removeLinkRecursion(IntelligentObject intelligentObject) {
        this.removeObjectByName(intelligentObject.InstanceName(), intelligentObject);
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition objectDefinition) ->
                objectDefinition.removeLink(intelligentObject));
        this.flashState();
        this.resetTable(255);
    }

    private void removeLink(IntelligentObject intelligentObject) {
        this.UpdateForParentObjectMemberElementChange(intelligentObject);
        if (intelligentObject instanceof Link link) {
            this.NetworkProperty.removeLink(link);
        }
        this.getChildrenObject().remove(intelligentObject);
        for (int i = 0; i < this.getChildrenObject().size(); i++) {
            this.getChildrenObject().get(i).processPropertyIndex = i;
        }
        this.removeLinkRecursion(intelligentObject);
    }

    private NodeRestoreHandle removeNode(Node node) {
        List<IntelligentObjectDefinition.NodeRestoreHandle> linkRestoreHandles = new ArrayList<>();
        List<Link> inLinkList = new ArrayList<>(node.inLinkList);
        inLinkList.forEach((Link link) -> linkRestoreHandles.add(this.removeLink(link)));
        List<Link> outLinkList = new ArrayList<>(node.outLinkList);
        outLinkList.forEach((Link link) -> linkRestoreHandles.add(this.removeLink(link)));
        this.RemoveRelationExistingRunSpace(node);
        this.nodes.remove(node);
        node.Parent(null);
        this.getNameUtil().removeObjectByName(node.InstanceName(), node);
        int num = 0;
        for (Node nodeTmp : this.nodes) {
            nodeTmp.processPropertyIndex = num++;
        }

        this.UpdateForParentObjectMemberElementChange(node);
        this.triggerEvent_10ChildrenDefinitionRecursion(node);
        this.triggerGuidTargetsEvent(node);
        this.updateAfterNodeRemoveRecursion(node);
        return new NodeRestoreHandleOperator(this, node, null, linkRestoreHandles);
    }

    private void updateAfterNodeRemoveRecursion(Node node) {
        this.removeObjectByName(node.InstanceName(), node);
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition t) -> t.updateAfterNodeRemove(node));
        this.resetTable(255);
    }

    private void updateAfterNodeRemove(Node node) {
        this.UpdateForParentObjectMemberElementChange(node);
        this.getNodes().remove(node);
        for (int i = 0; i < this.getNodes().size(); i++) {
            this.getNodes().get(i).processPropertyIndex = i;
        }
        this.updateAfterNodeRemoveRecursion(node);
    }

    private Map<Guid, List<Object>> triggerGuidTargetsEvent(IntelligentObject intelligentObject) {
        return this.triggerGuidTargetsEventRecursion(intelligentObject, IntelligentObjectDefinition.Enum4.Zero);

    }

    private Map<Guid, List<Object>> triggerGuidTargetsEventRecursion(IntelligentObject intelligentObject, Enum4 enum4) {
        this.resetTable(255);
        Map<Guid, List<Object>> guidTargetsMap = null;
        // TODO: 2022/1/24
//		if (this.eventHandler_11 != null)
//		{
//			IntelligentObjectDefinition.IntelligentObjectEventArgs6 intelligentObjectEventArgs = new
//			IntelligentObjectDefinition.IntelligentObjectEventArgs6(intelligentObject, enum4);
//			this.eventHandler_11(this, intelligentObjectEventArgs);
//			if (intelligentObjectEventArgs.getGuidTargetsMap() != null && intelligentObjectEventArgs.getGuidTargetsMap
//			().Count > 0)
//			{
//				guidTargetsMap = intelligentObjectEventArgs.getGuidTargetsMap();
//			}
//		}
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition intelligentObjectDefinition) ->
        {
            intelligentObjectDefinition.triggerGuidTargetsEvent(intelligentObject);
        });
        return guidTargetsMap;
    }

    public NodeRestoreHandle removeLink(Link link) {
        if (link.Parent() != this) {
            return null;
        }

        List<Network> networkProperties = new ArrayList<Network>(link.networkProperties);
        for (Network network : networkProperties) {
            if (network.Parent() == link.Parent()) {
                network.removeLink(link);
                this.method_229(network);
            }
        }

        this.RemoveRelationExistingRunSpace(link);
        this.childrenObject.remove(link);
        link.Parent(null);
        int num = 0;
        for (IntelligentObject intelligentObject : this.childrenObject) {
            intelligentObject.processPropertyIndex = num++;
        }
        this.UpdateForParentObjectMemberElementChange(link);
        this.triggerEvent_10ChildrenDefinitionRecursion(link);
        Map<Guid, List<Object>> externalRestoreData = this.triggerGuidTargetsEventRecursion(link,
                IntelligentObjectDefinition.Enum4.One);
        this.removeLinkRecursion(link);
        return new IntelligentObjectDefinition.SubNodeRestoreHandleOperator(this, link, externalRestoreData, null,
                networkProperties);

    }

    private void triggerEvent_10ChildrenDefinitionRecursion(IntelligentObject intelligentObject) {
        // TODO: 2022/1/24
//        		if (this.eventHandler_10 != null)
//		{
//			this.eventHandler_10(this, new IntelligentObjectDefinition.IntelligentObjectEventArgs(intelligentObject));
//		}
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition t) ->
                t.triggerEvent_10ChildrenDefinitionRecursion(intelligentObject));
    }

    public void triggerPropertyLinkChanged(Network network, Link link) {
        // TODO: 2022/1/24
    }

    protected void DestroyObjectInstance(org.licho.brain.concrete.IntelligentObject intelligentObject) {
        if (intelligentObject.Parent() != this) {
            return;
        }

        this.resetTable(255);
        if (intelligentObject instanceof Node) {
            Node node = (Node) intelligentObject;
            this.destroyNodeInstance(node);
            return;
        }


    }

    private void destroyNodeInstance(Node node) {
        if (node.Parent() != this) {
            return;
        }

        if (node.IntelligentObject != null) {
            return;
        }
        this.destroyNode(node);

    }

    private void destroyNode(Node node) {
        List<Link> inLinkList = new ArrayList<Link>(node.inLinkList);
        inLinkList.forEach(this::destroyLink);
        List<Link> outLinkList = new ArrayList<Link>(node.outLinkList);
        outLinkList.forEach(this::destroyLink);
        node.DestroyInstance();
        this.RemoveRelationExistingRunSpace(node);
        this.nodes.remove(node);
        node.assignerDefinition.DestroyInstance(node);
        node.Parent(null);
        this.getNameUtil().removeObjectByName(node.InstanceName(), node);
        int num = 0;
        for (Node node1 : this.nodes) {
            node1.processPropertyIndex = num++;
        }
        this.UpdateForParentObjectMemberElementChange(node);
        this.triggerEvent_10ChildrenDefinitionRecursion(node);
        this.triggerGuidTargetsEvent(node);
        this.updateAfterNodeRemoveRecursion(node);
    }

    private void destroyLink(Link link) {
        if (link.Parent() != this) {
            return;
        }

        List<Network> networks = new ArrayList<>(link.networkProperties);
        for (Network network : networks) {
            if (network.Parent() == link.parent) {
                network.removeLink(link);
                this.method_229(network);
            }
        }

        link.DestroyInstance();
        this.RemoveRelationExistingRunSpace(link);
        this.childrenObject.remove(link);
        link.assignerDefinition.DestroyInstance(link);
        link.Parent(null);
        this.getNameUtil().removeObjectByName(link.InstanceName(), link);
        if (link.beginNode != null) {
            link.beginNode.outLinkList.indexOf(link);
            link.beginNode.outLinkList.remove(link);
            link.endNode.inLinkList.indexOf(link);
            link.endNode.inLinkList.remove(link);
        }

        int num = 0;
        for (IntelligentObject intelligentObject : this.childrenObject) {
            intelligentObject.processPropertyIndex = num++;
        }
        this.UpdateForParentObjectMemberElementChange(link);
        this.triggerEvent_10ChildrenDefinitionRecursion(link);
        this.triggerGuidTargetsEvent(link);
        this.removeLinkRecursion(link);
    }

    public boolean haveOneTransferPoints() {
        int num = 0;
        for (NodeClassProperty nodeClassProperty : this.transferPoints) {
            if (nodeClassProperty.InputLocationType() != NodeInputLogicType.None) {
                num++;
            }
        }
        return num == 1;
    }

    public void setAnimationSetup(IContextBound animationSetup) {
        this.animationSetup = animationSetup;
        if (this.defaultAdditionalSymbol != null) {
            this.defaultAdditionalSymbol.NotifyOtherContextBound(this.animationSetup);
        }
        if (this.dashboard != null) {
            this.dashboard.NotifyOtherContextBound(this.animationSetup);
        }
    }

    public void DefaultAdditionalSymbolNotifyOtherContextBound(IContextBound contextBound) {
        this.defaultAdditionalSymbol = contextBound;
        if (this.animationSetup != null) {
            this.animationSetup.NotifyOtherContextBound(this.defaultAdditionalSymbol);
        }
        if (this.dashboard != null) {
            this.dashboard.NotifyOtherContextBound(this.defaultAdditionalSymbol);
        }
    }

    public void DashboardNotifyOtherContextBound(IContextBound param0) {
        this.dashboard = param0;
        if (this.defaultAdditionalSymbol != null) {
            this.defaultAdditionalSymbol.NotifyOtherContextBound(this.dashboard);
        }
        if (this.animationSetup != null) {
            this.animationSetup.NotifyOtherContextBound(this.dashboard);
        }
    }

    public void method_428() {
        for (Table table : this.Tables().getValues()) {
            table.method_41();
        }
    }

    public boolean method_452() {
        return this.bool_6;
    }

    public void triggerPropertyUpdated(IntelligentObject intelligentObject) {
        EventHandler.fire(this.propertyUpdatedEvent, this, intelligentObject);
    }

    public void onNameChanged(IntelligentObject intelligentObject, String oldName) {
        if (this.activeModel != null && this.activeModel.projectDefinition != null) {
            this.activeModel.projectDefinition.inited();
        }
        this.triggerDefinitionNameChangedEvent(intelligentObject, name);
    }

    private void triggerDefinitionNameChangedEvent(IntelligentObject intelligentObject, String name) {
        this.resetTable(255);
        if (this.definitionNameChangedEvent != null) {
            this.definitionNameChangedEvent.fire(this, new DefinitionNameChangedEventArgs(intelligentObject, name,
                    intelligentObject.InstanceName()));
        }
        this.triggerDefinitionChildrenNameChangedEvent((IntelligentObjectDefinition t) -> t.triggerDefinitionNameChangedEvent(intelligentObject, name));
    }

    public void updateObjectName(AbsIntelligentPropertyObject absIntelligentPropertyObject, String name) {
        this.updateObjectName(name, absIntelligentPropertyObject.InstanceName(), absIntelligentPropertyObject);
        if (this.noUpdating()) {
            this.propertyListenHandle_1((IListener listener) ->
                    listener.UpdateForParentObjectMemberElementChange(absIntelligentPropertyObject, Enum38.One));
        }
        this.flashState();
        this.processChildrenDefinition((IntelligentObjectDefinition subClass) ->
                subClass.updateObjectName(absIntelligentPropertyObject, name));
    }

    private void processChildrenDefinition(Action<IntelligentObjectDefinition> action) {
        if (this.childrenDefinitions != null) {
            for (IntelligentObjectDefinition obj : this.childrenDefinitions) {
                action.apply(obj);
            }
        }
    }

    void insertTokenDefinition(TokenDefinition tokenDefinition, int index) {
        this.addProcessPropertyByName(tokenDefinition.Name(), tokenDefinition);
        this.processChildrenDefinition((IntelligentObjectDefinition t) -> t.getTokens().Insert(index, tokenDefinition));
        this.flashState();
        this.resetTable(255);
    }

    public void removeTokenDefinition(TokenDefinition tokenDefinition) {
        this.removeObjectByName(tokenDefinition.Name(), tokenDefinition);
        this.propertyListenHandle_1((IListener t) -> t.UpdateForParentObjectTokenDefinitionChange(tokenDefinition,
                Enum38.Zero));
        this.processChildrenDefinition((IntelligentObjectDefinition t) -> t.getTokens().Remove(tokenDefinition));
        this.resetTable(255);
    }

    void updateRecuriseTokenDefinition(int index, TokenDefinition old, TokenDefinition newObject) {
        this.removeObjectByName(old.Name(), old);
        this.addProcessPropertyByName(newObject.Name(), newObject);
        this.propertyListenHandle_1((IListener listener) ->
                listener.UpdateForParentObjectTokenDefinitionChange(old, Enum38.Zero));
        this.flashState();
        this.processChildrenDefinition((IntelligentObjectDefinition definition) ->
                definition.getTokens().updateTokenDefinition(index, newObject));
    }

    public void insertDayPattern(DayPattern dayPattern, int index) {
        this.addProcessPropertyByName(dayPattern.Name(), dayPattern);
        this.processChildrenDefinition((IntelligentObjectDefinition t) ->
        {
            t.getWorkSchedulesUtils().DayPatterns().Insert(index, dayPattern);
        });
        this.flashState();
        if (dayPattern.IsOwnedBy(this)) {
            this.triggerInsertGridObjectEvent(dayPattern);
            this.resetTable(255);
        }
    }

    private void triggerInsertGridObjectEvent(IGridObject gridObject) {
        if (this.insertGridObjectEventHandler != null) {
            this.insertGridObjectEventHandler.fire(this,
                    new IntelligentObjectDefinition.GridObjectInsertEventArgs(gridObject));
        }

    }

    public void RemoveDayPattern(DayPattern dayPattern) {
        this.removeObjectByName(dayPattern.Name(), dayPattern);
        this.propertyListenHandle_1((IListener listener) ->
                listener.UpdateForParentObjectDayPatternChange(dayPattern, Enum38.Zero));
        this.processChildrenDefinition((IntelligentObjectDefinition definition) ->
                definition.getWorkSchedulesUtils().DayPatterns().Remove(dayPattern));
        this.activeModel.getErrors().RemoveErrorByObject(dayPattern);
        if (dayPattern.IsOwnedBy(this)) {
            this.resetTable(255);
        }
    }

    public void addWorkPeriod(WorkPeriod workPeriod) {
        this.flashState();
        if (workPeriod.IsOwnedBy(this)) {
            this.triggerInsertGridObjectEvent(workPeriod);
            this.resetTable(255);
        }
    }

    public void removeWorkPeriod(WorkPeriod workPeriod) {
        this.activeModel.getErrors().RemoveErrorByObject(workPeriod);
        if (workPeriod.IsOwnedBy(this)) {
            this.resetTable(255);
        }
    }

    public void addWorkSchedule(WorkSchedule workSchedule, int index) {
        this.addProcessPropertyByName(workSchedule.Name(), workSchedule);
        this.processChildrenDefinition((IntelligentObjectDefinition t) ->
                t.getWorkSchedulesUtils().getWorkSchedules().Insert(index, workSchedule));
        this.flashState();
        if (workSchedule.IsOwnedBy(this)) {
            this.triggerInsertGridObjectEvent(workSchedule);
            this.resetTable(255);
        }
    }

    public void removeWorkSchedule(WorkSchedule workSchedule) {
        this.removeObjectByName(workSchedule.Name(), workSchedule);
        this.propertyListenHandle_1((IListener t) ->
                t.UpdateForParentObjectWorkScheduleChange(workSchedule, Enum38.Zero));
        this.processChildrenDefinition((IntelligentObjectDefinition t) ->
                t.getWorkSchedulesUtils().getWorkSchedules().Remove(workSchedule));
        this.activeModel.getErrors().RemoveErrorByObject(workSchedule);
        if (workSchedule.IsOwnedBy(this)) {
            this.resetTable(255);
        }
    }

    public enum Enum_7 {
        V_1,
        V_2,
        V_3,
    }

    void propertyChanged(IntelligentObject intelligentObject) {
        // TODO: 2021/11/12
    }

    public boolean notHaveParent() {
        return this.parent == null;
    }


    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    public ActiveModel ActiveModel() {
        return activeModel;
    }

    public void ActiveModel(ActiveModel activeModel) {
        this.activeModel = activeModel;
    }

    public IDisposable createDefinitionOperator() {
        return new DefinitionOperator(this);
    }


    public enum ResultType implements IEnumMask {
        Zero,
        InteractiveResults,
        ExperimentResults,
        PlanResults,
        RiskResults,
        None;


        ResultType() {
            this.mask = (1 << ordinal());
        }

        private final int mask;

        public final int mask() {
            return this.mask;
        }
    }

    public enum Relation {
        None,
        DiffVersion,
        Same
    }

    public enum Enum6 {
        Zero,
        One
    }

    public enum Enum8 {
        Zero,
        One
    }

    public enum Enum5 {
        Zero,
        One
    }

    public enum Enum4 {
        Zero,
        One
    }


    private static class ModelOperatorCounter implements IDisposable {
        private IntelligentObjectDefinition intelligentObjectDefinition;

        public ModelOperatorCounter(IntelligentObjectDefinition intelligentObjectDefinition) {
            this.intelligentObjectDefinition = intelligentObjectDefinition;
            this.intelligentObjectDefinition.enter();
        }

        @Override
        public void Dispose() {
            if (this.intelligentObjectDefinition != null) {
                this.intelligentObjectDefinition.exit();
                this.intelligentObjectDefinition = null;
            }
        }
    }

    private static class DefinitionOperator implements IDisposable {

        public DefinitionOperator(IntelligentObjectDefinition intelligentObjectDefinition) {
            this.intelligentObjectDefinition = intelligentObjectDefinition;
            this.intelligentObjectDefinition.enterDefinitionCount();
        }

        public void Dispose() {
            this.intelligentObjectDefinition.exitDefinitionCount();
        }

        private IntelligentObjectDefinition intelligentObjectDefinition;
    }

    public void exitDefinitionCount() {
        this.definitionCount--;
        if (!this.updating() && this.inUpdating) {
            this.inUpdating = false;
            this.flashState(false);
            this.triggerDefinitionChildrenNameChangedEvent(IntelligentObjectDefinition::flashState);
        }
    }

    public void enterDefinitionCount() {
        this.definitionCount++;
    }

    public class EntityProcess implements IEntityProcess {

        private final int entityInterfaceProcessIndex;
        private final String enumName;
        private final ObjectClass objectClass;

        public EntityProcess(int entityInterfaceProcessIndex, String enumName, ObjectClass objectClass) {
            this.entityInterfaceProcessIndex = entityInterfaceProcessIndex;
            this.enumName = enumName;
            this.objectClass = objectClass;
        }

        @Override
        public String Name() {
            return this.enumName;
        }

        @Override
        public int Index() {
            return this.entityInterfaceProcessIndex;
        }

        public ObjectClass Type() {
            return this.objectClass;
        }
    }

    public static class ElementEventArgs extends EventArgs {
        public ElementEventArgs(AbsIntelligentPropertyObject element) {
            this.element = element;
        }

        public AbsIntelligentPropertyObject Element() {
            return this.element;
        }

        private AbsIntelligentPropertyObject element;
    }


    public static class DefinitionNameChangeEventArgs extends EventArgs {
        public DefinitionNameChangeEventArgs(IntelligentObjectDefinition intelligentObjectDefinition,
                                             String param1) {
            super();
            this.IntelligentObjectFacility = intelligentObjectDefinition;
            this.name = param1;
        }

        public final IntelligentObjectDefinition IntelligentObjectFacility;
        public final String name;
    }

    private static class LocationDirectionEventArgs extends EventArgs {
        private IntelligentObject intelligentObject;
        private Location location;
        private Direction direction;

        public LocationDirectionEventArgs(IntelligentObject intelligentObject,
                                          Location location, Direction direction) {
            this.intelligentObject = intelligentObject;
            this.location = location;
            this.direction = direction;
        }

        public IntelligentObject Instance() {
            return this.intelligentObject;
        }

        public Location Location() {
            return this.location;
        }

        public Direction getDirection() {
            return this.direction;
        }
    }

    public static class ChangesLocationDirectionEventArgs extends LocationDirectionEventArgs {

        public ChangesLocationDirectionEventArgs(org.licho.brain.concrete.IntelligentObject intelligentObject,
                                                 Location location, Direction direction) {
            super(intelligentObject, location, direction);
        }
    }

    private static class AfterChangesLocationDirectionEventArgs extends LocationDirectionEventArgs {

        public AfterChangesLocationDirectionEventArgs(org.licho.brain.concrete.IntelligentObject intelligentObject,
                                                      Location location, Direction direction) {
            super(intelligentObject, location, direction);
        }
    }

    public enum SomeStatus {
        Zero,
        One,
        Two,
        Three
    }

    public class GridObjectEventArgs extends EventArgs {
        private String propertyName;
        private IGridObject gridObject;

        public GridObjectEventArgs(IGridObject gridObject, String propertyName) {
            this.gridObject = gridObject;
            this.propertyName = propertyName;
        }

        public IGridObject getGridObject() {
            return this.gridObject;
        }

        public String PropertyName() {
            return this.propertyName;
        }
    }

    public class IntelligentObjectPropertyEventArgs extends EventArgs {
        private IntelligentObjectProperty intelligentObjectProperty;

        public IntelligentObjectPropertyEventArgs(IntelligentObjectProperty intelligentObjectProperty) {
            this.intelligentObjectProperty = intelligentObjectProperty;
        }
    }

    public class IntelligentObjectPropertyErrorEvent extends IntelligentObjectPropertyEventArgs {
        public IntelligentObjectPropertyErrorEvent(IntelligentObjectProperty intelligentObjectProperty) {
            super(intelligentObjectProperty);
        }
    }

    public class IntelligentObjectPropertyChangeEvent extends IntelligentObjectPropertyEventArgs {
        public IntelligentObjectPropertyChangeEvent(IntelligentObjectProperty intelligentObjectProperty) {
            super(intelligentObjectProperty);
        }
    }

    public class PropertyErrorEvent extends GridObjectEventArgs {

        public PropertyErrorEvent(IGridObject gridObject, String propertyName) {
            super(gridObject, propertyName);
        }
    }

    public class PropertyChangeEvent extends GridObjectEventArgs {

        public PropertyChangeEvent(IGridObject gridObject, String propertyName) {
            super(gridObject, propertyName);
        }
    }

    public static class BuiltInFunction implements IGridObject {
        @Override
        public String getObjectClassName() {
            return "Built-in Function";
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
        public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties,
                                                      GridObjectDefinition gridObjectDefinition) {
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
    }

    public interface IProcessPropertyHierarchy {
        void destroyOverrideProcessProperty();
    }

    public class ProcessPropertyHierarchy implements IProcessPropertyHierarchy {

        private ProcessProperty overrideProcessProperty;
        private ProcessProperty processProperty;
        private IntelligentObjectDefinition parent;

        public ProcessPropertyHierarchy(IntelligentObjectDefinition parent,
                                        ProcessProperty overrideProcessProperty, ProcessProperty processProperty) {
            this.parent = parent;
            this.overrideProcessProperty = overrideProcessProperty;
            this.processProperty = processProperty;
        }

        public IntelligentObjectDefinition Parent() {
            return this.parent;
        }

        public ProcessProperty Override() {
            return this.overrideProcessProperty;
        }

        public ProcessProperty Original() {
            return this.processProperty;
        }

        public void destroyOverrideProcessProperty() {
            if (this.overrideProcessProperty != null) {
                this.overrideProcessProperty.DestroyInstance();
                this.overrideProcessProperty.assignerDefinition.DestroyInstance(this.overrideProcessProperty);
                this.overrideProcessProperty = null;
                this.processProperty = null;
                this.parent = null;
            }
        }
    }

    public List<String> filterNameByStringPropertyDefinition(Predicate<StringPropertyDefinition> match) {
        List<String> result = new ArrayList<>();

        for (Table table : this.Tables().getValues()) {
            table.Schema().processStringPropertyDefinition((StringPropertyDefinition stringPropertyDefinition) -> {
                if (match.test(stringPropertyDefinition)) {
                    result.add(table.Name() + "." + stringPropertyDefinition.Name());
                }
            });
        }

        for (StringPropertyDefinition propertyDefinition : super.getPropertyDefinitions().getValues()) {
            RepeatingPropertyDefinition repeatingPropertyDefinition = (RepeatingPropertyDefinition) propertyDefinition;
            if (repeatingPropertyDefinition != null && repeatingPropertyDefinition.IsVisible(super.getPropertyDefinitions())) {
                repeatingPropertyDefinition.propertyDefinitions.process((StringPropertyDefinition stringPropertyDefinition) -> {
                    if (match.test(stringPropertyDefinition) && stringPropertyDefinition.IsVisible(repeatingPropertyDefinition.propertyDefinitions)) {
                        result.add(repeatingPropertyDefinition.Name() + "." + stringPropertyDefinition.Name());
                    }
                });
            }
        }
        return result;
    }

    public class DirectionEventArgs extends EventArgs {
        public final Node Node;
        private Direction direction;

        public Direction getDirection() {
            return this.direction;
        }

        public void setDirection(Direction direction) {
            this.direction = direction;
        }

        public DirectionEventArgs(Node node) {
            this.Node = node;
            this.setDirection(Direction.Instance);
        }
    }

    public interface NodeRestoreHandle {
        void DestroyInstance();
    }

    public static class NodeRestoreHandleOperator implements NodeRestoreHandle {
        private IntelligentObjectDefinition intelligentObjectDefinition;
        private IntelligentObject instance;
        private Map<Guid, List<Object>> externalRestoreData;
        private List<IntelligentObjectDefinition.NodeRestoreHandle> additionalHandles;
        private boolean alreadyRestore;

        public NodeRestoreHandleOperator(IntelligentObjectDefinition intelligentObjectDefinition,
                                         IntelligentObject instance, Map<Guid, List<Object>> externalRestoreData,
                                         List<IntelligentObjectDefinition.NodeRestoreHandle> additionalHandles) {
            this.intelligentObjectDefinition = intelligentObjectDefinition;
            this.instance = instance;
            this.externalRestoreData = externalRestoreData;
            this.additionalHandles = additionalHandles;
        }

        public IntelligentObjectDefinition getIntelligentObjectDefinition() {
            return this.intelligentObjectDefinition;
        }

        public IntelligentObject Instance() {
            return this.instance;
        }

        public List<IntelligentObjectDefinition.NodeRestoreHandle> getAdditionalHandles() {
            return this.additionalHandles;
        }

        public void restored() {
            this.alreadyRestore = true;
        }

        public boolean isAlreadyRestore() {
            return this.alreadyRestore;
        }

        public Map<Guid, List<Object>> getExternalRestoreData() {
            return this.externalRestoreData;
        }

        public void initLink() {
        }

        public void DestroyInstance() {
            if (this.instance != null) {
                this.instance.DestroyInstance();
                this.instance.assignerDefinition.DestroyInstance(this.instance);
                this.instance = null;
                this.intelligentObjectDefinition = null;
            }
        }
    }

    public static class SubNodeRestoreHandleOperator extends NodeRestoreHandleOperator {
        public SubNodeRestoreHandleOperator(IntelligentObjectDefinition intelligentObjectDefinition,
                                            IntelligentObject instance, Map<Guid, List<Object>> externalRestoreData,
                                            List<IntelligentObjectDefinition.NodeRestoreHandle> additionalHandles,
                                            List<Network> networks) {
            super(intelligentObjectDefinition, instance, externalRestoreData, additionalHandles);
            this.networks = networks;
        }

        @Override
        public void initLink() {
            Link link = (Link) super.Instance();
            if (this.networks != null) {
                this.networks.forEach((Network network) -> network.addLink(link));
            }
            link.beginNode.outLinkList.add(link);
            link.endNode.inLinkList.add(link);
            super.initLink();
        }

        private List<Network> networks;
    }

    public class DefinitionNameChangedEventArgs {
        private IntelligentObject intelligentObject;
        private String name;
        private String instanceName;

        public DefinitionNameChangedEventArgs(IntelligentObject intelligentObject,
                                              String name, String instanceName) {
            this.intelligentObject = intelligentObject;
            this.name = name;
            this.instanceName = instanceName;
        }

        public IntelligentObject Instance() {
            return this.intelligentObject;
        }

        public String getName() {
            return this.name;
        }

        public String getInstanceName() {
            return this.instanceName;
        }
    }

    public class GridObjectInsertEventArgs {
        public GridObjectInsertEventArgs(IGridObject gridObject) {
            this.gridObject = gridObject;
        }

        public IGridObject getGridObject() {
            return this.gridObject;
        }

        private IGridObject gridObject;

    }

    public class NodePointInfo {
        public IntelligentObject IntelligentObject;
        public String instanceName;
        public String underlineName;
        public int index;
        public IntelligentObjectDefinition.NodeRestoreHandle NodeRestoreHandle;
    }
}
