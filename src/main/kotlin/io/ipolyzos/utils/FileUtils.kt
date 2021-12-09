package io.ipolyzos.utils

import io.ipolyzos.models.ClickEvent
import io.ipolyzos.models.clickEvent
import java.io.File

object FileUtils {

//    fun readFile(path: String): List<ClickEvent> {
//        return File(path).useLines { lines ->
//            lines.drop(1)
//                .map { strToEvent(it) }
//                .toList()
//        }
//    }
//
//    private fun strToEvent(line: String): ClickEvent {
//        val tokens = line.split(",")
//        return ClickEvent(
//            Timestamp.valueOf(tokens[0].replace(" UTC", "")).time / 1000,
//            tokens[1],
//            tokens[2].toLong(),
//            tokens[3].toLong(),
//            tokens[4],
//            tokens[5],
//            tokens[6],
//            tokens[7].toLong(),
//            tokens[8]
//        )
//    }

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