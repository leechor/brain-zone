package com.zdpx.cctpp.concrete;

import com.google.common.base.Strings;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.fake.XmlWriter;
import com.zdpx.cctpp.concrete.node.Node;
import com.zdpx.cctpp.event.EventArgs;
import com.zdpx.cctpp.utils.CollectionUtil;
import com.zdpx.cctpp.utils.simu.IIntelligentObjectDefinitionOperator;

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
    private List<InternalReference.NameIntelligentObjectDefinition> newNameDefinitions = new ArrayList<>();
    private boolean bool_0;

    public InternalReference(IntelligentObjectDefinition objectFacility) {
        this.intelligentObjectDefinition = objectFacility;
    }


    public List<InternalReference.NameIntelligentObjectDefinition> allNameDefinitions() {
        return CollectionUtil.concatenate(this.reserveNameDefinitions, this.newNameDefinitions);
    }

    public Iterable<InternalReference.NameIntelligentObjectDefinition> allNameDefinitionsAndStandardLibrary() {
        List<NameIntelligentObjectDefinition> first = this.allNameDefinitions();
        List<IntelligentObjectDefinition> source = this.getStandardLibraryFacility();

        return CollectionUtil.concatenate(first, source.stream()
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

    public List<NameIntelligentObjectDefinition> getActiveModelNameDefinitions() {
        if (this.intelligentObjectDefinition.activeModel == null ||
                this.intelligentObjectDefinition.activeModel.getIntelligentObjectDefinition() != this.intelligentObjectDefinition ||
                this.intelligentObjectDefinition.activeModel.parentProjectDefinition == null) {
            return new ArrayList<>();
        }

        List<ActiveModel> activeModels =
                this.intelligentObjectDefinition.activeModel.parentProjectDefinition.ActiveModels;
        var definitionOfActiveModel
                = activeModels.stream().map(ActiveModel::getIntelligentObjectDefinition).collect(Collectors.toList());

        if (!definitionOfActiveModel.contains(this.intelligentObjectDefinition)) {
            definitionOfActiveModel.add(this.intelligentObjectDefinition);
        }

        return definitionOfActiveModel.stream()
                .map(t -> new NameIntelligentObjectDefinition(t, t.Name())).collect(Collectors.toList());
    }

    public IntelligentObjectDefinition updateSameIntelligentObjectDefinition(IntelligentObjectDefinition intelligentObjectDefinition) {
        if (intelligentObjectDefinition == null) {
            return null;
        }

        IntelligentObjectDefinition objectDefinition;
        var nameIntelligentObjectDefinition = this.reserveNameDefinitions.stream()
                .filter(definition -> definition.Definition().sameGuid(intelligentObjectDefinition))
                .findAny()
                .orElse(null);

        if (nameIntelligentObjectDefinition == null) {
            IntelligentObjectDefinition standard =
                    this.getStandardLibraryFacility().stream()
                            .filter((IntelligentObjectDefinition definition) -> definition.sameGuid(intelligentObjectDefinition))
                            .findAny()
                            .orElse(null);

            if (standard != null) {
                IntelligentObjectDefinition base = standard.CreateNewBaseClassDefinition();
                this.registerNameDefinition(base, null, false);
                objectDefinition = base;
            } else {
                IntelligentObjectDefinition definition = null;
                NameIntelligentObjectDefinition nDefinition =
                        this.reserveNameDefinitions.stream()
                                .filter(t -> Objects.equals(t.Name(), intelligentObjectDefinition.Name()))
                                .findAny()
                                .orElse(null);
                if (nDefinition != null) {
                    definition = nDefinition.Definition();
                }
                Runnable remove = this.removeNameDefinitionAction(definition);
                this.registerNameDefinition(intelligentObjectDefinition, remove, false);
                objectDefinition = intelligentObjectDefinition;
            }

        } else {
            objectDefinition = nameIntelligentObjectDefinition.Definition();
        }

        return objectDefinition;
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
        transportDefinition(nodeDefinition, null);
    }

    public void transportDefinition(NodeDefinition nodeDefinition, String name) {
        IntelligentObjectDefinition objectDefinition = null;
        var newFind =
                this.newNameDefinitions.stream()
                        .filter(definition -> definition.Definition().sameGuidAndVersion(nodeDefinition))
                        .findAny()
                        .orElse(null);

        if (newFind != null) {
            objectDefinition = newFind.Definition();
        }

        if (objectDefinition == null) {
            newFind = new InternalReference.NameIntelligentObjectDefinition(nodeDefinition,
                    nodeDefinition.Name());
            this.newNameDefinitions.add(newFind);
        }

        if (name != null) {
            newFind.Name(name);
        }

        newFind = this.reserveNameDefinitions.stream()
                .filter(definition -> definition.Definition().sameGuidAndVersion(nodeDefinition))
                .findAny()
                .orElse(null);

        if (newFind != null) {
            this.reserveNameDefinitions.remove(newFind);
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
                if (intelligentObject != objectDefinition.IntelligentObject) {
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
                                    this.transportDefinition((NodeDefinition) definition, t.Name());
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
        return false;
    }

    public void transportDefinitions(NodeDefinition nodeDefinition, String name) {
        IntelligentObjectDefinition objectDefinition = null;
        InternalReference.NameIntelligentObjectDefinition newFind =
                this.newNameDefinitions.stream().filter((InternalReference.NameIntelligentObjectDefinition definition) ->
                        definition.Definition().sameGuidAndVersion(nodeDefinition)).findFirst().orElse(null);
        if (newFind != null) {
            objectDefinition = newFind.Definition();
        }
        if (objectDefinition == null) {
            newFind = new InternalReference.NameIntelligentObjectDefinition(nodeDefinition, nodeDefinition.Name());
            this.newNameDefinitions.add(newFind);
        }
        if (name != null) {
            newFind.Name(name);
        }
        newFind =
                this.reserveNameDefinitions.stream().filter((InternalReference.NameIntelligentObjectDefinition definition) ->
                        definition.Definition().sameGuidAndVersion(nodeDefinition)).findFirst().orElse(null);
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
