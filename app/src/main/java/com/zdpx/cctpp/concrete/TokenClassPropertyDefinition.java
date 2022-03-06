package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.cont.EngineResources;
import com.zdpx.cctpp.concrete.property.IntelligentObjectProperty;
import com.zdpx.cctpp.enu.DataFormat;

/**
 *
 */
public class TokenClassPropertyDefinition extends StringPropertyDefinition {
    public TokenClassPropertyDefinition(String name) {
        super(name);
        this.defaultString = "Token";
        this.dataFormat = DataFormat.List;
    }
    
    @Override
    	public IntelligentObjectProperty CreateInstance(Properties properties)
	{
		return new TokenClassPropertyRow(this, properties);
	}

    @Override
	public  String GetGridObjectClassName()
	{
		return EngineResources.TokenClassProperty_ClassName;
	}

    @Override
	public  String GetGridObjectDescription()
	{
		return EngineResources.TokenClassProperty_ClassDescription;
	}

	// (get) Token: 0x060055F5 RID: 22005 RVA: 0x00044885 File Offset: 0x00042A85
    @Override
	  IValueProvider ValueProvider()
	{
	    return new StringValueProvider(super.NullNullString());
	}
}
