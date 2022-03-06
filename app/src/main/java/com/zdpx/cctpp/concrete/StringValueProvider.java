package com.zdpx.cctpp.concrete;

import java.util.ArrayList;
import java.util.List;

/**
 *
 */
public class StringValueProvider implements IValueProvider {
    private final String name;

    public StringValueProvider(String name) {
        this.name = name;
    }

    	public String[] getValue(IntelligentObjectDefinition intelligentObjectDefinition)
	{
		if (intelligentObjectDefinition == null)
		{
			return null;
		}
		List<String> list = new ArrayList<>();
		for (TokenDefinition token : intelligentObjectDefinition.getTokens().values)
		{
			list.add(token.Name());
		}

		list.add(0, TokenDefinition.name);
		return list.toArray(new String[0]);
	}
}
