package com.keremturker.coronavirusapi.ui.activity

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(app: Application) : AndroidViewModel(app) {}