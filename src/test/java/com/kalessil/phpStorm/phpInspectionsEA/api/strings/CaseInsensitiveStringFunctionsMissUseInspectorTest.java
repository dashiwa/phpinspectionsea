package com.kalessil.phpStorm.phpInspectionsEA.api.strings;

import com.kalessil.phpStorm.phpInspectionsEA.PhpCodeInsightFixtureTestCase;
import com.kalessil.phpStorm.phpInspectionsEA.inspectors.apiUsage.strings.CaseInsensitiveStringFunctionsMissUseInspector;

final public class CaseInsensitiveStringFunctionsMissUseInspectorTest extends PhpCodeInsightFixtureTestCase {
    public void testIfFindsAllPatterns() {
        myFixture.enableInspections(new CaseInsensitiveStringFunctionsMissUseInspector());

        myFixture.configureByFile("fixtures/api/strings/str-i-functions.php");
        myFixture.testHighlighting(true, false, true);
    }
}