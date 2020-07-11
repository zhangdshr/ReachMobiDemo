package com.dsz.reachmobilab.utils

import com.dsz.reachmobilab.db.model.Leagues

object CommonUtil {

    fun convertLeaguesToArrayString(input: List<Leagues>): Array<String> {
        var list = Array(input.size, { " " })
        for (i in 0 until list.size) {
            list[i] = input[i].leagueName
        }
        return list
    }

}