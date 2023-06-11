/*
 * Copyright 2023 The IShare Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

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
