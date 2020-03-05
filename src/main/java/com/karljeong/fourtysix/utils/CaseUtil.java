package com.karljeong.fourtysix.utils;

import com.google.common.base.CaseFormat;

public class CaseUtil {

    public static String convertCamelCaseToUnderScore(String str) {
        return CaseFormat.UPPER_CAMEL.to(CaseFormat.UPPER_UNDERSCORE, str);
    }

    public static String convertUnderScoreToCamelCase(String str) {
        return CaseFormat.UPPER_UNDERSCORE.to(CaseFormat.UPPER_CAMEL, str);
    }
}
