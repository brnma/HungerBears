package com.example.hungerbears

import android.animation.Animator
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewPropertyAnimator
import android.view.animation.AccelerateInterpolator
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.DefaultItemAnimator
import com.example.hungerbears.databinding.FragmentSelectionBinding
import com.yuyakaido.android.cardstackview.*


class SelectionFragment : Fragment() {
    private var _binding:FragmentSelectionBinding? = null
    private val binding get() = _binding!!

    // private val viewModel: SharedViewModel by activityViewModels()

    private lateinit var manager: CardStackLayoutManager

    private var dummyList = ArrayList<Restaurant>()


    // for animations
    private lateinit var animNoSpin: ViewPropertyAnimator
    private lateinit var animWebSpin: ViewPropertyAnimator
    private lateinit var animYesSpin: ViewPropertyAnimator


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentSelectionBinding.inflate(inflater, container, false)

        initAnimations()
        val chipotle = Restaurant()
        chipotle.setItem("Chipotle", R.drawable.chipotle, "10.1 Miles Away", 2.69f)
        val mcdonalds = Restaurant()
        mcdonalds.setItem("Mcdonalds", R.drawable.mcdonalds, "43.2 Miles Away", 1.2f)
        val jollibee = Restaurant()
        jollibee.setItem("Jollibee", R.drawable.jollibee, "1.5 Miles Away", 4.20f)

        dummyList.add(chipotle)
        dummyList.add(mcdonalds)
        dummyList.add(jollibee)
        dummyList.add(chipotle)
        dummyList.add(mcdonalds)
        dummyList.add(jollibee)
        init()
        binding.cardStackView.layoutManager = manager
        binding.cardStackView.itemAnimator = DefaultItemAnimator()
        binding.cardStackView.adapter = CardStackAdapter(requireContext(), dummyList,)

        binding.yesButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Right)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
        }

        binding.webButton.setOnClickListener{
            Toast.makeText(this.context, "TODO: Opens up a webpage", Toast.LENGTH_SHORT).show()
            binding.webButton.animate().apply {
                duration = 1000
                rotationBy(-360f)
            }.start()

        }

        binding.noButton.setOnClickListener {
            val setting = SwipeAnimationSetting.Builder()
                .setDirection(Direction.Left)
                .setDuration(Duration.Normal.duration)
                .setInterpolator(AccelerateInterpolator())
                .build()
            manager.setSwipeAnimationSetting(setting)
            binding.cardStackView.swipe()
        }

        return binding.root
    }

    private fun initAnimations() {
        // for animations
        animNoSpin = binding.noButton.animate().apply {
            duration = 1000
            rotationXBy(360f)}
        animNoSpin.setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationCancel(animation: Animator?) {
            }
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                binding.noButton.rotationX = 0f
                binding.webButton.rotation = 0f
                binding.yesButton.rotationX = 0f
            }
        })


        animYesSpin = binding.yesButton.animate().apply {
            duration = 1000
            rotationXBy(360f)}

        animYesSpin.setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationCancel(animation: Animator?) {
            }
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                binding.noButton.rotationX = 0f
                binding.webButton.rotation = 0f
                binding.yesButton.rotationX = 0f
            }
        })

        animWebSpin =binding.webButton.animate().apply {
            duration = 1000
            rotationBy(-360f) }
        animWebSpin.setListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) {
            }
            override fun onAnimationCancel(animation: Animator?) {
            }
            override fun onAnimationStart(animation: Animator?) {}

            override fun onAnimationEnd(animation: Animator?) {
                binding.noButton.rotationX = 0f
                binding.webButton.rotation = 0f
                binding.yesButton.rotationX = 0f
            }
        })
    }

    private fun init() {
        manager = CardStackLayoutManager(activity as MainActivity, object: CardStackListener{
            override fun onCardDragging(direction: Direction?, ratio: Float) {
            }

            override fun onCardSwiped(direction: Direction?) {
                if (direction == Direction.Left) {
                    binding.noButton.animate().apply {
                        duration = 1000
                        rotationXBy(360f)
                    }.start()
                }

                else if (direction == Direction.Right) {
                    binding.yesButton.animate().apply {
                        duration = 1000
                        rotationXBy(360f)
                    }.start()
                }
                else if (direction == Direction.Top) {
                    binding.webButton.animate().apply {
                        duration = 1000
                        rotationBy(-360f)
                    }.start()
                }
            }

            override fun onCardRewound() {
            }

            override fun onCardCanceled() {
            }

            override fun onCardAppeared(view: View?, position: Int) {
            }

            override fun onCardDisappeared(view: View?, position: Int) {
            }

        })
        manager.setSwipeableMethod(SwipeableMethod.AutomaticAndManual)
        manager.setVisibleCount(3)
        manager.setTranslationInterval(0.6f)
        manager.setScaleInterval(0.8f)
        manager.setMaxDegree(20.0f)
        manager.setDirections(Direction.FREEDOM)
    }


}