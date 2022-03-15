package org.licho.brain.concrete;

import org.licho.brain.concrete.step.BatchStepDefinition;
import org.licho.brain.concrete.step.ConsumeStepDefinition;
import org.licho.brain.concrete.step.CreateStepDefinition;
import org.licho.brain.concrete.step.DecideStepDefinition;
import org.licho.brain.concrete.step.DelayStepDefinition;
import org.licho.brain.concrete.step.DestroyStepDefinition;
import org.licho.brain.concrete.step.EndTransferStepDefinition;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StepDefinitionWrapper {

	public static void init()
	{
		List<AbsStepDefinition> definitions = new ArrayList<>();
			definitions.add(AbsBaseStepDefinition.definition.Instance(EndTransferStepDefinition.class));
			definitions.add(AbsBaseStepDefinition.definition.Instance(AssignStepDefinition.class));
			definitions.add(AbsBaseStepDefinition.definition.Instance(BatchStepDefinition.class));
			definitions.add(AbsBaseStepDefinition.definition.Instance(ConsumeStepDefinition.class));
			definitions.add(AbsBaseStepDefinition.definition.Instance(CreateStepDefinition.class));
			definitions.add(AbsBaseStepDefinition.definition.Instance(DecideStepDefinition.class));
			definitions.add(AbsBaseStepDefinition.definition.Instance(DelayStepDefinition.class));
			definitions.add(AbsBaseStepDefinition.definition.Instance(DestroyStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(DisengageStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(DropoffStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(EndActivityStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(EndOperationStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(EngageStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(ExecuteStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(FailStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(FireStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(ParkStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(PickupStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(PlanVisitStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(AllocateStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(ProduceStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(ReleaseStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(RepairStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(ResumeStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(RideStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(RouteStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(SeizeStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(SelectDropoffStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(SelectVisitStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(SetRowStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(SetNetworkStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(SetNodeStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(StartActivityStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(StartOperationStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(SuspendStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(TallyStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(TravelStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(UnBatchStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(UnParkStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(VisitNodeStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(WaitStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(TransferStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(SearchStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(SubscribeStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(UnSubscribeStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(InsertStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(RemoveStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(ArriveStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(InterruptStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(MoveStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(NotifyStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(AddRowStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(EndRunStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(ClearStatisticsStepDefinition.class));
//			definitions.add(AbsBaseStepDefinition.definition.Instance(FindStepDefinition.class));

		AbsStepDefinition.init(definitions);
	}

}
