package bytecode;

import com.vea.common.transform.ams.BaseWeaver;
import com.vea.plugin.TimeExtension;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

import java.util.logging.Logger;

/**
 * @author Vea
 * @since 2019-01
 */
public class TimingWeaver extends BaseWeaver {
    private static final String PLUGIN_LIBRARY = "com.vea.ams.lib.time";
    private TimeExtension timeExtension;
//    private Logger mLogger = Logger.getLogger(TimingWeaver.class.getSimpleName());

    @Override
    public void setExtension(Object extension) {
        if (extension == null) return;
        this.timeExtension = (TimeExtension) extension;
    }

    @Override
    public boolean isWeavableClass(String fullQualifiedClassName) {
        boolean superResult = super.isWeavableClass(fullQualifiedClassName);
        boolean isByteCodePlugin = fullQualifiedClassName.startsWith(PLUGIN_LIBRARY);
//        mLogger.warning(fullQualifiedClassName);
        if (timeExtension != null) {
            //whitelist is prior to to blacklist
            if (!timeExtension.whitelist.isEmpty()) {
                boolean inWhiteList = false;
                for (String item : timeExtension.whitelist) {
                    if (fullQualifiedClassName.contains(item)) {
                        inWhiteList = true;
                    }
                }
                return superResult && !isByteCodePlugin && inWhiteList;
            }
            if (!timeExtension.blacklist.isEmpty()) {
                boolean inBlackList = false;
                for (String item : timeExtension.blacklist) {
                    if (fullQualifiedClassName.startsWith(item)) {
                        inBlackList = true;
                    }
                }
                return superResult && !isByteCodePlugin && !inBlackList;
            }
        }
        return superResult && !isByteCodePlugin;
    }

    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new TimingClassAdapter(classWriter);
    }
}
