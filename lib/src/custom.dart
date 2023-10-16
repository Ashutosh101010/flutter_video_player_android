import 'package:flutter/services.dart';

class CustomClass {
  static const platform = const MethodChannel('my_plugin');

  Future<void> myMethod() async {
    try {
      final result = await platform.invokeMethod('my_method');
      print(result);
    } on PlatformException catch (e) {
      print("Failed to invoke method: '${e.message}'.");
    }
  }
}
