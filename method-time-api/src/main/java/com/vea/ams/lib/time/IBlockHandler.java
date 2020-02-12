package com.vea.ams.lib.time;

import android.util.Log;

/**
 * @author Vea
 * @since 2019-01
 */
public interface IBlockHandler {

    IBlockHandler DEFAULT = new IBlockHandler() {

        private static final String TAG = "MethodTime";

        private static final int BLOCK_THRESHOLD = 50;

        @Override
        public void timingMethod(String method, int cost) {
            if(cost >= threshold()) {
                Log.i(TAG, method + " costed " + cost);
            }
        }

        @Override
        public String dump() {
            return "";
        }

        @Override
        public void clear() {

        }

        @Override
        public int threshold() {
            return BLOCK_THRESHOLD;
        }
    };

    public void timingMethod(String method, int cost);

    public String dump();

    public void clear();

    public int threshold();

}
