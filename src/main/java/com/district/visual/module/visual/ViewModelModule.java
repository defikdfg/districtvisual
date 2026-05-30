package com.district.visual.module.visual;

import com.district.visual.module.Module;

public class ViewModelModule extends Module {
    public static float posX = 0.0f;
    public static float posY = 0.0f;
    public static float posZ = 0.0f;
    public static float rotX = 0.0f;
    public static float rotY = 0.0f;
    public static float rotZ = 0.0f;
    public static float scale = 1.0f;
    public static float fov = 70.0f;

    public ViewModelModule() {
        super("ViewModel", "Adjust hand position, rotation and FOV", Category.VISUAL);
        enabled = true;
    }
}
