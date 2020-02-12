package com.vea.okhttp.plugin;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Collections;

/**
 * @author Vea
 * @since 2019-02
 */
public final class OkHttpHunterPlugin implements Plugin<Project> {

    @SuppressWarnings("NullableProblems")
    @Override
    public void apply(Project project) {
        AppExtension appExtension = (AppExtension)project.getProperties().get("android");
        appExtension.registerTransform(new OkHttpHunterTransform(project), Collections.EMPTY_LIST);
    }

}
