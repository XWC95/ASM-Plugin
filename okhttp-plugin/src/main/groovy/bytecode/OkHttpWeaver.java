package bytecode;

import com.vea.common.transform.ams.BaseWeaver;
import com.vea.okhttp.plugin.OkHttpHunterExtension;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.ClassWriter;

/**
 * @author Vea
 * @since 2019-02
 */
public final class OkHttpWeaver extends BaseWeaver {
    private OkHttpHunterExtension okHttpHunterExtension;

    @Override
    public void setExtension(Object extension) {
        if (extension == null) return;
        this.okHttpHunterExtension = (OkHttpHunterExtension) extension;
    }

    @Override
    protected ClassVisitor wrapClassWriter(ClassWriter classWriter) {
        return new OkHttpClassAdapter(classWriter, this.okHttpHunterExtension.weaveEventListener);
    }
}
