package com.example.tetrio_app

import io.flutter.embedding.android.FlutterActivity
import android.app.Instrumentation
import android.view.KeyEvent
import android.os.Bundle
import io.flutter.plugin.common.MethodChannel
import io.flutter.plugins.GeneratedPluginRegistrant

class MainActivity: FlutterActivity() {
    private val CHANNEL = "native"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        provideFlutterEngine(this)?.let { GeneratedPluginRegistrant.registerWith(it) }
        MethodChannel(flutterEngine?.dartExecutor?.binaryMessenger!!, CHANNEL)?.setMethodCallHandler { call, result ->

            when (call.method) {
                "sendLeftEvent" -> sendLeftEvent()
                "sendRightEvent" -> sendRightEvent()
                "sendDownEvent" -> sendDownEvent()
                "sendLongLeftEvent" -> sendLongLeftEvent()
                "sendLongRightEvent" -> sendLongRightEvent()
                "sendLongDownEvent" -> sendLongDownEvent()
                "sendShiftEvent" -> sendShiftEvent()
                "sendSpaceBarEvent" -> sendSpaceBarEvent()
                "sendZEvent" -> sendZEvent()
                "sendXEvent" -> sendXEvent()
                "sendAEvent" -> sendAEvent()
                "sendREvent" -> sendREvent()
            }
        }
    }

    private fun sendLeftEvent() {
        Thread(Runnable{
            Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT)
        }).start()
    }

    private fun sendLongLeftEvent() {
        Thread(Runnable{
            for(i in 1..10) {
                Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_LEFT)
            }
        }).start()
    }

    private fun sendRightEvent() {
        Thread(Runnable{
            Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT)
        }).start()
    }

    private fun sendLongRightEvent() {
        Thread(Runnable{
            for(i in 1..10) {
                Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_RIGHT)
            }
        }).start()
    }

    private fun sendDownEvent() {
        Thread(Runnable{
            Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN)
        }).start()
    }

    private fun sendLongDownEvent() {
        Thread(Runnable{
            for (i in 1..10) {
                Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_DPAD_DOWN)
            }
        }).start()
    }

    private fun sendShiftEvent() {
        Thread(Runnable{
            Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_SHIFT_RIGHT)
        }).start()
    }

    private fun sendSpaceBarEvent() {
        Thread(Runnable{
            Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_SPACE)
        }).start()
    }

    private fun sendZEvent() {
        Thread(Runnable{
            Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_Z)
        }).start()
    }

    private fun sendXEvent() {
        Thread(Runnable{
            Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_X)
        }).start()
    }

    private fun sendAEvent() {
        Thread(Runnable{
            Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_A)
        }).start()
    }

    private fun sendREvent() {
        Thread(Runnable{
            Instrumentation().sendKeyDownUpSync(KeyEvent.KEYCODE_A)
        }).start()
    }
}
