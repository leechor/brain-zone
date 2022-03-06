package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.XmlReader;

/**
 *
 */
public class LogWrapper {
    private final ActiveModel activeModel;
    private ResourceUsageLog targetResults;
    private InOutputStream resourceUsageLogStream;
    private ResourceStateLog resourceStateLog;
    private InOutputStream resourceStateLogStream;
    private ResourceCapacityLog resourceCapacityLog;
    private InOutputStream resourceCapacityLogStream;
    private ConstraintRecord constraintRecord;
    private InOutputStream constraintLogStream;
    private TransporterUsageLog transporterUsageLog;
    private InOutputStream transporterUsageLogStream;
    private StateObservationLog stateObservationLog;
    private InOutputStream stateObservationLogStream;
    private TallyObservationLog tallyObservationLog;
    private InOutputStream tallyObservationLogStream;
    private MaterialUsageLog materialUsageLog;
    private InOutputStream materialUsageLogStream;

    public LogWrapper(ActiveModel activeModel) {
        this.activeModel = activeModel;

    }

    public void ResetBindings() {
        // TODO: 2021/12/17 
    }

    public boolean readXmlResourceUsageLog(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return false;
//        return this.readXmlResourceUsageLog(xmlReader, intelligentObjectXml, "") ||
//                this.readXmlResourceUsageLog(xmlReader, intelligentObjectXml, "_v2") ||
//                (this.readXmlResourceStateLog(xmlReader, intelligentObjectXml, "") ||
//                        this.readXmlResourceStateLog(xmlReader, intelligentObjectXml, "_v2")) ||
//                (this.method_31(xmlReader, intelligentObjectXml, "") ||
//                        this.method_31(xmlReader, intelligentObjectXml, "_v2")) ||
//                (this.method_34(xmlReader, intelligentObjectXml, "") ||
//                        this.method_34(xmlReader, intelligentObjectXml, "_v2")) ||
//                (this.method_33(xmlReader, intelligentObjectXml, "") ||
//                        this.method_33(xmlReader, intelligentObjectXml, "_v2")) ||
//                (this.method_37(xmlReader, intelligentObjectXml, "") ||
//                        this.method_37(xmlReader, intelligentObjectXml, "_v2")) ||
//                (this.method_35(xmlReader, intelligentObjectXml, "") ||
//                        this.method_35(xmlReader, intelligentObjectXml, "_v2")) ||
//                (this.method_36(xmlReader, intelligentObjectXml, "") ||
//                        this.method_36(xmlReader, intelligentObjectXml, "_v2"));

    }

    public void reset() {
        // TODO: 2022/2/6 
    }

    public void dispose() {
        // TODO: 2022/2/7 
    }
}
