package com.example.balloonpop

import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import com.example.balloonpop.databinding.ActivityMainBinding

class MainActivity:AppCompatActivity(),View.OnClickListener
{
    val images = arrayOf(R.drawable.blue_balloon, R.drawable.red_balloon)

    val randomToast=arrayOf("blue","red")
    private var tMessage=""

    private lateinit var binding:ActivityMainBinding
    private lateinit var animation:Animation
    private lateinit var mediaPlayer:MediaPlayer

    override fun onCreate(savedInstanceState:Bundle?)
    {
        super.onCreate(savedInstanceState)
        binding=DataBindingUtil.setContentView(this,R.layout.activity_main)

        mediaPlayer=MediaPlayer.create(this,R.raw.balloon_pop)
        animation=AnimationUtils.loadAnimation(this,R.anim.slide_up)

        init()
        applyClickListener()



    }

    private fun balloonClicked(imageView : ImageView)
    {

        if(tMessage==imageView.contentDescription)
        {
            mediaPlayer.start()
            imageView.setImageDrawable(null)
            animation.setAnimationListener(object : Animation.AnimationListener{
                override fun onAnimationStart(p0:Animation?)
                {
                    val id=images.random()
                    if(id==R.drawable.blue_balloon)
                    {
                        imageView.contentDescription="blue"
                    }
                    else
                    {
                        imageView.contentDescription="red"
                    }
                    imageView.setImageResource(id)
                }

                override fun onAnimationEnd(p0:Animation?)
                {
                    tMessage=randomToast.random()
                    Toast.makeText(this@MainActivity, "pop $tMessage balloon", Toast.LENGTH_SHORT).show()
                }

                override fun onAnimationRepeat(p0:Animation?)
                {

                }
            })
            imageView.startAnimation(animation)
        }

        else
        {
            Toast.makeText(this, "pop $tMessage balloon", Toast.LENGTH_SHORT).show()
        }

    }


    private fun init()
    {
        binding.apply {

            val id1=images.random()
            val id2=images.random()
            val id3=images.random()
            val id4=images.random()
            val id5=images.random()
            val id6=images.random()
            val id7=images.random()
            val id8=images.random()
            val id9=images.random()
            val id10=images.random()
            val id11=images.random()
            val id12=images.random()

            val ids = intArrayOf(id1,id2,id3,id4,id5,id6,id7,id8,id9,id10,id11,id12)
            val ivs:Array<ImageView> =arrayOf(iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,iv10,iv11,iv12)


            for (i in ids.indices)
            {
                if (ids[i] == R.drawable.blue_balloon) {
                    ivs[i].contentDescription = "blue"
                } else {
                    ivs[i].contentDescription = "red"
                }
                ivs[i].setImageResource(ids[i])
                ivs[i].startAnimation(animation)
            }


        }
    }

    private fun applyClickListener()
    {
        binding.apply {

            val ivs:Array<ImageView> =arrayOf(iv1,iv2,iv3,iv4,iv5,iv6,iv7,iv8,iv9,iv10,iv11,iv12)

            for(element in ivs)
            {
                element.setOnClickListener(this@MainActivity)
            }
        }
    }

    override fun onClick(p0:View?)
    {

        val imageView = p0 as ImageView
        balloonClicked(imageView)

    }


    override fun onPause()
    {
        super.onPause()
        mediaPlayer.release()
    }

    override fun onResume()
    {
        super.onResume()
        tMessage=randomToast.random()
        Toast.makeText(this, "pop $tMessage balloon", Toast.LENGTH_SHORT).show()
    }



}