package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;
import org.licho.brain.api.IFunctionTable;
import org.licho.brain.api.IFunctionTables;

import java.util.Iterator;

/**
 *
 */
public class FunctionTables extends BindingList<UserFunction> implements IFunctionTables, IAutoComplete {
    private IntelligentObjectDefinition assigner;

    public FunctionTables(IntelligentObjectDefinition assigner) {
        this.assigner = assigner;
    }

    private IntelligentObjectDefinition Parent() {
        return this.assigner;
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
                (XmlReader body) -> UserFunction.readXml(xmlReader, intelligentObjectXml, this.assigner) != null);
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
