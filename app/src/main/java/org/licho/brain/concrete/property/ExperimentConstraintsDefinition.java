package org.licho.brain.concrete.property;


import org.licho.brain.concrete.AbsPropertyObject;
import org.licho.brain.concrete.ActiveModel;
import org.licho.brain.concrete.BindingList;
import org.licho.brain.concrete.ExperimentConstraint;
import org.licho.brain.concrete.ExperimentResponse;
import org.licho.brain.concrete.GridObjectDefinition;
import org.licho.brain.concrete.INotifyPropertyChanged;
import org.licho.brain.concrete.IntelligentObjectXml;
import org.licho.brain.concrete.Scenario;
import org.licho.brain.concrete.SimioProjectDefinition;
import org.licho.brain.concrete.StringPropertyDefinition;
import org.licho.brain.concrete.UnitConversions;
import org.licho.brain.concrete.fake.TimeSpan;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.enu.StatisticConfidenceIntervalType;
import org.licho.brain.enu.UnitType;
import org.licho.brain.resource.Image;
import org.licho.brain.api.IExperiment;
import org.licho.brain.api.IExperimentConstraint;
import org.licho.brain.api.IExperimentConstraints;
import org.licho.brain.api.IItemDescriptor;
import org.licho.brain.api.IModel;
import org.licho.brain.api.IRunSetup;
import org.licho.brain.utils.simu.IUnit;
import org.licho.brain.utils.simu.IValues;
import org.licho.brain.utils.simu.system.DateTime;
import org.licho.brain.utils.simu.system.IBindingList;
import org.licho.brain.utils.simu.system.ICancelAddNew;
import org.licho.brain.utils.simu.system.IDisposable;
import org.licho.brain.utils.simu.system.ListChangedEventArgs;
import org.licho.brain.utils.simu.system.PropertyChangedEventArgs;
import org.licho.brain.utils.simu.system.PropertyChangedEventHandler;

import java.beans.PropertyDescriptor;
import java.util.Iterator;
import java.util.Objects;

/**
 *
 */
public final class ExperimentConstraintsDefinition extends GridObjectDefinition implements IDisposable,
        INotifyPropertyChanged,
        IExperiment, IRunSetup, IItemDescriptor, IExperimentConstraints, IBindingList, ICancelAddNew, IUnit, IValues {
    private ActiveModel activeModel;
    private final BindingList<ExperimentResponse> responses = new BindingList<>();
    private BindingList<ExperimentConstraint> constraints = new BindingList<>();
    private int defaultReplicationsRequired = 10;
    private PropertyChangedEventHandler propertyChangedEventHandler;


    public ExperimentConstraintsDefinition(String name, ActiveModel activeModel) {
        super(name);
        this.activeModel = activeModel;
        for (StringPropertyDefinition stringPropertyDefinition :
                activeModel.getDefinition().getPropertyDefinitions().values) {
            super.getPropertyDefinitions().addDefinition(stringPropertyDefinition);
            if (!stringPropertyDefinition.IsOwnedBy(activeModel.getDefinition())) {
                stringPropertyDefinition.SetLocalVisible(false, super.getPropertyDefinitions());
            }
        }
        this.initEvent();
    }

    private void initEvent() {
        this.activeModel.getDefinition().getPropertyDefinitions().addListChanged(this::
                onPropertyListChanged);
        this.responses.addListChanged(this::onResponsesListChanged);
        this.constraints.addListChanged(this::onConstraintsListChanged);
    }

    private void onResponsesListChanged(Object sender, ListChangedEventArgs e) {
        // TODO: 2022/1/28
    }

    private void onPropertyListChanged(Object sender, ListChangedEventArgs e) {
        // TODO: 2022/1/28
    }

    private void onConstraintsListChanged(Object sender, ListChangedEventArgs e) {
        // TODO: 2022/1/28
    }


    public static ExperimentConstraintsDefinition readXml(XmlReader xmlReader,
                                                          IntelligentObjectXml intelligentObjectXml,
                                                          ActiveModel activeModel,
                                                          SimioProjectDefinition.ExperimentConstraintsXmlReader experimentConstraintsXmlReader) {
        if (Objects.equals(xmlReader.Name(), "Experiment") && activeModel != null) {
            ExperimentConstraintsDefinition experimentConstraintsDefinition = activeModel.addExperimentConstraints();
            experimentConstraintsDefinition.readXml(xmlReader, intelligentObjectXml, experimentConstraintsXmlReader);
            if (intelligentObjectXml.FileVersion() < 11) {
                experimentConstraintsDefinition.Reset();
            }
            return experimentConstraintsDefinition;
        }
        return null;
    }

    private void Reset() {

    }

    private void readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                         SimioProjectDefinition.ExperimentConstraintsXmlReader experimentConstraintsXmlReader) {

    }

    public ActiveModel getProjectAndApplicationContext() {
        return this.activeModel;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    @Override
    public void Dispose() {

    }

    @Override
    public String getName() {
        return null;
    }

    @Override
    public IModel getModel() {
        return null;
    }

    @Override
    public IRunSetup getRunSetup() {
        return null;
    }

    @Override
    public Object getItem() {
        return null;
    }

    @Override
    public String Group() {
        return null;
    }

    @Override
    public int GroupImportance() {
        return 0;
    }

    @Override
    public String DisplayName() {
        return null;
    }

    @Override
    public String ObjectType() {
        return null;
    }

    @Override
    public String Category() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 0;
    }

    @Override
    public int SateIconIndex() {
        return 0;
    }

    @Override
    public Image getIcon() {
        return null;
    }

    @Override
    public String getSummary() {
        return null;
    }

    @Override
    public void Rename(String newName) {

    }

    @Override
    public boolean canRenameTo(String newName, String failureReason) {
        return false;
    }

    @Override
    public DateTime StartTime() {
        return null;
    }

    @Override
    public void StartTime(DateTime startTime) {

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
        return null;
    }

    @Override
    public void WarmupPeriod(TimeSpan warmupPeriod) {

    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IExperimentConstraint getByIndex(int index) {
        return null;
    }

    @Override
    public IExperimentConstraint getByName(String name) {
        return null;
    }

    @Override
    public Iterator<IExperimentConstraint> iterator() {
        return null;
    }

    public ActiveModel getActiveModel() {
        return this.activeModel;
    }

    @Override
    public boolean AllowNew() {
        return false;
    }

    @Override
    public <T> T AddNew() {
        return null;
    }

    @Override
    public boolean AllowEdit() {
        return false;
    }

    @Override
    public boolean AllowRemove() {
        return false;
    }

    @Override
    public boolean SupportsChangeNotification() {
        return false;
    }

    @Override
    public boolean SupportsSearching() {
        return false;
    }

    @Override
    public boolean SupportsSorting() {
        return false;
    }

    @Override
    public boolean IsSorted() {
        return false;
    }

    @Override
    public PropertyDescriptor SortProperty() {
        return null;
    }

    @Override
    public void AddIndex(PropertyDescriptor property) {

    }

    @Override
    public int Find(PropertyDescriptor property, Object key) {
        return 0;
    }

    @Override
    public void RemoveIndex(PropertyDescriptor property) {

    }

    @Override
    public void RemoveSort() {

    }

    @Override
    public void CancelNew(int itemIndex) {

    }

    @Override
    public void EndNew(int itemIndex) {

    }

    @Override
    public int GetUnitsFor(UnitType unitType) {
        return 0;
    }

    @Override
    public StatisticConfidenceIntervalType ConfidenceLevel() {
        return null;
    }

    @Override
    public UnitConversions UnitConversions() {
        return null;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Scenario(this, name);
    }

    public int DefaultReplicationsRequired() {
        return this.defaultReplicationsRequired;
    }

    public void DefaultReplicationsRequired(int value) {
        this.defaultReplicationsRequired = value;
        this.triggerPropertyChangedEventHandler("DefaultReplicationsRequired");
    }

    private void triggerPropertyChangedEventHandler(String name) {
        if (this.propertyChangedEventHandler != null) {
            this.propertyChangedEventHandler.fire(this, new PropertyChangedEventArgs(name));
        }
    }
}
