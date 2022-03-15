package com.zdpx.cctpp.concrete.property;


import com.zdpx.cctpp.concrete.AbsPropertyObject;
import com.zdpx.cctpp.concrete.ActiveModel;
import com.zdpx.cctpp.concrete.BindingList;
import com.zdpx.cctpp.concrete.ExperimentConstraint;
import com.zdpx.cctpp.concrete.ExperimentResponse;
import com.zdpx.cctpp.concrete.GridObjectDefinition;
import com.zdpx.cctpp.concrete.INotifyPropertyChanged;
import com.zdpx.cctpp.concrete.IntelligentObjectXml;
import com.zdpx.cctpp.concrete.Scenario;
import com.zdpx.cctpp.concrete.SimioProjectDefinition;
import com.zdpx.cctpp.concrete.StringPropertyDefinition;
import com.zdpx.cctpp.concrete.UnitConversions;
import com.zdpx.cctpp.concrete.fake.TimeSpan;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.enu.StatisticConfidenceIntervalType;
import com.zdpx.cctpp.enu.UnitType;
import com.zdpx.cctpp.resource.Image;
import com.zdpx.cctpp.simuApi.IExperiment;
import com.zdpx.cctpp.simuApi.IExperimentConstraint;
import com.zdpx.cctpp.simuApi.IExperimentConstraints;
import com.zdpx.cctpp.simuApi.IItemDescriptor;
import com.zdpx.cctpp.simuApi.IModel;
import com.zdpx.cctpp.simuApi.IRunSetup;
import com.zdpx.cctpp.utils.simu.IUnit;
import com.zdpx.cctpp.utils.simu.IValues;
import com.zdpx.cctpp.utils.simu.system.DateTime;
import com.zdpx.cctpp.utils.simu.system.IBindingList;
import com.zdpx.cctpp.utils.simu.system.ICancelAddNew;
import com.zdpx.cctpp.utils.simu.system.IDisposable;
import com.zdpx.cctpp.utils.simu.system.ListChangedEventArgs;
import com.zdpx.cctpp.utils.simu.system.PropertyChangedEventArgs;
import com.zdpx.cctpp.utils.simu.system.PropertyChangedEventHandler;

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
                activeModel.getIntelligentObjectDefinition().getPropertyDefinitions().values) {
            super.getPropertyDefinitions().addDefinition(stringPropertyDefinition);
            if (!stringPropertyDefinition.IsOwnedBy(activeModel.getIntelligentObjectDefinition())) {
                stringPropertyDefinition.SetLocalVisible(false, super.getPropertyDefinitions());
            }
        }
        this.initEvent();
    }

    private void initEvent() {
        this.activeModel.getIntelligentObjectDefinition().getPropertyDefinitions().addListChanged(this::
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
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
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
