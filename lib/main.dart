import 'package:flutter/material.dart';
import 'package:webview_flutter/webview_flutter.dart';
import 'package:flutter/services.dart';

void main() {
  runApp(const AppRoot());
}

class AppRoot extends StatelessWidget {
  const AppRoot({Key? key}) : super(key: key);

  // This widget is the root of your application.
  @override
  Widget build(BuildContext context) {
    SystemChrome.setPreferredOrientations(
        [DeviceOrientation.landscapeLeft]); // 가로모드 고정

    return MaterialApp(
      title: 'Flutter Demo',
      theme: ThemeData(
        primarySwatch: Colors.blue,
      ),
      home: const WebAppPage(title: 'Flutter Demo Home Page'),
    );
  }
}

class WebAppPage extends StatefulWidget {
  const WebAppPage({Key? key, required this.title}) : super(key: key);

  final String title;

  @override
  State<WebAppPage> createState() => _WebAppPageState();
}

class _WebAppPageState extends State<WebAppPage> {
  static const platform = const MethodChannel('native');

  WebViewController? webViewController = null;

  @override
  Widget build(BuildContext context) {
    var webView = WebView(
      initialUrl: 'https://tetr.io',
      javascriptMode: JavascriptMode.unrestricted,
      onWebViewCreated: (WebViewController controller) {
        webViewController = controller;
      },
    );

    var foo = Container();

    var buttonStyle = ElevatedButton.styleFrom(
        textStyle: const TextStyle(
            fontSize: 20, color: Color.fromARGB(240, 248, 255, 0)));

    var zButton = ElevatedButton(
      onPressed: () async {
        await platform.invokeMethod('sendZEvent');
      },
      child: const Text('Z'),
      style: buttonStyle,
    );

    var xButton = ElevatedButton(
      onPressed: () async {
        await platform.invokeMethod('sendXEvent');
      },
      child: const Text('X'),
      style: buttonStyle,
    );

    var leftButton = ElevatedButton(
      onPressed: () async {
        await platform.invokeMethod('sendLeftEvent');
      },
      /* onLongPress: () async {
        await platform.invokeMethod('sendLongLeftEvent');
      },*/
      child: Text('<'),
      style: buttonStyle,
    );
    var rightButton = ElevatedButton(
      onPressed: () async {
        print("!!! 눌림");
        await platform.invokeMethod('sendRightEvent');
      },
      onLongPress: () async {
        await platform.invokeMethod('sendLongRightEvent');
      },
      child: Text('>'),
      style: buttonStyle,
    );

    var holdButton = ElevatedButton(
      onPressed: () async {
        await platform.invokeMethod('sendShiftEvent');
      },
      child: Text('HOLD'),
      style: buttonStyle,
    );

    var softDropButton = ElevatedButton(
      onPressed: () async {
        await platform.invokeMethod('sendDownEvent');
      },
      onLongPress: () async {
        await platform.invokeMethod('sendLongDownEvent');
      },
      child: Text('SOFT'),
      style: buttonStyle,
    );

    var hardDropButton = ElevatedButton(
      onPressed: () async {
        await platform.invokeMethod('sendSpaceBarEvent');
      },
      child: Text('HARD'),
      style: buttonStyle,
    );

    var buttons = Column(
      mainAxisAlignment: MainAxisAlignment.center,
      children: [
        Row(
          children: [zButton, xButton],
        ),
        Row(
          children: [leftButton, rightButton],
        ),
        Row(
          children: [holdButton, softDropButton, hardDropButton],
        ),
      ],
    );

    return Scaffold(body: Stack(children: [webView, buttons]));
  }
}
