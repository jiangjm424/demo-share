package com.happy.ishare.core

/**
 *
=================================
｜ title          --------------｜
｜ description    ---thumbData--｜
｜                --------------｜
｜                --------------｜
================================
分享链接   url

attachInfo 用来作为对应分享内容的参数，如果该值不为null，则直接是对应分享渠道的参数，由业务端直接生成
如果业务端只是压根简单分享上面的一个链接，则只需要写入对应上面四个值，分享sdk内部会去转换
 */
class ShareEntity {
    var title: String? = null
    var description: String? = null
    var thumbData: ByteArray? = null
    var url: String? = null
    var scene: Scene? = null
    var attachInfo: ShareParam? = null
}

