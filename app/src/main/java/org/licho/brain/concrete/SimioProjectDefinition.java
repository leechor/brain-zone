package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.Entity;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.ExperimentConstraintsDefinition;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.event.EventHandler;
import org.licho.brain.event.IEvent;
import org.licho.brain.exceptions.InvalidOperationException;
import org.licho.brain.resource.Image;
import org.licho.brain.simuApi.IModel;
import org.licho.brain.simuApi.IModels;
import org.licho.brain.simuApi.ISimioProject;
import org.licho.brain.utils.simu.IFilesStream;
import org.licho.brain.utils.simu.system.IDisposable;
import org.licho.brain.utils.simu.system.PropertyChangedEventArgs;
import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

/**
 *
 */
@Setter
@Getter
public class SimioProjectDefinition implements ISimioProject, IModels {
    public static final String objectName = "ModelEntity";
    public static final String pictureName = "Picture";
    public static final String animationStateName = "Animation";
    public List<ActiveModel> ActiveModels = new ArrayList<>();

    //Class591
    private String name;

    private Image icon;

    private int count;
    private char csvSeparator;

    private SimioApplicationContext applicationContext;
    private ItemEditPolicy itemEditPolicy = new ItemEditPolicy();
    public boolean isInited;
    private String fileName;
    private DocumentInfo documentInfo;
    private EventHandler<AddActiveModelEventArgs> addActiveModelEvent = new EventHandler<>();
    private String localName;
    private EventHandler<SimioProjectDefinition> libraryChangedEventHandler;
    private EventHandler<PropertyChangedEventArgs> propertyChangedEventHandler;


    public SimioProjectDefinition() {
    }

    public SimioProjectDefinition(SimioApplicationContext applicationContext) {
        this.applicationContext = applicationContext;
    }

    public void addLimitEventHandler(IEvent<AddActiveModelEventArgs> value) {
        EventHandler.subscribe(this.addActiveModelEvent, value);
    }

    public void removeLimitEventHandler(IEvent<AddActiveModelEventArgs> value) {
        EventHandler.unSubscribe(this.addActiveModelEvent, value);
    }


    @Override
    public String getName() {
        return this.name;
    }

    public static String getName(String localName, String fileName) {
        if (!Strings.isNullOrEmpty(localName)) {
            return localName;
        }
        if (Strings.isNullOrEmpty(fileName)) {
            return EngineResources.DefaultProjectName;
        }
        return fileName.substring(fileName.lastIndexOf('.'));
    }

    @Override
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public IModels getModels() {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IModel getByIndex(int index) {
        return null;
    }

    @Override
    public String toString() {
        return "Project";
    }

    public ItemEditPolicy getItemEditPolicy() {
        return this.itemEditPolicy;
    }

    public void inited() {
        this.isInited = true;
    }

    public String getFileName() {
        return this.fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    @Override
    public Iterator<IModel> iterator() {
        return null;
    }

    public LoadOperator loadXml(IFilesStream filesStream, IntelligentObjectXmlReader intelligentObjectXmlReader,
                                ExperimentConstraintsXmlReader experimentConstraintsXmlReader, boolean multi) {
        SimioProjectDefinition.LoadOperator result;
        try (InOutputStream stream = filesStream.OpenMainStream()) {
            if (stream == null) {
                throw new IllegalArgumentException("Unable to get XML contents");
            }

            IntelligentObjectXml intelligentObjectXml = this.CreateReadingContext();
            intelligentObjectXml.setMultiFile(multi);
            intelligentObjectXml.setFiles(filesStream.StreamStore());
            this.preLoadXml(stream.inputStream);
            stream.reopen();
            this.readXMlCommonItems(stream.inputStream, intelligentObjectXml);
            stream.reopen();
            boolean flag = this.loadProjectXml(stream, intelligentObjectXml, intelligentObjectXmlReader,
                    experimentConstraintsXmlReader);
            if (flag) {
                this.FinishedReading(intelligentObjectXml);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void FinishedReading(IntelligentObjectXml intelligentObjectXml) {
    }


    public boolean containActiveModel(ActiveModel activeModel) {
        return this.ActiveModels.contains(activeModel);
    }

    public char getCsvSeparator() {
        return csvSeparator;
    }

    public void setCsvSeparator(char csvSeparator) {
        this.csvSeparator = csvSeparator;
    }

    private boolean loadProjectXml(InOutputStream stream, IntelligentObjectXml intelligentObjectXml,
                                   IntelligentObjectXmlReader intelligentObjectXmlReader,
                                   ExperimentConstraintsXmlReader experimentConstraintsXmlReader) {
        class Display {
            public IntelligentObjectXml readContext;
            public IntelligentObjectXmlReader readModelInfoDel;
            public ExperimentConstraintsXmlReader readExperimentInfoDel;
        }

        var display = new Display();
        display.readContext = intelligentObjectXml;
        display.readModelInfoDel = intelligentObjectXmlReader;
        display.readExperimentInfoDel = experimentConstraintsXmlReader;
        this.OnLoadingFromXmlWithContext(display.readContext);
        InputStreamReader streamReader = new InputStreamReader(stream.inputStream);
        boolean result = false;
        try (XmlReader xmlReader = XmlReader.Create(streamReader, XmlSettings.Pure)) {
            xmlReader.MoveToContent();
            String nodeName = "Project";

            SomeXmlOperator.AttributeXmlDelegate attributeXmlDelegate = attr -> {
                String attribute = attr.GetAttribute("Name");
                if (!Strings.isNullOrEmpty(attribute)) {
                    this.name = attribute;
                }
                display.readContext.readXmlFileVersion(attr);
                SomeXmlOperator.readXmlAttributeString(attr, "CsvSeparator", t -> this.setCsvSeparator(t.charAt(0)));
                this.ReadAttributesFromXml(attr, display.readContext);
                if (this.documentInfo == null) {
                    this.documentInfo = new DocumentInfo();
                }
            };

            SomeXmlOperator.BodyXmlDelegate bodyXmlDelegate = body ->
                    SomeXmlOperator.xmlReaderElementOperator(body, "Icon", null,
                            iconBody -> SomeXmlOperator.readXmlIcon(iconBody, display.readContext, this::setIcon)) ||

                            SomeXmlOperator.xmlReaderElementAll(body, "Models", null,
                                    beforeModels -> display.readContext.createReadModelOperator(),
                                    modelsBody -> ActiveModel.readXmlModel(modelsBody, display.readContext, this,
                                            null, ActiveModel.Enum46.Zero, display.readModelInfoDel,
                                            display.readExperimentInfoDel) != null,
                                    afterModels -> {
                                        for (ActiveModel activeModel : this.ActiveModels) {
                                            activeModel.flush();
                                        }
                                    }) ||

                            this.ReadBodyFromXml(xmlReader, display.readContext) ||
                            (Objects.equals(xmlReader.Name(), "CommonItems") && display.readContext.readXmlCommonItemsNode(xmlReader));

            result = SomeXmlOperator.xmlReaderElementAll(xmlReader, nodeName, attributeXmlDelegate,
                    this::aboutLicensed, bodyXmlDelegate, t -> {
                        this.SubmitToSearch();
                        this.falseChanged();
                    });
        }
        return result;
    }

    private void falseChanged() {
        for (ActiveModel activeModel : this.ActiveModels) {
            activeModel.getIntelligentObjectDefinition().unChanged();
        }
        this.isInited = false;
    }

    protected boolean ReadBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return false;
    }

    private void SubmitToSearch() {
        this.ActiveModels.forEach(this::SubmitToSearch);
    }

    public ActiveModel get(String name) {
        ActiveModel result = this.findActiveModelByName(name);
        if (result != null) {
            return result;
        }
        throw new IllegalArgumentException();
    }

    public ActiveModel findActiveModelByName(String name) {
        return this.ActiveModels.stream()
                .filter(t -> t.getIntelligentObjectDefinition().Name().equals(name))
                .findFirst().orElse(null);
    }

    private IDisposable aboutLicensed(XmlReader xmlReader) {
        return null;
    }

    protected void ReadAttributesFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
    }


    protected void OnLoadingFromXmlWithContext(IntelligentObjectXml readContext) {
    }

    private void preLoadXml(InputStream stream) throws IOException {
        var streamReader = new InputStreamReader(stream);
        if (!XMLCheck.check(streamReader)) {
            throw new IOException("Unable to verify XML contents");
        }
    }

    private void readXMlCommonItems(InputStream stream, IntelligentObjectXml intelligentObjectXml) {
        InputStreamReader reader = new InputStreamReader(stream);
        try (XmlReader xmlReader = XmlReader.Create(reader, XmlSettings.Pure)) {
            xmlReader.MoveToContent();
            intelligentObjectXml.readXMlCommonItems(xmlReader);
        }
    }


    public IntelligentObjectXml CreateReadingContext() {
        return new IntelligentObjectXml(0);
    }

    @Override
    public IModel getByName(String name) {
        return null;
    }

    public ActiveModel createActiveModel(ObjectClass objectClass, Action<ActiveModel> initializeModel) {
        return this.createActiveModel(objectClass, null, initializeModel);

    }

    public ActiveModel createActiveModel(ObjectClass objectClass, String param1, Action<ActiveModel> initializeModel) {
        return this.createActiveModel(null, objectClass, param1, initializeModel);
    }

    private ActiveModel createActiveModel(IntelligentObjectDefinition intelligentObjectDefinition,
                                          ObjectClass objectClass, String param2, Action<ActiveModel> initializeModel) {
        ActiveModel activeModel = new ActiveModel(this);
        activeModel.reRegisterEventByType(intelligentObjectDefinition, objectClass,
                this.getLastClassNameByType(param2, objectClass), false);
        this.BootstrapModel(activeModel);
        if (initializeModel != null) {
            initializeModel.apply(activeModel);
        }
        activeModel.getIntelligentObjectDefinition().unChanged();
        this.addActiveModelAndLimit(activeModel);
        return activeModel;
    }

    protected void BootstrapModel(ActiveModel activeModel) {
    }

    private String getLastClassNameByType(String name, ObjectClass objectClass) {
        String className = "Model";
        if (name == null) {
            if (objectClass != ObjectClass.Fixed) {
                className = objectClass.toString();
            }
        } else {
            className = name;
        }
        int count = (int) this.ActiveModels.stream().filter((ActiveModel model) -> {
            if (name == null) {
                return model.getIntelligentObjectDefinition().ObjectClass() == objectClass;
            }
            return model.Name().startsWith(name);
        }).count();
        return this.getLastName(className, count, objectClass == ObjectClass.Fixed || name != null);
    }

    private String getLastName(String name, int matchNameCount, boolean fixedAndName) {
        if (fixedAndName && this.findActiveModelByName(name) == null) {
            return name;
        }
        String text;
        do {
            matchNameCount++;
            text = name + matchNameCount;
        }
        while (this.findActiveModelByName(text) != null);
        return text;
    }

    public void addActiveModelAndLimitOuter(ActiveModel activeModel) {
        this.addActiveModelAndLimit(activeModel);

    }

    private void addActiveModelAndLimit(ActiveModel activeModel) {
        int index = this.addActiveModel(activeModel);
        this.limit(activeModel, index);
    }

    private void limit(ActiveModel activeModel, int index) {
        this.ActiveModels.forEach(model -> model.limit(activeModel));
        EventHandler.fire(this.addActiveModelEvent, this, new AddActiveModelEventArgs(activeModel, index));
    }

    private int addActiveModel(ActiveModel activeModel) {
        this.ActiveModels.add(activeModel);
        this.inited();
        this.SubmitToSearch(activeModel);
        return this.ActiveModels.indexOf(activeModel);
    }

    private void SubmitToSearch(ActiveModel activeModel) {
        activeModel.getIntelligentObjectDefinition().SubmitToSearch((ISearch search) ->
                search.SubmitToSearch(this.itemEditPolicy, activeModel));
    }

    public ILibraries Libraries() {
        return null;
    }

    public Image Icon() {
        return this.icon;
    }

    public void Icon(Image icon) {
        // TODO: 2022/1/28 
    }

    public int getActiveModelsCount() {
        return this.ActiveModels.size();
    }

    public ActiveModel get(int index) {
        return this.ActiveModels.get(index);
    }

    public boolean getInited() {
        return this.isInited;
    }

    public String Name() {
        return SimioProjectDefinition.getName(this.localName, this.fileName);
    }

    public void Name(String value) {
        if (value != this.localName) {
            this.localName = value;
            this.inited();
            this.triggerLibraryChangedEventHandler();
            this.triggerPropertyCHangedEventHandler("Name");
            this.triggerPropertyCHangedEventHandler("Project Name");
        }
    }

    private void triggerPropertyCHangedEventHandler(String name) {
        if (this.propertyChangedEventHandler != null) {
            this.propertyChangedEventHandler.fire(this, new PropertyChangedEventArgs(name));
        }
    }

    private void triggerLibraryChangedEventHandler() {
        if (this.libraryChangedEventHandler != null) {
            this.libraryChangedEventHandler.fire(null, this);
        }
    }

    public ActiveModel initProjectModels(Action<ActiveModel> initializeModel) throws InvalidOperationException {
        if (this.getInited() || this.getActiveModelsCount() > 0) {
            throw new InvalidOperationException();
        }

        boolean[] isNewActiveModel = new boolean[1];
        ActiveModel result = this.initProjectModels(initializeModel, isNewActiveModel)[1];
        this.isInited = false;
        return result;
    }

    private ActiveModel[] initProjectModels(Action<ActiveModel> initializeModel, boolean[] isNewEntity) {
        ActiveModel[] activeModels = new ActiveModel[2];
        isNewEntity[0] = false;
        for (ActiveModel activeModel : this.ActiveModels) {
            if (activeModel.getIntelligentObjectDefinition() instanceof EntityDefinition) {
                activeModels[0] = activeModel;
                break;
            }
        }
        if (activeModels[0] == null) {
            activeModels[0] = this.createActiveModel(null, ObjectClass.Entity, null, initializeModel);
            activeModels[0].Name(this.getModelEntityName(SimioProjectDefinition.objectName));
            activeModels[0].getIntelligentObjectDefinition().Description(EngineResources.ModelEntityDescription);
            this.SetupDefaultEntityDefinition((EntityDefinition) activeModels[0].getIntelligentObjectDefinition());
            isNewEntity[0] = true;
        }
        activeModels[1] = this.createActiveModel(null, ObjectClass.Fixed, null, initializeModel);
        Entity entity = ((FixedDefinition) activeModels[1].getIntelligentObjectDefinition())
                .createEntity((EntityDefinition) activeModels[0].getIntelligentObjectDefinition());
        this.SetupDefaultEntityInstance(entity);
        activeModels[1].getIntelligentObjectDefinition().getWorkSchedulesUtils().setScheduleTime();
        PropertyDefinitions propertyDefinitions =
                activeModels[1].getIntelligentObjectDefinition().getPropertyDefinitions();
        propertyDefinitions.findStringPropertyDefinitionInfoByName("ReportStatistics").SetLocalVisible(false,
                propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("ParentCostCenter").SetLocalVisible(false,
                propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("InitialCost").SetLocalVisible(false,
                propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("InitialCostRate").SetLocalVisible(false,
                propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("DisplayName").SetLocalVisible(false,
                propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("TransferInConstraintsType").SetLocalVisible(false
                , propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("TransferOutConstraintsType").SetLocalVisible(false,
                propertyDefinitions);
        propertyDefinitions.findStringPropertyDefinitionInfoByName("CurrentSizeIndex").SetLocalVisible(false,
                propertyDefinitions);
        activeModels[1].getIntelligentObjectDefinition().ResourceLogic(false);
        return activeModels;
    }

    private void SetupDefaultEntityInstance(Entity entity) {
        entity.setSize(0.5, 0.5, 0.25);
    }

    protected void SetupDefaultEntityDefinition(EntityDefinition intelligentObjectDefinition) {

    }

    private String getModelEntityName(String name) {
        return this.getLastName(name, 1, true);
    }

    public void updateModel(ActiveModel activeModel) {
        if (this.ActiveModels.remove(activeModel)) {
            this.ActiveModels.add(activeModel);
        }
    }

    public class LoadOperator {
    }

    public interface IntelligentObjectXmWriter {
        boolean apply(ActiveModel model, XmlWriter writer, CommonItems writeContext);
    }

    public interface IntelligentObjectXmlReader {
        boolean apply(ActiveModel model, XmlReader reader, IntelligentObjectXml readContext);
    }

    public interface ExperimentConstraintsXmlWriter {
        boolean apply(ExperimentConstraintsDefinition experimentConstraintsDefinition, XmlWriter writer,
                      CommonItems writeContext);
    }

    public interface ExperimentConstraintsXmlReader {
        boolean apply(ExperimentConstraintsDefinition experimentConstraintsDefinition, XmlReader reader,
                      IntelligentObjectXml readContext);

    }

}
