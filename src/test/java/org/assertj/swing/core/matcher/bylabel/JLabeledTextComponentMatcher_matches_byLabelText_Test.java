/**
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * Copyright 2012-2015 the original author or authors.
 */
package org.assertj.swing.core.matcher.bylabel;

import org.assertj.swing.test.builder.LabeledTextComponentWindow;
import static org.assertj.core.api.Assertions.assertThat;

import static org.assertj.swing.test.builder.LabeledTextComponentWindow.labeledTextField;

import org.assertj.swing.test.core.RobotBasedTestCase;
import org.junit.Test;

/**
 * Tests for {@link JTextComponentMatcher#matches(java.awt.Component)}.
 *
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
public class JLabeledTextComponentMatcher_matches_byLabelText_Test extends RobotBasedTestCase {

    @Test
    public void should_Return_True_If_Text_Is_Equal_To_Expected() {
        String text = "Hello";
        LabeledTextComponentWindow window = labeledTextField().withLabelText(text).createNew(getClass());
        JLabeledTextComponentMatcher matcher = JLabeledTextComponentMatcher.withLabelText(text);
        assertThat(matcher.matches(window.getTextField())).isTrue();
    }

    @Test
    public void should_Return_True_If_Text_Matches_Pattern() {
        LabeledTextComponentWindow window = labeledTextField().withLabelText("Bye").createNew(getClass());
        JLabeledTextComponentMatcher matcher = JLabeledTextComponentMatcher.withLabelText("Hello");
        assertThat(matcher.matches(window.getTextField())).isFalse();
    }

    @Test
    public void should_Return_False_If_Text_Is_Not_Equal_To_Expected() {
        LabeledTextComponentWindow window = labeledTextField().withLabelText("Bye").createNew(getClass());
        JLabeledTextComponentMatcher matcher = JLabeledTextComponentMatcher.withLabelText("He.*");
        assertThat(matcher.matches(window.getTextField())).isFalse();
    }
}
