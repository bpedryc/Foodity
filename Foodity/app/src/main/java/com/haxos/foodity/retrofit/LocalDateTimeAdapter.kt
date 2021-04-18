package com.haxos.foodity.retrofit

import com.google.gson.TypeAdapter
import com.google.gson.stream.JsonReader
import com.google.gson.stream.JsonWriter
import java.time.LocalDateTime

class LocalDateTimeAdapter : TypeAdapter<LocalDateTime>() {
    override fun write(writer: JsonWriter, localDateTime: LocalDateTime) {
        writer.value(localDateTime.toString())
    }

    override fun read(reader: JsonReader): LocalDateTime {
        return LocalDateTime.parse(reader.nextString())
    }

}