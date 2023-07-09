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
        val a = TreeMap<LocalDateTime, ClimateEntry>()
        for (e in repository.getEveryEntryUniqueByDate()) {
            a[e.from_when] = e;
        }

        model["chartData"] = a;
        return "climate";
    }
}