package org.licho.brain.concrete.fixed;

import org.licho.brain.concrete.TokenRunSpace;
import org.licho.brain.concrete.IntelligentObject;
import org.licho.brain.concrete.IntelligentObjectRunSpace;
import org.licho.brain.concrete.MayApplication;
import org.licho.brain.simioEnums.TransferConstraintsType;

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
