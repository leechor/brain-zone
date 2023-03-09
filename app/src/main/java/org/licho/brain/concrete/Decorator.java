package org.licho.brain.concrete;

import org.licho.brain.IFunction.Action;
import org.licho.brain.IPropertyChangedCreateAction;
import org.licho.brain.Interface190;
import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.concrete.property.IntelligentObjectProperty;
import org.licho.brain.resource.Image;
import org.licho.brain.utils.simu.system.IDisposable;

/**
 *
 */
public final class Decorator implements IDisposable, IGridObject, IItemDescriptor, IPropertyChangedCreateAction {
    private String name;
    private Guid guid = Guid.Empty;

    public Decorator() {
        this.name = "Decorator";
        this.initEvent();
        this.guid = Guid.NewGuid();
    }

    public static boolean readXmlPathDecoratorRef(XmlReader xmlReader, Facade facade, Action<Decorator> assignAction) {
        Decorator[] decorator = new Decorator[1];
        boolean result = SomeXmlOperator.xmlReaderElementOperator(xmlReader, "PathDecoratorRef",
                (XmlReader attr) ->
                {
                    String attribute = attr.GetAttribute("Id");
                    Guid guid = new Guid(attribute);
                    decorator[0] = facade.getDecoratorByGuid(guid);
                }, null);
        if (decorator[0] != null) {
            assignAction.apply(decorator[0]);
        }
        return result;
    }

    private void initEvent() {
        // TODO: 2022/1/28
    }


    @Override
    public String Description() {
        return null;
    }

    @Override
    public Image GetOrCreateThumbnail(Interface190 param0, int param1, int param2) {
        return null;
    }

    @Override
    public Guid UniqueID() {
        return null;
    }

    @Override
    public IGridObject PropertyGridObject() {
        return null;
    }

    @Override
    public String getObjectClassName() {
        return null;
    }

    @Override
    public String getObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties getPropertyItemList(GridItemProperties gridItemProperties, GridObjectDefinition gridObjectDefinition) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int index, Object value) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int index) {
        return new String[0];
    }

    @Override
    public Object Item() {
        return null;
    }

    @Override
    public String Name() {
        return null;
    }

    @Override
    public String Group() {
        return null;
    }

    @Override
    public int GroupImportance() {
        return 0;
    }

    @Override
    public String DisplayName() {
        return null;
    }

    @Override
    public String ObjectType() {
        return null;
    }

    @Override
    public String Category() {
        return null;
    }

    @Override
    public int IconIndex() {
        return 0;
    }

    @Override
    public int StateIconIndex() {
        return 0;
    }

    @Override
    public Image Icon() {
        return null;
    }

    @Override
    public void Rename(String newName) {

    }

    @Override
    public boolean CanRenameTo(String newName, StringBuffer failureReason) {
        return false;
    }

    @Override
    public void Dispose() {

    }
}
