package org.licho.brain.concrete;

import org.licho.brain.enu.IconIndex;
import org.licho.brain.brainApi.enu.ListType;

/**
 *
 */
public class StringList extends AbsListProperty {
    public StringList(GridObjectDefinition definition, String name) {
        super(definition, name);
    }

    @Override
    protected String ListTypeDescription() {
        return "Strings";
    }

    @Override
    public ListType ListType() {
        return ListType.String;
    }

    @Override
    public String ItemTypeName() {
        return "String List";
    }

    @Override
    protected IconIndex AutoCompleteChoiceIconIndex() {
        return IconIndex.FOURTHENN;
    }

    @Override
    public String XmlTypeName() {
        return "StringList";
    }

    @Override
    public boolean IsANumberedList() {
        return true;
    }


    @Override
    public boolean RowNumbersAreZeroBased() {
        return true;
    }

    public void initItems(String[] values) {
        String[][] array = new String[values.length][1];
        for (int i = 0; i < values.length; i++) {
            array[i][0] = values[i];
        }
        ((RepeatStringPropertyRow) this.properties.get(0)).setStringValues(array);

    }
}
