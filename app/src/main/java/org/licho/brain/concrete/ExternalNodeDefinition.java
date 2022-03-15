package org.licho.brain.concrete;

import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.entity.EnumPropertyDefinition;
import org.licho.brain.element.Station;

/**
 *
 */
public class ExternalNodeDefinition extends GridObjectDefinition {
    public static GridObjectDefinition Instance = new ExternalNodeDefinition();

    public ExternalNodeDefinition() {
        super("ExternalNode");


		NodeClassPropertyDefinitionDefinition nodeClassPropertyDefinitionDefinition = new NodeClassPropertyDefinitionDefinition("NodeClassName");
		nodeClassPropertyDefinitionDefinition.DisplayName(EngineResources.ExternalNode_NodeClassName_DisplayName);
		nodeClassPropertyDefinitionDefinition.Description(EngineResources.ExternalNode_NodeClassName_Description);
		nodeClassPropertyDefinitionDefinition.CategoryName(EngineResources.CategoryName_BasicLogic);
		nodeClassPropertyDefinitionDefinition.NullNullString(NodeDefinition.NodeFacility.Name());
		nodeClassPropertyDefinitionDefinition.DefaultString(NodeDefinition.NodeFacility.Name());
		nodeClassPropertyDefinitionDefinition.RequiredValue(false);
		EnumPropertyDefinition enumPropertyDefinition = new EnumPropertyDefinition("InputLocationType",
				NodeInputLogicType.class);
		enumPropertyDefinition.DisplayName(EngineResources.ExternalNode_InputLocationType_DisplayName);
		enumPropertyDefinition.Description(EngineResources.ExternalNode_InputLocationType_Description);
		enumPropertyDefinition.CategoryName(EngineResources.CategoryName_BasicLogic);
		enumPropertyDefinition.DefaultString(NodeInputLogicType.None.toString());
		SequenceDestinationPropertyDefinition sequenceDestinationPropertyDefinition = new SequenceDestinationPropertyDefinition("NodeName");
		sequenceDestinationPropertyDefinition.DisplayName( EngineResources.ExternalNode_NodeName_DisplayName);
		sequenceDestinationPropertyDefinition.Description( EngineResources.ExternalNode_NodeName_Description);
		sequenceDestinationPropertyDefinition.CategoryName( EngineResources.CategoryName_BasicLogic);
		sequenceDestinationPropertyDefinition.DefaultString("");
		sequenceDestinationPropertyDefinition.SwitchNumericProperty( enumPropertyDefinition);
		sequenceDestinationPropertyDefinition.SwitchNumericValue( 1.0);
		sequenceDestinationPropertyDefinition.RequiredValue( false);
		ElementPropertyDefinition stationName = new ElementPropertyDefinition("StationName", Station.class);
//		stationName.method_43(true);
		stationName.DisplayName( EngineResources.ExternalNode_StationName_DisplayName);
		stationName.Description( EngineResources.ExternalNode_StationName_Description);
		stationName.CategoryName( EngineResources.CategoryName_BasicLogic);
		stationName.DefaultString( "");
		stationName.SwitchNumericProperty( enumPropertyDefinition);
		stationName.SwitchNumericValue( 2.0);
		stationName.RequiredValue( false);
		ElementPropertyDefinition containerName = new ElementPropertyDefinition("ContainerName", Container.class);
//		containerName.method_43(true);
		containerName.DisplayName( EngineResources.ExternalNode_ContainerName_DisplayName);
		containerName.Description( EngineResources.ExternalNode_ContainerName_Description);
		containerName.CategoryName( EngineResources.CategoryName_BasicLogic);
		containerName.DefaultString( "");
		containerName.SwitchNumericProperty( enumPropertyDefinition);
		containerName.SwitchNumericValue( 3.0);
		containerName.RequiredValue( false);
		this.propertyDefinitions.add(nodeClassPropertyDefinitionDefinition);
		this.propertyDefinitions.add(enumPropertyDefinition);
		this.propertyDefinitions.add(sequenceDestinationPropertyDefinition);
		this.propertyDefinitions.add(stationName);
		this.propertyDefinitions.add(containerName);

    }

    @Override
    public boolean IsJustAFactory() {
        return true;
    }

    @Override
    public AbsPropertyObject CreateInstance(String name) {
		return new NodeClassProperty(this, name);
    }
}
