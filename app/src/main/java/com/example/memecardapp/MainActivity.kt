package com.example.memecardapp

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.FragmentManager
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.viewpager2.widget.CompositePageTransformer
import androidx.viewpager2.widget.MarginPageTransformer
import androidx.viewpager2.widget.ViewPager2
import com.bumptech.glide.Glide
import com.example.memeapp.data.network.apiData.Meme
import com.example.memecardapp.data.repository.MemesRepository
import com.example.memecardapp.databinding.ActivityMainBinding
import com.example.memecardapp.di.AppModule
import com.example.memecardapp.presentation.meme_list.ui.adapters.ViewPagerAdapter
import com.example.memecardapp.presentation.meme_list.model.MemesViewModel
import com.example.memecardapp.presentation.meme_list.model.MemesViewModelFactory
import com.google.android.material.bottomsheet.BottomSheetBehavior

import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject
import kotlin.math.abs

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    @Inject
    lateinit var memesViewModel : MemesViewModel
    private lateinit var binding:ActivityMainBinding
    private lateinit var viewPager2 : ViewPager2
    private lateinit var handler : Handler
    lateinit var memeRepository : MemesRepository
    lateinit var memeDetails : List<Meme>


    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

//        var bottomState = BottomSheetBehavior.State()

        init()
        setUpTransformer()

        var viewPager  = binding.viewpager2
        val deleteBtn = binding.deleteBtn
        val prevBtn = binding.prevBtn
        val nextBtn = binding.nextBtn
        val shareBtn = binding.shareBtn

        BottomSheetBehavior.from(binding.sheet).apply {
            peekHeight = 250
            this.state = BottomSheetBehavior.STATE_COLLAPSED
        }.addBottomSheetCallback(object:BottomSheetBehavior.BottomSheetCallback(){
            override fun onStateChanged(bottomSheet : View, newState : Int) {
                if(BottomSheetBehavior.STATE_EXPANDED == newState){
                    binding.memeSwipeUpDetails.text = memeDetails[viewPager.currentItem].name
                    binding.memeExtraDetails.text = "ID : ${memeDetails[viewPager.currentItem].id} "
                    binding.memeUrl.text = "URL : ${memeDetails[viewPager.currentItem].url}"
                    Glide.with(this@MainActivity)
                        .load(memeDetails[viewPager.currentItem].url)
                        .into(binding.memeDetailsImageView)
                }
            }

            override fun onSlide(bottomSheet : View, slideOffset : Float) {
                Log.d("ON Slide","$bottomSheet $slideOffset")
            }
        })


        //builder calls
        val apiInterfaceBuilder = AppModule.provideRetrofitBuilder()
        val getapiInterface = AppModule.providesApiService(apiInterfaceBuilder)

        val dbProvideInstance = AppModule.provideDatabase(this)
        val getdbInstance = AppModule.providesMemeDao(dbProvideInstance)

        memeRepository = MemesRepository(getapiInterface,getdbInstance)
        memesViewModel = ViewModelProvider(this,MemesViewModelFactory(memeRepository)).get(
            MemesViewModel::class.java)

        memesViewModel.memes.observe(this){
            viewPager  = binding.viewpager2
            Log.d("TAG",viewPager.toString())
            val adapter = ViewPagerAdapter(this,it.data.memes)
            memeDetails = it.data.memes
            viewPager.adapter = adapter
            viewPager.offscreenPageLimit = 3
        }

        //btn actions
        deleteBtn.setOnClickListener {
            Log.d("Deleted item ", viewPager.currentItem.toString())
            memesViewModel.memes.observe(this){
                memesViewModel.deleteMeme(it.data.memes[viewPager.currentItem])
                Toast.makeText(applicationContext,"Deleted ${it.data.memes[viewPager.currentItem].name}",Toast.LENGTH_SHORT).show()
            }
        }

        prevBtn.setOnClickListener {
            if (viewPager.currentItem == 0){
                Toast.makeText(this,"first meme reached",Toast.LENGTH_SHORT).show()
            }
            else{
                viewPager.currentItem--
            }
        }

        nextBtn.setOnClickListener {
            viewPager.currentItem ++
        }
        // share intent
        shareBtn.setOnClickListener {
            memesViewModel.memes.observe(this){
                val sendIntent: Intent = Intent().apply {
                    action = Intent.ACTION_SEND
                    putExtra(Intent.EXTRA_TEXT, " share this meme : ${it.data.memes[viewPager.currentItem].name} ${it.data.memes[viewPager.currentItem].url}")
                    type = "text/plain"
                }
                val shareIntent = Intent.createChooser(sendIntent, null)
                startActivity(shareIntent)
            }
        }
    }

    private fun init(){
        viewPager2 = findViewById(R.id.viewpager2)
        handler = Handler(Looper.myLooper()!!)
        viewPager2.clipToPadding = false
        viewPager2.clipChildren = false
        viewPager2.getChildAt(0).overScrollMode = RecyclerView.OVER_SCROLL_NEVER
    }
    private fun setUpTransformer(){
        val transformer = CompositePageTransformer()
        transformer.addTransformer(MarginPageTransformer(40))
        transformer.addTransformer{
                page,position ->
            val r = 1- abs(position)
            page.scaleY = 0.80f + r * 0.24f
        }
        viewPager2.setPageTransformer(transformer)
    }


    override fun onBackPressed() {
        AlertDialog.Builder(this)
            .setTitle("Do you want to exit?")
            .setMessage("")
            .setPositiveButton("Yes"){
                it , _ ->
                it.dismiss()
                super.onBackPressed()
            }
            .setNegativeButton("No",null)
            .show()
    }
}