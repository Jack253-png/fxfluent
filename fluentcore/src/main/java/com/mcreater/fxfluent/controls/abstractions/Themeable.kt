package com.mcreater.fxfluent.controls.abstractions

import com.mcreater.fxfluent.xaml.style.AppColorTheme


interface Themeable {
    fun onUpdateTheme(theme: AppColorTheme?)
}

