package org.licho.brain.concrete;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 *
 */
public class BindingListWrapper {

    public static <T> List<T> yieldBingdingListItem(BindingList<T> list) {
        return list.values;
    }

    public static <T> List<T> getNotNull(List<T> source) {
        return source.stream().filter(Objects::nonNull).collect(Collectors.toList());
    }
}
