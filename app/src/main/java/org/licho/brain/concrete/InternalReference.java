package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.node.Node;
import org.licho.brain.event.EventArgs;
import org.licho.brain.utils.CollectionUtil;
import org.licho.brain.utils.simu.IIntelligentObjectDefinitionOperator;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 *
 */
public class InternalReference implements IIntelligentObjectDefinitionOperator {
    private final IntelligentObjectDefinition intelligentObjectDefinition;
    private static Map<String, IntelligentObjectDefinition> NameDefinitionMap = new HashMap<>();
    private List<NameIntelligentObjectDefinition> reserveNameDefinitions = new ArrayList<>();
    private List<NameIntelligentObjectDefinition> newNameDefinitions = new ArrayList<>();
    private boolean bool_0;

    public InternalReference(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.intelligentObjectDefinition = intelligentObjectDefinition;
    }


    public List<InternalReference.NameIntelligentObjectDefinition> allNameDefinitions() {
        return CollectionUtil.concatenate(this.reserveNameDefinitions, this.newNameDefinitions);
    }

    public Iterable<InternalReference.NameIntelligentObjectDefinition> allNameDefinitionsAndStandardLibrary() {
        List<NameIntelligentObjectDefinition> nameIntelligentObjectDefinitions = this.allNameDefinitions();
        List<IntelligentObjectDefinition> standardLibraryFacility = this.getStandardLibraryFacility();

        return CollectionUtil.concatenate(nameIntelligentObjectDefinitions, standardLibraryFacility.stream()
                .map(t -> new NameIntelligentObjectDefinition(t, t.Name())).collect(Collectors.toList()));
    }

    private List<IntelligentObjectDefinition> getStandardLibraryFacility() {
        return List.of(IntelligentObjectDefinition.Instance,
                AgentDefinition.AgentFacility,
                EntityDefinition.EntityFacility,
                TransporterDefinition.transporterFacility,
                LinkDefinition.LinkFacility,
                FixedDefinition.FixedFacility,
                NodeDefinition.NodeFacility);
    }

    public boolean containInActiveModel(IntelligentObjectDefinition intelligentObjectDefinition) {
        return this.getActiveModelNameDefinitions().stream()
                .anyMatch((NameIntelligentObjectDefinition d) -> d.Definition() == intelligentObjectDefinition);
    }

    /**
     * @return
     */
    public List<NameIntelligentObjectDefinition> getActiveModelNameDefinitions() {
        if (this.intelligentObjectDefinition.activeModel == null ||
                this.intelligentObjectDefinition.activeModel.getDefinition() != this.intelligentObjectDefinition ||
                this.intelligentObjectDefinition.activeModel.parentProjectDefinition == null) {
            return new ArrayList<>();
        }

        var activeModels = this.intelligentObjectDefinition.activeModel.parentProjectDefinition.activeModels;
        var definitionOfActiveModel = activeModels.stream().map(ActiveModel::getDefinition).toList();

        if (!definitionOfActiveModel.contains(this.intelligentObjectDefinition)) {
            definitionOfActiveModel.add(this.intelligentObjectDefinition);
        }

        return definitionOfActiveModel.stream().map(t -> new NameIntelligentObjectDefinition(t, t.Name())).toList();
    }

    public IntelligentObjectDefinition updateSameIntelligentObjectDefinition(IntelligentObjectDefinition intelligentObjectDefinition) {
        if (intelligentObjectDefinition == null) {
            return null;
        }

        var nameIntelligentObjectDefinition = this.reserveNameDefinitions.stream()
                .filter(definition -> definition.Definition().sameGuid(intelligentObjectDefinition))
                .findAny()
                .orElse(null);

        if (nameIntelligentObjectDefinition == null) {
            var standard = this.getStandardLibraryFacility().stream()
                    .filter((IntelligentObjectDefinition definition) -> definition.sameGuid(intelligentObjectDefinition))
                    .findAny()
                    .orElse(null);

            if (standard != null) {
                var base = standard.createDefaultParentDefinition();
                this.registerNameDefinition(base, null, false);
                return base;
            } else {
                IntelligentObjectDefinition definition = null;
                var sameNameDefinition =
                        this.reserveNameDefinitions.stream()
                                .filter(t -> Objects.equals(t.Name(), intelligentObjectDefinition.Name()))
                                .findAny()
                                .orElse(null);
                if (sameNameDefinition != null) {
                    definition = sameNameDefinition.Definition();
                }
                var remove = this.removeNameDefinitionAction(definition);
                this.registerNameDefinition(intelligentObjectDefinition, remove, false);
                return intelligentObjectDefinition;
            }
        } else {
            return nameIntelligentObjectDefinition.Definition();
        }
    }


    private Runnable removeNameDefinitionAction(IntelligentObjectDefinition intelligentObjectDefinition) {
        Runnable result = null;
        if (intelligentObjectDefinition != null && !intelligentObjectDefinition.haveChildrenNode(this.intelligentObjectDefinition)) {
            result = () -> {
                this.bool_0 = true;
                this.removeNameDefinition(intelligentObjectDefinition);
                this.bool_0 = false;
            };
        }
        return result;
    }

    public void transportDefinitionBy(NodeDefinition nodeDefinition) {
        transportToNewDefinitions(nodeDefinition, null);
    }

    /**
     * reserse to newDefinitions
     * transportDefinition
     *
     * @param nodeDefinition
     * @param name
     */
    public void transportToNewDefinitions(NodeDefinition nodeDefinition, String name) {
        IntelligentObjectDefinition objectDefinition = null;
        var nameIntelligentObjectDefinition =
                this.newNameDefinitions.stream()
                        .filter(definition -> definition.Definition().sameGuidAndVersion(nodeDefinition))
                        .findAny()
                        .orElse(null);

        if (nameIntelligentObjectDefinition != null) {
            objectDefinition = nameIntelligentObjectDefinition.Definition();
        }

        if (objectDefinition == null) {
            nameIntelligentObjectDefinition = new NameIntelligentObjectDefinition(nodeDefinition,
                    nodeDefinition.Name());
            this.newNameDefinitions.add(nameIntelligentObjectDefinition);
        }

        if (name != null) {
            nameIntelligentObjectDefinition.Name(name);
        }

        nameIntelligentObjectDefinition = this.reserveNameDefinitions.stream()
                .filter(definition -> definition.Definition().sameGuidAndVersion(nodeDefinition))
                .findAny()
                .orElse(null);

        if (nameIntelligentObjectDefinition != null) {
            this.reserveNameDefinitions.remove(nameIntelligentObjectDefinition);
        }
    }


    private void registerNameDefinition(IntelligentObjectDefinition intelligentObjectDefinition, Runnable action) {
        registerNameDefinition(intelligentObjectDefinition, action, true);
    }

    private void registerNameDefinition(IntelligentObjectDefinition intelligentObjectDefinition, Runnable action,
                                        boolean bool_0) {
        if (this.reserveNameDefinitions.stream().anyMatch(definition -> definition.Definition() == intelligentObjectDefinition)) {
            return;
        }

        this.reserveNameDefinitions.add(new NameIntelligentObjectDefinition(intelligentObjectDefinition,
                intelligentObjectDefinition.Name()));

        intelligentObjectDefinition.addDefinitionNameChangeEvent(this::DefinitionNameChangeHandler);
        intelligentObjectDefinition.addDefinitionRemovedEvent(this::DefinitionRemovedEventHandler);
        if (action != null) {
            action.run();
        }

        if (this.intelligentObjectDefinition != null && bool_0) {
            this.intelligentObjectDefinition.flashState(intelligentObjectDefinition);
        }
    }

    private void removeNameDefinition(IntelligentObjectDefinition intelligentObjectDefinition) {
        intelligentObjectDefinition.removeDefinitionNameChangeEvent(this::DefinitionNameChangeHandler);
        intelligentObjectDefinition.removeDefinitionRemovedEvent(this::DefinitionRemovedEventHandler);

        this.reserveNameDefinitions.removeIf(definition -> definition.Definition() == intelligentObjectDefinition);
        if (this.intelligentObjectDefinition != null) {
            this.intelligentObjectDefinition.removeNameDefinition(intelligentObjectDefinition);
        }
    }

    private void DefinitionNameChangeHandler(Object source,
                                             IntelligentObjectDefinition.DefinitionNameChangeEventArgs e) {
        this.reserveNameDefinitions.stream()
                .filter(definition -> definition.Definition() == e.IntelligentObjectFacility)
                .findFirst()
                .ifPresent(t -> t.Name(e.IntelligentObjectFacility.Name()));

        if (this.intelligentObjectDefinition != null) {
            this.intelligentObjectDefinition.changeNameSome(e.IntelligentObjectFacility, e.name);
        }
    }

    private void DefinitionRemovedEventHandler(Object source, EventArgs e) {
        IntelligentObjectDefinition objectDefinition = null;
        if (objectDefinition != null) {
            this.removeNameDefinition(objectDefinition);
        }
    }


    public static IntelligentObjectDefinition get(String name) {
        return InternalReference.NameDefinitionMap.get(name);
    }

    static void put(String name, IntelligentObjectDefinition intelligentObjectDefinition) {
        InternalReference.NameDefinitionMap.put(name, intelligentObjectDefinition);
    }


    @Override
    public String Name(IntelligentObjectDefinition intelligentObjectDefinition) {
        if (intelligentObjectDefinition.notHaveParent()) {
            return intelligentObjectDefinition.ExpressionIdentifier();
        }

        var nameIntelligentObjectDefinition = this.reserveNameDefinitions.stream()
                .filter(definition -> definition.Definition().getGuid() == intelligentObjectDefinition.getGuid())
                .findAny()
                .orElse(null);

        if (nameIntelligentObjectDefinition != null) {
            return nameIntelligentObjectDefinition.Name();
        }

        var objectDefinition = this.newNameDefinitions.stream()
                .filter(definition -> definition.Definition().sameGuidAndVersion(intelligentObjectDefinition))
                .findAny()
                .orElse(null);

        if (objectDefinition != null) {
            return objectDefinition.Name();
        }

        return intelligentObjectDefinition.Name();
    }

    public IntelligentObjectDefinition getIntelligentObjectDefinition(String name) {
        IntelligentObjectDefinition iod = InternalReference.get(name);
        if (iod != null) {
            return iod;
        }

        if (this.intelligentObjectDefinition != null && Objects.equals(this.intelligentObjectDefinition.Name(), name)) {
            return this.intelligentObjectDefinition;
        }

        IntelligentObjectDefinition activeModelDefinition = null;
        var nameIntelligentObjectDefinition = this.getActiveModelNameDefinitions().stream()
                .filter(d -> d.Name().equals(name))
                .findAny()
                .orElse(null);

        if (nameIntelligentObjectDefinition != null) {
            activeModelDefinition = nameIntelligentObjectDefinition.Definition();
        }

        IntelligentObjectDefinition result = null;
        var definition = this.reserveNameDefinitions.stream()
                .filter(d1 -> d1.Name().equals(name))
                .findAny()
                .orElse(this.reserveNameDefinitions.stream()
                        .filter(d1 -> d1.Name().equals(name))
                        .findAny()
                        .orElse(null));
        if (definition != null) {
            result = definition.Definition();
        }

        if (this.bool_0) {
            return result;
        }

        if (activeModelDefinition != null && (result == null ||
                (result != null && !result.haveChildrenNode(this.intelligentObjectDefinition)))) {
            return this.updateSameIntelligentObjectDefinition(activeModelDefinition);
        }
        return result;
    }

    public boolean contains(IntelligentObjectDefinition objectDefinition) {
        return this.reserveNameDefinitions.stream().anyMatch(definition -> definition.Definition().sameGuid(objectDefinition));
    }

    void removeSameGuidIntelligentObjectDefinition(IntelligentObjectDefinition intelligentObjectDefinition) {
        boolean f = this.reserveNameDefinitions.stream()
                .anyMatch(definition -> definition.Definition().sameGuid(intelligentObjectDefinition));

        if (!f) {
            this.reserveNameDefinitions.removeIf(definition ->
                    definition.Definition().sameGuid(intelligentObjectDefinition) ||
                            !definition.Definition().haveChildrenNode(this.intelligentObjectDefinition));

            if (this.intelligentObjectDefinition != null) {
                this.intelligentObjectDefinition.removeNameDefinition(intelligentObjectDefinition);
            }
        }
    }

    private IntelligentObjectDefinition getSameGuidIntelligentObjectDefinition(IntelligentObjectDefinition intelligentObjectDefinition) {
        var nameIntelligentObjectDefinition =
                this.reserveNameDefinitions.stream()
                        .filter(definition ->
                                definition.Definition().getGuid() == intelligentObjectDefinition.getGuid())
                        .findFirst()
                        .orElse(null);

        if (nameIntelligentObjectDefinition != null) {
            return nameIntelligentObjectDefinition.Definition();
        }
        return null;
    }

    public boolean isVersionBigger(IntelligentObjectDefinition intelligentObjectDefinition) {
        IntelligentObjectDefinition objectDefinition =
                this.getSameGuidIntelligentObjectDefinition(intelligentObjectDefinition);
        return objectDefinition != null && objectDefinition.isVersionBigger(intelligentObjectDefinition);
    }

    public IntelligentObjectDefinition getVersionBiggerThan(IntelligentObjectDefinition intelligentObjectDefinition) {
        IntelligentObjectDefinition objectDefinition =
                this.getSameGuidIntelligentObjectDefinition(intelligentObjectDefinition);
        if (objectDefinition != null && objectDefinition.isVersionBigger(intelligentObjectDefinition)) {
            return objectDefinition;
        }
        return null;
    }

    public void updateDefinition(IntelligentObjectDefinition intelligentObjectDefinition) {
        Predicate<AbsPropertyObject> func = t -> {
            Node node = t instanceof Node ? (Node) t : null;
            if (node == null && t.Parent() == this.intelligentObjectDefinition) {
                return true;
            }

            if (node != null) {
                if (node.NodeClassProperty == null && node.Parent() == this.intelligentObjectDefinition) {
                    return true;
                }
                if (node.NodeClassProperty != null && node.Parent() != this.intelligentObjectDefinition) {
                    return node.NodeClassProperty.Parent() == this.intelligentObjectDefinition;
                }
            }
            return false;
        };

        if (!this.isVersionBigger(intelligentObjectDefinition)) {
            throw new IllegalArgumentException("The definition passed in cannot be updated to");
        }

        IntelligentObjectDefinition objectDefinition =
                this.getSameGuidIntelligentObjectDefinition(intelligentObjectDefinition);
        if (objectDefinition != null && objectDefinition != intelligentObjectDefinition) {
            List<AbsPropertyObject> absPropertyObjects =
                    new ArrayList<>(objectDefinition.getAssociatedInstances());

            this.reserveNameDefinitions.stream()
                    .filter(definition -> definition.Definition().getGuid() == intelligentObjectDefinition.getGuid())
                    .findFirst()
                    .ifPresent(nameIntelligentObjectDefinition -> nameIntelligentObjectDefinition.Definition(intelligentObjectDefinition));

            absPropertyObjects.stream().filter(func).forEach(absPropertyObject -> {
                IntelligentObject intelligentObject = (IntelligentObject) absPropertyObject;
                if (intelligentObject != objectDefinition.instance) {
                    intelligentObject.updateDefinition(intelligentObjectDefinition);
                }
            });
            this.intelligentObjectDefinition.method_83(objectDefinition, intelligentObjectDefinition, true);
        }
    }

    void transportDefinitionBy(IntelligentObjectDefinition intelligentObjectDefinition,
                               IntelligentObjectDefinition definition) {
        if (intelligentObjectDefinition instanceof NodeDefinition) {
            this.newNameDefinitions.stream()
                    .filter(objectDefinition -> objectDefinition.Definition() == intelligentObjectDefinition)
                    .findFirst()
                    .ifPresent(t -> {
                                if (t.Definition().propertyVersionDifferent(this.intelligentObjectDefinition)) {
                                    this.transportToNewDefinitions((NodeDefinition) definition, t.Name());
                                } else {
                                    t.Definition(definition);
                                }
                                this.intelligentObjectDefinition.method_83(intelligentObjectDefinition, definition,
                                        false);
                            }
                    );
        }
    }

    public String getNameByGuid(Guid guid) {
        return this.reserveNameDefinitions.stream()
                .filter(objectDefinition -> objectDefinition.Definition().getGuid() == guid)
                .findFirst()
                .map(NameIntelligentObjectDefinition::Name)
                .orElse(null);
    }

    public void changeGuidVersionIndexName(Guid guid, int versionIndex, String newName) {
        if (Strings.isNullOrEmpty(newName)) {
            return;
        }

        if (this.getActiveModelNameDefinitions().stream().anyMatch(definition -> definition.Definition().getGuid() == guid)) {
            return;
        }

        var source = CollectionUtil.concatenate(this.reserveNameDefinitions, this.newNameDefinitions);
        source.stream()
                .filter(t -> t.Definition().getGuid() == guid && t.Definition().getRevision() == versionIndex)
                .findFirst()
                .ifPresent(first -> {

                    var name = first.Name();
                    if (this.intelligentObjectDefinition != null) {
                        this.intelligentObjectDefinition.changeGuidVersionIndex(first.Definition(), name);
                    }

                    source.stream()
                            .filter(second -> second != first && second.Name().equals(newName))
                            .forEach(v -> {
                                        if (this.intelligentObjectDefinition != null) {
                                            this.intelligentObjectDefinition.changeNameSome(v.Definition());
                                        }
                                    }
                            );
                });
    }


    public int getVersionIndexByGuid(Guid guid) {
        InternalReference.NameIntelligentObjectDefinition nameDefinition = this.reserveNameDefinitions.stream()
                .filter(definition -> definition.Definition().getGuid() == guid)
                .findFirst().orElse(null);

        if (nameDefinition != null) {
            return nameDefinition.Definition().getRevision();
        }
        return 0;
    }

    public void changeVersionIndex(Guid guid, int versionIndex) {
        this.getActiveModelNameDefinitions().stream()
                .filter(definition -> definition.Definition().getGuid() == guid)
                .findFirst()
                .ifPresent(t -> t.Definition().setRevision(versionIndex));
    }

    public void writeXml(XmlWriter xmlWriter, CommonItems commonItems) {

    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "InternalReferences", null,
                (XmlReader body) -> SomeXmlOperator.xmlReaderElementOperator(body, "InternalReference",
                        (XmlReader attr) -> {
                            class Tmp {
                                String id;
                                String name;
                                String type;
                            }
                            var tmp = new Tmp();
                            SomeXmlOperator.readXmlAttributeString(xmlReader, "Id", t -> tmp.id = t);
                            SomeXmlOperator.readXmlAttributeString(xmlReader, "Name", t -> tmp.name = t);
                            SomeXmlOperator.readXmlAttributeString(xmlReader, "Type", t -> tmp.type = t);
                            if (!Strings.isNullOrEmpty(tmp.id) && !Strings.isNullOrEmpty(tmp.name)) {
                                IntelligentObjectDefinition intelligentObjectDefinition =
                                        intelligentObjectXml.readIntelligentObjectDefinitionByName(tmp.id, null,
                                                this.intelligentObjectDefinition.activeModel != null ?
                                                        this.intelligentObjectDefinition.activeModel.projectDefinition : null,
                                                false, false);
                                if (intelligentObjectDefinition != null) {
                                    if (Objects.equals(tmp.name, "AssociatedReference")) {
                                        this.newNameDefinitions
                                                .add(new InternalReference.NameIntelligentObjectDefinition(intelligentObjectDefinition,
                                                        tmp.name));
                                        return;
                                    }

                                    this.reserveNameDefinitions.add(new InternalReference.NameIntelligentObjectDefinition(intelligentObjectDefinition, tmp.name));
                                }
                            }
                        }, null));
    }

    public void transportDefinitions(NodeDefinition nodeDefinition, String name) {
        IntelligentObjectDefinition objectDefinition = null;
        NameIntelligentObjectDefinition newFind =
                this.newNameDefinitions.stream()
                        .filter((NameIntelligentObjectDefinition definition) -> definition.Definition().sameGuidAndVersion(nodeDefinition))
                        .findFirst()
                        .orElse(null);
        if (newFind != null) {
            objectDefinition = newFind.Definition();
        }
        if (objectDefinition == null) {
            newFind = new NameIntelligentObjectDefinition(nodeDefinition, nodeDefinition.Name());
            this.newNameDefinitions.add(newFind);
        }
        if (name != null) {
            newFind.Name(name);
        }
        newFind =
                this.reserveNameDefinitions.stream().filter((NameIntelligentObjectDefinition definition) ->
                                definition.Definition().sameGuidAndVersion(nodeDefinition))
                        .findFirst()
                        .orElse(null);
        if (newFind != null) {
            this.reserveNameDefinitions.remove(newFind);
        }
    }

    public static class NameIntelligentObjectDefinition {
        private IntelligentObjectDefinition definition;
        private String name;

        public NameIntelligentObjectDefinition(IntelligentObjectDefinition intelligentObjectDefinition, String name) {
            this.definition = intelligentObjectDefinition;
            this.name = name;
        }

        public String Name() {
            return this.name;
        }

        public void Name(String value) {
            this.name = value;
        }

        public IntelligentObjectDefinition Definition() {
            return this.definition;
        }

        public void Definition(IntelligentObjectDefinition value) {
            this.definition = value;
        }
    }
}
