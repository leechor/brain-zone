package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.enu.Enum90;
import org.licho.brain.concrete.exception.SimioRuntimeException;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.ExperimentConstraintsDefinition;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.IEnumMask;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.RunEndType;
import org.licho.brain.event.EventArgs;
import org.licho.brain.event.EventHandler;
import org.licho.brain.exceptions.InvalidOperationException;
import org.licho.brain.resource.Image;
import org.licho.brain.brainEnums.AppViewType;
import org.licho.brain.brainEnums.ModelViewType;
import org.licho.brain.brainEnums.ProjectViewType;
import org.licho.brain.utils.simu.IFilesStream;
import org.licho.brain.utils.simu.IFilesStreamOperator;
import org.licho.brain.utils.simu.IProjectOperator;
import org.licho.brain.utils.simu.system.DateTime;
import org.licho.brain.utils.simu.system.IDisposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.io.StringReader;
import java.text.MessageFormat;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Stack;

/**
 *
 */
public class ProjectManager {
    private static Logger logger = LoggerFactory.getLogger(ProjectManager.class);
    private SimioProjectManagerWrapper simioProjectManagerWrapper;
    private Enum90 enum90;
    private ProductComplexityLevel productComplexityLevel;
    private Action<ActiveModel> action_0;
    private Action<ActiveModel> action_1;
    private Map<Symbol, SymbolViewTypeView> symbolViewTypeViews;
    private Map<ActiveModel, CompleteChoiceOperator> completeChoiceOperators;
    private CommandHanderBinderComponent commandHanderBinderComponent;
    public ProductComplexityLevel ComplexityLevel;

    private Project currentProject;
    private IProjectOperator project;
    private Map<ActiveModel, String> activeModelMap = new HashMap();
    private ActiveModel activeModel;
    private ExperimentConstraintsDefinition activeExperiment;
    private Symbol symbol;
    private Action<Symbol> symbolChangeHandler;
    private Action<ExperimentConstraintsDefinition> activeExperimentChangeHandler;
    private Map<ExperimentConstraintsDefinition, ExperimentViewTypeView> experimentViewTypeViews;
    private boolean bool_0;
    private Object view;
    private Map<ActiveModel, ModelViewTypeView> modelViewTypeViews;
    private EventHandler<EventArgs> runEventHandler;
    private EventHandler<ActiveModel> activeModelEventHandler;
    private EventHandler<ProjectDefinition> removeObjectEvent;
    private ProjectViewTypeView projectViewTypeView;
    private Object actionTarget;
    private ActionRun actionRun;
    private final List<String> exceptions;
    private EventHandler<AddActiveModelEventArgs> limitEventHandler = new EventHandler<>();
    private Image StatusRun;
    private Image StatusPause;
    private Image StatusBreakpoint;
    private Image StatusStop;
    private Image StatusEndOfRun;
    private ActionRun _actionCount2;
    private Map.Entry<IGridObject, GridObjectDefinition> gridDefinitionEntry;
    private boolean hasAdvancedProperties;
    private Action<IGridObject> action_8;
    private ActionRun showInitialModelViewAction;
    private Stack<PropertyStatus> propertyStatusStack;
    private AppViewNone appViewNone;
    private boolean bool_2;

    public ProjectManager(IProjectOperator project) {
        this.project = project;
        this.exceptions = new ArrayList<>();
        this.actionRun = new ActionRun(null);
        this.commandHanderBinderComponent = new CommandHanderBinderComponent();
        this.completeChoiceOperators = new HashMap<>();
        this.modelViewTypeViews = new HashMap<>();
        this.experimentViewTypeViews = new HashMap<>();
        this.symbolViewTypeViews = new HashMap<>();
        this.enum90 = Enum90.One;

        this.StatusRun = Resources.StatusRun;
        this.StatusPause = Resources.StatusPause;
        this.StatusBreakpoint = Resources.StatusBreakpoint;
        this.StatusStop = Resources.StatusStop;
        this.StatusEndOfRun = Resources.StatusEndOfRun;

        this.productComplexityLevel = ProductComplexityLevel.Advanced;
        this.propertyStatusStack = new Stack<>();
        this.limitEventHandler.subscribe((Object sender, AddActiveModelEventArgs e) -> {
            this.registerEvent(e.Model());
            if (!this.method_1344()) {
                this.setActiveModel(e.Model());
                if (!this.showInitialModelViewAction.empty()) {
//                    locals1.showInitialModelView();
                }
            }
        });
        this.showInitialModelViewAction = new ActionRun(() -> this.method_78(ModelViewType.Overall));

        this.action_0 = (ActiveModel activeModel) -> {
            this.closeView(activeModel);
            this.method_849(null, null);
        };
        this.action_1 = (ActiveModel activeModel) -> {
            this.closeView(activeModel);
            this.method_849(null, null);
        };

        this.registerEvents(this.project().currentProjectDefinition);
        this.bindCommandHandler();
        this.propertyStatusStack.push(new PropertyStatus(this));
        this.simioProjectManagerWrapper = new SimioProjectManagerWrapper(this);
        this.createNoneAppView();

    }

    boolean method_1344() {
        return this.bool_2;
    }

    private void createNoneAppView() {
        this.activeModel(null);
        this.ActiveExperiment(null);
        this.setSymbol(null);
        Object param = this.getNoneAppView().createView(AppViewType.Start, this.project, "Simio");
        this.getNoneAppView().showView(param, this.project);
    }

    private AppViewNone getNoneAppView() {
        if (this.appViewNone == null) {
            this.appViewNone = new AppViewNone(this);
        }
        return this.appViewNone;
    }

    private void bindCommandHandler() {

    }

    private void closeView(ActiveModel activeModel) {
        // TODO: 2022/2/11
    }

    public Object method_78(ModelViewType modelViewType) {
        return this.method_79(modelViewType, ProjectManager.Enum78.Zero);
    }

    private Object method_79(ModelViewType modelViewType, Enum78 zero) {
        // TODO: 2022/2/11
        return null;
    }

    public void setActiveModel(ActiveModel activeModel) {
        final ProjectDefinition projectDefinition = this.project().currentProjectDefinition;
        if ((projectDefinition == null && activeModel != null) ||
                (projectDefinition != null && !projectDefinition.containActiveModel(activeModel))) {
            throw new IllegalArgumentException();
        }
        this.activeModel(activeModel);
    }


    public boolean loadProject(String fileName, int param1, String param2) {
        if (Strings.isNullOrEmpty(fileName)) {
            return false;
        }

        if (fileName.equals(this.project().getFileName())) {
            return true;
        }

        boolean success = false;
        StringBuffer _fileName = new StringBuffer(fileName);
        try (InputStream stream = this.project.CreateReadStream(_fileName.toString())) {
            if (this.haveProject(stream, _fileName)) {
                this.clearProject(false);
            }

            IFilesStreamOperator fileStreamOperator = ProjectConfig.Instance().getFileStreamOperator(_fileName);

            if (fileStreamOperator != null) {
                DateTime now = DateTime.Now();
                try (IFilesStream filesStream = fileStreamOperator.getFilesStream(stream)) {
                    try (ProjectManager.ProjectFileOperator projectFileOperator =
                                 new ProjectManager.ProjectFileOperator(this, filesStream)) {
                        boolean shift = false;
                        ProjectDefinition simioProject = new ProjectDefinition();
                        projectFileOperator.Project(simioProject);
                        BaseProjectDefinition.LoadOperator loadOperator = simioProject.loadXml(filesStream,
                                projectFileOperator::readModelXml,
                                projectFileOperator::readExperimentConstraintsXml, shift);
                        this.switchToProject(simioProject, _fileName.toString());
                        projectFileOperator.loadConfigureFinish();
                    }
                }
                success = true;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }


    private void clearProject(boolean b) {
    }

    private boolean haveProject(InputStream stream, StringBuffer error) {
        if (stream == null) {
            if (this.project != null) {
                this.project.NotifyError(MessageFormat.format(Resources.FailedToOpenProject(), error));
            }
            return false;
        }
        return true;
    }

    public Project project() {
        if (this.currentProject == null) {
            this.currentProject = new Project(this, (this.project != null) ? this.project.Log() : null);
        }
        return this.currentProject;
    }

    public ActiveModel getActiveModel() {
        if (this.activeModel() != null) {
            return this.activeModel();
        }
        if (this.ActiveExperiment() != null) {
            return this.ActiveExperiment().getActiveModel();
        }
        return null;
    }

    public boolean isHasAdvancedProperties() {
        return this.hasAdvancedProperties;
    }

    public void isHasAdvancedProperties(Boolean hasAdvancedProperties) {
        this.hasAdvancedProperties = hasAdvancedProperties;
    }


    public enum Enum79 implements IEnumMask {
        ;

        Enum79() {
            this.mask = (1 << ordinal());
        }

        private int mask;

        @Override
        public int mask() {
            return this.mask;
        }
    }

    private void runButtonAction(CommandHandlerBinder commandHandlerBinder) {
        try {
            if (this.activeModel() != null) {
                if (this.activeModel().beRun()) {
                    logger.info("Pause Run");
                    this.activeModel().PauseRun();
                } else {
                    ActiveModel.RunModel runModel = ActiveModel.RunModel.Run;
                    if (this.getBaseSelectObject() != null) {
                        runModel = ActiveModel.RunModel.AnimatePlan;
                    }
                    this.logger.info(runModel == ActiveModel.RunModel.AnimatePlan ? "Run (animate Plan)" : "Run");
                    this.activeModel.execute(this::triggerRunEventHandler, runModel, Integer.MAX_VALUE);
                }
            }
        } catch (SimioRuntimeException ex) {
            this.processRuntimeException(ex);
        } catch (Exception ex) {
            this.processException(ex);
        }
    }

    public void execute() {
        ActiveModel activeModel = this.activeModel();
        if (activeModel == null) {
            return;
        }
        switch (activeModel.getRunSetup().LoadAction()) {
            case Reset:
                if (activeModel.isRunnableAndNotTokenCancel()) {
                    this.Reset(null);
                    return;
                }
                break;
            case Step:
                if (activeModel.canStep()) {
                    this.CoarseStep(null);
                    return;
                }
                break;
            case Run:
                if (activeModel.canRun()) {
                    this.runButtonAction(null);
                    return;
                }
                break;
            case FastForward:
                if (activeModel.canFastForward()) {
                    this.FastForward(null);
                }
                break;
            default:
                return;
        }
    }

    private void FastForward(CommandHandlerBinder commandHandlerBinder) {
    }

    private void CoarseStep(CommandHandlerBinder commandHandlerBinder) {
        // TODO: 2022/2/8 
    }


    private void Reset(CommandHandlerBinder commandHandlerBinder) {
        ActiveModel.RunModel runModel = ActiveModel.RunModel.Run;
        if (this.getBaseSelectObject() != null) {
            runModel = ActiveModel.RunModel.AnimatePlan;
        }
        this.logger.info((runModel == ActiveModel.RunModel.AnimatePlan) ? "Reset run (animate Plan)" : "Reset run");
        try {
            this.activeModel().reset(runModel);
        } catch (SimioRuntimeException param) {
            this.processRuntimeException(param);
        } catch (Exception param2) {
            this.processException(param2);
        }
    }

    private void processException(Exception ex) {
        this.method_61(ex);
        try {
            this.activeModel().stop(true);
        } catch (Exception ignored) {
        }
    }

    public void method_198(CommandHandlerBinder commandHandlerBinder) {
        if (this.project().currentProjectDefinition == null) {
            try {
                this.newProject(commandHandlerBinder);
            } catch (InvalidOperationException e) {
                e.printStackTrace();
            }
            return;
        }
//		this.jEwuCeegklF(commandHandlerBinder); todo
    }


    private void newProject(CommandHandlerBinder commandHandlerBinder) throws InvalidOperationException {
        if (!this.closeProject(false)) {
            return;
        }
        ProjectDefinition simioProject = new ProjectDefinition();
        this.switchToProject(simioProject, "");
        this.reInitProjectModels(simioProject);
    }

    public void reInitProjectModels(ProjectDefinition simioProject) throws InvalidOperationException {
        try (var t = this.actionRunDisposable()) {
            try (var tmp = this.showInitialModelViewAction.disposable()) {
                simioProject.initProjectModels(null);
            }
        }
    }

    private IDisposable actionRunDisposable() {
        return this.getActionRun().disposable();
    }

    private ActionRun getActionRun() {
        if (this._actionCount2 == null) {

            this._actionCount2 = new ActionRun(() -> {
                if (this.gridDefinitionEntry != null) {
                    this.method_849(this.gridDefinitionEntry.getKey(), this.gridDefinitionEntry.getValue());
                    this.gridDefinitionEntry = null;
                }
            });
        }
        return this._actionCount2;
    }

    public void method_849(IGridObject gridObject, GridObjectDefinition gridObjectDefinition) {
        IntelligentObjectDefinition intelligentObjectDefinition = null;
        if (gridObjectDefinition instanceof IntelligentObjectDefinition && intelligentObjectDefinition.method_452()) {
            return;
        }
        if (this.getActionRun().empty()) {
            this.gridDefinitionEntry = new AbstractMap.SimpleEntry<>(gridObject, gridObjectDefinition);
            return;
        }
        if (!this.getPropertyStatus().reSelect(gridObject, gridObjectDefinition, false)) {
            return;
        }
        if (this.action_8 != null) {
            this.action_8.apply(gridObject);
        }
    }

    PropertyStatus getPropertyStatus() {
        return this.propertyStatusStack.peek();
    }

    private void switchToProject(ProjectDefinition projectDefinition, String fileName) {
        if (this.removeObjectEvent != null) {
            this.removeObjectEvent.fire(null, projectDefinition);
        }

        final Project p = this.project();
        if (p.currentProjectDefinition != null && p.currentProjectDefinition != projectDefinition) {
            p.currentProjectDefinition.Dispose();
        }

        p.currentProjectDefinition = projectDefinition;
        p.setFileName(fileName);
        this.view = null;

        if (p.currentProjectDefinition != null) {
            for (int i = 0; i < p.currentProjectDefinition.getActiveModelsCount(); i++) {
                ActiveModel model = p.currentProjectDefinition.get(i);
                this.registerEvent(model);
                this.setActiveModel(model);
            }
            this.registerEvents(p.currentProjectDefinition);
            this.createProjectViewTypeView().createView(ProjectViewType.Overall, this.project, "");
        }

        this.logger.info(MessageFormat.format("============== Switch to project {0} ==================",
                this.getProjectName()));
    }

    private void registerEvents(ProjectDefinition simioProject) {
        EventHandler.subscribe(simioProject.getAddActiveModelEvent(), this.limitEventHandler);
        // TODO: 2022/2/9 
    }

    private void registerEvent(ActiveModel activeModel) {
        activeModel.addProjectViewNameChangeEvent((ignored, t) -> {
            ModelViewTypeView modelViewTypeView = this.modelViewTypeViews.get(t);
            if (modelViewTypeView != null && this.project != null) {
                modelViewTypeView.updateViewsName(this.project, t.Name());
            }
        });

        activeModel.addCancelEvent((sender, e) -> {
            if (activeModel.getRunSetup().EndTime().compare(activeModel.getRunSetup().StartTime()) < 0
                    && activeModel.getRunSetup().RunEndType() != RunEndType.Infinite) {

                if (this.project != null) {
                    this.project.NotifyError(Resources.RunSetupEndTimeBeforeStartTime);
                }
                e.cancel(true);
            }
        });

        activeModel.addStartEvent((o, e) -> this.startTimer((ActiveModel) o));
        activeModel.addStopEvent((o, e) -> this.stopTimer());
        activeModel.errorHandler = this::errorHandler;
        // TODO: 2022/2/9 ignored some view display event
    }

    public void errorHandler(double unitCarry, IRunSpace runSpace, AbsPropertyObject absPropertyObject,
                             IntelligentObjectProperty intelligentObjectProperty, String param4) {
        StringBuilder text = new StringBuilder();
        if (!RuntimeErrorFullMessageDetails.IsError(this.actionRun, unitCarry, runSpace, absPropertyObject,
                intelligentObjectProperty, param4, text)) {
            return;
        }
        if (!this.method_861()) {
            throw new SimioRuntimeException(text.toString());
        }
        this.exceptions.add(text.toString());
    }

    private boolean method_861() {
        // TODO: 2022/2/9
        return false;
    }

    private void startTimer(ActiveModel activeModel) {
        if (activeModel != null && activeModel.projectDefinition != null && this.project != null) {
            this.actionTarget = this.project.startTimer(120000, activeModel, this::stopActiveModel);
        }
    }

    private void stopTimer() {
        if (this.actionTarget != null) {
            if (this.project != null) {
                this.project.cancelCountdown(this.actionTarget);
            }
            this.actionTarget = null;
        }
    }


    private void stopActiveModel(Object target) {
        this.actionTarget = null;
        if (target instanceof ActiveModel model) {
            model.stop(false);
        }
    }

    private ProjectViewTypeView createProjectViewTypeView() {
        if (this.projectViewTypeView == null && this.project().currentProjectDefinition != null) {
            this.projectViewTypeView = new ProjectViewTypeView(this);
        }
        return this.projectViewTypeView;
    }

    private boolean closeProject(boolean createNoneView) {
        if (this.project().currentProjectDefinition != null && !this.projectBusy()) {
            return false;
        }
        this.clearProject(createNoneView);
        this.logger.info(MessageFormat.format("========== Close Project {0} =============", this.getProjectName()));
        return true;
    }

    private String getProjectName() {
        if (!Strings.isNullOrEmpty(this.getSimioProjectName())) {
            return this.getSimioProjectName();
        }
        return "[None]";
    }

    private String getSimioProjectName() {
        if (this.project().currentProjectDefinition != null) {
            return this.project().currentProjectDefinition.Name();
        }
        return null;
    }

    private boolean projectBusy() {
        if (this.project().currentProjectDefinition != null) {
            boolean canModify = false;
            for (int i = 0; i < this.project().currentProjectDefinition.getActiveModelsCount(); i++) {
                if (this.project().currentProjectDefinition.get(i).canModify()) {
                    canModify = true;
                }
                if (canModify) {
                    if (this.project != null && !this.project.AskYesNoQuestion(Resources.CantCloseProjectBecauseModelRunning)) {
                        return false;
                    }
                    for (int j = 0; j < this.project().currentProjectDefinition.getActiveModelsCount(); j++) {
                        try {
                            this.project().currentProjectDefinition.get(j).stop(true);
                        } catch (Exception ignored) {
                        }
                    }
                }
                if (this.project().currentProjectDefinition.getInited() && this.project != null) {
                    String askSaveCurrentProject = Resources.AskSaveCurrentProject;
                    if (!Strings.isNullOrEmpty(this.project().getFileName())) {
                        askSaveCurrentProject = MessageFormat.format(Resources.AskSaveCurrentProjectWithName,
                                this.project().getFileName());
                    }
                    switch (this.project.AskYesNoCancelQuestion(askSaveCurrentProject)) {
                        case Zero:
                            if (!this.saveProject(this.project().getFileName(), false, false, null)) {
                                return false;
                            }
                            break;
                        case Two:
                            return false;
                    }
                }
            }
        }
        return true;
    }

    private boolean saveProject(String fileName, boolean b, boolean b1, Object o) {
        return true;
    }


    private void method_61(Exception ex) {
        // TODO: 2022/2/7 
    }

    private void processRuntimeException(SimioRuntimeException ex) {
        this.logger.error(MessageFormat.format("Runtime Error: {0}", ex.getMessage()));
        if (this.project != null) {
            this.project.NotifyError(ex.getMessage());
        }
        try {
            this.activeModel().stop(true);
        } catch (Exception ignored) {
        }
    }

    void triggerRunEventHandler() {
        if (this.runEventHandler != null) {
            this.runEventHandler.fire(this, EventArgs.Empty);
        }
    }

    private BaseViewInfo getBaseSelectObject() {
        if (this.view != null && this.getModelViewTypeView() != null && this.view == this.getModelViewTypeView().getViewByType(ModelViewType.OperationalFacility)) {
            return this.getModelViewTypeView().BaseSelectObject;
        }
        return null;
    }

    private ModelViewTypeView getModelViewTypeView() {
        return this.getModelViewTypeView(this.activeModel());
    }

    private ModelViewTypeView getModelViewTypeView(ActiveModel activeModel) {
        ModelViewTypeView modelViewTypeView = this.modelViewTypeViews.get(activeModel);
        if (activeModel != null && modelViewTypeView != null && !this.bool_0) {
            modelViewTypeView = new ModelViewTypeView(this, activeModel);
            this.modelViewTypeViews.put(activeModel, modelViewTypeView);
        }
        return modelViewTypeView;
    }

    private ActiveModel activeModel() {
        if (this.project().currentProjectDefinition == null) {
            return null;
        }
        return this.activeModel;
    }

    private void activeModel(ActiveModel value) {
        if (value != null) {
            this.ActiveExperiment(null);
            this.setSymbol(null);
        }

        if (this.activeModel != value) {
            if (this.activeModel != null && this.activeModel.canModify()) {
                this.activeModel.PauseRun();
            }
            this.activeModel = value;
            this.triggerActiveModelChangeEventHandler(this.activeModel);
            // TODO: 2022/2/6
        }
    }

    private void triggerActiveModelChangeEventHandler(ActiveModel activeModel) {
        if (this.activeModelEventHandler != null) {
            this.activeModelEventHandler.fire(null, activeModel);
        }
    }

    private ExperimentConstraintsDefinition ActiveExperiment() {
        if (this.project().currentProjectDefinition == null) {
            return null;
        }
        return this.activeExperiment;
    }

    private void ActiveExperiment(ExperimentConstraintsDefinition value) {
        if (value != null) {
            this.activeModel(null);
            this.setSymbol(null);
        }

        if (this.activeExperiment != value) {
            this.activeExperiment = value;
            if (this.activeExperimentChangeHandler != null) {
                this.activeExperimentChangeHandler.apply(this.activeExperiment);
            }
            if (this.project != null && this.project.StaticViewContainer() != null) {
                CurrentErrorView currentErrorView = null;
                if (this.getExperimentViewTypeView() != null) {
                    currentErrorView = this.getExperimentViewTypeView().CurrentErrorView;
                }
                this.project.StaticViewContainer().SetCurrentErrorView(currentErrorView);
            }
            this.processErrorView();
        }
    }

    private void processErrorView() {
        // TODO: 2022/2/6 
    }

    private ExperimentViewTypeView getExperimentViewTypeView() {
        return this.getExperimentViewTypeViewByDefinition(this.ActiveExperiment());
    }

    private ExperimentViewTypeView getExperimentViewTypeViewByDefinition(ExperimentConstraintsDefinition experimentConstraintsDefinition) {
        ExperimentViewTypeView experimentViewTypeView =
                this.experimentViewTypeViews.get(experimentConstraintsDefinition);
        if (experimentConstraintsDefinition != null && experimentViewTypeView == null && !this.bool_0) {
            experimentViewTypeView = new ExperimentViewTypeView(this, experimentConstraintsDefinition);
            this.experimentViewTypeViews.put(experimentConstraintsDefinition, experimentViewTypeView);
        }
        return experimentViewTypeView;
    }


    private void setSymbol(Symbol symbol) {
        if (symbol != null) {
            this.activeModel(null);
            this.ActiveExperiment(null);
        }
        if (this.symbol != symbol) {
            this.symbol = symbol;
            if (this.symbolChangeHandler != null) {
                this.symbolChangeHandler.apply(this.symbol);
            }
        }
    }

    public enum Enum78 {
        Zero
    }

    public class ProjectFileOperator implements IDisposable {

        private final ProjectManager projectManager;
        private final IFilesStream filesStream;
        private BaseProjectDefinition projectDefinition;
        private Map<ActiveModel, String> activeModelMap = new HashMap<>();
        private Map<ExperimentConstraintsDefinition, String> experimentConstraintsXmlMap = new HashMap<>();
        private boolean haveConfigure;


        public ProjectFileOperator(ProjectManager projectManager, IFilesStream filesStream) {
            this.projectManager = projectManager;
            this.filesStream = filesStream;
        }

        @Override
        public void Dispose() {
            if (!this.haveConfigure) {
                return;
            }

            boolean bShowedSomething = false;
            for (Map.Entry<ActiveModel, String> keyValuePair : this.activeModelMap.entrySet()) {
                var model = keyValuePair.getKey();
                var value = keyValuePair.getValue();
                try (StringReader stringReader = new StringReader(value)) {
                    try (var reader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
//                        reader.MoveToContent();
//                        var readContext = this.createIntelligentObjectXml();
//                        ModelViewTypeView[] viewInfo = new ModelViewTypeView[1];
//                        SomeXmlOperator.xmlReaderElementOperator(reader, "ViewInfo", (XmlReader attr) -> {
//                            viewInfo[0] = this.simioProjectManager.getModelViewTypeView(model);
//                            bool_0 = false;
//                        })
                    }

                }
            }
        }

        private IntelligentObjectXml createIntelligentObjectXml() {
            if (this.Project() == null || this.filesStream == null) {
                return null;
            }

            IntelligentObjectXml intelligentObjectXml = this.Project().CreateReadingContext();
            intelligentObjectXml.setFiles(this.filesStream.StreamStore());
            return intelligentObjectXml;
        }

        public BaseProjectDefinition Project() {
            return this.projectDefinition;
        }

        public void Project(ProjectDefinition projectDefinition) {
            this.projectDefinition = projectDefinition;
        }

        public boolean readModelXml(ActiveModel activeModel, XmlReader xmlReader,
                                    IntelligentObjectXml intelligentObjectXml) {
            if (Objects.equals(xmlReader.Name(), "ViewInfo")) {
                this.activeModelMap.put(activeModel, xmlReader.ReadOuterXml());
                return true;
            }
            return false;
        }

        public boolean readExperimentConstraintsXml(ExperimentConstraintsDefinition experimentConstraintsDefinition,
                                                    XmlReader xmlReader,
                                                    IntelligentObjectXml intelligentObjectXml) {
            if (Objects.equals(xmlReader.Name(), "ViewInfo")) {
                this.experimentConstraintsXmlMap.put(experimentConstraintsDefinition, xmlReader.ReadOuterXml());
                return true;
            }
            return false;
        }

        public void loadConfigureFinish() {
            this.haveConfigure = true;
        }
    }
}
