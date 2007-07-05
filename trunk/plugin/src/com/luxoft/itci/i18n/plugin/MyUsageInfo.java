package com.luxoft.itci.i18n.plugin;

import com.intellij.usageView.UsageInfo;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiReference;
import com.intellij.psi.PsiJavaCodeReferenceElement;

public class MyUsageInfo extends UsageInfo {
    private String tooltip;
    public MyUsageInfo(@org.jetbrains.annotations.NotNull PsiElement element, int startOffset, int endOffset, boolean isNonCodeUsage) {
        super(element, startOffset, endOffset, isNonCodeUsage);
    }

    public MyUsageInfo(@org.jetbrains.annotations.NotNull PsiElement element, boolean isNonCodeUsage) {
        super(element, isNonCodeUsage);
    }

    public MyUsageInfo(@org.jetbrains.annotations.NotNull PsiElement element, int startOffset, int endOffset) {
        super(element, startOffset, endOffset);
    }

    public MyUsageInfo(@org.jetbrains.annotations.NotNull PsiReference reference) {
        super(reference);
    }

    public MyUsageInfo(@org.jetbrains.annotations.NotNull PsiJavaCodeReferenceElement reference) {
        super(reference);
    }

    public MyUsageInfo(@org.jetbrains.annotations.NotNull PsiElement element) {
        super(element);
    }

    public void setTooltipText(String tooltip){
        this.tooltip = tooltip;
    }

    public String getTooltipText() {
        return tooltip;
    }
}
