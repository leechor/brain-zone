package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.event.EventArgs;
import org.licho.brain.event.EventHandler;
import org.licho.brain.resource.Image;
import org.licho.brain.api.IModel;
import org.licho.brain.utils.simu.system.IDisposable;
import javassist.compiler.ast.Symbol;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class SimioProject extends SimioProjectDefinition implements IDisposable {
    private Guid guid;
    private SymbolDecorator symbolDecorator;
    private PathDecorator pathDecorator;
    private MaterialsWrapper materials;
    private MaterialsWrapper skyBoxMaterials;
    private boolean bool_1;
    private EventHandler<EventArgs60> eventHandler_1;
    public static boolean bool_2;
    private SimioProject.LibrariesClass libraries;
    private List<Symbol> symbols;
    private List<AbsBaseMaterial> absBaseMaterials;
    private List<Decorator> decorators;

    public SimioProject() {
        this.guid = Guid.Empty;
        this.symbolDecorator = new SymbolDecorator();
        this.pathDecorator = new PathDecorator();
        this.materials = new MaterialsWrapper("Materials");
        this.skyBoxMaterials = new MaterialsWrapper("SkyBoxMaterials");
        this.bool_1 = true;
        this.symbols = new ArrayList<>();
        this.absBaseMaterials = new ArrayList<>();
        this.decorators = new ArrayList<>();
        this.libraries = new SimioProject.LibrariesClass(this);
        this.guid = Guid.NewGuid();
        SymbolDecorator symbolDecorator = this.symbolDecorator;
    }

    @Override
    public IntelligentObjectXml CreateReadingContext() {
        return new Facade();
    }

    @Override
    public void Dispose() {

    }

    @Override
    public Iterator<IModel> iterator() {
        return null;
    }

    public Guid ID() {
        return this.guid;
    }

    public static class EventArgs60 extends EventArgs {
    }

    @Override
    protected void ReadAttributesFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        super.ReadAttributesFromXml(xmlReader, intelligentObjectXml);
        SomeXmlOperator.readAttributesFromXml(xmlReader, "Id", t -> this.guid = t);
    }


    @Override
    protected boolean ReadBodyFromXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        if (super.ReadBodyFromXml(xmlReader, intelligentObjectXml)) {
            return true;
        }
        Facade facade = (Facade) intelligentObjectXml;
        return this.symbolDecorator.readXml(xmlReader, facade) ||
                this.materials.readXml(xmlReader, facade) ||
                this.skyBoxMaterials.readXml(xmlReader, facade) ||
                this.pathDecorator.readXml(xmlReader, facade) ||
                this.libraries.readXml(xmlReader, facade);
    }

    private static class LibrariesClass implements IDisposable, ILibraries {
        private SimioProject simioProject;
        private List<ILibrary> libraries = new ArrayList<>();
        private List<InnerLibraryRef> libRefs = new ArrayList<>();


        public LibrariesClass(SimioProject simioProject) {
            this.simioProject = simioProject;
            this.libraries.add(new LibrariesClass.InnerLibrary(simioProject, this));
        }

        @Override
        public ILibrary get() {
            return null;
        }

        @Override
        public int NumberOfLibraries() {
            return 0;
        }

        @Override
        public void ForEachExternalLibrary(Action<ILibrary> action) {

        }

        @Override
        public void ForEachExternalLibraryRef(Action<ILibraryRef> action) {

        }

        @Override
        public IntelligentObjectDefinition ObjectDefinitionFor(String name) {
            return null;
        }

        @Override
        public List<?> ListOfAll() {
            return null;
        }

        @Override
        public void Dispose() {

        }

        boolean readXml(XmlReader xmlReader, Facade facade) {
            return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Libraries", null,
                    (XmlReader body) -> LibrariesClass.LibraryRef.readXml(xmlReader, facade, this.libRefs::add));
        }

        private static class InnerLibraryRef implements ILibraryRef {
            private final Guid id;
            private final String name;
            private final String pathHint;

            public InnerLibraryRef(Guid guid, String name, String pathHint) {
                this.id = guid;
                this.name = name;
                this.pathHint = pathHint;
            }

            @Override
            public Guid Id() {
                return this.id;
            }

            @Override
            public String Name() {
                return this.name;
            }

            @Override
            public String PathHint() {
                return this.pathHint;
            }
        }

        private class InnerLibrary extends LibrariesClass.AbsLibrary {
            public InnerLibrary(SimioProject simioProject, LibrariesClass librariesClass) {
                super(simioProject, librariesClass);
                // TODO: 2022/1/28
            }

            @Override
            public SimioProject Project() {
                return this.simioProject;
            }


            @Override
            public String Name() {
                return Resources.ProjectLibraryTitle();
            }

            @Override
            public Image Icon() {
                return null;
            }

            @Override
            public boolean IsInternal() {
                return true;
            }
        }

        private abstract class AbsLibrary implements ILibrary {
            private SimioProject.LibrariesClass librariesClass;

            protected SimioProject simioProject;

            protected SimioProject.LibrariesClass getLibrariesClass() {
                return this.librariesClass;
            }

            public AbsLibrary(SimioProject simioProject, SimioProject.LibrariesClass librariesClass) {
                this.simioProject = simioProject;
                this.librariesClass = librariesClass;
            }

            public abstract SimioProject Project();


            @Override
            public Image Icon() {
                return this.simioProject.Icon();
            }

            @Override
            public List<ILibraryInfo> Objects() {
                List<ActiveModel> activeModels = this.Project().ActiveModels;
                return activeModels.stream().map(AbsLibrary.ActiveModelLibraryInfo::new).collect(Collectors.toList());
            }

            public boolean IsInternal() {
                return false;
            }

            public class ActiveModelLibraryInfo implements ILibraryInfo {
                private ActiveModel activeModel;

                public ActiveModelLibraryInfo(ActiveModel activeModel) {
                    this.activeModel = activeModel;
                }

                @Override
                public String Name() {
                    return this.activeModel.Name();
                }

                @Override
                public String Description() {
                    return this.activeModel.getIntelligentObjectDefinition().Description();
                }

                @Override
                public Image Icon() {
                    return this.activeModel.Icon();
                }

                @Override
                public IntelligentObjectDefinition getIntelligentObjectDefinition() {
                    return this.activeModel.getIntelligentObjectDefinition();
                }

                @Override
                public boolean isChild() {
                    return true;
                }

                @Override
                public String getId() {
                    return this.activeModel.getIntelligentObjectDefinition().getGuid().toString();
                }
            }
        }

        private static class LibraryRef {
            public static boolean readXml(XmlReader xmlReader, Facade facade,
                                          Action<LibrariesClass.InnerLibraryRef> assignAction) {
                LibrariesClass.InnerLibraryRef[] libRef = new InnerLibraryRef[1];
                boolean result = SomeXmlOperator.xmlReaderElementOperator(xmlReader, "LibraryRef", (XmlReader attr) ->
                {
                    String name = attr.GetAttribute("Name");
                    String id = attr.GetAttribute("Id");
                    String pathHint = attr.GetAttribute("PathHint");
                    Guid guid = new Guid(id);
                    libRef[0] = new LibrariesClass.InnerLibraryRef(guid, name, pathHint);
                }, null);
                if (libRef[0] != null) {
                    assignAction.apply(libRef[0]);
                }
                return result;
            }
        }
    }



}
