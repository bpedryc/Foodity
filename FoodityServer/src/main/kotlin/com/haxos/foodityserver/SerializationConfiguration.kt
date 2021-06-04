package com.haxos.foodityserver

import com.fasterxml.jackson.annotation.JsonBackReference
import com.fasterxml.jackson.annotation.JsonIgnore
import com.google.gson.ExclusionStrategy
import com.google.gson.FieldAttributes
import com.google.gson.GsonBuilder
import com.google.gson.typeadapters.RuntimeTypeAdapterFactory
import com.haxos.foodityserver.rest.notes.noteelement.ImageNoteElement
import com.haxos.foodityserver.rest.notes.noteelement.ListNoteElement
import com.haxos.foodityserver.rest.notes.noteelement.NoteElement
import com.haxos.foodityserver.rest.notes.noteelement.TextNoteElement
import org.springframework.context.annotation.Configuration
import org.springframework.http.converter.HttpMessageConverter
import org.springframework.http.converter.json.GsonHttpMessageConverter
import org.springframework.web.servlet.config.annotation.EnableWebMvc
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@EnableWebMvc
@Configuration
class SerializationConfiguration : WebMvcConfigurer {

    override fun configureMessageConverters(converters: MutableList<HttpMessageConverter<*>>) {
        converters.add(customGsonHttpMessageConverter())
        super.configureMessageConverters(converters)
    }

    fun customGsonHttpMessageConverter() : GsonHttpMessageConverter {
        val gson = GsonBuilder()
            .registerTypeAdapterFactory(typeAdapterFactory())
            .addSerializationExclusionStrategy(serializationExclusionStrategy)
            .create()

        val messageConverter = GsonHttpMessageConverter()
        messageConverter.gson = gson
        return messageConverter
    }

    private fun typeAdapterFactory() : RuntimeTypeAdapterFactory<NoteElement> {
        return RuntimeTypeAdapterFactory.of(NoteElement::class.java)
            .registerSubtype(TextNoteElement::class.java)
            .registerSubtype(ListNoteElement::class.java)
            .registerSubtype(ImageNoteElement::class.java)
    }

    val serializationExclusionStrategy = object : ExclusionStrategy {
        override fun shouldSkipField(f: FieldAttributes): Boolean =
            f.getAnnotation(JsonIgnore::class.java) != null ||
            f.getAnnotation(JsonBackReference::class.java) != null

        override fun shouldSkipClass(clazz: Class<*>?): Boolean = false
    }
}