package com.happy.ishare.core

import com.tencent.mm.opensdk.modelmsg.WXMediaMessage.IMediaObject

open class ShareParam {
    var title: String? = null
    var description: String? = null
    var thumbData: ByteArray? = null
    var mediaObject: IMediaObject? = null
    var mediaTagName: String? = null
    var messageAction: String? = null
    var messageExt: String? = null
}
