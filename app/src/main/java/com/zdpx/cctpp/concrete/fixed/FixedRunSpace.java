package com.zdpx.cctpp.concrete.fixed;

import com.zdpx.cctpp.concrete.TokenRunSpace;
import com.zdpx.cctpp.concrete.IntelligentObject;
import com.zdpx.cctpp.concrete.IntelligentObjectRunSpace;
import com.zdpx.cctpp.concrete.MayApplication;
import com.zdpx.cctpp.simioEnums.TransferConstraintsType;

/**
 *
 */
public class FixedRunSpace extends IntelligentObjectRunSpace {

    private Fixed fix;
    private TransferConstraintsType transferInConstraintsType;
    private TransferConstraintsType transferOutConstraintsType;

    public FixedRunSpace(IntelligentObjectRunSpace statisticsDataSourceIntelligentObject,
                         MayApplication application, IntelligentObject intelligentObject) {
        super(statisticsDataSourceIntelligentObject, application, intelligentObject);
        this.fix = (Fixed) this.myElementInstance;
    }

    public boolean method_70(TokenRunSpace tokenRunSpace) {
        return this.fix.method_94(tokenRunSpace, this.ParentObjectRunSpace);
    }

    @Override
    public void Initialize(boolean initializeState, boolean initializeStatistics) {
        super.Initialize(initializeState, initializeStatistics);
        if (initializeState && this.fix != null) {
            this.transferInConstraintsType = (TransferConstraintsType) this.fix.method_89().getObjectValue(this,
                    this.ParentObjectRunSpace);
            this.transferOutConstraintsType = (TransferConstraintsType) this.fix.method_91().getObjectValue(this,
                    this.ParentObjectRunSpace);
        }
    }

    public TransferConstraintsType TransferOutConstraintsType() {
        return this.transferOutConstraintsType;
    }

    public TransferConstraintsType TransferInConstraintsType() {
        return this.transferInConstraintsType;
    }

    @Override
    public boolean SupportsFacilityView() {
        return true;
    }

}
