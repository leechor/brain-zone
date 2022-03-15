package org.licho.brain.concrete;

/**
 *
 */
public class ItemEditPolicy {

    public static String getName() {
        return name;
    }

    public static void setName(String name) {
        ItemEditPolicy.name = name;
    }

    public static String getDescription() {
        return description;
    }

    public static void setDescription(String description) {
        ItemEditPolicy.description = description;
    }

    public static String  name = "Name";

    public static String description = "Description";

    public void method_0(ISearch search, Object name, ActiveModel activeModel) {

    }

    public void remove(ISearch search) {
        // TODO: 2022/1/6
//        if (this.searchItemMap.ContainsKey(search))
//        {
//            this.searchItemMap.Remove(search);
//        }
//        this.remove((ItemEditPolicy.Class1234 tempRef) => tempRef.getSearch() == search);

    }
}
