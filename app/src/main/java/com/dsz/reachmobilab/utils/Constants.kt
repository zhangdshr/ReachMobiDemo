package com.dsz.reachmobilab.utils

class Constants {
    companion object {

        const val DOMAIN_NAME = "https://www.thesportsdb.com/"

        const val API_VERSION = "api/v1/"

        const val API_KEY = "1/"

        const val RESPONSE_TYPE = "Teams"

        const val RESPONSE_R = "json/"

        const val NETWORK_TIMEOUT = 3000

        const val BASE_URL = DOMAIN_NAME + API_VERSION + RESPONSE_R + API_KEY

        const val URL_SEARCH_TEAMS_BY_NAME = "searchteams.php"
    }
}