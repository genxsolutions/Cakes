package com.genxsol.cakesapp.common.networkhelper

class TestNetworkHelper : NetworkHelper {
    override fun isNetworkConnected(): Boolean {
        return true
    }
}