package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.IFunction.Action;
import org.licho.brain.concrete.cont.EngineResources;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.fake.XmlWriter;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.enu.ElementReferenceType;
import org.licho.brain.enu.PropertyGroupClass;
import org.licho.brain.brainApi.IPropertyDefinition;
import org.licho.brain.utils.simu.system.IDisposable;
import org.licho.brain.utils.simu.system.ListChangedEventArgs;
import org.licho.brain.utils.simu.system.ListChangedType;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

/**
 *
 */
public class PropertyDefinitions extends BindingList<StringPropertyDefinition> implements IIdentityName,
        IPropertyDefinitions {

    public PropertyGroupClass propertyGroupClass;
    private List<PropertyDefinitionFacade> propertyDefinitionFacadeList = new ArrayList<>();
    public GridObjectDefinition TargetObject;
    public org.licho.brain.concrete.RepeatingPropertyDefinition RepeatingPropertyDefinition;
    public String seperator = ", ";
    private ActionRun actionRun;
    public final List<OverrideObject> overrides = new ArrayList<>();
    private Map<String, List<StringPropertyDefinition>> categories = new HashMap<>();
    private final List<PropertyReplacement> propertyReplacements = new ArrayList<>();


    public PropertyDefinitions(GridObjectDefinition targetObject,
                               org.licho.brain.concrete.RepeatingPropertyDefinition repeatingPropertyDefinition) {
        this.TargetObject = targetObject;
        this.RepeatingPropertyDefinition = repeatingPropertyDefinition;
    }

    public static boolean isValidNodeName(String name) {
        return Objects.equals(name, "Categories") ||
                Objects.equals(name, "Overrides") ||
                Objects.equals(name, "PropertyReplacements");
    }

    public List<PropertyDefinitionFacade> getPropertyDefinitionList() {
        return this.propertyDefinitionFacadeList;
    }


    @Override
    public boolean IsValidIdentifier(String param0, StringBuffer error) {
        return false;
    }

    @Override
    public String GetUniqueName(String param0) {
        return null;
    }


    @Override
    public IPropertyDefinition AddStringProperty(String name, String defaultValue) {
        return null;
    }

    @Override
    public IPropertyDefinition AddRealProperty(String name, double defaultValue) {
        return null;
    }

    @Override
    public IPropertyDefinition AddExpressionProperty(String name, String defaultValue) {
        return null;
    }

    @Override
    public IPropertyDefinition AddStateProperty(String name) {
        return null;
    }

    @Override
    public IPropertyDefinition AddElementProperty(String name, UUID elementDefinitionID) {
        return null;
    }

    @Override
    public IPropertyDefinitions AddRepeatGroup(String name, String description) {
        return null;
    }

    public int getCount() {
        return 0;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IPropertyDefinition getByIndex(int index) {
        return null;
    }


    public void process(Action<StringPropertyDefinition> action) {
        for (StringPropertyDefinition stringPropertyDefinition : this.values) {
            action.apply(stringPropertyDefinition);
            if (stringPropertyDefinition instanceof RepeatingPropertyDefinition) {
                RepeatingPropertyDefinition repeatingPropertyDefinitionInfo =
                        (RepeatingPropertyDefinition) stringPropertyDefinition;
                repeatingPropertyDefinitionInfo.getPropertyDefinitions().process(action);
            }
        }
    }

    @Override
    protected void OnListChanged(ListChangedEventArgs listChangedEventArgs) {
        if (listChangedEventArgs.ListChangedType() != ListChangedType.ItemChanged) {
            int num = 0;
            for (StringPropertyDefinition stringPropertyDefinition : this.values) {
                if (stringPropertyDefinition.IsOwnedBy(this.TargetObject)) {
                    stringPropertyDefinition.overRidePropertyIndex = num;
                }

                num++;
            }
        }

        switch (listChangedEventArgs.ListChangedType()) {
            case Reset:
            case ItemAdded:
            case ItemDeleted:
            case ItemChanged:
                this.categories.clear();
                break;
        }

        super.OnListChanged(listChangedEventArgs);
    }

    private Map<String, List<StringPropertyDefinition>> getCategories() {
        if (this.categories.isEmpty()) {
            for (StringPropertyDefinition stringPropertyDefinition : this.values) {
                List<StringPropertyDefinition> list = null;
                if (!this.categories.containsKey(stringPropertyDefinition.Name())) {
                    list = new ArrayList<>();
                    this.categories.put(stringPropertyDefinition.Name(), list);
                }
                list.add(stringPropertyDefinition);
            }
        }
        return this.categories;
    }

    public StringPropertyDefinition findStringPropertyDefinitionInfoByName(String name) {
        List<StringPropertyDefinition> source = this.getCategories().get(name);
        if (name != null && source != null) {
            return source.get(0);
        }
        return null;
    }

    void processOverridesListener(Action<OverrideObject> action) {

    }

    void PropertyDefaultChanged(StringPropertyDefinition stringPropertyDefinition,
                                PropertyDefinitions propertyDefinitions, String s1, String s2) {

    }

    public org.licho.brain.concrete.RepeatingPropertyDefinition getRepeatingPropertyDefinition() {
        return RepeatingPropertyDefinition;
    }

    public void setRepeatingPropertyDefinition(org.licho.brain.concrete.RepeatingPropertyDefinition repeatingPropertyDefinition) {
        this.RepeatingPropertyDefinition = repeatingPropertyDefinition;
    }

    public PropertyGroupClass getPropertyGroupClass() {
        return propertyGroupClass;
    }

    public void setPropertyGroupClass(PropertyGroupClass propertyGroupClass) {
        this.propertyGroupClass = propertyGroupClass;
    }

    public GridObjectDefinition getTargetObject() {
        return TargetObject;
    }

    public boolean notHaveRepeatingProperty() {
        return this.RepeatingPropertyDefinition == null;
    }

    public void addPropertyDefinition(PropertyDefinitionFacade definition) {
        this.insertPropertyDefinition(definition, this.getPropertyDefinitionList().size());
    }

    private void insertPropertyDefinition(PropertyDefinitionFacade propertyDefinitionFacade, int count) {
        this.getPropertyDefinitionList().add(count, propertyDefinitionFacade);
        if (this.TargetObject instanceof IntelligentObjectDefinition) {
            ((IntelligentObjectDefinition) this.TargetObject).insertChildrenProperty(propertyDefinitionFacade, count);
        }
    }

    public void NumericPropertyDefaultUnitSubTypeChanged(NumericDataPropertyDefinition numericDataPropertyDefinition,
                                                         PropertyDefinitions owner, int type, int defaultUnitSubType) {
    }

    public String[] getCategoriesArray() {
        if (this.RepeatingPropertyDefinition != null) {
            return this.RepeatingPropertyDefinition.owner.getCategoriesArray();
        }

        Map<String, String> categories = new HashMap<>();
        categories.put(EngineResources.CategoryName_General, null);
        this.process((StringPropertyDefinition stringPropertyDefinition) ->
        {
            categories.put(stringPropertyDefinition.GetCategoryName(this), null);
            if (stringPropertyDefinition.CategoryName() != null) {
                categories.put(stringPropertyDefinition.CategoryName(), null);
            }
        });
        List<String> result = new ArrayList<>(categories.keySet());
        result.sort(String::compareTo);
        return result.toArray(new String[0]);
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml, IIdentityName identityName) {
        if (Objects.equals(xmlReader.Name(), "PropertyDefinitions") || Objects.equals(xmlReader.Name(), "Properties")) {
            return SomeXmlOperator.xmlReaderElementOperator(xmlReader, xmlReader.Name(), null, (XmlReader body) ->
                    SomeXmlOperator.xmlReaderElementAll(body, "Categories", null, (XmlReader beforeCatsBody) ->
                    {
                        if (this.propertyDefinitionFacadeList != null) {
                            this.propertyDefinitionFacadeList.clear();
                        }
                        return null;
                    }, (XmlReader catsBody) -> SomeXmlOperator.xmlReaderElementOperator(catsBody, "Category",
                            (XmlReader catAttr) -> {
                                var propertyDefinition = new PropertyDefinitionFacade();
                                String name = catAttr.GetAttribute("Name");
                                if (!Strings.isNullOrEmpty(name)) {
                                    propertyDefinition.Name(name);
                                    String description = catAttr.GetAttribute("Description");
                                    if (!Strings.isNullOrEmpty(description)) {
                                        propertyDefinition.Description(description);
                                    }

                                    String initiallyExpanded = "InitiallyExpanded";
                                    SomeXmlOperator.readXmlBooleanAttribute(catAttr, initiallyExpanded,
                                            propertyDefinition::InitiallyExpanded);
                                    int num = propertyDefinition.Name().lastIndexOf('/');
                                    if (num >= 0) {
                                        var tmp = propertyDefinition.Name().substring(0, num);
                                        if (!Strings.isNullOrEmpty(tmp)) {
                                            propertyDefinition.Parent(this.getPropertyDefinitionList().stream().filter(t -> t.Name().equals(tmp)).findFirst().orElse(null));
                                        }
                                    }

                                    if (this.propertyDefinitionFacadeList == null) {
                                        this.propertyDefinitionFacadeList = new ArrayList<>();
                                    }

                                    List<PropertyDefinitionFacade> propertyDefinitionFacades =
                                            this.getPropertyDefinitionList();

                                    if (propertyDefinitionFacades.stream().noneMatch(t -> t.Name().equals(propertyDefinition.Name()))) {
                                        this.getPropertyDefinitionList().add(propertyDefinition);
                                    }
                                }
                            }, null), (XmlReader x) -> this.afterBody(intelligentObjectXml)) ||
                            StringPropertyDefinition.readXml(body, intelligentObjectXml, this, identityName,
                                    StringPropertyDefinition.Enum17.One) != null ||
                            SomeXmlOperator.xmlReaderElementOperator(body, "Overrides", null,
                                    (XmlReader overridesBody) -> OverrideObject.readXml(overridesBody,
                                            intelligentObjectXml, this) != null) ||
                            SomeXmlOperator.xmlReaderElementOperator(body,
                                    "PropertyReplacements", null, (XmlReader replacementsBody) ->
                                    {
                                        PropertyDefinitions.PropertyReplacement cl =
                                                PropertyDefinitions.PropertyReplacement.readXmlPropertyReplacement(replacementsBody);
                                        if (cl != null) {
                                            this.propertyReplacements.add(cl);
                                            return true;
                                        }

                                        return false;
                                    }));
        }

        return false;
    }

    public static StringPropertyDefinition readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml,
                                                   PropertyDefinitions propertyDefinitions,
                                                   IIdentityName identityName, StringPropertyDefinition.Enum17 enum17) {
        StringPropertyDefinition stringPropertyDefinition;
        if (Objects.equals(xmlReader.Name(), "RepeatingProperty")) {
            stringPropertyDefinition = new RepeatingPropertyDefinition(identityName.GetUniqueName("Property"),
                    propertyDefinitions.TargetObject);
        } else {
            stringPropertyDefinition = StringPropertyDefinition.getStringPropertyDefinition(xmlReader.Name(),
                    identityName);
        }
        if (stringPropertyDefinition == null && !PropertyDefinitions.isValidNodeName(xmlReader.Name()) && enum17 == StringPropertyDefinition.Enum17.One) {
            intelligentObjectXml.addWarning(String.format(EngineResources.LoadWarning_CouldNotFindProperty,
                    xmlReader.Name()));
            stringPropertyDefinition = new MissingPropertyDefinition(identityName.GetUniqueName("Property"));
        }
        if (stringPropertyDefinition != null) {
            propertyDefinitions.add(stringPropertyDefinition);
            stringPropertyDefinition.ReadFromXml(xmlReader, intelligentObjectXml, propertyDefinitions, identityName);
            return stringPropertyDefinition;
        }
        return null;
    }


    private void afterBody(IntelligentObjectXml intelligentObjectXml) {
        if (intelligentObjectXml.FileVersion() <= 35) {
            List<PropertyDefinitionFacade> list = this.getPropertyDefinitionList();
            if (list.stream().noneMatch(t -> t.Name().equals(EngineResources.CategoryName_Financials))) {
                PropertyDefinitionFacade propertyDefinitionFacade = new PropertyDefinitionFacade();
                propertyDefinitionFacade.Name(EngineResources.CategoryName_Financials);
                propertyDefinitionFacade.Description(EngineResources.CategoryName_Financials);
                propertyDefinitionFacade.InitiallyExpanded(false);

                List<PropertyDefinitionFacade> propertyDefinitionFacades = this.getPropertyDefinitionList();
                int num = IntStream.range(0, this.propertyDefinitionFacadeList.size())
                        .filter(i -> this.propertyDefinitionFacadeList.get(i).Name().equals(EngineResources.CategoryName_AdvancedOptions))
                        .findFirst().orElse(-1);
                if (num != -1) {
                    this.getPropertyDefinitionList().add(num, propertyDefinitionFacade);
                }
                this.getPropertyDefinitionList().add(propertyDefinitionFacade);
            }

        }
    }

    @Override
    protected void InsertItem(int index, StringPropertyDefinition stringPropertyDefinition) {
        this.insertItem(index, stringPropertyDefinition, null);
    }

    private void insertItem(int index, StringPropertyDefinition stringPropertyDefinition,
                            Map<Properties, IntelligentObjectProperty> originalPropertyInstances) {
        if (stringPropertyDefinition.owner == null) {
            stringPropertyDefinition.owner = this;
        }

        OverrideObject overrideObject = null;
        if (this.TargetObject instanceof IntelligentObjectDefinition) {
            IntelligentObjectDefinition intelligentObjectDefinition =
                    (IntelligentObjectDefinition) this.TargetObject;
            if (this == intelligentObjectDefinition.getPropertyDefinitions() && intelligentObjectDefinition.parent != null) {
                PropertyDefinitions definitions = intelligentObjectDefinition.parent.getPropertyDefinitions();
                if (definitions.overrides != null && index < definitions.overrides.size()) {
                    overrideObject = definitions.overrides.get(index);
                }
            }
        }

        OverrideObject item = new OverrideObject(stringPropertyDefinition, this, overrideObject);
        this.overrides.add(index, item);
        super.InsertItem(index, stringPropertyDefinition);
        if (this.TargetObject != null) {
            this.actionPropertiesByCondition(
                    (Properties repeatingPropertyGroupInstance) -> {
                        repeatingPropertyGroupInstance.insert(index, stringPropertyDefinition,
                                originalPropertyInstances);
                    }, false, false, null, null);
            this.TargetObject.NotifyPropertyInserted(this, stringPropertyDefinition, index,
                    originalPropertyInstances);
        }
    }

    private void actionPropertiesByCondition(Action<Properties> targetAction, boolean bool_0, boolean bool_1,
                                             Action<AbsPropertyObject> beforeTraverse,
                                             Action<AbsPropertyObject> afterTraverse) {
        boolean flag = true;
        List<AbsPropertyObject> absPropertyObjects = this.TargetObject.getAssociatedInstances();
        if (this.RepeatingPropertyDefinition != null) {
            flag = false;
            absPropertyObjects = this.TargetObject.AllAssociatedInstancesOfThisPropertyBagList();
        }
        List<AbsPropertyObject> propertyObjects = new ArrayList<>();
        IDisposable disposable = null;
        if (bool_1) {
            List<IntelligentObjectDefinition> enumerable =
                    BindingListWrapper.getNotNull(absPropertyObjects.stream().map(AbsPropertyObject::Parent).collect(Collectors.toList()))
                            .stream().distinct().collect(Collectors.toList());

            IDisposable[] disposables = (IDisposable[]) enumerable.stream()
                    .map(IntelligentObjectDefinition::createDefinitionOperator)
                    .toArray();
            disposable = new DisposableWrapper(disposables);
        }

        try (var a = disposable) {
            for (AbsPropertyObject absPropertyObject : absPropertyObjects) {
                if (beforeTraverse != null) {
                    beforeTraverse.apply(absPropertyObject);
                }

                Properties propertyGroupInstance = absPropertyObject.GetPropertyGroupInstance(this.propertyGroupClass);
                if (this.RepeatingPropertyDefinition == null) {
                    if (!flag || (flag && absPropertyObject.objectDefinition == this.TargetObject)) {
                        targetAction.apply(propertyGroupInstance);
                    } else if (flag && absPropertyObject.objectDefinition != this.TargetObject) {
                        propertyObjects.add(absPropertyObject);
                    }
                } else {
                    this.actionProperties(propertyGroupInstance, this, targetAction, bool_0);
                }

                if (afterTraverse != null) {
                    afterTraverse.apply(absPropertyObject);
                }
            }
        }

    }

    public void add(StringPropertyDefinition simioProjectDefinition) {
        super.Add(simioProjectDefinition);
    }

    private void actionProperties(Properties properties, PropertyDefinitions propertyDefinitions,
                                  Action<Properties> targetAction, boolean bool_0) {
        PropertyDefinitions definitions = properties.PropertyDefinitions;
        for (int i = 0; i < definitions.size(); i++) {
            StringPropertyDefinition stringPropertyDefinition = definitions.get(i);
            org.licho.brain.concrete.RepeatingPropertyDefinition repeatingPropertyDefinition =
                    (org.licho.brain.concrete.RepeatingPropertyDefinition) stringPropertyDefinition;
            if (repeatingPropertyDefinition != null) {
                RepeatStringPropertyRow repeatStringPropertyRow = (RepeatStringPropertyRow) properties.get(i);
                BindingList<Properties> enumerable = repeatStringPropertyRow.PropertyDescriptors;
                if (bool_0) {
                    enumerable.values = enumerable.reverse();
                }

                if (repeatingPropertyDefinition.propertyDefinitions == propertyDefinitions) {
                    for (var item : enumerable.values) {
                        targetAction.apply(item);
                    }
                    return;
                }

                for (Properties p : enumerable.values) {
                    this.actionProperties(p, propertyDefinitions, targetAction, false);
                }
            }
        }
    }


    public Object method_20(ElementPropertyDefinition elementPropertyDefinition, PropertyDefinitions owner,
                            ElementReferenceType referenceType, ElementReferenceType referenceType1, Object param1) {
        // TODO: 2022/1/21 
        return null;
    }

    public void addDefinition(StringPropertyDefinition stringPropertyDefinition) {
        super.Add(stringPropertyDefinition);
    }

    public void UpdateForNewPropertyDefinition(StringPropertyDefinition stringPropertyDefinition) {
        List<AbsPropertyObject> absPropertyObjects = this.TargetObject.getAssociatedInstances();
        for (AbsPropertyObject propertyObject : absPropertyObjects) {
            Properties properties = propertyObject.properties;
            if (this.RepeatingPropertyDefinition == null) {
                properties.get(stringPropertyDefinition.overRidePropertyIndex).UpdateForNewPropertyDefinition();
            } else {
                this.UpdateChildrenForNewPropertyDefinition(stringPropertyDefinition, properties, stringPropertyDefinition.owner);
            }
        }
    }

    private void UpdateChildrenForNewPropertyDefinition(StringPropertyDefinition stringPropertyDefinition, Properties properties,
                                                        PropertyDefinitions owner) {
        PropertyDefinitions definitions = properties.PropertyDefinitions;

        for (int i = 0; i < definitions.size(); i++) {
            if (definitions.get(i) instanceof RepeatingPropertyDefinition) {
                RepeatingPropertyDefinition repeatingPropertyDefinition =
                        (RepeatingPropertyDefinition) definitions.get(i);
                RepeatStringPropertyRow repeatStringPropertyRow =
                        (RepeatStringPropertyRow) properties.get(i);
                if (repeatingPropertyDefinition.propertyDefinitions == owner) {
                    for (var ps : repeatStringPropertyRow.PropertyDescriptors.getValues()) {
                        ps.get(stringPropertyDefinition.overRidePropertyIndex).UpdateForNewPropertyDefinition();
                    }
                    continue;
                }

                for (Properties p : repeatStringPropertyRow.PropertyDescriptors.getValues()) {
                    this.UpdateChildrenForNewPropertyDefinition(stringPropertyDefinition, p, owner);
                }
            }
        }
    }

    public void setTargetObjectDefinition(GridObjectDefinition gridObjectDefinition) {
        this.TargetObject = gridObjectDefinition;
    }

    @Override
    public IPropertyDefinition getByName(String name) {
        return null;
    }

    @Override
    public Iterator<IPropertyDefinition> iterator() {
        return this.values.stream().map(IPropertyDefinition.class::cast).iterator();
    }

    public static class PropertyReplacement {
        private int version;
        private String oldPropertyName;
        private String newPropertyName;

        private PropertyReplacement() {

        }

        public PropertyReplacement(int version, String oldPropertyName, String newPropertyName) {
            this.version = version;
            this.oldPropertyName = oldPropertyName;
            this.newPropertyName = newPropertyName;
        }

        public static PropertyReplacement readXmlPropertyReplacement(XmlReader xmlReader) {
            if (Objects.equals(xmlReader.Name(), "PropertyReplacement")) {
                PropertyDefinitions.PropertyReplacement propertyReplacement =
                        new PropertyDefinitions.PropertyReplacement();
                propertyReplacement.readPropertyReplacement(xmlReader);
                return propertyReplacement;
            }
            return null;
        }

        void writeXmlPropertyReplacement(XmlWriter xmlWriter) {
//            xmlWriter.WriteStartElement("PropertyReplacement");
//            xmlWriter.WriteAttributeString("Version", this.version.ToString(CultureInfo.InvariantCulture));
//            xmlWriter.WriteAttributeString("OldPropertyName", this.oldPropertyName);
//            xmlWriter.WriteAttributeString("NewPropertyName", this.newPropertyName);
//            xmlWriter.WriteEndElement();
        }

        void readPropertyReplacement(XmlReader xmlReader) {
            SomeXmlOperator.xmlReaderOperator(xmlReader, "PropertyReplacement",
                    this::readPropertyVersion, null);

        }

        private void readPropertyVersion(XmlReader xmlReader) {
            SomeXmlOperator.readXml(xmlReader, "Version", this::setVersion);
            SomeXmlOperator.readXml2(xmlReader, "OldPropertyName", this::OldPropertyName);
            SomeXmlOperator.readXml2(xmlReader, "NewPropertyName", this::NewPropertyName);
        }

        public int getVersion() {
            return version;
        }

        public void setVersion(int version) {
            this.version = version;
        }

        public String OldPropertyName() {
            return oldPropertyName;
        }

        public void OldPropertyName(String oldPropertyName) {
            this.oldPropertyName = oldPropertyName;
        }

        public String NewPropertyName() {
            return newPropertyName;
        }

        public void NewPropertyName(String newPropertyName) {
            this.newPropertyName = newPropertyName;
        }
    }
}
