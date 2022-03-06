package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.IFunction.Action;
import com.zdpx.cctpp.IPropertyChangedCreateAction;
import com.zdpx.cctpp.Interface190;
import com.zdpx.cctpp.concrete.fake.XmlReader;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.resource.Image;
import com.zdpx.cctpp.utils.simu.system.IDisposable;

/**
 *
 */
public class Symbol implements IDisposable, IGridObject, IItemDescriptor, IPropertyChangedCreateAction {

	public static boolean readXml(XmlReader xmlReader, Facade facade, Symbol symbol, Action<Symbol> assignAction)
	{
		Symbol[] symbolByGuid = new Symbol[1];
		boolean result = SomeXmlOperator.xmlReaderElementOperator(xmlReader, "SymbolRef", (XmlReader attr) ->
		{
			String attribute = attr.GetAttribute("Id");
			if (symbol != null && attribute.equalsIgnoreCase("[UseExternalSymbol]"))
			{
				symbolByGuid[0] = symbol;
				return;
			}
			Guid guid = new Guid(attribute);
			symbolByGuid[0] = facade.getSymbolByGuid(guid);
		}, null);
		if (symbolByGuid[0] != null)
		{
			assignAction.apply(symbolByGuid[0]);
		}
		return result;
	}
    @Override
    public String GetGridObjectClassName() {
        return null;
    }

    @Override
    public String GetGridObjectDescription() {
        return null;
    }

    @Override
    public String GetGridObjectInstanceName() {
        return null;
    }

    @Override
    public GridItemProperties GetGridPropertyItemList(GridItemProperties param0, GridObjectDefinition param1) {
        return null;
    }

    @Override
    public IntelligentObjectProperty UpdatePropertyChange(int param0, Object param1) {
        return null;
    }

    @Override
    public String[] DisplayedValuesNeeded(int param0) {
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
