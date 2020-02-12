package com.vea.okhttp.plugin;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformOutputProvider;
import com.vea.common.transform.BaseTransform;
import com.vea.common.transform.RunVariant;

import bytecode.OkHttpWeaver;

import org.gradle.api.Project;

import java.io.IOException;
import java.util.Collection;

/**
 * @author Vea
 * @since 2019-02
 */
final class OkHttpHunterTransform extends BaseTransform {

    private Project project;
    private OkHttpHunterExtension okHttpHunterExtension;

    public OkHttpHunterTransform(Project project) {
        super(project);
        this.project = project;
        project.getExtensions().create("okHttpHunterExt", OkHttpHunterExtension.class);
        this.bytecodeWeaver = new OkHttpWeaver();
    }

    @Override
    public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        okHttpHunterExtension = (OkHttpHunterExtension) project.getExtensions().getByName("okHttpHunterExt");
        this.bytecodeWeaver.setExtension(okHttpHunterExtension);
        super.transform(context, inputs, referencedInputs, outputProvider, isIncremental);
    }

    @Override
    protected RunVariant getRunVariant() {
        return okHttpHunterExtension.runVariant;
    }

    @Override
    protected boolean inDuplcatedClassSafeMode() {
        return okHttpHunterExtension.duplcatedClassSafeMode;
    }
}
