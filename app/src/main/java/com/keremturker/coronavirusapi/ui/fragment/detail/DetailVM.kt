package com.keremturker.coronavirusapi.ui.fragment.detail

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailVM @Inject constructor(app: Application) : AndroidViewModel(app)