package com.zdpx.cctpp.concrete;

/**
 *
 */
public class PropertiesRunSpaceWrapper {
    public PropertiesRunSpaceWrapper(Properties properties, IntelligentObjectRunSpace intelligentObjectRunSpace) {
        this.Properties = properties;
        this.IntelligentObjectRunSpace = intelligentObjectRunSpace;
    }

    public final Properties Properties;

    public final IntelligentObjectRunSpace IntelligentObjectRunSpace;
}
