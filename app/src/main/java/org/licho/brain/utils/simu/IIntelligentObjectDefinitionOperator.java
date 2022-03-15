package org.licho.brain.utils.simu;

import org.licho.brain.concrete.IntelligentObjectDefinition;

/**
 *
 */
public interface IIntelligentObjectDefinitionOperator {
	IntelligentObjectDefinition getIntelligentObjectDefinition(String name);

	String Name(IntelligentObjectDefinition intelligentObjectDefinition);
}
