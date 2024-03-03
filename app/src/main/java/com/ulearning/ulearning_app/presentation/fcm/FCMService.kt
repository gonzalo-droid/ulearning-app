package com.ulearning.ulearning_app.presentation.fcm

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.core.app.NotificationCompat
import androidx.core.content.ContextCompat
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.google.gson.Gson
import com.ulearning.ulearning_app.R
import com.ulearning.ulearning_app.core.extensions.toJsonElement
import com.ulearning.ulearning_app.core.utils.Config
import com.ulearning.ulearning_app.data.remote.entities.response.ConversationResponse
import com.ulearning.ulearning_app.domain.model.Conversation
import com.ulearning.ulearning_app.domain.model.FirstMessage
import com.ulearning.ulearning_app.domain.model.User
import com.ulearning.ulearning_app.presentation.features.home.view.HomeActivity
import com.ulearning.ulearning_app.presentation.features.message.MessageActivity
import okhttp3.internal.notify

class FCMService : FirebaseMessagingService() {

    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)

        message.notification?.let {
            sendNotification(title = it.title!!, body = it.body!!, data = message.data)
        }
    }

    private fun sendNotification(title: String, body: String, data: Map<String, String>) {

        val soundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)

        val notificationManager =
            getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        val channelId = getString(R.string.notification_channel_id)
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {

            val notificationChannel = NotificationChannel(
                getString(R.string.notification_channel_id),
                getString(R.string.notification_channel_id),
                NotificationManager.IMPORTANCE_DEFAULT
            )

            notificationChannel.description = getString(R.string.notification_channel_description)
            notificationChannel.enableLights(true)
            notificationChannel.lightColor = ContextCompat.getColor(this, R.color.white)
            notificationChannel.vibrationPattern = longArrayOf(0, 1000, 500, 1000)
            notificationChannel.enableVibration(true)
            notificationChannel.setSound(
                soundUri,
                AudioAttributes.Builder().setUsage(AudioAttributes.USAGE_NOTIFICATION)
                    .setContentType(AudioAttributes.CONTENT_TYPE_SPEECH).build()
            )
            notificationManager.createNotificationChannel(notificationChannel)
        }

        val intent = Intent(this, HomeActivity::class.java)

        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)

        val pendingIntent = PendingIntent.getActivity(
            this,
            0,
            intent(data = data).apply { flags = Intent.FLAG_ACTIVITY_CLEAR_TOP },
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.S) {

                PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_MUTABLE
            } else {

                PendingIntent.FLAG_ONE_SHOT
            }
        )

        val notificationBuilder = NotificationCompat.Builder(this, channelId)
            .setSmallIcon(R.drawable.ic_chat)
            .setColor(ContextCompat.getColor(this, R.color.green))
            .setContentTitle(title)
            .setContentText(body)
            .setAutoCancel(true)
            .setSound(soundUri)
            .setContentIntent(pendingIntent)

        notificationManager.notify(
            0,
            notificationBuilder.build()
        )
    }

    private fun intent(data: Map<String, String>): Intent {
        Log.d("LogFCMService", data["conversation"]!!.isNotEmpty().toString())
        return if (data.isNotEmpty()) {
            return if (data["conversation"]!!.isNotEmpty()) {
                val jsonObject = data["conversation"]!!.toJsonElement().asJsonObject
                Log.d("LogFCMService", jsonObject.toString())

                val gson = Gson()
                val conversation = gson.fromJson(jsonObject, ConversationResponse::class.java)

                Intent(this, MessageActivity::class.java).apply {
                    putExtra(
                        Config.CONVERSATION_PUT,
                        conversationToDomain(conversation as ConversationResponse)
                    )
                }
            } else {
                Intent(this, HomeActivity::class.java)
            }
        } else {

            Intent(this, HomeActivity::class.java)
        }
    }

    fun conversationToDomain(data: ConversationResponse): Conversation {
        return data.let {
            Conversation(
                canByReply = it.canByReply,
                courseId = it.courseId,
                firstMessage = FirstMessage(
                    classification = it.firstMessage?.classification,
                    content = it.firstMessage?.content,
                    id = it.firstMessage?.id,
                    publishedAt = it.firstMessage?.publishedAt,
                    sendBy = it.firstMessage?.sendBy.let { user ->
                        User(
                            address = user?.address,
                            dateOfBirth = user?.dateOfBirth,
                            documentNumber = user?.documentNumber,
                            documentType = user?.documentType,
                            email = user?.email,
                            firstName = user?.firstName,
                            gender = user?.gender,
                            id = user?.id,
                            lastName = user?.lastName,
                            name = user?.name,
                            phone = user?.phone,
                            phoneCode = user?.phoneCode,
                            role = user?.role,
                            secondLastName = user?.secondLastName,
                        )
                    },
                    status = it.firstMessage?.status,
                    subject = it.firstMessage?.subject,
                    type = it.firstMessage?.type,
                    userIds = it.firstMessage?.userIds,
                    uuid = it.firstMessage?.uuid,
                ),
                id = it.courseId,
                isBroadcast = it.isBroadcast,
                replyToAuthor = it.replyToAuthor,
                toSupport = it.toSupport,
                uuid = it.uuid
            )
        }
    }
}
