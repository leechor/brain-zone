package org.licho.brain.concrete;

import cn.hutool.core.collection.CollectionUtil;
import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.ExperimentConstraintsDefinition;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.IEnumMask;
import org.licho.brain.utils.simu.IFiles;
import org.licho.brain.utils.simu.system.IDisposable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;
import java.util.stream.IntStream;


/**
 *
 */
public class IntelligentObjectXml {
    private static final Logger logger = LoggerFactory.getLogger(IntelligentObjectXml.class);
    private final IntelligentObjectXml.ModeType mode;
    private int fileVersion;
    private boolean multiFile;
    private IFiles files;
    private final Map<String, GuidRevision> definitionRefNameGuidVersionsMap = new HashMap<>();
    private final List<IntelligentObjectDefinition> IntelligentObjectDefinitions = new ArrayList<>();
    private final Map<String, IntelligentObjectDefinition> allIntelligentObjectDefinitionNameMap = new HashMap<>();
    private final Map<GuidRevision, String> definitionXmlMap = new HashMap<>();
    private int modelCount;
    private List<RunnableInstanceOutXml> runnableInstanceXml = new ArrayList<>();
    private Map<ActiveModel, List<Warning>> activeModelWarning = new HashMap<>();
    private List<IntelligentObjectXml.InnerExperimentConstraint> experimentConstraints = new ArrayList<>();
    private Stack<IntelligentObjectDefinition> objectDefinitionStack = new Stack<>();
    private List<IntelligentObjectXml.OutXmlOperator> outXmlOperators = new ArrayList<>();
    private final List<String> warnings = new ArrayList<>();
    private final List<String> guidWarnings = new ArrayList<>();
    private boolean bool_1;
    private Stack<IntelligentObjectXml.OutXmlWrapper> outXmlWrappers = new Stack<>();
    private boolean bool_0;
    private Map<String, String> nameInstanceNameMap = new HashMap<>();


    public IntelligentObjectXml(int modeType) {
        this.mode = IntelligentObjectXml.ModeType.values()[modeType];
    }

    public IntelligentObjectXml(ModeType modeType) {
        this.mode = modeType;
    }

    public int FileVersion() {
        return fileVersion;
    }

    public void setFileVersion(int fileVersion) {
        this.fileVersion = fileVersion;
    }

    public void setMultiFile(boolean multi) {
        this.multiFile = multi;
    }

    public void setFiles(IFiles streamStore) {
        this.files = streamStore;
    }

    public void readXMlCommonItems(XmlReader xmlReader) {
        xmlReader.MoveToContent();
        String name = xmlReader.Name();
        SomeXmlOperator.xmlReaderElementOperator(xmlReader, name, null, (XmlReader body) ->
                SomeXmlOperator.xmlReaderElementOperator(body, "CommonItems", null,
                        this::processXmlCommonItems));
    }

    private boolean processXmlCommonItems(XmlReader xmlReader) {
        class Tmp {
            public String name;
            public String guid;
            public String version;

            class Inner {
                Tmp tmp;
                Guid guid;
                int version;

                public boolean sameGuidVersion(IntelligentObjectDefinition definition) {
                    return definition.getGuid() == this.guid && definition.getRevision() == this.version;
                }
            }
        }

        var tmp = new Tmp();

        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "DefinitionRefs", null,
                body -> SomeXmlOperator.xmlReaderElementAll(body,
                        "DefinitionRef",
                        t -> tmp.name = xmlReader.GetAttribute("Name"),
                        null,
                        drbody -> SomeXmlOperator.xmlReaderElementOperator(drbody,
                                "Id",
                                null,
                                idbody -> SomeXmlOperator.readXMLText(xmlReader, guid -> tmp.guid = guid))
                                || SomeXmlOperator.xmlReaderElementOperator(drbody,
                                "Revision",
                                null,
                                revbody -> SomeXmlOperator.readXMLText(xmlReader, s -> tmp.version = s)),
                        afterbody ->
                        {
                            if (!Strings.isNullOrEmpty(tmp.name) && !Strings.isNullOrEmpty(tmp.guid) && !Strings.isNullOrEmpty(tmp.version)) {
                                var cl = tmp.new Inner();
                                cl.tmp = tmp;
                                cl.guid = new Guid(tmp.guid);
                                cl.version = 0;
                                cl.version = Integer.parseInt(tmp.version);
                                this.definitionRefNameGuidVersionsMap.put(tmp.name,
                                        new GuidRevision(cl.guid, cl.version));
                                this.IntelligentObjectDefinitions.stream()
                                        .filter(cl::sameGuidVersion)
                                        .findFirst()
                                        .ifPresent(t -> this.allIntelligentObjectDefinitionNameMap.put(tmp.name, t));
                            }
                        }))
                || SomeXmlOperator.xmlReaderElementOperator(xmlReader,
                "Definitions",
                null,
                xr -> {
                    if (xr.Name().endsWith("Definition")) {
                        var id = xr.GetAttribute("Id");
                        var revision = xr.GetAttribute("Revision");
                        if (!Strings.isNullOrEmpty(id) && !Strings.isNullOrEmpty(revision)) {
                            int rev = Integer.parseInt(revision);
                            Guid guid = new Guid(id);
                            var outerXml = xr.ReadOuterXml();
                            this.definitionXmlMap.put(new GuidRevision(guid, rev), outerXml);
                        }
                        return true;
                    }
                    return false;
                }) || this.getFiles().readXmlFiles(xmlReader);
    }

    public void readXmlFileVersion(XmlReader xmlReader) {
        SomeXmlOperator.readXmlIntAttribute(xmlReader, "FileVersion", this::setFileVersion);
    }

    public static void readXmlIntAttribute(XmlReader xmlReader, String attributeName, Action<Integer> assignAction) {
        String attribute = xmlReader.GetAttribute(attributeName);
        if (!Strings.isNullOrEmpty(attribute)) {
            int obj = 0;
            try {
                obj = Integer.parseInt(attribute);
                assignAction.apply(obj);
            } catch (NumberFormatException e) {
                logger.error("int convert error");
            }

        }
    }

    public IFiles getFiles() {
        if (this.files == null) {
            this.files = new Files();
        }
        return this.files;
    }

    public IDisposable createReadModelOperator() {
        return new IntelligentObjectXml.ReadModelOperator(this);
    }

    public Guid getGuidByName(String definitionName) {
        GuidRevision guidRevision = this.definitionRefNameGuidVersionsMap.getOrDefault(definitionName,
                new GuidRevision());
        return guidRevision.guid;
    }

    public IntelligentObjectDefinition readIntelligentObjectDefinitionByName(String definitionName,
                                                                             ActiveModel activeModel,
                                                                             SimioProjectDefinition simioProjectDefinition,
                                                                             boolean alreadyHaveModel,
                                                                             boolean b1) {
        IntelligentObjectDefinition objectDefinition = this.allIntelligentObjectDefinitionNameMap.get(definitionName);
        if (alreadyHaveModel || objectDefinition == null) {
            var guidVersion = this.definitionRefNameGuidVersionsMap.get(definitionName);
            if (guidVersion != null) {
                objectDefinition = guidVersion.tryCreateBasicDefinition(activeModel);
                if (objectDefinition != null) {
                    this.allIntelligentObjectDefinitionNameMap.put(definitionName, objectDefinition);
                } else {
                    objectDefinition = null;
                    String outXml = this.definitionXmlMap.get(guidVersion);
                    if (outXml != null) {
                        try (StringReader stringReader = new StringReader(outXml)) {
                            try (XmlReader xmlReader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
                                xmlReader.MoveToContent();
                                objectDefinition = IntelligentObjectDefinition.readXmlDefinition(xmlReader,
                                        this, simioProjectDefinition, IntelligentObjectDefinition.Enum8.One);
                                if (objectDefinition != null && !alreadyHaveModel) {
                                    this.allIntelligentObjectDefinitionNameMap.put(definitionName, objectDefinition);
                                }
                            }
                        }

                        var o = objectDefinition;
                        if (!this.modelUpdating() && !this.haveParent(objectDefinition)) {
                            var oxo = new IntelligentObjectXml.OutXmlOperator();
                            oxo.intelligentObjectDefinition = objectDefinition;
                            oxo.outXml = outXml;
                            this.outXmlOperators.add(oxo);

                            try (var ad = this.createObjectDefinitionStackOperator(objectDefinition)) {
                                try (StringReader stringReader2 = new StringReader(outXml)) {
                                    try (XmlReader xmlReader2 = XmlReader.Create(stringReader2, XmlSettings.Pure)) {
                                        xmlReader2.MoveToContent();
                                        if (!this.modelUpdating()) {
                                            IntelligentObjectDefinition intelligentObjectDefinition =
                                                    objectDefinition.parent;
                                            if (intelligentObjectDefinition != null) {
                                                this.readXmlObjectDefinitionParent(intelligentObjectDefinition);
                                            }
                                            objectDefinition.readXmlDefinition(xmlReader2, this,
                                                    IntelligentObjectDefinition.Enum8.Zero);
                                        }
                                    }
                                }
                            }

                            IntelligentObjectDefinition tmp = objectDefinition;
                            int num = IntStream.range(0, this.outXmlOperators.size())
                                    .filter(i -> this.outXmlOperators.get(i).equals(tmp))
                                    .findFirst()
                                    .orElse(-1);
                            this.outXmlOperators.remove(num);
                        } else if (this.outXmlOperators.stream()
                                .noneMatch(t -> t.intelligentObjectDefinition.getGuid().equals(o.getGuid())
                                        && t.intelligentObjectDefinition.getRevision() == o.getRevision())) {
                            var tmp = new IntelligentObjectXml.OutXmlOperator();
                            tmp.intelligentObjectDefinition = objectDefinition;
                            tmp.outXml = outXml;
                            this.outXmlOperators.add(tmp);
                        }
                    }
                }
            }
        }
        if (!this.modelUpdating() && !this.haveParent(objectDefinition)) {
            if (b1) {
                var tmp = objectDefinition;
                if (this.outXmlOperators.stream().anyMatch(outXmlOperator -> outXmlOperator.intelligentObjectDefinition == tmp)) {
                    return objectDefinition;
                }
            }
            this.readXmlObjectDefinitionParent(objectDefinition);
        }
        return objectDefinition;
    }

    private void readXmlObjectDefinitionParent(IntelligentObjectDefinition intelligentObjectDefinition) {
        if (CollectionUtil.isNotEmpty(this.outXmlOperators)) {
            int num = -1;
            for (int i = 0; i < this.outXmlOperators.size(); i++) {
                if (this.outXmlOperators.get(i).intelligentObjectDefinition.equals(intelligentObjectDefinition)) {
                    num = i;
                }
            }

            if (num != -1 && !this.contains(intelligentObjectDefinition)) {
                try (var d1 = this.createObjectDefinitionStackOperator(intelligentObjectDefinition)) {
                    IntelligentObjectXml.OutXmlOperator outXmlOperator = this.outXmlOperators.get(num);
                    this.readXmlObjectDefinition(outXmlOperator);
                    this.outXmlOperators.removeIf(t -> t.intelligentObjectDefinition.equals(intelligentObjectDefinition));
                }
            }
        }
    }

    private void readXmlObjectDefinition(OutXmlOperator outXmlOperator) {
        IntelligentObjectDefinition intelligentObjectDefinition = outXmlOperator.intelligentObjectDefinition.parent;
        if (intelligentObjectDefinition != null) {
            this.readXmlObjectDefinitionParent(intelligentObjectDefinition);
        }
        outXmlOperator.readXmlDefinition(this);
    }

    private IDisposable createObjectDefinitionStackOperator(IntelligentObjectDefinition intelligentObjectDefinition) {
        return new IntelligentObjectXml.ObjectDefinitionStackOperator(this, intelligentObjectDefinition);

    }

    private boolean haveParent(IntelligentObjectDefinition intelligentObjectDefinition) {
        IntelligentObjectDefinition parent = (intelligentObjectDefinition != null) ?
                intelligentObjectDefinition.parent : null;
        return parent != null && this.contains(parent);
    }

    private boolean contains(IntelligentObjectDefinition intelligentObjectDefinition) {
        return this.objectDefinitionStack.contains(intelligentObjectDefinition);
    }

    private boolean modelUpdating() {
        return this.modelCount > 0;
    }

    public void RunnableInstance(String runnableInstanceXml, ActiveModel activeModel) {
        this.runnableInstanceXml.add(new RunnableInstanceOutXml(runnableInstanceXml, activeModel));
    }

    public void addWarning(ActiveModel activeModel, Warning warning) {
        List<Warning> list = this.activeModelWarning.computeIfAbsent(activeModel, k -> new ArrayList<>());
        list.add(warning);
    }

    public void Experiments(String readOuterXml, ActiveModel activeModel,
                            SimioProjectDefinition simioProjectDefinition,
                            SimioProjectDefinition.ExperimentConstraintsXmlReader experimentConstraintsXmlReader) {
    }

    public boolean readXmlCommonItemsNode(XmlReader xmlReader) {
        return SomeXmlOperator.xmlReaderElementAll(xmlReader, "CommonItems", null,
                t -> {
                    this.vmethod_0();
                    return null;
                }, body -> this.vmethod_2(xmlReader), t -> this.vmethod_1());
    }

    protected void vmethod_1() {
        for (IntelligentObjectDefinition intelligentObjectDefinition :
                this.getAllIntelligentObjectDefinitionNameMap()) {
            intelligentObjectDefinition.flashStateFalse();
        }
        this.readXmlExperimentConstraints();
        this.readRunnableInstanceXml();
        this.recordWarning();
    }

    private void recordWarning() {
        this.activeModelWarning.forEach((key, v) -> v.forEach(warning -> {
            if (warning.getInstanceNamePeek() != null) {
                key.getDefinition().recordWarning(warning);
            } else {
                key.recordWarning(warning, null);
            }
        }));
    }

    private void readRunnableInstanceXml() {
        this.runnableInstanceXml.forEach(runnableInstanceOutXml -> {
            IntelligentObject intelligentObject = runnableInstanceOutXml.ActiveModel.getDefinition().instance;
            intelligentObject.properties.values.forEach(p -> {
                p.SetStringValue(p.getDefaultName(intelligentObject.assignerDefinition.getPropertyDefinitions()), null,
                        IntelligentObjectProperty.ValueVersion.userVersion(this.FileVersion()));
            });
        });
    }

    private void readXmlExperimentConstraints() {
        this.experimentConstraints.forEach(
                innerExperimentConstraint ->
                {
                    try (StringReader stringReader = new StringReader(innerExperimentConstraint.xmlContext)) {
                        try (XmlReader xmlReader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
                            xmlReader.MoveToContent();
                            if (ExperimentConstraintsDefinition.readXml(xmlReader, this,
                                    innerExperimentConstraint.ActiveModel,
                                    innerExperimentConstraint.ExperimentConstraintsXmlReader) == null) {
                                xmlReader.Skip();
                            }
                        }
                    }
                }
        );
    }

    private Iterable<? extends IntelligentObjectDefinition> getAllIntelligentObjectDefinitionNameMap() {
        return this.allIntelligentObjectDefinitionNameMap.values();
    }

    protected boolean vmethod_2(XmlReader xmlReader) {
        return false;
    }

    protected void vmethod_0() {

    }

    public void addWarning(String warning) {
        this.warnings.add(warning);
    }

    public IntelligentObjectXml.ModeType Mode() {
        return this.mode;
    }

    public void addGuidWarnings(String warning) {
        this.guidWarnings.add(warning);
    }

    public boolean getMultiFile() {
        return this.multiFile;
    }

    public boolean method_17() {
        return this.bool_1;
    }

    public boolean haveOutXml() {
        return !this.outXmlWrappers.isEmpty();
    }

    public void method_13() {
        this.bool_0 = true;
    }

    public String getObjectName(String name) {
        String result = this.nameInstanceNameMap.get(name);
        if (result != null) {
            return result;
        }
        return name;
    }

    public void addNameInstanceNameMap(String name, String instanceName) {
        this.nameInstanceNameMap.put(name, instanceName);
    }

    public boolean xmlReaderOutXmlOperator(XmlReader xmlReader, IntelligentObjectXml.OutXmlReader outXmlReader) {
        if (!this.outXmlWrappers.isEmpty()) {
            IntelligentObjectXml.OutXmlWrapper outXmlWrapper = this.outXmlWrappers.peek();
            outXmlWrapper.addOutXml(xmlReader.ReadOuterXml(), outXmlReader);
            return true;
        }
        return outXmlReader.apply(xmlReader);
    }

    public boolean readAdditionalSymbol(XmlReader xmlReader, IntelligentObjectDefinition intelligentObjectDefinition) {
        return false;
    }

    public boolean vmethod_5(XmlReader xmlReader, IntelligentObjectDefinition iIntelligentObjects) {
        return false;
    }

    public void initContextBound(IntelligentObjectDefinition intelligentObjectDefinition) {
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObject intelligentObject) {
        return false;
    }

    public enum ModeType implements IEnumMask {
        Zero,
        One,
        Two;

        ModeType() {
            this.mask = (1 << ordinal());
        }

        private final int mask;

        @Override
        public int mask() {
            return this.mask;
        }
    }

    @FunctionalInterface
    public interface OutXmlReader {
        boolean apply(XmlReader xmlReader);
    }

    public class GuidRevision {
        private final int revision;
        private final Guid guid;

        public GuidRevision() {
            this.revision = 0;
            this.guid = new Guid();
        }

        public GuidRevision(Guid guid, int version) {
            this.guid = guid;
            this.revision = version;
        }

        public IntelligentObjectDefinition tryCreateBasicDefinition(ActiveModel activeModel) {
            if (this.guid == AgentDefinition.guid) {
                return AgentDefinition.create();
            }
            if (this.guid == EntityDefinition.guid) {
                return EntityDefinition.create();
            }
            if (this.guid == TransporterDefinition.guid) {
                return TransporterDefinition.create();
            }
            if (this.guid == NodeDefinition.guid) {
                return NodeDefinition.create();
            }
            if (this.guid == LinkDefinition.guid) {
                return LinkDefinition.create();
            }
            if (this.guid == FixedDefinition.guid) {
                return FixedDefinition.create();
            }
            return null;
        }

        @Override
        public String toString() {
            return this.revision + ":" + this.guid;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (o == null || getClass() != o.getClass()) {
                return false;
            }
            GuidRevision that = (GuidRevision) o;
            return revision == that.revision && Objects.equals(guid, that.guid);
        }

        @Override
        public int hashCode() {
            int num = 17;
            num = num * 23 + this.guid.hashCode();
            return num * 23 + Integer.hashCode(this.revision);
        }
    }

    private class RunnableInstanceOutXml {
        public String outXml;
        public ActiveModel ActiveModel;

        public RunnableInstanceOutXml(String runnableInstanceXml, ActiveModel activeModel) {
            this.outXml = runnableInstanceXml;
            this.ActiveModel = activeModel;
        }
    }

    class ReadModelOperator implements IDisposable {
        private IntelligentObjectXml intelligentObjectXml;

        public ReadModelOperator(IntelligentObjectXml intelligentObjectXml) {
            this.intelligentObjectXml = intelligentObjectXml;
            this.intelligentObjectXml.enterModel();
        }

        @Override
        public void Dispose() {
            this.intelligentObjectXml.exitModel();
        }
    }

    private void exitModel() {
        // TODO: 2021/12/15 
    }

    private void enterModel() {
        this.modelCount++;
    }

    public class InnerExperimentConstraint {
        public String xmlContext;
        public ActiveModel ActiveModel;
        public SimioProjectDefinition SimioProjectDefinition;
        public SimioProjectDefinition.ExperimentConstraintsXmlReader ExperimentConstraintsXmlReader;
    }

    public class OutXmlOperator {

        public void readXmlDefinition(IntelligentObjectXml intelligentObjectXml) {
            try (StringReader stringReader = new StringReader(this.outXml)) {
                try (XmlReader xmlReader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
                    xmlReader.MoveToContent();
                    this.intelligentObjectDefinition.readXmlDefinition(xmlReader, intelligentObjectXml,
                            IntelligentObjectDefinition.Enum8.Zero);
                }
            }
        }

        // Token: 0x040023D9 RID: 9177
        public IntelligentObjectDefinition intelligentObjectDefinition;

        // Token: 0x040023DA RID: 9178
        public String outXml;
    }

    public IDisposable createFacadeWrapper() {
        return null;
    }

    public class ObjectDefinitionStackOperator implements IDisposable {
        private IntelligentObjectXml intelligentObjectXml;

        public ObjectDefinitionStackOperator(IntelligentObjectXml intelligentObjectXml,
                                             IntelligentObjectDefinition intelligentObjectDefinition) {
            this.intelligentObjectXml = intelligentObjectXml;
            this.intelligentObjectXml.objectDefinitionStack.push(intelligentObjectDefinition);
        }

        public void Dispose() {
            this.intelligentObjectXml.objectDefinitionStack.pop();
        }

    }

    public class OutXmlWrapper implements IDisposable {

        private IntelligentObjectXml intelligentObjectXml;
        private Queue<OutXml> outXmls = new LinkedList<>();

        public OutXmlWrapper(IntelligentObjectXml intelligentObjectXml) {
            this.intelligentObjectXml = intelligentObjectXml;
        }

        public void addOutXml(String xml, IntelligentObjectXml.OutXmlReader outXmlReader) {
            IntelligentObjectXml.OutXmlWrapper.OutXml tmp = new OutXml();
            tmp.xml = xml;
            tmp.OutXmlReader = outXmlReader;
            this.outXmls.add(tmp);
        }

        @Override
        public void Dispose() {
            this.intelligentObjectXml.outXmlWrappers.pop();
            while (!this.outXmls.isEmpty()) {
                IntelligentObjectXml.OutXmlWrapper.OutXml outXml = this.outXmls.remove();
                try (StringReader stringReader = new StringReader(outXml.xml)) {
                    try (XmlReader xmlReader = XmlReader.Create(stringReader, XmlSettings.Pure)) {
                        xmlReader.MoveToContent();
                        outXml.OutXmlReader.apply(xmlReader);
                    }
                }
            }
        }

        public class OutXml {
            public String xml;
            public IntelligentObjectXml.OutXmlReader OutXmlReader;
        }
    }
}
