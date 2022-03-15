package org.licho.brain.state;

import org.licho.brain.unit.UnitType;

/**
 * The state representing a real numeric value that may change by assignment logic at discrete times during a model run.
 */
public class RealState<T> extends State {
    /**
     * todo: maybe enum type
     */
    private UnitType unitType;

    public enum DimensionType {
        SCALAR,
        VECTOR,
        MATRIX,
        MATRIX_FROM_TABLE
    }

    private T initStateValue;

    /**
     * Auto reset when statistics cleared
     */
    private boolean autoReset;

}
