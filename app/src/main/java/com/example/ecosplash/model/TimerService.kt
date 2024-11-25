package com.example.ecosplash.model

import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import kotlinx.coroutines.*

class TimerService : Service() {

    private var timerJob: Job? = null
    private var remainingTime: Long = 0L
    private var isRunning: Boolean = false

    companion object {
        const val CHANNEL_ID = "TimerServiceChannel"
        const val NOTIFICATION_ID = 1
        const val ACTION_START = "ACTION_START"
        const val ACTION_STOP = "ACTION_STOP"
        const val EXTRA_TIME = "EXTRA_TIME"
    }

    override fun onCreate() {
        super.onCreate()
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            createNotificationChannel()
        }
    }

    override fun onStartCommand(intent: Intent?, flags: Int, startId: Int): Int {
        when (intent?.action) {
            ACTION_START -> {
                if (!isRunning) {
                    remainingTime = intent.getLongExtra(EXTRA_TIME, 1200000L)
                    startForegroundService()
                    startTimer()
                }
            }
            ACTION_STOP -> stopService()
        }
        return START_NOT_STICKY
    }

    private fun startForegroundService() {
        val notification = createNotification("El temporizador ha comenzado.", "Tiempo restante: ${formatTime(remainingTime)}")
        startForeground(NOTIFICATION_ID, notification)
    }

    private fun startTimer() {
        isRunning = true
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            while (isRunning && remainingTime > 0) {
                delay(1000L)
                remainingTime -= 1000L

                sendBroadcast(Intent("TIMER_UPDATED").apply {
                    putExtra("remaining_time", remainingTime)
                })

                if (remainingTime == 960000L) {
                    sendNotification("¡Apresúrate!", "Te queda un minuto para registrar una ducha corta")
                }

                if (remainingTime <= 0) {
                    sendNotification("El tiempo terminó", "Hoy no ahorraste agua, ¡pero mañana puedes lograrlo!")
                    stopService()
                }
            }
        }
    }

    private fun stopService() {
        isRunning = false
        timerJob?.cancel()

        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.cancel(NOTIFICATION_ID)

        stopSelf()
    }

    private fun sendNotification(title: String, body: String) {
        val notification = createNotification(title, body)
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotification(title: String, body: String): Notification {
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle(title)
            .setContentText(body)
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setOngoing(isRunning)
            .build()
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val channel = NotificationChannel(
            CHANNEL_ID,
            "Temporizador",
            NotificationManager.IMPORTANCE_HIGH
        )
        val manager = getSystemService(NotificationManager::class.java)
        manager.createNotificationChannel(channel)
    }

    override fun onDestroy() {
        stopService()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null

    private fun formatTime(time: Long): String {
        val minutes = time / 60000
        val seconds = (time / 1000) % 60
        return String.format("%02d:%02d", minutes, seconds)
    }
}
