package com.vea.ams.lib.time;


/**
 * @author Vea
 * @since 2019-01
 */
public class BlockManager {
    private static IBlockHandler iBlockHandler = IBlockHandler.DEFAULT;

    public static void installBlockManager(IBlockHandler custom) {
        BlockManager.iBlockHandler = custom;
    }

    public static void timingMethod(String method, long cost) {
        iBlockHandler.timingMethod(method, (int) cost);
    }
}
