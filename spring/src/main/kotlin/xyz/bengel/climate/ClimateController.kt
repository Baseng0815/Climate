package xyz.bengel.climate

import org.springframework.stereotype.Controller
import org.springframework.ui.Model
import org.springframework.ui.set
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RestController
import java.time.LocalDate
import java.time.LocalDateTime
import java.util.*

@Controller
class ClimateController(private val repository: ClimateRepository) {
    @GetMapping("/")
    fun climate(model: Model): String {
        val entries = TreeMap<LocalDateTime, ClimateEntry>()
        for (entry in repository.getEveryEntryUniqueByDate()) {
            entries[entry.from_when] = entry;
        }

        model["chartData"] = entries;
        model["latest"] = repository.getLatest();
        return "climate";
    }
}