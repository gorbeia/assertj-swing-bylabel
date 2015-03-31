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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.swing.test.builder.JTextFields.textField;

import org.assertj.swing.test.core.EDTSafeTestCase;
import org.junit.Test;

/**
 * Tests for {@link JLabeledTextComponentMatcher#matches(java.awt.Component)}.
 * We are looking for a text field with a label. The component to be matched
 * should have a parent.
 *
 * @author Iñaki Ibarrola
 */
public class JLabeledTextComponentMatcher_no_parent_Test extends EDTSafeTestCase {

    @Test
    public void should_Always_Return_False_When_JTextComponent_Has_No_Parent() {
        JLabeledTextComponentMatcher matcher = JLabeledTextComponentMatcher.withLabelText("hello");
        assertThat(matcher.matches(textField().createNew())).isFalse();
    }
}
