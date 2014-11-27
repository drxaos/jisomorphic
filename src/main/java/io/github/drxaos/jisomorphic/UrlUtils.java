package io.github.drxaos.jisomorphic;

public class UrlUtils {

    public static class Params {
        String[] split;

        public Params(String params) {
            split = params.split("\\/");
        }


        public String getString(int paramIndex, String defaultValue) {
            String v = null;
            if (split.length > paramIndex) {
                v = split[paramIndex];
            }
            return v == null ? defaultValue : v;
        }

        public Integer getInt(int paramIndex, Integer defaultValue) {
            String v = null;
            if (split.length > paramIndex) {
                v = split[paramIndex];
            }
            try {
                return v == null ? defaultValue : Integer.parseInt(v);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }

        public Float getFloat(int paramIndex, Float defaultValue) {
            String v = null;
            if (split.length > paramIndex) {
                v = split[paramIndex];
            }
            try {
                return v == null ? defaultValue : Float.parseFloat(v);
            } catch (NumberFormatException e) {
                return defaultValue;
            }
        }

    }

    public static Params parseParams(String params) {
        return new Params(params);
    }

}
