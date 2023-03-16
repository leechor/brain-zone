package org.licho.brain.concrete;

/**
 *
 */
public class CompleteChoiceOperator {
    private IntelligentObjectDefinition intelligentObjectDefinition;
    private ProjectManager projectManager;

    public CompleteChoiceOperator(IntelligentObjectDefinition intelligentObjectDefinition,
								  ProjectManager projectManager) {
        this.intelligentObjectDefinition = intelligentObjectDefinition;
        this.projectManager = projectManager;
    }
}
