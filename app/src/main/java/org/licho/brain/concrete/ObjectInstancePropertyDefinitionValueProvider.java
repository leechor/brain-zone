package org.licho.brain.concrete;

import org.licho.brain.brainEnums.ValidObjectType;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class ObjectInstancePropertyDefinitionValueProvider implements IValueProvider {
    private ObjectInstancePropertyDefinition target;

    public ObjectInstancePropertyDefinitionValueProvider(ObjectInstancePropertyDefinition value) {
        this.target = value;
    }

    public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition) {
        if (intelligentObjectDefinition == null) {
            return new String[0];
        }

        List<String> list = new ArrayList<>(4);
        this.addInstanceName(intelligentObjectDefinition.activeModel.getIntelligentObjectDefinition().childrenObject,
                list, this.target);
        this.addInstanceName(intelligentObjectDefinition.activeModel.getIntelligentObjectDefinition().nodes, list,
                this.target);
        list.sort(String::compareTo);
        List<String> list2 = list;
        List<ElementReferenceStatePropertyObject> elementReferenceStateProperties =
                intelligentObjectDefinition.getStateDefinitions().StateProperties.values.stream()
                        .filter(ElementReferenceStatePropertyObject.class::isInstance)
                        .filter(t -> SomeOperatorHelper.funcProcess(ElementReferenceStatePropertyObject.class, t,
                                (IntelligentObjectDefinition e) -> e.valid(this.target.ValidObjectType())))
                        .map(ElementReferenceStatePropertyObject.class::cast).sorted(Comparator.comparing(BaseStatePropertyObject::Name)).collect(Collectors.toList());
        list2.addAll(elementReferenceStateProperties.stream().map(ElementReferenceStatePropertyObject::Name).collect(Collectors.toList()));

        List<String> list3 =
                new ArrayList<>(intelligentObjectDefinition.filterNameByStringPropertyDefinition((StringPropertyDefinition stringPropertyDefinition) -> {
                    ObjectInstancePropertyDefinition objectInstancePropertyDefinition = null;
                    if (stringPropertyDefinition instanceof ObjectInstancePropertyDefinition) {
                        objectInstancePropertyDefinition = (ObjectInstancePropertyDefinition) stringPropertyDefinition;
                    }

                    return (objectInstancePropertyDefinition == null || !this.target.FilterToResources() ||
                            objectInstancePropertyDefinition.FilterToResources()) &&

                            ((stringPropertyDefinition.getClass() == TransporterPropertyDefinition.class &&
                                    (this.target.ValidObjectType() == ValidObjectType.Transporter ||
                                            this.target.ValidObjectType() == ValidObjectType.Entity ||
                                            this.target.ValidObjectType() == ValidObjectType.Agent ||
                                            this.target.ValidObjectType() == ValidObjectType.Object)) ||

                                    (stringPropertyDefinition.getClass() == DynamicObjectInstancePropertyDefinition.class && (this.target.ValidObjectType() == ValidObjectType.Agent ||
                                            this.target.ValidObjectType() == ValidObjectType.Object)) ||
                                    ((stringPropertyDefinition.getClass() == NodePropertyDefinition.class ||
                                            stringPropertyDefinition.getClass() == SequenceDestinationPropertyDefinition.class) && (this.target.ValidObjectType() == ValidObjectType.Node ||
                                            this.target.ValidObjectType() == ValidObjectType.Object)) ||
                                    (objectInstancePropertyDefinition != null && objectInstancePropertyDefinition.ValidObjectType() == this.target.ValidObjectType()));
                }));

        list3.sort(String::compareTo);
        list.addAll(list3);

        if (this.target.NullNullString() != null) {
            list.add(0, this.target.NullNullString());
        }
        return list.toArray(new String[0]);
    }


    private void addInstanceName(Iterable<?> enumerable, List<String> names,
                                 ObjectInstancePropertyDefinition objectInstancePropertyDefinition) {
        for (Object obj : enumerable) {
            IntelligentObject intelligentObject = (IntelligentObject) obj;
            IntelligentObjectDefinition objectObjectDefinition =
                    (IntelligentObjectDefinition) intelligentObject.assignerDefinition;
            if (objectObjectDefinition.valid(objectInstancePropertyDefinition.ValidObjectType()) && (!objectInstancePropertyDefinition.FilterToResources() || objectObjectDefinition.ResourceLogic())) {
                names.add(intelligentObject.InstanceName());
            }
        }
    }

}
