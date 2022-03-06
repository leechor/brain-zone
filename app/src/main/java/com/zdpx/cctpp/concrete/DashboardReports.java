package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;

/**
 *
 */
public class DashboardReports {
	private final ActiveModel activeModel;

	public DashboardReports(ActiveModel activeModel) {
        this.activeModel = activeModel;
      //  this.activeModel.getIntelligentObjectDefinition().Tables.ListChanged += this.method_0;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return false;
    }
}
