package com.zdpx.cctpp.concrete.fake;

import com.zdpx.cctpp.concrete.IRunSpace;
import com.zdpx.cctpp.concrete.IntelligentObjectRunSpace;
import com.zdpx.cctpp.concrete.Properties;
import com.zdpx.cctpp.concrete.RepeatStringPropertyRow;
import com.zdpx.cctpp.concrete.Table;
import com.zdpx.cctpp.concrete.TableRow;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TableOperator {
    private int indexRow;
    private Table table;
    private final List<RepeatStringPropertyWrapper> repeatStringPropertyWrappers = new ArrayList<>();

    public int getTableRow() {
        return this.indexRow;
    }

    public int getTableRowCount() {
        if (this.table != null) {
            return this.table.Data().Rows().values.size();
        }
        return 0;
    }

    public TableRow[] getTableRows() {
        // TODO: 2022/1/20 
        return new TableRow[0];
    }

    public Properties getProperties(RepeatStringPropertyRow repeatStringPropertyRow, IRunSpace runSpace,
                                    IntelligentObjectRunSpace intelligentObjectRunSpace) {
        int index = this.getIndex(repeatStringPropertyRow);
        if (index != -1) {
            return this.repeatStringPropertyWrappers.get(index).Properties();
        }
        return null;
    }

    private int getIndex(RepeatStringPropertyRow repeatStringPropertyRow) {
        int index = 0;
        for (TableOperator.RepeatStringPropertyWrapper repeatStringPropertyWrapper :
				this.repeatStringPropertyWrappers) {
            if (repeatStringPropertyWrapper.RepeatStringPropertyRow == repeatStringPropertyRow) {
                return index;
            }
            index++;
        }
        return -1;
    }

    public boolean method_39(Table table) {
        return false;
//		return table != null && (this.dictionary_1.ContainsKey(table) || this.dictionary_0.ContainsKey(table));
    }

    public void clear() {
        // TODO: 2022/2/7 
    }

    public static class RepeatStringPropertyWrapper {
        public RepeatStringPropertyWrapper(com.zdpx.cctpp.concrete.RepeatStringPropertyRow repeatStringPropertyRow, int index) {
            this.RepeatStringPropertyRow = repeatStringPropertyRow;
            this.index = index;
        }

        public Properties Properties() {
            return this.RepeatStringPropertyRow.PropertyDescriptors.get(this.index);

        }

        public final com.zdpx.cctpp.concrete.RepeatStringPropertyRow RepeatStringPropertyRow;

        public final int index;

        public static final TableOperator.RepeatStringPropertyWrapper Instance = new TableOperator.RepeatStringPropertyWrapper(null, -1);
    }
}
