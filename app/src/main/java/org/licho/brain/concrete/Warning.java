package org.licho.brain.concrete;

import com.google.common.base.Strings;
import org.licho.brain.concrete.fake.XmlReader;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Objects;
import java.util.Stack;

/**
 *
 */
public class Warning implements Comparable<Warning>, Cloneable {
    private String identifier;
    private Stack<String> instanceNames = new Stack<>();

    private Warning() {
    }

    public Warning(String identifier) {
        this.identifier = identifier;
    }

    public static Warning xmlReaderWarning(XmlReader xmlReader) {
        if (Objects.equals(xmlReader.Name(), "Warning")) {
            Warning warning = new Warning();
            warning.xmlReaderWarningAttribute(xmlReader);
            return warning;
        }
        return null;
    }

    boolean xmlReaderWarningAttribute(XmlReader xmlReader) {
        return SomeXmlOperator.xmlReaderElementOperator(xmlReader, "Warning",
                t -> {
                    this.identifier = t.GetAttribute("Identifier");
                    String location = t.GetAttribute("Location");
                    if (!Strings.isNullOrEmpty(location)) {
                        List<String> tmp = Arrays.asList(location.split("\\."));
                        Collections.reverse(tmp);
                        tmp.forEach(this::bindToInstance);
                    }
                }, null);
    }


    public void bindToInstance(String instanceName) {
        if (!Strings.isNullOrEmpty(instanceName)) {
            this.instanceNames.add(instanceName);
        }
    }

    @Override
    public int compareTo(Warning o) {
        return 0;
    }

    public String removeInstanceNamesPeek() {
        if (!this.instanceNames.isEmpty()) {
            return this.instanceNames.pop();
        }
        return null;
    }

    @Override
    @SuppressWarnings("unchecked")
    protected Warning clone() {
        var tmp = new Warning(this.identifier);
        tmp.instanceNames = (Stack<String>) this.instanceNames.clone();
        return tmp;
    }

    public String getInstanceNamePeek() {
        if (!this.instanceNames.isEmpty()) {
            return this.instanceNames.peek();
        }
        return null;
    }
}
