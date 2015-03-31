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

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.util.Lists.newArrayList;
import static org.assertj.swing.test.builder.JTextFields.textField;

import java.util.Collection;

import javax.swing.JTextField;

import org.assertj.swing.test.core.EDTSafeTestCase;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

/**
 * Tests for {@link JLabeledTextComponentMatcher#matches(java.awt.Component)}.
 * 
 * @author Alex Ruiz
 * @author Yvonne Wang
 */
@RunWith(Parameterized.class)
public class JLabeledTextComponentMatcher_matches_byNameAndText_withNoMatch_Test extends EDTSafeTestCase {
  private final String name;
  private final String text;

  @Parameters
  public static Collection<Object[]> namesAndText() {
    return newArrayList(new Object[][] { { "someName", "text" }, { "name", "someText" }, { "name", "text" } });
  }

  public JLabeledTextComponentMatcher_matches_byNameAndText_withNoMatch_Test(String name, String text) {
    this.name = name;
    this.text = text;
  }

  @Test
  public void should_Return_False_If_Name_Or_Text_Are_Not_Equal_To_Expected() {
    JLabeledTextComponentMatcher matcher = JLabeledTextComponentMatcher.withName(name).andText(text);
    JTextField textField = textField().withName("someName").withText("someText").createNew();
    assertThat(matcher.matches(textField)).isFalse();
  }
}
