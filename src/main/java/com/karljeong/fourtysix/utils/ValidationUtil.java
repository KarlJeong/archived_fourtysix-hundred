package com.karljeong.fourtysix.utils;

import java.util.List;
import java.util.Map;

public class ValidationUtil {
    public static Boolean isValidPageSize(int number, List<Map<String, Object>> articleNumberList) {
        for (Map<String, Object> obj : articleNumberList) {
            if (number == Integer.parseInt((String) obj.get("codeValue")))
            { return true; }
        }

        return false;
    }
}
