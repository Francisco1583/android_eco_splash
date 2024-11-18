package com.example.ecosplash.model

import android.annotation.SuppressLint
import android.app.Notification
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.Service
import android.content.Intent
import android.os.Build
import android.os.IBinder
import android.util.Log
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
        Log.d("TimerService", "Recibido intent con acción: ${intent?.action}")
        if (intent == null || intent.action == null) {
            Log.e("TimerService", "Intent o acción nula, deteniendo servicio")
            stopSelf()
            return START_NOT_STICKY
        }

        when (intent.action) {
            ACTION_START -> {
                remainingTime = intent.getLongExtra(EXTRA_TIME, 1200000L)
                Log.d("TimerService", "Iniciando temporizador con tiempo: $remainingTime")
                if (!isRunning) {
                    startForegroundService()
                    startTimer()
                }
            }
            ACTION_STOP -> {
                Log.d("TimerService", "Deteniendo servicio")
                stopSelf()
            }
        }
        return START_NOT_STICKY
    }





    @SuppressLint("ForegroundServiceType")
    private fun startForegroundService() {
        try {
            val notification = createNotification(remainingTime)
            startForeground(NOTIFICATION_ID, notification)
            Log.d("TimerService", "Servicio en primer plano iniciado correctamente.")
        } catch (e: SecurityException) {
            Log.e("TimerService", "Error al iniciar servicio en primer plano: ${e.message}")
            stopSelf() // Detén el servicio si ocurre un error crítico
        }
    }



    private fun startTimer() {
        isRunning = true
        timerJob = CoroutineScope(Dispatchers.Default).launch {
            while (isRunning && remainingTime > 0) {
                delay(1000L)
                remainingTime -= 1000L
                if (shouldSendNotification(remainingTime)) {
                    updateNotification(remainingTime)
                }

                // Aquí puedes enviar broadcast para actualizar la UI si es necesario
                val intent = Intent("TIMER_UPDATED")
                intent.putExtra("remaining_time", remainingTime)
                sendBroadcast(intent)
            }
            if (remainingTime <= 0) {
                stopSelf()
            }
        }
    }
    private fun shouldSendNotification(remainingTime: Long): Boolean {
        return when {
            remainingTime % (60 * 1000*15) == 0L -> true
            remainingTime % (60 * 1000*10) == 0L -> true
            remainingTime % (60 * 1000*5) == 0L -> true
            remainingTime <= 10 * 1000 -> true
            else -> false
        }
    }
    private fun textNotification(remainingTime: Long): String {
        return when {
            remainingTime % (60 * 1000*15) == 0L -> "haz perdido tu racha, "
            remainingTime % (60 * 1000*10) == 0L -> "Corre queda menos tiempo, "
            remainingTime % (60 * 1000*5) == 0L -> "solo te quedan 5 minutos :(, "
            remainingTime <= 10 * 1000 -> "Ya no hay tiempo, "
            else -> ""
        }
    }

    private fun updateNotification(time: Long) {
        val notification = createNotification(time)
        val notificationManager = getSystemService(NotificationManager::class.java)
        notificationManager.notify(NOTIFICATION_ID, notification)
    }

    private fun createNotification(time: Long): Notification {
        val minutes = time / 60000
        val seconds = (time / 1000) % 60
        val timeText = String.format("%02d:%02d", minutes, seconds)
        val textNotification = textNotification(remainingTime)
        return NotificationCompat.Builder(this, CHANNEL_ID)
            .setContentTitle("Temporizador")
            .setContentText(textNotification +"Tiempo restante: $timeText")
            .setSmallIcon(android.R.drawable.ic_notification_overlay)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
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
        isRunning = false
        timerJob?.cancel()
        super.onDestroy()
    }

    override fun onBind(intent: Intent?): IBinder? = null
}
