package com.darkstoreapp.test.data.mapper

import com.darkstoreapp.test.data.database.HistoryRecordEntity
import com.darkstoreapp.test.domain.entity.HistoryRecord
import javax.inject.Inject

class HistoryRecordMapper @Inject constructor(
) {

    operator fun invoke(entity: HistoryRecordEntity): HistoryRecord {
        return HistoryRecord(
            date = entity.date,
            originalCc = entity.originalCc,
            originalAmount = entity.originalAmount,
            targetCc = entity.targetCc,
            targetAmount = entity.targetAmount
        )
    }

    operator fun invoke(entity: HistoryRecord): HistoryRecordEntity {
        return HistoryRecordEntity(
            lu = System.currentTimeMillis(),
            date = entity.date,
            originalCc = entity.originalCc,
            originalAmount = entity.originalAmount,
            targetCc = entity.targetCc,
            targetAmount = entity.targetAmount
        )
    }
}