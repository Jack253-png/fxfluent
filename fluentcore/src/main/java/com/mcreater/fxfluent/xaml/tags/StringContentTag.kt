package com.mcreater.fxfluent.xaml.tags


class StringContentTag : SimpleContentTag<String?>() {
    override fun toObject(): String {
        return element?.data.toString()
    }
}

