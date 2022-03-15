package org.licho.brain.concrete;

import org.licho.brain.brainEnums.StateReferenceType;

/**
 *
 */
public class StateReferenceTypeValueProvider implements IValueProvider {
    private boolean isAssignable;
    private boolean dontUseAssociatedObjects;
    private StateReferenceType value;
    private String nullNullString;

    public StateReferenceTypeValueProvider(String nullNullString, boolean isAssignable,
                                           boolean dontUseAssociatedObjects,
                                           StateReferenceType stateReferenceType) {
        this.nullNullString = nullNullString;
        this.isAssignable = isAssignable;
        this.dontUseAssociatedObjects = dontUseAssociatedObjects;
        this.value = stateReferenceType;
    }

    private boolean valid(BaseStatePropertyObject baseStatePropertyObject) {
        switch (this.value) {
            case AnyState:
                return true;
            case DiscreteState:
                return true;
            case QueueState:
                if (baseStatePropertyObject instanceof AbsQueuePropertyObject) {
                    return true;
                }
                break;
            case ListState:
                if (baseStatePropertyObject instanceof ListStatePropertyObject) {
                    return true;
                }
                break;
            case LevelState:
                if (baseStatePropertyObject instanceof CostStatePropertyObject) {
                    return true;
                }
                break;
            case MovementState:
                if (baseStatePropertyObject instanceof MovementStatePropertyObject) {
                    return true;
                }
                break;
            case QueueStateModifyable: {
                if (baseStatePropertyObject instanceof AbsQueuePropertyObject) {
                    AbsQueuePropertyObject absQueueProperty = (AbsQueuePropertyObject) baseStatePropertyObject;
                    if (absQueueProperty.CanInsert() && absQueueProperty.CanRemove()) {
                        return true;
                    }
                }
                break;
            }
            case QueueStateRemovable: {
                if (baseStatePropertyObject instanceof AbsQueuePropertyObject) {
                    AbsQueuePropertyObject absQueueProperty = (AbsQueuePropertyObject) baseStatePropertyObject;
                    if (absQueueProperty.CanRemove()) {
                        return true;
                    }
                }
                break;
            }
        }
        return false;
    }

    @Override
    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {
        return new String[0];
    }
}
