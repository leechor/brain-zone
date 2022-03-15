package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.simuApi.IFunctionTable;
import org.licho.brain.simuApi.IFunctionTables;

import java.util.Iterator;

/**
 *
 */
public class FunctionTables extends BindingList<UserFunction> implements IFunctionTables, IAutoComplete {
    private IntelligentObjectDefinition parent;

    public FunctionTables(IntelligentObjectDefinition iIntelligentObjects) {
        this.parent = iIntelligentObjects;
    }

    private IntelligentObjectDefinition Parent() {
        return this.parent;
    }


    @Override
    public IFunctionTable getByName(String name) {
        return null;
    }

    @Override
    public int Count() {
        return 0;
    }

    @Override
    public IFunctionTable getByIndex(int index) {
        return null;
    }

    @Override
    public Iterator<IFunctionTable> iterator() {
        return null;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "UserFunctions", null,
                (XmlReader body) -> UserFunction.readXml(xmlReader, intelligentObjectXml, this.parent) != null);
    }

    public UserFunction getUserFunction(String name) {
        for (UserFunction userFunction : this.values) {
            if (StringHelper.equalsLocal(name, userFunction.Name())) {
                return userFunction;
            }
        }
        return null;
    }
}
