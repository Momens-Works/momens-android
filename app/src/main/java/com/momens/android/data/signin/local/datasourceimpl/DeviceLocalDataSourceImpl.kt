package com.momens.android.data.signin.local.datasourceimpl

import android.os.Build
import com.momens.android.data.signin.local.datasource.DeviceLocalDataSource
import javax.inject.Inject

class DeviceLocalDataSourceImpl @Inject constructor() : DeviceLocalDataSource {

    override fun getDeviceModel(): String = Build.MODEL
}
