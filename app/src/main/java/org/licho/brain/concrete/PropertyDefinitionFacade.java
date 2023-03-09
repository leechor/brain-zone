package org.licho.brain.concrete;

import com.google.common.base.Strings;
import lombok.Builder;

@Builder
public class PropertyDefinitionFacade implements IPropertyDefinitionFacade {


    private String name;

    private String description;

    private PropertyDefinitionFacade parent;

    @Builder.Default
    private boolean initiallyExpanded = true;

    public PropertyDefinitionFacade() {
    }

    public PropertyDefinitionFacade(String name) {
        this.name = name;
    }

    public PropertyDefinitionFacade(String name, boolean initiallyExpanded) {
        this.name = name;
        this.description = name;
        this.initiallyExpanded = initiallyExpanded;
    }

    public PropertyDefinitionFacade(String name, String description, boolean initiallyExpanded) {
        this.name = name;
        this.description = description;
        this.initiallyExpanded = initiallyExpanded;
    }

    public PropertyDefinitionFacade(String name, String description, PropertyDefinitionFacade parent,
                                    boolean initiallyExpanded) {
        this.name = name;
        this.description = description;
        this.parent = parent;
        this.initiallyExpanded = initiallyExpanded;
    }

    public String Name() {
        return this.name;
    }

    public void Name(String value) {
        this.name = value;
    }

    public String Description() {
        return this.description;
    }

    public void Description(String value) {
        this.description = value;
    }

    public String DisplayName() {
        if (Strings.isNullOrEmpty(name)) {
            return this.name;
        }
        int num = this.name.lastIndexOf('/');
        if (num >= 0) {
            return this.name.substring(num + 1);
        }
        return this.name;
    }

    public PropertyDefinitionFacade Parent() {
        return this.parent;
    }

    public void Parent(PropertyDefinitionFacade value) {
        this.parent = value;
    }

    public boolean InitiallyExpanded() {
        return this.initiallyExpanded;
    }

    public void InitiallyExpanded(boolean value) {
        this.initiallyExpanded = value;
    }
}




