package com.vea.plugin;


import com.vea.common.transform.RunVariant;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Vea
 * @since 2019-01
 */
public class TimeExtension {

    public RunVariant runVariant = RunVariant.ALWAYS;
    public List<String> whitelist = new ArrayList<>();
    public List<String> blacklist = new ArrayList<>();
    public boolean duplcatedClassSafeMode = false;

    @Override
    public String toString() {
        return "TimingHunterExtension{" +
            "runVariant=" + runVariant +
            ", whitelist=" + whitelist +
            ", blacklist=" + blacklist +
            ", duplcatedClassSafeMode=" + duplcatedClassSafeMode +
            '}';
    }
}
