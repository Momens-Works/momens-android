package com.momens.android.data.signin.remote.datasourceimpl

import android.os.Build
import com.momens.android.data.signin.remote.datasource.DeviceLocalDataSource
import javax.inject.Inject

class DeviceLocalDataSourceImpl @Inject constructor() : DeviceLocalDataSource {

    override fun getDeviceModel(): String = Build.MODEL
}
