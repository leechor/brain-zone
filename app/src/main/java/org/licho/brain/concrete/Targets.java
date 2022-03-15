package org.licho.brain.concrete;

import java.util.Iterator;
import java.util.Objects;

/**
 *
 */
public class Targets implements IIdentityName, Iterable<Target> {
    private BindingList<Target> targets = new BindingList<Target>();

    public int Count() {
        return this.targets.values.size();
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
    public Iterator<Target> iterator() {
        return null;
    }

    public Target getTargetByName(String targetName) {
        return this.targets.values.stream().filter(target -> targetName != null &&
                        (Objects.equals(target.DisplayName(), targetName) || Objects.equals(target.Name(), targetName)))
                .findFirst()
                .orElse(null);
    }
}
