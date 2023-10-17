import io.flutter.embedding.engine.FlutterEngine;
import io.flutter.plugin.common.MethodCall;
import io.flutter.plugin.common.MethodChannel;
import io.flutter.plugin.common.MethodChannel.MethodCallHandler;

public class MyPlugin {

    private final FlutterEngine flutterEngine;
    private final MethodChannel methodChannel;

    public MyPlugin(Context context) {
        flutterEngine = new FlutterEngine(context);
        methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "my_channel");

        methodChannel.setMethodCallHandler(new MethodCallHandler() {
            @Override
            public void onMethodCall(MethodCall call, MethodChannel.Result result) {
                // Handle the method call from Flutter.
                System.out.println("Method call from Flutter: " + call.method);
                if (call.method.equals("myMethod")) {
                    // Invoke the method here.
                    System.out.println("Successfully invoked myMethod");
                    result.success("Hello from Android");
                } else {
                    System.out.println("Method not implemented");
                    result.notImplemented();
                }
            }
        });
    }

    public void invokeMethod(String methodName, Object arguments) {
        methodChannel.invokeMethod(methodName, arguments);
    }
}
