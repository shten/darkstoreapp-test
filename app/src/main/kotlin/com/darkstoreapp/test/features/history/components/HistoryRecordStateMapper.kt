package com.darkstoreapp.test.features.history.components

import com.darkstoreapp.test.domain.entity.HistoryRecord
import java.time.format.DateTimeFormatter
import javax.inject.Inject

class HistoryRecordStateMapper @Inject constructor() {

    operator fun invoke(record: HistoryRecord): HistoryRecordState {
        return HistoryRecordState(
            date = DATE_FORMATTER.format(record.date),
            original = String.format("%s %s", record.originalAmount.toString(), record.originalCc),
            target = String.format("%s %s", record.targetAmount.toString(), record.targetCc),
        )
    }

    companion object {
        private val DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy.MM.dd")
    }
}