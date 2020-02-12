package bytecode;

import org.objectweb.asm.ClassVisitor;
import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;

import java.io.File;
import java.util.Arrays;

/**
 * ClassVisitor：主要负责“拜访”类或成员信息。
 * 其中包括标记在类上的注解，类的构造方法，类的字段，类的方法。静态代码块。
 *
 * @author Vea
 * @since 2019-01
 */
public class TimingClassAdapter extends ClassVisitor {
    private String className;
    private boolean isHeritedFromBlockHandler = false;

    public TimingClassAdapter(ClassVisitor cv) {
        super(Opcodes.ASM5, cv);
    }


    // visit 方法：该方法是当扫描类时第一个拜访的方法
    @Override
    public void visit(int version, int access, String name, String signature, String superName, String[] interfaces) {
        super.visit(version, access, name, signature, superName, interfaces);
        this.isHeritedFromBlockHandler = Arrays.toString(interfaces).contains("com/vea/ams/lib/time/IBlockHandler");
        this.className = name;
    }
    // visitMethod 该方法是当扫描器扫描到类的方法时进行调用
    @Override
    public MethodVisitor visitMethod(final int access, final String name,
        final String desc, final String signature, final String[] exceptions) {
        MethodVisitor mv = cv.visitMethod(access, name, desc, signature, exceptions);
        if (isHeritedFromBlockHandler) {
            return mv;
        } else {
            return mv == null ? null : new TimingMethodAdapter(className + File.separator + name, access, desc, mv);
        }
    }
}
