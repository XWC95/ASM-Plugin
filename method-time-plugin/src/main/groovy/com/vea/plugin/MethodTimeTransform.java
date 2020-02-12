package com.vea.plugin;

import com.android.build.api.transform.Context;
import com.android.build.api.transform.TransformException;
import com.android.build.api.transform.TransformInput;
import com.android.build.api.transform.TransformOutputProvider;
import com.vea.common.transform.BaseTransform;
import com.vea.common.transform.RunVariant;

import org.gradle.api.Project;

import java.io.IOException;
import java.util.Collection;

import bytecode.TimingWeaver;

/**
 * @author Vea
 * @since 2019-12
 */
class MethodTimeTransform extends BaseTransform {
    private Project project;
    private TimeExtension timeExtension;

    public MethodTimeTransform(Project project) {
        super(project);
        this.project = project;
        project.getExtensions().create("TimeExt", TimeExtension.class);
        this.bytecodeWeaver = new TimingWeaver();
    }

    @Override
    public void transform(Context context, Collection<TransformInput> inputs, Collection<TransformInput> referencedInputs, TransformOutputProvider outputProvider, boolean isIncremental) throws IOException, TransformException, InterruptedException {
        timeExtension = (TimeExtension) project.getExtensions().getByName("TimeExt");
        bytecodeWeaver.setExtension(timeExtension);
        super.transform(context, inputs, referencedInputs, outputProvider, isIncremental);
    }

    protected RunVariant getRunVariant() {
        return timeExtension.runVariant;
    }

    @Override
    protected boolean inDuplcatedClassSafeMode() {
        return timeExtension.duplcatedClassSafeMode;
    }
}
