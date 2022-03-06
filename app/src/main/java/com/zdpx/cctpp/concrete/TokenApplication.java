package com.zdpx.cctpp.concrete;

import com.zdpx.cctpp.concrete.fake.TableRowReferences;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class TokenApplication {

	private Token token;

	private List<TokenRunSpace> tokenRunSpaces = new ArrayList<>();

	private final MayApplication application;

	public TokenApplication(TokenDefinition tokenDefinition, MayApplication mayApplication)
	{
		this.application = mayApplication;
		this.token = (Token)tokenDefinition.CreateInstance("CreatorInstance");
	}

	public TokenRunSpace setTokenRunSpace(IntelligentObjectRunSpace intelligentObjectRunSpace, ProcessPropertyElementRunSpace processPropertyElementRunSpace, IntelligentObjectRunSpace associatedObjectRunSpace, IntelligentObjectRunSpace contextObjectRunSpace, TableRowReferences tableRowReferences)
	{
		TokenRunSpace tokenRunSpace;
		if (this.tokenRunSpaces.size() > 0)
		{
			int index = this.tokenRunSpaces.size() - 1;
			tokenRunSpace = this.tokenRunSpaces.get(index);
			this.tokenRunSpaces.remove(index);
			tokenRunSpace.parentObjectRunSpace = intelligentObjectRunSpace;
			tokenRunSpace.setApplication(intelligentObjectRunSpace.getMayApplication());
			tokenRunSpace.myElementInstance = this.token;
		}
		else
		{
			tokenRunSpace = (TokenRunSpace)this.token.CreateRunSpaceWithPopulation(intelligentObjectRunSpace, intelligentObjectRunSpace.getMayApplication());
		}
		tokenRunSpace.init(processPropertyElementRunSpace, associatedObjectRunSpace, contextObjectRunSpace, tableRowReferences);
		if (this.application != null)
		{
			this.application.numberOfTokensInSystemIncrement();
		}
		return tokenRunSpace;
	}

	public void addTokenRunSpace(TokenRunSpace tokenRunSpace)
	{
		this.tokenRunSpaces.add(tokenRunSpace);
		if (this.application != null)
		{
			this.application.numberOfTokensInSystemDecrement();
		}
	}

	public int getTokenCount()
	{
		return this.tokenRunSpaces.size();
	}



}
