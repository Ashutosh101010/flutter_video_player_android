
package io.flutter.plugins.videoplayer;

import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;
import io.flutter.plugin.common.PluginRegistry;
import io.flutter.plugin.common.PluginRegistry.Registrar;

public class MyPlugin implements FlutterPlugin, MethodCallHandler {
    private MethodChannel channel;

    public static void registerWith(Registrar registrar) {
        MyPlugin plugin = new MyPlugin();
        plugin.setupMethodChannel(registrar.messenger());
    }

    private void setupMethodChannel(BinaryMessenger messenger) {
        channel = new MethodChannel(messenger, "my_plugin");
        channel.setMethodCallHandler(this);
    }

    @Override
    public void onMethodCall(MethodCall call, Result result) {
        System.out.println("Hello From Java");
        if (call.method.equals("getPlatformVersion")) {
            result.success("Android " + android.os.Build.VERSION.RELEASE);
        } else {
            result.notImplemented();
        }
    }

    @Override
    public void onAttachedToEngine(FlutterPluginBinding binding) {
        setupMethodChannel(binding.getBinaryMessenger());
    }

    @Override
    public void onDetachedFromEngine(FlutterPluginBinding binding) {
        channel.setMethodCallHandler(null);
    }
}
