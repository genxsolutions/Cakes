package com.genxsol.cakesapp.common.networkhelper

import com.example.utilities.NetworkHelper

class TestNetworkHelper : NetworkHelper {
    override fun isNetworkConnected(): Boolean {
        return true
    }
}