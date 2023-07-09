package xyz.bengel.climate

import jakarta.persistence.*
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.CrudRepository
import java.time.LocalDateTime

@Entity
@Table(name = "climate")
class ClimateEntry(
    var temperature: Float,
    var humidity: Float,
    @Id var from_when: LocalDateTime
)

interface ClimateRepository : CrudRepository<ClimateEntry, Long> {
    @Query("SELECT DISTINCT ON (DATE(from_when)) * FROM climate", nativeQuery = true)
    //@Query("SELECT * FROM climate", nativeQuery = true)
    fun getEveryEntryUniqueByDate(): Iterable<ClimateEntry>
    override fun findAll(): Iterable<ClimateEntry>
}