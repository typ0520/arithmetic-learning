package com.example;

import java.util.HashMap;
import java.util.Map;

public class biao_shi_shu_zhi_de_zi_fu_chuan_by_leetcode_soluti {
    public static void main(String[] args) {
        Map<String, Boolean> testCase = new HashMap<>();
        testCase.put("+100", true);
        testCase.put("5e2", true);
        testCase.put("-123", true);
        testCase.put("3.1416", true);
        testCase.put("-1E-16", true);
        testCase.put("0123", true);

        testCase.put("12e", false);
        testCase.put("1a3.14", false);
        testCase.put("1.2.3", false);
        testCase.put("+-5", false);
        testCase.put("12e+5.4", false);

        for (Map.Entry<String, Boolean> entry : testCase.entrySet()) {
            boolean result = isNumber(entry.getKey());
            if (result != entry.getValue()) {
                System.out.println(entry.getKey() + ", expect: " + entry.getValue() + " actual: " + result);
            }
        }
    }

    //https://leetcode-cn.com/problems/biao-shi-shu-zhi-de-zi-fu-chuan-lcof/solution/biao-shi-shu-zhi-de-zi-fu-chuan-by-leetcode-soluti/
    public static boolean isNumber(String s) {
        Map<State, Map<CharType, State>> transfer = new HashMap<>();
        Map<CharType, State> initialMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_SPACE, State.STATE_INITIAL);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
            put(CharType.CHAR_SIGN, State.STATE_INT_SIGN);
        }};
        transfer.put(State.STATE_INITIAL, initialMap);

        Map<CharType, State> intSignMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_POINT, State.STATE_POINT_WITHOUT_INT);
        }};
        transfer.put(State.STATE_INT_SIGN, intSignMap);

        Map<CharType, State> integerMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_POINT, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_NUMBER, State.STATE_INTEGER);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_INTEGER, integerMap);

        Map<CharType, State> pointMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_SPACE, State.STATE_END);
            put(CharType.CHAR_EXP, State.STATE_END);
        }};
        transfer.put(State.STATE_POINT, pointMap);

        Map<CharType, State> pointWithoutIntMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
        }};
        transfer.put(State.STATE_POINT_WITHOUT_INT, pointWithoutIntMap);

        Map<CharType, State> fractionMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_NUMBER, State.STATE_FRACTION);
            put(CharType.CHAR_EXP, State.STATE_EXP);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_FRACTION, fractionMap);

        Map<CharType, State> expMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_SIGN, State.STATE_EXP_SIGN);
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP, expMap);

        Map<CharType, State> expSignMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
        }};
        transfer.put(State.STATE_EXP_SIGN, expSignMap);

        Map<CharType, State> expNumberMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_NUMBER, State.STATE_EXP_NUMBER);
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_EXP_NUMBER, expNumberMap);

        Map<CharType, State> endMap = new HashMap<CharType, State>(){{
            put(CharType.CHAR_SPACE, State.STATE_END);
        }};
        transfer.put(State.STATE_END, endMap);

        int length = s.length();
        State state = State.STATE_INITIAL;
        for (int i = 0; i < length; i++) {
            CharType charType = toCharType(s.charAt(i));
            if (!transfer.get(state).containsKey(charType)) {
                return false;
            } else {
                state = transfer.get(state).get(charType);
            }
        }
        return state == State.STATE_INTEGER || state == State.STATE_POINT || state == State.STATE_FRACTION || state == State.STATE_EXP_NUMBER || state == State.STATE_END;
    }

    public static CharType toCharType(char c) {
        if (c == ' ') {
            return CharType.CHAR_SPACE;
        } else if (c == '-' || c == '+') {
            return CharType.CHAR_SIGN;
        } else if (c >= '0' && c <= '9') {
            return CharType.CHAR_NUMBER;
        } else if (c == '.') {
            return CharType.CHAR_POINT;
        } else if (c == 'e' || c == 'E') {
            return CharType.CHAR_EXP;
        } else {
            return CharType.CHAR_ILLEGAL;
        }
    }

    public enum State {
        STATE_INITIAL,
        STATE_INT_SIGN,
        STATE_INTEGER,
        STATE_POINT,
        STATE_POINT_WITHOUT_INT,
        STATE_FRACTION,
        STATE_EXP,
        STATE_EXP_SIGN,
        STATE_EXP_NUMBER,
        STATE_END
    }

    public enum CharType {
        CHAR_SPACE,
        CHAR_SIGN,
        CHAR_NUMBER,
        CHAR_POINT,
        CHAR_EXP,
        CHAR_ILLEGAL
    }
}
