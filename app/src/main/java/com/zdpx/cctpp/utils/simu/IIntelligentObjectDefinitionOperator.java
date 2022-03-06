package com.zdpx.cctpp.utils.simu;

import com.zdpx.cctpp.concrete.IntelligentObjectDefinition;

/**
 *
 */
public interface IIntelligentObjectDefinitionOperator {
	IntelligentObjectDefinition getIntelligentObjectDefinition(String name);

	String Name(IntelligentObjectDefinition intelligentObjectDefinition);
}
