package bytecode;

import org.objectweb.asm.MethodVisitor;
import org.objectweb.asm.Opcodes;
import org.objectweb.asm.commons.LocalVariablesSorter;

/**
 * @author Vea
 * @since 2019-02
 */
public final class OkHttpMethodAdapter extends LocalVariablesSorter implements Opcodes {
    private boolean defaultOkhttpClientBuilderInitMethod = false;
    private boolean weaveEventListener;

    OkHttpMethodAdapter(String name, int access, String desc, MethodVisitor mv, boolean weaveEventListener) {
        super(Opcodes.ASM5, access, desc, mv);
        if ("okhttp3/OkHttpClient$Builder\\<init>".equals(name) && "()V".equals(desc)) {
            defaultOkhttpClientBuilderInitMethod = true;
        }
        this.weaveEventListener = weaveEventListener;
    }

    @Override
    public void visitInsn(int opcode) {
        if (defaultOkhttpClientBuilderInitMethod) {
            if ((opcode >= IRETURN && opcode <= RETURN) || opcode == ATHROW) {
                //EventListenFactory
                if (weaveEventListener) {
                    mv.visitVarInsn(ALOAD, 0);
                    mv.visitFieldInsn(GETSTATIC, "com/vea/okhttp/api/OkHttpHooker", "globalEventFactory", "Lokhttp3/EventListener$Factory;");
                    mv.visitFieldInsn(PUTFIELD, "okhttp3/OkHttpClient$Builder", "eventListenerFactory", "Lokhttp3/EventListener$Factory;");
                }

                //Dns
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETSTATIC, "com/vea/okhttp/api/OkHttpHooker", "globalDns", "Lokhttp3/Dns;");
                mv.visitFieldInsn(PUTFIELD, "okhttp3/OkHttpClient$Builder", "dns", "Lokhttp3/Dns;");

                //Interceptor
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETFIELD, "okhttp3/OkHttpClient$Builder", "interceptors", "Ljava/util/List;");
                mv.visitFieldInsn(GETSTATIC, "com/vea/okhttp/api/OkHttpHooker", "globalInterceptors", "Ljava/util/List;");
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "addAll", "(Ljava/util/Collection;)Z", true);
                mv.visitInsn(POP);

                //NetworkInterceptor
                mv.visitVarInsn(ALOAD, 0);
                mv.visitFieldInsn(GETFIELD, "okhttp3/OkHttpClient$Builder", "networkInterceptors", "Ljava/util/List;");
                mv.visitFieldInsn(GETSTATIC, "com/vea/okhttp/api/OkHttpHooker", "globalNetworkInterceptors", "Ljava/util/List;");
                mv.visitMethodInsn(INVOKEINTERFACE, "java/util/List", "addAll", "(Ljava/util/Collection;)Z", true);
                mv.visitInsn(POP);
            }
        }
        super.visitInsn(opcode);
    }
}
