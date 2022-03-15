package org.licho.brain.concrete;

import org.licho.brain.concrete.annotation.BaseElementFunction;
import org.licho.brain.concrete.annotation.ElementFunction;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EntityRunSpace;
import org.licho.brain.concrete.property.AbsBaseRunSpace;
import org.licho.brain.element.Station;
import org.licho.brain.enu.IconIndex;
import org.licho.brain.enu.ObjectClass;
import org.licho.brain.enu.ProductComplexityLevel;
import org.licho.brain.enu.TransporterInterfaceProcess;
import org.licho.brain.simioEnums.ElementScope;
import org.licho.brain.utils.simu.IEntityProcess;

import java.util.Collection;

/**
 *
 */
public class TransporterDefinition extends EntityDefinition {
    public static final Guid guid = new Guid("{34DBE61D-0AD3-4041-A98C-BFE214625925}");
    public static final String name = "Transporter";
    public static final TransporterDefinition transporterFacility = new TransporterDefinition(null);

    static {
        InternalReference.put(TransporterDefinition.name, TransporterDefinition.transporterFacility);
    }

    public TransporterDefinition(String name, ActiveModel activeModel,
                                 IntelligentObjectDefinition parent) {
        super(name, activeModel, parent);
    }

    private TransporterDefinition(ActiveModel activeModel) {
        super(TransporterDefinition.name, activeModel, TransporterDefinition.guid);
        super.Description(EngineResources.Transporter_Description);

        Station station =
                super.addElement(StationDefinition.createStation("RideStation"), ElementScope.Public);

        station.Description(EngineResources.Transporter_RideStation_Description);
        station.setBooleanPropertyStringValue(true);

        station.getInitialCapacity().StringValue("InitialRideCapacity");
        station.getCostPerUse().StringValue("TransportCostPerRider");
        station.getHoldingCostRate().StringValue("TransportCostRate");
    }

    @Override
    public ObjectClass ObjectClass() {
        return ObjectClass.Transporter;
    }

    @Override
    protected void DefineSchema() {
        super.DefineSchema();

        ExpressionPropertyDefinition initialRideCapacity = new ExpressionPropertyDefinition("InitialRideCapacity");
        initialRideCapacity.Description(EngineResources.Transporter_InitialRideCapacity_Description);
        initialRideCapacity.DisplayName(EngineResources.Transporter_InitialRideCapacity_DisplayName);
        initialRideCapacity.CategoryName(EngineResources.CategoryName_TransportLogic);
        initialRideCapacity.DefaultString("1");
        initialRideCapacity.ComplexityLevel(ProductComplexityLevel.Basic);

        ExpressionPropertyDefinition transportCostPerRider = new ExpressionPropertyDefinition("TransportCostPerRider");
        transportCostPerRider.DisplayName(EngineResources.Transporter_TransportCostPerRider_DisplayName);
        transportCostPerRider.Description(EngineResources.Transporter_TransportCostPerRider_Description);
        transportCostPerRider.DefaultString("0.0");
        transportCostPerRider.CategoryName(EngineResources.CategoryName_FinancialsTransportCosts);
        transportCostPerRider.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.Currency);
        transportCostPerRider.ComplexityLevel(ProductComplexityLevel.Advanced);

        ExpressionPropertyDefinition transportCostRate = new ExpressionPropertyDefinition("TransportCostRate");
        transportCostRate.DisplayName(EngineResources.Transporter_TransportCostRate_DisplayName);
        transportCostRate.Description(EngineResources.Transporter_TransportCostRate_Description);
        transportCostRate.DefaultString("0.0");
        transportCostRate.CategoryName(EngineResources.CategoryName_FinancialsTransportCosts);
        transportCostRate.UnitType(NumericDataPropertyDefinition.NumericPropertyUnitType.CurrencyPerTimeUnit);
        transportCostRate.ComplexityLevel(ProductComplexityLevel.Advanced);

        super.getPropertyDefinitions().add(initialRideCapacity);
        super.getPropertyDefinitions().add(transportCostPerRider);
        super.getPropertyDefinitions().add(transportCostRate);
        super.createProcessProperties(ObjectClass.Transporter);
    }

    @Override
    public IntelligentObjectDefinition CreateNewBaseClassDefinition() {
        return TransporterDefinition.create();
    }

    public static TransporterDefinition create() {
        return new TransporterDefinition(null);
    }

    @Override
    protected boolean IsImplicitlySubclassedFrom(IntelligentObjectDefinition intelligentObjectDefinition) {
        return super.IsImplicitlySubclassedFrom(intelligentObjectDefinition) || intelligentObjectDefinition.getGuid() == EntityDefinition.guid;
    }

    public Station getStationProperty() {
        return (Station) this.elements.values.get(0);
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
        return new Transporter(this, name, ElementScope.Private);
    }

    @Override
    public void GetInterfaceProcessInformation(Collection<IEntityProcess> infoList) {
        super.GetInterfaceProcessInformation(infoList);
        super.injectEntityProcessToObjectClass(infoList, TransporterInterfaceProcess.class, ObjectClass.Transporter);
    }

    @Override
    public IconIndex AutoCompleteChoiceIconIndex() {
        return IconIndex.Ten;
    }

    @Override
    public StringPropertyDefinition GetPropertyForLoad(String name, IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() <= 49 && name.equals("RideCapacity")) {
            return super.getPropertyDefinitions().findStringPropertyDefinitionInfoByName("InitialRideCapacity");
        }
        return super.GetPropertyForLoad(name, intelligentObjectXml);
    }

    @Override
    void DoInterfaceProcessNameFixup(IntelligentObjectXml intelligentObjectXml, StringBuffer oldName,
                                     StringBuffer newName) {
        if (intelligentObjectXml.FileVersion() < 22 && newName.equals("Transporter.OnRiderReservation")) {
            oldName.append("OnRiderReservationAccepted");
            newName.append("Transporter.OnRiderReservationAccepted");
            return;
        }
        super.DoInterfaceProcessNameFixup(intelligentObjectXml, oldName, newName);
    }

    @Override
    public String GetPropertyValueFixup(IntelligentObjectXml intelligentObjectXml, String s) {
        if (intelligentObjectXml.FileVersion() < 22) {
            s = s.replaceAll("(^|[^\\w_])(?i:OnRiderReservation)([^\\w_]|$)", "$1OnRiderReservationAccepted$2");
        }
        return super.GetPropertyValueFixup(intelligentObjectXml, s);
    }

    @BaseElementFunction("RideStationLoad")
    public static double RideStationLoad(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        TransporterRunSpace transporterRunSpace = (TransporterRunSpace) absBaseRunSpace;
        return (double) (transporterRunSpace.RideStation().getQueueGridItemTrace().NumberInQueue() + transporterRunSpace.loadedNum);
    }

    @BaseElementFunction("RideStationOverload")
    public static double RideStationOverload(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        TransporterRunSpace transporterRunSpace = (TransporterRunSpace) absBaseRunSpace;
        return (double) (transporterRunSpace.RideStation().getQueueGridItemTrace().NumberInQueue() + transporterRunSpace.loadedNum) - transporterRunSpace.RideStation().CurrentCapacity().DoubleValue();
    }

    @BaseElementFunction("NumberRiders")
    public static double NumberRiders(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        TransporterRunSpace transporterRunSpace = (TransporterRunSpace) absBaseRunSpace;
        return (double) transporterRunSpace.RideStation().getQueueGridItemTrace().NumberInQueue();
    }

    @BaseElementFunction("NumberRiders.Loading")
    public static double NumberRiders_Loading(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        TransporterRunSpace transporterRunSpace = (TransporterRunSpace) absBaseRunSpace;
        return (double) transporterRunSpace.getPickupNum();
    }

    @BaseElementFunction("NumberRiders.Unloading")
    public static double NumberRiders_Unloading(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        TransporterRunSpace transporterRunSpace = (TransporterRunSpace) absBaseRunSpace;
        return (double) transporterRunSpace.getDropoffNum();
    }

    @ElementFunction("RideCapacityRemaining")
    public static double RideCapacityRemaining(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        TransporterRunSpace transporterRunSpace = (TransporterRunSpace) absBaseRunSpace;
        return transporterRunSpace.RideStation().RideCapacityRemaining();
    }

    @ElementFunction("RemainingRideCapacity")
    public static double RemainingRideCapacity(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return TransporterDefinition.RideCapacityRemaining(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberRidersCanDropoff")
    public static double NumberRidersCanDropoff(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return TransporterDefinition.NumberRiders_CanDropoff(absBaseRunSpace, runSpace);
    }

    @ElementFunction("NumberRiders.CanDropoff")
    public static double NumberRiders_CanDropoff(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        TransporterRunSpace transporterRunSpace = (TransporterRunSpace) absBaseRunSpace;
        if (transporterRunSpace.CurrentNode() == null || transporterRunSpace.RideStation().getQueueGridItemTrace().NumberInQueue() == 0) {
            return 0.0;
        }
        double num = 0.0;
        // TODO: 2021/11/26
        try (QueueList<EntityRunSpace>.EnumeratorUtil queueList =
                     transporterRunSpace.RideStation().getQueueGridItemTrace().getQueueList().getQueueList()) {
            while (queueList.hasNext()) {
                Queue<EntityRunSpace> current = queueList.next();
                EntityRunSpace entityRunSpace = current.current();
                if (!entityRunSpace.isEntryStatus() &&
                        (entityRunSpace.DestinationNode() == transporterRunSpace.CurrentNode() || entityRunSpace.DestinationNode() == null)) {
                    num += 1.0;
                }
            }
        }
        return num;
    }

    @ElementFunction("NumberRiders.CanPickup")
    public static double NumberRiders_CanPickup(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        TransporterRunSpace transporterRunSpace = (TransporterRunSpace) absBaseRunSpace;
        if (transporterRunSpace.CurrentNode() == null || transporterRunSpace.RideStation().RideCapacityRemaining() < 1.0) {
            return 0.0;
        }
        var ridePickupQueue = transporterRunSpace.CurrentNode().RidePickupQueue();
        double num = 0.0;
        try (QueueList<IneligiblePickupSelection>.EnumeratorUtil queueList =
                     ridePickupQueue.getQueueList().getQueueList()) {
            while (queueList.hasNext()) {
                Queue<IneligiblePickupSelection> current = queueList.next();
                if (current.current().method_4(transporterRunSpace, (TokenRunSpace) runSpace, false)) {
                    num += 1.0;
                    if (num == transporterRunSpace.RideStation().RideCapacityRemaining()) {
                        break;
                    }
                }
            }
        }
        return num;
    }

     @ElementFunction("NumberRidersCanPickup")
    public static double NumberRidersCanPickup(AbsBaseRunSpace absBaseRunSpace, IRunSpace runSpace) {
        return TransporterDefinition.NumberRiders_CanPickup(absBaseRunSpace, runSpace);
    }


}
