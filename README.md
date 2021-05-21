## 放弃xml写drawable，试试DSL写法

<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/a69f71166c004ce5a00087925486426d~tplv-k3u1fbpfcp-zoom-1.image" alt="RECTANGLE" style="zoom:50%;" />

## Kotlin应该这样写系列

[SharedPreferences用Kotlin应该这样写](https://juejin.cn/post/6857432013424001038)

[Glide用Kotlin应该这样封装（一）](https://juejin.cn/post/6946396538289537061)

[Glide用Kotlin应该这样封装（二）](https://juejin.cn/post/6948594232626003999)

[drawable用Kotlin应该这样写](https://juejin.cn/post/6953472037012635655)

## 集成和使用
1. 在项目级的build.gradle文件种添加仓库Jitpack：
```groovy
allprojects {
    repositories {
        ...
        maven { url 'https://jitpack.io' }
    }
}
//添加依赖
dependencies {
	implementation 'com.github.forJrking:DrawableDsl:0.0.3’
}
```

2. 抛弃xml创建方式使用代码

```kotlin
//扩展变量开省略setImageDrawable()括号                                                         
image.src = shapeDrawable {                                    
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
<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/070a4175b9eb4fb5a240bd5804f305e5~tplv-k3u1fbpfcp-zoom-1.image" alt="OVAL" style="zoom:50%;" />
<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/5c60c733b84c4636b296daea51a6b1e3~tplv-k3u1fbpfcp-zoom-1.image" alt="LayerList" style="zoom:50%;" />
<img src="https://p3-juejin.byteimg.com/tos-cn-i-k3u1fbpfcp/d221f05e5eb146a79b6bab4ca0642461~tplv-k3u1fbpfcp-zoom-1.image" alt="Selector" style="zoom:55%;" />

## 注意事项

```kotlin
//如果要使用shape制作圆环 ring，由于ring的api只能通过xml,而且一般要预览
// 这里建议用OVAL方式代替
iv.src = shapeDrawable {
    shape(Shape.OVAL)
    solid(android.R.color.transparent)
    stroke(android.R.color.black, 12f)
    size(200f, 200f)
}

//所有的大小设置，圆角等支持，默认dp
iv.src = shapeDrawable {
    solid(android.R.color.black)
  //设置成px
    size(200f, 200f,isDp = false) 
  //设置dp
    size(200f, 200f)
}
```

## 作者

👨：岛主  
📮：forjrking@sina.com

有内推或岗位请联系我

## 开源协议

```
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
