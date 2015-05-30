package com.kalessil.phpStorm.phpInspectionsEA.inspectors.regularExpressions.classesStrategy;

import com.intellij.codeInspection.ProblemHighlightType;
import com.intellij.codeInspection.ProblemsHolder;
import com.intellij.openapi.util.text.StringUtil;
import com.jetbrains.php.lang.psi.elements.StringLiteralExpression;
import org.jetbrains.annotations.NotNull;

import java.util.HashMap;

public class ShortClassDefinitionStrategy {
    private static final String strProblemDescription = "'%p%' can be replaced with '%r%'";

    private static HashMap<String, String> mapping = null;
    private static HashMap<String, String> getMapping() {
        if (null == mapping) {
            mapping = new HashMap<String, String>();

            mapping.put("[0-9]",         "\\d");
            mapping.put("[:digit:]",     "\\d");

            mapping.put("[^0-9]",        "\\D");
            mapping.put("[^\\d]",        "\\D");

            mapping.put("[:word:]",      "\\w");
            mapping.put("[A-Za-z0-9_]",  "\\w");

            mapping.put("[^\\w]",        "\\W");
            mapping.put("[^A-Za-z0-9_]", "\\W");
            mapping.put("[^a-zA-Z0-9_]", "\\W");

            mapping.put("[^\\s]",        "\\S");
        }

        return mapping;
    }

    static public void apply(final String pattern, @NotNull final StringLiteralExpression target, @NotNull final ProblemsHolder holder) {
        if (!StringUtil.isEmpty(pattern)) {
            HashMap<String, String> mapping = getMapping();
            for (String wildcard : mapping.keySet()) {
                if (pattern.contains(wildcard)) {
                    String strError = strProblemDescription
                            .replace("%p%", wildcard)
                            .replace("%r%", mapping.get(wildcard));
                    holder.registerProblem(target, strError, ProblemHighlightType.WEAK_WARNING);
                }
            }
        }
    }
}