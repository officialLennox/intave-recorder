package de.lennox.ir.mongodb.query

import com.mongodb.client.model.Filters
import com.mongodb.client.model.Updates
import de.lennox.ir.mongodb.Converter
import de.lennox.ir.mongodb.StringConverter
import org.bson.conversions.Bson

class RecordQuery(
    private val type: RecordQueryType,
    private val obj: Any
) : Query {
    override fun updateQuery(): Bson {
        return Updates.set(type.type, type.converter.convert(obj))
    }

    override fun findQuery(): Bson {
        return Filters.eq(type.type, type.converter.convert(obj))
    }
}

enum class RecordQueryType(
    val type: String,
    val converter: Converter<*>
) {
    RECORD_ID("recordId", StringConverter())
}