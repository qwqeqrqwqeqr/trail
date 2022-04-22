package kr.ac.kgu.app.trail.data.local.datasource

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Trail")
class TrailEntity {
    @PrimaryKey
    val id : Int

    constructor(id: Int) {
        this.id = id
    }
}