package com.demo.drawabledsl

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.github.forjrking.drawable.*
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        DrawableBuilder.app = this.application

        linear.dividerDrawable = shapeDrawable {
            shape(ShapeBuilder.Shape.RECTANGLE)
            solid("#84232323")
            size(-2f, 1f)
        }

        iv1.background = shapeDrawable {
            shape(ShapeBuilder.Shape.RECTANGLE)
            solid("#ABE2E3")
            stroke(android.R.color.white, 2f, 5f, 8f)
        }

        iv2 src shapeDrawable {
            shape(ShapeBuilder.Shape.OVAL)
            solid("#E3ABC2")
            stroke(android.R.color.white, 2f)
            dash(6f, 6f)
            size(200f, 200f)
        }

//     点击效果等
        iv3 src selectorDrawable {
            normal = shapeDrawable {
                corner(20f)
                gradient(90, R.color.F97794, R.color.C623AA2)
            }
            pressed = shapeDrawable {
                corner(20f)
                solid("#84232323")
            }
        }

        //添加padding
        iv4 src insetDrawable {
            drawable = shapeDrawable {
                corner(10f)
                solid("#0396FF")
            }
            setInset(10)
        }
        iv3.setOnClickListener { }

        iv5 src resourceDrawable(R.mipmap.ic_launcher_round)

        iv6 src layerDrawable {
            addLayer(shapeDrawable {
                solid("#ffff00")
            })
            addLayer(resourceDrawable(R.mipmap.ic_launcher))
        }
    }
}