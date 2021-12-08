package io.ipolyzos.utils

import io.ipolyzos.models.ClickEvent
import io.ipolyzos.models.Event
import java.io.File
import java.sql.Timestamp

object FileUtils {

    fun readFile(path: String): List<ClickEvent> {
        return File(path).useLines { lines ->
            lines.drop(1)
                .map { strToEvent(it) }
                .toList()
        }
    }

    private fun strToEvent(line: String): ClickEvent {
        val tokens = line.split(",")
        return ClickEvent(
            Timestamp.valueOf(tokens[0].replace(" UTC", "")).time / 1000,
            tokens[1],
            tokens[2].toLong(),
            tokens[3].toLong(),
            tokens[4],
            tokens[5],
            tokens[6],
            tokens[7].toLong(),
            tokens[8]
        )
    }

    fun readFileJ(path: String): List<Event.JEvent> {
        return File(path).useLines { lines ->
            lines.drop(1)
                .map { strToJEvent(it) }
                .toList()
        }
    }

    private fun strToJEvent(line: String): Event.JEvent {
        val tokens = line.split(",")
        return Event.JEvent.newBuilder()
            .setEventTime(tokens[0])
            .setEventType(tokens[1])
            .setProductId(tokens[2])
            .setCategoryId(tokens[3])
            .setCategoryCode(tokens[4])
            .setBrand(tokens[5])
            .setPrice(tokens[6])
            .setUserId(tokens[7])
            .setUserSession(tokens[8])
            .build()
    }
}

//    fun readEvents(path: String): List<JEventKt> {
//        return File(path).useLines { lines ->
//            lines.drop(1)
//                .map { strToJEvent(it) }
//                .toList()
//        }
//    }
//
//    private fun strToJEvent(line: String): JEventKt {
//        val tokens = line.split(",")
//        return JEventKt.apply {
//            jEvent {
//                eventTime = tokens[0]
//            }
//        }
//    }
//}