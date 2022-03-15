package org.licho.brain.concrete.fake;

import org.licho.brain.concrete.IRunSpace;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.Properties;
import org.licho.brain.concrete.RepeatStringPropertyRow;
import org.licho.brain.concrete.Table;
import org.licho.brain.concrete.TableRow;

/**
 *
 */
public class TableRowReferences {
    private TableOperator tableOperator;

    public Table getTable() {
        return null;
    }

    public int getCount(Table table, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        return 0;
    }

    public int getTableRow() {
        if (this.tableOperator == null) {
            return -1;
        }
        return this.tableOperator.getTableRow();
    }

    public int getTableRowCount() {
        if (this.tableOperator == null) {
            return 0;
        }
        return this.tableOperator.getTableRowCount();
    }

    public TableRow[] getTableRows() {
        if (this.tableOperator == null) {
            return null;
        }
        return this.tableOperator.getTableRows();
    }

    public Properties getProperties(RepeatStringPropertyRow repeatStringPropertyRow, IRunSpace runSpace,
                                    IntelligentObjectRunSpace intelligentObjectRunSpace) {
        if (this.tableOperator == null) {
            return null;
        }
        return this.getTableOperator().getProperties(repeatStringPropertyRow, runSpace, intelligentObjectRunSpace);
    }

    private TableOperator getTableOperator() {
        if (this.tableOperator == null) {
            this.tableOperator = new TableOperator();
        }
        return this.tableOperator;
    }

    public boolean method_20(Table table) {
		return this.tableOperator != null && this.tableOperator.method_39(table);
    }

    public void clear() {
		if (this.tableOperator != null)
		{
			this.tableOperator.clear();
		}
    }

    public void method_1(TableRowReferences tableRowReferences) {
        // TODO: 2022/2/7 
    }
}
