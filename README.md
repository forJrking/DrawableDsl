# 用dsl创建drawable

>通常我们在`res/drawable`下面自定义`shape`和`selector`来满足一些UI的设计，但是由于`xml`最终转换为`drawable`需要经过`IO`和反射创建，会有一些性能损耗，另外随着项目的增大和模块化等，很多通用的样式并不能快速复用，需要合理的项目资源管理规范才能实施。那么通过代码直接创建这些`drawable`，可以在一定程度上降低这些副作用。本篇介绍用`kotlin DSL`简洁的语法特性来实现常见的`drawable`.

## Kotlin应该这样写系列

[SharedPreferences用Kotlin应该这样写](https://juejin.cn/post/6857432013424001038)

[Glide用Kotlin应该这样封装（一）](https://juejin.cn/post/6946396538289537061)

[Glide用Kotlin应该这样封装（二）](https://juejin.cn/post/6948594232626003999)

## 集成和使用
1. 在项目级的build.gradle文件种添加仓库Jitpack：
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
```

2. 添加依赖

```groovy
dependencies {
	implementation 'com.github.forJrking:DrawableDsl:0.0.1’
}
```

3. 抛弃xml创建方式使用代码

```kotlin
// infix用法用于去掉括号更加简洁，详细后面说明
image src = shapeDrawable {
  	//指定shape样式
    shape(ShapeBuilder.Shape.RECTANGLE)
  	//圆角，支持4个角单独设置
  	corner(20f)
  	//solid 颜色
    solid("#ABE2E3")
  	//stroke 颜色，边框dp，虚线设置
    stroke(android.R.color.white, 2f, 5f, 8f)
}
//按钮点击样式
btn.background = selectorDrawable {
  	//默认样式
    normal = shapeDrawable {
        corner(20f)
        gradient(90, R.color.F97794, R.color.C623AA2)
    }
  	//点击效果
    pressed = shapeDrawable {
        corner(20f)
        solid("#84232323")
    }
}
```

## 代码对应效果预览

<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/eb5945489a92401294163a2aea9f6224~tplv-k3u1fbpfcp-zoom-1.image" alt="shape_line" style="zoom:50%;" />
<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a69f71166c004ce5a00087925486426d~tplv-k3u1fbpfcp-zoom-1.image" alt="RECTANGLE" style="zoom:50%;" />
<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/070a4175b9eb4fb5a240bd5804f305e5~tplv-k3u1fbpfcp-zoom-1.image" alt="OVAL" style="zoom:50%;" />
<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5c60c733b84c4636b296daea51a6b1e3~tplv-k3u1fbpfcp-zoom-1.image" alt="LayerList" style="zoom:50%;" />
<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d221f05e5eb146a79b6bab4ca0642461~tplv-k3u1fbpfcp-zoom-1.image" alt="Selector" style="zoom:55%;" />

## 作者

👨岛主  
目前在西安求职中，有内推或岗位请联系我
邮箱：forjrking@sina.com

## 开源协议

```less
Copyright 2020-present forjrking

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

   http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```