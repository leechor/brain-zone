package org.licho.brain.concrete;

import org.licho.brain.concrete.fake.XmlReader;

import java.util.List;
import java.util.stream.Collectors;

/**
 *
 */
public class Tokens extends BindingList<TokenDefinition> {
    private final IntelligentObjectDefinition intelligentObjectDefinition;

    public Tokens(IntelligentObjectDefinition intelligentObjectDefinition) {
        this.intelligentObjectDefinition = intelligentObjectDefinition;
    }

    public boolean readXml(XmlReader xmlReader, IntelligentObjectXml intelligentObjectXml) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Tokens", null, (XmlReader body) ->
                TokenDefinition.readXmlToken(xmlReader, intelligentObjectXml, this.intelligentObjectDefinition) != null);
    }

    public TokenDefinition getTokenDefinitionByName(String name) {
        if (TokenDefinition.name.equalsIgnoreCase(name)) {
            return TokenDefinition.Instance;
        }
        for (TokenDefinition tokenDefinition : this.getValues()) {
            if (StringHelper.equalsLocal(tokenDefinition.Name(), name)) {
                return tokenDefinition;
            }
        }
        return null;
    }

    public List<TokenDefinition> getTokenDefinitionsByName(String name) {
        if (TokenDefinition.name.equalsIgnoreCase(name)) {
            return List.of(TokenDefinition.Instance);
        }

        return this.getValues().stream()
                .filter(t -> StringHelper.equalsLocal(t.Name(), name)).collect(Collectors.toList());
    }

    @Override
    protected Object AddNewCore() {
        TokenDefinition tokenDefinition =
                TokenDefinition.createTokenDefinition(this.intelligentObjectDefinition.GetUniqueName("MyToken"),
                        this.intelligentObjectDefinition);
        super.Add(tokenDefinition);
        return tokenDefinition;
    }

    @Override
    protected void InsertItem(int index, TokenDefinition tokenDefinition) {
        super.InsertItem(index, tokenDefinition);
        this.intelligentObjectDefinition.insertTokenDefinition(tokenDefinition, index);
    }

    @Override
    protected void RemoveItem(int index) {
        TokenDefinition param = super.get(index);
        super.RemoveItem(index);
        this.intelligentObjectDefinition.removeTokenDefinition(param);
    }

    	public void updateTokenDefinition(int index, TokenDefinition tokenDefinition)
	{
		TokenDefinition definition = super.get(index);
		if (definition != tokenDefinition)
		{
			super.Insert(index, tokenDefinition);
			this.intelligentObjectDefinition.updateRecuriseTokenDefinition(index, definition, tokenDefinition);
		}
	}
}
