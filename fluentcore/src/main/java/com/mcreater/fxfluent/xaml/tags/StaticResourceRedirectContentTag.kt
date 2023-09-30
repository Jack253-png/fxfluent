package com.mcreater.fxfluent.xaml.tags


class StaticResourceRedirectContentTag : SimpleContentTag<String>() {
    override fun toObject(): String {
        return element().attributeValue("ResourceKey")
    }

    override fun toString(): String {
        return "Redirection {" + super.toString() + "}"
    }
}

