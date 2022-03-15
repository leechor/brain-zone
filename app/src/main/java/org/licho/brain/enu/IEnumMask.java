package org.licho.brain.enu;


/**
 *
 */
public interface IEnumMask {

    int mask();

    static boolean contains(int value, IEnumMask... resultType) {
        for (var r : resultType) {
            if ((value & r.mask()) == 0) {
                return false;
            }
        }
        return true;
    }

    static boolean contains(int parent, int sub) {
        return (parent & sub) == parent;
    }

    static int add(int total, IEnumMask mask) {
        return total | mask.mask();
    }

    static int remove(int total, IEnumMask mask) {
        return total & ~mask.mask();
    }
}
