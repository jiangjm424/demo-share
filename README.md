为了方便的集成各家三方平台的分享组件，我们这里实现了微信和qq的分享
#### 1
集成方式
```kts
dependencies {
    val version = "0.0.4"

    // 必选
    implementation("io.github.jiangjm424:ishare-core:$version")
    // 可选 - 支持qq分享，目前依赖的qq平台版本是3.52.0（可在jcenter上在线依赖）
    implementation("io.github.jiangjm424:ishare-qq:$version")
    // 可选 - 支持微信分享
    implementation("io.github.jiangjm424:ishare-wechat:$version")

    // 可选 - 分享弹框 - 建议app按照这个库的实现来自己实现分享框ui以满足自己的app要求
    implementation("io.github.jiangjm424:ishare-ui:$version")
}
```
当然还有一个简单的方法，直接依赖全部的lib
```kt
dependencies {
    val version = "0.0.4"
    implementation("io.github.jiangjm424:ishare-bom:$version")
}
```

#### 2
在依赖后，需要在app启动中进行初始化当依赖了
```kt
class AppDemo : Application() {
    override fun onCreate() {
        super.onCreate()
        ShareSDK.init(
            this,
            BuildConfig.DEBUG,
            listOf(FactoryShareQQ(this), FactoryShareWechat(this), FactoryShareLink(this)),
        )
    }
}
```
> 需要注意的是FactoryShareQQ 和 FactoryShareWechat 分别是在ishare-qq 和 ishare-wechat中，所以只有依赖了这两个库的时候才能在初始分享库的时候添加进来

#### 3
在对应的分享平台中申请appid （https://open.tencent.com/）[https://open.tencent.com/]
并在编译脚本中添加
app模块下的build.gradle
```kts
android {
    defaultConfig {
        applicationId = "com.share.sample"
        manifestPlaceholders["WECHAT_APPID"] = "xxxxx"
        manifestPlaceholders["QQ_APPID"] = "xxxxx"
    }
}

```
最后就是使用阶段了，使用也简单，看样例：
```kt
ShareCore.Builder(scene.platform)
    .shareEntity(
        ShareEntity().apply {
            title = "title"
            description = "desc"
            url = "www.baidu.com"
            this.scene = scene
            thumbData = BitmapFactory.decodeResource(resources, R.drawable.wechat)
                .toThumbnail(150, 150).toByteArray()
        },
    ).scene(scene)
    .build()?.share()
```
> 在share方法中还可以提供一个回调来监听分享结果，不过目前这个在微信上不是很完善（微信的分享结果是在activty的回调结果中异步传过来的，目前我们的实现未去处理这一块，后续看情况可以对这一块进行优化）
这里我们使用的是构建者模式来方便引用分享api, 创建builder时需要指定分享的平台，需要继承自Platform， 指定分享的平台下的场景，如qq好友，qq空间等，继承自Scene。

最后，就是我们需要分享的内容ShareEntity，看一下这个类的定义
```kt
/**
 *
=================================
｜ title          --------------｜
｜ description    ---thumbData--｜
｜                ---thumbData--｜
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
    var attachInfo: ShareParam? = null
}
```

该类的注释中也已经说明，如果是简单的分享链接，可以在ShareEntity中写上简单的四个字段， 如果是比较复杂一些的分享，比如分享到微信小程序啥的，可以使用attachInfo给到应的WeChatParam，并按小程序的分享要求添加对应的内容
之后，在分享中会优先使用attachInfo的内容进行分享， 这里我们简单的看一下源码就清楚了
```kt
    override fun convertShareEntity2ShareParam(scene: Scene?, entity: ShareEntity): WechatShareParam {
        val param = entity.attachInfo as? WechatShareParam
        return param ?: WechatShareParam().apply {
            title = entity.title
            description = entity.description
            webPageUrl = entity.url
            thumbData = entity.thumbData
            mTargetScene = scene as? WechatScene ?: WechatScene.Session
        }
    }
```

最后看一下我们的效果吧,不会制作gif, 先和mp4将就一下吧，有会的大佬还请留言，上门讨教一下。
**视频中未有设置appid，所以分享时提示失败**
