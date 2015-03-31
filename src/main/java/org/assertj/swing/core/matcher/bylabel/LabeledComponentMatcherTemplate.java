/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License. You may obtain a copy of
 * the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the
 * License for the specific language governing permissions and limitations under
 * the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.swing.core.matcher.bylabel;

import java.awt.Component;
import java.util.regex.Pattern;
import javax.swing.JLabel;
import org.assertj.swing.core.matcher.JLabelMatcher;
import org.assertj.swing.core.matcher.NamedComponentMatcherTemplate;

/**
 *
 * @param <T> the type of {@code Component} supported by this matcher.
 */
public abstract class LabeledComponentMatcherTemplate<T extends Component> extends NamedComponentMatcherTemplate<T> {

    private JLabelMatcher labelMatcher;
    private Object labelText;
    private Object labelName;

    protected LabeledComponentMatcherTemplate(Class<T> supportedType, Object name) {
        super(supportedType, name);
    }

    protected LabeledComponentMatcherTemplate(Class<T> supportedType, Object name, Object labelName, Object labelText) {
        super(supportedType, name);
        if (labelName instanceof String) {
            labelMatcher = JLabelMatcher.withName((String) labelName);
        } else {
            labelMatcher = JLabelMatcher.any();
        }
        this.labelName = labelName;
        setLabelText(labelText);
    }

    protected boolean isComponentMatching(T component) {
        if (labelName == null && labelText == null) {
            return isExtendedMatching(component);
        } else {
            return isMatchingByLabel(component);
        }
    }

    protected abstract boolean isExtendedMatching(T component);

    private boolean isMatchingByLabel(T component) {
        if (component.getParent() == null) {
            return false;
        }
        int componentCount = component.getParent().getComponentCount();
        boolean labelFound = false;
        for (int i = 0; i < componentCount; i++) {
            Component c = component.getParent().getComponent(i);
            if (labelFound) {
                if (this.supportedType().isInstance(c) && c.equals(component)) {
                    return true;
                }
                labelFound = false;
            }
            if (c instanceof JLabel && labelMatcher.matches(c)) {
                Component labelFor = ((JLabel) c).getLabelFor();
                if (labelFor != null) {
                    return labelFor.equals(component);
                } else {
                    labelFound = true;
                }
            }
        }
        return false;
    }

    void setLabelName(String name) {
        labelMatcher = JLabelMatcher.withName(name);
        this.labelName = name;
    }

    protected final void setLabelText(Object text) {
        if (labelMatcher == null) {
            labelMatcher = JLabelMatcher.any();
        }
        this.labelText = text;
        if (labelText instanceof String) {
            labelMatcher.andText((String) labelText);
        } else if (labelText instanceof Pattern) {
            labelMatcher.andText((Pattern) labelText);
        }
    }

    public Object quotedLabelName() {
        return quoted(labelName);
    }

    public Object quotedLabelText() {
        return quoted(labelText);
    }
}
