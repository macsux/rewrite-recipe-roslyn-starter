package com.openrewrite.roslyn.recipe;

import org.openrewrite.NlsRewrite;
import org.openrewrite.Recipe;

public class SampleRecipe extends RoslynRecipe {
    @Override
    public String getRecipeId() {
        return "ORFX0001";
    }

    @Override
    public String getNugetPackageName() {
        return "";
    }

    @Override
    public String getNugetPackageVersion() {
        return "";
    }

    @Override
    public @NlsRewrite.DisplayName String getDisplayName() {
        return "";
    }

    @Override
    public @NlsRewrite.Description String getDescription() {
        return "";
    }
}
