package com.vea.plugin;

import com.android.build.gradle.AppExtension;

import org.gradle.api.Plugin;
import org.gradle.api.Project;

import java.util.Collections;

/**
 * @author Vea
 * @since 2019-12
 */
public class MethodTimePlugin implements Plugin<Project> {
    @Override
    public void apply(Project project) {
        AppExtension appExtension = (AppExtension) project.getProperties().get("android");
        appExtension.registerTransform(new MethodTimeTransform(project), Collections.EMPTY_LIST);
    }
}
