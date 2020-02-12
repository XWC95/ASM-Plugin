package com.vea.okhttp.plugin;

import com.vea.common.transform.RunVariant;

/**
 * @author Vea
 * @since 2019-02
 */
public class OkHttpHunterExtension {

    public RunVariant runVariant = RunVariant.ALWAYS;
    public boolean weaveEventListener = true;
    public boolean duplcatedClassSafeMode = false;

    @Override
    public String toString() {
        return "OkHttpHunterExtension{" +
                "runVariant=" + runVariant +
                ", weaveEventListener=" + weaveEventListener +
                ", duplcatedClassSafeMode=" + duplcatedClassSafeMode +
                '}';
    }

}
