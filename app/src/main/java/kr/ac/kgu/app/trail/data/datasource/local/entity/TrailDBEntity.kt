package kr.ac.kgu.app.trail.data.datasource.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Trail")
class TrailDBEntity {
    @PrimaryKey
    val id : Int

    constructor(id: Int) {
        this.id = id
    }
}