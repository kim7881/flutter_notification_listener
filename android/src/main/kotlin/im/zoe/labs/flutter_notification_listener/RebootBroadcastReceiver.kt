package im.zoe.labs.flutter_notification_listener

import android.content.BroadcastReceiver
import android.content.ComponentName
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.util.Log


class RebootBroadcastReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            Intent.ACTION_REBOOT, Intent.ACTION_BOOT_COMPLETED -> {
                Log.i("NotificationListener", "Registering notification listener, after reboot!")
                FlutterNotificationListenerPlugin.registerAfterReboot(context)

                val intent = Intent(context, NotificationsHandlerService::class.java)
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                    context.startForegroundService(intent)
                } else {
                    context.startService(intent)
                }

            }
            else -> {
                Log.i("NotificationListener", intent.action.toString())
            }
        }
    }
}