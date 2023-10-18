// package io.flutter.plugins.videoplayer;

// import android.content.Context;
// import android.os.Bundle;
// import android.view.LayoutInflater;
// import android.view.View;
// import android.view.ViewGroup;

// import io.flutter.embedding.engine.FlutterEngine;
// import io.flutter.plugin.common.MethodCall;
// import io.flutter.plugin.common.MethodChannel.MethodCallHandler;
// import io.flutter.plugin.common.MethodChannel;
// import io.flutter.embedding.android.FlutterFragment;

// public class MyPlugin extends FlutterFragment {

//     private FlutterEngine flutterEngine;
//     private MethodChannel methodChannel;

//     @Override
//     public void onCreate(@Nullable Bundle savedInstanceState) {
//         super.onCreate(savedInstanceState);

//         flutterEngine = new FlutterEngine(getContext());

//         methodChannel = new MethodChannel(flutterEngine.getDartExecutor().getBinaryMessenger(), "my_channel");
//         methodChannel.setMethodCallHandler(new MethodCallHandler() {
//             @Override
//             public void onMethodCall(MethodCall call, MethodChannel.Result result) {
//                 // Handle the method call from Flutter.
//                 System.out.println("Method call from Flutter: " + call.method);
//             }
//         });
//     }

//     @Override
//     public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
//             @Nullable Bundle savedInstanceState) {
//         FlutterView flutterView = (FlutterView) inflater.inflate(R.layout.fragment_flutter, container, false);
//         flutterView.attachToFlutterEngine(flutterEngine);

//         return flutterView;
//     }
// }
