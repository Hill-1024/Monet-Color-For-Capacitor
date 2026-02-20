package com.monet.plugins.example;

import android.content.Context;
import android.os.Build;
import com.getcapacitor.JSObject;
import com.getcapacitor.Plugin;
import com.getcapacitor.PluginCall;
import com.getcapacitor.PluginMethod;
import com.getcapacitor.annotation.CapacitorPlugin;

@CapacitorPlugin(name = "MonetColor")
public class MonetColorPlugin extends Plugin {

    @PluginMethod
    public void getColors(PluginCall call) {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.S) {
            call.reject("Monet colors require Android 12 (API 31) or higher.");
            return;
        }

        try {
            Context context = getContext();
            JSObject ret = new JSObject();

            ret.put("accent1", getTonalPalette(context, "system_accent1_"));
            ret.put("accent2", getTonalPalette(context, "system_accent2_"));
            ret.put("accent3", getTonalPalette(context, "system_accent3_"));
            ret.put("neutral1", getTonalPalette(context, "system_neutral1_"));
            ret.put("neutral2", getTonalPalette(context, "system_neutral2_"));

            call.resolve(ret);
        } catch (Exception e) {
            call.reject("Failed to get Monet colors: " + e.getMessage());
        }
    }

    private JSObject getTonalPalette(Context context, String prefix) {
        JSObject palette = new JSObject();
        int[] shades = {0, 10, 50, 100, 200, 300, 400, 500, 600, 700, 800, 900, 1000};

        for (int shade : shades) {
            String resourceName = prefix + shade;
            int colorId = context.getResources().getIdentifier(resourceName, "color", "android");
            
            if (colorId != 0) {
                int color = context.getResources().getColor(colorId, context.getTheme());
                String hex = String.format("#%06X", (0xFFFFFF & color));
                palette.put(String.valueOf(shade), hex);
            }
        }
        return palette;
    }
}
