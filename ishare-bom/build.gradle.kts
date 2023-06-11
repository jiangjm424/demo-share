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
import buildhelper.publicModules
import buildhelper.setupPublishing
import com.vanniktech.maven.publish.JavaPlatform

plugins {
    id("java-platform")
    id("com.vanniktech.maven.publish.base")
}

setupPublishing {
    configure(JavaPlatform())
}

dependencies {
    constraints {
        for (subproject in rootProject.subprojects) {
            if (subproject.name in publicModules) {
                api(subproject)
            }
        }
    }
}