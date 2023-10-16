import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
import io.flutter.plugin.common.MethodChannel.Result;

public class MyPlugin {
    public static void registerWith(Registrar registrar) {
        final MethodChannel channel = new MethodChannel(registrar.messenger(), "my_plugin");
        channel.setMethodCallHandler(new MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall call, Result result) {
                System.out.println("Java code called");
                if (call.method.equals("getPlatformVersion")) {
                    result.success("Android " + android.os.Build.VERSION.RELEASE);
                } else {
                    result.notImplemented();
                }
            }
        });
    }
}
