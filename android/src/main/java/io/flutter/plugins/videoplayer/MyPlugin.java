package io.flutter.plugins.videoplayer;

import androidx.annotation.NonNull;
import io.flutter.embedding.android.FlutterActivity;
import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodChannel;

public class MyPlugin extends FlutterActivity {
    private static final String CHANNEL = "custom_channel";
    private static final String CHANNEL2 = "my_plugin";

    private static String secretKey ;

    @Override
    public void configureFlutterEngine(@NonNull FlutterEngine flutterEngine) {
        super.configureFlutterEngine(flutterEngine);
        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(),CHANNEL2).setMethodCallHandler((call , result)-> {
            System.out.println("Hello World from Java");
            if(call.method.equals("getPlatformVersion")){
                System.out.println("new Channel In Java");
            }else{
                result.notImplemented();
            }
        });

        new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), CHANNEL)
                .setMethodCallHandler(
                        (call, result) -> {
                            // This method is invoked on the main thread.
                            // TODO
                            if(call.method.equals("java_method")){
                                String secretKey = call.argument("secretKey");
                                System.out.println("Hello From Java");
                                saveSecretKey(secretKey);
                                result.success("Success");
                            }else {
                                result.notImplemented();
                                System.out.println("Error in Java Code");
                            }


                        }
                );
    }

    private void saveSecretKey (String key) {
        secretKey = key;
        System.out.println("Secret Key set Successfully : "+getKey());

    }

    public String getKey () {
        return secretKey;
    }
}