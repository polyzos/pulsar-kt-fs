package io.ipolyzos.utils

import io.ipolyzos.models.ClickEvent
import io.ipolyzos.models.JEventKt
import io.ipolyzos.models.clickEvent
import java.io.File
import java.sql.Timestamp

object FileUtils {
    fun loadJEvents(path: String): List<JEventKt> {
        return File(path).useLines { lines ->
            lines.drop(1)
                .map { toJEvent(it) }
                .toList()
        }
    }

    fun toJEvent(str: String): JEventKt {
        val tokens = str.split(",")
        val eventTime = Timestamp.valueOf(tokens[0].replace(" UTC", ""))
        return JEventKt(
            tokens[7],
            eventTime.time,
            tokens[1],
            tokens[2],
            tokens[3],
            tokens[4],
            tokens[5], tokens[6].toDouble(),
            tokens[8]
        )
    }
    fun readFile(path: String): List<ClickEvent> {
        return File(path).useLines { lines ->
            lines.drop(1)
                .map { strToJEvent(it) }
                .toList()
        }
    }

    private fun strToJEvent(line: String): ClickEvent {
        val tokens = line.split(",")
        return clickEvent {
            eventTime = tokens[0]
            eventType = tokens[1]
            productId = tokens[2]
            categoryId = tokens[3]
            categoryCode = tokens[4]
            brand = tokens[5]
            price = tokens[6]
            userId = tokens[7]
            userSession = tokens[8]
        }
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